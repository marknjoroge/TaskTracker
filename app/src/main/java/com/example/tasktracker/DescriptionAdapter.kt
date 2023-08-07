package com.example.tasktracker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktracker.DescriptionAdapter.ViewHolder
import com.example.tasktracker.databinding.ActivityDescriptionCardBinding
import com.example.tasktracker.databinding.ActivityTaskCardBinding

class DescriptionAdapter: RecyclerView.Adapter<ViewHolder>() {
    private lateinit var binding: ActivityDescriptionCardBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): ViewHolder {
      binding=ActivityDescriptionCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context=parent.context
        return ViewHolder()
    }
    override fun onBindViewHolder(holder: DescriptionAdapter.ViewHolder, position: Int) {
        holder.setDescriptionData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() =differ.currentList.size
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setDescriptionData(item : TasksCreator){
            binding.apply {
                descriptionCheckbox.text= item.description.toString()




            }
        }

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
//checkBox.isChecked=item.checked