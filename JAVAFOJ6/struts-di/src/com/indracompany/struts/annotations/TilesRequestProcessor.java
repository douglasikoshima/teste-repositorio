package com.indracompany.struts.annotations;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.RequestUtils;

public class TilesRequestProcessor extends org.apache.struts.tiles.TilesRequestProcessor {

	@Override
	protected Action processActionCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws IOException {

		String keyName = request.getSession().getId()+"__"+mapping.getType();

		Action instance;

		synchronized (actions) {

			boolean isNewInstance = (actions.get(keyName) == null);

			instance = processActionCreate0(request, response, mapping);

			if (isNewInstance) {
				InjectionHelper.injectEJB(instance, instance.getClass());
			}

			System.out.println("Instance: " + instance.hashCode() + " Type: " + mapping.getType());
		}

		return instance;
	}

	@SuppressWarnings("unchecked")
	protected Action processActionCreate0(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws IOException {

		String className = mapping.getType();
		String keyName = request.getSession().getId()+"__"+mapping.getType();

		Action instance = null;
		synchronized (actions) {

			instance = (Action) actions.get(keyName);
			if (instance != null) {
				return (instance);
			}

			try {
				instance = (Action) RequestUtils.applicationInstance(className);

			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, getInternal().getMessage("actionCreate", mapping.getPath()));
				return (null);
			}

			instance.setServlet(this.servlet);
			actions.put(keyName, instance);
		}
		return (instance);
	}
}
