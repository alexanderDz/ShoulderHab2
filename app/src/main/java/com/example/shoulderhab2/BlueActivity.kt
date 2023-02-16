package com.example.shoulderhab2

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.shoulderhab2.databinding.ActivityBlueBinding
import com.example.shoulderhab2.databinding.ActivityExerciseBinding
import com.ingenieriajhr.blujhr.BluJhr

class BlueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlueBinding

    lateinit var blue: BluJhr
    var devicesBluetooth = ArrayList<String>()
    var state = BluJhr.Connected.False

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = ActivityBlueBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        blue = BluJhr(this)
        blue.onBluetooth()

        binding.listDeviceBluetooth.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this,ExerciseActivity::class.java)
            intent.putExtra("addres",devicesBluetooth[i])
            startActivity(intent)
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (blue.checkPermissions(requestCode,grantResults)){
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show()
            blue.initializeBluetooth()
        }else{
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
                blue.initializeBluetooth()
            }else{
                Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!blue.stateBluetoooth() && requestCode == 100){
            blue.initializeBluetooth()
        }else{
            if (requestCode == 100){
                devicesBluetooth = blue.deviceBluetooth()
                if (devicesBluetooth.isNotEmpty()){
                    val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,devicesBluetooth)
                    binding.listDeviceBluetooth.adapter = adapter
                }else{
                    Toast.makeText(this, "No tienes vinculados dispositivos", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}