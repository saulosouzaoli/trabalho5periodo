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

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Endereco;
import br.com.xsalada.model.UsuarioEmp;
import br.com.xsalada.service.IEnderecoSRV;
import br.com.xsalada.service.IUsuarioEmpSRV;

@Controller
@Scope("session")
public class LoginEmpresaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUsuarioEmpSRV usuarioEmpSRV;
	
	@Autowired
	private IEnderecoSRV enderecoSRV;

	private UsuarioEmp usuEmp;

	private Empresa empresa;
	private String login;
	private String senha;
	private List<Endereco> enderecos;

	// private TsdParticipante userLogado = null;
//	@PostConstruct
//	public void init() {
//		usuEmp = new UsuarioEmp();
//
//	}

	public void autentica(String login,String senha){
		getUsuEmp().setLogin(login);
		getUsuEmp().setSenha(senha);
		autentica();
		
	} 
	public void autentica() {
		usuEmp = usuarioEmpSRV.buscaPorLogin(usuEmp.getLogin(),usuEmp.getSenha());
		if (usuEmp == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção: ",
							"Dados inválido. Verifique e tente novamente."));
		} else {
			empresa = usuEmp.getEmpresa();
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("empresa/home.jsf");
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
							.getContextPath() + "/index"+(isLogado()?"Emp":"Cli")+".jsf");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UsuarioEmp getUsuEmp() {
		if (usuEmp == null)
			usuEmp = new UsuarioEmp();
		return usuEmp;
	}

	public void setUsuEmp(UsuarioEmp usuEmp) {
		this.usuEmp = usuEmp;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
		if(enderecos ==null)
			enderecos = enderecoSRV.buscaPorEmpresa(empresa);
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public boolean isLogado(){
		return usuEmp != null && usuEmp.getId() != null;
	}

}
