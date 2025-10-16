import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init() {
        AppInitializer().doInit(app: AppContext())
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
