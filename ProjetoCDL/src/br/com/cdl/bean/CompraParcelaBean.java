package br.com.cdl.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cdl.dao.CompraParcelaDAO;
import br.com.cdl.domain.CompraParcela;
import br.com.cdl.util.JSFUtil;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBparcelamento"))
@ViewScoped

public class CompraParcelaBean {

	private ArrayList<CompraParcela> itens;
	private ArrayList<CompraParcela> itensFiltrados;
	private CompraParcela compraParcela;

	
	@PostConstruct
	public void prepararPesquisa() {
		CompraParcelaDAO dao = new CompraParcelaDAO();
		try {
			itens = (ArrayList<CompraParcela>) dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}
	public ArrayList<CompraParcela> getItens() {
		return itens;
	}

	public void setItens(ArrayList<CompraParcela> itens) {
		this.itens = itens;
	}

	public ArrayList<CompraParcela> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<CompraParcela> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public CompraParcela getCompraParcela() {
		return compraParcela;
	}

	public void setCompraParcela(CompraParcela compraParcela) {
		this.compraParcela = compraParcela;
	}

	public void prepararNovo() {
		// funcao = new Funcao();
	}

}
