package com.example.newproject

import android.app.Application
import androidx.lifecycle.LiveData

class TaskRepositorijOR(application: Application?,taskDao: TaskDao
) : TaskRepositorij(application, taskDao)
{
    override val taskList: LiveData<List<Task>>
        get() {
            return this.getTaskList
        }
}