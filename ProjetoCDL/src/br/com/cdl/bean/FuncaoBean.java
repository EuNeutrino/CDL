package br.com.cdl.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cdl.dao.FuncaoDAO;
import br.com.cdl.domain.Funcao;
import br.com.cdl.util.JSFUtil;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBfuncao"))
@ViewScoped

public class FuncaoBean {

	private ArrayList<Funcao> itens;
	private ArrayList<Funcao> itensFiltrados;
	private Funcao funcao;

	
	public ArrayList<Funcao> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Funcao> itens) {
		this.itens = itens;
	}

	public ArrayList<Funcao> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Funcao> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	@PostConstruct
	public void prepararPesquisa() {
		FuncaoDAO dao = new FuncaoDAO();

		try {
			itens = (ArrayList<Funcao>) dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		FuncaoDAO dao = new FuncaoDAO();
		try {
			System.out.println(funcao.toString());
			dao.excluir(funcao);
			JSFUtil.adiocionarMensagemSucesso("Funcao removido com sucesso!");
			itens = (ArrayList<Funcao>) dao.listar();
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void editar() {
		FuncaoDAO dao = new FuncaoDAO();
		try {

			dao.editar(funcao);
			itens = (ArrayList<Funcao>) dao.listar();
			JSFUtil.adiocionarMensagemSucesso("Função editado com sucesso!");
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void prepararNovo() {
		funcao = new Funcao();
	}

	public void novo() {
		FuncaoDAO dao = new FuncaoDAO();
		try {
			dao.salvar(funcao);
			JSFUtil.adiocionarMensagemSucesso("Funçao salvo com sucesso!");
			itens = (ArrayList<Funcao>) dao.listar();

		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());
		}

	}

}
