package com.example.tasktracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.TaskDatabase
import com.example.tasktracker.Constants.BUNDLE_TASK_ID
import com.example.tasktracker.databinding.ActivityTaskCardBinding

class Task1Adapter :RecyclerView.Adapter<Task1Adapter.ViewHolder>() {

    private lateinit var binding: ActivityTaskCardBinding
    private lateinit var context: Context



    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): Task1Adapter.ViewHolder {
     binding= ActivityTaskCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
     context=parent.context
     return ViewHolder()

    }
    override fun onBindViewHolder(holder: Task1Adapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }
    override fun getItemCount() =differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){

        /*init {
            binding.mMenus.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (view.id == R.id.mMenus) {
                showPopupMenu(view)
            }
        }

        private fun showPopupMenu(view: View) {
            val popupMenu = PopupMenu(context, view)
            popupMenu.inflate(R.menu.show_menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.edit -> {
                        // Handle edit task menu item click
                        true
                    }
                    R.id.delete -> {
                        // Handle delete task menu item click
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
*/

        @SuppressLint("setText")
        fun setData(item : TasksCreator){

        binding.apply {
            //taskId.text =item.id.toString()
            cardTaskName.text=item.taskName
            cardStartDate.text=item.startDate
            cardDueDate.text=item.dueDate
            status1.text= item.status.toString()
            cardStartTime.text=item.time



            root.setOnClickListener{
                 val intent=Intent(context,task_item::class.java)
                intent.putExtra(BUNDLE_TASK_ID, item.task_id)
                context.startActivity(intent)
            }

            //mMenus.setOnClickListener { popUpMenus(it) }

        }
      }

        /*private fun popUpMenus(v: View) {
            val popMenus = PopupMenu(c,v )
        }*/
    }

    private val differCallback = object : DiffUtil.ItemCallback<TasksCreator>(){
        override fun areItemsTheSame(oldItem: TasksCreator, newItem: TasksCreator): Boolean {
            return oldItem.task_id==newItem.task_id
        }

        override fun areContentsTheSame(oldItem: TasksCreator, newItem: TasksCreator): Boolean {
           return oldItem == newItem
        }

    }
     val differ= AsyncListDiffer(this,differCallback)
}