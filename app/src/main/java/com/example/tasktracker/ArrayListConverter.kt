package com.example.tasktracker

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArrayListConverter {
    @TypeConverter
    fun fromStringArrayList(value: ArrayList<String>): String {

        return Gson().toJson(value)
    }


    @TypeConverter
    fun toStringArrayList(value: String): ArrayList<String> {
        return try {
            Gson().fromJson<ArrayList<String>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }
    @TypeConverter
    fun fromArray(value: Array<String>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toArray(value: String): Array<String> {
        return try {
            Gson().fromJson<Array<String>>(value) //using extension function
        } catch (e: Exception) {
            arrayOf()
        }
    }


}