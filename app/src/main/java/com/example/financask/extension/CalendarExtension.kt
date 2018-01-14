package com.example.financask.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro() : String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(this.time)
}