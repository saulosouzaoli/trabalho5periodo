package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IProdutoIngredienteDAO;
import br.com.xsalada.model.Produto;
import br.com.xsalada.model.ProdutoIngrediente;

@Repository
public class ProdutoIngredienteDAO extends ModelDAO<ProdutoIngrediente> implements IProdutoIngredienteDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	@Override
	public List<ProdutoIngrediente> buscaPorProduto(Produto produto){
		try {
			return (List<ProdutoIngrediente>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " PI where PI.produto like ?", produto );
		} catch (Exception e) {
			return null;
		}
	}

}
