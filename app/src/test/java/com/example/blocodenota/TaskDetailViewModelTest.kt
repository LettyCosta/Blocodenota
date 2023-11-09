package com.example.blocodenota

import com.example.blocodenota.data.Task
import com.example.blocodenota.data.TaskDao
import com.example.blocodenota.presentation.ActionType
import com.example.blocodenota.presentation.TaskAction
import com.example.blocodenota.presentation.TaskDetailViewModel
import com.example.blocodenota.presentation.TaskListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class TaskDetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val TaskDao: TaskDao = mock()

    private val underTest: TaskDetailViewModel by lazy {
        TaskDetailViewModel(
            TaskDao,

        )
    }
    @Test
    fun update_task() = runTest {

        //given

        val task = Task(
            id = 1,
            title = "title",
            description = "description"

        )
        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.UPDATE.name
        )

        //when

        underTest.Execute(taskAction)

        //then
        verify(TaskDao).update(task)

    }

    @Test
    fun delete_task() = runTest {

        //given
        val task = Task(
            id = 1,
            title = "Title",
            description = "description",
        )
        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.DELETE.name
        )

        //when

        underTest.Execute(taskAction)

        //then
        verify(TaskDao).DeleteById(task.id)


    }

    @Test
    fun create_task() = runTest{

        //given
        val task = Task(
            id = 1,
            title = "Title",
            description = "description",
        )
        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.CREATE.name
        )

        //when

        underTest.Execute(taskAction)

        //then
        verify(TaskDao).insert(task)


    }
}
