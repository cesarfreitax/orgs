package br.com.alura.orgs.extensions

import android.widget.ImageView
import br.com.alura.orgs.R
import coil.load

fun ImageView.tentaCarregarImagem(url: String? = null, fallback: Int = R.drawable.imagem_padrao){
    load(url) {
        placeholder(R.drawable.noimage)
        fallback(fallback)
        error(R.drawable.erro)
    }

}