package com.example.shoulderhab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoulderhab2.databinding.ActivityConsultasBinding
import com.example.shoulderhab2.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ConsultasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConsultasBinding
    private lateinit var user: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        var ses1:String?
        var ses2:String?
        var ses3:String?
        var ses4:String?
        var ses5:String?
        var ses6:String?


        user = FirebaseAuth.getInstance()
        val email = user.currentUser?.email.toString()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultas)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = ActivityConsultasBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)


        if (user.currentUser != null){
            user.currentUser?.let {
                db.collection("Usuarios").document(email).get().addOnSuccessListener {
                    ses1 = it.get("Sesion 1") as String?
                    ses2 = it.get("Sesion 2") as String?
                    ses3 = it.get("Sesion 3") as String?
                    ses4 = it.get("Sesion 4") as String?
                    ses5 = it.get("Sesion 5") as String?
                    ses6 = it.get("Sesion 6") as String?
                    binding.textView1.setText(ses1)
                    binding.textView2.setText(ses2)
                    binding.textView3.setText(ses3)
                    binding.textView4.setText(ses4)
                    binding.textView5.setText(ses5)
                    binding.textView6.setText(ses6)
                }
            }
        }
    }
}