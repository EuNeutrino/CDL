package br.com.cdl.bean;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cdl.dao.FuncaoDAO;
import br.com.cdl.dao.FuncionarioDAO;
import br.com.cdl.dao.PrefeituraDAO;
import br.com.cdl.dao.SetorDAO;
import br.com.cdl.domain.Funcao;
import br.com.cdl.domain.Funcionario;
import br.com.cdl.domain.Prefeitura;
import br.com.cdl.domain.Setor;
import br.com.cdl.util.JSFUtil;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBfuncionario"))
@ViewScoped

public class FuncionarioBean {

	private ArrayList<Funcionario> itens;
	private ArrayList<Funcionario> itensFiltrados;
	private ArrayList<Funcao> comboFuncao;
	private ArrayList<Setor> comboSetor;
	private ArrayList<Prefeitura> comboPrefeitura;
	private Funcionario funcionario;

	public ArrayList<Funcao> getComboFuncao() {
		return comboFuncao;
	}

	public ArrayList<Prefeitura> getComboPrefeitura() {
		return comboPrefeitura;
	}

	public void setComboPrefeitura(ArrayList<Prefeitura> comboPrefeitura) {
		this.comboPrefeitura = comboPrefeitura;
	}

	public void setComboFuncao(ArrayList<Funcao> comboFuncao) {
		this.comboFuncao = comboFuncao;
	}

	public ArrayList<Setor> getComboSetor() {
		return comboSetor;
	}

	public void setComboSetor(ArrayList<Setor> comboSetor) {
		this.comboSetor = comboSetor;
	}

	public ArrayList<Funcionario> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Funcionario> itens) {
		this.itens = itens;
	}

	public ArrayList<Funcionario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Funcionario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@PostConstruct
	public void prepararPesquisa() {
		FuncionarioDAO dao = new FuncionarioDAO();

		try {
			itens = (ArrayList<Funcionario>) dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		FuncionarioDAO dao = new FuncionarioDAO();
		try {
			System.out.println(funcionario.toString());
			dao.excluir(funcionario);
			JSFUtil.adiocionarMensagemSucesso("Funcionário removido com sucesso!");
			itens = (ArrayList<Funcionario>) dao.listar();
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void editar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		try {

			dao.editar(funcionario);
			itens = (ArrayList<Funcionario>) dao.listar();
			JSFUtil.adiocionarMensagemSucesso("Funcionário editado com sucesso!");
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void prepararNovo() {
		funcionario = new Funcionario();
		try {
			SetorDAO sdao = new SetorDAO();
			FuncaoDAO fdao = new FuncaoDAO();
			PrefeituraDAO pdao = new PrefeituraDAO();
			comboFuncao = (ArrayList<Funcao>) fdao.listar();
			comboSetor = (ArrayList<Setor>) sdao.listar();
			comboPrefeitura= (ArrayList<Prefeitura>) pdao.listar();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void novo() {
		FuncionarioDAO dao = new FuncionarioDAO();
		try {

			dao.salvar(funcionario);
			JSFUtil.adiocionarMensagemSucesso("Funcionário salvo com sucesso!");
			itens = (ArrayList<Funcionario>) dao.listar();

		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());
		}

	}

	public void prepaparEditar() {
		try {
			SetorDAO sdao = new SetorDAO();
			FuncaoDAO fdao = new FuncaoDAO();
			PrefeituraDAO pdao = new PrefeituraDAO();
			comboFuncao = (ArrayList<Funcao>) fdao.listar();
			comboSetor = (ArrayList<Setor>) sdao.listar();
			comboPrefeitura =  (ArrayList<Prefeitura>) pdao.listar();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
