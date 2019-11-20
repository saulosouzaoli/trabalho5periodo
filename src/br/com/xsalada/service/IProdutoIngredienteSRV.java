package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.Produto;
import br.com.xsalada.model.ProdutoIngrediente;

public interface IProdutoIngredienteSRV  extends IModelSRV<ProdutoIngrediente>  {

	public List<ProdutoIngrediente> buscaPorProduto(Produto produto);

	public void persistir(List<ProdutoIngrediente> listaProdIngr,
			Produto produto);
	

	
	
}