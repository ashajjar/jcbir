package com.hajjar.jcbir.gui;

import com.hajjar.jcbir.gui.data.DataProvider;
import com.hajjar.jcbir.security.JCBIRSecurityManager;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("jcbir")
public class MainUI extends UI {

	private JCBIRSecurityManager secManager;
	private DataProvider dataProvider;
	
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		layout.addComponent(button);
	}

	public JCBIRSecurityManager getSecManager() {
		return secManager;
	}

	public void setSecManager(JCBIRSecurityManager secManager) {
		this.secManager = secManager;
	}

	public DataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

}