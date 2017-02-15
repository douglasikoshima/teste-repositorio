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

    <dl>
        <dt>Nome do cliente</dt>
        <dd>
            <logic:present name="Form" property="dadosCliente.nome">
            <bean:write name="Form" property="dadosCliente.nome" />
            </logic:present>
        </dd>
        <dt>Segmentação</dt>
        <dd>
            <logic:present name="Form" property="dadosCliente.segmentacaoVO.descricao">
            <bean:write name="Form" property="dadosCliente.segmentacaoVO.descricao" />
            </logic:present>
        </dd>
        <dt>Carteirização</dt>
        <dd>
            <logic:present name="Form" property="dadosCliente.carterizacaoVO.descricao" >
            <bean:write name="Form" property="dadosCliente.carterizacaoVO.descricao" />
            </logic:present>
        </dd>
        <dt>Nome do Contato</dt>
        <dd>
            <logic:present name="Form" property="dadosContato.nmContato">
            <bean:write name="Form" property="dadosContato.nmContato" />
            </logic:present>
        </dd>
        <dt>Tel do Contato</dt>
        <dd>
            <logic:present name="Form" property="dadosContato.nrTelefone">
            <bean:write name="Form" property="dadosContato.nrTelefone" />
            </logic:present>
        </dd>
        <dt>Conta</dt>
        <dd>
            <logic:present name="Form" property="dadosContato.nrConta">
            <bean:write name="Form" property="dadosContato.nrConta" />
            </logic:present>
        </dd>
        <dt>Linha</dt>
        <dd>
            <logic:present name="Form" property="dadosContato.cdAreaRegistro">
            (<bean:write name="Form" property="dadosContato.cdAreaRegistro" />) <bean:write name="Form" property="dadosContato.nrLinha" />
            </logic:present>
        </dd>
    </dl>

    <vivo:tbTable selectable="true" layoutWidth="730" layoutHeight="25" tableWidth="780" styleId="tbResultadoPesquisa" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Tipo de Retorno</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Retorno</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows scroll="false">
            <logic:iterate id="retorno" name="Form" property="dadosContato.WFAtendimentoContatoComunicVOArray">
                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn><bean:write name="retorno" property="dsTipoComunicacao" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="retorno" property="dsComunicacao" /></vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>

</div>

<style type="text/css">
    #clienteWrapper {
        margin:5px;
    }
</style>