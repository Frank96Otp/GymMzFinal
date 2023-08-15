package com.example.proyectofinalgymmz

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.proyectofinalgymmz.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {


    private lateinit var sharePreferences: SharedPreferences

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var googleLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicializacion de lateinit
        binding = ActivityLoginBinding.inflate(layoutInflater)
        firebaseAuth = Firebase.auth


        setContentView(binding.root)

        setViews()


        googleLauncher  =   registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if(result.resultCode == RESULT_OK){
                val task =  GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account =  task.getResult(ApiException::class.java)
                    authFireBAseWithGoogle(account.idToken)
                }catch (e: Exception){

                }
            }

        }

        sharePreferences = this.getSharedPreferences(SESSION_PREFERENS_KEY, Context.MODE_PRIVATE)
        val email = sharePreferences.getString(EMAIL_DATA, "")

        if (email != null) {
            if (email.isNotEmpty()){
                goToMenu()
            }
        }
    }

    private fun authFireBAseWithGoogle(idToken: String?) {
        var authCredential = GoogleAuthProvider.getCredential(idToken!!, null)
        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    with(sharePreferences.edit()){
                        putString(EMAIL_DATA, user?.email ).commit()
                    }
                    goToMenu()
                }else{
                    Toast.makeText(this, "Ocurrio un error" , Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun setViews() {

        binding.btnLogin.setOnClickListener {
            val email= binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            signInWithFirebas(email,password)
        }

        binding.btnLoginWithGoogle.setOnClickListener {
            signinWithGoogle()
        }

        binding.btnSignUp.setOnClickListener {
            val email= binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            signUpWithFirebase(email,password)

        }
    }

    private fun signInWithFirebas(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    val user =  firebaseAuth.currentUser
                    with(sharePreferences.edit()){
                        putString(EMAIL_DATA, user?.email ).commit()
                    }
                    goToMenu()
                }else{
                    Toast.makeText(this,"contraseña o usuario incorrecto",Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun signUpWithFirebase(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"El usuario fue creado correctamente",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"El usuario no fue creado",Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun signinWithGoogle() {
        val googleSingInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_app_id))
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this, googleSingInOptions)
        val intent = googleClient.signInIntent
        googleLauncher.launch(intent)

    }



    private fun goToMenu(){
            val intent = Intent(this, BottonNavigationViewActivity::class.java)
            startActivity(intent)
            finish()
    }

    companion object{
        const val SESSION_PREFERENS_KEY = "SESSION_PREFERENS_KEY"
        const val EMAIL_DATA = "EMAIL_DATA"
    }
}