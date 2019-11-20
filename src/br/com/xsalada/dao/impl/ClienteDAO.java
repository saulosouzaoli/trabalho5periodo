package br.com.xsalada.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IClienteDAO;
import br.com.xsalada.model.Cliente;

@Repository
public class ClienteDAO extends ModelDAO<Cliente> implements IClienteDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Cliente buscaPorCPF(String cpf) {
		try {
			return (Cliente) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " C Where C.cpf= ?",
							new Object[] { cpf }).get(0);
		} catch (Exception e) {
			return null;
		}
	}

}
