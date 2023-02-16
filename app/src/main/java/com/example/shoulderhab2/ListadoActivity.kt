package com.example.shoulderhab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoulderhab2.databinding.ActivityListadoBinding
import com.example.shoulderhab2.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ListadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding
    private lateinit var user: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        var paciente1Nombre=""
        var paciente2Nombre=""
        var paciente3Nombre=""
        var paciente4Nombre=""
        var paciente5Nombre=""
        var paciente6Nombre=""
        var paciente7Nombre=""
        var paciente8Nombre=""
        var paciente9Nombre=""
        var paciente10Nombre=""

        var paciente1Correo=""
        var paciente2Correo=""
        var paciente3Correo=""
        var paciente4Correo=""
        var paciente5Correo=""
        var paciente6Correo=""
        var paciente7Correo=""
        var paciente8Correo=""
        var paciente9Correo=""
        var paciente10Correo=""

        user = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.LogOutImageDoc.setOnClickListener {
            user.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        if (user.currentUser != null){
            user.currentUser?.let {
                db.collection("Usuarios").document("medico@hotmail.com").get().addOnSuccessListener {
                    paciente1Nombre = (it.get("Paciente 1 Nombre") as String?).toString()
                    paciente2Nombre = (it.get("Paciente 2 Nombre") as String?).toString()
                    paciente3Nombre = (it.get("Paciente 3 Nombre") as String?).toString()
                    paciente4Nombre = (it.get("Paciente 4 Nombre") as String?).toString()
                    paciente5Nombre = (it.get("Paciente 5 Nombre") as String?).toString()
                    paciente6Nombre = (it.get("Paciente 6 Nombre") as String?).toString()
                    paciente7Nombre = (it.get("Paciente 7 Nombre") as String?).toString()
                    paciente8Nombre = (it.get("Paciente 8 Nombre") as String?).toString()
                    paciente9Nombre = (it.get("Paciente 9 Nombre") as String?).toString()
                    paciente10Nombre = (it.get("Paciente 10 Nombre") as String?).toString()

                    paciente1Correo = (it.get("Paciente 1 Correo") as String?).toString()
                    paciente2Correo = (it.get("Paciente 2 Correo") as String?).toString()
                    paciente3Correo = (it.get("Paciente 3 Correo") as String?).toString()
                    paciente4Correo = (it.get("Paciente 4 Correo") as String?).toString()
                    paciente5Correo = (it.get("Paciente 5 Correo") as String?).toString()
                    paciente6Correo = (it.get("Paciente 6 Correo") as String?).toString()
                    paciente7Correo = (it.get("Paciente 7 Correo") as String?).toString()
                    paciente8Correo = (it.get("Paciente 8 Correo") as String?).toString()
                    paciente9Correo = (it.get("Paciente 9 Correo") as String?).toString()
                    paciente10Correo = (it.get("Paciente 10 Correo") as String?).toString()

                    if(paciente1Nombre != "null")
                        binding.text1.setText(paciente1Nombre)
                    if(paciente2Nombre != "null")
                        binding.text2.setText(paciente2Nombre)
                    if(paciente3Nombre != "null")
                        binding.text3.setText(paciente3Nombre)
                    if(paciente4Nombre != "null")
                        binding.text4.setText(paciente4Nombre)
                    if(paciente5Nombre != "null")
                        binding.text5.setText(paciente5Nombre)
                    if(paciente6Nombre != "null")
                        binding.text6.setText(paciente6Nombre)
                    if(paciente7Nombre != "null")
                        binding.text7.setText(paciente7Nombre)
                    if(paciente8Nombre != "null")
                        binding.text8.setText(paciente8Nombre)
                    if(paciente9Nombre != "null")
                        binding.text9.setText(paciente9Nombre)
                    if(paciente10Nombre != "null")
                        binding.text10.setText(paciente10Nombre)
                }
            }
        }

        binding.card1.setOnClickListener{
            if(paciente1Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente1Correo)
                startActivity(intent)
            }

        }

        binding.card2.setOnClickListener{
            if(paciente2Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente2Correo)
                startActivity(intent)
            }

        }

        binding.card3.setOnClickListener{
            if(paciente3Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente3Correo)
                startActivity(intent)
            }

        }

        binding.card4.setOnClickListener{
            if(paciente4Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente4Correo)
                startActivity(intent)
            }

        }

        binding.card5.setOnClickListener{
            if(paciente5Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente5Correo)
                startActivity(intent)
            }

        }

        binding.card6.setOnClickListener{
            if(paciente6Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente6Correo)
                startActivity(intent)
            }

        }

        binding.card7.setOnClickListener{
            if(paciente7Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente7Correo)
                startActivity(intent)
            }

        }

        binding.card8.setOnClickListener{
            if(paciente8Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente8Correo)
                startActivity(intent)
            }

        }

        binding.card8.setOnClickListener{
            if(paciente8Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente8Correo)
                startActivity(intent)
            }

        }

        binding.card9.setOnClickListener{
            if(paciente9Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente9Correo)
                startActivity(intent)
            }

        }

        binding.card10.setOnClickListener{
            if(paciente10Nombre != "null"){
                val intent = Intent(this,FeedbackActivity::class.java)
                intent.putExtra("correo",paciente10Correo)
                startActivity(intent)
            }

        }

    }
}