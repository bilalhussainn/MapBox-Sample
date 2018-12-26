package com.example.bilal.maparsample.utils

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat



/**
 * Created by Bilal Hussain on 25/12/18 7:51 PM
 */
object PermissionUtil {

    var PERMISSION_ALL = 1
    var PERMISSIONS = arrayOf( android.Manifest.permission.ACCESS_FINE_LOCATION,
//            android.Manifest.permission.READ_CONTACTS,
//            android.Manifest.permission.WRITE_CONTACTS,
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            android.Manifest.permission.READ_SMS,
            android.Manifest.permission.CAMERA)


    fun hasPermissions(context: Context?, permissions: Array<String>): Boolean {
        if (context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context!!, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }
}