package br.com.xsalada.mb.paginas;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.model.Cliente;
import br.com.xsalada.model.Endereco;
import br.com.xsalada.model.UsuarioCli;
import br.com.xsalada.service.IEnderecoSRV;
import br.com.xsalada.service.IUsuarioCliSRV;

@Controller
@Scope("session")
public class LoginClienteMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUsuarioCliSRV usuarioCliSRV;

	@Autowired
	private IEnderecoSRV enderecoSRV;

	private UsuarioCli usuCli;

	private Cliente cliente;
	private String login;
	private String senha;
	private List<Endereco> enderecos;

	// @PostConstruct
	// public void init(){
	//
	// }

	public void autentica(String login, String senha) {
		usuCli.setLogin(login);
		usuCli.setSenha(senha);
		autentica();

	}

	public void autentica() {
		usuCli = usuarioCliSRV.buscaPorLogin(usuCli.getLogin(),
				usuCli.getSenha());
		if (usuCli == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção: ",
							"Dados inválido. Verifique e tente novamente."));
		} else {
			cliente = usuCli.getCliente();
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("cliente/home.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void encerrarSessao() {
		try {

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			session.invalidate();
			fc.getExternalContext().redirect(
					((HttpServletRequest) FacesContext.getCurrentInstance()
							.getExternalContext().getRequest())
							.getContextPath() + "/index.jsf");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Endereco> getEnderecos() {
		if (enderecos == null)
			enderecos = enderecoSRV.buscaPorCliente(cliente);
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public boolean isLogado() {
		return usuCli != null && usuCli.getId() != null;
	}

	public UsuarioCli getUsuCli() {
		if (usuCli == null)
			usuCli = new UsuarioCli();
		return usuCli;
	}

	public void setUsuCli(UsuarioCli usuCli) {
		this.usuCli = usuCli;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
