package br.com.vivo.security; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserPrincipal { 
    
    static private String msgNullSession = "Sessão nula ou inválida";
    static private String strUserID = "usuarioIdSession";
    
    static public String getUserID(HttpServletRequest request) throws NullPointerException {
        if (request == null) throw new NullPointerException(msgNullSession);
        HttpSession session = request.getSession();
        if (session == null) throw new NullPointerException(msgNullSession);
        String user = (String)session.getAttribute(strUserID);
        if ((user == null) || (user.trim().length() == 0)) throw new NullPointerException(msgNullSession);
        return user;
    }
        
    static public void setUserID(HttpServletRequest request, String userID) throws NullPointerException {
        HttpSession session = request.getSession();
        if (session == null) throw new NullPointerException(msgNullSession);
        session.setAttribute(strUserID, userID);
    }

} 
