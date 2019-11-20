package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.PedidoProduto;
import br.com.xsalada.model.PedidoProdutoAdicional;



public interface IPedidoProdutoAdicionalDAO extends IModelDAO<PedidoProdutoAdicional> {

	List<PedidoProdutoAdicional> buscaPorPedidoProduto(PedidoProduto pp);

	

}