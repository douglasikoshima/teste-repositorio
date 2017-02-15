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
    <div id="divTipoCliente">
        Tipo de Cliente: 
        <span id="dsTipoCliente" style="color:#1865c5;">
        <logic:equal name="Form" property="dadosCliente.idTipoPessoa" value="0">
            NÃO CLASSIFICADO
        </logic:equal>
        <logic:equal name="Form" property="dadosCliente.idTipoPessoa" value="1">
            PESSOA FÍSICA
        </logic:equal>
        <logic:equal name="Form" property="dadosCliente.idTipoPessoa" value="2">
            PESSOA JURÍDICA
        </logic:equal>
        </span>
    </div>
    <div id="divSaldoPontos">
        Saldo Total de Pontos: <span id="qtPontos" style="width:65px;color:#1865c5;"></span>
        <img align="absmiddle" src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif" border="0" onmouseup="getSaldoPontos()" style="cursor:pointer;" />
    </div>
    <vivo:abaGrupo id="abasDetalheRetencao" width="250" height="10" styleClass="abasRetencao">
        <vivo:abaItem id="abaHistoricoRetencao" onclick="abaSelected($('abasDetalheRetencao'), abaHistoricoRetencao);CarregaAba('detalheHistoricoRetencao');" value="Histórico de Retenção" select="S"/>
        <% if (!"TELAATENDIMENTO".equals(Form.getDsOrigem()) && 
        !"CANCELADO".equals(Form.getDadosProcesso().getWFEstadoVO().getDsEstado())
        && !"FECHADO".equals(Form.getDadosProcesso().getWFEstadoVO().getDsEstado())) { %>
        <vivo:abaItem id="abaRetencao" onclick="abaSelected($('abasDetalheRetencao'), abaRetencao);CarregaAba('fluxoRetencao');" value="Retenção" />        
        <% } %>
    </vivo:abaGrupo>
    <div id="containerRetencao"></div>
</div>

<style type="text/css">
    #divTipoCliente {
        float:left;
    }
    #clienteWrapper {
        margin:10px;
    }
    #divSaldoPontos {
        float:right;
    }
    .abasRetencao {
        margin-top:10px;
        clear:both;
    }
    #containerRetencao {
        width:757px;
        height:324px;
        background:#ededed;
        border:1px solid #adadad;
        overflow:hidden;
    }
    #fidelizacaoWrapper {
        padding:5px;
        overflow:auto;
    }
    #fidelizacaoNavigation {
        height:30px;
    }
    #fidelizacaoNavigation {
        bottom:0;
        display:block;
        width:100%;
    }
</style>