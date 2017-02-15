package com.indracompany.actions;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts.action.Action;

public class SessionListener implements HttpSessionListener {
    
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		clearActions(se);
	}

	private void clearActions(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		String id = session.getId();

		@SuppressWarnings("unchecked")
		ConcurrentHashMap<String, Action> actions = (ConcurrentHashMap<String, Action>) se.getSession().getServletContext().getAttribute("actionsCache");

		Set<Entry<String,Action>> entrySet = actions.entrySet();
		Iterator<Entry<String, Action>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<String, Action> entry = iterator.next();
			if (entry.getKey().indexOf(id) > -1) {
				iterator.remove();
				System.out.println(entry.getKey() + " removed....");
			}
		}
	}

}
