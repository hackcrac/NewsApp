package com.regxl.news.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.regxl.news.dataBase.ArticleDataBase
import com.regxl.news.network.Article
import com.regxl.news.network.NewsApi
import com.regxl.news.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val dataBase: ArticleDataBase) {

     suspend fun refreshGeneralNews():List<Article>{
        return NewsApi.retrofitService.getNews("general","en",Constants.API_KEY).articles
    }
    suspend fun refreshBusinessNews():List<Article>{
        return NewsApi.retrofitService.getNews("business","en",Constants.API_KEY).articles
    }
    suspend fun refreshEntertainmentNews():List<Article>{
        return NewsApi.retrofitService.getNews("entertainment","en",Constants.API_KEY).articles
    }
    suspend fun refreshHealthNews():List<Article>{
        return NewsApi.retrofitService.getNews("health","en",Constants.API_KEY).articles
    }
    suspend fun refreshScienceNews():List<Article>{
        return NewsApi.retrofitService.getNews("science","en",Constants.API_KEY).articles
    }
    suspend fun refreshSportsNews():List<Article>{
        return NewsApi.retrofitService.getNews("sports","en",Constants.API_KEY).articles
    }
    suspend fun refreshTechnologyNews():List<Article>{
        return NewsApi.retrofitService.getNews("technology","en",Constants.API_KEY).articles
    }

    suspend fun insertArticle(article: Article){
        withContext(Dispatchers.IO){
            dataBase.getArticleDao().insert(article)
        }
    }

    fun getSavedNews():LiveData<List<Article>>{
        return dataBase.getArticleDao().getAllArticles().asLiveData()
    }

    suspend fun deleteSavedNews(article: Article){
        withContext(Dispatchers.IO){
            dataBase.getArticleDao().deleteArticle(article)
        }
    }
}