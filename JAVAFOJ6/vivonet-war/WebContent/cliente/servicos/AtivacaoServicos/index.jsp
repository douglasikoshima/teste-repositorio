<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

   

<acesso:controlInitEnv/>

    <%/*request.getSession().setAttribute("idUsuario","1");
      request.getSession().setAttribute("nrCodArea","11");
      request.getSession().setAttribute("nrLinha","71010155");*/
    %>
    <head>
        <title>Pré Pago</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>

	
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <vivo:sessao id="PrePago" width="790" height="495" label="Ativação/Desativação de Serviço" operacoes="Ativar/Desativar">
     <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
    <div id="divFormCliente" style="visibility:" align="center">
        <iframe id="formCliente" width="750" height="125" frameborder="0" style="background-color:#000000;" src="verificaServico.do?codServico=<%=request.getParameter("codServico")%>&paginaAtual=<%=request.getParameter("paginaAtual")%>">
    </div>

    </vivo:sessao>
       
	

