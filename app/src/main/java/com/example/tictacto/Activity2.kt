package com.example.tictacto

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {
    private var boardGame: ArrayList<ImageButton> = ArrayList(9)
    private var name1: String? = null
    private var name2: String? = null
    private var textGame: TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        textGame = findViewById(R.id.Activity2_textOfFlow)
        name1 = intent.getStringExtra("Name1")
        name2 = intent.getStringExtra("Name2")
        textGame?.text = "$name1 play"

        val board: LinearLayout = findViewById(R.id.linearLayout);
        for (i in 0 until 9)
            this.boardGame.add(board.findViewWithTag(i.toString()))

        this.boardGame.forEachIndexed { index, imageButton ->
            imageButton.setOnClickListener { onClick(imageButton) }
        }

        findViewById<Button?>(R.id.Activity2_reset).setOnClickListener { finish() }
    }

    private var turn: Boolean = true
    private var counter = 0

    @SuppressLint("SetTextI18n")
    private fun onClick(btn: ImageButton) {
        var name: String? = if (!turn) name1 else name2

        if (btn.drawable != null) return

        if (this.turn) btn.setImageResource(R.drawable.x)
        else btn.setImageResource(R.drawable.circle)

        this.turn = !this.turn
        this.counter++
        textGame?.text = "$name play"

        name = if (!turn) name1 else name2
        if (win()) return endOfGame(name)
        if (counter == 9) endOfGame()
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
    private fun endOfGame(win: String? = null) {
        textGame?.setBackgroundColor(Color.parseColor("#E91E63"))
        textGame?.setTextColor(Color.parseColor("#BCF481"))

        if (win == null)
            textGame?.text = "$name1 and $name2 is the draw"
        else if (win == name1)
            textGame?.text = "$name1 is the winner"
        else
            textGame?.text = "$name2 is the winner"

        this.boardGame.forEachIndexed { index, imageButton ->
            imageButton.isClickable = false
        }

        findViewById<Button>(R.id.Activity2_reset).visibility = View.VISIBLE
    }
}