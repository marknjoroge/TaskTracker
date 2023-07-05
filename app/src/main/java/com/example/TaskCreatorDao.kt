package com.example

import androidx.room.*
import com.example.tasktracker.Constants.TASK_TABLE
import com.example.tasktracker.TasksCreator

@Dao
interface TaskCreatorDao {
     @Upsert
     fun upsertTask(tasksCreator: TasksCreator)

    @Update
    fun updateTask(tasksCreator: TasksCreator)

    @Delete
     fun deleteTask(tasksCreator: TasksCreator)

   /* @Query("UPDATE $TASK_TABLE SET is_deleted = 1 WHERE task_id = :taskId")
    fun deleteTask(taskId: Int)*/

    /*@Query("UPDATE $TASK_TABLE SET is_deleted = 0 WHERE task_id = :taskId")
    fun restoreTask(taskId: Int)*/

    @Query("SELECT *FROM $TASK_TABLE ORDER BY task_id DESC")
    fun getAllTasks() :MutableList<TasksCreator>

    @Query("SELECT * FROM $TASK_TABLE WHERE task_id LIKE :id")
    fun getNote(id :Int) :TasksCreator

   /* @Query("SELECT * FROM $TASK_TABLE WHERE task_id LIKE :id")
    fun getTask(id: Int): TasksCreator*/

}