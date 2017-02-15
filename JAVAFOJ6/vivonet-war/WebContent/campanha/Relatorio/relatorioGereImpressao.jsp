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

<bean:define id="numerico" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGerenciamentoForm.campanhaRelatorioVO.numericoArray"/>
<bean:define id="porcentagem" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGerenciamentoForm.campanhaRelatorioVO.porcentagemArray"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>

<html>
<head>
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <SCRIPT FOR=window EVENT=onload>
        window.print();
    </script>
</head>
<body leftmargin="0" bottommargin="0" rightmargin="0" topmargin="0">
    <table width="600" align="left" cellpadding="5" cellspacing="0">
        <tr>
            <td>
            <table width="100%" cellpadding="2" cellspacing="2" border="1">
                <tr bordercolor="#FFFFFF">
                    <td rowspan="8"><img src="/FrontOfficeWeb/resources/images/logo_relatorio.jpg"></td>
                    <td colspan="4" bgcolor="#00569f" align="center"><b><font color="#FFFFFF">Relatório de Gerenciamento</font></b></td>
                </tr>
                <tr>
                    <td width="100" bordercolor="#FFFFFF" bgcolor="#ededed"><b>Data de Emissão:</b></td>
                    <td width="180" bordercolor="#adadad"><bean:write name="relHeader" property="dtEmissao" /></td>
                    <td width="150" bordercolor="#FFFFFF" bgcolor="#ededed"><b>Operador que emitiu o Relatório:</b></td>
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
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Status Sub. Camp.:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="inDisponibilidade" /></td>
                </tr>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Grupo:</b></td>
                    <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmGrupo" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Canal:</b></td>
                    <td bordercolor="#adadad"><bean:write name="relHeader" property="nmCanal" /></td>
                </tr>
                <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Período de Datas:</b></td>
                    <td bordercolor="#adadad">de <bean:write name="relHeader" property="filtroDtInicio" /> a <bean:write name="relHeader" property="filtroDtFim" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Operador Selecionado:</b></td>
                    <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmLoginUsuario"  /></td>
                </tr>
               <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Período Vigência Sub. Camp.:</b></td>
                    <td bordercolor="#adadad">de <bean:write name="relHeader" property="subCampanhaDtInicio" /> a <bean:write name="relHeader" property="subCampanhaDtFim" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Meta Diária da Sub Campanha:</b></td>
                    <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nrMetaDiariaCampanha" /></td>
                </tr>
               <tr>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Público Total:</b></td>
                    <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="publicoAlvo" /></td>
                    <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Regional:</b></td>
                    <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmRegional" /></td>
                </tr>
            </table>
            </td>
        </tr>
        <tr>
            <td>
            <table width="100%" cellpadding="2" cellspacing="2" border="1">
                <tr bordercolor="#FFFFFF">
                    <td width="50%" align="center">
                        <img src="{request.graph}" />
                        <br>
                        <b>* Aguarde até o gráfico ser carregado.</b>
                    </td>
                    <td width="50%" valign="top">
                    <table width="100%" cellspacing="0" cellpadding="2" border="1">
                        <logic:iterate id="item" name="numerico" >
                        <tr valign="top">
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
            <table width="100%" cellpadding="2" cellspacing="2" border="1">
                <tr bordercolor="#FFFFFF">
                    <td width="50%" align="center">
                        <img src="{request.graph2}" />
                        <br>
                        <b>* Aguarde até o gráfico ser carregado.</b>
                    </td>
                    <td width="50%" valign="top">
                    <table width="100%" cellspacing="0" cellpadding="2" border="1">
                        <logic:iterate id="item" name="porcentagem" >
                        <tr valign="top">
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
    </table>
</body>
</html>