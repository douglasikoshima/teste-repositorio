package com.indracompany.actions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import usuario.alterarSenha.AlterarSenhaController.AlterarSenhaForm;
import br.com.vivo.fo.acesso.ControlAcesso;
import br.com.vivo.fo.admsistemas.vo.LDAPVODocument.LDAPVO;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument.AbreProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloTODocument.AbreProtocoloTO;
import br.com.vivo.fo.atendimento.vo.FechaProtocoloTODocument.FechaProtocoloTO;
import br.com.vivo.fo.commons.utils.businessDelegate.JATMIBusinessDelegate;
import br.com.vivo.fo.commons.utils.properties.LoadProperties;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.protocolo.ProtocoloFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.usuario.acesso.ControleAcessoFacade;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ControleAcessoEnvioVODocument.ControleAcessoEnvioVO;
import br.com.vivo.fo.usuario.vo.MenuVODocument.MenuVO;
import br.com.vivo.fo.usuario.vo.RuleVODocument;
import br.com.vivo.fo.usuario.vo.RuleVODocument.RuleVO;
import br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO;
import br.com.vivo.fo.xml.XmlManager;
import br.com.vivo.ldap.LDAPUtil;
import br.com.vivo.ldap.exceptions.PasswordExpiredException;
import br.com.vivo.ldap.exceptions.PasswordExpiringException;

@SuppressWarnings("unchecked")
public class Controller extends AbstractAction {

    private static final long    serialVersionUID = 3298695415723737854L;

    @EJB
    private ControleAcessoFacade controlAcesso;

    @EJB
    private ManterSistemaFacade  controlSistema;

    @EJB
    private ProtocoloFacade      protocoloFacade;

    @EJB
    private TelaInicialFacade    telaInicialFacadeControl;

    private transient Logger     log              = new Logger(Controller.class.getName());

