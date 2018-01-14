package com.example.financask.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somaTipo(Tipo.RECEITA)
    val despesa get() = somaTipo(Tipo.DESPESA)
    val total get() : BigDecimal = receita.subtract(despesa)

    private fun somaTipo(tipo: Tipo): BigDecimal {
        val somaDeTipo = transacoes
                .filter { it.tipo == tipo }
                .sumByDouble { it.valor.toDouble() }

        return BigDecimal(somaDeTipo)
    }
}