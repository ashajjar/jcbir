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

import com.hajjar.jcbir.data.model.SecUsers;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecUsers entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hajjar.jcbir.data.model.SecUsers
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SecUsersDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SecUsersDAO.class);
	// property constants
	public static final String USER_LOGINNAME = "userLoginname";
	public static final String USER_PASSWORD = "userPassword";
	public static final String NAME = "name";
	public static final String ENABLED = "enabled";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";

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

	public void save(SecUsers transientInstance) {
		log.debug("saving SecUsers instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecUsers persistentInstance) {
		log.debug("deleting SecUsers instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecUsers findById(java.lang.Integer id) {
		log.debug("getting SecUsers instance with id: " + id);
		try {
			SecUsers instance = (SecUsers) getCurrentSession().get(
					"com.hajjar.jcbir.data.model.SecUsers", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SecUsers> findByExample(SecUsers instance) {
		log.debug("finding SecUsers instance by example");
		try {
			List<SecUsers> results = (List<SecUsers>) getCurrentSession()
					.createCriteria("com.hajjar.jcbir.data.model.SecUsers")
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
		log.debug("finding SecUsers instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SecUsers as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SecUsers> findByUserLoginname(Object userLoginname) {
		return findByProperty(USER_LOGINNAME, userLoginname);
	}

	public List<SecUsers> findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List<SecUsers> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<SecUsers> findByEnabled(Object enabled) {
		return findByProperty(ENABLED, enabled);
	}

	public List<SecUsers> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<SecUsers> findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	public List findAll() {
		log.debug("finding all SecUsers instances");
		try {
			String queryString = "from SecUsers";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecUsers merge(SecUsers detachedInstance) {
		log.debug("merging SecUsers instance");
		try {
			SecUsers result = (SecUsers) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecUsers instance) {
		log.debug("attaching dirty SecUsers instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecUsers instance) {
		log.debug("attaching clean SecUsers instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SecUsersDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SecUsersDAO) ctx.getBean("SecUsersDAO");
	}
}