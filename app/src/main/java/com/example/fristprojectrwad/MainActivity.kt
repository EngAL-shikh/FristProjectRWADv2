package com.example.fristprojectrwad

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"
private const val REQUEST_CODE_CHEAT = 0
class MainActivity : AppCompatActivity() {
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

   lateinit var cheatButton: Button
    var Tanswer:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var easylistQuesation= arrayListOf<Qustion>()
        easylistQuesation.addAll(quizViewModel.easyqustionBank)
        var midlistQuestion=arrayListOf<Qustion>()
        midlistQuestion.addAll(quizViewModel.midqustionBank)
        var diflistQuestion=arrayListOf<Qustion>()
        diflistQuestion.addAll(quizViewModel.difqustionBank)
        randomQustion(easylistQuesation)
        randomQustion(midlistQuestion)
        randomQustion(diflistQuestion)
        cheatButton = findViewById(R.id.cheating)











        //=======trueBT=================
        bt_true.setOnClickListener {
            checkAnswer(true)
        }

        //=======falseBT=================
        bt_false.setOnClickListener {
            checkAnswer(false)
        }




        //=======Next quastion=================
        tv_qustion.setText(quizViewModel.currentQuestionText)
        tv_qustion.setOnClickListener {


            updateQuestion()

        }



        bt_next.setOnClickListener {

            updateQuestion()

        }



        //=======previous=================
        previous.setOnClickListener {

            privQ()
        }






        cheatButton.setOnClickListener {
            // Start CheatActivity

            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)

        }






    }







    //===================== Get random ==============================

    fun randomQustion(list: ArrayList<Qustion>){
        var random = Random()
        for (i in 1..2){
            var randIndex=random.nextInt(list.size)
            var randItem:Qustion=list.get(randIndex)

            quizViewModel.qustionBank.add(randItem)
            list.remove(list[randIndex])

        }
    }

    //===================== Next Quastion ===================================
    private fun updateQuestion() {

        previous.visibility=View.VISIBLE
        if (quizViewModel.currentIndex==5){
            quizViewModel.currentIndex=( quizViewModel.currentIndex - 6)


        }

        if (quizViewModel.currentQuestionNextstatus=="" ){
            bt_false.isClickable=true
            bt_true.isClickable=true
            bt_true.setBackgroundResource(R.drawable.trueshape)
            bt_false.setBackgroundResource(R.drawable.falseshape)

              quizViewModel.moveToNext()
            val questionTextResId = quizViewModel.currentQuestionText
            tv_qustion.setText(questionTextResId)
            tv_qustion.setBackgroundResource(R.drawable.qshape)
        }else{

            bt_false.isClickable=false
            bt_true.isClickable=false
            bt_true.setBackgroundResource(R.drawable.answerd)
            bt_false.setBackgroundResource(R.drawable.answerd)
            quizViewModel.moveToNext()

            tv_qustion.setText(quizViewModel.currentQuestionText)
            tv_qustion.setBackgroundResource(R.drawable.qshape)
        }
        }


    //===================== Privuse Quastion ===================================


    private fun privQ() {

        if (quizViewModel.currentIndex==0){
            quizViewModel.currentIndex=( quizViewModel.currentIndex + 6)


        }

        if (quizViewModel.currentQuestionprivstatus=="" ){


            bt_false.isClickable=true
            bt_true.isClickable=true
            bt_true.setBackgroundResource(R.drawable.trueshape)
            bt_false.setBackgroundResource(R.drawable.falseshape)
            if (quizViewModel.currentIndex==0){
                quizViewModel.currentIndex=(quizViewModel.currentIndex + 6)


            }
             quizViewModel.moveToPriv()
            val questionTextResId = quizViewModel.currentQuestionText
            tv_qustion.setText(questionTextResId)
            tv_qustion.setBackgroundResource(R.drawable.qshape)

        }else{

            bt_false.isClickable=false
            bt_true.isClickable=false
            bt_true.setBackgroundResource(R.drawable.answerd)
            bt_false.setBackgroundResource(R.drawable.answerd)


            quizViewModel.moveToPriv()
            val questionTextResId = quizViewModel.currentQuestionText
            tv_qustion.setText(questionTextResId)
            tv_qustion.setBackgroundResource(R.drawable.qshape)

        }



    }




    //===================== check answer function ====================================

    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.isCheater -> {


                quizViewModel.addtoStatus("1")

                Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show()
                tv_qustion.setBackgroundResource(R.drawable.qtrue)
                bt_true.setBackgroundResource(R.drawable.answerd)
                bt_false.setBackgroundResource(R.drawable.answerd)
                bt_false.isClickable=false
                bt_true.isClickable=false


            }



            userAnswer == correctAnswer -> {


                quizViewModel.addtoStatus("1")


                tv_qustion.setBackgroundResource(R.drawable.qtrue)
                bt_true.setBackgroundResource(R.drawable.answerd)
                bt_false.setBackgroundResource(R.drawable.answerd)
                bt_false.isClickable=false
                bt_true.isClickable=false

                Tanswer+=quizViewModel.currentQuestionDegree
                score.text=Tanswer.toString()


            }






            else -> {

                quizViewModel.addtoStatus("0")

                bt_false.isClickable=false
                bt_true.isClickable=false
                bt_true.setBackgroundResource(R.drawable.answerd)
                bt_false.setBackgroundResource(R.drawable.answerd)

                Toast.makeText(this, R.string.False, Toast.LENGTH_SHORT).show()

                tv_qustion.setBackgroundResource(R.drawable.qfalse)

            }


        }












    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

}
