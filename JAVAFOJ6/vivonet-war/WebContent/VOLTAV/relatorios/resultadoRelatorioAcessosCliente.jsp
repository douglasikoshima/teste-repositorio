<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioAcessosForm" />
<bean:define id="relatorioAcessoCliente" name="form" property="relatorioAcessoCliente" />

<acesso:controlInitEnv/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Front-Office >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    </netui-template:section>
    <netui-template:section name="bodySection">

        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <!--APLICACAO-->
        <vivo:sessao id="qdMain" height="475" width="790" label="Acessos por Cliente" operacoes="Relatório">

            <form action="gerarRelatorioAcessosCliente.do" method="post" name="relatorioAcessosForm">

                <br>

                <table border="0" cellpadding="0" cellspacing="0" bgcolor="#adadad">
				<tr>
						<td colspan="3">
							<table width="100%" cellpadding="0" cellspacing="1" border="0">
							<tr bordercolor="#FFFFFF">
								 <td rowspan="7" bgcolor="#FFFFFF"><img src="/FrontOfficeWeb/resources/images/logo_relatorio.jpg"></td>
							</tr>
							<tr>
								<td width="100" bgcolor="#ededed"><b>Data de Emissão:</b></td>
								<td width="180" bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="dtAtual"/></td>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Número da linha:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmNumeroRegistro" /></td>
							</tr>
							<tr>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Período de Datas</b></td>
								<td bgcolor="#FFFFFF" colspan="3">&nbsp;de <bean:write name="form" property="dataInicio" /> a <bean:write name="form" property="dataFim" /></td>
							</tr>
							</table>
						</td>
					</tr>
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="1" style="font-family: Tahoma, Arial; font-size: 10px; font-weight: bold; color: #ffffff; border: #D4D7DE 1px solid;">
                                <tr bgcolor="#545454">
                                    <td align="center" height="25" width="150">DATA</td>
                                    <td align="center" height="25" width="450">OPERAÇÃO</td>
                                    <td align="center" height="25" width="100">CANAL</td>
                                </tr>
                            </table>
                            <table border="0" cellpadding="0" cellspacing="1" style="font-family: Tahoma, Arial; font-size: 10px; color: #000000; border: #D4D7DE 1px solid;">
								<logic:iterate name="relatorioAcessoCliente" id="rel" indexId="ctr">
                                    <tr bgcolor="#E3ECF4">
                                        <td align="center" height="25" width="150" valign="middle"><bean:write name="rel" property="dtRelaciomaneto"/><bean:write name="rel" property="hrRelaciomaneto"/></td>
                                        <td align="center" height="25" width="450" valign="middle"><bean:write name="rel" property="dsOperacao"/></td>
                                        <td align="center" height="25" width="100" valign="middle"><bean:write name="rel" property="dsCanal"/></td>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>

                <table width="100%">
                    <tr>
                        <td align="center">
                            <vivo:botao id="btFechar" width="60px" height="10px" value="Voltar" styleClass="btTemplate" onclick="location.href='relatorioAcessosCliente.do'"/>
                        </td>
                    </tr>
                </table>

            </form>

        </vivo:sessao>
    </netui-template:section>
</netui-template:template>