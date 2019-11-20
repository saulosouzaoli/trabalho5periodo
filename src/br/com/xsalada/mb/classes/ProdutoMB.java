package br.com.xsalada.mb.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.service.IProdutoSRV;

@Controller
@Scope("session")
public class ProdutoMB {
	
	@Autowired
	private IProdutoSRV ProdutoSRV;

}
