package com.alex.hoo.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alex.hoo.R
import java.util.*

class LoginActivity : AppCompatActivity(){

    //获取权限
    private val REQUEST_CODE_PERMISSIONS = 101
    private val KEY_PERMISSIONS_REQUEST_COUNT = "KEY_PERMISSIONS_REQUEST_COUNT"
    private val MAX_NUMBER_REQUEST_PERMISSIONS = 2

    private val perimissions = Arrays.asList(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    private var permissionRequestCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        savedInstanceState?.let {
            permissionRequestCount = it.getInt(KEY_PERMISSIONS_REQUEST_COUNT, 0)
        }
        requestPermissionsIfNecessary()
    }

    @SuppressLint("ShowToast")
    private fun requestPermissionsIfNecessary(){
        if (!checkAllPermissions()){
            if (permissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS){
                permissionRequestCount +=1
                ActivityCompat.requestPermissions(this,
                    perimissions.toTypedArray(),
                    REQUEST_CODE_PERMISSIONS)
            }
        } else {
            Toast.makeText(this, R.string.set_permissions_in_settings,
                Toast.LENGTH_LONG).show()
        }
    }

    private fun checkAllPermissions(): Boolean{
        var hasPermissions = true
        for (permission in perimissions){
            hasPermissions = hasPermissions and isPermissionGranted(permission)
        }
        return hasPermissions
    }

    private fun isPermissionGranted(permission: String) =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS){
            requestPermissionsIfNecessary()
        }
    }

}