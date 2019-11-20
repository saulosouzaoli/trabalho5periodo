package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.PedidoProduto;

public interface IPedidoProdutoSRV  extends IModelSRV<PedidoProduto>  {

	List<PedidoProduto> buscaPorEmpresa(Empresa empresa);
	

	
	
}