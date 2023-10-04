package com.xevidev.todoapp.addtask.ui

import com.xevidev.todoapp.addtask.ui.model.TaskModel

sealed interface TasksUIState {
    //If we not gona recieve data, use object
    object Loading:TasksUIState
    //Then, if we need to recieve any data, use a data class
    data class Error(val throwable: Throwable):TasksUIState
    data class Success(val tasks:List<TaskModel>): TasksUIState
}