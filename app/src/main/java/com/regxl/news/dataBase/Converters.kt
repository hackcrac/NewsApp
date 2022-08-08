package com.regxl.news.dataBase

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.regxl.news.network.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source):String {
        return source.name!!
    }

    @TypeConverter
    fun toSource(name: String):Source{
        return Source(name,name)
    }

}