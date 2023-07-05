package com.example.tasktracker
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.tasktracker.Constants.TASK_TABLE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity (tableName =TASK_TABLE)
@TypeConverters(ArrayListConverter::class)
data class TasksCreator(
    @PrimaryKey(autoGenerate = true)
    val task_id: Int,
    val taskName:String,
    //val description: String,
    val description: ArrayList<String>,
    val startDate: String,
    val dueDate: String,
    //var checked: Boolean
    val status: String
)
/*class DescriptionTypeConvertor {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String>{
       val listType =object :TypeToken<ArrayList<String>>(){}.type
      return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun descArray(list: ArrayList<String?>): String{
         return Gson().toJson(list)
    }
}*/


