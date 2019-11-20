package br.com.xsalada.service;

import java.util.List;

import br.com.xsalada.model.Cidade;
import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Endereco;

public interface IEnderecoSRV  extends IModelSRV<Endereco>  {

	public  List<Endereco> buscaPorEmpresa(Empresa empresa);

	public List<Endereco> buscaPorCliente(Cliente cliente);

	public void salvar(List<Endereco> listaEnderecos, Cidade cidade, Empresa empresa);

	public void salvar(List<Endereco> listaEnderecos, Cidade cidade, Cliente cliente);
	

	
	
}