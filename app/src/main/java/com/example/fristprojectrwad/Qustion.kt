package com.example.fristprojectrwad

import androidx.annotation.StringRes

data class Qustion(@StringResval val restTextid:Int, val answer:Boolean, var status:String,var level:Int,val Degree:Int,var isCheater:Boolean){
    annotation class StringResval



}


