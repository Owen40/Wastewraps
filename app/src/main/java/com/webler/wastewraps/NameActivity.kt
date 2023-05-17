package com.webler.wastewraps

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.webler.wastewraps.databinding.ActivityNameBinding

class NameActivity : AppCompatActivity() {
    private lateinit var Fname_edt : EditText
    private lateinit var Lname_edt : EditText
    private lateinit var Fin_btn : Button
    private lateinit var databaseReference: DatabaseReference
    private lateinit var currentUserId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Users")
        currentUserId = FirebaseAuth.getInstance().currentUser!!.uid

        Fname_edt = findViewById(R.id.edt_fname)
        Lname_edt = findViewById(R.id.edt_lname)
        Fin_btn = findViewById(R.id.SaveBtn)

        Fin_btn.setOnClickListener {
            val firstName = Fname_edt.text.toString()
            val lastName = Lname_edt.text.toString()

            //Create new child node with the userId as the key in the database
            val userReference = databaseReference.child(currentUserId)

            userReference.child("firstName").setValue(firstName)
            userReference.child("lastName").setValue(lastName)
                .addOnSuccessListener {
                    Toast.makeText(this, "Account created successfully on database", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LandingActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "failed to finish creating profile. Error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }


}