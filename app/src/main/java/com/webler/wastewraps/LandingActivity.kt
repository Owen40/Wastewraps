package com.webler.wastewraps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.webler.wastewraps.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.tips -> replaceFragment(TipsFragment())
                R.id.profile -> replaceFragment(ProfileFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}