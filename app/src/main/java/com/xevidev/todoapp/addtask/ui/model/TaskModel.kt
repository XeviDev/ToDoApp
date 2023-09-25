package com.xevidev.todoapp.addtask.ui.model

//This is our data model
// Add the time in milis to create an unique id
data class TaskModel(
    //We dont need to add the first arg because we already give it when create a TaskModel
    val id: Long = System.currentTimeMillis(),
    val task: String,
    var selected: Boolean = false
)