package com.example.newproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.barrycards.StudDatabase

class TaskViewModel (application: Application?) : ViewModel() {

    private var taskRepositorij: TaskRepositorij
    private var sviTasks: LiveData<List<Task>>
    private var taskDao: TaskDao


    fun insert(task: Task?) {
        taskRepositorij.insert(task)
    }

    fun update(task: Task?){
        taskRepositorij.update(task)
    }

    fun delete(task: Task?){
        taskRepositorij.delete(task)
    }

    fun deleteAll(){
        taskRepositorij.deleteAll()
    }


    fun getSviTasks(): LiveData<List<Task>> {
        return sviTasks
    }


    fun getTaskByDate(datum: String?, studentId:String?){
        return taskRepositorij.getTaskByDate(datum,studentId)
    }

    init {
        val baza: StudDatabase = StudDatabase.getInstance(application)
        taskDao = baza.taskDao()!!
        taskRepositorij = TaskRepositorijOR(application, taskDao)
        sviTasks = taskRepositorij.taskList
    }
}