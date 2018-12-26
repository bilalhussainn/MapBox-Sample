package com.example.bilal.maparsample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bilal.maparsample.utils.PermissionUtil
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import kotlinx.android.synthetic.main.activity_main.*
import com.mapbox.mapboxsdk.style.layers.Layer

class MainActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private lateinit var mapView: MapView
    private var mapboxMap: MapboxMap? = null
    private lateinit var currentLatLng: LatLng
    private var currentMarker: Marker? = null

    // @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Example of a call to a native method
        // sample_text.text = stringFromJNI()

        setContentView(R.layout.activity_main)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        change.setOnClickListener {
            mapboxMap?.let {
                for (singleLayer in it.layers) {
                    Log.d("TAG", "onMapReady: layer id = " + singleLayer.id)
                }
            }
            //     mapboxMap?.getLayers("park").setProperties(
            //             PropertyFactory.fillColor(Color.parseColor("#0e6001")));
        }

    }

    override fun onLocationChanged(location: Location?) {
        currentLatLng = LatLng(location?.latitude!!, location?.longitude!!)
        currentMarker?.remove()
        currentMarker = mapboxMap?.addMarker(MarkerOptions().position(currentLatLng).title(
                        getString(R.string.draw_marker_options_title)).snippet(
                        getString(R.string.draw_marker_options_snippet)))

        mapboxMap?.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng))

    }

    override fun onMapReady(mapboxMap: MapboxMap?) {
        this.mapboxMap = mapboxMap
        loadMarker()
    }

    @SuppressLint("MissingPermission")
    fun loadMarker() {
        // if (PermissionUtil.hasPermissions(applicationContext, PermissionUtil.PERMISSIONS)) {
        var locationManager: LocationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0f, this)
        // }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    public override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    public override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    public override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    // external fun stringFromJNI(): String

    /*companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }*/
}
