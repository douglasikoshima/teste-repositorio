<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRelatorios"/>                 
<bean:define id="formDadosPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa"/>                 
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <SCRIPT FOR=window EVENT=onload>
            //top.frameApp.oculta_div();        
            window.print();
        </script>  
    </head>
    <body leftmargin="0" bottommargin="0" rightmargin="0" topmargin="0">    
        <table width="650">
            <tr>
                <td><img src="/FrontOfficeWeb/resources/images/img_relatorio.gif"></td>
            </tr>
        </table>
        <table border="0" width="650" class="tbl_bgblue">
            <tr>
                <td><b><bean:write name="relatorio" property="nome"/></b></td>
            </tr>
        </table>
        <table width="650" cellpadding="0" cellspacing="0" border="0" >
            <tr>
                <td>                               
                    <table width="100%" cellpadding="5" cellspacing="0" border="0" class="tbl_bggray">
                        <tr>
                            <td rowspan="5" valign="middle" align="center" bgcolor="white"><img src="/FrontOfficeWeb/resources/images/logo_relatorio.jpg"></td>
                        </tr>
                        <tr>
                            <td>Período de:</td>
                            <td >
                                <table cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td><b><bean:write name="formDadosPesquisa" property="dataInicio"/></b></td>
                                        <td>&nbsp;até:</td>
                                        <td>&nbsp;<b><bean:write name="formDadosPesquisa" property="dataFim"/></b>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td align="left" valign="top">Login do Usuário:</td>
                            <td align="left" valign="top"><b><bean:write name="formDadosPesquisa" property="operador"/></b></td>
                        </tr>
                        <tr>
                            <td>Segmentação:</td>
                            <td>
                                <b><bean:write name="formDadosPesquisa" property="dsClassificacao"/></b>
                            </td>
                            <td valign="top" align="left">Oferta:</td>
                            <td valign="top"  style="padding-left:2px"><b><bean:write name="formDadosPesquisa" property="dsOferta"/></b></td>
                        </tr>
                        <tr>
                            <td valign="top">Regional:</td>
                            <td valign="top">
                                <table>
                                    <logic:iterate id="operadora" name="formDadosPesquisa" property="dsOperadora">
                                        <tr>
                                            <td>
                                                <b><bean:write name="operadora"/></b>
                                            </td>
                                        </tr>
                                    </logic:iterate>
                                </table>
                            </td>
                            <td valign="top">Tipo Cliente:</td>
                            <td valign="top" style="padding-left:2px"><b><bean:write name="formDadosPesquisa" property="dsTipoCliente"/></b></td>
                        </tr>
                        <tr>
                            <td >Grupo:</td>
                            <td><b><bean:write name="formDadosPesquisa" property="dsGrupo"/></b></td>
                            <td >Foram selecionados </td>
                            <td><b><bean:write name="relatorio" property="totalReg"/></b> registros</td>
                        </tr>
                    </table>
                </td>
            </tr>            
        </table>
        <bean:write name="relatorio" property="relatorio" filter="false"/>
    </body>
</html>