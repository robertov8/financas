package com.example.financask.extension

fun String.limitaEmAte(quantidade: Int) : String {
    if (this.length > quantidade) {
        val startIndex = 0
        return "${this.substring(startIndex, quantidade)}..."
    }
    return this
}