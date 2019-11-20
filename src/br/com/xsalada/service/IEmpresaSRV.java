package br.com.xsalada.service;

import br.com.xsalada.model.Empresa;

public interface IEmpresaSRV  extends IModelSRV<Empresa>  {
	
	public Empresa buscaPorCNPJ(String cnpj);
}
