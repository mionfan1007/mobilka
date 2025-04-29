package com.example.testmobilka.Domain.Valids

fun String.isPasswordValid():Boolean {

    if (this.length < 6) return false
    if (this.filter { it.isDigit() }.firstOrNull() == null) return false
    if (this.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
    if (this.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
    if (this.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

    return true
}