package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.Produto;
import br.com.xsalada.model.ProdutoIngrediente;


public interface IProdutoIngredienteDAO extends IModelDAO<ProdutoIngrediente> {

	public List<ProdutoIngrediente> buscaPorProduto(Produto produto);

	

}