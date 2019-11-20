package br.com.xsalada.mb.paginas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.model.Produto;
import br.com.xsalada.model.ProdutoIngrediente;
import br.com.xsalada.service.IIngredienteSRV;
import br.com.xsalada.service.IProdutoIngredienteSRV;
import br.com.xsalada.service.IProdutoSRV;
import br.com.xsalada.utilitarios.JsfUtils;

@Controller
@Scope("session")
public class ProdutoMB {

	@Autowired
	private IProdutoSRV produtoSRV;
	@Autowired
	private LoginEmpresaMB loginEmpresaMB;
	@Autowired
	private IIngredienteSRV ingredienteSrv;
	@Autowired
	private IProdutoIngredienteSRV prodingSrv;
	@Autowired
	private IIngredienteSRV ingredienteSRV;

	private Produto produto;
	private List<ProdutoIngrediente> listaProdIngr;
	private List<ProdutoIngrediente> listaProdIngrREAL;
	private List<Produto> listaProduto;
	private Ingrediente ingrediente;
	private boolean selected;

	@PostConstruct
	private void init() {
		// produto = new Produto();
		listaProdIngr = new ArrayList<>();
		listaProdIngrREAL = new ArrayList<>();
		listaProduto = produtoSRV.buscaPorEmpresa(loginEmpresaMB.getEmpresa());
		limparIng();
	}

	public void gerenciaIngrediente() {
		if (ingrediente.getId() == null) {
			List<Ingrediente> i = ingredienteSrv.buscaPorDescricao(
					ingrediente.getDescricao(), loginEmpresaMB.getEmpresa());
			if (i != null && !i.isEmpty())
				ingrediente = i.get(0);
		}

		if (ingrediente.getId() == null) {
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("resizable", false);
			options.put("draggable", false);
			options.put("modal", true);
			RequestContext.getCurrentInstance().openDialog(
					"/empresa/ingrediente", options, null);
		} else {
			adicionaIngrediente();
		}
	}

	public void cadastrarIngrediente() {
		ingrediente.setEmpresa(loginEmpresaMB.getEmpresa());
		ingredienteSRV.salvar(ingrediente);
		RequestContext.getCurrentInstance().closeDialog(ingrediente);
	}

	public void adicionaIngrediente() {
		if (ingrediente != null && ingrediente.getId() != null) {
			ProdutoIngrediente pi = new ProdutoIngrediente();
			pi.setIngrediente(ingrediente);
			pi.setProduto(produto);
			listaProdIngr.add(pi);
			listaProdIngrREAL.add(pi);
			limparIng();
		}
	}

	public void limparIng() {
		ingrediente = new Ingrediente();
		ingrediente.setEmpresa(loginEmpresaMB.getEmpresa());

	}

	public void adicionarProduto() {
		produto = new Produto();
		produto.setEmpresa(loginEmpresaMB.getEmpresa());
		listaProdIngr = new ArrayList<>();
		listaProdIngrREAL = new ArrayList<>();
		selected = true;
	}
	
	

	public void removerIngrediente(ProdutoIngrediente proding) {
		proding.setDeleta(true);
		listaProdIngr.remove(proding);
	}

	public void selecionaProduto() {
		listaProdIngrREAL = prodingSrv.buscaPorProduto(produto);
		listaProdIngr = new ArrayList<>();
		listaProdIngr.addAll(listaProdIngrREAL);
		selected = true;
	}

	public void removerProduto(Produto p) {
		listaProduto.remove(p);
		produtoSRV.excluir(p, prodingSrv.buscaPorProduto(produto));

		if (p == produto)
			produto = new Produto();
	}

	public void salvar() {
		try {
			produtoSRV.salvar(produto, listaProdIngrREAL);
			listaProduto.add(produto);
			JsfUtils.msgInformativa("Sucesso", "O seu produto foi cadastrado");
		} catch (Exception e) {
			JsfUtils.msgErro("Erro", "Houve um erro ao salvar");
		}
	}

	public void update() {
		try {
			produtoSRV.atualizar(produto, listaProdIngrREAL);
			JsfUtils.msgInformativa("Sucesso", "O seu produto foi cadastrado");
		} catch (Exception e) {
			JsfUtils.msgErro("Erro", "Houve um erro ao salvar");
		}
	}

	public void cancelar() {
		try {
			produto = new Produto();
			listaProduto = produtoSRV.buscaPorEmpresa(loginEmpresaMB
					.getEmpresa());
			selected = false;
		} catch (Exception e) {
			JsfUtils.msgErro("Erro", "Houve um erro ao salvar");
		}
	}

	public List<Ingrediente> completeIngrediente(String query) {
		List<Ingrediente> b = ingredienteSrv.buscaPorDescricao("%" + query
				+ "%", loginEmpresaMB.getEmpresa());

		return b;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean b) {
		selected = b;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<ProdutoIngrediente> getListaProdIngr() {
		return listaProdIngr;
	}

	public void setListaProdIngr(List<ProdutoIngrediente> listaProdIngr) {
		this.listaProdIngr = listaProdIngr;
	}
	
	
}
