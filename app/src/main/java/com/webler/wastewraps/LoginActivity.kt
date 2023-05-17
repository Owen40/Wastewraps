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

class LoginActivity : AppCompatActivity() {
    lateinit var loginbtn:Button
    lateinit var edtMail:EditText
    lateinit var edt_pass:EditText
    lateinit var gotoreg:TextView

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //View Binding Code
        gotoreg = findViewById(R.id.txt_gotoreg)
        loginbtn = findViewById(R.id.login_btn)
        edtMail = findViewById(R.id.edt_email)
        edt_pass = findViewById(R.id.edt_pass)

        //Initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        loginbtn.setOnClickListener {
            login()
        }

        gotoreg.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }


        // Button to show the user's password
        val password = findViewById<EditText>(R.id.edt_pass)
        val showpass = findViewById<Switch>(R.id.switch_showpass)

        showpass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }

    private fun login() {
        val email = edtMail.text.toString()
        val pass = edt_pass.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully signed in", Toast.LENGTH_LONG).show()
                val user = FirebaseAuth.getInstance().currentUser
                val sessionToken = user?.uid
                SessionManager.setSessionToken(sessionToken)
                var intent = Intent(this, LandingActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Failed to log in to account", Toast.LENGTH_LONG)
            }
        }
    }
}