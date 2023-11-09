package com.example.blocodenota.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.blocodenota.BlocodenotaApplication
import com.example.blocodenota.data.local.Task
import com.example.blocodenota.data.local.TaskDao
import kotlinx.coroutines.launch

class TaskDetailViewModel(private val taskDao: TaskDao,
): ViewModel() {

    fun Execute(taskAction: TaskAction){
        when (taskAction.actionType) {
            ActionType.DELETE.name -> DeleteById(taskAction.task!!.id)
            ActionType.CREATE.name ->  insertIntoDataBase(taskAction.task!!)
            ActionType.UPDATE.name ->  UpdateIntoDataBase(taskAction.task!!)

        }
    }
    private fun DeleteById(id:Int){
        viewModelScope.launch {
            taskDao.DeleteById(id)
        }
    }
    private fun insertIntoDataBase(task: Task){
        viewModelScope.launch{
            taskDao.insert(task)
        }
    }
    private fun UpdateIntoDataBase(task: Task){
        viewModelScope.launch {
            taskDao.update(task)
        }
    }
    companion object {

        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val dataBaseInstance = (application as BlocodenotaApplication).getAppDataBase()
            val dao  = dataBaseInstance.taskDao()

           val factory = object : ViewModelProvider.Factory {
               override fun <T : ViewModel> create(modelClass: Class<T>): T {
                   return TaskDetailViewModel(dao) as T
               }
           }
            return factory
        }
    }
}