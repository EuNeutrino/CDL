package br.com.cdl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cdl.domain.Compra;
import br.com.cdl.util.HibernateUtil;

public class CompraDAO {
	public void salvar(Compra compra) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(compra);
			transacao.commit();
		} catch (Exception ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			// sessao.flush();
			sessao.close();
		}
	}

	public void excluir(Compra compra) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(compra);
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

	public void editar(Compra compra) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(compra);
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
	public List<Compra> listar() throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(Compra.class).addOrder(Order.asc("id"));
			return criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	public Compra listarPorCodigo(Compra compra) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(compra.getClass());
			return (Compra) criteria.add(Restrictions.idEq(compra.getId())).uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

}
