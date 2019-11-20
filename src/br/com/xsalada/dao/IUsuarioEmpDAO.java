package br.com.xsalada.dao;

import br.com.xsalada.model.UsuarioEmp;


public interface IUsuarioEmpDAO extends IModelDAO<UsuarioEmp> {

	public UsuarioEmp buscaPorLogin(String login,String senha);
	public UsuarioEmp buscaPorCNPJ(String cnpj);

}