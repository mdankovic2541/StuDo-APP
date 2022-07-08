package com.example.newproject

import android.app.Application
import androidx.lifecycle.LiveData

class StudentRepositorijOR(application: Application?,studentDao: StudentDao
) : StudentRepositorij(application, studentDao)
{
    override val studentList: LiveData<List<Student>>
        get() {
            return this.getStudentList
        }
}