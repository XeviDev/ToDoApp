package com.xevidev.todoapp.addtask.domain

import com.xevidev.todoapp.addtask.data.TaskRepository
import com.xevidev.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    operator fun invoke():Flow<List<TaskModel>>{
        return taskRepository.tasks
    }

}