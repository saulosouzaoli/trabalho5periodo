package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.ICidadeDAO;
import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.model.Cidade;
import br.com.xsalada.service.ICidadeSRV;

@Service
public class CidadeSRV extends ModelSRV<Cidade> implements ICidadeSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ICidadeDAO cidadeDAO;
	
	

	@Override
	public IModelDAO<Cidade> getModelDao() {
		return cidadeDAO;
	}
	
	@Override
	public List<Cidade> buscaPorUf(String uf){
		return cidadeDAO.buscaPorUf(uf);
	}

	
}
