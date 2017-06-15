package br.com.cdl.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.cdl.dao.UsuarioDAO;
import br.com.cdl.domain.Usuario;
import br.com.cdl.util.JSFUtil;

@ManagedBean(name = ("MBlogado"))
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuarioLogado;
	static Usuario UsuarioFinal;

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			usuarioLogado = new Usuario();
		}

		UsuarioFinal = usuarioLogado;
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String entrar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario = dao.autenticar(usuarioLogado);

			usuarioLogado = usuario;
			setUsuarioLogado(usuario);

			if (usuarioLogado == null) {
				JSFUtil.adiocionarMensagemErro("Código de usuário e ou senha inválidos!");
				return null;
			} else {

				JSFUtil.adiocionarMensagemSucesso("Usuario autenticado com sucesso!");
				return "/pages/principal.xhtml?faces-redirect=true";
			}

		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro("Erro ao tentar entraro no sitema! " + ex.getMessage());
			return null;
		}
	}

	public String sair() {
		usuarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}

}
