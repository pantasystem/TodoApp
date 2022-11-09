import SwiftUI
import Foundation
import shared
import KMPNativeCoroutinesCombine
import Combine


class AppViewModel : ObservableObject {
    @Published var isLoading: Bool = true
    @Published var isFailure: Bool = false
    @Published var account: Account? = nil
    @Published var error: Error? = nil
    let usecase: LoadAccountUseCase = LoadAccountUseCase(accountRepository: IOSModule().provideAccountRepository())
    
    private var cancellables: Set<AnyCancellable> = []
        
    deinit {
        cancellables.forEach { cancellable in
            cancellable.cancel()
        }
    }
    
    func load() {
        
//        let future = createFuture(for: IOSModule().provideLoadAccountUseCase().invokeNative())
        
        let future = createFuture(for: usecase.invokeNative())
        
        
        future.receive(on: RunLoop.main).sink(receiveCompletion: { e in
            switch e {
            case .failure(let e):
                self.isFailure = true
                self.isLoading = false
                self.error = e
            case .finished:
                self.isFailure = false
                self.isLoading = true
            }
            
            
        }, receiveValue: { result in
            self.account = (result as! Account)
            self.isLoading = false
            self.isFailure = false
        }).store(in: &cancellables)

            
        
    }
    
}
