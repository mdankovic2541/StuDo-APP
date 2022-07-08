package com.example.newproject

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.newproject.databinding.ActivityMaps2Binding
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaps2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val collegeName = intent.getStringExtra("College")
        var gc = Geocoder(this, Locale.getDefault())
        var addresses = gc.getFromLocationName(collegeName,2)
        var address = addresses!![0]
        // Add a marker
        if (addresses.size > 1){
            val address1 = addresses!![0]
            val address2 = addresses!![1]
            val college1 = LatLng(address1.latitude,address1.longitude)
            val college2 = LatLng(address2.latitude,address2.longitude)
            mMap!!.addMarker(MarkerOptions().position(college1).title(collegeName))
            mMap!!.addMarker(MarkerOptions().position(college2).title(collegeName))
            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(college1, 15f))
        }else {
            val college = LatLng(address.latitude, address.longitude)
            mMap!!.addMarker(MarkerOptions().position(college).title(collegeName))
            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(college, 15f))
        }
    }
}