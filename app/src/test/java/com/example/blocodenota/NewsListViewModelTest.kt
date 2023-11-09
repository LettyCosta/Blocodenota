package com.example.blocodenota

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.blocodenota.data.remote.NewsDTO
import com.example.blocodenota.data.remote.NewsResponse
import com.example.blocodenota.data.remote.NewsService
import com.example.blocodenota.presentation.NewsListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class NewsListViewModelTest {
@get:Rule
val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val service : NewsService = mock()
    private lateinit var underTest : NewsListViewModel

    @Test
    fun`GIVEN request succeed news WHEN fetch THEN return list`(){
        runBlocking {
            //given
            val ExpectedTop = listOf<NewsDTO>(
                NewsDTO(
                    id = "id1",
                    title = "title1",
                    content = "content1",
                    imageURL = "imageURL1"

                )

            )

            val ExpectedAll = listOf<NewsDTO>(
                NewsDTO(
                    id = "id2",
                    title = "title2",
                    content = "content2",
                    imageURL = "imageURL2"

                )

            )
            val Topresponse = NewsResponse(data = ExpectedTop,)
            val Allresponse = NewsResponse(data = ExpectedAll,)
            whenever(service.fetchTopNews()).thenReturn(Topresponse)
            whenever(service.fetchAllNews()).thenReturn(Allresponse)
            //when
            underTest = NewsListViewModel(service)
            val result = underTest.newsListLiveData.getOrAwaitValue()

            //then
            assert(result == ExpectedTop + ExpectedAll)
        }

    }

}