package br.com.cdl.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Parcela {

	private BigDecimal valorParcela;
	private Date dataVencimento;

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public String toString() {
		return "Parcela [valorParcela=" + valorParcela + ", dataVencimento=" + dataVencimento + "]";
	}
	

}
