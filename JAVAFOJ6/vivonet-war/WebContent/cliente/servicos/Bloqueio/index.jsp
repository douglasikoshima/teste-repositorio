<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

   

<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Gerenciamento de Árvore de Perguntas"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>
    <netui-template:section name="headerSection">
    <head>
        <title>Pré Pago</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    </netui-template:section>   
    <netui-template:section name="bodySection">    
	<acesso:controlHiddenItem nomeIdentificador="cli_bloqindex_verpagina">
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <vivo:sessao id="PrePago" width="790" height="495" label="Bloqueio de Celular" operacoes="Bloqueio">
     <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
    <div id="divFormCliente" style="visibility:" align="center">
        <iframe id="formCliente" width="750" height="125" frameborder="0" style="background-color:#000000;" src="carregaForm.do">
    </div>

    </vivo:sessao>
       
	</acesso:controlHiddenItem>
    </netui-template:section> 
</netui-template:template>
