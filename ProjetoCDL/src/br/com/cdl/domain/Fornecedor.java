package br.com.cdl.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author David Nogueira
 */
@Entity
@Table(name = "tab_fornecedor", schema = "db_cdl")
public class Fornecedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "BIGINT(10)", name = "idFornecedor")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 35, nullable = false)
	private String fantasia;

	@Column(columnDefinition = "BIGINT(14)", nullable = false)
	private Long documento;

	@Column(length = 35, nullable = false)
	private String logradouro;

	@Column(length = 20)
	private String complemento;

	@Column(length = 20, nullable = false)
	private String bairro;

	@Column(length = 35, nullable = false)
	private String cidade;

	@Column(length = 30)
	private String email;

	@Column(length = 2, nullable = false)
	private String uf;

	@Column(length = 10, nullable = false)
	private String cep;

	@Column(length = 20)
	private String fonCel;

	@Column(length = 20)
	private String foneFixo;

	@OneToMany(mappedBy = "fornecedor")
	private List<Compra> Compra;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Compra == null) ? 0 : Compra.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fantasia == null) ? 0 : fantasia.hashCode());
		result = prime * result + ((fonCel == null) ? 0 : fonCel.hashCode());
		result = prime * result + ((foneFixo == null) ? 0 : foneFixo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (Compra == null) {
			if (other.Compra != null)
				return false;
		} else if (!Compra.equals(other.Compra))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fantasia == null) {
			if (other.fantasia != null)
				return false;
		} else if (!fantasia.equals(other.fantasia))
			return false;
		if (fonCel == null) {
			if (other.fonCel != null)
				return false;
		} else if (!fonCel.equals(other.fonCel))
			return false;
		if (foneFixo == null) {
			if (other.foneFixo != null)
				return false;
		} else if (!foneFixo.equals(other.foneFixo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public Long getDocumento() {
		return documento;
	}

	public List<Compra> getCompra() {
		return Compra;
	}

	public void setCompra(List<Compra> compra) {
		Compra = compra;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFonCel() {
		return fonCel;
	}

	public void setFonCel(String fonCel) {
		this.fonCel = fonCel;
	}

	public String getFoneFixo() {
		return foneFixo;
	}

	public void setFoneFixo(String foneFixo) {
		this.foneFixo = foneFixo;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

}
