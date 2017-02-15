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
						<td colspan="6">
							<table width="100%" cellpadding="0" cellspacing="1" border="0">
							<tr bordercolor="#FFFFFF">
								 <td rowspan="7" bgcolor="#FFFFFF"><img src="/FrontOfficeWeb/resources/images/logo_relatorio.jpg"></td>
							</tr>
							<tr>
								<td width="100" bgcolor="#ededed"><b>Data de Emissão:</b></td>
								<td width="180" bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="dtAtual"/></td>
								<td width="150" bgcolor="#ededed"><b>Operadora:</b></td>
								<td width="180" bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmOperadora"/></td>
							</tr>
							<tr>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Regional:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmRegional" /></td>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Loja:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmLoja" /></td>
							</tr>
							<tr>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Tipo de Serviço:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmTipoServico" /></td>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Status da Venda:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="dsStatusVenda" /></td>
							</tr>
							<tr>
								<td bgcolor="#ededed"><b>Recarga:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmRecarga"/></td>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Período de Datas</b></td>
								<td colspan="80" bgcolor="#FFFFFF">&nbsp;de <bean:write name="form" property="dataInicio" /> a <bean:write name="form" property="dataFim" /></td>
							</tr>
							</table>
						</td>
					</tr>
                    <tr>
                        <td>
                            <table border="0" width="100%" cellpadding="0" cellspacing="1" style="font-family: Tahoma, Arial; font-size: 10px; font-weight: bold; color: #ffffff; border: #D4D7DE 1px solid;">
                                <tr bgcolor="#545454">
                                    <td align="center" height="25" width="10%">DATA VENDA</td>
                                    <td align="center" height="25" width="20%">LOJA</td>
                                    <td align="center" height="25" width="9%">TERMINAL</td>
									<td align="center" height="25" width="10%">LINHA</td>
									<td align="center" height="25" width="20%">SERVIÇO</td>
									<td align="center" height="25" width="12%">CARTÃO</td>
									<td align="center" height="25" width="10%">VALOR R$</td>
									<td align="center" height="25" width="13%">STATUS</td>
                                </tr>
                            </table>
                            <table border="0" width="100%" cellpadding="0" cellspacing="1" style="font-family: Tahoma, Arial; font-size: 10px; color: #000000; border: #D4D7DE 1px solid;">
                                <netui-data:repeater dataSource="{pageContext.form.relatorioFinanceiro}">
                                    <tr bgcolor="#E3ECF4">
                                        <td align="center" height="25" width="10%" valign="middle"><netui:content value="{container.item.dtVenda}"/></td>
                                        <td align="center" height="25" width="20%" valign="middle"><netui:content value="{container.item.dsLoja}"/></td>
										<td align="center" height="25" width="9%" valign="middle"><netui:content value="{container.item.nrTerminal}"/></td>
										<td align="center" height="25" width="10%" valign="middle"><netui:content value="{container.item.cdAreaRegistro}"/> <netui:content value="{container.item.nrLinha}"/></td>
                                        <td align="center" height="25" width="20%" valign="middle"><netui:content value="{container.item.dsTipoServico}"/></td>
										<td align="center" height="25" width="12%" valign="middle"><netui:content value="{container.item.dsBandeiraCartao}"/></td>
                                        <td align="center" height="25" width="10%" valign="middle"><netui:content value="{container.item.vlCartao}"/></td>
                                        <td align="center" height="25" width="13%" valign="middle"><netui:content value="{container.item.dsStatusVenda}"/></td>
                                    </tr>
                                </netui-data:repeater>
								<logic:notEmpty name="form" property="vlrTotalFinanceiro">
									<tr bgcolor="#E3ECF4">
										<td align="center" height="25" width="10%" valign="middle">&nbsp;</td>
                                        <td align="center" height="25" width="20%" valign="middle">&nbsp;</td>
                                        <td align="center" height="25" width="9%" valign="middle">&nbsp;</td>
										<td align="center" height="25" width="10%" valign="middle">&nbsp;</td>
										<td align="center" height="25" width="20%" valign="middle">&nbsp;</td>
										<td align="center" height="25" width="12%" valign="middle">&nbsp;</td>
                                        <td align="center" height="25" width="10%" valign="middle"><b><netui:content value="{pageContext.form.vlrTotalFinanceiro}"/></b></td>
										<td align="center" height="25" width="13%" valign="middle">&nbsp;</td>
									</tr>
								</logic:notEmpty>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>

                <table width="100%">
                    <tr>
                        <td align="center">
                            <vivo:botao id="btFechar" width="60px" height="10px" value="Voltar" styleClass="btTemplate" onclick="location.href='relatorioFinanceiro.do'"/>
                        </td>
                    </tr>
                </table>

            </form>

        </vivo:sessao>
    </netui-template:section>
</netui-template:template>