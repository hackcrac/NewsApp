package com.regxl.news.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class News (
    @Json(name = "status") val status:String,
    @Json(name = "totalResults") val totalResults:Int,
    @Json(name = "articles") val articles:List<Article>
        )


@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,
    @Json @ColumnInfo(name = "source") val source: Source?,
    @Json @ColumnInfo(name = "author") val author:String?,
    @Json @ColumnInfo(name = "title") val title:String?,
    @Json @ColumnInfo(name = "description") val description:String?,
    @Json @ColumnInfo(name = "url") val url:String?,
    @Json @ColumnInfo(name = "urlToImage") val urlToImage:String?,
    @Json @ColumnInfo(name = "publishedAt") val publishedAt:String?,
    @Json @ColumnInfo(name = "content") val content:String?
) : Serializable

data class Source(
    @Json(name = "id") val id:String?,
    @Json(name = "name") val name:String?
)