package br.com.xsalada.service;

import br.com.xsalada.model.UsuarioCli;

public interface IUsuarioCliSRV  extends IModelSRV<UsuarioCli>  {
	
	public UsuarioCli buscaPorLogin(String login,String senha);
	public UsuarioCli buscaPorCPF(String cpf);
	
	
}