package com.example.blocodenota

import com.example.blocodenota.data.local.TaskDao
import com.example.blocodenota.presentation.TaskListViewModel
import org.mockito.kotlin.mock

class TaskListViewModelTest {

    private val TaskDao: TaskDao = mock()

    private val underTest: TaskListViewModel by lazy {
        TaskListViewModel(
            TaskDao
        )
    }

}