package com.example.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.financask.R
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.model.Resumo
import com.example.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()

        with(view.resumo_card_receita) {
            text = totalReceita.formataParaBrasileiro()
            setTextColor(corReceita)
        }
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()

        with(view.resumo_card_despesa) {
            text = totalDespesa.formataParaBrasileiro()
            setTextColor(corDespesa)
        }
    }

    fun adicionaTotal() {
        val total = resumo.total()
        val color = corTotal(total)

        with(view.resumo_card_total) {
            text = total.formataParaBrasileiro()
            setTextColor(color)
        }
    }

    private fun corTotal(total: BigDecimal): Int {
        return if (total >= BigDecimal.ZERO) {
            corReceita
        } else {
            corDespesa
        }
    }
}