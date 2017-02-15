<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="detalheLinhaForm"/>

<head>    
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
</head>

<body>    
	<acesso:controlHiddenItem nomeIdentificador="cli_dlpp_verpagina">
    <table width="739" height="90" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr><td height="7"></td></tr>
        <logic:empty name="error">
            <tr>
                <td width="140">&nbsp;<b>Modelo</b></td>
                <td width="150"><bean:write name="form" property="detalheLinhaVO.modelo" /></td>
                <td width="170"><b>Descrição</b></td>
                <td width="279"><bean:write name="form" property="detalheLinhaVO.descricao" /></td>
            </tr>
            <tr>
                <td>&nbsp;<b>Tecnologia</b></td>
                <td><bean:write name="form" property="detalheLinhaVO.dsTecnologia" /></td>
                <td><b>Marca</b></td>
                <td><bean:write name="form" property="detalheLinhaVO.marca" /></td>
            </tr>
            <tr>
                <td>&nbsp;<b>Contrato de Fidelização</b></td>
                <td><bean:write name="form" property="detalheLinhaVO.contratoFidelizacao" /></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>&nbsp;<b>Multa Contratual</b></td>
                <td><bean:write name="form" property="detalheLinhaVO.dsMultaContrato" /></td>
                <td></td>
                <td></td>
            </tr> 
        </logic:empty>
        <logic:notEmpty name="error">
            <tr>
                <td colspan="4"><bean:write name="error"/></td>
            </tr>
        </logic:notEmpty name="error">       
    </table>    
	</acesso:controlHiddenItem>
</body>
