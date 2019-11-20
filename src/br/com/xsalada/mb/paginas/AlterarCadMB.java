package br.com.xsalada.mb.paginas;

import java.io.Serializable;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.utilitarios.JsfUtils;

@Controller
@Scope("session")
public class AlterarCadMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LoginEmpresaMB loginEmpresaMB;
	@Autowired
	private LoginClienteMB loginClienteMB;
	@Autowired
	private AlterarCadEmpMB alterarCadEmpMB;
	@Autowired
	private AlterarCadCliMB alterarCadCliMB;

	private String alterarCad;
	private String alterarSenha;

	public void alterarCadastro() {

		if (loginEmpresaMB.isLogado())
			setAlterarCad(alterarCadEmpMB.chamaAlteracao());
		if (loginClienteMB.isLogado())
			setAlterarCad(alterarCadCliMB.chamaAlteracao());
	}
	
	public void alterarSenha() {

		if (loginEmpresaMB.isLogado())
			alterarSenha= alterarCadEmpMB.chamaAlteracaoSenha();
		if (loginClienteMB.isLogado())
			alterarSenha= alterarCadCliMB.chamaAlteracaoSenha();
	}
	public void onCloseDlg(SelectEvent event){
		boolean b = (boolean) event.getObject();
		if(b)
			JsfUtils.msgInformativa("Sucesso!","Sua senha foi alterada!");
	}
	public String getNome(){
		if (loginEmpresaMB.isLogado())
			return  alterarCadEmpMB.getEmpresa().getNomeFantasia();
		if (loginClienteMB.isLogado())
			return  alterarCadCliMB.getCliente().getNome();
		
		return "";
		
	}

	public String getAlterarCad() {
		alterarCadastro();
		return alterarCad;
	}

	public void setAlterarCad(String alterarCad) {
		this.alterarCad = alterarCad;
	}

	public String getAlterarSenha() {
		alterarSenha();
		return alterarSenha;
	}

	public void setAlterarSenha(String alterarSenha) {
		this.alterarSenha = alterarSenha;
	}
}
