package com.example

import com.example.tasktracker.TasksCreator

sealed interface TaskEvent{
    object CreateTask: TaskEvent
    data class SetTaskName(val taskName: String): TaskEvent
    data class SetDescription(val description: String): TaskEvent
    data class SetStartDate(val startDate: String): TaskEvent
    data class SetDueDate(val dueDate: String): TaskEvent
    object ShowDialog: TaskEvent
    object HideDialog: TaskEvent
    data class DeleteTask(val tasksCreator: TasksCreator): TaskEvent 

}