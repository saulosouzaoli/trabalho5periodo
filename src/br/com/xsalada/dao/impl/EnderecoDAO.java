package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IEnderecoDAO;
import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Endereco;

@Repository
public class EnderecoDAO extends ModelDAO<Endereco> implements IEnderecoDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> buscaPorEmpresa(Empresa empresa) {
		try {
			return ( List<Endereco>) this.getHibernateTemplate().find(
					"from " + getTClass().getSimpleName()
							+ " E   where E.empresa= ?",
					new Object[] { empresa });
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> buscaPorCliente(Cliente cliente) {
		try {
			return ( List<Endereco>) this.getHibernateTemplate().find(
					"from " + getTClass().getSimpleName()
							+ " E   where E.cliente= ?",
					new Object[] { cliente });
		} catch (Exception e) {
			return null;
		}
	}


}
