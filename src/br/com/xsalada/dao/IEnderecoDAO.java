package br.com.xsalada.dao;

import java.util.List;

import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Endereco;


public interface IEnderecoDAO extends IModelDAO<Endereco> {

	public List<Endereco> buscaPorEmpresa(Empresa empresa);

	public List<Endereco> buscaPorCliente(Cliente cliente);

	


}