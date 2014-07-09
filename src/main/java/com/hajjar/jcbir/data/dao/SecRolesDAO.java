package com.hajjar.jcbir.data.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.hajjar.jcbir.data.model.SecRoles;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecRoles entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hajjar.jcbir.data.model.SecRoles
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SecRolesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SecRolesDAO.class);
	// property constants
	public static final String NAME = "name";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(SecRoles transientInstance) {
		log.debug("saving SecRoles instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecRoles persistentInstance) {
		log.debug("deleting SecRoles instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecRoles findById(java.lang.Integer id) {
		log.debug("getting SecRoles instance with id: " + id);
		try {
			SecRoles instance = (SecRoles) getCurrentSession().get(
					"com.hajjar.jcbir.data.model.SecRoles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SecRoles> findByExample(SecRoles instance) {
		log.debug("finding SecRoles instance by example");
		try {
			List<SecRoles> results = (List<SecRoles>) getCurrentSession()
					.createCriteria("com.hajjar.jcbir.data.model.SecRoles")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SecRoles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SecRoles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SecRoles> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all SecRoles instances");
		try {
			String queryString = "from SecRoles";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecRoles merge(SecRoles detachedInstance) {
		log.debug("merging SecRoles instance");
		try {
			SecRoles result = (SecRoles) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecRoles instance) {
		log.debug("attaching dirty SecRoles instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecRoles instance) {
		log.debug("attaching clean SecRoles instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SecRolesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SecRolesDAO) ctx.getBean("SecRolesDAO");
	}
}