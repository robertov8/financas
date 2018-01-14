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

class ResumoView(private val context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        val color = ContextCompat.getColor(context, R.color.receita)

        view.resumo_card_receita.text = totalReceita.formataParaBrasileiro()
        view.resumo_card_receita.setTextColor(color)
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()
        val color = ContextCompat.getColor(context, R.color.despesa)

        view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
        view.resumo_card_despesa.setTextColor(color)
    }

    fun adicionaTotal() {
        val total = resumo.total()

        val color = if (total >= BigDecimal.ZERO) {
            ContextCompat.getColor(context, R.color.receita)
        } else {
            ContextCompat.getColor(context, R.color.despesa)
        }

        view.resumo_card_total.text = total.formataParaBrasileiro()
        view.resumo_card_total.setTextColor(color)
    }
}