package com.feryaeldev.androidapps.apps.todoapp

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feryaeldev.androidapps.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoApp : AppCompatActivity() {

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoryAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapterRV

    private lateinit var fabAddTask: FloatingActionButton

    private val categories = listOf(
        TaskCategory.Personal,
        TaskCategory.Business,
        TaskCategory.Other
    )

    private val tasks = mutableListOf(
        Task("Prueba business", TaskCategory.Business),
        Task("Prueba personal", TaskCategory.Personal),
        Task("Prueba other", TaskCategory.Other)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_app)
        initComponents()
        initUI()
        initListeners()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter = CategoryAdapter(categories) { position ->
            updateCategories(position)
        }
        rvCategories.adapter = categoriesAdapter
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        tasksAdapter = TasksAdapterRV(tasks) { position ->
            onTaskSelected(position)
        }
        rvTasks.adapter = tasksAdapter
        rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_task)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        val etTaskName: EditText = dialog.findViewById(R.id.etTaskName)
        val rgTaskCategory: RadioGroup = dialog.findViewById(R.id.rgTaskCategory)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)

        etTaskName.hint = getString(R.string.your_new_task)

        btnAddTask.setOnClickListener {
            val currentTask = etTaskName.text.toString()
            if (currentTask.isNotEmpty()) {
                val selectedId = rgTaskCategory.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgTaskCategory.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_category_personal) -> TaskCategory.Personal
                    getString(R.string.todo_dialog_category_business) -> TaskCategory.Business
                    else -> TaskCategory.Other
                }

                tasks.add(Task(etTaskName.text.toString(), currentCategory))
                updateTasks(true)
                dialog.dismiss()
            } else {
                Toast.makeText(this, getString(R.string.todo_dialog_empty_task), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        dialog.show()
    }

    private fun onTaskSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks(true)
    }

    private fun updateCategories(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks(false)
    }

    private fun updateTasks(isComingFromChangingATaskOrCategory: Boolean) {
        // isComingFromChangingATaskOrCategory is true when we are coming from selecting a task and false for a category
        if (!isComingFromChangingATaskOrCategory) {
            val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
            val newTasks = tasks.filter { selectedCategories.contains(it.category) }
            tasksAdapter.tasks = newTasks
        }
        tasksAdapter.notifyDataSetChanged()
    }
}