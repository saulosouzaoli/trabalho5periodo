package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IAdicionalDAO;
import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.model.Adicional;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.service.IAdicionalSRV;

@Service
public class AdicionalSRV extends ModelSRV<Adicional> implements IAdicionalSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IAdicionalDAO adicionalDAO;
	
	

	@Override
	public IModelDAO<Adicional> getModelDao() {
		return adicionalDAO;
	}



	@Override
	public List<Adicional> buscaPorEmpresa(Empresa e) {
		return adicionalDAO.buscaPorEmpresa(e);
	}
	
	

	
}
