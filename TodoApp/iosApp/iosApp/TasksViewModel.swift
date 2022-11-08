//
//  TasksViewModel.swift
//  iosApp
//
//  Created by 木下雄一朗 on 2022/11/08.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class TasksViewModel : ObservableObject {
    @Published var tasks: [Task] = []
    
}
