package com.example.testmobilka.Domain.Valids
import android.text.TextUtils

fun String.isPhoneValid():Boolean{
    return !TextUtils.isEmpty(this) && android.util.Patterns.PHONE.matcher(this).matches()
}