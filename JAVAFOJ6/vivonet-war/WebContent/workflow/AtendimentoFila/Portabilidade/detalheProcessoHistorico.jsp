<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="detalhesProcessoForm"
             type="workflow.AtendimentoFila.Portabilidade.formBeans.DetalhesProcessoForm" />

<div id="clienteWrapper">

	<img src="<%=request.getContextPath()%>/resources/images/bt_histport_nrml.gif" style="cursor:pointer" onmouseup="CarregaAba('detalheHistoricoPortabilidade')" />

    <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="100" tableWidth="750" styleId="tbProcessoHistorico" sortable="true" onRowClick="getComentarioProcesso(this);">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Grupo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Atendente</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="15%" type="string">Operação</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="30%" type="string">Estado / Subestado</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="15%" type="string">Data</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows scroll="false">
            <logic:iterate id="historicoProcesso" name="Form" property="historicoProcesso">
                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn><bean:write name="historicoProcesso" property="dsGrupo" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="historicoProcesso" property="nmNome" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="historicoProcesso" property="dsOperacao" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="historicoProcesso" property="dsEstado" /> / 
                        <bean:write name="historicoProcesso" property="dsSubEstado" />
                        <input type="hidden" class="classIdAtendimento" value="<bean:write name="historicoProcesso" property="dsComentario" />" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="historicoProcesso" property="dtTratamento" /></vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
    <div id="processosComentarios"></div>
</div>

<style type="text/css">
    #clienteWrapper {
        margin:5px;
    }
    #processosComentarios {
        width:766px;
        border:1px solid #ccc;
        background:#fff;
        margin:5px 0 0 0;
        height:57px;
        padding:5px;
        overflow-x:hidden;
        overflow-y:auto;
    }
</style>