package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IPedidoProdutoAdicionalDAO;
import br.com.xsalada.model.PedidoProduto;
import br.com.xsalada.model.PedidoProdutoAdicional;

@Repository
public class PedidoProdutoAdicionalDAO extends ModelDAO<PedidoProdutoAdicional> implements IPedidoProdutoAdicionalDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoProdutoAdicional> buscaPorPedidoProduto(PedidoProduto pp) {
		try {
			return (List<PedidoProdutoAdicional>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " PPA Where PPA.pedidoProduto= ?",pp );
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
