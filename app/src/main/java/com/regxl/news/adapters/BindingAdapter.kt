package com.regxl.news.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.regxl.news.R
import com.regxl.news.network.Article
import java.text.SimpleDateFormat
import java.util.*

enum class NewsApiStatus{
    LOADING,DONE,ERROR
}

@BindingAdapter("image")
fun image(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun recyclerView(recyclerView: RecyclerView, dataList:List<Article>?){
    val adapter = recyclerView.adapter as ArticleListAdapter
    adapter.submitList(dataList)
}

@BindingAdapter("status")
fun dataStatus(statusImageView: ImageView, newsApiStatus: NewsApiStatus){
    when(newsApiStatus){
        NewsApiStatus.LOADING ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        NewsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        NewsApiStatus.ERROR->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("setDate")
fun setDate(textView: TextView,publishedAt:String){
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val output = SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss z",Locale.ENGLISH)
    var date: Date? = null
    try{
        date = input.parse(publishedAt)
    }
    catch (e:Exception){
        e.printStackTrace()
    }
     val formatted = date?.let { output.format(it) }
    textView.text = formatted
}