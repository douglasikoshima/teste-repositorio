<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>


<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="fidelizacaoForm"
             type="workflow.AtendimentoFila.Portabilidade.PortabilidadeController.FidelizacaoForm" />

<bean:define id="DadosLinha"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="detalhesProcessoForm.dadosLinha"
             type="br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO" />

<jsp:include page="fidelizacaoDadosLinha.jsp" />

<html:hidden name="Form" property="inExcecao" styleId="inExcecao" />

<div id="fidelizacaoWrapper">

    <vivo:quadro id="divDadosAparelho" height="57" width="720" label="Dados do Aparelho" scroll="no">
        <table width="100%">
            <tr>
                <td>Modelo:</td>
                <td>
                    <html:text style="width:120px;" name="Form" property="aparelhoSelecionado.nmModeloAparelho" maxlength="255" readonly="true" size="50" styleId="nmModeloAparelho"/>
                </td>
                <td>Cor:</td>
                <td>
                    <html:text style="width:45px;" name="Form" property="aparelhoSelecionado.nmCor" maxlength="255" readonly="true" size="50" styleId="nmCor"/>
                </td>
                <td>Preço:</td>
                <td>
                    <html:text style="width:70px;" name="Form" property="aparelhoSelecionado.vlPrecoAbs" maxlength="255" readonly="true" size="50" styleId="vlPrecoAbs"/>
                </td>
                <td nowrap>Meio de Pagamento:</td>
                <td>
                    <html:text style="width:110px;" name="Form" property="dsTipoPagamento" maxlength="255" readonly="true" size="50" styleId="dsTipoPagamento"/>
                </td>
            </tr>
            <tr>
                <td>Desconto:</td>
                <td>
                    <html:text style="width:70px;" name="Form" property="aparelhoSelecionado.vlPercentualDesconto" maxlength="255" readonly="true" size="50" styleId="vlPercentualDesconto"/>
                </td>
                <td nowrap>Nº de Parcelas:</td>
                <td>
                    <html:text style="width:70px;" name="Form" property="aparelhoSelecionado.nrParcelas" maxlength="255" readonly="true" size="50" styleId="nrParcelas"/>
                </td>
                <td nowrap>Valor Parcela:</td>
                <td>
                    <html:text style="width:70px;" name="Form" property="aparelhoSelecionado.vlParcela" maxlength="255" readonly="true" size="50" styleId="vlParcela"/>
                </td>
                <td colspan="2">
                    Delivery: <input type="radio" name="dsTipoEntrega" id="radioDelivery" value="DELIVERY" class="checkbox" onclick="validacaoEntregaAparelho(this.value)" />
                    Loja: <input type="radio" name="dsTipoEntrega" id="radioLoja" value="LOJA" class="checkbox" onclick="validacaoEntregaAparelho(this.value)" />
                </td>
            </tr>
        </table>
    </vivo:quadro>

    <div id="containerDivEntregaAparelho" style="display:none;padding-top:3px;">
        <vivo:quadro id="divEntregaAparelho" height="80" width="720" label="Entrega do Aparelho" scroll="no">
            <table width="100%">
                <tr>
                    <td>Rua:</td>
                    <td colspan="5">
                        <html:text title="Endereço" name="Form" property="enderecoEntrega.dsEndereco" maxlength="255" size="50" styleId="dsEndereco"/>
                    </td>
                </tr>
                <tr>
                    <td>Número:</td>
                    <td>
                        <html:text title="Número" name="Form" property="enderecoEntrega.nrEndereco" maxlength="10" size="2" styleId="nrEndereco" />
                    </td>
                    <td>Complemento:</td>
                    <td>
                        <html:text title="Complemento" name="Form" property="enderecoEntrega.dsComplemento" maxlength="50" size="10" styleId="dsComplemento"/>
                    </td>
                    <td>Bairro:</td>
                    <td>
                        <html:text title="Bairro" name="Form" property="enderecoEntrega.dsBairro" maxlength="255" size="35" styleId="dsBairro"/>
                    </td>
                </tr>
                <tr>
                    <td>Cidade:</td>
                    <td>
                        <html:text title="Cidade" name="Form" property="enderecoEntrega.dsCidade" maxlength="255" size="25" styleId="dsCidade"/>
                    </td>
                    <td>Estado:</td>
                    <td>
                        <bean:define id="Regionais" name="Form" property="listaRegionais" />
                        <html:select title="Estado" style="width:45px" name="Form" property="enderecoEntrega.dsUF" styleId="dsUF">
                            <html:options collection="Regionais" property="sgUF" labelProperty="sgUF"/>
                        </html:select>
                    </td>
                    <td>CEP:</td>
                    <td>
                        <html:text title="CEP" name="Form" property="enderecoEntrega.dsCEP" styleId="dsCEP" maxlength="9" size="10" onkeypress="checaCEP(this)"/>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
    </div>

    <div id="containerDivAutorizacaoEntrega" style="display:none;padding-top:3px;">
        <vivo:quadro id="divAutorizacaoEntrega" height="32" width="720" label="Autorização de Entrega para Terceiros" scroll="no">
            <table width="100%">
                <tr>
                    <td>
                        Nome: 
                        <html:text style="width:300px;" name="Form" property="aparelhoSelecionado.nmTerceiro" maxlength="255" size="50" styleId="nmTerceiro"/>
                    </td>
                    <td>
                        RG: 
                        <html:text title="RG do Contato" style="width:120px;" name="Form" property="aparelhoSelecionado.dsDocumentoTerceiro" maxlength="255" size="50" styleId="dsDocumentoTerceiro"/>
                    </td>
                    <td>
                        Telefone Contato: 
                        <html:text onkeyup="maskPhoneNumberObj(this)" style="width:120px;" title="Telefone do Contato" name="Form" property="aparelhoSelecionado.nrContatoTelefone" maxlength="14" size="50" styleId="nrContatoTelefone"/>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
    </div>

    <div id="containerDivAgendamento" style="display:none;padding-top:3px;">
        <vivo:quadro id="divAgendamento" height="60" width="720" label="Agendar para Retirar em Loja" scroll="no">
            <table width="100%">
                <tr>
                    <td>
                        <a style="color:#000;" href="http://intranet.vivo-prsc.com.br/deptos/cli/aloglobal/call.php?topframe=grupos&area=grupos&x=index.php" target="_blank">Nome da loja:</a>
                    </td>
                    <td colspan="2">
                        <html:text title="Nome da loja" style="width:270px;" name="Form" property="aparelhoSelecionado.nmLoja" maxlength="255" size="50" styleId="nmLoja"/>
                    </td>
                </tr>
                <tr>
                    <td nowrap>Quantidade em Estoque:</td>
                    <td width="50%">
                        <html:text title="Quantidade em estoque" style="width:120px;" name="Form" property="aparelhoSelecionado.qtdEstoqueLoja" maxlength="255" size="50" styleId="qtdEstoqueLoja" onkeyup="checaInteiro(this)" onblur="checaInteiro(this)" />
                    </td>
                    <td align="right">
                        Data de Retirada: 
                        <html:text title="Data de retirada do aparelho" style="width:70px;" name="Form" property="aparelhoSelecionado.dtRetirada" maxlength="255" readonly="true" size="50" styleId="dtRetirada"/> 
                        <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtRetirada', '%d/%m/%Y');">
                    </td>
                </tr>
            </table>
        </vivo:quadro>
    </div>

</div>

<jsp:include page="fidelizacaoNavigation.jsp"/>

<script type="text/javascript" language="javascript">
    operacaoAtual = 'fidelizacaoAparelhosFinalizacao';
    proximaOperacao = 'finalizacao';
</script>