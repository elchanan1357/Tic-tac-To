package com.example.tictacto

import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.biometrics.PromptContentItemPlainText
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startGame: Button = findViewById(R.id.MainActivity_StartBtn);
        startGame.setOnClickListener(::moveActivity2);


    }

   private fun moveActivity2(view: View){
        val namePlayer1:EditText = findViewById(R.id.MainActivity_Player1);
        val namePlayer2:EditText = findViewById(R.id.MainActivity_Player2);
        val name: String = namePlayer2.text.toString()
        Log.d("print",name);

        if(namePlayer1.text.toString().isNotEmpty() && namePlayer2.text.toString().isNotEmpty()){
            val intent =  Intent(this,Activity2::class.java);
            startActivity(intent);
        }

    }
}