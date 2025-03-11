package com.example.testmobilka.Presentation.Screen.SignIn

import android.text.TextUtils

fun String.isAgeValid():Boolean{
    return !TextUtils.isEmpty(this) && this.all { char -> char.isDigit()}
}