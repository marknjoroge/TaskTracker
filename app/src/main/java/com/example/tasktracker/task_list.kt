package com.example.tasktracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.TaskDatabase
import com.example.tasktracker.databinding.ActivityTaskListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class task_list : AppCompatActivity() {
    //private lateinit var recyclerView: RecyclerView
  //  private lateinit var adapter: TasksAdapter
   // private lateinit var data: List<String>
  //  private lateinit var tasks: Tasks

    private lateinit var binding: ActivityTaskListBinding

    private  val  taskDB : TaskDatabase by lazy {
        Room.databaseBuilder(this, TaskDatabase::class.java, Constants.TASK_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }
    private val task1Adapter by lazy { Task1Adapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_task_list)

        // Initialize the RecyclerView
       // recyclerView = findViewById(R.id.taskRecyclerView)
       // recyclerView.layoutManager = LinearLayoutManager(this)

        // Create the Tasks object with the data to be displayed
      //  tasks = Tasks()

        // Create and set the adapter
      //  adapter = TasksAdapter()
     //   recyclerView.adapter = adapter


        /*// Set item click listener
        adapter.setOnItemClickListener(object : TasksAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                //Handle item click event here
                navigateToDetailActivity(position)
            }
        })*/

        // Call a method to load your data into the adapter
       // loadTasksData()

    val fab: FloatingActionButton = findViewById(R.id.fabT)
        fab.setOnClickListener {
            val intent= Intent(this,task_creation::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        checkItem()
    }



    private fun checkItem(){
        binding.apply {
            if (taskDB.dao().getAllTasks().isNotEmpty()){
              taskRecyclerView.visibility= View.VISIBLE
              task1Adapter.differ.submitList(taskDB.dao().getAllTasks())
                setupRecyclerview()
            }
            else{
                taskRecyclerView.visibility= View.GONE

            }
        }
    }
    private fun setupRecyclerview(){
        binding.taskRecyclerView.apply {
            layoutManager=LinearLayoutManager(this@task_list)
            adapter=task1Adapter
        }
    }
}




/*
private fun loadTasksData() {
    // Implement your logic to retrieve the data for the RecyclerView
    // Assign the tasks data to the adapter's tasks variable
    adapter.tasks = getTasks()
    // Notify the adapter that the data has changed
    adapter.notifyDataSetChanged()
}


private fun getTasks(): Tasks {
    // Implement your logic to retrieve the tasks data
    // Return the Tasks object

    return Tasks()
}
private fun navigateToDetailActivity(taskPosition: Int) {
    // Implement your code to navigate to the detail activity
    // Example:
    val intent = Intent(this, task_item::class.java)
    intent.putExtra("task", taskPosition)
    startActivity(intent)
}*/
