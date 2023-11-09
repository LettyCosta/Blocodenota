package com.example.blocodenota

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.blocodenota.data.local.Task

class TaskListAdapter(

    private val openTaskDetailView: (task: Task) -> Unit //ACAO DE CLICK
    ): ListAdapter<Task, TasklistViewHolder>(TaskListAdapter) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasklistViewHolder {

        val view: View = LayoutInflater.
        from(parent.context).inflate(
            R.layout.item_task,parent,false)

        return TasklistViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasklistViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, openTaskDetailView)
    }


    companion object: DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.description == newItem.description
        }

    }
}

class TasklistViewHolder(
    private val view: View): RecyclerView.ViewHolder(view) {


    val Tvtasktitle= view.findViewById<TextView>(R.id.tv_task_title)

    val Tvtaskdescription= view.findViewById<TextView>(R.id.tv_task_description)

    fun bind (
        task : Task,
        openTaskDetailView: (task: Task) -> Unit
    ){
        Tvtasktitle.text = task.title
        Tvtaskdescription.text = "${task.id}- ${task.description}"

        view.setOnClickListener {
            openTaskDetailView.invoke(task)
        }

    }

}