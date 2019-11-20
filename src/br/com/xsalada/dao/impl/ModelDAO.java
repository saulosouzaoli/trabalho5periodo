package br.com.xsalada.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.model.generico.Reflections;

@Repository
public abstract class ModelDAO<T> implements IModelDAO<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HibernateTemplate hibernateTemplate;
	private Class<T> t;

	protected Class<T> getTClass() {

		if (this.t == null) {
			this.t = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.t;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Autowired
	@Qualifier("sessionFactoryXSalada")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void salvar(T modelo) {
		org.hibernate.Session session = getHibernateTemplate()
				.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			// hibernateTemplate.save(modelo);
			session.save(modelo);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro ao salvar:"+e.getMessage());
			e.printStackTrace();
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void atualizar(T modelo) {
		org.hibernate.Session session = getHibernateTemplate()
				.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(modelo);
			session.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Erro ao atualizar:"+e.getMessage());
			try {				
				session.beginTransaction();
				session.merge(modelo);
				session.getTransaction().commit();
				System.out.println("\nErro de atualizar contornado");
			} catch (Exception e2) {
				throw e;
			}
		} finally {
			session.close();
		}

	}

	@Override
	public void excluir(T modelo) {
		// hibernateTemplate.delete(modelo);
		org.hibernate.Session session = getHibernateTemplate()
				.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			// hibernateTemplate.save(modelo);
			session.delete(modelo);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro ao excluir:"+e.getMessage());			
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public T buscaPorId(Integer id) {
		return (T) hibernateTemplate.get(getTClass(), id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscaTodos() {
		return (List<T>) hibernateTemplate.find("SELECT t from  "
				+ getTClass().getSimpleName() + " t ");
	}


}
