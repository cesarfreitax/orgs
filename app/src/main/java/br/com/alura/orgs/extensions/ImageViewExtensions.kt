package br.com.alura.orgs.extensions

import android.widget.ImageView
import br.com.alura.orgs.R
import coil.ImageLoader
import coil.load

fun ImageView.tentaCarregarImagem(url: String? = null, imageLoader: ImageLoader? = null){
    load(url) {
        placeholder(R.drawable.noimage)
        fallback(R.drawable.erro)
        error(R.drawable.erro)
    }

}