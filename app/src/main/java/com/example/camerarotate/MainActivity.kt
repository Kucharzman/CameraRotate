package com.example.camerarotate

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.app.ActivityCompat
import com.example.camerarotate.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var bindowanie : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindowanie = ActivityMainBinding.inflate(layoutInflater)
        val widok = bindowanie.root
        setContentView(widok)

        bindowanie.sbRotateX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                var rotata : Float = bindowanie.sbRotateX.progress.toFloat()
                bindowanie.ivMain.rotationX = rotata - 180

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        bindowanie.sbRotateY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                var rotata : Float = bindowanie.sbRotateY.progress.toFloat()
                bindowanie.ivMain.rotationY = rotata - 180

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        bindowanie.sbTransparency.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var flota : Float = bindowanie.sbTransparency.progress.toFloat()
                findViewById<ImageView>(R.id.ivMain).setAlpha(flota/100)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


        findViewById<Button>(R.id.bttCamera).isEnabled = false

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 111)
        }
        else{
            findViewById<Button>(R.id.bttCamera).isEnabled = true
        }

        findViewById<Button>(R.id.bttCamera).setOnClickListener {

            var intei = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intei,101)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101){

            var pictura = data?.getParcelableExtra<Bitmap>("data")
            findViewById<ImageView>(R.id.ivMain).setImageBitmap(pictura)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            findViewById<Button>(R.id.bttCamera).isEnabled = true
        }

    }

}