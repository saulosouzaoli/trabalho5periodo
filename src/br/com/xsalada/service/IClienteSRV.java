package br.com.xsalada.service;

import br.com.xsalada.model.Cliente;

public interface IClienteSRV  extends IModelSRV<Cliente>  {

	public Cliente buscaPorCPF(String cpf);
	

	
	
}