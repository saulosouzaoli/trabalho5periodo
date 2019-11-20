package br.com.xsalada.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.service.IModelSRV;

@Service
public abstract class ModelSRV<T> implements IModelSRV<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract IModelDAO<T> getModelDao();

	@Override
	public void salvar(T modelo) {
		getModelDao().salvar(modelo);
	}

	@Override
	public void atualizar(T modelo) {
		getModelDao().atualizar(modelo);
	}

	@Override
	public void excluir(T modelo) {
		getModelDao().excluir(modelo);
	}

	@Override
	public T buscaPorId(Integer id) {
		return getModelDao().buscaPorId(id);

	}

	@Override
	public List<T> buscaTodos() {
		return getModelDao().buscaTodos();
	}

}
