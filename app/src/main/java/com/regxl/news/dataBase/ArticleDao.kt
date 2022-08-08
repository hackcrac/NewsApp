package com.regxl.news.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.regxl.news.network.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    fun deleteArticle(article: Article)
}