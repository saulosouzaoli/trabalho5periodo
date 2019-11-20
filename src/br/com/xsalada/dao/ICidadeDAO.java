package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.Cidade;


public interface ICidadeDAO extends IModelDAO<Cidade> {

	public List<Cidade> buscaPorUf(String uf);

}