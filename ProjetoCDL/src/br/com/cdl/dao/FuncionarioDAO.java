package br.com.cdl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cdl.domain.Funcionario;
import br.com.cdl.util.HibernateUtil;

public class FuncionarioDAO {
	public void salvar(Funcionario funcionario) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
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

	public void excluir(Funcionario funcionario) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
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

	public void editar(Funcionario funcionario) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(funcionario);
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
	public List<Funcionario> listar() throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(Funcionario.class).addOrder(Order.asc("nome"));
			return criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listarPorNome(Funcionario funcionario) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(funcionario.getClass());
			return criteria.add(Restrictions.ilike("nome", funcionario.getNome() + "%")).addOrder(Order.asc("nome")).list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}

	public Funcionario listarPorCodigo(Funcionario funcionario) throws Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = sessao.createCriteria(funcionario.getClass());
			return (Funcionario) criteria.add(Restrictions.idEq(funcionario.getId())).uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.flush();
			sessao.close();
		}
	}
}
