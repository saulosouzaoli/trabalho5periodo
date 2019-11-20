package br.com.xsalada.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

public interface IModelDAO<T> extends Serializable{
	
	public void salvar(T modelo);
	
	public void atualizar(T modelo);
	
	public void excluir(T modelo);
	
	public T buscaPorId(Integer id);

	public List<T> buscaTodos();
		
	public HibernateTemplate getHibernateTemplate();

	

}
