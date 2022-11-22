//
//  TaskListScreen.swift
//  iosApp
//
//  Created by 木下雄一朗 on 2022/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct TaskListScreen: View {
    @ObservedObject var tasksViewModel: TasksViewModel
    init() {
        self.tasksViewModel = TasksViewModel()
        
        self.tasksViewModel.load()
    }
    var body: some View {
        NavigationView {
            List {
                ForEach(tasksViewModel.tasks, id: \.id) { task in
                    Text(task.title)
                }
            }.navigationTitle("タスク一覧").toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(
                        destination: TaskEditorScreen(tasksViewModel: tasksViewModel)
                    ) {
                        Image(systemName: "plus")
                    }
                }
            }
        }
    }
}

struct TaskListScreen_Previews: PreviewProvider {
    static var previews: some View {
        TaskListScreen()
    }
}
