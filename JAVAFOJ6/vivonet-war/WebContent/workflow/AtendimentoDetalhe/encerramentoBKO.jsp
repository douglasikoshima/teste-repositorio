<!--
Módulo.....: Gestão de Processos
Caso de Uso: Detalhe do processo
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:40 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="atdVO"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.atendimentoVO"/>
<logic:notEmpty name="atdVO" property="encerramentoVO">
    <bean:define id="encerramentoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.atendimentoVO.encerramentoVO"/>
</logic:notEmpty>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<table border="0">
    <tr>
        <td valign="top">
            <bean:define id="scrPalitagem" name="form" property="scriptPalitagemEncerramento"/>
            <%= scrPalitagem.toString()%>
        </td>
        <td>
            <table border="0">
                <tr>
                    <td valign="middle" align="right">
                        Forma de Retorno:
                    </td>
                    <td align="left">
                        <logic:notEmpty name="atdVO" property="encerramentoVO">
                            <html:text name ="encerramentoVO" property="dsFormaRetorno" readonly="true"/>
                        </logic:notEmpty>
                    </td>
                </tr>
                <tr>
                    <td valign="middle" align="right">
                        Resposta Padrão:
                    </td>
                    <td align="left">
                        <logic:notEmpty name="atdVO" property="encerramentoVO">
                            <html:textarea name ="encerramentoVO" property="dsMensagem" readonly="true" cols="30" rows="3"/>
                        </logic:notEmpty>
                    </td>
                </tr>
                <tr>
                    <td valign="middle" align="right">
                        Comentário:
                    </td>
                    <td align="left">
                        <logic:notEmpty name="atdVO" property="encerramentoVO">
                            <html:textarea name ="encerramentoVO" property="dsComentario" readonly="true" cols="30" rows="5"/>
                        </logic:notEmpty>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
