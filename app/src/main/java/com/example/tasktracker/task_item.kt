package com.example.tasktracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class task_item : AppCompatActivity() {
    val stat = arrayOf(10,20,30,40,50,60,70,80,90,100)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_item)

        val state = findViewById<Spinner>(R.id.state)

        if (state != null) {
            val arrayAdapter =
                ArrayAdapter(this@task_item, android.R.layout.simple_spinner_item, stat)
            state.adapter = arrayAdapter
            state.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    p1: View,
                    p2: Int,
                    p3: Long
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }
}