package br.com.xsalada.service;

import br.com.xsalada.model.UsuarioEmp;

public interface IUsuarioEmpSRV  extends IModelSRV<UsuarioEmp>  {
	
	public UsuarioEmp buscaPorLogin(String login,String senha);
	public UsuarioEmp buscaPorCNPJ(String cnpj);

}
