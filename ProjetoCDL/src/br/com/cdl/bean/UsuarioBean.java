package br.com.cdl.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cdl.dao.UsuarioDAO;
import br.com.cdl.domain.Usuario;
import br.com.cdl.util.JSFUtil;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBusuario"))
@ViewScoped

public class UsuarioBean {

	private ArrayList<Usuario> itens;
	private ArrayList<Usuario> itensFiltrados;
	private Usuario usuario;

	public ArrayList<Usuario> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Usuario> itens) {
		this.itens = itens;
	}

	public ArrayList<Usuario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Usuario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		UsuarioDAO dao = new UsuarioDAO();

		try {
			itens = (ArrayList<Usuario>) dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void excluir() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			System.out.println(usuario.toString());
			dao.excluir(usuario);
			JSFUtil.adiocionarMensagemSucesso("Usuário removido com sucesso!");
			itens = (ArrayList<Usuario>) dao.listar();
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void editar() {
		UsuarioDAO dao = new UsuarioDAO();
		try {

			dao.editar(usuario);
			itens = (ArrayList<Usuario>) dao.listar();
			JSFUtil.adiocionarMensagemSucesso("Usuário editado com sucesso!");
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void prepararNovo() {
		usuario = new Usuario();
	}

	public void novo() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			dao.salvar(usuario);
			JSFUtil.adiocionarMensagemSucesso("Usuário salvo com sucesso!");
			itens = (ArrayList<Usuario>) dao.listar();

		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());
		}

	}

}
