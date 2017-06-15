package br.com.cdl.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.cdl.dao.UsuarioDAO;
import br.com.cdl.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	public void salvar() throws Exception {
		Usuario u = new Usuario();
		u.setId(1L);
		u.setSenha("113414");

		UsuarioDAO dao = new UsuarioDAO();
		Usuario a = dao.autenticar(u);
		System.out.println(a.toString());
		Assert.assertNotNull(a);

	}
}