package br.com.xsalada.mb.paginas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.model.Adicional;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.model.Pedido;
import br.com.xsalada.model.PedidoProduto;
import br.com.xsalada.model.PedidoProdutoAdicional;
import br.com.xsalada.model.Produto;
import br.com.xsalada.model.ProdutoIngrediente;
import br.com.xsalada.service.IAdicionalSRV;
import br.com.xsalada.service.IPedidoProdutoAdicionalSRV;
import br.com.xsalada.service.IPedidoProdutoSRV;
import br.com.xsalada.service.IPedidoSRV;
import br.com.xsalada.service.IProdutoIngredienteSRV;
import br.com.xsalada.utilitarios.JsfUtils;

@Controller
@Scope("session")
public class CarrinhoMB {

	private List<PedidoProduto> listaProdutosDesejados;

	private List<Adicional> listaAdicional;

	private List<Pedido> historicoPedidos;
	private List<Pedido> listaReceber;
	private Boolean tipoAdicional;
	private Ingrediente ingrediente;
	private Adicional adicional;
	private PedidoProduto pproduto;

	@Autowired
	private IProdutoIngredienteSRV prodingSrv;
	@Autowired
	private IAdicionalSRV adicionalSRV;
	@Autowired
	private LoginClienteMB loginClienteMB;
	@Autowired
	private IPedidoSRV pedidoSRV;
	@Autowired
	private IPedidoProdutoSRV pedidoProdutoSRV;
	@Autowired
	private IPedidoProdutoAdicionalSRV pedidoProdutoAdicionalSRV;

	@PostConstruct
	public void init() {
		limparCarrinho();
		atualizarTudo();
	}

	public boolean isRenderizaPedidos() {
		return listaProdutosDesejados != null
				&& !listaProdutosDesejados.isEmpty();
	}

	public boolean isRenderizaReceber() {
		return listaReceber != null && !listaReceber.isEmpty();
	}

	public boolean isRenderizaHistorico() {
		return historicoPedidos != null && !historicoPedidos.isEmpty();
	}

	public void atualizarTudo() {
		historicoPedidos = pedidoSRV.buscaHistoricoPorCliente(loginClienteMB
				.getCliente());
		listaReceber = pedidoSRV.buscaAbertosPorCliente(loginClienteMB
				.getCliente());

		if (historicoPedidos == null)
			historicoPedidos = new ArrayList<>();

		if (listaReceber == null)
			listaReceber = new ArrayList<>();
	}

