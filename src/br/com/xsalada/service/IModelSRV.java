package br.com.xsalada.service;

import java.io.Serializable;
import java.util.List;

public interface IModelSRV<T> extends Serializable {
	
	public void salvar(T modelo);
	
	public void atualizar(T modelo);
	
	public void excluir(T modelo);
	
	public T buscaPorId(Integer id);

	public List<T> buscaTodos();

}
