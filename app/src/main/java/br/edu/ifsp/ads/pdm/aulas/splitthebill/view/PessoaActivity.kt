package br.edu.ifsp.ads.pdm.aulas.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.ifsp.ads.pdm.aulas.splitthebill.databinding.ActivityPessoaBinding
import br.edu.ifsp.ads.pdm.aulas.splitthebill.model.Constant.EXTRA_PESSOA
import br.edu.ifsp.ads.pdm.aulas.splitthebill.model.Constant.VIEW_PESSOA
import br.edu.ifsp.ads.pdm.aulas.splitthebill.model.Pessoa
import kotlin.random.Random

class PessoaActivity : AppCompatActivity() {
    private val apb: ActivityPessoaBinding by lazy {
        ActivityPessoaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        val recebPessoa = intent.getParcelableExtra<Pessoa>(EXTRA_PESSOA)
        recebPessoa?.let{ _recebPessoa ->
            with(apb) {
                with(_recebPessoa) {
                    nomeEt.setText(nome)
                    valorPagoEt.setText(valorPago)
                    valorReceberEt.setText(valorReceber)
                    observacaoEt.setText(observacao)
                }
            }
        }
        val viewPerson = intent.getBooleanExtra(VIEW_PESSOA, false)
        if (viewPerson) {
            apb.nomeEt.isEnabled = false
            apb.valorPagoEt.isEnabled = false
            apb.valorReceberEt.isEnabled = false
            apb.observacaoEt.isEnabled = false
            apb.saveBt.visibility = View.GONE
        }

        apb.saveBt.setOnClickListener {
            val person = Pessoa(
                id = recebPessoa?.id?: Random(System.currentTimeMillis()).nextInt(),
                nome = apb.nomeEt.text.toString(),
                valorPago = apb.valorPagoEt.text.toString(),
                valorReceber = apb.valorReceberEt.text.toString(),
                observacao = apb.observacaoEt.text.toString(),
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PESSOA, person)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}