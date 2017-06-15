package br.com.cdl.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cdl.dao.FornecedorDAO;
import br.com.cdl.domain.Fornecedor;
import br.com.cdl.util.JSFUtil;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBfornecedor"))
@ViewScoped

public class FornecedorBean {

	private ArrayList<Fornecedor> itens;
	private ArrayList<Fornecedor> itensFiltrados;
	private Fornecedor fornecedor;

	public ArrayList<Fornecedor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedor> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedor> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@PostConstruct
	public void prepararPesquisa() {
		FornecedorDAO dao = new FornecedorDAO();

		try {
			itens = (ArrayList<Fornecedor>) dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		FornecedorDAO dao = new FornecedorDAO();
		try {
			System.out.println(fornecedor.toString());
			dao.excluir(fornecedor);
			JSFUtil.adiocionarMensagemSucesso("Fornecedor removido com sucesso!");
			itens = (ArrayList<Fornecedor>) dao.listar();
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void editar() {
		FornecedorDAO dao = new FornecedorDAO();
		try {

			dao.editar(fornecedor);
			itens = (ArrayList<Fornecedor>) dao.listar();
			JSFUtil.adiocionarMensagemSucesso("Fornecedor editado com sucesso!");
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void prepararNovo() {
		fornecedor = new Fornecedor();
	}

	public void novo() {
		FornecedorDAO dao = new FornecedorDAO();
		try {
			dao.salvar(fornecedor);
			JSFUtil.adiocionarMensagemSucesso("Fornecedor salvo com sucesso!");
			itens = (ArrayList<Fornecedor>) dao.listar();

		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());
		}

	}

}
