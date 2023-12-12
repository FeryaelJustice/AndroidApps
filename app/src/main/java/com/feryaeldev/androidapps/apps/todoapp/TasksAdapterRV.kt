package com.feryaeldev.androidapps.apps.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.feryaeldev.androidapps.R

class TasksAdapterRV(var tasks: List<Task>, private val onTaskSelected: (Int) -> Unit) :
    RecyclerView.Adapter<TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder =
        TasksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)
        )

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
        holder.itemView.setOnClickListener { onTaskSelected(position) }
    }
}

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)
    private val tvTask: TextView = view.findViewById(R.id.tvTask)

    fun render(task: Task) {
        cbTask.isChecked = task.isSelected
        tvTask.text = task.name
        if (task.isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

        val color = when (task.category) {
            TaskCategory.Personal -> R.color.todo_personal_category
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Other -> R.color.todo_other_category
        }

        cbTask.buttonTintList =
            ColorStateList.valueOf(ContextCompat.getColor(cbTask.context, color))
    }
}