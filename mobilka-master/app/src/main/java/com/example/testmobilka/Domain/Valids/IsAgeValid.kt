package com.example.testmobilka.Domain.Valids

import android.text.TextUtils

fun String.isAgeValid():Boolean{
    return !TextUtils.isEmpty(this) && this.all { char -> char.isDigit()}
}