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

<div id="fidelizacaoWrapper">

    <vivo:quadro id="divMatrizOfertas" height="111" width="743" label="Matriz de Oferta" scroll="no">

        <html:hidden name="Form" property="idIntencaoSelecionada" styleId="idIntencaoSelecionada" />
        <html:hidden name="Form" property="idDestinoSelecionado" styleId="idDestinoSelecionado" />

        <table width="100%" id="tableMatrizOfertas">
            <thead>
                <tr>
                    <td align="center">Ofertas disponíveis</td>
                    <td></td>
                    <td align="center">Ofertas realizadas</td>
                    <td></td>
                    <td align="center">Oferta aceita</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <html:select name="Form" styleId="listaOfertasDisponiveis" property="ofertasDisponiveis" multiple="multiple" ondblclick="transfereSelecaoLista($('listaOfertasDisponiveis'),$('listaOfertasRealizadas'));">
                            <logic:iterate name="Form" property="listaOfertas.itemListaDescricaoVOArray" id="iteratorListaOfertas">
                            <option value="<bean:write name="iteratorListaOfertas" property="id" />">
                                <bean:write name="iteratorListaOfertas" property="descricao1" />
                            </option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td align="center" valign="middle">
                        &nbsp;<a href="javascript:transfereSelecaoLista($('listaOfertasDisponiveis'),$('listaOfertasRealizadas'));"><img vspace="5" id="imgRightRegionais" src="<%=request.getContextPath()%>/resources/images/bt_rightaln_nrml.gif" style="clear:both;" border="0" /></a>
                        &nbsp;<a href="javascript:transfereSelecaoLista($('listaOfertasRealizadas'),$('listaOfertasDisponiveis'));"><img vspace="5" id="imgLeftRegionais" src="<%=request.getContextPath()%>/resources/images/bt_leftaln_nrml.gif" style="clear:both;margin-bottom:5px;" border="0" /></a><br/>
                    </td>
                    <td>
                        <html:select name="Form" styleId="listaOfertasRealizadas" property="ofertasRealizadas" multiple="multiple" ondblclick="transfereSelecaoLista($('listaOfertasRealizadas'),$('listaOfertasAceitas'));">
                            <logic:iterate name="Form" property="listaOfertasRealizadas.itemListaDescricaoVOArray" id="iteratorListaOfertasRealizadas">
                            <option value="<bean:write name="iteratorListaOfertasRealizadas" property="id" />">
                                <bean:write name="iteratorListaOfertasRealizadas" property="descricao1" />
                            </option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td align="center" valign="middle">
                        &nbsp;<a href="javascript:transfereSelecaoLista($('listaOfertasRealizadas'),$('listaOfertasAceitas'));"><img vspace="5" id="imgRightRegionais" src="<%=request.getContextPath()%>/resources/images/bt_rightaln_nrml.gif" style="clear:both;" border="0" /></a>
                        &nbsp;<a href="javascript:transfereSelecaoLista($('listaOfertasAceitas'),$('listaOfertasRealizadas'));"><img vspace="5" id="imgLeftRegionais" src="<%=request.getContextPath()%>/resources/images/bt_leftaln_nrml.gif" style="clear:both;margin-bottom:5px;" border="0" /></a><br/>
                    </td>
                    <td>
                        <html:select styleId="listaOfertasAceitas" name="Form" property="ofertasAceitas" multiple="multiple" ondblclick="transfereSelecaoLista($('listaOfertasAceitas'),$('listaOfertasRealizadas'));">
                            <logic:iterate name="Form" property="listaOfertasAceitas.itemListaDescricaoVOArray" id="iteratorListaOfertasAceitas">
                            <option value="<bean:write name="iteratorListaOfertasAceitas" property="id" />">
                                <bean:write name="iteratorListaOfertasAceitas" property="descricao1" />
                            </option>
                            </logic:iterate>
                        </html:select>
                    </td>
                </tr>
            </tbody>
        </table>
    </vivo:quadro>
</div>

<jsp:include page="fidelizacaoNavigation.jsp"/>

<script type="text/javascript" language="javascript">

    <logic:iterate name="Form" property="listaOfertas.itemListaDescricaoVOArray" id="iteratorListaOfertas">
    hashTiposMatriz.set(<bean:write name="iteratorListaOfertas" property="id" />,'<bean:write name="iteratorListaOfertas" property="sigla" />');
    </logic:iterate>

    <logic:iterate name="Form" property="listaOfertasRealizadas.itemListaDescricaoVOArray" id="iteratorListaOfertasRealizadas">
    hashTiposMatriz.set(<bean:write name="iteratorListaOfertasRealizadas" property="id" />,'<bean:write name="iteratorListaOfertasRealizadas" property="sigla" />');
    </logic:iterate>

    <logic:iterate name="Form" property="listaOfertasAceitas.itemListaDescricaoVOArray" id="iteratorListaOfertasAceitas">
    hashTiposMatriz.set(<bean:write name="iteratorListaOfertasAceitas" property="id" />,'<bean:write name="iteratorListaOfertasAceitas" property="sigla" />');
    </logic:iterate>

    proximaOperacao = 'fidelizacaoRedirecionamento';
    transfereSelecaoLista = function(listaOrigem, listaDestino) {
        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
        var i;
        var c = 0;
        var arrayTransferidos = new Array();
        for(i = 0; i < listaOrigem.options.length; i++) {
            if (listaOrigem.options[i].selected) {
                if (listaDestino.id == 'listaOfertasAceitas') {
                    if (listaDestino.options.length == 0) {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        arrayTransferidos[c] = i;
                        c++;
                    }
                } else {
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    arrayTransferidos[c] = i;
                    c++;
                }
            }
        }
        for (var j = arrayTransferidos.length-1; j >= 0; j--) {
            listaOrigem.options[arrayTransferidos[j]] = null;
        }
        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
    }
</script>