package br.com.xsalada.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IClienteDAO;
import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.model.Cliente;
import br.com.xsalada.service.IClienteSRV;

@Service
public class ClienteSRV extends ModelSRV<Cliente> implements IClienteSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IClienteDAO cidadeDAO;
	
	

	@Override
	public IModelDAO<Cliente> getModelDao() {
		return cidadeDAO;
	}
	
	@Override
	public Cliente buscaPorCPF(String cpf) {
		return cidadeDAO.buscaPorCPF(cpf);
	}


	
}
