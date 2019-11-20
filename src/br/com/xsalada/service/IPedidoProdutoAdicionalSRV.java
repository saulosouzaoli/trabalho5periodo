package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.PedidoProduto;
import br.com.xsalada.model.PedidoProdutoAdicional;

public interface IPedidoProdutoAdicionalSRV extends IModelSRV<PedidoProdutoAdicional> {

	public List<PedidoProdutoAdicional> buscaPorPedidoProduto(PedidoProduto pp);

}