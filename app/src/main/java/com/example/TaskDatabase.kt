package com.example

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

import com.example.tasktracker.TasksCreator

@Database(
    entities = [TasksCreator::class],
    version = 1

)
//@TypeConverters(DescriptionTypeConvertor::class)
abstract class TaskDatabase: RoomDatabase() {
  abstract fun dao(): TaskCreatorDao
}