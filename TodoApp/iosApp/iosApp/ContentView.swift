import SwiftUI
import shared

struct ContentView: View {
	
    @ObservedObject var appViewModel: AppViewModel
    init() {
        self.appViewModel = AppViewModel()
        self.appViewModel.load()
    }
	var body: some View {
        if appViewModel.isLoading && appViewModel.account == nil {
            ProgressView("")
        } else if appViewModel.isFailure && appViewModel.account == nil {
            Text("Error:\(appViewModel.error!.localizedDescription)")
        } else if appViewModel.account != nil {
            TaskListScreen()
        }
        
            
	}
}

struct AppView : View {
    var body: some View {
        Text("Hoge")
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
