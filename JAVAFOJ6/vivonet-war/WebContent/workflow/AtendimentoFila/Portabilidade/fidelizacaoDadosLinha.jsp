<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="fidelizacaoForm"
             type="workflow.AtendimentoFila.Portabilidade.PortabilidadeController.FidelizacaoForm" />

<bean:define id="DadosLinha"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="detalhesProcessoForm.dadosLinha"
             type="br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO" />

<script type="text/javascript" language="javascript">
    operacaoAtual = '<bean:write name="Form" property="destino" />';
</script>

<vivo:tbTable styleId="fidelizacaoDadosLinha" layoutHeight="35" tableWidth="755" layoutWidth="740">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Linha</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="date">Segmentação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Rentabilidade</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="16%" type="string">Contrato de Fidelização</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Multa Contratual</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Término Contrato</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Plano Serviço</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Habilitação</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <vivo:tbRow key="linha1">
            <vivo:tbRowColumn><bean:write name="DadosLinha" property="numero" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="DadosLinha" property="segmentacao" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="DadosLinha" property="rentabilidade" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="DadosLinha" property="contrato" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="DadosLinha" property="valorMulta" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="DadosLinha" property="dtFimContrato" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="DadosLinha" property="plano" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="DadosLinha" property="dtHabilitacao" /></vivo:tbRowColumn>
        </vivo:tbRow>
    </vivo:tbRows>
</vivo:tbTable>