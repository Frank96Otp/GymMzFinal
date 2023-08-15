package com.example.proyectofinalgymmz.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectofinalgymmz.LoginActivity
import com.example.proyectofinalgymmz.R
import com.example.proyectofinalgymmz.databinding.FragmentContactanosBinding
import com.example.proyectofinalgymmz.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ContactanosFragment : Fragment() {


    private lateinit var binding: FragmentContactanosBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences(LoginActivity.SESSION_PREFERENS_KEY, Context.MODE_PRIVATE)
        email = sharedPreferences.getString(LoginActivity.EMAIL_DATA, "")?:""
        firebaseAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentContactanosBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener{
            with(sharedPreferences.edit()){
                putString(LoginActivity.EMAIL_DATA, "")
                apply()
            }
            firebaseAuth.signOut()
            goToLogin()
        }


    }

    private fun goToLogin() {
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }
}