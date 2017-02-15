package com.indracompany.struts.annotations;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.RequestUtils;

public class RequestProcessor extends org.apache.struts.action.RequestProcessor {

    @Override
    protected Action processActionCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws IOException {

        String className = mapping.getType();
        String keyName = request.getSession().getId() + "__" + mapping.getType();

        @SuppressWarnings("unchecked")
        ConcurrentHashMap<String, Action> actions = (ConcurrentHashMap<String, Action>) request.getSession().getServletContext().getAttribute("actionsCache");

        Action instance = (Action) actions.get(keyName);
        if (instance == null) {
            try {
                Action action = (Action) RequestUtils.applicationInstance(className);
                action.setServlet(this.servlet);
                InjectionHelper.injectEJB(action, action.getClass());
                
                instance = actions.putIfAbsent(keyName, action);
                if(instance==null){
                    instance = action;
                }
                
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, getInternal().getMessage("actionCreate", mapping.getPath()));
                return (null);
            }
        }

        System.out.println("Instance: " + instance.hashCode() + " Type: " + mapping.getType());
        System.out.println("actions size: " + actions.size());

        return instance;
    }
}
