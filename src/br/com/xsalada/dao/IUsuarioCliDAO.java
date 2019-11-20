package br.com.xsalada.dao;

import br.com.xsalada.model.UsuarioCli;


public interface IUsuarioCliDAO extends IModelDAO<UsuarioCli> {

	public UsuarioCli buscaPorLogin(String login,String senha);
	public UsuarioCli buscaPorCPF(String cnpj);

}