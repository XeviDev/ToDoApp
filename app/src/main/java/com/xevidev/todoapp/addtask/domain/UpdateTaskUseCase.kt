package com.xevidev.todoapp.addtask.domain

import com.xevidev.todoapp.addtask.data.TaskRepository
import com.xevidev.todoapp.addtask.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.update(taskModel)
    }

}