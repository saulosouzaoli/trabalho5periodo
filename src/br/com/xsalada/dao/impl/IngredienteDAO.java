package br.com.xsalada.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IIngredienteDAO;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;

@Repository
public class IngredienteDAO extends ModelDAO<Ingrediente> implements
		IIngredienteDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingrediente> buscaPorEmpresa(Empresa empresa) {
		try {
			return (List<Ingrediente>) this
					.getHibernateTemplate()
					.find("from " + getTClass().getSimpleName()
							+ " I Where I.empresa= ?", new Object[] { empresa });
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingrediente> buscaPorDescricao(String string, Empresa empresa) {
		try {
			return (List<Ingrediente>) this
					.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " I where  I.nome like ? and ( I.empresa = ? or I.empresa is null )",
							new Object[] { string, empresa });
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingrediente> buscaPorDescricao(String string) {
		try {
			return (List<Ingrediente>) this
					.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " I where  I.nome like ? and  I.empresa is null ",
							new Object[] {string});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
