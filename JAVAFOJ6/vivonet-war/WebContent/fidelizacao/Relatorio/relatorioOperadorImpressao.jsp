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

<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOperador"/>                 
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
                            <fid:headerTd>Operador</fid:headerTd>
                            <fid:headerTd>Soma</fid:headerTd>
                            <fid:headerTd>% Retenções</fid:headerTd>
                            <fid:headerTd>% Migração</fid:headerTd>
                            <fid:headerTd>% Cancelado</fid:headerTd>
                            <fid:headerTd>% Oferta</fid:headerTd>
                            <fid:headerTd>% Suspensão</fid:headerTd>
                            <fid:headerTd>% Adequação de Plano</fid:headerTd>
                            <fid:headerTd>Percentual</fid:headerTd>
                            <fid:headerTd>Resultado</fid:headerTd>
                            <fid:headerTd>Quantidade</fid:headerTd>
                        </fid:tr>
                        <logic:iterate id="linha" name="relatorio" property="linhaOperador">
                            <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                                <fid:dataTd><bean:write name="linha" property="operador"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="soma"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcretencoes"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcmigracao"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porccancelado"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcoferta"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcsuspensao"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="porcadequacaoplano"/></fid:dataTd>
                                <fid:dataTd><bean:write name="linha" property="percentual"/></fid:dataTd>
                                <fid:dataTd>
                                    <logic:notEmpty name="linha" property="resultado">
                                        <logic:iterate id="descricao" name="linha" property="resultado.descricaoArray" offset="0" length="1">
                                            <bean:write name="descricao"/>
                                        </logic:iterate>
                                    </logic:notEmpty>                                    
                                </fid:dataTd>
                                <fid:dataTd>
                                    <logic:notEmpty name="linha" property="resultado">
                                        <logic:iterate id="quantidade" name="linha" property="resultado.quantidadeArray" offset="0" length="1">
                                            <bean:write name="quantidade"/>
                                        </logic:iterate>
                                    </logic:notEmpty>                                    
                                </fid:dataTd>
                            </fid:tr>
                            <logic:greaterThan name="linha" property="resultado.descricaoArray" value="1">
                                <logic:iterate id="descricao" name="linha" property="resultado.descricaoArray" offset="1" indexId="index">
                                    <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd></fid:dataTd>
                                        <fid:dataTd><bean:write name="descricao"/></fid:dataTd>
                                        <fid:dataTd>
                                            <logic:iterate id="quantidade" name="linha" property="resultado.quantidadeArray" offset="index" length="1">
                                                <bean:write name="quantidade" />
                                            </logic:iterate>
                                        </fid:dataTd>
                                    </fid:tr>
                                </logic:iterate>
                            </logic:greaterThan>            
                        </logic:iterate>
                    </fid:table>
                </td>
            </tr>            
        </table>
    </body>
</html>