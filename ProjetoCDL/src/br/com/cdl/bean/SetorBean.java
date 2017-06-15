package br.com.cdl.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cdl.dao.SetorDAO;
import br.com.cdl.domain.Setor;
import br.com.cdl.util.JSFUtil;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBsetor"))
@ViewScoped

public class SetorBean {

	private ArrayList<Setor> itens;
	private ArrayList<Setor> itensFiltrados;
	private Setor setor;

	public ArrayList<Setor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Setor> itens) {
		this.itens = itens;
	}

	public ArrayList<Setor> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Setor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	@PostConstruct
	public void prepararPesquisa() {
		SetorDAO dao = new SetorDAO();

		try {
			itens = (ArrayList<Setor>) dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		SetorDAO dao = new SetorDAO();
		try {
			System.out.println(setor.toString());
			dao.excluir(setor);
			JSFUtil.adiocionarMensagemSucesso("Setor removido com sucesso!");
			itens = (ArrayList<Setor>) dao.listar();
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void editar() {
		SetorDAO dao = new SetorDAO();
		try {

			dao.editar(setor);
			itens = (ArrayList<Setor>) dao.listar();
			JSFUtil.adiocionarMensagemSucesso("Setor editado com sucesso!");
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void prepararNovo() {
		setor = new Setor();
	}

	public void novo() {
		SetorDAO dao = new SetorDAO();
		try {
			dao.salvar(setor);
			JSFUtil.adiocionarMensagemSucesso("Setor salvo com sucesso!");
			itens = (ArrayList<Setor>) dao.listar();

		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());
		}

	}

}
