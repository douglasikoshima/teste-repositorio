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
<%@ taglib uri="BarChartGen.tld" prefix="bcg"%>

<bean:define id="numerico" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioEfectForm.relEfetividadeVO.numericoArray"/>
<bean:define id="porcentagem" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioEfectForm.relEfetividadeVO.porcentagemArray"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>
<%--
<bean:define id="grafico" type="br.com.vivo.report.bean.BarChartBean" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="grafico"/>
--%>
<html>
<head>
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <title> Web Application Page </title>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
    <SCRIPT FOR=window EVENT=onload>
        window.print();
    </script>
</head>
<body leftmargin="0" bottommargin="0" rightmargin="0" topmargin="0">
    <table width="600" align="left" cellpadding="5" cellspacing="0">
        <tr>
            <td>
            <table width="90%" cellpadding="2" cellspacing="2" border="1">
                <tr bordercolor="#FFFFFF">
                    <td rowspan="7"><img src="/FrontOfficeWeb/resources/images/logo_relatorio.jpg"></td>
                    <td colspan="4" bgcolor="#00569f" align="center"><b><font color="#FFFFFF">Relatório de Efetividade</font></b></td>
                </tr>
                <tr>
                    <td width="100" bordercolor="#FFFFFF" bgcolor="#ededed"><b>Data de Emissão:</b></td>
                    <td width="180" bordercolor="#adadad"><bean:write name="relHeader" property="dtEmissao" /></td>
                    <td width="150" bordercolor="#FFFFFF" bgcolor="#ededed"><b>Operador:</b></td>
                    <td width="180" bordercolor="#adadad"><bean:write name="relHeader" property="sgUsuario" /></td>
                </tr>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Campanha:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="sgCampanha" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Sub Campanha:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="nmSubCampanha" /></td>
                </tr>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Versão:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="sqVersao" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Período de Datas:</b></td>
                    <td bordercolor="#adadad">de <bean:write name="relHeader" property="filtroDtInicio" /> a <bean:write name="relHeader" property="filtroDtFim" /></td>
                </tr>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Grupo:</b></td>
                    <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmGrupo" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Canal:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="nmCanal" /></td>
                </tr>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Status Sub. Camp.:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="inDisponibilidade" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Período Vigência Sub. Camp.:</b></td>
                    <td bordercolor="#adadad">de <bean:write name="relHeader" property="subCampanhaDtInicio" /> a <bean:write name="relHeader" property="subCampanhaDtFim" /></td>
                </tr>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Regional:</b></td>
                    <td colspan="4" bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmRegional" /></td>
                </tr>
            </table>
            </td>
        </tr>
        <tr>
            <td>
            <table width="90%" cellpadding="2" cellspacing="2" border="1">
                <tr bordercolor="#FFFFFF">
                    <td width="280">
                        <img src="{request.graph}" />
                        <b>* Aguarde até o gráfico ser carregado.</b>
                    </td>
                    <td>
                    <table width="90%" cellspacing="0" cellpadding="2" border="1">
                        <logic:iterate id="item" name="numerico" >
                        <tr>
                            <td bordercolor="#FFFFFF" bgcolor="#ededed"><b><bean:write name="item" property="descricao" /></b></td>
                            <td bordercolor="#FFFFFF" bgcolor="#F7F7F7"><bean:write name="item" property="valor" /></td>
                        </tr>
                        </logic:iterate>
                    </table>
                    </td>
                </tr>
            </table>
            </td>
        </tr>
        <tr>
            <td>
            <table width="90%" cellpadding="2" cellspacing="0" border="1">
            <logic:iterate id="item" name="porcentagem">
                <tr bordercolor="#FFFFFF">
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b><bean:write name="item" property="descricao" /></b></td>
                    <td style="border-bottom: solid 1px #adadad;"><bean:write name="item" property="valor" /></td>
                </tr>
            </logic:iterate>
            </table>
            </td>
        </tr>
    </table>
</body>
</html>