package com.hajjar.jcbir.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class JCBIRNavigator extends Navigator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCBIRSecurityManager fmSecurityManager;
	
	public JCBIRNavigator(UI ui, ComponentContainer container) {
		super(ui, container);
		WebApplicationContext ctx=ContextLoader.getCurrentWebApplicationContext();
		fmSecurityManager=(JCBIRSecurityManager)ctx.getBean("fmSecManager");
	}
	
	@Override
	public void navigateTo(String navigationState) {		
		if(isAllowedToNavigate(fmSecurityManager.getCurrentUser(),navigationState)){
			super.navigateTo(navigationState);
		}
		else{
			super.navigateTo("/");
		}
	}

	private boolean isAllowedToNavigate(User user, String navigationState) {
		if(fmSecurityManager.isAdmin(user)){
			return true;
		}
		return fmSecurityManager.userHasPermission(user, navigationState);
	}

}
