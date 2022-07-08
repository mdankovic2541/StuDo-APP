package com.example.newproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.barrycards.StudDatabase

class StudentViewModel (application: Application?) : ViewModel() {

    private var studentRepositorij: StudentRepositorij
    private var sviStudenti: LiveData<List<Student>>
    private var studentDao: StudentDao


    fun insert(student: Student?) {
        studentRepositorij.insert(student)
    }

    fun update(student: Student?){
        studentRepositorij.update(student)
    }

    fun delete(student: Student?){
        studentRepositorij.delete(student)
    }

    fun deleteAll(){
        studentRepositorij.deleteAll()
    }


    fun getSviStudenti(): LiveData<List<Student>> {
        return sviStudenti
    }

    init {
        val baza: StudDatabase = StudDatabase.getInstance(application)
        studentDao = baza.studentDao()!!
        studentRepositorij = StudentRepositorijOR(application, studentDao)
        sviStudenti = studentRepositorij.studentList
    }
}