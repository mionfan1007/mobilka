package com.example.testmobilka.Domain.Valids

import android.text.TextUtils

fun String.isEmailValid():Boolean{
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}