package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IBairroDAO;
import br.com.xsalada.model.Bairro;
import br.com.xsalada.model.Cidade;

@Repository
public class BairroDAO extends ModelDAO<Bairro> implements IBairroDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bairro> buscaPorDescricao(String query, Cidade cidade){
		try {
			return (List<Bairro>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " B where B.cidade= ? and descricao like ?",
							new Object[] { cidade, query });
		} catch (Exception e) {
			return null;
		}
	}
}
