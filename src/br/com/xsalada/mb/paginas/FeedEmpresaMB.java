package br.com.xsalada.mb.paginas;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.model.Pedido;
import br.com.xsalada.model.PedidoProduto;
import br.com.xsalada.service.IPedidoProdutoAdicionalSRV;
import br.com.xsalada.service.IPedidoProdutoSRV;
import br.com.xsalada.service.IPedidoSRV;
import br.com.xsalada.utilitarios.JsfUtils;

@Controller
@Scope("session")
public class FeedEmpresaMB {

	@Autowired
	private LoginEmpresaMB loginEmpresaMB;
	@Autowired
	private IPedidoSRV pedidoSRV;
	@Autowired
	private IPedidoProdutoSRV pedidoProdutoSRV;
	@Autowired
	private IPedidoProdutoAdicionalSRV pedidoProdutoAdicionalSRV;
	private Date ultimaDataVista;
	private List<PedidoProduto> listaPedido;
	private List<Pedido> listaPedidoNovos;

	@PostConstruct
	private void init() {
		pedidosEmAberto();
	}

	// este metodo é disparado de 60 em 60 segundos parar informar se chegou
	// novos pedidos
	public void pedidosNovos() {
		if (loginEmpresaMB.isLogado()) {

			ultimaDataVista = new Date();
			listaPedidoNovos = pedidoSRV.buscaPorEmpresa(
					loginEmpresaMB.getEmpresa(), ultimaDataVista);
			if (listaPedidoNovos != null && !listaPedidoNovos.isEmpty()) {
				listaPedido = null;
				JsfUtils.msgInformativa("Atenção",
						"Há " + listaPedidoNovos.size()
								+ " novos pedidos encaminhados a você");
			}
		}
	}

	public void pedidosEmAberto() {
		if (loginEmpresaMB.isLogado() && listaPedido == null) {

			listaPedido = pedidoProdutoSRV.buscaPorEmpresa(loginEmpresaMB
					.getEmpresa());
			for(PedidoProduto pp:listaPedido){
				pp.setPedidoProdutoAdicionais(pedidoProdutoAdicionalSRV.buscaPorPedidoProduto(pp));
			}
		}
	}

	public void finalizarPedido(PedidoProduto pedidoProduto) {
		try {
			pedidoProduto.getPedido().setDataFinalizado(new Date());
			JsfUtils.msgErro("Sucesso", "O "
					+ pedidoProduto.getProduto().getNome() + " foi finalizado");
			listaPedido = null;
			pedidoSRV.atualizar(pedidoProduto.getPedido());
		} catch (Exception e) {
			JsfUtils.msgErro("Erro",
					"Algo inesperado aconteceu ao finalizar o pedido:"
							+ pedidoProduto.getProduto().getNome());
		}
	}

	public Date getUltimaDataVista() {
		return ultimaDataVista;
	}

	public void setUltimaDataVista(Date ultimaDataVista) {
		this.ultimaDataVista = ultimaDataVista;
	}

	public List<PedidoProduto> getListaPedido() {
		pedidosEmAberto();
		return listaPedido;
	}

	public void setListaPedido(List<PedidoProduto> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public List<Pedido> getListaPedidoNovos() {
		return listaPedidoNovos;
	}

	public void setListaPedidoNovos(List<Pedido> listaPedidoNovos) {
		this.listaPedidoNovos = listaPedidoNovos;
	}

}
