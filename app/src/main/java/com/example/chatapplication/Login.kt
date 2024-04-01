package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var edit_email: EditText
    private lateinit var edit_password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edit_email = findViewById(R.id.edit_email)
        edit_password = findViewById(R.id.edit_password)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignup)

        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val email = edit_email.text.toString()
            val password = edit_password.text.toString()

            login(email,password);
        }
    }

    private fun login(email: String,password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for loggin in user
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.
                   Toast.makeText(this@Login,"User not available",Toast.LENGTH_SHORT).show()
                }
            }

    }
}