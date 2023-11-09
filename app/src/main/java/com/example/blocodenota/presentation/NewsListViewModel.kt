package com.example.blocodenota.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blocodenota.data.remote.NewsDTO
import com.example.blocodenota.data.remote.NewsService
import com.example.blocodenota.data.remote.RetrofitModule
import kotlinx.coroutines.launch

class NewsListViewModel(
    private val newsService: NewsService
) : ViewModel() {

    private val _newsListLiveData = MutableLiveData<List<NewsDTO>>()
    val newsListLiveData : LiveData<List<NewsDTO>> = _newsListLiveData

    init {
        getNewsList()
    }

    private fun getNewsList(){
        viewModelScope.launch {
            try {
                val topNews = newsService.fetchTopNews().data
                val allNews = newsService.fetchAllNews().data
                _newsListLiveData.value = topNews + allNews
            }catch (ex: Exception){
                ex.printStackTrace()
            }
        }
    }
    companion object {
        fun create(): NewsListViewModel {
            val newsService = RetrofitModule.CreateNewsService()
            return NewsListViewModel(newsService)
        }
    }
    }
