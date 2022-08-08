package com.regxl.news.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.regxl.news.repository.NewsRepository

class NewsViewModelFactory(private val newsRepository: NewsRepository):ViewModelProvider.Factory {
    override fun <T:ViewModel>create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}