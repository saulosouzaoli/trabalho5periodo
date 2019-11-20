package br.com.xsalada.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.xsalada.dao.IPedidoDAO;
import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Pedido;

@Repository
public class PedidoDAO extends ModelDAO<Pedido> implements IPedidoDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaPorEmpresa(Empresa empresa) {
		try {
			return (List<Pedido>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " I Where I.empresa= ?",
							new Object[] { empresa });
		} catch (Exception e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaPorCliente(Cliente cliente) {
		try {
			return (List<Pedido>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " P Where P.empresa= ?",
							new Object[] { cliente });
		} catch (Exception e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaPorEmpresa(Empresa empresa, Date dataPedido) {
		try {
			return (List<Pedido>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " I Where I.empresa= ? and I.dataPedido >= ?",
							new Object[] { empresa,dataPedido });
		} catch (Exception e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaHistoricoPorCliente(Cliente cliente) {
		try {
			return (List<Pedido>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " P Where P.cliente= ? and P.dataConfirmacao is not null",
							new Object[] { cliente });
		} catch (Exception e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaAbertosPorCliente(Cliente cliente) {
		try {
			return (List<Pedido>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " P Where P.cliente= ? and P.dataConfirmacao is null",
							new Object[] { cliente });
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaHistoricoPorEmpresa(Empresa empresa) {
		try {
			return (List<Pedido>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " P Where P.empresa= ? and P.dataConfirmacao is not null",
							new Object[] { empresa });
		} catch (Exception e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaAbertosPorEmpresa(Empresa empresa) {
		try {
			return (List<Pedido>) this.getHibernateTemplate()
					.find("from "
							+ getTClass().getSimpleName()
							+ " P Where P.empresa= ? and P.dataConfirmacao is null",
							new Object[] { empresa });
		} catch (Exception e) {
			return null;
		}
	}

}
