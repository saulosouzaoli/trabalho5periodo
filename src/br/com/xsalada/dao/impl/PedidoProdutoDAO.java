package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IPedidoProdutoDAO;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.PedidoProduto;

@Repository
public class PedidoProdutoDAO extends ModelDAO<PedidoProduto> implements IPedidoProdutoDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoProduto> buscaPorEmpresa(Empresa empresa) {
		try {
			return (List<PedidoProduto>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " PP Where PP.pedido.empresa= ? and PP.pedido.dataFinalizado is null",
							new Object[] { empresa });
		} catch (Exception e) {
			return null;
		}
	}
	
}
