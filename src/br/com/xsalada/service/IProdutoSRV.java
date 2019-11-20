package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.model.Produto;
import br.com.xsalada.model.ProdutoIngrediente;

public interface IProdutoSRV  extends IModelSRV<Produto>  {

	List<Produto> buscaPorEmpresa(Empresa empresa);
	
	public void salvar(Produto modelo, List<ProdutoIngrediente> listaProdIngr);
	
	public void atualizar(Produto modelo, List<ProdutoIngrediente> listaProdIngr);

	public void excluir(Produto p, List<ProdutoIngrediente> buscaPorProduto);

	List<Produto> filtragem(String nome, Integer tipo, Float preco1,
			Float preco2, List<Ingrediente> listaIngrediente);
	
	
}