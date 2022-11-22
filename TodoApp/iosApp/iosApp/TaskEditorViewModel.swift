import SwiftUI
import shared


class TaskEditorViewModel : ObservableObject {
    @Published var title = ""
    @Published var description = ""
    

    func onCreateButtonClicked() {
        self.title = ""
        self.description = ""
        
    }
}
