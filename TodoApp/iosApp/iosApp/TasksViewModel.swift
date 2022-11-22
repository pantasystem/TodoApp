import SwiftUI
import Foundation
import shared
import KMPNativeCoroutinesCombine
import Combine

class TasksViewModel : ObservableObject {
    @Published var tasks: [Task] = []
    @Published var error: Error? = nil
    @Published var isLoading: Bool = true
    
    private var cancellables: Set<AnyCancellable> = []
        
    deinit {
        cancellables.forEach { cancellable in
            cancellable.cancel()
        }
    }
    
    func load() {
        let usecase = IOSModule().provideLoadTasksUseCase()
        let future = createFuture(for: usecase.invokeNative())
        future.receive(on: RunLoop.main).sink(receiveCompletion: {e in
            switch e {
            
            case .failure(let e):
                self.error = e
            case .finished:
                self.isLoading = false
            }
            
        }, receiveValue: { result in
            self.tasks = (result as! [Task])
        }).store(in: &cancellables)
    }
    
    func create(title: String, description: String) {
        IOSModule().provideCreateTaskUseCase().invoke(
            args: CreateTask(title: title, description: description), completionHandler: { task, err in
                self.load()
        })
    }
}
