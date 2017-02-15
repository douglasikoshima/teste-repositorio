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

<vivo:tbTable selectable="true" layoutWidth="717" layoutHeight="100" tableWidth="717" styleId="tbListaAparelhos" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="4%" type="string">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="16%" type="string">Manual</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="16%" type="string">Marca</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="16%" type="string">Modelo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="16%" type="string">Cor</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="16%" type="string">Multa</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="16%" type="string">Preço</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate indexId="c" id="iteratorAparelhos" name="Form" property="ofertaAparelhoVO.itemOfertaAparelhoVOArray">
            <vivo:tbRow key="linha">
                <vivo:tbRowColumn><input class="checkbox" type="radio" name="radioAparelhos" id="aparelhoIndex<%=c%>" onclick="flAparelhoSelecionado=true;getListaDescontosParcelas(<%=c%>)" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iteratorAparelhos" property="dsManual" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iteratorAparelhos" property="nmMarca" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iteratorAparelhos" property="modelo" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iteratorAparelhos" property="cor" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iteratorAparelhos" property="multa" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iteratorAparelhos" property="preco" /></vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>

<% if (Form.getOfertaAparelhoVO().getFidelizacaoListaGeralVO().getItemListaVOArray().length > 0) { %>
<script type="text/javascript" language="javascript">

    while ($('idTipoPagamentoAparelho').options.length > 0) {
        $('idTipoPagamentoAparelho').options[0] = null;
    }
    $('idTipoPagamentoAparelho').options[0] = new Option('-- Selecione --','');
    <% for (int i = 0; i < Form.getOfertaAparelhoVO().getFidelizacaoListaGeralVO().getItemListaVOArray().length; i++) { %>
    $('idTipoPagamentoAparelho').options[$('idTipoPagamentoAparelho').options.length] = new Option(
        '<%= Form.getOfertaAparelhoVO().getFidelizacaoListaGeralVO().getItemListaVOArray(i).getDescricao() %>',
        '<%= Form.getOfertaAparelhoVO().getFidelizacaoListaGeralVO().getItemListaVOArray(i).getId() %>'
    );
    <% }  %>

    if ($('prazoVigencia')) {
        while ($('prazoVigencia').options.length > 0) {
            $('prazoVigencia').options[0] = null;
        }
        $('prazoVigencia').options[0] = new Option('-- Selecione --','');
        <%
        if (Form.getOfertaAparelhoVO().getListaPrazoVigenciaVO() != null) {
            for (int i = 0; i < Form.getOfertaAparelhoVO().getListaPrazoVigenciaVO().getItemListaVOArray().length; i++) { %>
        $('prazoVigencia').options[$('prazoVigencia').options.length] = new Option(
            '<%= Form.getOfertaAparelhoVO().getListaPrazoVigenciaVO().getItemListaVOArray(i).getDescricao() %>',
            '<%= Form.getOfertaAparelhoVO().getListaPrazoVigenciaVO().getItemListaVOArray(i).getId() %>'
        );
        <%  }
        } %>
    }

</script>
<% }  %>