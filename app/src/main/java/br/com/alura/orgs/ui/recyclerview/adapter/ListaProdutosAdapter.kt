package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.*
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.extensions.formataParaMoedaBrasileira
import br.com.alura.orgs.extensions.tentaCarregarImagem
import br.com.alura.orgs.model.Produto


class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    var quandoClicaNoItem: (produto: Produto) -> Unit = {},
    var quandoClicaEmEditar: (produto: Produto) -> Unit = {},
    var quandoClicaEmExcluir: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {

        private lateinit var produto: Produto

        init {

            itemView.setOnClickListener {
                if(::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }

            itemView.setOnLongClickListener {
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
                    setOnMenuItemClickListener(this@ViewHolder)
                }.show()
                true
            }

        }



        fun showPopup(v: View){
            val popupMenu = PopupMenu(context, v)
            val menuInflater = popupMenu.menuInflater
            menuInflater.inflate(R.menu.menu_detalhes_produto, popupMenu.menu)

        }

        fun vincula(produto: Produto) {
            this.produto = produto
            val nome = binding.activityFormularioProdutoNome
            nome.text = produto.nome
            val descricao = binding.activityFormularioProdutoDescricao
            descricao.text = produto.descricao
            val valor = binding.activityFormularioProdutoValor
            val valorEmMoeda: String = produto.valor.formataParaMoedaBrasileira()
            valor.text = valorEmMoeda

            val visibilidade = if (produto.imagem != null){
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.produtoItemImagem.visibility = visibilidade

            binding.produtoItemImagem.tentaCarregarImagem(produto.imagem)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when(it.itemId){
                    R.id.menu_detalhes_produto_editar -> {
                        quandoClicaEmEditar(produto)
                    }
                    R.id.menu_detalhes_produto_excluir -> {
                        quandoClicaEmExcluir(produto)
                    }
                }
            }
            return true
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
