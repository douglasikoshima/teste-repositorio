<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="tiForm"
             type="cliente.TelaInicial.TelaInicialController.TIForm"/>

<html>

<head>
    <script type="text/javascript" language="javascript">

		if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

        closePortabilidade = function() {
            $('historicoPortabilidade').remove();
        }

    </script>

</head>

<body>

<div id="bodyHistoricoPortabilidade">

    <% if (request.getAttribute("msgErro") == null ) { %>

    <table width="650" height="117" cellspacing="0" cellpadding="2">
		<tr>
			<td width="162">Bilhete de Portabilidade:</td>
			<td width="105"><strong><bean:write name="Form" property="portabilidade.nrBilhetePortabilidade" /></strong></td>
			<td width="60">Operação:</td>
			<td width="61"><strong><bean:write name="Form" property="portabilidade.tpPortabilidade" /></strong></td>
			<td width="120">Data de solicitação:</td>
			<td width="142"><strong><bean:write name="Form" property="portabilidade.dtSolicitacao" /></strong></td>
		</tr>
		<tr>
			<td>Operadora Doadora/Receptora:</td>
			<td><strong><bean:write name="Form" property="portabilidade.nmOperadora" /></strong></td>
			<td colspan="2"></td>
			<td>Data / hora da Janela:</td>
			<td><strong><bean:write name="Form" property="portabilidade.dtJanela" /></strong></td>
		</tr>
		<tr>
			<td colspan="6" valign="top" style="padding-top:15px">
				<vivo:tbTable selectable="true" onRowClick="" layoutWidth="610" layoutHeight="231" tableWidth="610" styleId="tableHistoricoPortabilidade" sortable="true">
					<vivo:tbHeader>
						<vivo:tbHeaderColumn align="center" width="33%" type="string">Estado</vivo:tbHeaderColumn>
						<vivo:tbHeaderColumn align="center" width="34%" type="string">Data / Hora</vivo:tbHeaderColumn>
						<vivo:tbHeaderColumn align="center" width="33%" type="string">Observação</vivo:tbHeaderColumn>
					</vivo:tbHeader>
					<vivo:tbRows>
						<logic:iterate id="historicoPortabilidade" name="Form" property="portabilidade.historicoArray">
							<vivo:tbRow key="linha">
								<vivo:tbRowColumn><bean:write name="historicoPortabilidade" property="dsEstado" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="historicoPortabilidade" property="dtAbertura" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="historicoPortabilidade" property="dsObservacao" /></vivo:tbRowColumn>
							</vivo:tbRow>
						</logic:iterate>
					</vivo:tbRows>
				</vivo:tbTable>
			</td>
		</tr>
	</table>

    <% } %>

</div>

<style type="text/css">
    #bodyHistoricoPortabilidade {
        background-color:#ededed;
        padding:5px;
        font-size:11px;
        margin:0;
    }
</style>

<vivo:alert scope="request" atributo="msgErro" afterFunction="closePortabilidade()" />

</body>

</html>