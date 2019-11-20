package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.dao.IPedidoProdutoDAO;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.PedidoProduto;
import br.com.xsalada.service.IPedidoProdutoSRV;

@Service
public class PedidoProdutoSRV extends ModelSRV<PedidoProduto> implements IPedidoProdutoSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IPedidoProdutoDAO pedidoProdutoDAO;
	
	

	@Override
	public IModelDAO<PedidoProduto> getModelDao() {
		return pedidoProdutoDAO;
	}



	@Override
	public List<PedidoProduto> buscaPorEmpresa(Empresa empresa) {
		
		return pedidoProdutoDAO.buscaPorEmpresa(empresa);
	}

	
}
