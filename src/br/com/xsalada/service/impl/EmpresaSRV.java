package br.com.xsalada.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IEmpresaDAO;
import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.service.IEmpresaSRV;

@Service
public class EmpresaSRV extends ModelSRV<Empresa> implements IEmpresaSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IEmpresaDAO empresaDAO;
	
	
	
	@Override
	public Empresa buscaPorCNPJ(String cnpj) {
		return empresaDAO.buscaPorCNPJ(cnpj);
	}



	@Override
	public IModelDAO<Empresa> getModelDao() {
		return empresaDAO;
	}

	
}
