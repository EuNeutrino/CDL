package br.com.cdl.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.cdl.domain.Parcela;

public class CalculaParcela {
	private ArrayList<Parcela> lista = new ArrayList<Parcela>();

	private void calculaParcelas(BigDecimal compra, BigDecimal parcelas, Date Compra) {

		BigDecimal valorTotal = compra;
		BigDecimal numeroParcelas = parcelas;
		BigDecimal valorParcelas;
		BigDecimal diferenca;
		Date vencimento = Compra;

		for (int i = 0; i < numeroParcelas.intValue(); i++) {

			valorParcelas = valorTotal.divide(numeroParcelas, 2, RoundingMode.HALF_UP);
			vencimento = calculaVencimento(vencimento);

			Parcela p = new Parcela();
			p.setValorParcela(valorParcelas);
			p.setDataVencimento(vencimento);
			lista.add(p);

		}

		Double parcial = 0.00;

		for (int i = 0; i < lista.size(); i++) {
			parcial = parcial + lista.get(i).getValorParcela().doubleValue();

		}

		System.out.println("Valor Totall: " + valorTotal);

		BigDecimal outro = new BigDecimal(parcial.doubleValue());

		System.out.println("Outro: " + (outro.setScale(2, RoundingMode.HALF_EVEN)));

		diferenca = valorTotal.subtract(outro.setScale(2, RoundingMode.HALF_EVEN));

		System.out.println("Diferença: " + diferenca);

		if (diferenca.signum() < 0) {
			System.out.println("Diferença mennor que 0=" + diferenca);
			valorParcelas = lista.get(lista.size() - 1).getValorParcela();
			lista.remove(lista.get(lista.size() - 1));

			Parcela p = new Parcela();

			p.setValorParcela(valorParcelas.add(diferenca).setScale(2, RoundingMode.HALF_EVEN));
			p.setDataVencimento(new Date(System.currentTimeMillis()));
			lista.add(p);

		}

		if (diferenca.signum() > 0) {
			System.out.println("Diferença MAIOR que 0=" + diferenca);
			valorParcelas = lista.get(0).getValorParcela();
			lista.remove(0);

			Parcela p = new Parcela();

			p.setValorParcela(valorParcelas.add(diferenca).setScale(2, RoundingMode.HALF_EVEN));
			p.setDataVencimento(new Date(System.currentTimeMillis()));
			lista.add(0, p);

		}

		for (int i = 0; i < lista.size(); i++) {

			System.out.println("VALOR FINAL DA PARCELA>>>>  +" + lista.get(i).toString());

		}

	}

	public List<Parcela> gerarParcelas(BigDecimal compra, BigDecimal parcelas, Date data) {
		calculaParcelas(compra, parcelas, data);
		return lista;

	}

	@Test
	@Ignore
	public void teste() {
		Date data = new Date(System.currentTimeMillis());

		Date nova = calculaVencimento(data);
		System.out.println("Venciment0=" + nova.toString());

	}

	@Test
	public void teste2() {
		gerarParcelas(new BigDecimal("100"), new BigDecimal("10"), new Date(System.currentTimeMillis()));

	}

	public Date calculaVencimento(Date dataCompra) {

		Calendar c = Calendar.getInstance();
		c.setTime(dataCompra);
		c.add(Calendar.DATE, 30);
		Date mesSeguinte = c.getTime();

		return mesSeguinte;

	}
}
