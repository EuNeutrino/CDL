package br.com.cdl.test;

import org.junit.Test;

import br.com.cdl.dao.FornecedorDAO;
import br.com.cdl.domain.Fornecedor;

public class FornecedorTestDAO {
	@Test
	public void salvar() throws Exception {
		Fornecedor p = new Fornecedor();

		FornecedorDAO fdao = new FornecedorDAO();
		

		p.setNome("nome");
		p.setFantasia("Fantasia");
		p.setDocumento(111111L);
		p.setLogradouro("asmskdsajsadl");
		p.setComplemento(null);
		p.setBairro("AAAAAA");

		p.setCidade("CIDADE");
		p.setEmail("email");
		p.setUf("BA");
		p.setCep("45.604-330");
		p.setFonCel(null);
		p.setFoneFixo(null);
		
		

		fdao.salvar(p);

	}
}
