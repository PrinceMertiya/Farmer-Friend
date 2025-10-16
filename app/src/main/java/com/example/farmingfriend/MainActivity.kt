package com.example.farmingfriend

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.farmingfriend.Home
import com.example.farmingfriend.Market
import com.example.farmingfriend.Profile
import com.example.farmingfriend.Tickets
import com.example.farmingfriend.Weather
import com.example.farmingfriend.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Declare binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding and set content view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load initial fragment
        replaceFragment(Home())

        // Setup BottomNavigationView item selection listener
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Log.d("MainActivity", "Home selected")
                    replaceFragment(Home())
                }
                R.id.navigation_profile -> {
                    Log.d("MainActivity", "Profile selected")
                    replaceFragment(Profile())
                }
                R.id.navigation_market -> {
                    Log.d("MainActivity", "Market selected")
                    replaceFragment(Market())
                }
                R.id.navigation_tickets -> {
                    Log.d("MainActivity", "Tickets selected")
                    replaceFragment(Tickets())
                }
                R.id.navigation_weather -> {
                    Log.d("MainActivity", "Weather selected")
                    replaceFragment(Weather())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.frameLayout, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }
}
