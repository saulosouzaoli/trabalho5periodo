package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.Adicional;
import br.com.xsalada.model.Empresa;


public interface IAdicionalDAO extends IModelDAO<Adicional> {

	public List<Adicional> buscaPorEmpresa(Empresa empresa);

	


}