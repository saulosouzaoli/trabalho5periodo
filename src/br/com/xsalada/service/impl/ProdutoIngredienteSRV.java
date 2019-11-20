package br.com.xsalada.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xsalada.dao.IModelDAO;
import br.com.xsalada.dao.IProdutoIngredienteDAO;
import br.com.xsalada.model.Produto;
import br.com.xsalada.model.ProdutoIngrediente;
import br.com.xsalada.service.IProdutoIngredienteSRV;

@Service
public class ProdutoIngredienteSRV extends ModelSRV<ProdutoIngrediente>
		implements IProdutoIngredienteSRV, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IProdutoIngredienteDAO produtoIngredienteDAO;

	@Override
	public IModelDAO<ProdutoIngrediente> getModelDao() {
		return produtoIngredienteDAO;
	}

	public List<ProdutoIngrediente> buscaPorProduto(Produto produto) {
		return produtoIngredienteDAO.buscaPorProduto(produto);
	}

	@Override
	public void persistir(List<ProdutoIngrediente> listaProdIngr,Produto produto) {
		for (ProdutoIngrediente pi : listaProdIngr) {
			pi.setProduto(produto);
			if (pi.isDeleta()) {
				if (pi.getId() != null)
					produtoIngredienteDAO.excluir(pi);
			} else {
				if (pi.getId() == null)
					produtoIngredienteDAO.salvar(pi);
				else
					produtoIngredienteDAO.atualizar(pi);
			}
		}
	}

}
