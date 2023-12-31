package com.xevidev.todoapp.addtask.data

import com.xevidev.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

//Repository its the gate for enter to data layer
@Singleton
class TaskRepository @Inject constructor(private val taskDAO: TaskDAO) {

    val tasks: Flow<List<TaskModel>> =
        //A map its like a forEach but it returns the list that iterate
        taskDAO.getTasks().map { items ->
            items.map {
                TaskModel(it.id, it.task, it.selected)
            }
        }

    suspend fun add(taskModel: TaskModel) {
        taskDAO.addTask(taskModel.toData())
    }

    suspend fun update(taskModel: TaskModel) {
        taskDAO.updateTask(taskModel.toData())
    }

    suspend fun delete(taskModel: TaskModel) {
        taskDAO.deleteTask(taskModel.toData())

    }

    //A extension function
    private fun TaskModel.toData():TaskEntity{
        return TaskEntity(this.id, this.task, this.selected)
    }
}