package com.example.bilal.maparsample

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox

/**
 * Created by Bilal Hussain on 25/12/18 7:02 PM
 */

class MapBoxApp : Application(){

    lateinit var mapBox : Mapbox

    override fun onCreate() {
        super.onCreate()
        mapBox = Mapbox.getInstance(this, getString(R.string.map_box_token))


    }


}