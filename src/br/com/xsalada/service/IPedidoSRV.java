package br.com.xsalada.service;

import java.util.Date;
import java.util.List;

import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Pedido;

public interface IPedidoSRV extends IModelSRV<Pedido> {

	public List<Pedido> buscaPorCliente(Cliente cliente);

	public List<Pedido> buscaPorEmpresa(Empresa empresa);

	public List<Pedido> buscaPorEmpresa(Empresa empresa, Date ultimaDataVista);

	public List<Pedido> buscaHistoricoPorCliente(Cliente cliente);

	public List<Pedido> buscaAbertosPorCliente(Cliente cliente);

	List<Pedido> buscaHistoricoPorEmpresa(Empresa empresa);

	List<Pedido> buscaAbertosPorEmpresa(Empresa empresa);

}