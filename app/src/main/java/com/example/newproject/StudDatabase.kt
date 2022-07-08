package com.example.barrycards

import android.app.Application
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.newproject.Student
import com.example.newproject.StudentDao
import com.example.newproject.Task
import com.example.newproject.TaskDao


@Database(
    entities = [Student::class, Task::class],
    exportSchema = false,
    version = 3
)

abstract class StudDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao?
    abstract fun taskDao(): TaskDao?


    private class PopuniDbAsycTask(db: StudDatabase) :
        AsyncTask<Void?, Void?, Void?>() {
        private val studentDao: StudentDao = db.studentDao()!!
        private val taskDao: TaskDao = db.taskDao()!!


        override fun doInBackground(vararg params: Void?): Void? {

            studentDao.insertStudent(
                Student(

                    "Marko",
                    "marko@gmail.com",
                    "FERIT",
                    "m"
                )
            )

            studentDao.insertStudent(
                Student(

                    "Ivan",
                    "ivan@gmail.com",
                    "GRAFOS",
                    "i"
                )
            )

            taskDao.insertTask(
                Task(
                    "Novi dogadaj",
                    "1",
                    "16.6.2022."
                )
            )

            return null
        }

    }

    companion object {
        private const val DB_NAME = "StuDo"
        @Volatile
        private var instance: StudDatabase? = null
        @Synchronized
        fun getInstance(context: Application?): StudDatabase {
            if (instance == null) {
                if (context != null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudDatabase::class.java, DB_NAME
                    ).fallbackToDestructiveMigration().addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopuniDbAsycTask(instance!!).execute()
            }
        }
    }
}
