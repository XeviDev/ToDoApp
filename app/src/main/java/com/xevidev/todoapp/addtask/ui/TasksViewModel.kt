package com.xevidev.todoapp.addtask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xevidev.todoapp.addtask.domain.AddTaskUseCase
import com.xevidev.todoapp.addtask.domain.GetTasksUseCase
import com.xevidev.todoapp.addtask.domain.UpdateTaskUseCase
import com.xevidev.todoapp.addtask.ui.TasksUIState.Error
import com.xevidev.todoapp.addtask.ui.TasksUIState.Loading
import com.xevidev.todoapp.addtask.ui.TasksUIState.Success
import com.xevidev.todoapp.addtask.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    //Here grabs the content of the stateFlow and for each one you gona converted in a Success
    val uiState: StateFlow<TasksUIState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        //first the viewModelScope its the scope where you use it
        //second: when the app goes to the background its the time when the flows ends
        //third: the initial state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    //We can use here livedata but it's not a good option for lists


    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(task: String) {

        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
            onDialogClose()
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        /*
        We need to use this to recompose the view. If we change the value directly, it
        doesn't recompose.
        Using let we prevent to show null data
         */

        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }

    }

    fun onItemRemove(taskModel: TaskModel) {
//        val task = _tasks.find { it.id == taskModel.id }
//        _tasks.remove(task)
    }

}