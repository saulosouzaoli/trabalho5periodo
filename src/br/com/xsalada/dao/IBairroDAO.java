package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.Bairro;
import br.com.xsalada.model.Cidade;


public interface IBairroDAO extends IModelDAO<Bairro> {

	public List<Bairro> buscaPorDescricao(String query, Cidade cidade);




}