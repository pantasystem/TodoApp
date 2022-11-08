import SwiftUI
import shared

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
			ContentView()
                .environmentObject(Fruits())
		}
	}
}
class Fruits: ObservableObject {
    @Published var name = "りんご"
    @Published var price = 100
}
