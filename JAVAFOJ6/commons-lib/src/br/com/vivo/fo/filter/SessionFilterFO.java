package br.com.vivo.fo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

public class SessionFilterFO implements Filter {

    private transient Logger log = new Logger("start");

    private static String forwardTo;

    public void destroy() {
    } // End destroy.

    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("SessionFilterFO:init();");
        // Parametro vindo do web.xml, caminho do arquivo de sessão expirada.
        forwardTo = filterConfig.getInitParameter("forwardTo");
        log.debug("SessionFilterFO:init() --> forwardTo = " + forwardTo);
    } // End init().

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("SessionFilterFO:doFilter();");
        HttpServletRequest  req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Cache-Control","no-cache");
        res.setHeader("Pragma","no-cache");
        res.setHeader("Expires",ConstantesCRM.SZERO);
        // Se sessão não existe/Inválida.
        if(req.getRequestedSessionId() != null && !req.isRequestedSessionIdValid()) {
            log.debug("SessionFilterFO:doFilter()      -> SESSÃO EXPIRADA!");
            log.debug("SessionFilterFO:Session IdValid -> "+req.isRequestedSessionIdValid());
            log.debug("SessionFilterFO:Session Id      -> "+req.getSession().getId());
            log.debug("SessionFilterFO:IP Address      -> "+req.getRemoteAddr());
            log.debug("SessionFilterFO:IP X-Forward-For-> "+req.getHeader("X-Forwarded-For"));
            log.debug("SessionFilterFO:RequestURI      -> "+req.getRequestURI());
            log.debug("SessionFilterFO:Referer         -> "+req.getHeader("referer"));
            log.debug("SessionFilterFO:USUARIO_LOGIN   -> "+req.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));
            //RequestDispatcher rd = request.getRequestDispatcher(req.getContextPath() + forwardTo);
            // Redirecionando...
            res.sendRedirect(req.getContextPath() + forwardTo);
            return;
        }
        log.debug("SessionFilterFO:doFilter()      -> SESSÃO VÁLIDA!");
        log.debug("SessionFilterFO:Session IdValid -> "+req.isRequestedSessionIdValid());
        log.debug("SessionFilterFO:Session Id      -> "+req.getSession().getId());
        log.debug("SessionFilterFO:IP Address      -> "+req.getRemoteAddr());
        log.debug("SessionFilterFO:IP X-Forward-For-> "+req.getHeader("X-Forwarded-For"));
        log.debug("SessionFilterFO:RequestURI      -> "+req.getRequestURI());
        log.debug("SessionFilterFO:Referer         -> "+req.getHeader("referer"));
        log.debug("SessionFilterFO:USUARIO_LOGIN   -> "+req.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));
        filterChain.doFilter(request, res);
    } // End doFilter().
} // End class.
