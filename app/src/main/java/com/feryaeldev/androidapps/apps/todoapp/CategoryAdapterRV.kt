/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.feryaeldev.androidapps.apps.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.feryaeldev.androidapps.R

class CategoryAdapter(
    private val categories: List<TaskCategory>,
    private val onItemSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.render(categories[position])
        holder.itemView.setOnClickListener { onItemSelected(position) }
    }

}

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.itemTaskViewContainer)
    fun render(taskCategory: TaskCategory) {

        val color = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        when (taskCategory) {
            TaskCategory.Business -> {
                tvCategoryName.text = tvCategoryName.context.getString(R.string.business)
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_business_category
                    )
                )
            }

            TaskCategory.Personal -> {
                tvCategoryName.text = tvCategoryName.context.getString(R.string.personal)
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_personal_category
                    )
                )
            }

            TaskCategory.Other -> {
                tvCategoryName.text = tvCategoryName.context.getString(R.string.other)
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_other_category
                    )
                )
            }
        }
    }

}