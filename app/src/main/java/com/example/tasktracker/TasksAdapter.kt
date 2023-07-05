package com.example.tasktracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.tasktracker.databinding.ActivityTaskCardBinding
import com.example.tasktracker.databinding.ActivityTaskListBinding

class TasksAdapter :
        Adapter<TasksAdapter.ViewHolder>() {

    private lateinit var binding: ActivityTaskCardBinding
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    var tasks: Tasks = Tasks()

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder)
         */
        class ViewHolder(view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view) {
            lateinit var cardStartDate: TextView
            lateinit var cardEndDate: TextView
            lateinit var cardTaskName: TextView
            lateinit var status: TextView

            lateinit var deleteTaskBtn: TextView


            init {
                // Define click listener for the ViewHolder's View
                cardStartDate = view.findViewById(R.id.cardStartDate)
                cardEndDate=view.findViewById(R.id.cardDueDate)
                cardTaskName=view.findViewById(R.id.cardTaskName)
                status=view.findViewById(R.id.status)
                deleteTaskBtn=view.findViewById(R.id.deleteLink)

                itemView.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.activity_task_card, viewGroup, false)

            return ViewHolder(view,itemClickListener)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.cardTaskName.text = tasks.getItem("taskName", position)
            viewHolder.cardStartDate.text = tasks.getItem( "startDate", position)
            viewHolder.cardEndDate.text = tasks.getItem("dueDate", position)
            viewHolder.status.text = tasks.getItem("status", position)


        }

        // Return the size of your dataset (invoked by the layout manager)

        // TODO: change this later
        override fun getItemCount(): Int {
        return tasks.getSize()
    }


}
