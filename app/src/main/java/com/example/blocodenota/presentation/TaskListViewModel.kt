package com.example.blocodenota.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blocodenota.ActionType
import com.example.blocodenota.BlocodenotaApplication
import com.example.blocodenota.TaskAction
import com.example.blocodenota.data.Task
import com.example.blocodenota.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(private val taskDao: TaskDao): ViewModel() {

val TaskListLiveData: LiveData<List<Task>> = taskDao.getAll()

    fun Execute(taskAction: TaskAction){
        when (taskAction.actionType) {
            ActionType.DELETE.name -> DeleteById(taskAction.task!!.id)
            ActionType.CREATE.name ->  insertIntoDataBase(taskAction.task!!)
            ActionType.UPDATE.name ->  UpdateIntoDataBase(taskAction.task!!)
            ActionType.DELETE_ALL.name -> DeleteAll()

        }
    }

    private fun DeleteById(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.DeleteById(id)

        }
    }
    private fun insertIntoDataBase(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            taskDao.insert(task)

        }

    }

    private fun UpdateIntoDataBase(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.update(task)

        }
    }
    private fun DeleteAll(){
        viewModelScope.launch(Dispatchers.IO)  {
            taskDao.deleteAll()

        }
    }

    companion object {
        fun create(application: Application): TaskListViewModel {
            val dataBaseInstance = (application as BlocodenotaApplication).getAppDataBase()
            val dao = dataBaseInstance.taskDao()
            return TaskListViewModel(dao)
        }
    }
}