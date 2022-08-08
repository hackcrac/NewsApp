package com.regxl.news.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.regxl.news.adapters.NewsApiStatus
import com.regxl.news.network.Article
import com.regxl.news.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(newsRepository: NewsRepository):ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val repository = newsRepository
    private val _statusGeneral = MutableLiveData<NewsApiStatus>()
    private val _statusBusiness = MutableLiveData<NewsApiStatus>()
    private val _statusEntertainment = MutableLiveData<NewsApiStatus>()
    private val _statusHealth = MutableLiveData<NewsApiStatus>()
    private val _statusScience = MutableLiveData<NewsApiStatus>()
    private val _statusSports = MutableLiveData<NewsApiStatus>()
    private val _statusTechnology = MutableLiveData<NewsApiStatus>()


    private val _generalArticles = MutableLiveData<List<Article>>()
    private val _businessArticles = MutableLiveData<List<Article>>()
    private val _entertainmentArticles = MutableLiveData<List<Article>>()
    private val _healthArticles = MutableLiveData<List<Article>>()
    private val _scienceArticles = MutableLiveData<List<Article>>()
    private val _sportsArticles = MutableLiveData<List<Article>>()
    private val _technologyArticles = MutableLiveData<List<Article>>()

    // The external immutable LiveData for the request status
    val statusGeneral: LiveData<NewsApiStatus> = _statusGeneral
    val statusBusiness: LiveData<NewsApiStatus> = _statusBusiness
    val statusEntertainment: LiveData<NewsApiStatus> = _statusEntertainment
    val statusHealth: LiveData<NewsApiStatus> = _statusHealth
    val statusScience: LiveData<NewsApiStatus> = _statusScience
    val statusSports: LiveData<NewsApiStatus> = _statusSports
    val statusTechnology: LiveData<NewsApiStatus> = _statusTechnology

    val generalArticles:LiveData<List<Article>> = _generalArticles
    val businessArticles:LiveData<List<Article>> = _businessArticles
    val entertainmentArticles:LiveData<List<Article>> = _entertainmentArticles
    val healthArticles:LiveData<List<Article>> = _healthArticles
    val scienceArticles:LiveData<List<Article>> = _scienceArticles
    val sportsArticles:LiveData<List<Article>> = _sportsArticles
    val technologyArticles:LiveData<List<Article>> = _technologyArticles

    val savedNews = repository.getSavedNews()

    init {
        getGeneralResult()
        getBusinessResult()
        getEntertainmentResult()
        getHealthResult()
        getScienceResult()
        getSportsResult()
        getTechnologyResult()
    }

    private fun getGeneralResult(){
        viewModelScope.launch {
            _statusGeneral.value = NewsApiStatus.LOADING
            try {
               _generalArticles.value =  repository.refreshGeneralNews()
                _statusGeneral.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusGeneral.value = NewsApiStatus.ERROR
                _generalArticles.value = listOf()
            }
        }
    }
    private fun getBusinessResult(){
        viewModelScope.launch {
            _statusBusiness.value = NewsApiStatus.LOADING
            try {
                _businessArticles.value =  repository.refreshBusinessNews()
                _statusBusiness.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusBusiness.value = NewsApiStatus.ERROR
                _businessArticles.value = listOf()
            }
        }
    }
    private fun getEntertainmentResult(){
        viewModelScope.launch {
            _statusEntertainment.value = NewsApiStatus.LOADING
            try {
                _entertainmentArticles.value =  repository.refreshEntertainmentNews()
                _statusEntertainment.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusEntertainment.value = NewsApiStatus.ERROR
                _entertainmentArticles.value = listOf()
            }
        }
    }

    private fun getHealthResult(){
        viewModelScope.launch {
            _statusHealth.value = NewsApiStatus.LOADING
            try {
                _healthArticles.value =  repository.refreshHealthNews()
                _statusHealth.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusHealth.value = NewsApiStatus.ERROR
                _healthArticles.value = listOf()
            }
        }
    }

    private fun getScienceResult(){
        viewModelScope.launch {
            _statusScience.value = NewsApiStatus.LOADING
            try {
                _scienceArticles.value =  repository.refreshScienceNews()
                _statusScience.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusScience.value = NewsApiStatus.ERROR
                _scienceArticles.value = listOf()
            }
        }
    }

    private fun getSportsResult(){
        viewModelScope.launch {
            _statusSports.value = NewsApiStatus.LOADING
            try {
                _sportsArticles.value =  repository.refreshSportsNews()
                _statusSports.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusSports.value = NewsApiStatus.ERROR
                _sportsArticles.value = listOf()
            }
        }
    }

    private fun getTechnologyResult(){
        viewModelScope.launch {
            _statusTechnology.value = NewsApiStatus.LOADING
            try {
                _technologyArticles.value =  repository.refreshTechnologyNews()
                _statusTechnology.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusTechnology.value = NewsApiStatus.ERROR
                _technologyArticles.value = listOf()
            }
        }
    }

    fun saveArticle(article: Article){
        viewModelScope.launch {
            repository.insertArticle(article)
        }
    }
    fun deleteArticle(article: Article){
        viewModelScope.launch {
            repository.deleteSavedNews(article)
        }
    }

}