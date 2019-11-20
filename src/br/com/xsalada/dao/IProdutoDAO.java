package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.model.Produto;


public interface IProdutoDAO extends IModelDAO<Produto> {

	List<Produto> buscaPorEmpresa(Empresa empresa);

	List<Produto> filtragem(String nome, Integer tipo, Float preco1,
			Float preco2, List<Ingrediente> listaIngrediente);

	

}