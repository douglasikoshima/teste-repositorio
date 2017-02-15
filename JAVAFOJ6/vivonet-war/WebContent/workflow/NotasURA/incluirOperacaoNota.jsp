<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="notasForm" name="notasForm" scope="request" />
<bean:define id="motivoVO" name="notasForm" property="motivoVO"/>
<bean:define id="notaVO" name="notasForm" property="notaVO"/>
<bean:define id="acaoVO" name="notasForm" property="acaoVO"/>
<html>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <head>
        <title>
            Incluir Historico
        </title>
    </head>
    <body>
<SCRIPT LANGUAGE="JavaScript">
<!--
function incluirHistorico(){
    document.forms[0].submit();
}
function changeOpcao(obj){   

}
parent.screen_unblock();
//-->
</SCRIPT>
<acesso:controlHiddenItem nomeIdentificador="ntura_opr_verpagina">
<html:form action="incluirOperacaoNota.do">
    <table cellspacing="14">
    <tr>
        <td>
        Operações:
        </td>
        <td colspan="2">        
         <%                        
         String dtAberturaFim = ((WFAtdNotaVO)notaVO).getDtAberturaFim();
         if((dtAberturaFim != null && !dtAberturaFim.equals(ConstantesCRM.SVAZIO))){
         }
         %>
         <html:select name="notasForm" property="acaoSel" onchange="changeOpcao(this)" style="width=200">
         <html:option value="0">Selecionar Operação</html:option>
         <html:options collection="acaoVO" property="idAtividade" labelProperty="dsAtividade"/>
        </html:select>                        					
        </td>
    </tr>    
    <tr>
        <td>
        Motivos:
        </td>
        <td colspan="2">
        <html:select name="notasForm" property="motivoSel" style="width=200">
         <html:option value="0">Selecionar Motivo</html:option>
         <html:options collection="motivoVO" property="idMotivo" labelProperty="dsMotivo"/>
        </html:select>
        </td>
    </tr>
    <tr>
        <td valign=top>
        Comentário:
        </td>
        <td>
        <html:textarea cols="30" rows="5" property="dsObservacao" name="notasForm"></html:textarea>
        </td>
        <td>        
        <acesso:controlHiddenItem nomeIdentificador="ntura_opr_gravar">
        <img hspace="8" vspace="6" style="border:none;" onClick="incluirHistorico(); return false;" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/> 
        </acesso:controlHiddenItem>
        </td>
    </tr>
    </table>
</html:form>
</acesso:controlHiddenItem>
    </body>
</html>