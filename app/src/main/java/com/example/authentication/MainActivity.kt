package com.example.authentication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.authentication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding;
    private lateinit var firebaseAuth: FirebaseAuth ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater);
        firebaseAuth=FirebaseAuth.getInstance();

        binding.loginbutton.setOnClickListener{
            login();
        }

        binding.registerbutton.setOnClickListener{
            register();
        }


        setContentView(binding.root)

        }
    fun register (){
        var email : String = binding.email.text.toString();
        var password : String = binding.password.text.toString();
        if(email.isNotEmpty() && password.isNotEmpty()){
           firebaseAuth.createUserWithEmailAndPassword(email,password)
               .addOnCompleteListener(this){task->
                   if(task.isSuccessful){
                       Toast.makeText(this@MainActivity,"başarıyla üye olundu",Toast.LENGTH_SHORT).show()
                   }
                   else {
                       Toast.makeText(this@MainActivity,"hata oldu",Toast.LENGTH_SHORT).show()
                   }
               }
        }

    }
    fun login (){
        var email : String = binding.email.text.toString();
        var password : String = binding.password.text.toString();
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Toast.makeText(this@MainActivity,"giriş yapıldı",Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@MainActivity,"giriş yapılamadı",Toast.LENGTH_SHORT).show()

                }
            }
    }


    }
