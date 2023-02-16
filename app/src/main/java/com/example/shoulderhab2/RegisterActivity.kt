package com.example.shoulderhab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shoulderhab2.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class RegisterActivity : AppCompatActivity() {

    private var numPnuevo=""

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var user: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        var numPacientes:String?
        var numP:Int?


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        db.collection("Usuarios").document("medico@hotmail.com").get().addOnSuccessListener {
            numPacientes = it.get("Numero de pacientes") as String?
            if(numPacientes != null){
                numP = numPacientes!!.toLong().toInt()
                numP = numP!! + 1
                numPnuevo = numP.toString()
            }

        }

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()


        binding.Volver1.setOnClickListener(){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        binding.Registro.setOnClickListener(){
            registerUser()
        }

    }

    private fun registerUser(){
        val name = binding.NameEditText.text.toString()
        val lastName = binding.LastNameEditText.text.toString()
        val email = binding.EmailReg.text.toString()
        val password = binding.PasswordReg.text.toString()


        if(name.isNotEmpty()&&lastName.isNotEmpty()&&email.isNotEmpty()&&password.isNotEmpty()){


            user.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity()) {task->

                    if(task.isSuccessful){

                        db.collection("Usuarios").document("medico@hotmail.com").set(
                            hashMapOf(
                                "Numero de pacientes" to numPnuevo,
                                "Paciente $numPnuevo Correo" to email,
                                "Paciente $numPnuevo Nombre" to "$name $lastName"
                            ), SetOptions.merge()
                        )
                        ///////////////////////
                        db.collection("Usuarios")
                            .document("medico@hotmail.com")
                            .collection(email)
                            .document("Sesiones")
                            .set(hashMapOf(
                                "Sesion 1" to ""
                            ), SetOptions.merge())
                        ///////////////////////
                        db.collection("Usuarios").document(email).set(
                            hashMapOf(
                                "Nombre" to name,
                                "Apellido" to lastName,
                                "Sesion" to "1",
                                "Feedback" to ""
                            )
                        )

                        binding.NameEditText.text.clear()
                        binding.LastNameEditText.text.clear()
                        binding.EmailReg.text.clear()
                        binding.PasswordReg.text.clear()

                        Toast.makeText(this,"Registro Exitoso",
                            Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(
                            this,
                            task.exception!!.message,
                            Toast.LENGTH_SHORT).show()

                    }
                }




        }else{
            Toast.makeText(this,"Espacios vacios",
                Toast.LENGTH_SHORT).show()
        }
    }
}