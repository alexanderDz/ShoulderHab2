package com.example.shoulderhab2

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shoulderhab2.databinding.ActivityExerciseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.ingenieriajhr.blujhr.BluJhr

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private lateinit var user: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    var max = ""
    var ej_num = ""
    lateinit var numSesionActual:String
    lateinit var numSesion:String
    var max1 = ""
    var max2 = ""
    var max3 = ""
    var max4 = ""
    var max5 = ""


    var addres = ""
    lateinit var blue: BluJhr
    var estadoConexion = BluJhr.Connected.False

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        user = FirebaseAuth.getInstance()
        val email = user.currentUser?.email.toString()
        var datos = ""
        var sesion:String?
        var num:Int?


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://i.pinimg.com/originals/35/59/1b/35591bd4a5ec718ed5139a77460f426a.jpg","Ejercicio 1"))
        imageList.add(SlideModel("https://i.pinimg.com/originals/59/36/7d/59367d4075044473d10502d3d3e8e903.jpg","Ejercicio 2"))
        imageList.add(SlideModel("https://i.pinimg.com/originals/e1/ed/60/e1ed60ba8ccbd5bdcc09daa6f5e4bb46.jpg","Ejercicio 3"))
        imageList.add(SlideModel("https://i.pinimg.com/originals/e6/67/ff/e667ffffbdb8f538aaa88b9462d0ec3c.jpg","Ejercicio 4"))
        imageList.add(SlideModel("https://i.pinimg.com/originals/99/7e/dd/997edd8e0280edcdfe230c269b0108f5.jpg","Ejercicio 5"))

        
        binding.slider.setImageList(imageList,ScaleTypes.FIT)

        ////////////////////////////////////////


        /////// PARA BLUETOOTH//////////////
        addres = intent.getStringExtra("addres").toString()
        blue = BluJhr(this)

        blue.setDataLoadFinishedListener(object:BluJhr.ConnectedBluetooth{
            override fun onConnectState(state: BluJhr.Connected) {
                estadoConexion = state
                when (state) {
                    BluJhr.Connected.True -> {
                        Toast.makeText(applicationContext, "True", Toast.LENGTH_SHORT).show()
                        rxReceived()
                    }
                    BluJhr.Connected.Pending -> {
                        Toast.makeText(applicationContext, "Pending", Toast.LENGTH_SHORT).show()
                    }
                    BluJhr.Connected.False -> {
                        Toast.makeText(applicationContext, "False", Toast.LENGTH_SHORT).show()
                    }
                    BluJhr.Connected.Disconnect -> {
                        Toast.makeText(applicationContext, "Disconnect", Toast.LENGTH_SHORT).show()
                        estadoConexion = state
                        startActivity(Intent(applicationContext,MenuActivity::class.java))
                    }
                }
            }
        })


        binding.boton1.setOnClickListener{
            if(estadoConexion == BluJhr.Connected.True){
                blue.bluTx("1")
                ej_num = "1"
            }
        }
        binding.boton2.setOnClickListener{
            if(estadoConexion == BluJhr.Connected.True){
                blue.bluTx("2")
                ej_num = "2"
            }
        }
        binding.boton3.setOnClickListener{
            if(estadoConexion == BluJhr.Connected.True){
                blue.bluTx("3")
                ej_num = "3"
            }
        }
        binding.boton4.setOnClickListener{
            if(estadoConexion == BluJhr.Connected.True){
                blue.bluTx("4")
                ej_num = "4"
            }
        }
        binding.boton5.setOnClickListener{
            if(estadoConexion == BluJhr.Connected.True){
                blue.bluTx("5")
                ej_num = "5"
            }
        }
        binding.boton0.setOnClickListener {
            if(estadoConexion == BluJhr.Connected.True){
                blue.bluTx("0")
            }
        }

        binding.HomeBack.setOnClickListener(){
            startActivity(Intent(this,MenuActivity::class.java))
            finish()
        }

        binding.botonEmpezar.setOnClickListener {
            binding.receptor.text = "Colocate en la posición del ejercicio que quieres realizar y luego presiona el boton del ejercicio, cuando termines tus repeticiones presiona el boton de finalizar"
            if (user.currentUser != null){
                user.currentUser?.let {
                    db.collection("Usuarios").document(email).get().addOnSuccessListener {
                        sesion = it.get("Sesion") as String?
                        binding.receptor2.setText("Sesión $sesion")

                        if(sesion != null){
                             num = sesion!!.toLong().toInt()
                            numSesionActual = num.toString()
                                num = num!! + 1
                            numSesion = num.toString()
                        }

                    }
                    //binding.user_id.text = it.email
                }
            }
        }

        binding.botonTerminar.setOnClickListener {

            db.collection("Usuarios").document(email).set(
                hashMapOf(
                    "Sesion" to numSesion

                ), SetOptions.merge()
            )

            val rutina = hashMapOf(
                "Sesion $numSesionActual" to "$max1, $max2, $max3, $max4, $max5"
            )
            /// GUARDADO EN MEDICO
            db.collection("Usuarios")
                .document("medico@hotmail.com")
                .collection(email)
                .document("Sesiones")
                .set(rutina, SetOptions.merge())


            // GUARDADO EN USUARIO
            db.collection("Usuarios")
                .document(email)
                .set(hashMapOf(
                    "Sesion $numSesionActual" to "$max1, $max2, $max3, $max4, $max5",
                    "Ejercicio 1" to max1,
                    "Ejercicio 2" to max2,
                    "Ejercicio 3" to max3,
                    "Ejercicio 4" to max4,
                    "Ejercicio 5" to max5

                ), SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "Guardado con exito", Toast.LENGTH_SHORT).show()
                }

            /*db.collection("Usuarios")
                .document(email)
                .collection("Sesiones")
                .document("Sesion $numSesionActual")
                .set(rutina, SetOptions.merge())
                .addOnSuccessListener {
                Toast.makeText(applicationContext, "Guardado con exito", Toast.LENGTH_SHORT).show()
            }*/
        }


    }

    private fun rxReceived() {
        blue.loadDateRx(object:BluJhr.ReceivedData{
            override fun rxDate(rx: String) {
                binding.receptor.text = rx
                max = rx
                if(ej_num == "1")
                    max1 = max
                if(ej_num == "2")
                    max2 = max
                if(ej_num == "3")
                    max3 = max
                if(ej_num == "4")
                    max4 = max
                if(ej_num == "5")
                    max5 = max

            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Toast.makeText(applicationContext, "Entro", Toast.LENGTH_SHORT).show()
        if (estadoConexion != BluJhr.Connected.True) {
            blue.connect(addres)
        }

    }








    }