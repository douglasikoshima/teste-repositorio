<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="ConsultaAdimplenciaForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaAdimplenciaForm" />
<bean:define id="DadosClienteForm"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm" />
<bean:define id="OfertaAparelhoForm"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm" />
<bean:define id="UFs"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.regionais"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/fidelizacao-aparelhos-entrega.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
                top.frameApp.$("idAnime").style.display="none";
            -->
        </SCRIPT>
        <script language="javascript">
            function confirmarProsseguir() {
                var acao = "ANALISEAPROVADA";
                var parametros = "";
                parametros += "acao="+acao;
                parametros += "&idDestino=<bean:write name="DadosClienteForm" property="idDestinoPrevisto" />";
                parametros += "&idIntencao=<bean:write name="DadosClienteForm" property="idIntencaoCancelamento" />";
                parametros += "&dsIntencao=<bean:write name="ConsultaAdimplenciaForm" property="dsIntencaoCancelamento" />";
                parametros += "&dsDestinoPrevisto=<bean:write name="ConsultaAdimplenciaForm" property="dsDestinoPrevisto" />";
                parametros += "&sgOferta=<bean:write name="ConsultaAdimplenciaForm" property="sgOferta" />";
                parametros += "&idAtendimento="+idAtendimento;
                parametros += "&nrCPF=<bean:write name="ConsultaAdimplenciaForm" property="nrDocumento" />";
                parametros += "&nrLinha=<bean:write name="DadosClienteForm" property="numeroLinha" />";
                parametros += "&idLinha=<bean:write name="DadosClienteForm" property="idLinhaTelefonica" />";
                parametros += "&dsMeioPagamento=<bean:write name="ConsultaAdimplenciaForm" property="dsMeioPagamento" />";
                parametros += "&idTipoPagamento=<bean:write name="ConsultaAdimplenciaForm" property="idTipoPagamento" />";
                parametros += "&idRetencao=<%=request.getAttribute("idRetencao")%>";
                parametros += "&dsMaterial=<bean:write name="ConsultaAdimplenciaForm" property="dsMaterial" />";
                parametros += "&idAparelho=<bean:write name="OfertaAparelhoForm" property="idAparelho" />";
                parametros += "&idAparelhoCor=<bean:write name="OfertaAparelhoForm" property="idAparelhoCor" />";

                var doc = window.top.frameApp.ti_frameAbas.frames["frmQuestionario"];
                doc.location.href = "getDadosAparelho.do?"+parametros;
                top.frameApp.mostrar_div();
                top.frameApp.$("idAnime").style.display = "block";
            }

            function confirmarNovaOferta() {
                var acao = "ANALISEAPROVADA_NOVAOFERTA";
                var parametros = "";
                parametros += "acao="+acao;
                parametros += "&idDestino=<bean:write name="DadosClienteForm" property="idDestinoPrevisto"/>";
                parametros += "&idIntencao=<bean:write name="DadosClienteForm" property="idIntencaoCancelamento"/>";
                parametros += "&idAtendimento="+idAtendimento;
                parametros += "&nrCPF=<bean:write name="ConsultaAdimplenciaForm" property="nrDocumento"/>";
                parametros += "&dsDestinoPrevisto=<bean:write name="ConsultaAdimplenciaForm" property="dsDestinoPrevisto"/>";
                parametros += "&dsIntencao=<bean:write name="ConsultaAdimplenciaForm" property="dsIntencaoCancelamento"/>";
                parametros += "&idTipoPagamento=<bean:write name="ConsultaAdimplenciaForm" property="idTipoPagamento"/>";
                parametros += "&nrLinha=<bean:write name="DadosClienteForm" property="numeroLinha" />";
                parametros += "&idLinha=<bean:write name="DadosClienteForm" property="idLinhaTelefonica"/>";
                parametros += "&idRetencao=<%=request.getAttribute("idRetencao")%>";
                parametros += "&dsMaterial=<bean:write name="ConsultaAdimplenciaForm" property="dsMaterial"/>";
                parametros += "&sgOferta=<bean:write name="ConsultaAdimplenciaForm" property="sgOferta"/>";
                parametros += "&idAparelho=<bean:write name="OfertaAparelhoForm" property="idAparelho"/>";
                parametros += "&idAparelhoCor=<bean:write name="OfertaAparelhoForm" property="idAparelhoCor" />";
                parametros += "&idSegmentacaoLinha=<bean:write name="OfertaAparelhoForm" property="idSegmentacaoLinha" />";

                var doc = window.top.frameApp.ti_frameAbas.frames["frmQuestionario"];
                doc.location.href = "getMatrizOferta.do?"+parametros;
                top.frameApp.mostrar_div();
                top.frameApp.$("idAnime").style.display = "block";
            }

            getCPF = function(){ return onlyNumbers(window.top.frameApp.$('ddClienteDocumento').innerHTML); }
            idAtendimento = top.frameApp.idAtendimento;
        </script>
    </netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlInitEnv/>

    <acesso:controlHiddenItem nomeIdentificador="fid_credaprov_verpagina">
    <form action="finalizarAnaliseEndereco.do" method="post" name="ofertaAparelhoForm">

    <html:hidden name="ConsultaAdimplenciaForm" property="idRetencao" />
    <html:hidden name="ConsultaAdimplenciaForm" property="nrParcelas" />
    <html:hidden name="OfertaAparelhoForm" property="SAP" />
    <html:hidden name="OfertaAparelhoForm" property="idAparelho" />
    <html:hidden name="OfertaAparelhoForm" property="dsMaterial" />

    <% if (ConstantesCRM.SONE.equals(request.getParameter("inVoltar"))) { %>
    <table border='0' cellpadding='0' cellspacing='0' height='125' width='760' id='table_qdMain'>
        <tr valign='top'>
            <td>
                <table width='760' border='0' cellpadding='0' cellspacing='0' bgcolor='#0066cb'>
                    <tr>
                        <td style='width:7px;'><img src='/FrontOfficeWeb/resources/images/bcliente_left.gif'></td>
                        <td style='width:auto;' align='left' id='qdMain' class='tbl_tituloBG'>
                            Finalização de Retenção - Análise de crédito aprovada
                        </td>
                        <td style='width:93px;' align='right' class='tbl_tituloBG'></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr valign='top'>
            <td height='125'>
                <table width='760' height='160' border='0' cellpadding='0' cellspacing='0' bgcolor='#adadad'>
                    <tr>
                        <td bgcolor='#E3ECF4' align='center'>
                            <div align='left' style='width:750px;height:119px;' id='qdMain_div'>
    <% } %>

    <table cellpadding="0" cellspacing="0" style="border:1px solid #adadad; background-color:#E7E7E7; margin-bottom:5px;" width="750" border="0">

        <tr style="background-color:#fff;">
            <td style="padding:5px;">
                Cliente:
                <b><bean:write name="DadosClienteForm" property="nomeCliente" /></b>
            </td>
            <td>
                Modelo:
            </td>
            <td>
                <b><bean:write name="ConsultaAdimplenciaForm" property="dsModelo" /></b>
            </td>
            <td>
                Percentual de desconto:
            </td>
            <td>
                <b><bean:write name="ConsultaAdimplenciaForm" property="vlPercentualDesconto" /></b>
            </td>
        </tr>
        <tr>
            <td style="padding:5px;">
                CPF:
                <b><bean:write name="ConsultaAdimplenciaForm" property="nrDocumento" /></b>
            </td>
            <td>
                Cor:
            </td>
            <td>
                <b><bean:write name="ConsultaAdimplenciaForm" property="nmCor" /></b>
            </td>
            <td>
                N&uacute;mero de parcelas:
            </td>
            <td>
                <b><bean:write name="ConsultaAdimplenciaForm" property="nrParcelas" /></b>
            </td>
        </tr>
        <tr style="background-color:#fff;">
            <td style="padding:5px;">
                Inten&ccedil;&atilde;o de cancelamento:
                <b><bean:write name="ConsultaAdimplenciaForm" property="dsIntencaoCancelamento" /></b>
            </td>
            <td>
                Pre&ccedil;o:
            </td>
            <td>
                <b>R$<bean:write name="ConsultaAdimplenciaForm" property="vlPreco" /></b>
            </td>
            <td>
                Valor da parcela:
            </td>
            <td>
                <b>R$<bean:write name="ConsultaAdimplenciaForm" property="vlParcela" /></b>
            </td>
        </tr>
        <tr>
            <td style="padding:5px;">
                Destino Previsto:
                <b><bean:write name="ConsultaAdimplenciaForm" property="dsDestinoPrevisto" /></b>
            </td>
            <td>
                Meio de pagamento:
            </td>
            <td>
                <b><bean:write name="ConsultaAdimplenciaForm" property="dsMeioPagamento" /></b>
            </td>
            <td>
                Valor com desconto:
            </td>
            <td>
                <b>R$<bean:write name="ConsultaAdimplenciaForm" property="vlPrecoComDesconto" /></b>
            </td>
        </tr>

    </table>


    <table width="100%">
        <tr>
            <td align="right" valign="bottom" style="padding-right:10px;">
                <acesso:controlHiddenItem nomeIdentificador="fid_credaprov_novaoferta">
	                <img action="begin"
	                	 src="/FrontOfficeWeb/resources/images/bt_novaoferta_nrml.gif"
	                	 class="button"
	                	 onmouseup="confirmarNovaOferta();return false;" />
                </acesso:controlHiddenItem>
                &nbsp;
                <acesso:controlHiddenItem nomeIdentificador="fid_credaprov_prosseguir">
                	<img action="begin"
                		 src="/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif"
                		 class="button"
                		 onmouseup="confirmarProsseguir();return false;"/>
                </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>

    <% if (ConstantesCRM.SONE.equals(request.getParameter("inVoltar"))) { %>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <% } %>


    </form>

    </acesso:controlHiddenItem>

    </netui-template:section>

</netui-template:template>
