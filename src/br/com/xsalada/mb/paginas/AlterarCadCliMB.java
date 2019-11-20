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
import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Endereco;
import br.com.xsalada.model.UsuarioCli;
import br.com.xsalada.service.IBairroSRV;
import br.com.xsalada.service.ICidadeSRV;
import br.com.xsalada.service.IClienteSRV;
import br.com.xsalada.service.IEnderecoSRV;
import br.com.xsalada.service.IUsuarioCliSRV;
import br.com.xsalada.utilitarios.JsfUtils;

@Controller
@Scope("session")
public class AlterarCadCliMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private UsuarioCli usuCli;
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
	private IClienteSRV clienteSrv;
	@Autowired
	private IUsuarioCliSRV usuarioEmpSrv;
	@Autowired
	private IEnderecoSRV enderecoSrv;
	@Autowired
	private IBairroSRV bairroSrv;
	@Autowired
	private ICidadeSRV cidadeSRV;

	@Autowired
	private LoginClienteMB loginClienteMB;

	@PostConstruct
	public void init() {
		cliente = loginClienteMB.getCliente();
		usuCli = loginClienteMB.getUsuCli();
		listaEnderecos = loginClienteMB.getEnderecos();
		if (listaEnderecos != null && !listaEnderecos.isEmpty()) {
			selecionaEnd(1);
		} else {
			endereco = new Endereco();
			endereco.setCliente(cliente);
		}

	}

	public void selecionaEnd(int numero) {
		endereco = listaEnderecos.get(numero - 1);
		cidade = endereco.getBairro().getCidade();
		uf = cidade.getUf();
		listaCidade = cidadeSRV.buscaPorUf(uf);
	}

	public String chamaAlteracao() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog(
				"/cliente/alterarCadastroCliente", options, null);
		return "/cliente/alterarCadastroCliente.xhtml";
	}

	public String chamaAlteracaoSenha() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog("/cliente/alterarSenha",
				options, null);
		return "alterarSenha";
	}

	public void salvar() {
		try {
			clienteSrv.atualizar(cliente);
			usuarioEmpSrv.atualizar(usuCli);

			JsfUtils.msgInformativa("Sucesso!",
					"Os dados do seu cadastro foram alterados!");
		} catch (Exception e) {
			JsfUtils.msgErro("Erro!", "Não foi possível alterar seu cadastro!");
		}
	}

	public void alterarSenha() {
		if (novaSenha.equals(confSenha)) {
			if (senha.equals(usuCli.getSenha())) {
				usuCli.setSenha(novaSenha);
				usuarioEmpSrv.atualizar(usuCli);
				RequestContext.getCurrentInstance().closeDialog(true);

			} else
				JsfUtils.msgInformativa("Atenção!",
						"Digite sua senha atual corretamente");
		} else
			JsfUtils.msgAlerta("Atenção!",
					"A nova senha e sua confirmação não batem!");
	}

	public List<Integer> getQtd() {
		List<Integer> qtd = new ArrayList<>();

		for (int i = 1; i <= listaEnderecos.size(); i++) {
			qtd.add(new Integer(i));
		}

		return qtd;
	}

	public void salvarEndereco() {
		try {
			if (endereco.getBairro().getId() == null) {
				endereco.getBairro().setCidade(cidade);

				bairroSrv.salvar(endereco.getBairro());
			}
			if (endereco.getId() == null){
				enderecoSrv.salvar(endereco);
				listaEnderecos.add(endereco);
			}else
				enderecoSrv.atualizar(endereco);
			JsfUtils.msgInformativa("Sucesso!",
					"Os dados dos seu endereco foram alterados!");
		} catch (Exception e) {
			JsfUtils.msgErro("Erro!", "Não foi possível alterar seu cadastro!");
		}
	}

	public void cadastraMaisEndereco() {
		endereco = new Endereco();
		endereco.setCliente(cliente);
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getConfSenha() {
		return confSenha;
	}

	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}

	public UsuarioCli getUsuCli() {
		return usuCli;
	}

	public void setUsuCli(UsuarioCli usuCli) {
		this.usuCli = usuCli;
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
