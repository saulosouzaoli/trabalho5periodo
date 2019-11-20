package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.dao.IPedidoDAO;
import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Pedido;
import br.com.xsalada.service.IPedidoSRV;

@Service
public class PedidoSRV extends ModelSRV<Pedido> implements IPedidoSRV,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IPedidoDAO pedidoDAO;

	@Override
	public IModelDAO<Pedido> getModelDao() {
		return pedidoDAO;
	}

	@Override
	public List<Pedido> buscaPorCliente(Cliente cliente) {
		return pedidoDAO.buscaPorCliente(cliente);
	}

	@Override
	public List<Pedido> buscaPorEmpresa(Empresa empresa) {
		return pedidoDAO.buscaPorEmpresa(empresa);
	}

	@Override
	public List<Pedido> buscaPorEmpresa(Empresa empresa, Date dataPedido) {
		return pedidoDAO.buscaPorEmpresa(empresa, dataPedido);
	}

	@Override
	public List<Pedido> buscaHistoricoPorCliente(Cliente cliente) {
		return pedidoDAO.buscaHistoricoPorCliente(cliente);
	}

	@Override
	public List<Pedido> buscaAbertosPorCliente(Cliente cliente) {
		return pedidoDAO.buscaAbertosPorCliente(cliente);
	}

	@Override
	public List<Pedido> buscaHistoricoPorEmpresa(Empresa empresa) {
		return pedidoDAO.buscaHistoricoPorEmpresa(empresa);
	}

	@Override
	public List<Pedido> buscaAbertosPorEmpresa(Empresa empresa) {
		return pedidoDAO.buscaAbertosPorEmpresa(empresa);
	}

}
