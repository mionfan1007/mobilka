package com.example.testmobilka.Presentation.Screen.SignIn
import android.text.TextUtils

fun String.isPhoneValid():Boolean{
    return !TextUtils.isEmpty(this) && android.util.Patterns.PHONE.matcher(this).matches()
}