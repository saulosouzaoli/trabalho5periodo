package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.dao.IProdutoDAO;
import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;
import br.com.xsalada.model.Produto;
import br.com.xsalada.service.IProdutoIngredienteSRV;
import br.com.xsalada.service.IProdutoSRV;
import br.com.xsalada.model.ProdutoIngrediente;
import br.com.xsalada.utilitarios.JsfUtils;

@Service
public class ProdutoSRV extends ModelSRV<Produto> implements IProdutoSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IProdutoDAO produtoDAO;
	@Autowired
	private IProdutoIngredienteSRV prodingSrv;
	
	

	@Override
	public IModelDAO<Produto> getModelDao() {
		return produtoDAO;
	}
	
	@Override
	public List<Produto> buscaPorEmpresa(Empresa empresa){
		return produtoDAO.buscaPorEmpresa(empresa);
		
	}
	
	@Override
	public void salvar(Produto produto, List<ProdutoIngrediente> listaProdIngr){
		try {
			produtoDAO.salvar(produto);
			prodingSrv.persistir(listaProdIngr,produto);
			
			JsfUtils.msgInformativa("Sucesso","O seu produto foi cadastrado");
		} catch (Exception e) {
			JsfUtils.msgErro("Erro", "Houve um erro ao salvar");
		}
	}
	
	@Override
	public void atualizar(Produto produto, List<ProdutoIngrediente> listaProdIngr){
		try {
			produtoDAO.atualizar(produto);
			prodingSrv.persistir(listaProdIngr,produto);
			
			JsfUtils.msgInformativa("Sucesso","O seu produto foi cadastrado");
		} catch (Exception e) {
			JsfUtils.msgErro("Erro", "Houve um erro ao salvar");
		}
	}

	@Override
	public void excluir(Produto p, List<ProdutoIngrediente> listaPI) {
		for(ProdutoIngrediente pi:listaPI){
			pi.setDeleta(true);
		}
		prodingSrv.persistir(listaPI,p);
		produtoDAO.excluir(p);
		
	}

	@Override
	public List<Produto> filtragem(String nome, Integer tipo, Float preco1,
			Float preco2, List<Ingrediente> listaIngrediente) {
		// TODO Auto-generated method stub
		return produtoDAO.filtragem(nome,tipo,preco1,preco2,listaIngrediente);
	}

	
	
}
