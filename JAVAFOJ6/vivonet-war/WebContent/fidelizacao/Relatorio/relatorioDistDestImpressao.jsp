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
<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formRelatorioDistDest"/>                 
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
                                <fid:headerTd>Destino Previsto</fid:headerTd>
                                <fid:headerTd>Qtd Total</fid:headerTd>
                                <fid:headerTd>% Total</fid:headerTd>
                                <fid:headerTd>Qtd Retenção</fid:headerTd>
                                <fid:headerTd>% Retenção</fid:headerTd>
                                <fid:headerTd>Qtd Migração</fid:headerTd>
                                <fid:headerTd>% Migração</fid:headerTd>
                                <fid:headerTd>Qtd Cancelado</fid:headerTd>
                                <fid:headerTd>% Cancelado</fid:headerTd>
                                <fid:headerTd>Qtd Aparelhos</fid:headerTd>
                                <fid:headerTd>% Aparelhos</fid:headerTd>
                                <fid:headerTd>Qtd Suspensão</fid:headerTd>
                                <fid:headerTd>% Suspensão</fid:headerTd>
                                <fid:headerTd>Qtd Adequação Plano</fid:headerTd>
                                <fid:headerTd>% Adequação Plano</fid:headerTd>          
                            </fid:tr>
                            <logic:iterate id="linha" name="relatorio" property="linhaDestino">
                                <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                                    <fid:dataTd><nobr><bean:write name="linha" property="destinoprevisto"/></nobr></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="qtddestinp"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="porctotal"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="qtdretencao"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="porcretencao"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="qtdmigracao"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="porcmigracao"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="qtdcancelado"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="porccancelado"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="porcoferta"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="porcbonus"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="qtdsuspensaoTemp"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="porcsuspensaoTemp"/></fid:dataTd>                                
                                    <fid:dataTd><bean:write name="linha" property="qtdadequacao"/></fid:dataTd>
                                    <fid:dataTd><bean:write name="linha" property="porcadequacao"/></fid:dataTd>                                          
                                </fid:tr>            
                            </logic:iterate>
                        </fid:table> 
                </td>
            </tr>            
        </table>
    </body>
</html>