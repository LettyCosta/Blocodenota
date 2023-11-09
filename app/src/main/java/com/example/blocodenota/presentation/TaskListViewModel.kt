package com.example.blocodenota.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.blocodenota.BlocodenotaApplication
import com.example.blocodenota.data.Task
import com.example.blocodenota.data.TaskDao
class TaskListViewModel( taskDao: TaskDao,
    ): ViewModel() {

val TaskListLiveData: LiveData<List<Task>> = taskDao.getAll()



    companion object {
        fun create(application: Application): TaskListViewModel {
            val dataBaseInstance = (application as BlocodenotaApplication).getAppDataBase()
            val dao = dataBaseInstance.taskDao()
            return TaskListViewModel(dao)
        }
    }
}