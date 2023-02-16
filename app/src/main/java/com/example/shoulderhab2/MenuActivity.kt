package com.example.shoulderhab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoulderhab2.databinding.ActivityMenuBinding
import com.example.shoulderhab2.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var user: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        user = FirebaseAuth.getInstance()
        val email = user.currentUser?.email.toString()

        var max1:String?
        var max2:String?
        var max3:String?
        var max4:String?
        var max5:String?
        var feedback:String = ""


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        binding.LogOutImage.setOnClickListener{
            user.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        binding.cardHistorial.setOnClickListener {
            startActivity(Intent(this,ConsultasActivity::class.java))
        }

        binding.cardEjercicios.setOnClickListener{
            startActivity(Intent(this,BlueActivity::class.java))
        }

        if (user.currentUser != null){
            user.currentUser?.let {
                db.collection("Usuarios").document(email).get().addOnSuccessListener {
                    max1 = it.get("Ejercicio 1") as String?
                    max2 = it.get("Ejercicio 2") as String?
                    max3 = it.get("Ejercicio 3") as String?
                    max4 = it.get("Ejercicio 4") as String?
                    max5 = it.get("Ejercicio 5") as String?
                    feedback = (it.get("Feedback") as String?).toString()
                    binding.textAngulo1.setText(max1)
                    binding.textAngulo2.setText(max2)
                    binding.textAngulo3.setText(max3)
                    binding.textAngulo4.setText(max4)
                    binding.textAngulo5.setText(max5)
                    binding.TextFeedback.setText(feedback)

                }
                //binding.user_id.text = it.email
            }
        }

    }

}