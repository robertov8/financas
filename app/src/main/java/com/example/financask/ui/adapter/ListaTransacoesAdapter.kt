package com.example.financask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.financask.R
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.extension.limitaEmAte
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*


class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val context: Context) : BaseAdapter() {

    private val limiteDaCategoria = 12

    override fun getView(posicao: Int, p1: View?, parrent: ViewGroup?): View {
        val viewCreate = LayoutInflater.from(context).inflate(R.layout.transacao_item, parrent, false)

        val transacao = transacoes[posicao]

        adicionaValor(transacao, viewCreate)
        adicionaIcone(transacao, viewCreate)
        adicionaCategoria(viewCreate, transacao)
        adicionaData(viewCreate, transacao)

        return viewCreate
    }

    private fun adicionaValor(transacao: Transacao, viewCreate: View) {
        var cor: Int = corTipo(transacao.tipo)

        viewCreate.transacao_valor.setTextColor(cor)
        viewCreate.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corTipo(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            ContextCompat.getColor(context, R.color.receita)
        } else {
            ContextCompat.getColor(context, R.color.despesa)
        }
    }

    private fun adicionaIcone(transacao: Transacao, viewCreate: View) {
        var icone: Int = iconeTipo(transacao.tipo)
        viewCreate.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconeTipo(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            R.drawable.icone_transacao_item_receita
        } else {
            R.drawable.icone_transacao_item_despesa
        }
    }

    private fun adicionaCategoria(viewCreate: View, transacao: Transacao) {
        viewCreate.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaData(viewCreate: View, transacao: Transacao) {
        viewCreate.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    override fun getItem(p0: Int): Transacao {
        return transacoes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}