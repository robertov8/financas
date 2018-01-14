package com.example.financask.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

fun String.limitaEmAte(quantidade: Int) : String {
    if (this.length > quantidade) {
        val startIndex = 0
        return "${this.substring(startIndex, quantidade)}..."
    }
    return this
}

fun String.converteParaCalendar() : Calendar {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida: Date = formatoBrasileiro.parse(this)
    val dataCalendar = Calendar.getInstance()
    dataCalendar.time = dataConvertida
    return dataCalendar
}