package br.com.cdl.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cdl.dao.UnidadeDAO;
import br.com.cdl.domain.Unidade;
import br.com.cdl.util.JSFUtil;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBunidade"))
@ViewScoped

public class UnidadeBean {

	private ArrayList<Unidade> itens;
	private ArrayList<Unidade> itensFiltrados;
	private Unidade unidade;

	public ArrayList<Unidade> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Unidade> itens) {
		this.itens = itens;
	}

	public ArrayList<Unidade> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Unidade> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@PostConstruct
	public void prepararPesquisa() {
		UnidadeDAO dao = new UnidadeDAO();
		try {
			itens = (ArrayList<Unidade>) dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		UnidadeDAO dao = new UnidadeDAO();
		try {
			System.out.println(unidade.toString());
			dao.excluir(unidade);
			JSFUtil.adiocionarMensagemSucesso("Unidade removido com sucesso!");
			itens = (ArrayList<Unidade>) dao.listar();
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void editar() {
		UnidadeDAO dao = new UnidadeDAO();
		try {

			dao.editar(unidade);
			itens = (ArrayList<Unidade>) dao.listar();
			JSFUtil.adiocionarMensagemSucesso("Unidade editado com sucesso!");
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void prepararNovo() {
		unidade = new Unidade();
	}

	public void novo() {
		UnidadeDAO dao = new UnidadeDAO();
		try {
			dao.salvar(unidade);
			JSFUtil.adiocionarMensagemSucesso("Unidade salvo com sucesso!");
			itens = (ArrayList<Unidade>) dao.listar();

		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());
		}

	}

}
