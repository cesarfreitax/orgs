package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityDetalheProdutoBinding
import br.com.alura.orgs.extensions.formataParaMoedaBrasileira
import br.com.alura.orgs.extensions.tentaCarregarImagem
import br.com.alura.orgs.model.Produto

class DetalheProdutoActivity : AppCompatActivity() {


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
        when(item.itemId){
            R.id.menu_detalhes_produto_editar -> {
                Log.i("DetalheProdutoActivity", "onOptionsItemSelected: Editar")
            }
            R.id.menu_detalhes_produto_excluir -> {
                Log.i("DetalheProdutoActivity", "onOptionsItemSelected: Excluir")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
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