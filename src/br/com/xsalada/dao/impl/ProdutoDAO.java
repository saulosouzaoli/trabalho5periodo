package br.com.xsalada.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IProdutoDAO;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.model.Produto;

@Repository
public class ProdutoDAO extends ModelDAO<Produto> implements IProdutoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscaPorEmpresa(Empresa empresa) {
		try {
			return (List<Produto>) this.getHibernateTemplate().find(
					"from " + getTClass().getSimpleName()
							+ " P where P.empresa like ?", empresa);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> filtragem(String nome, Integer tipo, Float preco1,
			Float preco2, List<Ingrediente> listaIngrediente) {
		StringBuilder birl = new StringBuilder("select p from "
				+ getTClass().getSimpleName() + " p");

		if (listaIngrediente != null && !listaIngrediente.isEmpty())
			birl.append(" inner join p.produtoIngredientes as pi ");
		birl.append(" where 1=1");

		if (nome != null && !nome.isEmpty())
			birl.append(" and p.nome like :nome");
		if (tipo != null && tipo != 0)
			birl.append(" and p.tipo = :tipo");
		if (preco1 != null)
			birl.append(" and p.preco >= :preco1");
		if (preco2 != null && preco2 !=0)
			birl.append(" and p.preco <= :preco2");
		if (listaIngrediente != null && !listaIngrediente.isEmpty())
			birl.append(" and pi.ingrediente in (:ingrediente)");

		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Query qry = session.createQuery(birl.toString());

		if (nome != null && !nome.isEmpty())
			qry.setParameter("nome", nome);
		if (tipo != null && tipo != 0)
			qry.setParameter("tipo", tipo);
		if (preco1 != null)
			qry.setParameter("preco1", preco1);
		if (preco2 != null && preco2 != 0)
			qry.setParameter("preco2", preco2);
		if (listaIngrediente != null && !listaIngrediente.isEmpty())
			qry.setParameterList("ingrediente", listaIngrediente);

		
		return qry.list();
		//
		// try {
		// return (List<Produto>) this.getHibernateTemplate()
		// .find("from "
		// + getTClass().getSimpleName()
		// + " P where P.empresa like ?", empresa );
		// } catch (Exception e) {
		// return null;
		// }
	}
}
