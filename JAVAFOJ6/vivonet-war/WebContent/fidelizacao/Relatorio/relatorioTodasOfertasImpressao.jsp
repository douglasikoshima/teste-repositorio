<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>

<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formTodasOfertas"/>
<bean:define id="formDadosPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa"/>                 
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/fidelizacao_relatorios.css">
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
        <table width="739" cellpadding="0" cellspacing="0" border="0" >
            <tr>
                <td class="tbl_bgblue"><b><%=request.getParameter("nome").toString()%></b></td>
            </tr>
            <tr>
                <td class="tbl_bggray">                               
                    <jsp:include page="headerImpressao.jsp" />
                </td>
            </tr>
            <tr>
                <td>
                    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
                    <fid:table>
                        <fid:tr bgColor="#545454">                  
                            <fid:headerTd>Intenção do Cancelamento</fid:headerTd>
                            <fid:headerTd>Qtd Intenções</fid:headerTd>
                            <fid:headerTd>% Intenções</fid:headerTd>
                            <fid:headerTd>Destino Previsto</fid:headerTd>
                            <fid:headerTd>Qtd Retencao</fid:headerTd>
                            <fid:headerTd>% Retencao</fid:headerTd>
                            <fid:headerTd>Qtd Migração</fid:headerTd>
                            <fid:headerTd>% Migração</fid:headerTd>
                            <fid:headerTd>Qtd Bônus</fid:headerTd>
                            <fid:headerTd>% Bônus</fid:headerTd>
                            <fid:headerTd>Qtd Pontos</fid:headerTd>
                            <fid:headerTd>% Pontos</fid:headerTd>
                        </fid:tr>
                        <logic:iterate id="linha" name="relatorio" property="linhaOferta">
                            <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                                <fid:dataTd><nobr><bean:write name="linha" property="intCancelamiento"/></nobr></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="qtdintencao"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcintencao"/></fid:dataTd>
                                <fid:dataTd><nobr><bean:write name="linha" property="destinoprevisto"/></nobr></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="qtdretencao"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcretencao"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="qtdmigracao"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcmigracao"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="qtdbonus"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcbonus"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="qtdpronto"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcpronto"/></fid:dataTd>                                                           
                            </fid:tr>            
                        </logic:iterate>                
                    </fid:table>
                </td>
            </tr>            
        </table>
    </body>
</html>