<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioAcessosForm" />
<bean:define id="relLayout"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioLayout" />


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

        <vivo:sessao id="qdMain" height="475" width="790" label="Acessos Negado" operacoes="Relatório">

            <form action="gerarRelatorioAcessosNegado.do" method="post" name="relatorioAcessosForm">

                <br>

                <table border="0" cellpadding="0" cellspacing="0" bgcolor="#adadad">
				<tr>
						<td colspan="6">
							<table width="100%" cellpadding="0" cellspacing="1" border="0">
							<tr bordercolor="#FFFFFF">
								 <td rowspan="8" bgcolor="#FFFFFF"><img src="/FrontOfficeWeb/resources/images/logo_relatorio.jpg"></td>
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
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Carteira:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmCarteira" /></td>
							</tr>
							<tr>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Segmento:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmSegmento" /></td>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Período de Datas</b></td>
								<td bgcolor="#FFFFFF">&nbsp;de <bean:write name="form" property="dataInicio" /> a <bean:write name="form" property="dataFim" /></td>
							</tr>
							<tr>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Canal:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmCanal" /></td>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Tipo Linha:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmTipoLinha" /></td>
							</tr>
							<tr>
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Número da linha:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmNumeroRegistro" /></td>
								<td bgcolor="#ededed"><b>Agrupado por Linha:</b></td>
								<td colspan="4" bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmAgrupado"/></td>
							</tr>
							<tr>
                                <td bgcolor="#ededed"><b>Tecnologia:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmTecnologia"/></td>
								<td bgcolor="#ededed"><b>Loja:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmLoja"/></td>
							</tr>
							<tr>
                                <td bgcolor="#ededed" colspan="2"></td>
								<td bgcolor="#ededed"><b>Terminal:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="ipTerminalSelecionado"/></td>
							</tr>
							</table>
						</td>
					</tr>
                    <tr>
                        <td>
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-family: Tahoma, Arial; font-size: 10px; font-weight: bold; color: #ffffff; border: #D4D7DE 1px solid;">
                                <tr bgcolor="#545454">
                                    <td align="center" width="11%" rowspan="3">DIA</td>
                                    <td align="center" style="display:<bean:write name="relLayout" property="apenasTAV" />" rowspan="3">Loja</td>
                                    <td align="center" width="11%" style="display:<bean:write name="relLayout" property="apenasTAV" />" rowspan="3">Terminal</td>
                                    <td align="center" width="11%" rowspan="3">Total</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayVOL" />" height="25" colspan="<bean:write name="relLayout" property="colspanVOL" />">VOL</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayTAV" />" height="25" colspan="<bean:write name="relLayout" property="colspanTAV" />">TAV</td>
                                </tr>
                                 <tr bgcolor="#545454">
									<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPRE" />" height="25" colspan="2">Pré-Pago (<bean:write name="relLayout" property="tipoTecnologia" />)</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPOS" />" height="25" colspan="2">Pós-Pago (<bean:write name="relLayout" property="tipoTecnologia" />)</td>
                                    <td align="center" style="display:<bean:write name="relLayout" property="displayVOLCON" />" height="25" colspan="2">Controle (<bean:write name="relLayout" property="tipoTecnologia" />)</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPRE" />" height="25" colspan="2">Pré-Pago (<bean:write name="relLayout" property="tipoTecnologia" />)</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPOS" />" height="25" colspan="2">Pós-Pago (<bean:write name="relLayout" property="tipoTecnologia" />)</td>
                                    <td align="center" style="display:<bean:write name="relLayout" property="displayTAVCON" />" height="25" colspan="2">Controle (<bean:write name="relLayout" property="tipoTecnologia" />)</td>
                                </tr>
								<tr bgcolor="#545454">
									<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPRE" />" height="25" width="74">Cliente</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPRE" />" height="25" width="74">Usuário</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPOS" />" height="25" width="74">Cliente</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPOS" />" height="25" width="74">Usuário</td>
                                    <td align="center" style="display:<bean:write name="relLayout" property="displayVOLCON" />" height="25" width="74">Cliente</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayVOLCON" />" height="25" width="74">Usuário</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPRE" />" height="25" width="74">Cliente</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPRE" />" height="25" width="74">Usuário</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPOS" />" height="25" width="74">Cliente</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPOS" />" height="25" width="74">Usuário</td>
                                    <td align="center" style="display:<bean:write name="relLayout" property="displayTAVCON" />" height="25" width="74">Cliente</td>
									<td align="center" style="display:<bean:write name="relLayout" property="displayTAVCON" />" height="25" width="74">Usuário</td>
								</tr>
                            </table>
                            <table border="0" width="100%" cellpadding="0" cellspacing="1" style="font-family: Tahoma, Arial; font-size: 10px; color: #000000; border: #D4D7DE 1px solid;">
                                 <netui-data:repeater dataSource="{pageContext.form.relatorioAcessoNegado}">
                                     <netui-data:repeaterItem>
										 <tr bgcolor="#E3ECF4">
                                            <td align="center" height="25" width="11%" valign="middle"><netui:content value="{container.item.dtRegistroHistorico}"/></td>
                                            <td style="display:<bean:write name="relLayout" property="apenasTAV" />"><netui:content value="{container.item.dsLoja}"/>&nbsp;</td>
                                            <td width="11%" style="display:<bean:write name="relLayout" property="apenasTAV" />"><netui:content value="{container.item.nrTerminal}"/>&nbsp;</td>
                                            <td align="center" height="25" width="11%" valign="middle"><netui:content value="{container.item.qtTotal}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPRE" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtVOLPreCliente}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPRE" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtVOLPreUsuario}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPOS" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtVOLPosCliente}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPOS" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtVOLPosUsuario}"/></td>
                                            <td align="center" style="display:<bean:write name="relLayout" property="displayVOLCON" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtVOLConCliente}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLCON" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtVOLConUsuario}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPRE" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtTAVPreCliente}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPRE" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtTAVPreUsuario}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPOS" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtTAVPosCliente}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPOS" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtTAVPosUsuario}"/></td>
                                            <td align="center" style="display:<bean:write name="relLayout" property="displayTAVCON" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtTAVConCliente}"/></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVCON" />" height="25" width="74" valign="middle"><netui:content value="{container.item.qtTAVConUsuario}"/></td>
                                        </tr>
                                    </netui-data:repeaterItem>
                                    <netui-data:repeaterFooter>
										 <tr bgcolor="#E3ECF4">
                                            <td align="center" colspan="<bean:write name="relLayout" property="colspanTotal" />" height="25" width="11%" valign="middle"></td>
											<td align="center" height="25" width="11%" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[0]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPRE" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[1]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPRE" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[2]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPOS" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[3]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLPOS" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[4]}"/></b></td>
                                            <td align="center" style="display:<bean:write name="relLayout" property="displayVOLCON" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[5]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayVOLCON" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[6]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPRE" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[7]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPRE" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[8]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPOS" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[9]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVPOS" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[10]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVCON" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[11]}"/></b></td>
											<td align="center" style="display:<bean:write name="relLayout" property="displayTAVCON" />" height="25" width="74" valign="middle"><b><netui:content value="{pageContext.form.totaisRelatorioAcessoNegado[12]}"/></b></td>
                                        </tr>
                                    </netui-data:repeaterFooter>
                                </netui-data:repeater>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>

                <table width="100%">
                    <tr>
                        <td align="center">
                            <vivo:botao id="btFechar" width="60px" height="10px" value="Voltar" styleClass="btTemplate" onclick="location.href='relatorioAcessosNegado.do'"/>
                        </td>
                    </tr>
                </table>

            </form>

        </vivo:sessao>
    </netui-template:section>
</netui-template:template>