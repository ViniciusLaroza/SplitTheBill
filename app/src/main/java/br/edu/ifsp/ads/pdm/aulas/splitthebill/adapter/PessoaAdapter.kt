package br.edu.ifsp.ads.pdm.aulas.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.ads.pdm.aulas.splitthebill.R
import br.edu.ifsp.ads.pdm.aulas.splitthebill.model.Pessoa
import java.math.RoundingMode
import java.text.DecimalFormat

class PessoaAdapter(
    context: Context,
    private val pessoaList: MutableList<Pessoa>
) : ArrayAdapter<Pessoa>(context, R.layout.tile_pessoa, pessoaList) {
    private data class TileContactHolder(val nomeTv: TextView, val valorReceberTv: TextView, val valorPagoTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val pessoa = pessoaList[position]
        var pessoaTileView = convertView
        if (pessoaTileView == null) {
            // Inflo uma nova célula
            pessoaTileView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.tile_pessoa,
                    parent,
                    false
                )

            val tileContactHolder = TileContactHolder(
                pessoaTileView.findViewById(R.id.nomeTv),
                pessoaTileView.findViewById(R.id.valorReceberTv),
                pessoaTileView.findViewById(R.id.valorPagoTv),
            )
            pessoaTileView.tag = tileContactHolder
        }

        with(pessoaTileView?.tag as TileContactHolder) {
            nomeTv.text = "Nome: " + pessoa.nome
            valorPagoTv.text = "Valor Pago: " + pessoa.valorPago

            val valor = pessoa.valorReceber.toDouble()
            if(valor< 0){
                pessoa.valorReceber = (valor * -1).toString()
                valorReceberTv.text = "Deve receber: " + pessoa.valorReceber
            }
            else valorReceberTv.text = "Deve pagar: " + pessoa.valorReceber
        }

        return pessoaTileView
    }
}