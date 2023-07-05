package com.example.tasktracker

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class Tasks() : Parcelable {
    val tasksJson = """
{
  "tasks": [
    {
      "taskName": "Writing Assignment",
      "description": "outline the content, summarise the content",
      "startDate": "2023-01-05",
      "dueDate": "2023-05-15",
      "status": "20% complete"
    },
    {
      "taskName": "Research for Writing Assignment",
       "description": "outline the content, summarise the content",
      "startDate": "2023-01-05",
      "dueDate": "2023-01-12",
      "status": "Not started"
    },
    {
      "taskName": "Outlining the Writing Assignment",
       "description": "outline the content, summarise the content",
      "startDate": "2023-01-12",
      "dueDate": "2023-01-20",
      "status": "In progress (10% complete)"
    },
    {
      "taskName": "Writing Introduction for Writing Assignment",
       "description": "outline the content, summarise the content",
      "startDate": "2023-01-20",
      "dueDate": "2023-01-25",
      "status": "In progress (5% complete)"
    },
    {
      "taskName": "Writing Body Paragraphs for Writing Assignment",
       "description": "outline the content, summarise the content",
      "startDate": "2023-01-25",
      "dueDate": "2023-02-05",
      "status": "Not started"
    },
     {
      "taskName": "Working out",
       "description": "outline the content, summarise the content",
      "startDate": "2023-01-25",
      "dueDate": "2023-02-05",
      "status": "Not started"
    },
     {
      "taskName": "Coding",
       "description": "outline the content, summarise the content",
      "startDate": "2023-01-25",
      "dueDate": "2023-02-05",
      "status": "Not started"
    }
  ]
}
""".trimIndent()

    constructor(parcel: Parcel) : this() {
    }

    fun getItem(key: String, position: Int): String {

        val tasks = JSONObject(tasksJson).getJSONArray("tasks")
        val taskObject = tasks.getJSONObject(position)
        return taskObject.getString(key)
    }
    
    fun getSize(): Int {
        val tasks = JSONObject(tasksJson).getJSONArray("tasks")
        return tasks.length()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tasks> {
        override fun createFromParcel(parcel: Parcel): Tasks {
            return Tasks(parcel)
        }

        override fun newArray(size: Int): Array<Tasks?> {
            return arrayOfNulls(size)
        }
    }
}