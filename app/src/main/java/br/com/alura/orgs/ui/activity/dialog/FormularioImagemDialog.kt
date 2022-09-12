package br.com.alura.orgs.ui.activity.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.ActivityFormularioImagemBinding
import br.com.alura.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(quandoImagemCarregada: (imagem: String) -> Unit){
        val binding = ActivityFormularioImagemBinding.inflate(LayoutInflater.from(context))
        binding.activityFormularioImagemBotao.setOnClickListener{
            val url = binding.activityFormularioUrl.text.toString()
            binding.activityFormularioImagemImageview.tentaCarregarImagem(url)
        }
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _,_ ->
                val url = binding.activityFormularioUrl.text.toString()
                quandoImagemCarregada(url)
            }
            .setNegativeButton("Cancelar") { _,_ ->

            }
            .show()
    }
}