package br.com.alura.orgs.database.dao

import androidx.room.*
import br.com.alura.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): List<Produto>

    @Insert
    fun adiciona(vararg produto: Produto)

    @Delete
    fun exclui(vararg produto: Produto)

    @Update
    fun edita(vararg produto: Produto)
}