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
             property="fidelizacaoForm"
             type="workflow.AtendimentoFila.Portabilidade.PortabilidadeController.FidelizacaoForm" />

<jsp:include page="fidelizacaoDadosLinha.jsp" />

<div style="margin:10px 0 0 10px">
<logic:equal name="Form" property="destino" value="fidelizacaoIntencaoCancelamento">
    <strong>Qual a intenção do cancelamento?</strong>
</logic:equal>
<logic:equal name="Form" property="destino" value="fidelizacaoDestinoPrevisto">
    <strong>Qual o destino previsto?</strong>
</logic:equal>
</div>

<html:hidden name="Form" property="idIntencaoSelecionada" styleId="idIntencaoSelecionada" />
<html:hidden name="Form" property="idDestinoSelecionado" styleId="idDestinoSelecionado" />

<div id="fidelizacaoWrapper" style="margin:0;padding:0;clear:both;" onselectstart="return false;"><br/>
    <logic:equal name="Form" property="destino" value="fidelizacaoIntencaoCancelamento">
        <script type="text/javascript" language="javascript">proximaOperacao = 'fidelizacaoDestinoPrevisto';</script>
        <ul class="ulListasRetencao">
        <logic:iterate id="iteratorIntencoes" name="Form" property="listaIntencoesCancelamento.itemListaVOArray" type="br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO">
            <li>
                <input onclick="$('idIntencaoSelecionada').value = this.value;"
                       id="radio<bean:write name="iteratorIntencoes" property="id" />"
                       class="radio intencoesSelecionadas"
                       type="radio"
                       name="idIntencaoSelecionada"
                       value="<bean:write name="iteratorIntencoes" property="id" />"
                       <logic:equal name="Form" property="idIntencaoSelecionada" value="<%=iteratorIntencoes.getId()%>"> checked="checked" </logic:equal> />
                <label ondblclick="FidelizacaoFlowController()" for="radio<bean:write name="iteratorIntencoes" property="id" />"><bean:write name="iteratorIntencoes" property="descricao" /></label>
            </li>
        </logic:iterate>
        </ul>
    </logic:equal>
    <logic:equal name="Form" property="destino" value="fidelizacaoDestinoPrevisto">
        <script type="text/javascript" language="javascript">proximaOperacao = 'fidelizacaoMatrizOfertas';</script>
        <ul class="ulListasRetencao">
        <logic:iterate id="iteratorDestinosPrevistos" name="Form" property="listaDestinosPrevistos.itemListaVOArray" type="br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO">
            <li>
                <input onclick="$('idDestinoSelecionado').value = this.value;"
                       id="radio<bean:write name="iteratorDestinosPrevistos" property="id" />"
                       class="radio destinosSelecionados"
                       type="radio"
                       name="idIntencaoSelecionada"
                       value="<bean:write name="iteratorDestinosPrevistos" property="id" />"
                       <logic:equal name="Form" property="idDestinoSelecionado" value="<%=iteratorDestinosPrevistos.getId()%>"> checked="checked" </logic:equal> />
                <label ondblclick="FidelizacaoFlowController()" for="radio<bean:write name="iteratorDestinosPrevistos" property="id" />"><bean:write name="iteratorDestinosPrevistos" property="descricao" /></label>
            </li>
        </logic:iterate>
        </ul>
    </logic:equal>
</div>

<jsp:include page="fidelizacaoNavigation.jsp"/>