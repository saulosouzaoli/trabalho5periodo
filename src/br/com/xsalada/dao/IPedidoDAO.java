package br.com.xsalada.dao;

import java.util.Date;
import java.util.List;

import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Pedido;


public interface IPedidoDAO extends IModelDAO<Pedido> {

	public List<Pedido> buscaPorEmpresa(Empresa empresa);

	public List<Pedido> buscaPorCliente(Cliente cliente);

	public List<Pedido> buscaPorEmpresa(Empresa empresa, Date dataPedido);

	public List<Pedido> buscaHistoricoPorCliente(Cliente cliente);

	public List<Pedido> buscaAbertosPorCliente(Cliente cliente);

	List<Pedido> buscaAbertosPorEmpresa(Empresa empresa);

	List<Pedido> buscaHistoricoPorEmpresa(Empresa empresa);


}