<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="fidelizacaoForm"
             type="workflow.AtendimentoFila.Portabilidade.PortabilidadeController.FidelizacaoForm" />

<div id="fidelizacaoWrapper">

    <% if (Form.getListaMensagensEncerramento().getItemListaVOArray().length > 0) { %>
    <div id="divListaMensagensVO" style="width:743px;height:40px;overflow:auto;">
        <ul>
            <logic:iterate id="iteratorListaMensagensVO"
                           name="Form"
                           property="listaMensagensEncerramento.itemListaVOArray"
                           type="br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO">
                <li><bean:write name="iteratorListaMensagensVO" property="descricao" /></li>
            </logic:iterate>
        </ul>
    </div>
    <% } %>

    <% if (Form.getListaLinhasAssociadasTratadas().getDetalheLinhaVOArray().length == 0) { %>
    <vivo:quadro id="divObservacao" height="111" width="743" label="Observação" scroll="no">
        <html:textarea name="Form"
                       property="mensagemEncerramentoVO.dsObservacao"
                       styleId="dsObservacao"
                       style="width:730px;height:100px;font-family:Tahoma;font-size:11px;"
                       onkeyup="limitaQtdeCaracteres(this, 400)"
                       onblur="limitaQtdeCaracteres(this, 400)" />
    </vivo:quadro>
    <% } else { %>
        <div id="divObservacao"></div>
    <% } %>

</div>

<jsp:include page="fidelizacaoNavigation.jsp"/>

<script type="text/javascript" language="javascript">
    <% if (Form.getListaLinhasAssociadasTratadas().getDetalheLinhaVOArray().length == 0) { %>
    <logic:notEqual name="Form" property="retornoVO.nrProtocolo" value="">
    alert('Número de Protocolo: <bean:write name="Form" property="retornoVO.nrProtocolo" />');
    </logic:notEqual>
    <% } %>
    proximaOperacao = '<bean:write name="Form" property="operacaoAtual" />';
    $('btVoltarDetalheProcesso').hide();

    <% if (((Form.getListaOfertasAceitas() != null && Form.getListaOfertasAceitas().getItemListaDescricaoVOArray().length > 0
             && Form.getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla() != null)
           && ("APC".equals(Form.getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla())
           || "AP".equals(Form.getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla())))
           || Form.getListaLinhasAssociadasTratadas().getDetalheLinhaVOArray().length
              == Form.getListaLinhasAssociadas().getDetalheLinhaVOArray().length) { %>
    flProcessosAgrupados = false;
    <% } else { %>
    flProcessosAgrupados = true;
    <% } %>
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
</script>

<vivo:alert atributo="msgSAP" scope="request" />