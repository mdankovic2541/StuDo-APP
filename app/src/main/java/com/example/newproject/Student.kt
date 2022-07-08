package com.example.newproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student")
class Student(
    @ColumnInfo(name = "ime") var ime: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "faks") var faks: String,
    @ColumnInfo(name = "lozinka") var lozinka: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}