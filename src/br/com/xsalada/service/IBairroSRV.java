package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.Bairro;
import br.com.xsalada.model.Cidade;

public interface IBairroSRV  extends IModelSRV<Bairro>  {

	public List<Bairro>  buscaPorDescricao(String query, Cidade cidade);

	public Bairro salvar(Bairro bairro, Cidade cidade);
	

	
	
}