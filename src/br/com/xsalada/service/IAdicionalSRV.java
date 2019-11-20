package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.Adicional;
import br.com.xsalada.model.Empresa;

public interface IAdicionalSRV  extends IModelSRV<Adicional>  {

	List<Adicional> buscaPorEmpresa(Empresa e);
	

	
	
}