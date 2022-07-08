package com.example.newproject

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @get:Query("Select * from Student")
    val getStudents: LiveData<List<Student>>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudent(student: Student?)

    @Query("SELECT id,email,faks,ime,lozinka from Student where email=(:email) and lozinka=(:lozinka)")
    fun prijava(email: String?, lozinka: String?): Student?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateStudent(student: Student?)

    @Delete
    fun deleteStudent(student: Student?)

    @Query("DELETE FROM Student")
    fun deleteAllStudents()


}