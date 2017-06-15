package br.com.cdl.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cdl.dao.PrefeituraDAO;
import br.com.cdl.domain.Prefeitura;
import br.com.cdl.util.JSFUtil;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBPrefeitura"))
@ViewScoped

public class PrefeituraBean {

	private ArrayList<Prefeitura> itens;
	private ArrayList<Prefeitura> itensFiltrados;
	private Prefeitura prefeitura;

	



	public ArrayList<Prefeitura> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Prefeitura> itens) {
		this.itens = itens;
	}

	public ArrayList<Prefeitura> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Prefeitura> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Prefeitura getPrefeitura() {
		return prefeitura;
	}

	public void setPrefeitura(Prefeitura prefeitura) {
		this.prefeitura = prefeitura;
	}

	@PostConstruct
	public void prepararPesquisa() {
		PrefeituraDAO dao  = new PrefeituraDAO();

		try {
			itens = (ArrayList<Prefeitura>) dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		PrefeituraDAO dao  = new PrefeituraDAO();
		try {
			System.out.println(prefeitura.toString());
			dao.excluir(prefeitura);
			JSFUtil.adiocionarMensagemSucesso("Prefeitura removido com sucesso!");
			itens = (ArrayList<Prefeitura>) dao.listar();
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void editar() {
		PrefeituraDAO dao  = new PrefeituraDAO();
		try {

			dao.editar(prefeitura);
			itens = (ArrayList<Prefeitura>) dao.listar();
			JSFUtil.adiocionarMensagemSucesso("Prefeitura editado com sucesso!");
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void prepararNovo() {
		prefeitura =  new Prefeitura();
	}

	public void novo() {
		PrefeituraDAO dao  = new PrefeituraDAO();
		try {
			dao.salvar(prefeitura);
			JSFUtil.adiocionarMensagemSucesso("Prefeitura salvo com sucesso!");
			itens = (ArrayList<Prefeitura>) dao.listar();

		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());
		}

	}

}
