package com.regxl.news.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.regxl.news.network.Article

@Database(entities = [Article::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object {
        private var INSTANCE: ArticleDataBase? = null
        fun getInstance(context: Context): ArticleDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDataBase::class.java,
                    "articleDataBase"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}