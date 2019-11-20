package br.com.xsalada.mb.paginas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
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
public class AlterarCadEmpMB implements Serializable {

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
	private String senha;
	private String novaSenha;
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
		empresa = loginEmpresaMB.getEmpresa();
		usuEmp = loginEmpresaMB.getUsuEmp();
		listaEnderecos = loginEmpresaMB.getEnderecos();
		if(listaEnderecos!=null && !listaEnderecos.isEmpty()){	
			selecionaEnd(1);
		}
		
	}
	public void selecionaEnd(int numero){
		endereco = listaEnderecos.get(numero-1);
		cidade = endereco.getBairro().getCidade();
		uf = cidade.getUf();
		listaCidade = cidadeSRV.buscaPorUf(uf);
	}

	public String chamaAlteracao(){
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog(
				"/empresa/alterarCadastroEmpresa", options, null);
			return "/empresa/alterarCadastroEmpresa.xhtml";		
	}
	public String chamaAlteracaoSenha() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog(
				"/empresa/alterarSenha", options, null);
			return "/empresa/alterarSenha.xhtml";	
	}
	public void salvar() {
		try{
			empresaSrv.atualizar(empresa);
			usuarioEmpSrv.atualizar(usuEmp);
			
			JsfUtils.msgInformativa("Sucesso!","Os dados do seu cadastro foram alterados!");
		}catch(Exception e){
			JsfUtils.msgErro("Erro!","Não foi possível alterar seu cadastro!");
		}

	}
	
	public void alterarSenha(){
		if(novaSenha.equals(confSenha)){
			if(senha.equals(usuEmp.getSenha())){
				usuEmp.setSenha(novaSenha);
				usuarioEmpSrv.atualizar(usuEmp);
				RequestContext.getCurrentInstance().closeDialog(true);
				
			}else
			JsfUtils.msgInformativa("Atenção!","Digite sua senha atual corretamente");
		}else
			JsfUtils.msgAlerta("Atenção!","A nova senha e sua confirmação não batem!");				
	}
	
	public List<Integer> getQtd(){
		List<Integer> qtd = new ArrayList<>();
		
		for(int i =1;i<=listaEnderecos.size();i++){
			qtd.add(new Integer(i));			
		}
		
		return qtd;
	}	
	
	public void salvarEndereco(){
		try{
		if (endereco.getBairro().getId() == null) {
			endereco.getBairro().setCidade(cidade);
			
			bairroSrv.salvar(endereco.getBairro());
		}
		if(endereco.getId()==null)
			enderecoSrv.salvar(endereco);
		else
			enderecoSrv.atualizar(endereco);
		JsfUtils.msgInformativa("Sucesso!","Os dados dos seu endereco foram alterados!");
		}catch(Exception e){
			JsfUtils.msgErro("Erro!","Não foi possível alterar seu cadastro!");
		}
	}

	public void cadastraMaisEndereco() {
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
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
