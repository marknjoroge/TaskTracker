package com.example.tasktracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class YourBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Handle the received broadcast here
        if (intent?.action == "YOUR_ACTION_NAME") {
            val taskName = intent.getStringExtra("TASK_NAME")
            val dueDate = intent.getStringExtra("DUE_DATE")
            Toast.makeText(context, "Task '$taskName' is due on $dueDate.", Toast.LENGTH_LONG).show()
        }
    }
}
