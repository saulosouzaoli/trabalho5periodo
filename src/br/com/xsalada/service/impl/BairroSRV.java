package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IBairroDAO;
import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.model.Bairro;
import br.com.xsalada.model.Cidade;
import br.com.xsalada.service.IBairroSRV;

@Service
public class BairroSRV extends ModelSRV<Bairro> implements IBairroSRV,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IBairroDAO bairroDAO;

	@Override
	public IModelDAO<Bairro> getModelDao() {
		return bairroDAO;
	}

	@Override
	public List<Bairro> buscaPorDescricao(String query, Cidade cidade) {
		return bairroDAO.buscaPorDescricao(query, cidade);
	}

	@Override
	public Bairro salvar(Bairro bairro, Cidade cidade) {
		List<Bairro> b =  bairroDAO.buscaPorDescricao(bairro.getDescricao(), cidade);
		if(b!=null && !b.isEmpty())
			return b.get(0);
		else{
			bairro.setCidade(cidade);
		 salvar(bairro);
		 return bairro;
		}
	}

}
