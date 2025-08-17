package com.sunnyweather.mylibrary

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
typealias PermissionCallback = (Boolean,List<String>) -> Unit// typealias给任意类型指定一个别名

class InvisibleFragment:Fragment() {
    private var callback: (PermissionCallback)? = null
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let {
                it(allGranted, deniedList)
            }
        }
    }
}