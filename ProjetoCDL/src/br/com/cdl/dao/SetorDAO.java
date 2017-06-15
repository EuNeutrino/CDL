package br.com.cdl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cdl.domain.Setor;
import br.com.cdl.util.HibernateUtil;

public class SetorDAO {
	public void salvar(Setor setor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(setor);
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

	public void excluir(Setor setor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(setor);
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

	public void editar(Setor setor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(setor);
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
	public List<Setor> listar() throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(Setor.class).addOrder(Order.asc("nome"));
			return criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Setor> listarPorNome(Setor setor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(setor.getClass());
			return criteria.add(Restrictions.ilike("nome", setor.getNome() + "%")).addOrder(Order.asc("nome")).list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	public Setor listarPorCodigo(Setor setor) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(setor.getClass());
			return (Setor) criteria.add(Restrictions.idEq(setor.getId())).uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}
}
