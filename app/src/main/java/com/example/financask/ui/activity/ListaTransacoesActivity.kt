package com.example.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.financask.R
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.ui.ResumoView
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumo(transacoes)
        configuraLista(transacoes)

        lista_transacoes_adiciona_receita.setOnClickListener {
            val  view: View = window.decorView

            val inflateDialog = LayoutInflater
                    .from(this)
                    .inflate(R.layout.form_transacao, view as ViewGroup, false)

            val ano = 2018
            val mes = 0
            val dia = 14

            val hoje = Calendar.getInstance()

            inflateDialog.form_transacao_data.setText(hoje.formataParaBrasileiro())
            inflateDialog.form_transacao_data.setOnClickListener {
                DatePickerDialog(this,
                        DatePickerDialog.OnDateSetListener { view, ano, mes, dia ->
                            val dataSelecionada = Calendar.getInstance()
                            dataSelecionada.set(ano, mes, dia)
                            inflateDialog.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                        }, ano, mes, dia)
                        .show()
            }

            val adapter = ArrayAdapter
                    .createFromResource(
                            this,
                            R.array.categorias_de_receita,
                            android.R.layout.simple_spinner_dropdown_item
                    )

            inflateDialog.form_transacao_categoria.adapter = adapter

            AlertDialog
                    .Builder(this)
                    .setTitle(R.string.adiciona_receita)
                    .setView(inflateDialog)
                    .setPositiveButton("Adicionar", null)
                    .setNegativeButton("Cancelar", null)
                    .show()
        }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)

        resumoView.atualiza()
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(
                Transacao(BigDecimal(20.5), "Almoço de final de semana", Tipo.DESPESA),
                Transacao(BigDecimal(20.5), "Comida", Tipo.DESPESA),
                Transacao(BigDecimal(100), "Economia", Tipo.RECEITA),
                Transacao(valor = BigDecimal(200.0), tipo = Tipo.DESPESA),
                Transacao(BigDecimal(500.0), "Prêmio", Tipo.RECEITA)
        )
    }
}