    private formSwitcher         formSwitcher;
    private FormInicio           formInicio       = new FormInicio();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);

        } else if ("consultaLinha".equals(mapping.getParameter())) {
            return consultaLinha(mapping, form, request, response);

        } else if ("generateToken".equals(mapping.getParameter())) {
            return generateToken(mapping, form, request, response);

        } else if ("autenticador".equals(mapping.getParameter())) {
            return autenticador(mapping, form, request, response);

        } else if ("meuCliente".equals(mapping.getParameter())) {
            return meuCliente(mapping, form, request, response);

        } else if ("voltar".equals(mapping.getParameter())) {
            return voltar(mapping, form, request, response);

        } else if ("autenticado".equals(mapping.getParameter())) {
            return autenticado(mapping, form, request, response);

        } else if ("retorna".equals(mapping.getParameter())) {
            return retorna(mapping, form, request, response);

        } else if ("about".equals(mapping.getParameter())) {
            return about(mapping, form, request, response);

        } else if ("login".equals(mapping.getParameter())) {
            return login(mapping, form, request, response);

        } else if ("logout".equals(mapping.getParameter())) {
            return logout(mapping, form, request, response);

        } else if ("restart".equals(mapping.getParameter())) {
            return restart(mapping, form, request, response);

        } else if ("submitToSwitcher".equals(mapping.getParameter())) {
            return submitToSwitcher(mapping, form, request, response);

		} else if ("valorSenha".equals(mapping.getParameter())) {
			return valorSenha(mapping, form, request, response);
			
        } else {
            return begin(mapping, form, request, response);
        }
    }
	
	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="error.jsp"
	 * @jpf:forward name="login" path="login.jsp"
	 */
	protected ActionForward valorSenha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			// Verifica se permite consultar a senha
			LoadProperties properties = new LoadProperties(request);
			String vs = properties.get("VALORSENHA");

			if (ConstantesCRM.SONE.equals(vs)) {
				String cdAreaRegistro = request.getParameter("ddd");
				String nrLinha = request.getParameter("fone");
				String idTipoRelacionamento = request.getParameter("tipoRel");

				String valorSenha = controlAcesso.obterValorSenha(ConstantesCRM.STWO,cdAreaRegistro, nrLinha, "15", idTipoRelacionamento);
				request.setAttribute("valorSenha", valorSenha);

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,this);
				return mapping.findForward(ConstantesCRM.SUCCESS);

			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			request.setAttribute("valorSenha", "ERRO ==> " + e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}		
	}			
	

    /**
     * @jpf:action
     * @jpf:forward name="error" path="error.jsp"
     * @jpf:forward name="login" path="login.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* ALTERAÇÃO DO CHART-SET PARA UTF-8 NAS CHAMADAS DE WEBSERVICE */
        System.setProperty("weblogic.webservice.i18n.charset", "utf-8");
        formSwitcher = new formSwitcher();
        formInicio = new FormInicio();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("login");
    }

    /**
     * @jpf:action
     * @jpf:forward name="inicio" path="index.jsp"
     * @jpf:forward name="login" path="login.jsp"
     * @jpf:forward name="erro" path="mensagem.jsp"
     */
    protected ActionForward consultaLinha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean success = false;
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        String nrLinha = request.getParameter("nrLinha");
        String nrProtocolo = request.getParameter("nrProtocolo");
        String protocoloInativo = request.getParameter("protocoloInativo");
        long cdArea = 0L;
        long nrTelefone = 0L;

        if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha.trim())) {
            if (nrLinha.length() == 10 || nrLinha.length() == 11) {
                try {
                    cdArea = Long.parseLong(nrLinha.substring(0, 2));
                    nrTelefone = Long.parseLong(nrLinha.substring(2));

                } catch (Exception e) {
                    success = false;
                }

            } else {
                success = false;
            }

        } else {
            success = false;
        }

        if (cdArea != 0L && nrTelefone != 0L) {
            request.setAttribute("consultaLinha", nrLinha);
            success = true;
        } else {
            success = false;
        }

        if (!success) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("erro");
        }

        if ((nrProtocolo == null || ConstantesCRM.SVAZIO.equals(nrProtocolo)) && success) {
            try {
                AbreProtocoloTO abreProtocoloTO = AbreProtocoloTO.Factory.newInstance();
                abreProtocoloTO.setIdTipoAberturaProtocolo(ConstantesCRM.STWO);
                abreProtocoloTO.setIdSistemaOrigem(ConstantesCRM.SSEVEN);
                abreProtocoloTO.setCdAreaRegistro(nrLinha.substring(0, 2));
                abreProtocoloTO.setNrTelefone(nrLinha.substring(2));
                AbreProtocoloOutTO out = protocoloFacade.setAbreValidaProtocolo(user, abreProtocoloTO);
                nrProtocolo = out.getNrProtocolo();
                request.setAttribute("consultaProtocolo", nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO);

            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        } else {
            request.setAttribute("consultaProtocolo", nrProtocolo);
            request.setAttribute("protocoloInativo", protocoloInativo);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("inicio");
    }

    /**
     * @jpf:action
     */
    protected ActionForward generateToken(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String apiPassword = ConstantesCRM.SVAZIO;
        String apiLogin = ConstantesCRM.SVAZIO;
        String apiURL = ConstantesCRM.SVAZIO;
        String retornoXML = "<xmlOUT>";
        String msgRetorno = ConstantesCRM.SVAZIO;
        String dsLogin = request.getParameter("dsLogin");

        Enumeration<String> enumx = request.getParameterNames();
        HashMap<String, String> parametros = new HashMap<String, String>();

        while (enumx.hasMoreElements()) {
            String attribName = enumx.nextElement();
            String attribValue = request.getParameter(attribName);

            if (attribName.equals("nrLinha")) {
                parametros.put("NRLINHA", request.getParameter("nrLinha").replaceAll("[^0-9]", ConstantesCRM.SVAZIO));

            } else if (attribName.equals("dsLogin")) {
                parametros.put("DSLOGIN", dsLogin);

            } else {
                parametros.put(attribName, attribValue);
            }
        }

        try {
            controlAcesso.carregaDadosUsuarioSessao("<login>" + dsLogin + "</login>");
        } catch (Exception e) {
            msgRetorno = "Usuário não encontrado no Vivonet.";
        }

        VerifySessionDAO autenticador = new VerifySessionDAO();
        try {
            apiPassword = telaInicialFacadeControl.getParametro(ConstantesCRM.SONE, "AUTENTICADOR_CAMPANHA_SENHA").getDsValorParametro();
            apiLogin = telaInicialFacadeControl.getParametro(ConstantesCRM.SONE, "AUTENTICADOR_CAMPANHA_LOGIN").getDsValorParametro();
            apiURL = telaInicialFacadeControl.getParametro(ConstantesCRM.SONE, "HOST_INTEGRADOR_CAMPANHA").getDsValorParametro();

        } catch (Exception e) {
            throw new Exception("Parâmetros insuficientes para criação de Token.");
        }

        parametros.put("APIPASSWORD", apiPassword);
        parametros.put("APILOGIN", apiLogin);
        parametros.put("APIURL", apiURL);

        try {
            if (!ConstantesCRM.SVAZIO.equals((msgRetorno))) {
                throw new Exception(msgRetorno);
            }
            HashMap<String, String> autenticacaoHash = autenticador.createSession(parametros);
            Object retorno = autenticacaoHash.get("retorno");
            if (retorno == null) {
                msgRetorno = "Houve um problema no Autenticador. Não foi possível a geração do Token.";
            } else if (ConstantesCRM.SZERO.equals(retorno)) {
                msgRetorno = "Token gerado com sucesso: ";
                msgRetorno += autenticacaoHash.get("token");
            }

        } catch (Exception e) {
            msgRetorno = e.getMessage();

        } finally {
            retornoXML += msgRetorno + "</xmlOUT>";
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType(ConstantesCRM.SContentType);
            bufferedOutputStream.write(retornoXML.getBytes(ConstantesCRM.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    /**
     * @jpf:action
     * @jpf:forward name="token" path="index_token_full.jsp"
     */
    protected ActionForward autenticador(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("token");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="token" path="index_token.jsp"
     * @jpf:forward name="erro" path="login_erro.jsp"
     */
    protected ActionForward meuCliente(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("Controller:meuCliente() - begin");

        if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("generateToken"))) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("token");
        }

        String dsLogin = ConstantesCRM.SVAZIO;
        String apiPassword = ConstantesCRM.SVAZIO;
        String apiLogin = ConstantesCRM.SVAZIO;
        String apiURL = ConstantesCRM.SVAZIO;

        VerifySessionDAO autenticador = new VerifySessionDAO();

        HashMap<String, String> parametros = new HashMap<String, String>();
        parametros.put("token", request.getParameter("token"));

        log.debug("Controller:meuCliente() - parametros: " + parametros.toString());

        try {
            apiPassword = telaInicialFacadeControl.getParametro(ConstantesCRM.SONE, "AUTENTICADOR_CAMPANHA_SENHA").getDsValorParametro();
            apiLogin = telaInicialFacadeControl.getParametro(ConstantesCRM.SONE, "AUTENTICADOR_CAMPANHA_LOGIN").getDsValorParametro();
            apiURL = telaInicialFacadeControl.getParametro(ConstantesCRM.SONE, "HOST_INTEGRADOR_CAMPANHA").getDsValorParametro();

        } catch (Exception e) {
            throw new Exception("Parâmetros insuficientes para autenticação de usuário.");
        }

        parametros.put("APIPASSWORD", apiPassword);
        parametros.put("APILOGIN", apiLogin);
        parametros.put("APIURL", apiURL);

        try {
            HashMap<String, String> autenticacaoHash = autenticador.getData(parametros);
            log.debug("Controller:meuCliente() - autenticacaoHash: " + autenticacaoHash.toString());

            Object login = autenticacaoHash.get("x-usuario");
            if (login == null) {
                throw new Exception("Login de usuário não fornecido.");
            }
            dsLogin = autenticacaoHash.get("x-usuario");
            UsuarioVO usuario = UsuarioVO.Factory.newInstance();

            try {
                usuario = controlAcesso.carregaDadosUsuarioSessao("<login>" + dsLogin + "</login>");

                request.getSession().setAttribute(ConstantesCRM.USUARIO_LOGIN, usuario.getIdUsuario());
                ConstantesCRM.setCurrentUser(request.getSession(), usuario.getIdUsuario());

                request.getSession().setAttribute("ACESSO_EXTERNO", ConstantesCRM.STRUE);
                request.setAttribute("inMeuCliente", ConstantesCRM.STRUE);
                request.getSession().setAttribute(ConstantesCRM.USUARIO_SITE, usuario.getDsSiteConsultorAtdAtual());
                request.getSession().setAttribute(ConstantesCRM.USUARIO_FORNECEDOR, usuario.getDsFornecedorConsultorAtdAtual());
                request.getSession().setAttribute(ConstantesCRM.USUARIO_PERFIL, usuario.getIdPerfilConsultorAtdAtual());

            } catch (Exception e) {
                log.error("Controller::meuCliente::Usuário não encontrado", e);
                throw new Exception("Usuário não encontrado no Vivonet.", e);
            }

        } catch (Exception e) {
            log.error("Controller::meuCliente::Exception", e);
            request.setAttribute("msgErro", e.getMessage());
            request.setAttribute("detailedMessage", e.getLocalizedMessage() != null ? e.getLocalizedMessage() + " - " + e.toString() : e.toString());
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("erro");
        }

        log.debug("Controller:meuCliente() - end");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:ActionForward name="error" path="error.jsp"
     * @jpf:ActionForward name="success" path="index.jsp"
     */
    protected ActionForward voltar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    // **********************************************
    // Vefirica se é o primeiro acesso do Usuário.
    // **********************************************
    private AlterarSenhaForm verificaUsuarioLDAP(UsuarioVO usuarioVO, HttpServletRequest request) throws Exception {
        AlterarSenhaForm form = null;

        LDAPUtil ldap = new LDAPUtil(request);
        LDAPVO ldapVO = ldap.getUsuarioByLogin(usuarioVO.getLogin(), LDAPConnection.SCOPE_SUB);

		String firstAccess = (ldapVO.getUser().getPassword().getTitle() != null) ? ldapVO.getUser().getPassword().getTitle() : ConstantesCRM.SVAZIO;

        if (firstAccess.equals("true")) {
            form = new AlterarSenhaForm();
            form.setLogin(ldapVO.getUser().getUid());
            form.setInMenu("false");
            form.setInVoltar("false");
            return form;
        }

        return null;
    }

    /**
     * @jpf:action
     * @jpf:ActionForward name="index" path="redirectCarregaPerfil.jsp"
     * @jpf:ActionForward name="inicio" path="inicio.jsp"
     * @jpf:ActionForward name="acessoNegado" path="acessoNegado.jsp"
     * @jpf:ActionForward name="error" path="error.jsp"
     * @jpf:ActionForward name="carregandoPerfil" path="carregandoPerfil.jsp"
     * @jpf:ActionForward name="retorna" path="retorna.do"
     */
    protected ActionForward autenticado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN) == null) {
            request.setAttribute("statusText", "Acesso Negado.");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("acessoNegado");
        }
        // verifica se a imagem "carregando perfil" foi chamada
		if (!ConstantesCRM.SONE.equals(request.getParameter("carregando_perfil"))) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("carregandoPerfil");
        }

        if (request.getParameter("numeroGrupo") != null) {
            request.getSession().setAttribute("numeroGrupo", request.getParameter("numeroGrupo"));
        }

        try {
            request.setAttribute("inMeuCliente", ConstantesCRM.SFALSE);
            this.loadMenu(request);
            this.loadPerfil(request);

        } catch (TuxedoWarningException twe) {
            String statusCode = twe.getXmlHeader().getStatusCode();
            String statusText = twe.getXmlHeader().getStatusText();
            formInicio.setMsgStatus(statusText);
            request.setAttribute("statusCode", statusCode);
            request.setAttribute("statusText", statusText);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("acessoNegado");
        }

        if (request.getParameter(ConstantesCRM.SACTION) == null) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("index");
        } else {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("inicio");
        }
    }

    /**
     * @jpf:action
     * @jpf:ActionForward name="error" path="error.jsp"
     * @jpf:ActionForward name="login" path="login.jsp"
     */
    protected ActionForward retorna(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("login");
    }

    /**
     * @jpf:action
     * @jpf:ActionForward name="success" path="about.jsp"
     */
    protected ActionForward about(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	log.info("[Controller.about] Inicio ");
    	request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        String strBuild = ConstantesCRM.SVAZIO;
        String strFechaSalida = ConstantesCRM.SVAZIO;
        try {

            URL configFile = this.getServlet().getServletContext().getResource("/WEB-INF/about.xml");
            File file = new File(configFile.toURI());
            if (file.exists()) {
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
                NodeList lista = document.getElementsByTagName("buildVersion");
                if (lista.getLength() > 0) {
                    strBuild = " [Build " + lista.item(0).getFirstChild().getNodeValue() + "]";
                }
                lista = document.getElementsByTagName("buildDate");
                if (lista.getLength() > 0) {
					String strFecha = lista.item(0).getFirstChild().getNodeValue() + ConstantesCRM.SVAZIO;
                    SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyyMMddhhmmss");
                    SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy");
                    strFechaSalida = formatoSalida.format(formatoEntrada.parse(strFecha));
                }
            } else {
            	log.warn("[Controller.about] file not exits ");
            }
            	
            request.setAttribute("version", strBuild);
            request.setAttribute("date", strFechaSalida);
            request.setAttribute(ConstantesCRM.USUARIO_LOGIN, request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));
            request.setAttribute(ConstantesCRM.USUARIO_NOME, request.getSession().getAttribute(ConstantesCRM.USUARIO_NOME));
            request.setAttribute("usuarioIdSession", request.getSession().getAttribute("usuarioIdSession"));
        } catch (Exception e) {
            System.out.println(e);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private void loadPerfil(HttpServletRequest request) throws TuxedoWarningException, FacadeException, Exception {
        ControlAcesso controleAcesso = new ControlAcesso();
        HashMap<String, List<String>> hash = controleAcesso.checaAcesso(ConstantesCRM.getCurrentUser(request.getSession()), request.getSession().getId());
        LoadProperties loadProperties = new LoadProperties(request);
        request.getSession().setAttribute(ConstantesCRM.PERFIL_USR_MIN, loadProperties.get(ConstantesCRM.PERFIL_USR_MIN));
        request.getSession().setAttribute("dt_login_usr", Calendar.getInstance());
        request.getSession().setAttribute(ConstantesCRM.PERFIL_SESSION, hash);
    }

    private String validaExitencia(HttpServletRequest request) throws TuxedoWarningException, FacadeException, Exception {
        ControlAcesso controleAcesso = new ControlAcesso();
        try {
            controleAcesso.checaUsuarioEstaLogado(ConstantesCRM.getCurrentUser(request.getSession()), request.getSession().getId());
        } catch (TuxedoWarningException twe) {
            return twe.getMessage();
        }
		return ConstantesCRM.SVAZIO;
    }

    private UsuarioVO loadDataUser(String user, HttpServletRequest request) throws TuxedoException, FacadeException {

        UsuarioVO usuarioVO = UsuarioVO.Factory.newInstance();
        try {
            usuarioVO = controlAcesso.carregaDadosUsuarioSessao("<login>" + user + "</login>");

            request.getSession().setAttribute(ConstantesCRM.USUARIO_LOGIN, user);
            ConstantesCRM.setCurrentUser(request.getSession(), usuarioVO.getIdUsuario());

            request.getSession().setAttribute(ConstantesCRM.USUARIO_LOGIN, usuarioVO.getLogin());
            request.getSession().setAttribute(ConstantesCRM.USUARIO_PERFIL, usuarioVO.getIdPerfilConsultorAtdAtual());

            if (usuarioVO.getDsFornecedorConsultorAtdAtual() != null && !ConstantesCRM.SVAZIO.equals(usuarioVO.getDsFornecedorConsultorAtdAtual())) {
                request.getSession().setAttribute(ConstantesCRM.USUARIO_FORNECEDOR, usuarioVO.getDsFornecedorConsultorAtdAtual());
            }

            if (usuarioVO.getDsSiteConsultorAtdAtual() != null && !ConstantesCRM.SVAZIO.equals(usuarioVO.getDsSiteConsultorAtdAtual())) {
                request.getSession().setAttribute(ConstantesCRM.USUARIO_SITE, usuarioVO.getDsSiteConsultorAtdAtual());
            }

            request.getSession().setAttribute(ConstantesCRM.USUARIO_LOGIN_CTI, usuarioVO.getLoginCti());
            request.getSession().setAttribute(ConstantesCRM.USUARIO_NOME, usuarioVO.getNome());

        } catch (Exception e) {
            log.error("Exception::loadDataUser::", e);
            usuarioVO = null;
        }

        return usuarioVO;
    }

    /**
     * @jpf:action
     */
    protected ActionForward login(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        FormInicio form = (FormInicio) formParam;
		StringBuffer retorno = new StringBuffer(ConstantesCRM.SVAZIO);
		String msg = ConstantesCRM.SVAZIO;
        String msgUser = "\"Usuário incorreto\"";
		String problemas = ConstantesCRM.SZERO;
		String autentica = ConstantesCRM.SZERO;
		String caminho = ConstantesCRM.SVAZIO;
		String altSenha = ConstantesCRM.SZERO;
		String expirando = ConstantesCRM.SZERO;
		String expirada = ConstantesCRM.SZERO;
        String pathSenha = "usuario/alterarSenha/begin.do?inMenu=false&inVoltar=false";
        try {
			if (ConstantesCRM.SVAZIO.equals(form.getPass()) || ConstantesCRM.SVAZIO.equals(form.getUser())) {
                msg = "Por favor, preencha os campos para realizar a autenticação.";
				autentica = ConstantesCRM.SZERO;
				problemas = ConstantesCRM.SONE;

            } else {
                log.info("login::LDAPUtil");
                LDAPUtil ldap = new LDAPUtil(request);
                if (ldap.autenticaUsuario(form.getUser(), form.getPass())) {
                    log.info("login::autenticaUsuario:=true");
					autentica = ConstantesCRM.SONE;
                    UsuarioVO usuarioVO = loadDataUser(form.getUser(), request);
                    if (usuarioVO != null) {
                        log.error("login::usuarioVO::" + usuarioVO.getIdUsuario());
                        AlterarSenhaForm form2 = verificaUsuarioLDAP(usuarioVO, request);
                        log.info("login::AlterarSenhaForm::" + form2);
                        if (form2 != null) {
                            caminho = pathSenha;
	                        altSenha = ConstantesCRM.SONE;
	                        problemas = ConstantesCRM.SONE;

                        } else {
	                        altSenha = ConstantesCRM.SZERO;
	                        caminho = ConstantesCRM.SVAZIO;
                            log.info("login::validaExitencia::call");
                            msg = validaExitencia(request);
                            log.info("login::validaExitencia::" + msg);
	                        if (msg.equals(ConstantesCRM.SVAZIO)) {
	                            problemas = ConstantesCRM.SZERO;
                            } else {
	                            problemas = ConstantesCRM.SONE;
                            }
                        }
                    } else {
                        log.info("login::autenticaUsuario:=false");
                        msg = "Não foi possivel autenticar o usuário. Por favor, tente novamente mais tarde.";
	                    autentica = ConstantesCRM.SZERO;
	                    problemas = ConstantesCRM.SONE;
                    }
                } else {
                    log.info("login::autenticaUsuario:=false");
                    msg = "Não foi possivel autenticar o usuário. Verifique o usuario ou/e senha";
					autentica = ConstantesCRM.SZERO;
					problemas = ConstantesCRM.SONE;
                }
            }
        } catch (TuxedoException e) {
            log.error("login::TuxedoException", e);
            String statusCode = e.getXmlHeader().getStatusCode();
            String statusText = e.getXmlHeader().getStatusText();
            formInicio.setMsgStatus(statusText);
            request.setAttribute("statusCode", statusCode);
            request.setAttribute("statusText", statusText);
			autentica = ConstantesCRM.SZERO;
			problemas = ConstantesCRM.SONE;
            msg = statusText;

        } catch (PasswordExpiringException ldape) {
            log.error("login::PasswordExpiringException", ldape);
			problemas = ConstantesCRM.SONE;
			autentica = ConstantesCRM.SONE;
            msg = "Sua senha esta expirando. Deseja Troca-la agora?";
            if (ldape.getTimeToExpireInHours() >= 24) {
                msg += "\nFaltam " + ldape.getTimeToExpireInDays() + " dia(s) para a expiração.";
            } else {
                msg += "\nFaltam " + ldape.getTimeToExpireInHours() + " hora(s) para a expiração.";
            }
            try {
                loadDataUser(form.getUser(), request);
				expirando = ConstantesCRM.SONE;
                caminho = pathSenha;
            } catch (Exception e) {
                msg += "[" + e.getMessage() + "]";
            }

        } catch (PasswordExpiredException ldape) {
            log.error("login::PasswordExpiredException", ldape);
			problemas = ConstantesCRM.SONE;
			autentica = ConstantesCRM.SONE;
            msg = ldape.getMessage();
            try {
                loadDataUser(form.getUser(), request);
				expirada = ConstantesCRM.SONE;
                caminho = pathSenha;

            } catch (Exception e) {
                msg += "[" + e.getMessage() + "]";
            }

        } catch (LDAPException ldape) {
            log.error("login::LDAPException", ldape);
			problemas = ConstantesCRM.SONE;
			autentica = ConstantesCRM.SZERO;
            switch (ldape.getLDAPResultCode()) {
                case LDAPException.NO_SUCH_ATTRIBUTE:
                    msg = msgUser;
                    break;
                case 100:// Codigo extra para usuario inexistente.
                    msg = msgUser;
                    break;
                case LDAPException.NO_SUCH_OBJECT:
                    msg = msgUser;
                    break;
                case LDAPException.INVALID_CREDENTIALS:
                    msg = "\"Senha incorreta tente novamente.\n\nO seu usuário será bloqueado após 03 tentativas incorretas.\"";
                    break;
                case LDAPException.SERVER_DOWN:
                    msg = ldape.getMessage();
                    break;
                case LDAPException.CONNECT_ERROR:
                    msg = "\nFalha na autenticação de usuário e senha.Por Favor, tente novamente, e persistindo o erro entre em contato com o Help Desk\n";
                    break;
                case LDAPException.CONSTRAINT_VIOLATION:
                    if (ldape.getLDAPErrorMessage().toUpperCase().indexOf("PASSWORD RETRY") > -1) {
                        msg = "Limite de tentativas para a senha excedido. Entre em contato com o Help Desk.";
                    } else {
                        msg = ldape.getLDAPErrorMessage();
                    }
                    break;
                case LDAPException.UNWILLING_TO_PERFORM:
                    if (ldape.getLDAPErrorMessage().toUpperCase().indexOf("ACCOUNT INACTIVATED") > -1) {
                        msg = "Login Bloqueado. Entre em contato com o Help Desk.";
                    } else {
                        msg = ldape.getLDAPErrorMessage();
                    }
                    break;
                default:
                    msg = "Erro sem tratamento. [code=" + ldape.getLDAPResultCode() + "|msg=" + ldape.getLDAPErrorMessage() + "]";
                    break;
            }// switch

        } catch (Exception e) {
            log.error("login::Exception", e);
			autentica = ConstantesCRM.SZERO;
			problemas = ConstantesCRM.SONE;
            msg = e.getMessage();
        }
        
        this.getFormInicio().setMsgStatus(msg);
        retorno.append("<login>");
        retorno.append("<autenticado>").append(autentica).append("</autenticado>");
        retorno.append("<problemas>").append(problemas).append("</problemas>");
        retorno.append("<mensagem>").append(msg).append("</mensagem>");
        retorno.append("<senha>");
        retorno.append("<alterar>").append(altSenha).append("</alterar>");
        retorno.append("<path>").append(caminho).append("</path>");
        retorno.append("<expirando>").append(expirando).append("</expirando>");
        retorno.append("<expirada>").append(expirada).append("</expirada>");
        retorno.append("</senha>");
        retorno.append("</login>");
        response.setContentType(ConstantesCRM.SContentType);
        
        try {
            BufferedOutputStream salida = new BufferedOutputStream(response.getOutputStream());
            salida.write(retorno.toString().getBytes(ConstantesCRM.SISO));
            salida.flush();
            salida.close();
        } catch (Exception e) {
        }
        
        return null;
    }

    /**
     * @jpf:action
     * @jpf:ActionForward name="success" path="restart.do"
     */
    protected ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ControlAcesso controleAcesso = new ControlAcesso();
        String usuario = ConstantesCRM.getCurrentUser(request.getSession());
        String sessionID = request.getSession().getId();
        try {
            // Fecha protocolo caso exista algum aberto
            String nrProtocolo = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

            if (!ConstantesCRM.SVAZIO.equals(nrProtocolo)) {
                FechaProtocoloTO fechaProtocoloTO = FechaProtocoloTO.Factory.newInstance();
                fechaProtocoloTO.setNrProtocolo(nrProtocolo);
                fechaProtocoloTO.setIdSistemaOrigem(Integer.toString(7));
                protocoloFacade.setFechaProtocolo(usuario, fechaProtocoloTO);
                request.getSession().removeAttribute(ConstantesCRM.NRPROTOCOLO);
            }

            controleAcesso.saidaSistema(usuario, sessionID);

            request.getSession().removeAttribute("numeroGrupo");
            request.getSession().removeAttribute("usuarioIdSession");
            request.getSession().removeAttribute("statusText");
            request.getSession().removeAttribute("dt_login_usr");
            request.getSession().removeAttribute(ConstantesCRM.PERFIL_USR_MIN);
            request.getSession().removeAttribute(ConstantesCRM.PERFIL_SESSION);
            request.getSession().removeAttribute(ConstantesCRM.USUARIO_LOGIN);
            request.getSession().removeAttribute(ConstantesCRM.USUARIO_PERFIL);
            request.getSession().removeAttribute(ConstantesCRM.USUARIO_FORNECEDOR);
            request.getSession().removeAttribute(ConstantesCRM.USUARIO_SITE);
            request.getSession().removeAttribute(ConstantesCRM.USUARIO_LOGIN_CTI);
            request.getSession().removeAttribute(ConstantesCRM.USUARIO_NOME);
            request.getSession().removeAttribute(ConstantesCRM.USUARIO_MENU);

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (TuxedoWarningException twe) {
            String statusCode = twe.getXmlHeader().getStatusCode();
            String statusText = twe.getXmlHeader().getStatusText();
            formInicio.setMsgStatus(statusText);
            request.setAttribute("statusCode", statusCode);
            request.setAttribute("statusText", statusText);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
        }
    }

    /**
     * carregar o menu principal
     */
    private void loadMenu(HttpServletRequest request) throws TuxedoException, FacadeException {
        // Verifica já carregou o menu?
        if (request.getSession().getAttribute(ConstantesCRM.USUARIO_MENU) != null) {
            return;
        }
        
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        MenuVO menu = controlSistema.carregaMenu(user);
        
        request.getSession().setAttribute(ConstantesCRM.USUARIO_MENU, menu);
    }

    /**
     * @jpf:action
     * @jpf:ActionForward name="success" path="login.jsp"
     */
    protected ActionForward restart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        formInicio = new FormInicio();
        request.getSession().invalidate();
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:ActionForward name="success" path="index.jsp"
     * @jpf:ActionForward name="acessoNegado" path="acessoNegado.jsp"
     * @jpf:ActionForward name="session" path="session.jsp"
     */
    protected ActionForward submitToSwitcher(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = ConstantesCRM.SVAZIO;
		// String pagina=ConstantesCRM.SVAZIO;
        String xmlIN;
        String xmlOUT;
        String xmlRetorno[];
        RuleVO ruleVO;
        ControleAcessoEnvioVO controleAcessoEnvioVO = ControleAcessoEnvioVO.Factory.newInstance();
        controleAcessoEnvioVO.setLogin(ConstantesCRM.getCurrentUser(request.getSession()));
		String BLOQUEADO = ConstantesCRM.SZERO;
		String EXPIRADA = ConstantesCRM.STWO;
		String INVALIDA = ConstantesCRM.STHREE;

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        if (ConstantesCRM.getCurrentUser(request.getSession()) == null) {
            return mapping.findForward("session");
        } else {
            user = ConstantesCRM.getCurrentUser(request.getSession());
            try {
                String timeSession = (String) request.getSession().getAttribute(ConstantesCRM.PERFIL_USR_MIN);
                long result = comparaDataLogin(request);

                if (result >= Integer.parseInt(timeSession)) {
                    JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();
					controleAcessoEnvioVO.setInAtivo(ConstantesCRM.SONE);
                    controleAcessoEnvioVO.setSessionId(request.getSession().getId());

					xmlIN = controleAcessoEnvioVO.toString().replaceAll("vo:", ConstantesCRM.SVAZIO);
                    xmlOUT = jatmi.executeCommnad(user, "UsrAcesso", xmlIN);

                    xmlRetorno = XmlManager.ParseXmlOut(xmlOUT);
                    ruleVO = RuleVODocument.Factory.parse(xmlRetorno[4]).getRuleVO();

                    if (ruleVO == null || ruleVO.getUs().equals(BLOQUEADO)) {
                        request.getSession().setAttribute("statusText", "Acesso Negado");
                        return mapping.findForward("acessoNegado");

                    } else if (ruleVO == null || ruleVO.getUs().equals(EXPIRADA)) {
                        request.getSession().setAttribute("statusText", xmlRetorno[3]);
                        return mapping.findForward("acessoNegado");

                    } else if (ruleVO == null || ruleVO.getUs().equals(INVALIDA)) {
                        request.getSession().setAttribute("statusText", xmlRetorno[3]);
                        return mapping.findForward("acessoNegado");

                    } else {
                        request.getSession().setAttribute("dt_login_usr", Calendar.getInstance());
                        LoadProperties prop = new LoadProperties(request);
                        ServletContext servletCurrentContext = getServlet().getServletConfig().getServletContext();
                        ServletContext servletOtherContext = servletCurrentContext.getContext(prop.get("OTHER_CONTEXT"));
                        request.setAttribute("remoteUser", request.getRemoteUser());
                        request.setAttribute("remoteAddr", request.getRemoteAddr());
                        request.setAttribute("usuarioLoginCTI", request.getSession().getAttribute((ConstantesCRM.USUARIO_LOGIN_CTI)));
                        request.setAttribute("usuarioLogin", request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));
                        request.setAttribute("usuarioNome", request.getSession().getAttribute(ConstantesCRM.USUARIO_NOME));
                        request.setAttribute("idUsuario", new Long(ConstantesCRM.getCurrentUser(request.getSession()).toString()));

                        try {
                            RequestDispatcher requestDispatcher = servletOtherContext.getRequestDispatcher(prop.get("OTHER_CONTEXT_REQUEST_DISPATCHER"));
                            requestDispatcher.forward(request, response);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    request.getSession().setAttribute("dt_login_usr", Calendar.getInstance());
                    LoadProperties prop = new LoadProperties(request);
                    ServletContext servletCurrentContext = getServlet().getServletConfig().getServletContext();
                    ServletContext servletOtherContext = servletCurrentContext.getContext(prop.get("OTHER_CONTEXT"));
                    request.setAttribute("remoteUser", request.getRemoteUser());
                    request.setAttribute("remoteAddr", request.getRemoteAddr());
                    request.setAttribute("usuarioLoginCTI", request.getSession().getAttribute((ConstantesCRM.USUARIO_LOGIN_CTI)));
                    request.setAttribute("usuarioLogin", request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));
                    request.setAttribute("usuarioNome", request.getSession().getAttribute(ConstantesCRM.USUARIO_NOME));
                    request.setAttribute("idUsuario", new Long(ConstantesCRM.getCurrentUser(request.getSession()).toString()));

                    try {
                        RequestDispatcher requestDispatcher = servletOtherContext.getRequestDispatcher(prop.get("OTHER_CONTEXT_REQUEST_DISPATCHER"));
                        requestDispatcher.forward(request, response);
                    } catch (Exception ex) {
                        // ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                // XmlHeader header = new XmlHeader("UsrAcesso", user, "00", 'E', "0000", "Acesso não autorizado");
                // TuxedoErrorException tex = new TuxedoErrorException(header, ex);
            }
        }
        return null;
    }

    private long comparaDataLogin(HttpServletRequest request) {
        Calendar dtLoginUsr = (Calendar) request.getSession().getAttribute("dt_login_usr");
        Calendar dtAtual = Calendar.getInstance();
        long result = dtAtual.getTimeInMillis() - dtLoginUsr.getTimeInMillis();
        if (result > 0) {
            result = result / 1000 / 60;
        }
        return result;
    }

    public static class formSwitcher extends ActionForm {

        private static final long serialVersionUID = 7072636554888023718L;
        private String            _goto;
        private String            password;
        private String            username;

        public formSwitcher() {
			password = ConstantesCRM.SVAZIO;
			username = ConstantesCRM.SVAZIO;
			_goto = ConstantesCRM.SVAZIO;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return this.username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return this.password;
        }

        public void setGoto(String _goto) {
            this._goto = _goto;
        }

        public String getGoto() {
            return this._goto;
        }
    }

    public formSwitcher getFormSwitcher() {
        return this.formSwitcher;
    }

    public static class FormInicio extends ActionForm {

        private static final long serialVersionUID = 1718611487757975747L;
        private boolean           flag;
        private String            msgStatus;
        private String            pass;
        private String            user;

        public void setUser(String user) {
            this.user = user;
        }

        public String getUser() {
            if (this.user == null) {
				this.user = ConstantesCRM.SVAZIO;
            }
            return this.user;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getPass() {
            if (this.pass == null) {
				this.pass = ConstantesCRM.SVAZIO;
            }
            return this.pass;
        }

        public void setMsgStatus(String msgStatus) {
            this.msgStatus = msgStatus;
        }

        public String getMsgStatus() {
            return this.msgStatus;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public boolean isFlag() {
            return this.flag;
        }
    }

    public FormInicio getFormInicio() {
        if (formInicio == null) {
            formInicio = new FormInicio();
        }
        return formInicio;
    }
}
