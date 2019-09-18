package com.bordscode.catching_game

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    var score = 0;
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray = arrayListOf(imageViewBird1, imageViewBird2, imageViewDog1, imageViewDog2, imageViewMonkey, imageViewPanda1, imageViewPanda2, imageViewPig, imageViewRabbit)

            doProcess()



    }

    //scoring the game
    fun scoring (view: View){
        score++
        textViewScore.text = "Score: " + score
    }


    fun doProcess(){

        hideImages()

        object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                textViewScore.text = "Time Is Up!"
                handler.removeCallbacks(runnable) // Remove your callback from your handler
                for (mgs in imageArray){
                    mgs.visibility=View.INVISIBLE
                }
                val alert= AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Do you want to play Again??")
                alert.setMessage("Are you Sure")
                alert.setPositiveButton("Yes"){ dialog: DialogInterface, which: Int -> run{
                    textViewScore.text="0"
                    score = 0
                    doProcess()
                }}
                alert.setNegativeButton("No"){ dialog: DialogInterface, which: Int -> ""}//Toast.makeText(this@MainActivity,"Great",Toast.LENGTH_LONG).show()
                alert.show()
            }

            // count down
            override fun onTick(millisUntilFinished: Long) {
                var x = millisUntilFinished / 1000
                textViewTimer.text = x.toString()
            }
        }.start()
    }

    fun hideImages(){
        runnable=object : Runnable{
            override fun run() {
                for (imgs in imageArray){
                    imgs.visibility= View.INVISIBLE
                }
                val random= Random()
                val index=random.nextInt(5)
                imageArray[index].visibility= View.VISIBLE
                handler.postDelayed(this,500)
            }

        }
        handler.post(runnable)

    }
}

