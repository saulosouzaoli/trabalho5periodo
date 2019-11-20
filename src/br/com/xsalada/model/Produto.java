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
@Table(name = "produto")
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "preco", nullable = false)
	private Float preco;

	@Column(name = "nome", length = 50, nullable = false)
	private String nome;

	@Column(name = "tempo_medio_preparo", length = 50)
	private Integer tempoMedioPreparo;

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@Column(name = "tipo", length = 50, nullable = false)
	private Integer tipo;

	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<ProdutoIngrediente> produtoIngredientes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTempoMedioPreparo() {
		return tempoMedioPreparo;
	}

	public void setTempoMedioPreparo(Integer tempoMedioPreparo) {
		this.tempoMedioPreparo = tempoMedioPreparo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public List<ProdutoIngrediente> getProdutoIngredientes() {
		return produtoIngredientes;
	}

	public void setProdutoIngredientes(
			List<ProdutoIngrediente> produtoIngredientes) {
		this.produtoIngredientes = produtoIngredientes;
	}

	@Transient
	public String getNomeIng() {
		StringBuilder birl = new StringBuilder();
		if (produtoIngredientes == null || produtoIngredientes.isEmpty())
			for (ProdutoIngrediente pi : produtoIngredientes) {
				birl.append(pi.getIngrediente().getNome());
				birl.append("; ");
			}
		return birl.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null && other.getId() == null)
			return true;

		if (id != null && id.equals(other.getId()))
			return true;

		return false;
	}
}
