package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;


public interface IIngredienteDAO extends IModelDAO<Ingrediente> {

	public List<Ingrediente> buscaPorEmpresa(Empresa empresa);

	public List<Ingrediente> buscaPorDescricao(String string, Empresa empresa);

	public List<Ingrediente> buscaPorDescricao(String string);

}