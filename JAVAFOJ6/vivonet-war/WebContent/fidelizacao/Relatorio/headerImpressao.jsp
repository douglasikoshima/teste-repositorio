<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<bean:define id="formDadosPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa"/>
<table cellpadding="5" cellspacing="0" border="0">
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
        <td><b><%=request.getParameter("totalReg").toString()%></b> registros</td>
    </tr>
</table>