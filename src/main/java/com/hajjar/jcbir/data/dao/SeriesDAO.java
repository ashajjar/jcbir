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

import com.hajjar.jcbir.data.model.Series;

/**
 * A data access object (DAO) providing persistence and search support for
 * Series entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hajjar.jcbir.data.model.Series
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SeriesDAO {
	private static final Logger log = LoggerFactory.getLogger(SeriesDAO.class);
	// property constants
	public static final String COMMENTS = "comments";

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

	public void save(Series transientInstance) {
		log.debug("saving Series instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Series persistentInstance) {
		log.debug("deleting Series instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Series findById(java.lang.Integer id) {
		log.debug("getting Series instance with id: " + id);
		try {
			Series instance = (Series) getCurrentSession().get(
					"com.hajjar.jcbir.data.model.Series", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Series> findByExample(Series instance) {
		log.debug("finding Series instance by example");
		try {
			List<Series> results = (List<Series>) getCurrentSession()
					.createCriteria("com.hajjar.jcbir.data.model.Series")
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
		log.debug("finding Series instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Series as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Series> findByComments(Object comments) {
		return findByProperty(COMMENTS, comments);
	}

	public List findAll() {
		log.debug("finding all Series instances");
		try {
			String queryString = "from Series";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Series merge(Series detachedInstance) {
		log.debug("merging Series instance");
		try {
			Series result = (Series) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Series instance) {
		log.debug("attaching dirty Series instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Series instance) {
		log.debug("attaching clean Series instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SeriesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SeriesDAO) ctx.getBean("SeriesDAO");
	}
}