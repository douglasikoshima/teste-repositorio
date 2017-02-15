<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaFaturamentoPos"
             type="cliente.TelaInicial.DetalheFatura.DetalheFaturaController.LupaFaturamentoPosForm" />

<%
String altura = String.valueOf(Form.getPesquisaDetalhada().length * 28);
%>

<vivo:tbTable selectable="true" layoutWidth="1000" layoutHeight="<%=altura%>" tableWidth="1018" styleId="tbResultadoPesquisa" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Celular Origem</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Número Discado</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="date">Data</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Hora início</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Tipo de Ligação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Localização</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Detalhe da Ligação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Uso</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Operadora</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Tarifação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Quantidade</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Valor</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="false">
        <logic:iterate id="chamada" name="Form" property="pesquisaDetalhada" indexId="idx" type="com.indracompany.ws.faturaservice.Chamada">
        <vivo:tbRow key="linha">
            <vivo:tbRowColumn><bean:write name="chamada" property="numeroOrigem" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="numeroDestino" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="dataChamadaFormatada" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="horaChamadaFormatada" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="tipoChamada.descricao" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="areaChamada.descricao" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="tipoDetalheChamada.descricao" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="tipoServicoChamada.descricao" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="destinoChamadaDescricao" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="tipoTarifa.descricao" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="duracaoFormatada" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="chamada" property="valorFormatado" /></vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
<table width="1000" cellpadding="1" cellspacing="0">
    <tr>
        <td width="7%"></td>
        <td width="7%"></td>
        <td width="7%"></td>
        <td width="7%"></td>
        <td width="7%"></td>
        <td width="10%"></td>
        <td width="10%"></td>
        <td width="10%"></td>
        <td width="7%"></td>
        <td width="7%"></td>
        <td width="7%" align="center"><strong>Total:</strong></td>
        <td width="7%" align="center">
            <strong><bean:write name="Form" property="valorTotalFaturamento" /></strong>
        </td>
    </tr>
</table>

<script type="text/javascript">
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
    <logic:equal name="Form" property="atualizacaoProtocolo" value="true">
    alert('Protocolo <bean:write name="Form" property="nrProtocolo" /> gerado.');
    top.frameApp.updateProtocolo('<bean:write name="Form" property="nrProtocolo" />');
    top.frameApp.nrProtocoloScreenPop = '';
    </logic:equal>
</script>

<vivo:alert atributo="msgErro" scope="request" />