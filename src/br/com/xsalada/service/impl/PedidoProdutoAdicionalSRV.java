package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.dao.IPedidoProdutoAdicionalDAO;
import br.com.xsalada.model.PedidoProduto;
import br.com.xsalada.model.PedidoProdutoAdicional;
import br.com.xsalada.service.IPedidoProdutoAdicionalSRV;

@Service
public class PedidoProdutoAdicionalSRV extends ModelSRV<PedidoProdutoAdicional>
		implements IPedidoProdutoAdicionalSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IPedidoProdutoAdicionalDAO pedidoProdutoAdicionalDAO;

	@Override
	public IModelDAO<PedidoProdutoAdicional> getModelDao() {
		return pedidoProdutoAdicionalDAO;
	}

	@Override
	public List<PedidoProdutoAdicional> buscaPorPedidoProduto(PedidoProduto pp) {
		return pedidoProdutoAdicionalDAO.buscaPorPedidoProduto(pp);
	}

}
