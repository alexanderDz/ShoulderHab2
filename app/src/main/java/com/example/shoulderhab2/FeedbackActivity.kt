package com.example.shoulderhab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shoulderhab2.databinding.ActivityFeedbackBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    private lateinit var user: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    var ses1:String = ""
    var ses2:String = ""
    var ses3:String = ""
    var ses4:String = ""
    var ses5:String = ""
    var ses6:String = ""
    var ses7:String = ""
    var ses8:String = ""
    var ses9:String = ""
    var ses10:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        user = FirebaseAuth.getInstance()
        val correo = intent.getStringExtra("correo").toString()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        if (user.currentUser != null){
            user.currentUser?.let {
                db.collection("Usuarios").document(correo).get().addOnSuccessListener {
                    ses1 = (it.get("Sesion 1") as String?).toString()
                    ses2 = (it.get("Sesion 2") as String?).toString()
                    ses3 = (it.get("Sesion 3") as String?).toString()
                    ses4 = (it.get("Sesion 4") as String?).toString()
                    ses5 = (it.get("Sesion 5") as String?).toString()
                    ses6 = (it.get("Sesion 6") as String?).toString()
                    ses7 = (it.get("Sesion 7") as String?).toString()
                    ses8 = (it.get("Sesion 8") as String?).toString()
                    ses9 = (it.get("Sesion 9") as String?).toString()
                    ses10 = (it.get("Sesion 10") as String?).toString()

                    if(ses1 != "null")
                        binding.text1.setText(ses1)
                    if(ses2 != "null")
                        binding.text2.setText(ses2)
                    if(ses3 != "null")
                        binding.text3.setText(ses3)
                    if(ses4 != "null")
                        binding.text4.setText(ses4)
                    if(ses5 != "null")
                        binding.text5.setText(ses5)
                    if(ses6 != "null")
                        binding.text6.setText(ses6)
                    if(ses7 != "null")
                        binding.text7.setText(ses7)
                    if(ses8 != "null")
                        binding.text8.setText(ses8)
                    if(ses9 != "null")
                        binding.text9.setText(ses9)
                    if(ses10 != "null")
                        binding.text10.setText(ses10)
                }
            }
        }

        binding.buttonEnviar2.setOnClickListener {
            var mensaje = binding.Feedback.text.toString()
            binding.Feedback.text.clear()

            if(mensaje.isNotEmpty()){
                db.collection("Usuarios").document(correo).set(
                    hashMapOf(
                        "Feedback" to mensaje
                    ), SetOptions.merge()
                ).addOnSuccessListener {
                    Toast.makeText(this,"Mensaje guardado",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }



    }
}