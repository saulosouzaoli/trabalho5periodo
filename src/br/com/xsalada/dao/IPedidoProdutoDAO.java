package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.PedidoProduto;


public interface IPedidoProdutoDAO extends IModelDAO<PedidoProduto> {

	List<PedidoProduto> buscaPorEmpresa(Empresa empresa);

	

}