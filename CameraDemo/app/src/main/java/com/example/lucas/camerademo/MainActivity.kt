package com.example.lucas.camerademo

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.frosquivel.magicalcamera.MagicalPermissions
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.keySet
import android.app.Fragment
import android.content.Intent
import android.util.Log
import com.frosquivel.magicalcamera.MagicalCamera
import android.os.AsyncTask
import android.app.Activity
import android.R.attr.data
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var magicalPermissions: MagicalPermissions
    private lateinit var magicalCamera: MagicalCamera
    private val RESIZE_PHOTO_PIXELS_PERCENTAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf<String>(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        magicalPermissions = MagicalPermissions(this, permissions)

        magicalCamera = MagicalCamera(this, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions)

        buttonTakePhoto.setOnClickListener {
            magicalCamera.takePhoto()
        }
        buttonPickPhoto.setOnClickListener{
            magicalCamera.selectedPicture("Selecione imagem com")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val map = magicalPermissions.permissionResult(requestCode, permissions, grantResults)
        for (permission in map.keys) {
            Log.d("PERMISSIONS", permission + " was: " + map[permission])
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //you should to call the method ever, for obtain the bitmap photo (= magicalCamera.getPhoto())
            magicalCamera.resultPhoto(requestCode, resultCode, data)
            imageViewPhoto.setImageBitmap(magicalCamera.photo)
            magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),"IMG_${Random().nextInt()}","Camera Demo", MagicalCamera.JPEG, true)
        }
    }
}
