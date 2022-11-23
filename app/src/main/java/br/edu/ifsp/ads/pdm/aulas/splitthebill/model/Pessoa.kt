package br.edu.ifsp.ads.pdm.aulas.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pessoa(
    var id: Int,
    var nome: String,
    var valorPago: String,
    var valorReceber: String,
    var observacao: String,
): Parcelable