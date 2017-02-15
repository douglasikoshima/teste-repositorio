package com.indracompany.actions;

import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.struts.action.Action;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		arg0.getServletContext().removeAttribute("actionsCache");
		System.out.println("actionsCache removed...");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		arg0.getServletContext().setAttribute("actionsCache", new ConcurrentHashMap<String, Action>(2000));
		System.out.println("actionsCache created...");
	}

}
