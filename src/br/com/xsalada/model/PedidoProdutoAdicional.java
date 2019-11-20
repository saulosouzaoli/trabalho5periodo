package br.com.xsalada.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO_PRODUTO_ADICIONAL")
public class PedidoProdutoAdicional implements Serializable {

	/**
			 * 
			 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_pedido_produto", nullable = false)
	private PedidoProduto pedidoProduto;

	@ManyToOne
	@JoinColumn(name = "id_adicional")
	private Adicional adicional;

	@ManyToOne
	@JoinColumn(name = "id_ingrediente")
	private Ingrediente ingrediente;

	@Column(name = "somar", nullable = false)
	private Boolean somar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PedidoProduto getPedidoProduto() {
		return pedidoProduto;
	}

	public void setPedidoProduto(PedidoProduto pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}

	public Adicional getAdicional() {
		return adicional;
	}

	public void setAdicional(Adicional adicional) {
		this.adicional = adicional;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Boolean getSomar() {
		return somar;
	}

	public void setSomar(Boolean somar) {
		this.somar = somar;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoProdutoAdicional other = (PedidoProdutoAdicional) obj;
		if (id == null && other.getId() == null)
			return true;

		if (id != null && id.equals(other.getId()))
			return true;

		return false;
	}

}
