package br.com.xsalada.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IEmpresaDAO;
import br.com.xsalada.model.Empresa;

@Repository
public class EmpresaDAO extends ModelDAO<Empresa> implements IEmpresaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public Empresa buscaPorCNPJ(String cnpj) {
		try {
			return (Empresa) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " E Where E.cnpj= ?",
							new Object[] { cnpj }).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	
	

}
