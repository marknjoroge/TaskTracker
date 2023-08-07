package com.example.tasktracker

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.room.Room
import com.example.TaskDatabase
import com.example.tasktracker.Constants.BUNDLE_TASK_ID
import com.example.tasktracker.databinding.ActivityTaskItemBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class task_item : AppCompatActivity() {
    private var status = arrayOf("10", "20", "30", "40", "50", "60", "70", "80", "90", "100")
    private lateinit var textTaskName: TextView
    private lateinit var textDescription: TextView
    private lateinit var textStartDate: TextView
    private lateinit var textDueDate: TextView
    private lateinit var context: Context
    private lateinit var binding: ActivityTaskItemBinding

    private val taskDB: TaskDatabase by lazy {
        Room.databaseBuilder(this, TaskDatabase::class.java, Constants.TASK_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }

    val cal=Calendar.getInstance()
    private var date="--/--/----"
    private var date1="--/--/----"
    private lateinit var dateBtn:Button
    private lateinit var dateText:TextView
    private lateinit var dateBtn1:Button
    private lateinit var dateText1:TextView

    private lateinit var taskCreator: TasksCreator
    private var task_id = 0
    private var defaultTaskName = ""
    private var defaultDescription: ArrayList<String> = arrayListOf()
    private var defaultStartDate = ""
    private var defaultDueDate = " "
    private var defaultStatus = ""
    private var defaultTime=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dateBtn=findViewById(R.id.startDate)
        dateText=findViewById(R.id.Date1)
        dateBtn1=findViewById(R.id.dueDate)
        dateText1=findViewById(R.id.dueDate1)

        intent.extras?.let {
            task_id = it.getInt(BUNDLE_TASK_ID)
        }
        context = this

        binding.apply {
            val note = taskDB.dao().getNote(task_id)
            if (note != null) {
                defaultTaskName = note.taskName
                defaultDescription = note.description
                defaultStartDate = note.startDate
                defaultDueDate = note.dueDate
                defaultStatus = note.status
                defaultTime=note.time

                editTaskName.setText(defaultTaskName)
                describeTask.setText(defaultDescription.joinToString(separator = "\n"))
                Date1.setText(defaultStartDate)
                dueDate1.setText(defaultDueDate)
                editTime.setText(defaultTime)
            } else {
                // Handle the case where the note is null or not found in the database
            }

            /*defaultTaskName = taskDB.dao().getNote(task_id).taskName
            defaultDescription = taskDB.dao().getNote(task_id).description
            defaultStartDate = taskDB.dao().getNote(task_id).startDate
            defaultDueDate = taskDB.dao().getNote(task_id).dueDate
            defaultStatus = taskDB.dao().getNote(task_id).status


            editTaskName.setText(defaultTaskName)
            // describe.setText(defaultDescription)
            describeTask.setText(defaultDescription.joinToString(separator = "\n"))

            Date1.setText(defaultStartDate)
            dueDate1.setText(defaultDueDate)*/

            DeleteTaskButton.setOnClickListener {
                taskCreator = TasksCreator(
                    task_id,
                    defaultTaskName,
                    defaultDescription,
                    defaultTime,
                    defaultStartDate,
                    defaultDueDate,
                    defaultStatus,

                )
                taskDB.dao().deleteTask(taskCreator)
                finish()
            }
            updateTask.setOnClickListener {
                val taskName = editTaskName.text.toString()
                val description = arrayListOf<String>(describeTask.text.toString())
                // val description=describe.text.toString()
                val startDate = Date1.text.toString()
                val dueDate = dueDate1.text.toString()
                val time=editTime.text.toString()
                val selectedStatus = state.selectedItem.toString()

                if (taskName.isNotEmpty() || description.isNotEmpty() || startDate.isNotEmpty() || dueDate.isNotEmpty()) {

                    taskCreator = TasksCreator(
                        task_id,
                        taskName,
                        description,
                        time,
                        startDate,
                        dueDate,

                        selectedStatus
                    )
                    taskDB.dao().updateTask(taskCreator)
                    finish()
                } else {
                    Snackbar.make(it, "Task cannot be empty", Snackbar.LENGTH_LONG).show()
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
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedStatus = status[position]
                    // Update the selected status in the task immediately
                    taskCreator = TasksCreator(
                        task_id,
                        defaultTaskName,
                        defaultDescription,
                        defaultTime,
                        defaultStartDate,
                        defaultDueDate,
                        selectedStatus
                    )
                    taskDB.dao().updateTask(taskCreator)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle the case where nothing is selected
                }
            }


        }

        setDate()
        setDate2()
    }
    private fun setDate() {
        val dateSetListener: DatePickerDialog.OnDateSetListener =
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDate()
                }
            }

        dateBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )

            // Set the minimum date to the current date
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }

        dateBtn1.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )

            // Set the minimum date to the current date
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }

    private fun setDate2() {
        val dateSetListener: DatePickerDialog.OnDateSetListener =
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDate2()
                }
            }

        dateBtn1.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )

            // Set the minimum date to the current date
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }


    private fun updateDate() {
        val myFormat="MM/dd/yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.US)
        date= sdf.format(cal.time)
        dateText.text=date


    }
    private fun updateDate2() {
        val myFormat="MM/dd/yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.US)
        date1=sdf.format(cal.time)
        dateText1.text=date1

    }
}
