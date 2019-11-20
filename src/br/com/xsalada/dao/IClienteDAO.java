package br.com.xsalada.dao;

import br.com.xsalada.model.Cliente;


public interface IClienteDAO extends IModelDAO<Cliente> {

	
	public Cliente buscaPorCPF(String cpf);

	


}