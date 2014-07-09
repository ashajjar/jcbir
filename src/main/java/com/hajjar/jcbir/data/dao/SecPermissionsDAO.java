package com.hajjar.jcbir.data.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.hajjar.jcbir.data.model.SecPermissions;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecPermissions entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hajjar.jcbir.data.model.SecPermissions
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SecPermissionsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SecPermissionsDAO.class);
	// property constants
	public static final String PERMISSION_NAME = "permissionName";

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

	public void save(SecPermissions transientInstance) {
		log.debug("saving SecPermissions instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecPermissions persistentInstance) {
		log.debug("deleting SecPermissions instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecPermissions findById(java.lang.Integer id) {
		log.debug("getting SecPermissions instance with id: " + id);
		try {
			SecPermissions instance = (SecPermissions) getCurrentSession().get(
					"com.hajjar.jcbir.data.model.SecPermissions", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SecPermissions> findByExample(SecPermissions instance) {
		log.debug("finding SecPermissions instance by example");
		try {
			List<SecPermissions> results = (List<SecPermissions>) getCurrentSession()
					.createCriteria(
							"com.hajjar.jcbir.data.model.SecPermissions")
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
		log.debug("finding SecPermissions instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecPermissions as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SecPermissions> findByPermissionName(Object permissionName) {
		return findByProperty(PERMISSION_NAME, permissionName);
	}

	public List findAll() {
		log.debug("finding all SecPermissions instances");
		try {
			String queryString = "from SecPermissions";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecPermissions merge(SecPermissions detachedInstance) {
		log.debug("merging SecPermissions instance");
		try {
			SecPermissions result = (SecPermissions) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecPermissions instance) {
		log.debug("attaching dirty SecPermissions instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecPermissions instance) {
		log.debug("attaching clean SecPermissions instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SecPermissionsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SecPermissionsDAO) ctx.getBean("SecPermissionsDAO");
	}
}