package br.com.xsalada.dao;

import br.com.xsalada.model.Empresa;


public interface IEmpresaDAO extends IModelDAO<Empresa> {

	public Empresa buscaPorCNPJ(String cnpj);


}