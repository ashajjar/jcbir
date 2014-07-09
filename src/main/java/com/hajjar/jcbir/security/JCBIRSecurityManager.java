package com.hajjar.jcbir.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import com.google.gwt.dev.util.collect.HashMap;
//import com.mobinets.fm.data.dao.SecPermissionsDAO;
//import com.mobinets.fm.data.dao.SecRolesDAO;
//import com.mobinets.fm.data.model.SecPermissions;
//import com.mobinets.fm.data.model.SecRoles;

public class JCBIRSecurityManager {
	private static JCBIRSecurityManager instance;
	private AuthenticationManager authenticationManager;
//	private SecPermissionsDAO secPermissionsDAO;
//	private SecRolesDAO secRolesDAO;
	
	public static JCBIRSecurityManager getInstance() {
		if (instance == null) {
			instance = new JCBIRSecurityManager();
		}
		return instance;
	}

	private HashMap<Integer, ArrayList<String>> permissionsMap=new HashMap<Integer, ArrayList<String>>();
	private HashMap<String,Integer> rolesMap=new HashMap<String,Integer>();
	
	private JCBIRSecurityManager(){}
	
//	public void init() {
//		loadRoles();
//		loadPermissions();
//	}

//	private void loadRoles() {
//		List<?> list=secRolesDAO.findAll();
//		for (Object object : list) {
//			SecRoles roles=(SecRoles)object;
//			rolesMap.put(roles.getName(),roles.getRoleId());
//		}
//	}
	
//	private void loadPermissions() {
//		List<?> list=secPermissionsDAO.findAll();
//		for (Object object : list) {
//			SecPermissions permissions=(SecPermissions)object;
//			if(permissionsMap.get(permissions.getSecRoles().getRoleId())==null){
//				ArrayList<String> permList=new ArrayList<String>();
//				permList.add(permissions.getPermissionName());
//				permissionsMap.put(permissions.getSecRoles().getRoleId(), permList);
//			}
//			else{
//				permissionsMap.get(permissions.getSecRoles().getRoleId()).add(permissions.getPermissionName());
//			}
//		}
//	}
	
	public boolean isAdmin(User user) {
		return hasRole(user, "ROLE_ADMIN");
	}
	
	public boolean userHasPermission(User user,String permission){
		Collection<GrantedAuthority> authorties= user.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorties) {
			String role=grantedAuthority.getAuthority();
			if(roleHasPermission(role, permission))
				return true;
		}
		return false;
	}
	
	public boolean roleHasPermission(String role,String permission) {
		Integer roleId=rolesMap.get(role);
		if(roleId==null)
			return false;
		
		ArrayList<String> permList=permissionsMap.get(roleId);
		if(permList==null)
			return false;
		
		if(!permList.contains(permission))
			return false;
		
		return true;
	}
	
	public boolean hasRole(User user, String role) {
		return user.getAuthorities().contains(new SimpleGrantedAuthority(role));
	}

	public String getCurrentUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication()
				.isAuthenticated();
	}

	public void setAuthenticated(boolean isAuthenticated) {
		SecurityContextHolder.getContext().getAuthentication()
				.setAuthenticated(isAuthenticated);
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean login(String username, String password) throws Exception {
		Authentication returned = getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
		// Authenticate
		if (returned == null) {
			throw new Exception("Not logged in");
		}
		if (!returned.isAuthenticated()) {
			throw new Exception("Invalid username and/or password");
		}
		SecurityContextHolder.getContext().setAuthentication(returned);
		return true;
	}

	public String toSHA1(byte[] convertme) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return byteArrayToHexString(md.digest(convertme));
	}

	public String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

//	public SecPermissionsDAO getSecPermissionsDAO() {
//		return secPermissionsDAO;
//	}
//
//	public void setSecPermissionsDAO(SecPermissionsDAO secPermissionsDAO) {
//		this.secPermissionsDAO = secPermissionsDAO;
//	}
//
//	public SecRolesDAO getSecRolesDAO() {
//		return secRolesDAO;
//	}
//
//	public void setSecRolesDAO(SecRolesDAO secRolesDAO) {
//		this.secRolesDAO = secRolesDAO;
//	}

}
