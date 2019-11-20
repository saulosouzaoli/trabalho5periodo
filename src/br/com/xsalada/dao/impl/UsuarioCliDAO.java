package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IUsuarioCliDAO;
import br.com.xsalada.model.UsuarioCli;

@Repository
public class UsuarioCliDAO extends ModelDAO<UsuarioCli> implements
		IUsuarioCliDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UsuarioCli buscaPorCPF(String cpf) {
		try {
			return (UsuarioCli) this.getHibernateTemplate().find(
					"from " + getTClass().getSimpleName()
							+ " UsuE join UsuE.empresa E  Where E.cpf= ?",
					new Object[] { cpf }).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UsuarioCli buscaPorLogin(String login, String senha) {
		try {
			List<UsuarioCli> resultado = this.getHibernateTemplate().find(
					"from " + getTClass().getSimpleName() + " E where E.login= ? and  E.senha= ?",
					new Object[] { login, senha });
			if (resultado.isEmpty())
				return null;
			else
				return resultado.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
