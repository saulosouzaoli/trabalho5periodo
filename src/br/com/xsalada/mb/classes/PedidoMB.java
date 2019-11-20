package br.com.xsalada.mb.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xsalada.service.IPedidoSRV;

@Controller
@Scope("session")
public class PedidoMB {
	
	@Autowired
	private IPedidoSRV PedidoSRV;

}
