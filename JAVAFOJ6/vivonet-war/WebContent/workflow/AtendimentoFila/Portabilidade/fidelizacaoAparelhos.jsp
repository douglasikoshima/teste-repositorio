<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
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

<bean:define id="DadosLinha"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="detalhesProcessoForm.dadosLinha"
             type="br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO" />

<jsp:include page="fidelizacaoDadosLinha.jsp" />

<% 
if (Form.getAparelhoSelecionado().getVlPrecoAbs() != null) {
    Form.getAparelhoSelecionado().setVlPrecoAbs(Form.getAparelhoSelecionado().getVlPrecoAbs().replaceAll("\\.","").replace(',','.'));
}
%>
<html:hidden name="Form" property="vlCalcularDesconto" styleId="vlCalcularDesconto" />

<html:hidden name="Form" property="aparelhoSelecionado.idMatrizAparelho" styleId="idMatrizAparelho" />
<html:hidden name="Form" property="aparelhoSelecionado.nmModeloAparelho" styleId="nmModeloAparelho" />
<html:hidden name="Form" property="aparelhoSelecionado.nmCor" styleId="nmCor" />
<html:hidden name="Form" property="aparelhoSelecionado.cdSapAparelho" styleId="cdSapAparelho" />
<html:hidden name="Form" property="aparelhoSelecionado.idAparelhoCor" styleId="idAparelhoCor" />

<div id="fidelizacaoWrapper">

    <vivo:quadro id="divAparelhos" height="111" width="743" label="Oferta de Aparelho" scroll="yes">
        <div id="divListaAparelhos">
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
                    <logic:iterate indexId="c" id="iteratorAparelhos" name="Form" property="ofertaAparelhoVO.itemOfertaAparelhoVOArray" type="br.com.vivo.fo.fidelizacao.vo.ItemOfertaAparelhoVODocument.ItemOfertaAparelhoVO">
                        <vivo:tbRow key="linha">
                            <vivo:tbRowColumn>
                                <input class="checkbox" type="radio" name="radioAparelhos" id="aparelhoIndex<%=c%>" onclick="flAparelhoSelecionado=true;getListaDescontosParcelas(<%=c%>)"
                                <% if (iteratorAparelhos.getIdMatrizAparelho().equals(Form.getAparelhoSelecionado().getIdMatrizAparelho())) { %>checked="checked"<% } %>
                                 />
                            </vivo:tbRowColumn>
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
        </div>
        <table width="100%">
            <tr>
                <td nowrap>
                    % de desconto: 
                    <bean:define id="PercentuaisDesconto" name="Form" property="listaPercentuaisDesconto" />
                    <html:select name="Form" title="Percentual de desconto" property="aparelhoSelecionado.vlPercentualDesconto" styleId="vlPercentualDesconto" style="width:50px" onchange="calculaPercentualDesconto(this)">
                    <html:options collection="PercentuaisDesconto" property="id" labelProperty="descricao"/>
                    </html:select>
                </td>
                <td nowrap>
                    Valor com desconto: 
                    <html:text name="Form" property="aparelhoSelecionado.vlPrecoAbs" styleId="vlPrecoAbs" style="width:70px" readonly="true" />
                </td>
                <td nowrap>
                    Número de parcelas:
                    <bean:define id="Parcelas" name="Form" property="listaParcelas" />
                    <html:select name="Form" title="Número de parcelas" property="aparelhoSelecionado.nrParcelas" styleId="nrParcelas" style="width:50px" onchange="calculaParcelas()">
                    <html:options collection="Parcelas" property="id" labelProperty="descricao"/>
                    </html:select>
                </td>
                <td nowrap>
                    Valor da parcela: 
                    <html:text name="Form" property="aparelhoSelecionado.vlParcela" styleId="vlParcela" style="width:70px" readonly="true" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    Meio de pagamento: 
                    <bean:define id="MeiosPagamento" name="Form" property="ofertaAparelhoVO.fidelizacaoListaGeralVO.itemListaVOArray" />
                    <html:select name="Form"
                                 title="Meio de pagamento"
                                 property="aparelhoSelecionado.idTipoPagamentoAparelho"
                                 styleId="idTipoPagamentoAparelho"
                                 style="width:150px;margin-left:12px;"
                                 onchange="verificaMeioPagamento()">
                        <option value="">-- Selecione --</option>
                        <html:options collection="MeiosPagamento" property="id" labelProperty="descricao"/>
                    </html:select>
                </td>
                <logic:equal name="Form" property="listaOfertasAceitas.itemListaDescricaoVOArray[0].sigla" value="APC">
                <% if (Form.getOfertaAparelhoVO().getListaPrazoVigenciaVO() == null) { 
                       Form.getOfertaAparelhoVO().addNewListaPrazoVigenciaVO();
                   }
                %>
                <td colspan="2">
                    Prazo de Vigência: 
                    <bean:define id="PrazosVigencia" name="Form" property="ofertaAparelhoVO.listaPrazoVigenciaVO.itemListaVOArray" />
                    <html:select name="Form"
                                 title="Prazo de Vigência"
                                 property="aparelhoSelecionado.przVigencia"
                                 styleId="prazoVigencia"
                                 style="width:150px;margin-left:14px;">
                        <option value="">-- Selecione --</option>
                        <html:options collection="PrazosVigencia" property="id" labelProperty="descricao"/>
                    </html:select>
                </td>
                </logic:equal>
            </tr>
            <tr>
                <td colspan="4">
                    <strong>Oferta de Exceção</strong>: 
                    <html:checkbox name="Form" property="inExcecao" styleClass="checkbox" onclick="getListaAparelhos()" />
                </td>
            </tr>
        </table>
    </vivo:quadro>

</div>

<jsp:include page="fidelizacaoNavigation.jsp"/>

<script type="text/javascript" language="javascript">
    operacaoAtual = 'fidelizacaoAparelhos';
    proximaOperacao = 'fidelizacaoAparelhosFinalizacao';
    <logic:equal name="Form" property="ofertaAparelhoVO.msgErro.codErro" value="1" >
    alert('<bean:write name="Form" property="ofertaAparelhoVO.msgErro.dsErro"/>');
    </logic:equal>
    verificaMeioPagamento();
    if ($('tbLinhasAssociadas') && $('tbLinhasAssociadas').visible()) {
        WorkspaceControl();
    }
    <logic:equal name="Form" property="listaOfertasAceitas.itemListaDescricaoVOArray[0].sigla" value="APC">
    $('nrParcelas').disabled = true;
    $('vlPercentualDesconto').disabled = true;
    $('vlCalcularDesconto').value = $F('vlPrecoAbs');
    $('vlPrecoAbs').value = '';
    </logic:equal>
</script>