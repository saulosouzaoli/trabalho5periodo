package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IAdicionalDAO;
import br.com.xsalada.model.Adicional;
import br.com.xsalada.model.Empresa;

@Repository
public class AdicionalDAO extends ModelDAO<Adicional> implements IAdicionalDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	@Override
	public List<Adicional> buscaPorEmpresa(Empresa empresa) {
		try {
			return (List<Adicional>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " A Where A.empresa= ?",
							new Object[] { empresa });
		} catch (Exception e) {
			return null;
		}
	}

}
