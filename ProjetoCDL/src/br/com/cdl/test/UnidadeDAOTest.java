package br.com.cdl.test;

import org.junit.Test;

import br.com.cdl.dao.PrefeituraDAO;
import br.com.cdl.domain.Prefeitura;

public class UnidadeDAOTest {
	@Test
	public void salvar() throws Exception {
		Prefeitura p = new Prefeitura();

		PrefeituraDAO pdao = new PrefeituraDAO();
		pdao.salvar(p);

		p.setNome("nome");
		p.setDocumento(111111L);
		
		
		
		p.setLogradouro("asmskdsajsadl");
		p.setBairro("BBBBBBBBBB");
		p.setCidade("BBBBBBBBBBBBb");
		p.setCep("55555");
		p.setUf("BA");
		
		pdao.salvar(p);

	}
}
