<!--
Módulo.....: Workflow
Caso de Uso: Registrar Contato (Atendimento)
Versão.....: $Author: a5112272 $ - $Revision: 1.3 $ - $Date: 2011/05/25 18:27:57 $
-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="workflow.RegistrarContato.RegistrarContatoController.RegistrarContatoForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />

<html>
    <head>
        <title>
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_atctes_verpagina">
        <form action="listarProcessos.do" method="post" name="registrarContatoForm">
            <html:hidden name="Form" property="rowIndex"/>                      
            <html:hidden name="Form" property="idAtendimentoExclusao"/>                      
            <html:hidden name="Form" property="idChamadaTelefonica"/>       
            <div id="divProcessoCorrentes" style="visibility:visible;POSITION:absolute;">
                <iframe name="ifrmListaProcessos" src="listarProcessos.do?idChamadaTelefonica=<%=((RegistrarContatoForm)Form).getIdChamadaTelefonica()%>" width="400" height="320" scrolling="no"></iframe>
            </div>
            <br><br>
        </form>
        <iframe name="ifrmRegistrarContato" style="visibility:hidden;" width="1" height="1"></iframe>
    </acesso:controlHiddenItem>
    </body>
</html>