<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.fidelizacao.vo.ListaDetalheLinhaVODocument.ListaDetalheLinhaVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="fidelizacaoForm"
             type="workflow.AtendimentoFila.Portabilidade.PortabilidadeController.FidelizacaoForm" />

<bean:define id="linhasAssociadasTratadas" 
             name="Form"
             property="listaLinhasAssociadasTratadas"
             type="br.com.vivo.fo.fidelizacao.vo.ListaDetalheLinhaVODocument.ListaDetalheLinhaVO" />

<%!

private boolean isPresent(String nrLinha, ListaDetalheLinhaVO listaLinhasAssociadas) {
    boolean blValue = false;
    for (int i = 0; i < listaLinhasAssociadas.getDetalheLinhaVOArray().length; i++) {
        if (listaLinhasAssociadas.getDetalheLinhaVOArray(i).getNumero().equals(nrLinha)) {
            blValue = true;
            break;
        }
    }
    return blValue;
}
%>

<%
boolean flNovaOfertaProcessosAgrupados = ("true".equals(request.getParameter("flNovaOfertaProcessosAgrupados"))) ? true : false;
%>

<div id="divSelecaoProcessosAgrupados">
    <vivo:tbTable selectable="true" layoutWidth="580" layoutHeight="190" tableWidth="580" styleId="tbSelecaoLinhasAssociadas" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">&nbsp;</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="25%" type="string">Linha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="25%" type="string">Conta</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="40%" type="string">Protocolo de Portabilidade</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="iteratorLinhasAssociadas"
                           name="Form"
                           property="listaLinhasAssociadas.detalheLinhaVOArray"
                           type="br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO">

                <% if (!isPresent(iteratorLinhasAssociadas.getNumero(), linhasAssociadasTratadas)) { %>

                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn>
                        <input name="processosAgrupados"
                               onclick="selecionaProcessosAgrupados(this)"
                               <% if (flNovaOfertaProcessosAgrupados) { %>
                               type="radio"
                               <% } else {%>
                               type="checkbox"
                               <% } %>
                               value="<bean:write name="iteratorLinhasAssociadas" property="numero" />"
                               class="checkbox" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="iteratorLinhasAssociadas" property="numero" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="iteratorLinhasAssociadas" property="nrConta" /></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="iteratorLinhasAssociadas" property="nrProtocoloPortabilidade" /></vivo:tbRowColumn>
                </vivo:tbRow>

                <% } %>

            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>

    <div id="divSelectAll" style="float:left;padding:6px 0 0 7px0;font-size:10px;font-weight:bold;display:none;">
        <input id="inputSelAll" type="checkbox" class="selAll" style="border:none;background:none;width:10px;height:10px;" onclick="checkProcessosAgrupados()" /> 
        <label style="margin-top:10px;vertical-align: middle" id="labelSelAll" for="inputSelAll">Selecionar todos</label>
    </div>
    <div style="float:right;padding:6px 7px 0 0;">
        <a style="padding-right:20px" href="javascript:selecionarProcessoAgrupado()"><img src="<%= request.getContextPath() %>/resources/images/bt_selecionar_nrml.gif" border="0"></a>
    </div>

</div>

<script type="text/javascript" language="javascript">
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
    <% if (flNovaOfertaProcessosAgrupados) { %>
        $('divSelectAll').hide();
    <% } else {%>
        $('divSelectAll').show();
    <% } %>
</script>