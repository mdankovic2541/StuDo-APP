package com.example.newproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
class Task(
    @field:ColumnInfo(name = "naslov") var naslov: String,
    @field:ColumnInfo(name = "studentId") var studentId: String,
    @field:ColumnInfo(name = "datum") var datum: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}