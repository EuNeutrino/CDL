package br.com.cdl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cdl.domain.Fornecedor;
import br.com.cdl.util.HibernateUtil;

public class FornecedorDAO {
	public void salvar(Fornecedor fornecedor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(fornecedor);
			transacao.commit();
		} catch (Exception ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			//sessao.flush();
			sessao.close();
		}
	}

	public void excluir(Fornecedor fornecedor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(fornecedor);
			transacao.commit();
		} catch (Exception ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	public void editar(Fornecedor fornecedor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(fornecedor);
			transacao.commit();
		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listar() throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(Fornecedor.class).addOrder(Order.asc("nome"));
			return criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listarPorNome(Fornecedor fornecedor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(fornecedor.getClass());
			return criteria.add(Restrictions.ilike("nome", fornecedor.getNome() + "%")).addOrder(Order.asc("nome")).list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	public Fornecedor listarPorCodigo(Fornecedor fornecedor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(fornecedor.getClass());
			return (Fornecedor) criteria.add(Restrictions.idEq(fornecedor.getId())).uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}
}
