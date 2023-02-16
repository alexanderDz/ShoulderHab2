package com.example.shoulderhab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shoulderhab2.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()

        checkIfUserIsLogged()


        binding.LogIn.setOnClickListener(){
            InicioSesion()
        }

        binding.registroTextView.setOnClickListener(){
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }


    }

    private fun checkIfUserIsLogged(){
        val email = user.currentUser?.email.toString()
        if(user.currentUser!=null){
            if(email == "medico@hotmail.com"){
                startActivity(Intent(this,ListadoActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this,MenuActivity::class.java))
                finish()
            }

        }
    }

    private fun InicioSesion(){
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (email.isNotEmpty()&&password.isNotEmpty()){

            if(email == "medico@hotmail.com"){
                user.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {mTask ->

                        if (mTask.isSuccessful){
                            startActivity(
                                Intent(this,
                                    ListadoActivity::class.java)
                            )
                            finish()

                        }else{
                            Toast.makeText(this,"Verifique datos",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{

                user.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {mTask ->

                        if (mTask.isSuccessful){
                            startActivity(
                                Intent(this,
                                    MenuActivity::class.java)
                            )
                            finish()

                        }else{
                            Toast.makeText(this,"Verifique datos",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }



        }else{
            Toast.makeText(this,"Campos vacios",
                Toast.LENGTH_SHORT).show()
        }

    }
}