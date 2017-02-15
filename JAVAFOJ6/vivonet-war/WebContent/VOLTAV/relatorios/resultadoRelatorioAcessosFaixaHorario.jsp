<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="br.com.vivo.report.bean.BarChartBean"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<%@ taglib uri="BarChartGen.tld" prefix="bcg"%>

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
    
        <vivo:sessao id="qdMain" height="475" width="790" label="Acessos por Faixa de Horário" operacoes="Relatório">
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
								<td bgcolor="#FFFFFF">&nbsp;de <bean:write name="form" property="dataInicio" /> à <bean:write name="form" property="dataFim" /></td>
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
								<td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Sumarizado por:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmSumarizado" /></td>
							</tr>
							<tr>
								<td bgcolor="#ededed"><b>Agrupado por Linha:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmAgrupado"/></td>
                                <td bgcolor="#ededed"><b>Tecnologia:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmTecnologia"/></td>
							</tr>
							<tr>
								<td bgcolor="#ededed"><b>Loja:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="nmLoja"/></td>
								<td bgcolor="#ededed"><b>Terminal:</b></td>
								<td bgcolor="#FFFFFF">&nbsp;<bean:write name="form" property="ipTerminalSelecionado"/></td>
							</tr>
							</table>
						</td>
				</tr>
				</table>
            <form action="gerarRelatorioAcessos.do" method="post" name="relatorioAcessosForm">
				<div id="idVOL" style="display:<bean:write name="relLayout" property="displayVOL" />">
					<bcg:barChart structure='<%= (BarChartBean)request.getAttribute("graficoAcessoFaixaHorarioVOL") %>'/>
				</div>
				<div id="idTAV" style="display:<bean:write name="relLayout" property="displayTAV" />">	
					<bcg:barChart structure='<%= (BarChartBean)request.getAttribute("graficoAcessoFaixaHorarioTAV") %>'/>
				</div>	
                <br>

               
                <!--

                <table border="0" cellpadding="0" cellspacing="0" bgcolor="#adadad">
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="1" style="font-family: Tahoma, Arial; font-size: 10px; font-weight: bold; color: #ffffff; border: #D4D7DE 1px solid;">
                                <tr bgcolor="#545454">
                                    <td align="center" height="25" width="100">HORA</td>
                                    <td align="center" height="25" width="100">VOL</td>
                                    <td align="center" height="25" width="100">TAV</td>
                                </tr>
                            </table>
                            <table border="0" cellpadding="0" cellspacing="1" style="font-family: Tahoma, Arial; font-size: 10px; color: #000000; border: #D4D7DE 1px solid;">
                                <netui-data:repeater dataSource="{pageContext.form.relatorioAcessoFaixaHorario}">
                                    <tr bgcolor="#E3ECF4">
                                        <td align="center" height="25" width="100" valign="middle"><netui:content value="{container.item.hrRegistroHistorico}"/></td>
                                        <td align="center" height="25" width="100" valign="middle"><netui:content value="{container.item.qtVOL}"/></td>
                                        <td align="center" height="25" width="100" valign="middle"><netui:content value="{container.item.qtTAV}"/></td>
                                    </tr>
                                </netui-data:repeater>
                            </table>
                        </td>
                    </tr>
                </table>
                
                -->

                <br>

                <table width="100%">
                    <tr>
                        <td align="center">
                            <vivo:botao id="btFechar" width="60px" height="10px" value="Voltar" styleClass="btTemplate" onclick="location.href='relatorioAcessos.do'"/>
                        </td>
                    </tr>
                </table>

            </form>
    
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>