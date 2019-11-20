package br.com.xsalada.mb.classes;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.mb.paginas.LoginEmpresaMB;
import br.com.xsalada.model.Adicional;
import br.com.xsalada.service.IAdicionalSRV;
import br.com.xsalada.utilitarios.JsfUtils;

@Controller
@Scope("session")
public class AdicionalMB {

	@Autowired
	private IAdicionalSRV adicionalSRV;
	@Autowired
	private LoginEmpresaMB loginEmpresaMB;
	private Adicional adicional;
	private List<Adicional> listaAdd;

	@PostConstruct
	private void init() {
		adicional = new Adicional();
		listaAdd = adicionalSRV.buscaPorEmpresa(loginEmpresaMB.getEmpresa());

	}

	public void cadastrarAdicional() {
		try {
			adicionalSRV.salvar(adicional);
			adicional = new Adicional();
			listaAdd.add(adicional);
			JsfUtils.msgInformativa("Atenção", "Cadastro realizado com sucesso");
		} catch (Exception e) {
			JsfUtils.msgAlerta("Atenção", "Erro ao inserir registro");
		}
	}

	public void removerAdicional(Adicional add) {
		try {
			listaAdd.remove(add);
			adicionalSRV.excluir(adicional);
			JsfUtils.msgInformativa("Atenção", "Cadastro realizado com sucesso");
		} catch (Exception e) {
			JsfUtils.msgAlerta("Atenção",
					"Não foi possível excluir: Este registro tem relações com pedidos");
		}

	}

	public boolean isRenderizaUpdate() {
		return adicional != null && adicional.getId() != null
				&& !adicional.getId().equals(0);
	}

	public Adicional getAdicional() {
		return adicional;
	}

	public void setAdicional(Adicional adicional) {
		this.adicional = adicional;
	}

	public List<Adicional> getListaAdd() {
		return listaAdd;
	}

	public void setListaAdd(List<Adicional> listaAdd) {
		this.listaAdd = listaAdd;
	}

}
