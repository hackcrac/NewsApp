package com.regxl.news

import android.app.Application
import com.regxl.news.dataBase.ArticleDataBase

class NewsApplication :Application (){
    val dataBase :ArticleDataBase by lazy {
        ArticleDataBase.getInstance(this)
    }
}