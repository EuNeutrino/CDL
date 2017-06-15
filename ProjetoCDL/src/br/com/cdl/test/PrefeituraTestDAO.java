package br.com.cdl.test;

import org.junit.Test;

import br.com.cdl.dao.PrefeituraDAO;
import br.com.cdl.domain.Prefeitura;

public class PrefeituraTestDAO {
	@Test
	public void salvar() throws Exception {
		Prefeitura p = new Prefeitura();

		PrefeituraDAO pdao = new PrefeituraDAO();
		

		p.setNome("nome");
		p.setDocumento(111111L);
		p.setLogradouro("asmskdsajsadl");
		p.setComplemento("XXXXXXX");
		p.setBairro("AAAAAA");

		p.setCidade("CIDADE");
		p.setEmail("email");
		p.setUf("BA");
		p.setCep("55555");
		p.setFonCel("11111");
		p.setFoneFixo("11111");
		
		

		pdao.salvar(p);

	}
}
