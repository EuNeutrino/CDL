package br.com.cdl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tab_compra_funcionario", schema = "db_cdl")
public class Compra implements Serializable {

	/**
	 * @author David Nogueira
	 */
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "BIGINT(10)", name = "idCompra")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(columnDefinition = "BIGINT(10)", nullable = false)
	private Long autorizacao;

	@ManyToOne(optional = false)
	private Unidade unidade;

	@ManyToOne(optional = false)
	private Funcionario funcionario;

	@ManyToOne(optional = false)
	private Fornecedor fornecedor;

	@ManyToOne(optional = false)
	private Usuario usuario;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataCompra;

	@Column(nullable = false)
	private BigDecimal valorCompra;

	@Column(columnDefinition = "BIGINT(2)", nullable = false)
	private Long parcelas;

	@Column(length = 50)
	private String observacao;
	
	@OneToMany(mappedBy = "compra")
	private List<CompraParcela> compraParcelas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(Long autorizacao) {
		this.autorizacao = autorizacao;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Long getParcelas() {
		return parcelas;
	}

	public void setParcelas(Long parcelas) {
		this.parcelas = parcelas;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	

	public List<CompraParcela> getCompraParcelas() {
		return compraParcelas;
	}

	public void setCompraParcelas(List<CompraParcela> compraParcelas) {
		this.compraParcelas = compraParcelas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autorizacao == null) ? 0 : autorizacao.hashCode());
		result = prime * result + ((compraParcelas == null) ? 0 : compraParcelas.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataCompra == null) ? 0 : dataCompra.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((parcelas == null) ? 0 : parcelas.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valorCompra == null) ? 0 : valorCompra.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (autorizacao == null) {
			if (other.autorizacao != null)
				return false;
		} else if (!autorizacao.equals(other.autorizacao))
			return false;
		if (compraParcelas == null) {
			if (other.compraParcelas != null)
				return false;
		} else if (!compraParcelas.equals(other.compraParcelas))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataCompra == null) {
			if (other.dataCompra != null)
				return false;
		} else if (!dataCompra.equals(other.dataCompra))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (parcelas == null) {
			if (other.parcelas != null)
				return false;
		} else if (!parcelas.equals(other.parcelas))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valorCompra == null) {
			if (other.valorCompra != null)
				return false;
		} else if (!valorCompra.equals(other.valorCompra))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

}
