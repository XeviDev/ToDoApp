package com.xevidev.todoapp.addtask.ui.model

//This is our data model
// Add the time in milis to create an unique id
data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    val task: String,
    var selected: Boolean
)