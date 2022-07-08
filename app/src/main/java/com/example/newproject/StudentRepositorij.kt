package com.example.newproject

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.barrycards.StudDatabase

abstract class StudentRepositorij(application: Application?,private var studentDao: StudentDao) {
    var getStudentList: LiveData<List<Student>>
        get() {
            return field
        }
    abstract val studentList: LiveData<List<Student>>



    fun insert(student: Student?) {
        InsertStudentAsyncTask(studentDao).execute(student)
    }

    fun update(student: Student?){
        UpdateStudentAsyncTask(studentDao).execute(student)
    }

    fun delete(student: Student?){
        DeleteStudentAsyncTask(studentDao).execute(student)
    }

    fun deleteAll() {
        DeleteAllStudentAyncTask(studentDao).execute()
    }

    private class InsertStudentAsyncTask(student: StudentDao) :
        AsyncTask<Student?, Void?, Void?>() {
        private val studentDao: StudentDao = student

        override fun doInBackground(vararg params: Student?): Void? {
            studentDao.insertStudent(params[0])
            return null
        }
    }

    private class UpdateStudentAsyncTask(studentDao: StudentDao) :
        AsyncTask<Student?, Void?, Void?>() {
        private val studentDao: StudentDao = studentDao

        override fun doInBackground(vararg params: Student?): Void? {
            studentDao.updateStudent(params[0])
            return null
        }
    }

    private class DeleteStudentAsyncTask(studentDao: StudentDao) :
        AsyncTask<Student?, Void?, Void?>() {
        private val studentDao: StudentDao = studentDao

        override fun doInBackground(vararg params: Student?): Void? {
            studentDao.deleteStudent(params[0])
            return null
        }
    }

    private class DeleteAllStudentAyncTask(studentDao: StudentDao) :
        AsyncTask<Student?, Void?, Void?>() {
        private val studentDao: StudentDao = studentDao

        override fun doInBackground(vararg params: Student?): Void? {
            studentDao.deleteAllStudents()
            return null
        }
    }


    init {
        val baza: StudDatabase = StudDatabase.getInstance(application)
        studentDao = baza.studentDao()!!
        getStudentList = studentDao.getStudents
    }
}