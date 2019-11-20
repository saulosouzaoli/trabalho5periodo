package br.com.xsalada.mb.paginas;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.service.IIngredienteSRV;

@Controller
@Scope("session")
public class IngredienteMB {

	@Autowired
	private IIngredienteSRV ingredienteSRV;
	@Autowired
	private LoginEmpresaMB loginEmpresaMB;

	private Ingrediente ingrediente;
	private List<Ingrediente> listIngrediente;

	@PostConstruct
	public void init() {

		listIngrediente = ingredienteSRV.buscaPorEmpresa(loginEmpresaMB
				.getEmpresa());
		ingrediente = new Ingrediente();
	}

	public void salvar() {
		ingrediente.setEmpresa(loginEmpresaMB.getEmpresa());
		ingredienteSRV.salvar(ingrediente);

	}

	public List<Ingrediente> getListIngredientes() {

		return listIngrediente;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	
	public List<Ingrediente> completeIngrediente(String query) {
		List<Ingrediente> b;
		if(loginEmpresaMB.isLogado()){
			b = ingredienteSRV.buscaPorDescricao("%" + query
				+ "%", loginEmpresaMB.getEmpresa());
		}else{
			b = ingredienteSRV.buscaPorDescricao("%" + query
					+ "%", loginEmpresaMB.getEmpresa());
		}

		return b;
	}

}
