package com.example.tasktracker

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.example.TaskDatabase
import com.example.tasktracker.Constants.BUNDLE_TASK_ID
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

    private var task_id=0
    private var defaultTaskName=""
    private var defaultDescription:  ArrayList<String> = arrayListOf()
    private var defaultStartDate=""
    private var defaultDueDate=" "

    private val CHANNEL_ID = "TaskReminderChannel"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTaskCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_task_creation)
        task_id = intent.getIntExtra(BUNDLE_TASK_ID, 0)
        val task = taskDB.dao().getNote(task_id)

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
                val time=editTime.text.toString()



               if(taskName.isNotEmpty() || description.isNotEmpty() || startDate.isNotEmpty() || dueDate.isNotEmpty()){
                   val status = ""
                   tasksCreator= TasksCreator( 0,taskName,description,time,startDate,dueDate,status)
                taskDB.dao().upsertTask(tasksCreator)
                   finish()
                }
                else{
                    Snackbar.make(binding.root,"Task cannot be empty", Snackbar.LENGTH_LONG).show()
                }


            }
                    }
        createNotificationChannel()
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
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channelName = "Task Reminder"
        val channelDescription = "Channel for task reminders"
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
            description = channelDescription
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    private fun showNotification(taskName: String, dueDate: String) {
        val notificationDelayMillis = 2 * 60 * 1000 // 2 minutes delay in milliseconds

        val notificationIntent = Intent(this, YourBroadcastReceiver::class.java).apply {
            action = "YOUR_ACTION_NAME"
            putExtra("TASK_NAME", taskName)
            putExtra("DUE_DATE", dueDate)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Task Reminder")
            .setContentText("Task '$taskName' is due on $dueDate.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setAutoCancel(true)


        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + notificationDelayMillis, pendingIntent)

        with(NotificationManagerCompat.from(this)) {
            notify(0, notificationBuilder.build())
        }
    }

}


