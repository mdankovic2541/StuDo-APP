package com.example.newproject

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.barrycards.StudDatabase

class MainActivity : AppCompatActivity() {
    var email: EditText? = null
    var password: EditText? = null
    var login: Button? = null
    var register: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.editTextTextEmailAddress)
        password = findViewById(R.id.editTextTextPassword)

        login = findViewById(R.id.loginbtn)
        login!!.setOnClickListener(View.OnClickListener {
            val emailText = email!!.text.toString()
            val passwordText = password!!.text.toString()

            if(emailText.isEmpty() || passwordText.isEmpty()){
                Toast.makeText(applicationContext, "Unesite podatke", Toast.LENGTH_SHORT).show()
            }else{
                val db = StudDatabase.getInstance(applicationContext as Application?)
                val studentDao: StudentDao? = db.studentDao()
                Thread{
                    val student: Student? = studentDao?.prijava(emailText, passwordText)
                    if(student == null){
                        runOnUiThread{
                            Toast.makeText(applicationContext, "Uneseni podaci su krivi", Toast.LENGTH_SHORT).show()
                        }
                    }else {
                        val intent = Intent(this, MainPageActivity::class.java)
                        intent.putExtra("Username",student.ime)
                        intent.putExtra("College",student.faks)
                        intent.putExtra("StudentId",student.id)
                        intent.putExtra("Email",student.email)

                        startActivity(intent)
                    }
                }.start()

            }

        })
        register = findViewById(R.id.registerbtn)
        register!!.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}