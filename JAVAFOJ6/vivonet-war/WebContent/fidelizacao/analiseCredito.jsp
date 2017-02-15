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
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/fidelizacao-aparelhos-entrega.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
                top.frameApp.$("idAnime").style.display = "none";
            -->
        </SCRIPT>
        <script language="javascript">
            function confirmarAprovacao() {
                var acao = "APROVAR";
                if (trim(document.forms[0].dsObsAnalista.value) == "") {
                    top.frameApp.$("idAnime").style.display = "block";
                    alert('O preenchimento do campo Observa\347\365es é obrigat\363rio.');
                    top.frameApp.$("idAnime").style.display="none";
                } else {
                    top.frameApp.$("idAnime").style.display = "block";
                    document.forms[0].action = "finalizarAnaliseCredito.do?acao="+acao+"&idAtendimento="+idAtendimento;
                    document.forms[0].submit();
                }
            }

            function confirmarReprovacao() {
                var acao = "REPROVAR";
                if (trim(document.forms[0].dsObsAnalista.value) == "") {
                    top.frameApp.$("idAnime").style.display = "block";
                    alert('O preenchimento do campo Observa\347\365es é obrigat\363rio.');
                    top.frameApp.$("idAnime").style.display="none";
                } else {
                    top.frameApp.$("idAnime").style.display = "block";
                    document.forms[0].action = "finalizarAnaliseCredito.do?acao="+acao+"&idAtendimento="+idAtendimento;
                    document.forms[0].submit();
                }
            }

            getCPF = function(){ return onlyNumbers(window.top.frameApp.$('ddClienteDocumento').innerHTML); }
            idAtendimento = top.frameApp.idAtendimento;
        </script>
    </netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlInitEnv/>

    <acesso:controlHiddenItem nomeIdentificador="fid_anacred_verpagina">

    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

    <form action="finalizarAnaliseCredito.do" method="post" name="consultaAdimplenciaForm">

    <html:hidden name="ConsultaAdimplenciaForm" property="idRetencao" />

    <table cellpadding="0" cellspacing="0" style="border:1px solid #adadad; background-color:#E7E7E7; margin-bottom:5px;" width="735" border="0">

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
                Valor da parcela
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
                Valor com desconto
            </td>
            <td>
                <b>R$<bean:write name="ConsultaAdimplenciaForm" property="vlPrecoComDesconto" /></b>
            </td>
        </tr>

        <logic:equal name="ConsultaAdimplenciaForm" property="inBloquearEdicao" value="true">

        <tr style="background-color:#fff;">
            <td colspan="5" style="padding:5px;">
                Status: <b>Reprovado</b>
            </td>
        </tr>

        </logic:equal>

    </table>

    <vivo:quadro id="resultadoAnalise" height="177" width="735" label="Resultado da An&aacute;lise">
        <b>Hist&oacute;rico da An&aacute;lise</b><br>
        <bean:write name="ConsultaAdimplenciaForm" property="dsHistorico" />
        <br><br>
        <table width="100%">
            <tr>
                <td>
                    <b>Observa&ccedil;&otilde;es</b><br>
                    <logic:equal name="ConsultaAdimplenciaForm" property="inBloquearEdicao" value="true">
                    <bean:write name="ConsultaAdimplenciaForm" property="dsObsAnalista" />
                    </logic:equal>
                    <logic:notEqual name="ConsultaAdimplenciaForm" property="inBloquearEdicao" value="true">
                    <html:text name="ConsultaAdimplenciaForm" property="dsObsAnalista" style="width:400px;height:80px;" />
                    </logic:notEqual>
                </td>
                <logic:notEqual name="ConsultaAdimplenciaForm" property="inBloquearEdicao" value="true">
                <td align="right" valign="bottom">
                    <acesso:controlHiddenItem nomeIdentificador="fid_adimp_reprovarcredito">
                    	<img src="/FrontOfficeWeb/resources/images/bt_reprovar_nrml.gif"
                    		 border="0"
                    		 onClick="confirmarReprovacao(); return false;"/>
                    </acesso:controlHiddenItem>
                    &nbsp;
                    <acesso:controlHiddenItem nomeIdentificador="fid_adimp_aprovarcredito">
                    	<img src="/FrontOfficeWeb/resources/images/bt_aprovar_nrml.gif"
                    		 border="0"
                    		 onClick="confirmarAprovacao(); return false;"/>
                    </acesso:controlHiddenItem>
                </td>
                </logic:notEqual>
            </tr>
        </table>
    </vivo:quadro>
    </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>