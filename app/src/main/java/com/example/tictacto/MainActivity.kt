package com.example.tictacto


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setTitle("Welcome To The Tic Tac To")
//        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
//        supportActionBar?.setTitle("new title")
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val startGame: Button = findViewById(R.id.MainActivity_StartBtn)
        startGame.setOnClickListener(::login)
    }

   private fun login(view: View){
        val player1:String = findViewById<EditText?>(R.id.Main_player1).text.toString()
        val player2:String = findViewById<EditText?>(R.id.Main_player2).text.toString()

        if(player1.isNotEmpty() && player2.isNotEmpty()){
            val intent =  Intent(this,Activity2::class.java)
            startActivity(intent)
        }

    }
}