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

import com.hajjar.jcbir.data.model.ImageFeature;

/**
 * A data access object (DAO) providing persistence and search support for
 * ImageFeature entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hajjar.jcbir.data.model.ImageFeature
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ImageFeatureDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ImageFeatureDAO.class);
	// property constants
	public static final String VALUE = "value";

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

	public void save(ImageFeature transientInstance) {
		log.debug("saving ImageFeature instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ImageFeature persistentInstance) {
		log.debug("deleting ImageFeature instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ImageFeature findById(java.lang.Integer id) {
		log.debug("getting ImageFeature instance with id: " + id);
		try {
			ImageFeature instance = (ImageFeature) getCurrentSession().get(
					"com.hajjar.jcbir.data.model.ImageFeature", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ImageFeature> findByExample(ImageFeature instance) {
		log.debug("finding ImageFeature instance by example");
		try {
			List<ImageFeature> results = (List<ImageFeature>) getCurrentSession()
					.createCriteria("com.hajjar.jcbir.data.model.ImageFeature")
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
		log.debug("finding ImageFeature instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ImageFeature as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ImageFeature> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all ImageFeature instances");
		try {
			String queryString = "from ImageFeature";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ImageFeature merge(ImageFeature detachedInstance) {
		log.debug("merging ImageFeature instance");
		try {
			ImageFeature result = (ImageFeature) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ImageFeature instance) {
		log.debug("attaching dirty ImageFeature instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ImageFeature instance) {
		log.debug("attaching clean ImageFeature instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ImageFeatureDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ImageFeatureDAO) ctx.getBean("ImageFeatureDAO");
	}
}