	public void chamarCarrinho() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		options.put("height", 630);
		options.put("contentHeight", 610);
		options.put("width", 750);
		options.put("contentWidth", 720);
		RequestContext.getCurrentInstance().openDialog("/cliente/carrinho",
				options, null);
	}

	public void adicionarAoCarrinho(Produto p) {
		PedidoProduto pp = new PedidoProduto();
		pp.setProduto(p);
		pp.getProduto().setProdutoIngredientes(prodingSrv.buscaPorProduto(p));
		pp.setQtd(1);
		listaProdutosDesejados.add(pp);
		JsfUtils.msgInformativa("Sucesso",
				"O produto foi adicionado ao carrinho");
	}

	public void altIng(PedidoProduto p) {
		pproduto = p;

		JsfUtils.msgInformativa("Sucesso",
				"O produto foi adicionado ao carrinho");
	}

	public void addAdicional() {
		PedidoProdutoAdicional ppa = new PedidoProdutoAdicional();
		ppa.setIngrediente(ingrediente);
		ppa.setAdicional(adicional);
		ppa.setSomar(tipoAdicional);
		ppa.setPedidoProduto(pproduto);
		if (pproduto.getPedidoProdutoAdicionais() == null)
			pproduto.setPedidoProdutoAdicionais(new ArrayList<PedidoProdutoAdicional>());
		pproduto.getPedidoProdutoAdicionais().add(ppa);

		JsfUtils.msgInformativa("Sucesso",
				"Com o adicional, o preço aumentou para " + pproduto.getTotal());
	}

	private Pedido filtraPedidoDaEmpresa(List<Pedido> lped, Empresa emp) {
		for (Pedido p : lped) {
			if (p.getEmpresa().equals(emp))
				return p;
		}
		return null;
	}

	private void cadastraPedidoProdutoAdicionais(
			List<PedidoProdutoAdicional> listaPPA, PedidoProduto pedidoProduto) {
		for (PedidoProdutoAdicional ppa : listaPPA) {
			ppa.setPedidoProduto(pedidoProduto);
			pedidoProdutoAdicionalSRV.salvar(ppa);
		}
	}

	public void finalizaPedido(Pedido p) {
		try {
			p = pedidoSRV.buscaPorId(p.getId());
			p.setDataFinalizado(new Date());
			p.setDataConfirmacao(new Date());
			JsfUtils.msgInformativa("Atenção",
					"Foi confirmado o recebimento do pedido");
		} catch (Exception e) {
			JsfUtils.msgErro("ERRO",
					"Erro ao confirmar");
		}
	}

	public void confirmarPedido() {
		boolean b1 = false, b2 = false, b3 = false;
		try {
			// ped.se;
			List<Pedido> pedidos = new ArrayList<>();
			List<Empresa> empresas = new ArrayList<>();

			// Cadastra pedidos para todas as empresas que tem produtos neste
			// carrinho
			for (PedidoProduto pedprod : listaProdutosDesejados) {
				if (!empresas.contains(pedprod.getProduto().getEmpresa())) {
					Pedido ped = new Pedido();
					ped.setCliente(loginClienteMB.getCliente());
					ped.setDataPedido(new Date());
					ped.setEmpresa(pedprod.getProduto().getEmpresa());
					pedidoSRV.salvar(ped);
					pedidos.add(ped);
					empresas.add(pedprod.getProduto().getEmpresa());
				}
			}
			b1 = true;
			//
			for (PedidoProduto pedprod : listaProdutosDesejados) {
				pedprod.setPedido(filtraPedidoDaEmpresa(pedidos, pedprod
						.getProduto().getEmpresa()));
				pedidoProdutoSRV.salvar(pedprod);
				// cadastraPedidoProdutoAdicionais(pedprod.getPedidoProdutoAdicionais(),
				// pedprod);
			}
			b2 = true;
			for (PedidoProduto pedprod : listaProdutosDesejados) {
				pedprod.setPedido(filtraPedidoDaEmpresa(pedidos, pedprod
						.getProduto().getEmpresa()));
				// pedidoProdutoSRV.salvar(pedprod);
				cadastraPedidoProdutoAdicionais(
						pedprod.getPedidoProdutoAdicionais(), pedprod);
			}
			b3 = true;
			JsfUtils.msgInformativa("Atenção", "O seu pedido foi enviado");

			listaProdutosDesejados.clear();
			listaReceber.addAll(pedidos);
		} catch (Exception e) {
			if (b1)
				JsfUtils.msgInformativa("Atenção",
						"Pedido foi cadastrado nas empresas");
			else
				JsfUtils.msgErro("ERRO",
						"Pedidos não foram cadastrados  nas empresas");
			if (b2)
				JsfUtils.msgInformativa("Atenção",
						"Os produtos foram associados");
			else
				JsfUtils.msgErro("ERRO",
						"Mas, os produtos não foram associados");
			if (b3)
				JsfUtils.msgInformativa("Atenção", "E os adicionais também");
			else
				JsfUtils.msgErro("ERRO", b2 ? "Mas os adicionais não"
						: "E os adicionais também não");
		}
	}

	public List<Adicional> getListaAdicional() {

		if (pproduto != null) {
			if (listaAdicional == null
					|| listaAdicional.isEmpty()
					|| listaAdicional.get(0).getEmpresa().getId()
							.equals(pproduto.getProduto().getEmpresa().getId()))
				listaAdicional = adicionalSRV.buscaPorEmpresa(pproduto
						.getProduto().getEmpresa());
		}
		return listaAdicional;
	}

	public List<Ingrediente> getListaIngredientes() {
		if (pproduto == null)
			return new ArrayList<>();

		List<Ingrediente> listaIngredientes = new ArrayList<>();

		for (ProdutoIngrediente pi : pproduto.getProduto()
				.getProdutoIngredientes()) {
			listaIngredientes.add(pi.getIngrediente());
		}

		return listaIngredientes;
	}

	public void removerPedido(PedidoProduto pp) {
		listaProdutosDesejados.remove(pp);
	}

	public void limparCarrinho() {
		listaProdutosDesejados = new ArrayList<>();
	}

	public List<PedidoProduto> getListaProdutosDesejados() {
		return listaProdutosDesejados;
	}

	public void setListaProdutosDesejados(
			List<PedidoProduto> listaProdutosDesejados) {
		this.listaProdutosDesejados = listaProdutosDesejados;
	}

	public List<Pedido> getHistoricoPedidos() {
		if (historicoPedidos == null)
			historicoPedidos = pedidoSRV.buscaPorCliente(loginClienteMB
					.getCliente());

		return historicoPedidos;
	}

	public void setHistoricoPedidos(List<Pedido> historicoPedidos) {
		this.historicoPedidos = historicoPedidos;
	}

	public String getNumero() {
		if (listaProdutosDesejados.isEmpty())
			return "";
		return "(" + listaProdutosDesejados.size() + ")";

	}

	public Boolean getTipoAdicional() {
		return tipoAdicional;
	}

	public void setTipoAdicional(Boolean tipoAdicional) {
		this.tipoAdicional = tipoAdicional;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Adicional getAdicional() {
		return adicional;
	}

	public void setAdicional(Adicional adicional) {
		this.adicional = adicional;
	}

	public List<Pedido> getListaReceber() {
		return listaReceber;
	}

	public void setListaReceber(List<Pedido> listaReceber) {
		this.listaReceber = listaReceber;
	}
}
