package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.Cidade;

public interface ICidadeSRV  extends IModelSRV<Cidade>  {

	public List<Cidade> buscaPorUf(String uf);
	

	
	
}