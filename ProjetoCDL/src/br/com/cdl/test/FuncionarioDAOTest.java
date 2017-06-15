package br.com.cdl.test;

import org.junit.Test;

import br.com.cdl.dao.FuncionarioDAO;
import br.com.cdl.domain.Funcionario;
import br.com.cdl.domain.Setor;

public class FuncionarioDAOTest {
	@Test
	public void salvar() throws Exception {

		Funcionario f = new Funcionario();
		FuncionarioDAO fd = new FuncionarioDAO();
		Funcionario david = new Funcionario();
		
		f.setId(1L);
		david=  fd.listarPorCodigo(f);
		

		Setor setor = new Setor();
		setor.setId(4L);
		
		
		david.setSetor(setor);

		fd.salvar(david);

	}

}