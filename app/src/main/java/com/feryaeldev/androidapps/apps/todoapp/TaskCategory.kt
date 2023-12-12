package com.feryaeldev.androidapps.apps.todoapp

sealed class TaskCategory(var isSelected: Boolean = false) {
    data object Personal : TaskCategory()
    data object Business : TaskCategory()
    data object Other : TaskCategory()
}
