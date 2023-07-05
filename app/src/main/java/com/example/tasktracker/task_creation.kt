package com.example.tasktracker

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import com.example.TaskDatabase
import com.example.tasktracker.Constants.TASK_DATABASE
import com.example.tasktracker.databinding.ActivityTaskCreationBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class task_creation : AppCompatActivity() {

    lateinit var binding: ActivityTaskCreationBinding
    private  val  taskDB : TaskDatabase by lazy {
        Room.databaseBuilder(this, TaskDatabase::class.java,TASK_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }
    private lateinit var tasksCreator:TasksCreator

    val cal=Calendar.getInstance()
    private var date="--/--/----"
    private var date1="--/--/----"
    private lateinit var dateBtn:Button
    private lateinit var dateText:TextView
    private lateinit var dateBtn1:Button
    private lateinit var dateText1:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTaskCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_task_creation)

        dateBtn=findViewById(R.id.startDate)
        dateText=findViewById(R.id.Date1)
        dateBtn1=findViewById(R.id.dueDate)
        dateText1=findViewById(R.id.dueDate1)

        binding.apply {
            
            createTask.setOnClickListener {
                val taskName=editTaskName.text.toString()
                //val description=describeTask.text.toString().split("\n").toMutableList()
                val description =arrayListOf<String>(describeTask.text.toString())
                val startDate =Date1.text.toString()
                val dueDate =dueDate1.text.toString()

                

               if(taskName.isNotEmpty() || description.isNotEmpty() || startDate.isNotEmpty() || dueDate.isNotEmpty()){
                tasksCreator= TasksCreator( 0,taskName, description,startDate,dueDate,"0")
                taskDB.dao().upsertTask(tasksCreator)
                   finish()
                }
                else{
                    Snackbar.make(it,"Task cannot be empty", Snackbar.LENGTH_LONG).show()
                }


            }
        }
        setDate()
        setDate2()
    }
    private fun setDate(){
        val dateSetListener :
       DatePickerDialog.OnDateSetListener =object :DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?,
                                   year: Int,
                                   month: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateDate()
            }

        }
        dateBtn.setOnClickListener{
          DatePickerDialog(
              this,dateSetListener,
              cal.get(Calendar.YEAR),
              cal.get(Calendar.MONTH),
              cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }
        dateBtn1.setOnClickListener{
            DatePickerDialog(
                this,dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }
    }
    private fun setDate2(){
        val dateSetListener :
                DatePickerDialog.OnDateSetListener =object :DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?,
                                   year: Int,
                                   month: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateDate2()
            }

        }

        dateBtn1.setOnClickListener{
            DatePickerDialog(
                this,dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }
    }

    private fun updateDate() {
        val myFormat="MM/dd/yyyy"
        val sdf=SimpleDateFormat(myFormat, Locale.US)
        date= sdf.format(cal.time)
        dateText.text=date
        

            }
    private fun updateDate2() {
        val myFormat="MM/dd/yyyy"
        val sdf=SimpleDateFormat(myFormat, Locale.US)
        date1=sdf.format(cal.time)
        dateText1.text=date1

    }
}