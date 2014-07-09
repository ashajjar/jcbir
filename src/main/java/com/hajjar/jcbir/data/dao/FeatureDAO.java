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

import com.hajjar.jcbir.data.model.Feature;

/**
 * A data access object (DAO) providing persistence and search support for
 * Feature entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hajjar.jcbir.data.model.Feature
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FeatureDAO {
	private static final Logger log = LoggerFactory.getLogger(FeatureDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CATEGORY = "category";

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

	public void save(Feature transientInstance) {
		log.debug("saving Feature instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Feature persistentInstance) {
		log.debug("deleting Feature instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Feature findById(java.lang.Integer id) {
		log.debug("getting Feature instance with id: " + id);
		try {
			Feature instance = (Feature) getCurrentSession().get(
					"com.hajjar.jcbir.data.model.Feature", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Feature> findByExample(Feature instance) {
		log.debug("finding Feature instance by example");
		try {
			List<Feature> results = (List<Feature>) getCurrentSession()
					.createCriteria("com.hajjar.jcbir.data.model.Feature")
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
		log.debug("finding Feature instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Feature as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Feature> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Feature> findByCategory(Object category) {
		return findByProperty(CATEGORY, category);
	}

	public List findAll() {
		log.debug("finding all Feature instances");
		try {
			String queryString = "from Feature";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Feature merge(Feature detachedInstance) {
		log.debug("merging Feature instance");
		try {
			Feature result = (Feature) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Feature instance) {
		log.debug("attaching dirty Feature instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Feature instance) {
		log.debug("attaching clean Feature instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FeatureDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FeatureDAO) ctx.getBean("FeatureDAO");
	}
}