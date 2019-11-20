package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;

public interface IIngredienteSRV  extends IModelSRV<Ingrediente>  {
	
	public List<Ingrediente> buscaPorEmpresa(Empresa emp);

	public List<Ingrediente> buscaPorDescricao(String string, Empresa empresa);

	List<Ingrediente> buscaPorDescricao(String string);
	
	
}
