package com.example.fristprojectrwad

import androidx.lifecycle.ViewModel
private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
    var currentIndex = 0

   val  qustionBank= mutableListOf<Qustion>(

    )

   val  easyqustionBank= listOf(
        Qustion(R.string.fristQ,true,"",1,2,false),
        Qustion(R.string.q2,true,"",1,2,false)  ,
        Qustion(R.string.q3,true,"",1,2,false),
        Qustion(R.string.q4,false,"",1,2,false),
       Qustion(R.string.q5,true,"",1,2,false),
       Qustion(R.string.q6,false,"",1,2,false)
    )

  val  midqustionBank= listOf(

        Qustion(R.string.mid1,true,"",2,4,false),
        Qustion(R.string.mid2,true,"",2,4,false),
        Qustion(R.string.mid3,true,"",2,4,false),
        Qustion(R.string.mid4,false,"",2,4,false),
      Qustion(R.string.mid5,true,"",2,4,false),
      Qustion(R.string.mid6,false,"",2,4,false)


    )
   val  difqustionBank= listOf(

        Qustion(R.string.dif1,true,"",3,6,false),
        Qustion(R.string.dif2,true,"",3,6,false)  ,
        Qustion(R.string.dif3,true,"",3,6,false),
        Qustion(R.string.dif4,false,"",3,6,false),
       Qustion(R.string.dif5,true,"",3,6,false),
       Qustion(R.string.dif6,false,"",3,6,false)



    )
    val currentQuestionAnswer: Boolean
        get() = qustionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = qustionBank[currentIndex].restTextid
    val currentFrestQuestionText: Int
        get() = qustionBank[0].restTextid
    val  currentQuestionstatus:String
       get()=qustionBank[currentIndex].status
    val  currentQuestionNextstatus:String
        get()=qustionBank[currentIndex+1].status
    val  currentQuestionprivstatus:String
        get()=qustionBank[currentIndex-1].status
    val  currentQuestionDegree:Int
        get()=qustionBank[currentIndex].Degree

    var isCheater:Boolean
        get()=qustionBank[currentIndex].isCheater
        set(value) {

            qustionBank[currentIndex].isCheater=true
        }




    fun  random(){

        var random= (1 until easyqustionBank.size).random()
        var a=easyqustionBank[random]

    }

    fun addtoStatus( s:String){

        qustionBank[currentIndex].status="1"
    }

    fun moveToNext() {

        currentIndex = (currentIndex + 1) % qustionBank.size
    }

    fun moveToPriv() {

        currentIndex = (currentIndex - 1) % qustionBank.size
    }






}