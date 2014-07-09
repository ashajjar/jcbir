package com.hajjar.jcbir.gui.data;

import com.hajjar.jcbir.data.dao.FeatureDAO;
import com.hajjar.jcbir.data.dao.ImageDAO;
import com.hajjar.jcbir.data.dao.PatientDAO;
import com.hajjar.jcbir.data.dao.SecPermissionsDAO;
import com.hajjar.jcbir.data.dao.SecRolesDAO;
import com.hajjar.jcbir.data.dao.SecUsersDAO;
import com.hajjar.jcbir.data.dao.SeriesDAO;

public class DataProvider {
	
	private FeatureDAO featureDAO;
	private ImageDAO imageDAO;
	private PatientDAO patientDAO;
	private SecPermissionsDAO secPermissionsDAO;
	private SecRolesDAO secRolesDAO;
	private SecUsersDAO secUsersDAO;
	private SeriesDAO seriesDAO;
	
	public FeatureDAO getFeatureDAO() {
		return featureDAO;
	}
	public void setFeatureDAO(FeatureDAO featureDAO) {
		this.featureDAO = featureDAO;
	}
	public ImageDAO getImageDAO() {
		return imageDAO;
	}
	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}
	public PatientDAO getPatientDAO() {
		return patientDAO;
	}
	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}
	public SecPermissionsDAO getSecPermissionsDAO() {
		return secPermissionsDAO;
	}
	public void setSecPermissionsDAO(SecPermissionsDAO secPermissionsDAO) {
		this.secPermissionsDAO = secPermissionsDAO;
	}
	public SecRolesDAO getSecRolesDAO() {
		return secRolesDAO;
	}
	public void setSecRolesDAO(SecRolesDAO secRolesDAO) {
		this.secRolesDAO = secRolesDAO;
	}
	public SecUsersDAO getSecUsersDAO() {
		return secUsersDAO;
	}
	public void setSecUsersDAO(SecUsersDAO secUsersDAO) {
		this.secUsersDAO = secUsersDAO;
	}
	public SeriesDAO getSeriesDAO() {
		return seriesDAO;
	}
	public void setSeriesDAO(SeriesDAO seriesDAO) {
		this.seriesDAO = seriesDAO;
	}

	public void init() {
		
	}
}
