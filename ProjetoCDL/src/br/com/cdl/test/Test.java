package br.com.cdl.test;

import br.com.cdl.dao.FuncionarioDAO;
import br.com.cdl.domain.Funcionario;
import br.com.cdl.domain.Setor;

public class Test {
	public void salvar() throws Exception {
		
		Funcionario f = new Funcionario();
		f.setId(1L);
		
		Setor setor = new Setor();
		setor.setId(1L);
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		fdao.salvar(f);
		
	}

}
