package com.sunnyweather.permissionx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sunnyweather.mylibrary.PermissionX
import com.sunnyweather.permissionx.databinding.ActivityMainBinding
import android.Manifest
import android.content.Intent
import android.net.Uri

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.makeCallBtn.setOnClickListener{
            PermissionX.request(this, Manifest.permission.CALL_PHONE)//获取权限
            { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}