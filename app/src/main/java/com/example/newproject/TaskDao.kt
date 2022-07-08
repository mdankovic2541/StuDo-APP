package com.example.newproject

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao {

    @get:Query("Select * from Task")
    val getTasks: LiveData<List<Task>>

    @Query("SELECT * from Task where datum=(:datum) AND studentId=(:studentId)")
    fun getTaskByDate(datum:String?, studentId:String?): Task

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTask(task: Task?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(task: Task?)

    @Delete
    fun deleteTask(task: Task?)

    @Query("DELETE FROM Task")
    fun deleteAllTasks()
}