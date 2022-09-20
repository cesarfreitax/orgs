package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityDetalheProdutoBinding
import br.com.alura.orgs.extensions.formataParaMoedaBrasileira
import br.com.alura.orgs.extensions.tentaCarregarImagem
import br.com.alura.orgs.model.Produto

class DetalheProdutoActivity : AppCompatActivity() {


    private lateinit var produto: Produto
    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(::produto.isInitialized) {
            val db = AppDatabase.instancia(this)
            val produtoDao = db.produtoDao()
            when (item.itemId) {
                R.id.menu_detalhes_produto_editar -> {
                   Intent(this, FormularioProdutoActivity::class.java).apply {
                       putExtra(CHAVE_PRODUTO, produto)
                       startActivity(this)
                   }
                }
                R.id.menu_detalhes_produto_excluir -> {
                    produtoDao.exclui(produto)
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            produto = produtoCarregado
            preencheCampos(produtoCarregado)
        } ?: finish()
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalheProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            activityDetalheProdutoValor.text = produtoCarregado.valor.formataParaMoedaBrasileira()
            activityDetalheProdutoTitulo.text = produtoCarregado.nome
            activityDetalheProdutoDesc.text = produtoCarregado.descricao
        }
    }


}