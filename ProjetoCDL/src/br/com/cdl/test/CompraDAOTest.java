package br.com.cdl.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.cdl.dao.CompraDAO;
import br.com.cdl.dao.CompraParcelaDAO;
import br.com.cdl.domain.Compra;
import br.com.cdl.domain.CompraParcela;
import br.com.cdl.domain.Fornecedor;
import br.com.cdl.domain.Funcionario;
import br.com.cdl.domain.Parcela;
import br.com.cdl.domain.Unidade;
import br.com.cdl.domain.Usuario;
import br.com.cdl.util.CalculaParcela;

public class CompraDAOTest {
	@Test
	@Ignore
	public void salvar() throws Exception {
		CompraDAO dao = new CompraDAO();
		Usuario usuario = new Usuario();
		usuario.setId(999L);

		Date data = new Date(System.currentTimeMillis());

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(1L);

		Funcionario funcionario = new Funcionario();
		funcionario.setId(1L);

		Unidade unidade = new Unidade();
		unidade.setId(1L);

		Compra c = new Compra();
		c.setDataCadastro(data);
		c.setFornecedor(fornecedor);
		c.setFuncionario(funcionario);
		c.setUnidade(unidade);
		c.setValorCompra(new BigDecimal("345.67"));

		c.setUsuario(usuario);
		c.setAutorizacao(10951L);
		c.setDataCompra(data);
		c.setParcelas(10L);

		dao.salvar(c);
		dao.listar();

	}

	@Test
	@Ignore
	public void listar() throws Exception {
		CompraDAO dao = new CompraDAO();
		dao.listar();

	}

	@Test
	@Ignore
	public void parcela() throws Exception {
		Compra compra = new Compra();
		compra.setId(5L);
		CompraDAO compraDAO = new CompraDAO();
		compra = compraDAO.listarPorCodigo(compra);

		CompraParcelaDAO parcelaDAO = new CompraParcelaDAO();
		CalculaParcela p = new CalculaParcela();

		List<Parcela> lista = p.gerarParcelas(new BigDecimal(compra.getValorCompra().toString()),
				new BigDecimal(compra.getParcelas().toString()), new Date(System.currentTimeMillis()));

		for (int i = 0; i < lista.size(); i++) {
			int x = i + 1;
			Long par = new Long(x);

			CompraParcela parcela = new CompraParcela();
			parcela.setCompra(compra);
			parcela.setDataCompra(compra.getDataCompra());
			parcela.setParcela(par);
			parcela.setStatus("AB");
			parcela.setValorParcela(lista.get(i).getValorParcela());
			parcela.setDataVencimento(lista.get(i).getDataVencimento());
			parcela.setFuncionario(compra.getFuncionario());

			parcelaDAO.salvar(parcela);

		}

	}

	@Test
	public void pesquisarParcela() throws Exception {

		CompraParcelaDAO parcelaDAO = new CompraParcelaDAO();
		Compra compra = new Compra();
		compra.setId(2L);

		List<CompraParcela> parcelamento = parcelaDAO.listarPorCodigoCompra(compra);
		for (int i = 0; i < parcelamento.size(); i++) {
			System.out.println("parcela: " + parcelamento.get(i).getParcela() + " - Valor da parcelas: "
					+ parcelamento.get(i).getValorParcela());

		}

	}

}
