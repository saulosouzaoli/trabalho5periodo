package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IEnderecoDAO;
import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.model.Cidade;
import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Endereco;
import br.com.xsalada.service.IBairroSRV;
import br.com.xsalada.service.IEnderecoSRV;

@Service
public class EnderecoSRV extends ModelSRV<Endereco> implements IEnderecoSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IEnderecoDAO enderecoDAO;
	@Autowired
	private IBairroSRV bairroSRV;
	
	

	@Override
	public IModelDAO<Endereco> getModelDao() {
		return enderecoDAO;
	}
	@Override
	public List<Endereco> buscaPorEmpresa(Empresa empresa){
		return enderecoDAO.buscaPorEmpresa(empresa);
	}
	@Override
	public List<Endereco> buscaPorCliente(Cliente cliente) {
		return enderecoDAO.buscaPorCliente(cliente);
	}
	@Override
	public void salvar(List<Endereco> listaEnderecos,Cidade cidade, Empresa empresa) {
		for(Endereco end:listaEnderecos){
			if(end.getBairro().getId()==null){
				end.setBairro(bairroSRV.salvar(end.getBairro(),cidade));
			}
			end.setEmpresa(empresa);
			enderecoDAO.salvar(end);
		}		
	}
	@Override
	public void salvar(List<Endereco> listaEnderecos,Cidade cidade, Cliente cliente) {
		for(Endereco end:listaEnderecos){
			if(end.getBairro().getId()==null){
				end.setBairro(bairroSRV.salvar(end.getBairro(),cidade));
			}
			end.setCliente(cliente);
			enderecoDAO.salvar(end);
		}
	}
	
}
