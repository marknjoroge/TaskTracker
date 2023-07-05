package com.example.tasktracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.room.Room
import com.example.TaskDatabase
import com.example.tasktracker.Constants.BUNDLE_TASK_ID
import com.example.tasktracker.databinding.ActivityTaskItemBinding
import com.google.android.material.snackbar.Snackbar

class task_item : AppCompatActivity() {
    private var status = arrayOf("10","20","30","40","50","60","70","80","90","100")
    private lateinit var textTaskName: TextView
    private lateinit var textDescription: TextView
    private lateinit var textStartDate: TextView
    private lateinit var textDueDate: TextView
    private lateinit var binding: ActivityTaskItemBinding

    private  val  taskDB : TaskDatabase by lazy {
        Room.databaseBuilder(this, TaskDatabase::class.java, Constants.TASK_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }
    private lateinit var taskCreator: TasksCreator
    private var task_id=0
    private var defaultTaskName=""
    private var defaultDescription:  ArrayList<String> = arrayListOf()
    private var defaultStartDate=""
    private var defaultDueDate=" "



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTaskItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            task_id= it.getInt(BUNDLE_TASK_ID)
        }

        binding.apply {
            defaultTaskName=taskDB.dao().getNote(task_id).taskName
            defaultDescription=taskDB.dao().getNote(task_id).description
            defaultStartDate=taskDB.dao().getNote(task_id).startDate
            defaultDueDate=taskDB.dao().getNote(task_id).dueDate
            status=taskDB.dao().getNote(task_id).status

            taskTitle.setText(defaultTaskName)
           // describe.setText(defaultDescription)
            describe.text = defaultDescription.joinToString(separator = "\n")

            date1start.setText(defaultStartDate)
            date1End.setText(defaultDueDate)

            DeleteTaskButton.setOnClickListener{
                taskCreator= TasksCreator(task_id,defaultTaskName,defaultDescription,defaultStartDate,defaultDueDate,status)
                taskDB.dao().deleteTask(taskCreator)
                finish()
            }
            SaveStateButton.setOnClickListener{
                val taskName=taskTitle.text.toString()
                val description=arrayListOf<String>(describe.text.toString())
               // val description=describe.text.toString()
                val startDate=date1start.text.toString()
                val dueDate=date1End.text.toString()
                val selectedStatus = state.selectedItem.toString()

                if(taskName.isNotEmpty() || description.isNotEmpty() || startDate.isNotEmpty() || dueDate.isNotEmpty()){

                    taskCreator= TasksCreator( task_id,taskName,description,startDate,dueDate,selectedStatus)
                    taskDB.dao().updateTask(taskCreator)
                    finish()
                }
                else{
                    Snackbar.make(it,"Task cannot be empty", Snackbar.LENGTH_LONG).show()
                }
            }

        }

        val state = findViewById<Spinner>(R.id.state)



        if (state != null) {
            val arrayAdapter =
                ArrayAdapter(this@task_item, android.R.layout.simple_spinner_item, status)
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