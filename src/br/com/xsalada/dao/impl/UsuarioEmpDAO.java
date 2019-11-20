package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IUsuarioEmpDAO;
import br.com.xsalada.model.UsuarioEmp;

@Repository
public class UsuarioEmpDAO extends ModelDAO<UsuarioEmp> implements
		IUsuarioEmpDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UsuarioEmp buscaPorCNPJ(String cnpj) {
		try {
			return (UsuarioEmp) this.getHibernateTemplate().find(
					"from " + getTClass().getSimpleName()
							+ " UsuE join UsuE.empresa E  Where E.cnpj= ?",
					new Object[] { cnpj }).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UsuarioEmp buscaPorLogin(String login, String senha) {
		try {
			List<UsuarioEmp> resultado = this.getHibernateTemplate().find(
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
