package com.example.tictacto

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {
    private var boardGame: ArrayList<ImageButton> = ArrayList(9)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        setTitle("                       Tic Tac To")

        val board: LinearLayout = findViewById(R.id.linearLayout);
        for (i in 0 until 9)
            this.boardGame.add(board.findViewWithTag(i.toString()))

        this.boardGame.forEachIndexed { index, imageButton ->
            imageButton.setOnClickListener { onClick(imageButton) }
        }

        findViewById<Button?>(R.id.Activity2_reset).setOnClickListener { finish() }
    }

    private var turn: Boolean = true

    @SuppressLint("SetTextI18n")
    private fun onClick(btn: ImageButton) {
        if (btn.drawable != null) return

        if (this.turn)
            btn.setImageResource(R.drawable.x)
        else
            btn.setImageResource(R.drawable.circle)

        this.turn = !this.turn


        if (win()) endOfGame(!turn)
    }

    private fun win(): Boolean {
        val rowsWin = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (row in rowsWin) {
            val state1 = this.boardGame[row[0]].drawable?.constantState
            val state2 = this.boardGame[row[1]].drawable?.constantState
            val state3 = this.boardGame[row[2]].drawable?.constantState
            if (state1 != null && state1 == state2 && state2 == state3)
                return true;
        }

        return false;
    }

    @SuppressLint("SetTextI18n")
    private fun endOfGame(win: Boolean) {
        val name: String? = intent.getStringExtra("Name${if (win) 1 else 2}")

        val winner: TextView = findViewById(R.id.Activity2_TextWin)
        winner.setBackgroundColor(Color.parseColor("#E91E63"))
        winner.setTextColor(Color.parseColor("#BCF481"))
        winner.text = "The $name is a Winner"

        this.boardGame.forEachIndexed { index, imageButton ->
            imageButton.isClickable = false
        }
    }
}