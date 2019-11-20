package br.com.xsalada.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@Column(name = "data_pedido", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataPedido;

	@Column(name = "data_finalizado")
	@Temporal(TemporalType.DATE)
	private Date dataFinalizado;

	@Column(name = "data_confirmacao")
	@Temporal(TemporalType.DATE)
	private Date dataConfirmacao;

	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<PedidoProduto> pedidoProdutos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Transient
	public String getDtPedido() {
		if (dataPedido == null)
			return "Ainda Não";
		else
			return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
					.format(dataPedido);

	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	@Transient
	public String getDtFinalizado() {
		if (dataFinalizado == null)
			return "Ainda Não";
		else
			return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
					.format(dataFinalizado);

	}

	public Date getDataFinalizado() {
		return dataFinalizado;
	}

	public void setDataFinalizado(Date dataFinalizado) {
		this.dataFinalizado = dataFinalizado;
	}

	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null && other.getId() == null)
			return true;

		if (id != null && id.equals(other.getId()))
			return true;

		return false;
	}

}
