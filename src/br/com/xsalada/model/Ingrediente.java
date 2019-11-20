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

@Entity
@Table(name = "ingrediente")
public class Ingrediente implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "descricao", length = 80)
	private String descricao;
	
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	@OneToMany(mappedBy = "ingrediente", fetch = FetchType.LAZY)
	private List<ProdutoIngrediente> produtoIngredientes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		
		if ( id != null && id.equals(other.getId()) && descricao!=null && descricao.equals(other.getDescricao()) && nome!=null && nome.equals(other.getNome()) && empresa==null && other.getEmpresa()==null )
			return true;
		
		 if ( id != null && id.equals(other.getId()) && descricao!=null && descricao.equals(other.getDescricao()) && nome!=null && nome.equals(other.getNome()) && empresa!=null  &&  other.getEmpresa()!=null && empresa.getId().equals(other.getEmpresa().getId()) )
			return true;
		 
		 if ( id == null && other.getId()==null && descricao!=null && other.getDescricao()!=null && nome.equals(other.getNome()) && empresa!=null  &&  other.getEmpresa()!=null  )
				return true;
		 
		 
		 
		return false;
	}
	public List<ProdutoIngrediente> getProdutoIngredientes() {
		return produtoIngredientes;
	}
	public void setProdutoIngredientes(List<ProdutoIngrediente> produtoIngredientes) {
		this.produtoIngredientes = produtoIngredientes;
	}
}
