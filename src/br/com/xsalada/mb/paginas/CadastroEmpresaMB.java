package br.com.xsalada.mb.paginas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.model.Bairro;
import br.com.xsalada.model.Cidade;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Endereco;
import br.com.xsalada.model.UsuarioEmp;
import br.com.xsalada.service.IBairroSRV;
import br.com.xsalada.service.ICidadeSRV;
import br.com.xsalada.service.IEmpresaSRV;
import br.com.xsalada.service.IEnderecoSRV;
import br.com.xsalada.service.IUsuarioEmpSRV;
import br.com.xsalada.utilitarios.JsfUtils;

@Controller
@Scope("session")
public class CadastroEmpresaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Empresa empresa;
	private UsuarioEmp usuEmp;
	private List<Endereco> listaEnderecos;
	private List<Cidade> listaCidade;
	private List<Bairro> listaBairro;
	private Endereco endereco;
	private String confSenha;
	private String uf;
	private Cidade cidade;

	@Autowired
	private IEmpresaSRV empresaSrv;
	@Autowired
	private IUsuarioEmpSRV usuarioEmpSrv;
	@Autowired
	private IEnderecoSRV enderecoSrv;
	@Autowired
	private IBairroSRV bairroSrv;
	@Autowired
	private ICidadeSRV cidadeSRV;
	
	@Autowired
	private LoginEmpresaMB loginEmpresaMB;

	@PostConstruct
	public void init() {
		empresa = new Empresa();
		endereco = new Endereco();
		endereco.setEmpresa(empresa);
		usuEmp = new UsuarioEmp();
		listaEnderecos = new ArrayList<>();
		listaEnderecos.add(endereco);
	}

	public void salvar() {
		try{
			empresaSrv.salvar(empresa);
			usuEmp.setEmpresa(empresa);
			usuarioEmpSrv.salvar(usuEmp);
			enderecoSrv.salvar(listaEnderecos,cidade,empresa);
			loginEmpresaMB.autentica(usuEmp.getLogin(),usuEmp.getSenha());
			//JsfUtils.msgInformativa("Sucesso!","Cadastro realizado!");
		}catch(Exception e){
			JsfUtils.msgErro("Erro!","Não foi possível finaalizar seu cadastro!");
		}

	}

	public void cadastraMaisEndereco() {
		if (endereco.getBairro().getId() == null) {
			endereco.getBairro().setCidade(cidade);
			bairroSrv.salvar(endereco.getBairro());
		}
//		enderecoSrv.salvar(endereco);
		endereco = new Endereco();
		endereco.setEmpresa(empresa);
		listaEnderecos.add(endereco);
	}

	public String onFlowProcess(FlowEvent event) {
		if (event.getOldStep().equals("enderecoTab")) {
			if (endereco.getBairro().getId() == null) {
				endereco.getBairro().setCidade(cidade);
			}
		} 
			return event.getNewStep();
		
	}



	public List<Bairro> completeBairro(String query) {
		List<Bairro> b = bairroSrv.buscaPorDescricao("%" + query + "%", cidade);

		return b;
	}

	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getConfSenha() {
		return confSenha;
	}

	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}

	public UsuarioEmp getUsuEmp() {
		return usuEmp;
	}

	public void setUsuEmp(UsuarioEmp usuEmp) {
		this.usuEmp = usuEmp;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
		// if(endereco.getBairro().getCidade()!=null &&
		// !cidade.getId().equals(endereco.getBairro().getCidade().getId())){
		// endereco.setBairro(new Bairro());
		// }
	}

	public void buscaCidades() {
		if (uf != null) {
			listaCidade = cidadeSRV.buscaPorUf(uf);
			endereco.setBairro(new Bairro());
		}
	}

	public List<Cidade> getListaCidade() {

		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public List<Bairro> getListaBairro() {

		return listaBairro;
	}

	public void setListaBairro(List<Bairro> listaBairro) {
		this.listaBairro = listaBairro;
	}

}
