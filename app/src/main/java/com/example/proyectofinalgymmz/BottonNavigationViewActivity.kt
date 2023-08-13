package com.example.proyectofinalgymmz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.proyectofinalgymmz.databinding.ActivityBottonNavigationViewBinding

class BottonNavigationViewActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityBottonNavigationViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottonNavigationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configurando el fragment conteiner view
        val fragmentView = supportFragmentManager.findFragmentById(R.id.fcv_navigation)as NavHostFragment
        val navController = fragmentView.navController

        binding.bnvMain.setupWithNavController(navController)


    }
}