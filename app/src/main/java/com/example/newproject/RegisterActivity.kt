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
import kotlinx.coroutines.Dispatchers

class RegisterActivity : AppCompatActivity() {
    var name: EditText? = null
    var email: EditText? = null
    var password: EditText? = null
    var college: EditText? = null
    var register: Button? = null
    var login: Button?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_act)

        register = findViewById<Button>(R.id.registerbtn)
        login = findViewById<Button>(R.id.loginbtn)
        name = findViewById<EditText>(R.id.editTextTextName)
        email = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        password = findViewById<EditText>(R.id.editTextTextPasswordR)
        college = findViewById<EditText>(R.id.editTextTextCollege)


        register!!.setOnClickListener(View.OnClickListener {
            val nameText = name!!.text.toString()
            val emailText = email!!.text.toString()
            val passwordText = password!!.text.toString()
            val collegeText = college!!.text.toString()


            if(emailText.isEmpty() || passwordText.isEmpty() || nameText.isEmpty() || collegeText.isEmpty()){
                Toast.makeText(applicationContext, "Unesite podatke", Toast.LENGTH_SHORT).show()
            }else{
                val db = StudDatabase.getInstance(applicationContext as Application?)
                val studentDao: StudentDao? = db.studentDao()

                Thread{

                   studentDao?.insertStudent(Student(nameText,emailText,collegeText,passwordText))


                }.start()
                Toast.makeText(applicationContext, "Registracija uspješna, odlazak na prijavu", Toast.LENGTH_LONG).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }

        })

         login = findViewById(R.id.loginbtn)
         login!!.setOnClickListener{
              val intent = Intent(this, MainActivity::class.java)
              startActivity(intent)
        }
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}