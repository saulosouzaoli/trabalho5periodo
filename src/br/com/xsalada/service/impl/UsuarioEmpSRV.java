package br.com.xsalada.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.dao.IUsuarioEmpDAO;
import br.com.xsalada.model.UsuarioEmp;
import br.com.xsalada.service.IUsuarioEmpSRV;

@Service
public class UsuarioEmpSRV extends ModelSRV<UsuarioEmp>     implements IUsuarioEmpSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IUsuarioEmpDAO usuarioEmpDAO;
	
	
	@Override
	public UsuarioEmp buscaPorCNPJ(String cnpj) {
		return usuarioEmpDAO.buscaPorCNPJ(cnpj);
	}
	@Override
	public UsuarioEmp buscaPorLogin(String login,String senha) {
		return usuarioEmpDAO.buscaPorLogin(login, senha);
	}
	@Override
	public IModelDAO<UsuarioEmp> getModelDao() {		
		return usuarioEmpDAO;
	}

}
