package br.com.xsalada.mb.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.service.IEmpresaSRV;

@Controller
@Scope("session")
public class EmpresaMB {
	
	@Autowired
	private IEmpresaSRV empresaSRV;
	
	private List<Empresa> empresas;

	public List<Empresa> getEmpresas() {
		if(empresas==null)
			empresas = empresaSRV.buscaTodos();
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	
	
	
}
