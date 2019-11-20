package br.com.xsalada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IIngredienteDAO;
import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.service.IIngredienteSRV;

@Service
public class IngredienteSRV extends ModelSRV<Ingrediente> implements IIngredienteSRV {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IIngredienteDAO ingredienteDao;

	@Override
	public IModelDAO<Ingrediente> getModelDao() {
		return ingredienteDao;
	}
	
	@Override
	public List<Ingrediente> buscaPorEmpresa(Empresa empresa) {
		return ingredienteDao.buscaPorEmpresa(empresa);
	}
	
	@Override
	public List<Ingrediente> buscaPorDescricao(String string){
		return ingredienteDao.buscaPorDescricao(string);
	}

	@Override
	public List<Ingrediente> buscaPorDescricao(String string, Empresa empresa) {
		return ingredienteDao.buscaPorDescricao(string,empresa);
	}
	
	
	
}
