package com.example.tasktracker

data class TaskModel(val id: Int, val taskName: String, val startDate: String, val dueDate: String, val status: String, var checked: Boolean)
