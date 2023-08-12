package com.example.proyectofinalgymmz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectofinalgymmz.R
import com.example.proyectofinalgymmz.databinding.FragmentContactanosBinding
import com.example.proyectofinalgymmz.databinding.FragmentHomeBinding


class ContactanosFragment : Fragment() {


    private lateinit var binding: FragmentContactanosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentContactanosBinding.inflate(inflater,container,false)
        return binding.root
    }


}