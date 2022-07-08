package com.example.newproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studo.CalendarActivity

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)

        val name = findViewById<TextView>(R.id.name)
        val username = intent.getStringExtra("Username")
        val college = intent.getStringExtra("College")
        val id = intent.getIntExtra("StudentId",0)
        val email = intent.getStringExtra("Email")
        name.text = "Logged in as $username"

        val buttonClickMap = findViewById<Button>(R.id.mapid)
        buttonClickMap.setOnClickListener {
            val intent = Intent(this,MapsActivity::class.java)
            intent.putExtra("College",college)
            startActivity(intent)
        }

        val buttonClickCalendar = findViewById<Button>(R.id.calendarid)
        buttonClickCalendar.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            intent.putExtra("Username",username)
            intent.putExtra("College",college)
            intent.putExtra("StudentId",id)
            intent.putExtra("Email",email)
            startActivity(intent)
        }
    }
}