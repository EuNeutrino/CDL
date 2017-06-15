package br.com.cdl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cdl.domain.Unidade;
import br.com.cdl.util.HibernateUtil;

public class UnidadeDAO {
	public void salvar(Unidade unidade) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(unidade);
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

	public void excluir(Unidade unidade) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(unidade);
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

	public void editar(Unidade unidade) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(unidade);
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
	public List<Unidade> listar() throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(Unidade.class).addOrder(Order.asc("nome"));
			return criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Unidade> listarPorNome(Unidade unidade) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(unidade.getClass());
			return criteria.add(Restrictions.ilike("nome", unidade.getNome() + "%")).addOrder(Order.asc("nome")).list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	public Unidade listarPorCodigo(Unidade unidade) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(unidade.getClass());
			return (Unidade) criteria.add(Restrictions.idEq(unidade.getId())).uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}
}
