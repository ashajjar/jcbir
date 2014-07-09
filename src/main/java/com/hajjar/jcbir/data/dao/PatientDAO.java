package com.hajjar.jcbir.data.dao;

import java.sql.Timestamp;
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

import com.hajjar.jcbir.data.model.Patient;

/**
 * A data access object (DAO) providing persistence and search support for
 * Patient entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hajjar.jcbir.data.model.Patient
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class PatientDAO {
	private static final Logger log = LoggerFactory.getLogger(PatientDAO.class);
	// property constants
	public static final String NAME = "name";
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

	public void save(Patient transientInstance) {
		log.debug("saving Patient instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Patient persistentInstance) {
		log.debug("deleting Patient instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Patient findById(java.lang.Integer id) {
		log.debug("getting Patient instance with id: " + id);
		try {
			Patient instance = (Patient) getCurrentSession().get(
					"com.hajjar.jcbir.data.model.Patient", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Patient> findByExample(Patient instance) {
		log.debug("finding Patient instance by example");
		try {
			List<Patient> results = (List<Patient>) getCurrentSession()
					.createCriteria("com.hajjar.jcbir.data.model.Patient")
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
		log.debug("finding Patient instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Patient as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Patient> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Patient> findByComments(Object comments) {
		return findByProperty(COMMENTS, comments);
	}

	public List findAll() {
		log.debug("finding all Patient instances");
		try {
			String queryString = "from Patient";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Patient merge(Patient detachedInstance) {
		log.debug("merging Patient instance");
		try {
			Patient result = (Patient) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Patient instance) {
		log.debug("attaching dirty Patient instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Patient instance) {
		log.debug("attaching clean Patient instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PatientDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PatientDAO) ctx.getBean("PatientDAO");
	}
}