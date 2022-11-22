//
//  TaskEditorScreen.swift
//  iosApp
//
//  Created by 木下雄一朗 on 2022/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct TaskEditorScreen: View {
    @ObservedObject var taskEditorViewModel: TaskEditorViewModel
    @ObservedObject var tasksViewModel: TasksViewModel
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    init(tasksViewModel: TasksViewModel) {
        self.taskEditorViewModel = TaskEditorViewModel()
        self.tasksViewModel = tasksViewModel
    }

    var body: some View {
        VStack {
            Form {
                TextField("タスク名", text: $taskEditorViewModel.title)
                
            }
            Button(action: {
                tasksViewModel.create(title: taskEditorViewModel.title, description: taskEditorViewModel.description)
                
                taskEditorViewModel.onCreateButtonClicked()
                presentationMode.wrappedValue.dismiss()
                
            }) {
                Text("作成")
                    .frame(width: UIScreen.main.bounds.width - 30, height: 45)
                    
            }
            .foregroundColor(Color.white)
            .background(Color.orange)
            .cornerRadius(5)
        }
        .navigationTitle("タスクを作成")
        
    }
}

