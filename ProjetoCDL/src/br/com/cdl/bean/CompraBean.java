package br.com.cdl.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.cdl.dao.CompraDAO;
import br.com.cdl.dao.CompraParcelaDAO;
import br.com.cdl.dao.FornecedorDAO;
import br.com.cdl.dao.FuncionarioDAO;
import br.com.cdl.domain.Compra;
import br.com.cdl.domain.CompraParcela;
import br.com.cdl.domain.Fornecedor;
import br.com.cdl.domain.Funcionario;
import br.com.cdl.domain.Parcela;
import br.com.cdl.domain.Unidade;
import br.com.cdl.domain.Usuario;
import br.com.cdl.util.CalculaParcela;
import br.com.cdl.util.HibernateUtil;
import br.com.cdl.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author David Nogueira
 */
@ManagedBean(name = ("MBcompra"))
@ViewScoped

public class CompraBean {

	private ArrayList<Compra> itens;
	private ArrayList<Compra> itensFiltrados;
	private ArrayList<Funcionario> comboFuncionario;
	private ArrayList<Fornecedor> comboFornecedor;

	private ArrayList<CompraParcela> parcelasFiltrados;
	private Compra compraSelecionada;

	private Compra compra;
	private CompraParcela parcela;
	private Date inicial;
	private Date fim;

	public Date getInicial() {
		return inicial;
	}

	public void setInicial(Date inicial) {
		this.inicial = inicial;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public ArrayList<Compra> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Compra> itens) {
		this.itens = itens;
	}

	public ArrayList<Compra> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Compra> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Funcionario> getComboFuncionario() {
		return comboFuncionario;
	}

	public void setComboFuncionario(ArrayList<Funcionario> comboFuncionario) {
		this.comboFuncionario = comboFuncionario;
	}

	public ArrayList<Fornecedor> getComboFornecedor() {
		return comboFornecedor;
	}

	public void setComboFornecedor(ArrayList<Fornecedor> comboFornecedor) {
		this.comboFornecedor = comboFornecedor;
	}

	public Compra getCompraSelecionada() {
		return compraSelecionada;
	}

	public void setCompraSelecionada(Compra compraSelecionada) {
		this.compraSelecionada = compraSelecionada;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public CompraParcela getParcela() {
		return parcela;
	}

	public void setParcela(CompraParcela parcela) {
		this.parcela = parcela;
	}

	public ArrayList<CompraParcela> getParcelasFiltrados() {
		return parcelasFiltrados;
	}

	public void setParcelasFiltrados(ArrayList<CompraParcela> parcelasFiltrados) {
		this.parcelasFiltrados = parcelasFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		CompraDAO dao = new CompraDAO();

		try {
			itens = (ArrayList<Compra>) dao.listar();

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adiocionarMensagemErro(e.getMessage());
		}

	}

	public void excluir() {
		CompraDAO dao = new CompraDAO();
		try {
			System.out.println(compra.toString());
			dao.excluir(compra);
			JSFUtil.adiocionarMensagemSucesso("Compra removido com sucesso!");
			itens = (ArrayList<Compra>) dao.listar();
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void editar() {
		CompraDAO dao = new CompraDAO();
		try {

			dao.editar(compra);
			itens = (ArrayList<Compra>) dao.listar();
			JSFUtil.adiocionarMensagemSucesso("Compra editado com sucesso!");
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());

		}

	}

	public void prepararNovo() {

		compra = new Compra();
		Unidade unidade = new Unidade();
		unidade.setId(1L);

		Usuario usuario = new Usuario();
		usuario.setId(AutenticacaoBean.UsuarioFinal.getId());

		Date data = new Date(System.currentTimeMillis());
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		formatador.format(data);

		compra.setUsuario(usuario);
		compra.setUnidade(unidade);
		compra.setDataCadastro(data);

		FuncionarioDAO fordao = new FuncionarioDAO();
		FornecedorDAO fundao = new FornecedorDAO();

		try {
			comboFuncionario = (ArrayList<Funcionario>) fordao.listar();
			comboFornecedor = (ArrayList<Fornecedor>) fundao.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void novo() {
		CompraDAO dao = new CompraDAO();
		CompraParcelaDAO parcelaDAO = new CompraParcelaDAO();
		CalculaParcela parcelas = new CalculaParcela();
		List<Parcela> lista = parcelas.gerarParcelas(new BigDecimal(compra.getValorCompra().toString()),
				new BigDecimal(compra.getParcelas().toString()), compra.getDataCompra());
		try {
			dao.salvar(compra);
			JSFUtil.adiocionarMensagemSucesso("Compra salvo com sucesso!");
			itens = (ArrayList<Compra>) dao.listar();

			for (int i = 0; i < lista.size(); i++) {
				int x = i + 1;
				Long par = new Long(x);

				parcela = new CompraParcela();
				parcela.setCompra(compra);
				parcela.setDataCompra(compra.getDataCompra());
				parcela.setParcela(par);
				parcela.setStatus("AB");
				parcela.setValorParcela(lista.get(i).getValorParcela());
				parcela.setDataVencimento(lista.get(i).getDataVencimento());
				parcela.setFuncionario(compra.getFuncionario());
				parcelaDAO.salvar(parcela);

			}
		} catch (Exception ex) {
			JSFUtil.adiocionarMensagemErro(ex.getMessage());
		}

	}

	public void prepaparEditar() {
		FuncionarioDAO fordao = new FuncionarioDAO();
		FornecedorDAO fundao = new FornecedorDAO();
		try {
			comboFuncionario = (ArrayList<Funcionario>) fordao.listar();
			comboFornecedor = (ArrayList<Fornecedor>) fundao.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void imprimir() throws IOException {

		String caminho = Faces.getRealPath("/reports/compraFuncionario.jasper");
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("DAT_INICIO", inicial);
		parametros.put("DATA_FINAL", fim);
		Connection conexao = HibernateUtil.getConexao();
		try {
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperViewer.viewReport(relatorio, true);
			// JasperPrintManager.printReport(relatorio, true);

		} catch (JRException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Compra Selected", ((Compra) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		CompraParcelaDAO pdao = new CompraParcelaDAO();
		try {
			parcelasFiltrados = (ArrayList<CompraParcela>) pdao.listarPorCodigoCompra(getCompraSelecionada());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Compra Unselected", ((Compra) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		parcelasFiltrados.clear();

	}

	

}
