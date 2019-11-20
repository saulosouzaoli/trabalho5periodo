package br.com.xsalada.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PEDIDO_PRODUTO")
public class PedidoProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_pedido", nullable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_prod", nullable = false)
	private Produto produto;

	@Column(name = "qtd", nullable = false)
	private Integer qtd;

	@OneToMany(mappedBy = "pedidoProduto", fetch = FetchType.LAZY)
	private List<PedidoProdutoAdicional> pedidoProdutoAdicionais;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Transient
	public String getNomeAdicionais() {
		StringBuilder birl = new StringBuilder();
		if (pedidoProdutoAdicionais == null
				|| pedidoProdutoAdicionais.isEmpty())
			return "";
		else
			for (PedidoProdutoAdicional ppa : pedidoProdutoAdicionais) {
				if (ppa.getSomar()) {
					birl.append(" +");
					birl.append(ppa.getAdicional().getNome());
				} else {
					birl.append(" -");
					birl.append(ppa.getIngrediente().getNome());
				}

				birl.append("; ");
			}
		return birl.toString();

	}

	@Transient
	public Float getTotal() {
		Float total = qtd * produto.getPreco();
		if (pedidoProdutoAdicionais == null
				|| pedidoProdutoAdicionais.isEmpty())
			return (float) 0;
		else
			for (PedidoProdutoAdicional ppa : pedidoProdutoAdicionais) {
				if (ppa.getAdicional() != null)
					total += ppa.getAdicional().getValor();
			}

		return total;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public List<PedidoProdutoAdicional> getPedidoProdutoAdicionais() {
		return pedidoProdutoAdicionais;
	}

	public void setPedidoProdutoAdicionais(
			List<PedidoProdutoAdicional> pedidoProdutoAdicionais) {
		this.pedidoProdutoAdicionais = pedidoProdutoAdicionais;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoProduto other = (PedidoProduto) obj;
		if (id == null && other.getId() == null)
			return true;

		if (id != null && id.equals(other.getId()))
			return true;

		return false;
	}
}
