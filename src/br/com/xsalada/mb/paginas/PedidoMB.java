package br.com.xsalada.mb.paginas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.model.Endereco;
import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.model.Produto;
import br.com.xsalada.service.IEnderecoSRV;
import br.com.xsalada.service.IProdutoIngredienteSRV;
import br.com.xsalada.service.IProdutoSRV;
import br.com.xsalada.utilitarios.JsfUtils;

@Controller
@Scope("session")
public class PedidoMB {

	@Autowired
	private IProdutoSRV produtoSRV;
	@Autowired
	private IProdutoIngredienteSRV prodingSrv;
	@Autowired
	private IEnderecoSRV enderecoSRV;

	private String filtro;
	private Ingrediente ingrediente;
	private List<Ingrediente> listaIngrediente;
	private boolean usaIng;
	private boolean usaPreco;
	private Integer tipo;
	private String nome;
	private Float preco1;
	private Float preco2;
	private List<Produto> listaProdutos;
	private Produto produto;
	private Endereco endereco;

	@PostConstruct
	private void init() {
		produto = new Produto();
		preco2 = (float) 100.0;

	}

	// private
	public void selecionouProduto(Produto p) {
		produto = p;
		produto.setProdutoIngredientes(prodingSrv.buscaPorProduto(p));
		endereco = enderecoSRV.buscaPorEmpresa(produto.getEmpresa()).get(0);
	}

	public void pesquisarProdutos() {
		if (tipo == 0)
			tipo = null;

		listaProdutos = produtoSRV.filtragem("%" + nome + "%", tipo, preco1,
				preco2, listaIngrediente);
		if (listaProdutos == null || listaProdutos.isEmpty()) {
			JsfUtils.msgInformativa("Atenção",
					"Não foi encontrado nenhum produto com estes filtros.");
		} else {

			JsfUtils.redirecionar("/cliente/resultado.jsf");

		}
	}

	public void limparIngs() {

		listaIngrediente = new ArrayList<>();
	}

	public void adicionaIng() {
		if (ingrediente == null || ingrediente.getId() == null)
			JsfUtils.msgInformativa("Atenção", "Selecione um ingrediente");
		else
			listaIngrediente.add(ingrediente);
	}

	public void removerIngrediente(Ingrediente ing) {
		listaIngrediente.remove(ing);
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getTela() {
		if (filtro != null && !filtro.isEmpty())
			return "/cliente/" + filtro + ".xhtml";
		else
			return "";
	}

	public String getTp() {
		if (produto == null || produto.getTipo() == null)
			return "";
		switch (produto.getTipo()) {
		case 2:
			return "Comida";

		case 1:
			return "Bebida";

		default:
			return "";
		}
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public List<Ingrediente> getListaIngrediente() {
		return listaIngrediente;
	}

	public void setListaIngrediente(List<Ingrediente> listaIngrediente) {
		this.listaIngrediente = listaIngrediente;
	}

	public boolean isUsaIng() {
		return usaIng;
	}

	public void setUsaIng(boolean usaIng) {
		this.usaIng = usaIng;
	}

	public boolean isUsaPreco() {
		return usaPreco;
	}

	public void setUsaPreco(boolean usaPreco) {
		this.usaPreco = usaPreco;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getPreco1() {
		return preco1;
	}

	public void setPreco1(Float preco1) {
		this.preco1 = preco1;
	}

	public Float getPreco2() {
		return preco2;
	}

	public void setPreco2(Float preco2) {
		this.preco2 = preco2;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
