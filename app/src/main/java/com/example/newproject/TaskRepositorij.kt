package com.example.newproject

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.barrycards.StudDatabase

abstract class TaskRepositorij(application: Application?,private var taskDao: TaskDao) {
    var getTaskList: LiveData<List<Task>>
        get() {
            return field
        }
    abstract val taskList: LiveData<List<Task>>



    fun insert(task: Task?) {
        InsertTaskAsyncTask(taskDao).execute(task)
    }

    fun update(task: Task?){
        UpdateTaskAsyncTask(taskDao).execute(task)
    }

    fun delete(task: Task?){
        DeleteTaskAsyncTask(taskDao).execute(task)
    }

    fun deleteAll() {
        DeleteAllTasksAyncTask(taskDao).execute()
    }

    fun getTaskByDate(datum: String?, studentId: String?) {
        GetTaskAyncTask(taskDao).execute()
    }

    private class GetTaskAyncTask(taskDao: TaskDao) :
        AsyncTask<Task?, Void?, Void?>() {
        private val taskDao: TaskDao = taskDao

        override fun doInBackground(vararg p0: Task?): Void? {
            taskDao.getTaskByDate(p0[0].toString(),p0[1].toString())
            return null
        }
    }

    private class InsertTaskAsyncTask(taskDao: TaskDao) :
        AsyncTask<Task?, Void?, Void?>() {
        private val taskDao: TaskDao = taskDao

        override fun doInBackground(vararg params: Task?): Void? {
            taskDao.insertTask(params[0])
            return null
        }
    }

    private class UpdateTaskAsyncTask(taskDao: TaskDao) :
        AsyncTask<Task?, Void?, Void?>() {
        private val taskDao: TaskDao = taskDao

        override fun doInBackground(vararg params: Task?): Void? {
            taskDao.updateTask(params[0])
            return null
        }
    }

    private class DeleteTaskAsyncTask(taskDao: TaskDao) :
        AsyncTask<Task?, Void?, Void?>() {
        private val taskDao: TaskDao = taskDao

        override fun doInBackground(vararg params: Task?): Void? {
            taskDao.deleteTask(params[0])
            return null
        }
    }

    private class DeleteAllTasksAyncTask(taskDao: TaskDao) :
        AsyncTask<Task?, Void?, Void?>() {
        private val taskDao: TaskDao = taskDao

        override fun doInBackground(vararg params: Task?): Void? {
            taskDao.deleteAllTasks()
            return null
        }
    }


    init {
        val baza: StudDatabase = StudDatabase.getInstance(application)
        taskDao = baza.taskDao()!!
        getTaskList = taskDao.getTasks
    }
}