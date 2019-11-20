package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.ICidadeDAO;
import br.com.xsalada.model.Cidade;

@Repository
public class CidadeDAO extends ModelDAO<Cidade> implements ICidadeDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Cidade> buscaPorUf(String uf){
		try {
			return (List<Cidade>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " C where C.uf= ?",uf );
		} catch (Exception e) {
			return null;
		}
	}

}
