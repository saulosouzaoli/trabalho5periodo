package br.com.xsalada.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.dao.IUsuarioCliDAO;
import br.com.xsalada.model.UsuarioCli;
import br.com.xsalada.service.IUsuarioCliSRV;

@Service
public class UsuarioCliSRV extends ModelSRV<UsuarioCli>   implements IUsuarioCliSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IUsuarioCliDAO usuarioCliDAO;
	
	@Override
	public UsuarioCli buscaPorLogin(String login,String senha) {
		return usuarioCliDAO.buscaPorLogin(login, senha);
	}

	@Override
	public UsuarioCli buscaPorCPF(String cpf) {
		return usuarioCliDAO.buscaPorCPF(cpf);
	}

	@Override
	public IModelDAO<UsuarioCli> getModelDao() {		
		return usuarioCliDAO;
	}

}
