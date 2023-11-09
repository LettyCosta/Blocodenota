package com.example.blocodenota

import com.example.blocodenota.data.Task
import com.example.blocodenota.data.TaskDao
import com.example.blocodenota.presentation.TaskListViewModel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class TaskListViewModelTest {

    private val TaskDao: TaskDao = mock()

    private val underTest: TaskListViewModel by lazy {
        TaskListViewModel(
            TaskDao
        )
    }

}