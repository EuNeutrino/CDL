package br.com.cdl.util;

import br.com.cdl.dao.UnidadeDAO;
import br.com.cdl.domain.Unidade;

public class GerarTabela {

	public static void main(String[] args) throws Exception {

		Unidade u = new Unidade();
		UnidadeDAO dao = new UnidadeDAO();
		u.setNome("UNIDADE CDL");
		u.setDocumento(12345678901234L);
		u.setLogradouro("RUA IGAPORA");
		u.setComplemento(null); // aceita nulo
		u.setBairro("MANGABEIRA	");
		u.setCidade("FEIRA DE SANTANA");
		u.setEmail(null);
		u.setUf("BA");
		u.setCep("44056734");
		u.setFonecel(null);
		u.setFonefixo(null);

		dao.salvar(u);
		System.out.println(u.toString());
		
	}

}
