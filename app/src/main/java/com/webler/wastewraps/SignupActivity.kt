package com.webler.wastewraps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    lateinit var edtEmail:EditText
    lateinit var edtPass:EditText
    lateinit var confPass:EditText
    lateinit var gotolog:TextView
    lateinit var signupbtn:Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // View Bindings
        edtEmail = findViewById(R.id.edt_email)
        edtPass = findViewById(R.id.edt_pass)
        confPass = findViewById(R.id.edt_confpass)
        gotolog = findViewById(R.id.txt_gotolog)
        signupbtn = findViewById(R.id.register_btn)
        auth= Firebase.auth

        signupbtn.setOnClickListener {
            SignupUser()
            // val intent = Intent(this, NameActivity::class.java)
            // startActivity(intent)
        }

        gotolog.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Button to show the user's password
        val password = findViewById<EditText>(R.id.edt_pass)
        val confpwd = findViewById<EditText>(R.id.edt_confpass)
        val showpass = findViewById<Switch>(R.id.switch_showpass)

        showpass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                confpwd.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                confpwd.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }

    private fun SignupUser() {
        val email=edtEmail.text.toString()
        val pass = edtPass.text.toString()
        val confpass = confPass.text.toString()

        if (email.isBlank() || pass.isBlank() || confpass.isBlank()) {
            Toast.makeText(this, "Please fill in all the fields",Toast.LENGTH_LONG).show()
            return
        } else if (pass != confpass){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful){
                Toast.makeText(this,"Account created successfully",Toast.LENGTH_LONG).show()
                var intent = Intent(this, NameActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Failed to create account", Toast.LENGTH_LONG).show()
            }
        }
    }
}