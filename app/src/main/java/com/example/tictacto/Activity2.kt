package com.example.tictacto

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {
    var boardGame:ArrayList<ImageButton> = ArrayList();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        getBoard();


    }

    private fun getBoard(){
        //לשנות את זה שזה יעבוד לפי tag ולא לפי id
        for (i in 0 until 9) {
            var imageButtonID: Int =
                resources.getIdentifier("Activity2_box$i", "id", packageName);//find id dynamic
            if (imageButtonID != 0) {
                boardGame.add(findViewById(imageButtonID));
            }
        }

        boardGame.forEachIndexed{ index,imageButton->
            imageButton.setOnClickListener{
                click(index,imageButton)
        }
        }
    }

    //צריך לטפל בלחיצה כפולה שמשנה את הרקע
    var first:Boolean = true
    private fun click(i:Int ,imageButton: ImageButton){
        if (first)
             imageButton.setImageResource(R.drawable.x);
        else
             imageButton.setImageResource(R.drawable.circle);

        first = !first
    }
}