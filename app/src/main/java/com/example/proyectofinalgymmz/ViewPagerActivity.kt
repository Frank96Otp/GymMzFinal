package com.example.proyectofinalgymmz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.proyectofinalgymmz.databinding.ActivityViewPagerBinding
import com.example.proyectofinalgymmz.databinding.BoardItemBinding
import com.example.proyectofinalgymmz.onboarding.Board
import com.example.proyectofinalgymmz.onboarding.ViewPagerAdapter

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding
    private lateinit var boardList: List<Board>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        boardList = listOf(

            Board(
                fondo = R.color.onboarding_1,
                img = R.drawable.onbording1,
                titulo = "Paga con de una manera facil",
                descripcion = "esta descripcion se tiene que llenar con un texto que hable del pago de tarjeta"
            ),
            Board(
                fondo = R.color.onboarding_1,
                img = R.drawable.onbording2,
                titulo = "Encuentras todos nuestros locales",
                descripcion = "esta descripcion se tiene que llenar con un texto que hable del pago de tarjeta"
            ),
            Board(
                fondo = R.color.onboarding_1,
                img = R.drawable.onbording3,
                titulo = "Contactanos directamente con nosotros",
                descripcion = "esta descripcion se tiene que llenar con un texto que hable del pago de tarjeta"
            )
        )

        val adapter = ViewPagerAdapter(boardList)
        binding.viewPager.adapter = adapter

    }
}