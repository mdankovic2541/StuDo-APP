package com.example.studo

import TaskFactory
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barrycards.StudDatabase
import com.example.newproject.*

class CalendarActivity : AppCompatActivity() {
    var currentYear = 0;
    var currentMonth = 0;
    var currentDay = 0;
    var daysIndex = 0;

    var naslov: EditText? = null
    var userId: EditText? = null
    var datum: EditText? = null
    var saveEvent: Button? = null
    var datumM = ""

    private var taskViewModel: TaskViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_act)
        val selectedDay = findViewById<TextView>(R.id.selectedDay)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)

        val recyclerViewTask = findViewById<RecyclerView>(R.id.recyclerViewTask)
        recyclerViewTask.layoutManager = LinearLayoutManager(this)
        recyclerViewTask.setHasFixedSize(true)

        val adapterTask = TaskAdapter()
        recyclerViewTask.adapter = adapterTask

        taskViewModel = ViewModelProvider(this, TaskFactory(application))[TaskViewModel::class.java]
        taskViewModel!!.getSviTasks().observe(this
        ) { drugiKolegiji -> adapterTask.setDrugiKolegijiList(drugiKolegiji as List<Task>) }


        naslov = findViewById(R.id.eventText)
        datum = findViewById(R.id.dateText3)
        userId = findViewById(R.id.idText2)
        calendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener { calendar, year, month, day ->
            selectedDay.text = "Selected day: ${day}.${month + 1}.${year}."
            currentYear = year
            currentMonth = month
            currentDay = day
            datumM = "${day}.${month + 1}.${year}."

        })
        saveEvent = findViewById(R.id.saveEventBtn)
        saveEvent!!.setOnClickListener(View.OnClickListener {
            val naslovText = naslov!!.text.toString()
            val datumText = datumM!!
            val userIdText = intent.getIntExtra("StudentId",0)

            if(naslovText.isEmpty()){
                Toast.makeText(applicationContext, "Unesite novi event", Toast.LENGTH_SHORT).show()
            }else{
                val db = StudDatabase.getInstance(applicationContext as Application?)
                val taskDao: TaskDao? = db.taskDao()
                Thread{

                   taskDao?.insertTask(Task(naslovText,userIdText.toString(),datumText))
                    runOnUiThread{
                        Toast.makeText(applicationContext, "Event unesen", Toast.LENGTH_SHORT).show()
                    }

                }.start()
            }


        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                taskViewModel?.delete(adapterTask.getDrugiKolegijiAt(viewHolder.adapterPosition))
                Toast.makeText(this@CalendarActivity, "Event obrisan", Toast.LENGTH_SHORT)
                    .show()
            }
        }).attachToRecyclerView(recyclerViewTask)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }




}

