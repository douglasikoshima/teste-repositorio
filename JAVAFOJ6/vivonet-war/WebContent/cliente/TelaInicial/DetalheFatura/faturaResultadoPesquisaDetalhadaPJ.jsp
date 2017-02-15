<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="java.math.BigInteger"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="org.displaytag.tags.TableTagParameters"%>
<%@ page import="org.displaytag.util.ParamEncoder"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaFaturamentoPos"
             type="cliente.TelaInicial.DetalheFatura.DetalheFaturaController.LupaFaturamentoPosForm" />

<%
double total = 0;
String altura = String.valueOf(Form.getPesquisaDetalhada().length * 28);
%>

<%!
/* Fomatação de valores no padrão 0000,00 */
public String formataValor(String valor) {
    if (valor == null || valor.equals(ConstantesCRM.SVAZIO)) valor = "0";
    DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
    dSymbols.setDecimalSeparator(',');
    dSymbols.setGroupingSeparator('.');
    DecimalFormat dFormat = new DecimalFormat("00",dSymbols);
    dFormat.isDecimalSeparatorAlwaysShown();
    dFormat.applyPattern("###,#00.00;(###,#00.00)");
    return dFormat.format(new Double(valor));
}
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
<link rel="stylesheet" media="all" type="text/css" href="<%=request.getContextPath()%>/resources/css/autocomplete.css" />
<link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/displaytag.css">

<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
<script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/messages.js" ></script>
<script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/date.format.js" ></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/autocomplete.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>

<script type="text/javascript">
    function voltar()
    {
         if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
        self.location.href = 'faturaDetalhadaPJ.do?operacao=getPesquisaDetalhada';
    }
    
    function onload() {
         if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
    }
</script>

<vivo:tbTable selectable="true" layoutWidth="761" layoutHeight="142" tableWidth="761" styleId="tbResultadoPesquisa" sortable="true">
   <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Celular Origem</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Número Discado</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="9%" type="date">Data</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="9%" type="string">Hora início</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Tipo Ligação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Localização</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="9%" type="string">Detalhe Ligação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="7%" type="string">Uso</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="8%" type="string">Operadora</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="8%" type="string">Tarifação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="8%" type="string">Qtd</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%" type="string">Valor</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="true">

       <display:table
            name="retornoPaginado"
            id="retorno"
            sort="external"
            export="false"
            class="displaytag"
            pagesize="100"
            partialList="true"
            size="totalRows"
            requestURI="pesquisaFaturaDetalhadaPJ.do?paginacao=true">
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='10%'" property="numeroOrigem" title="" />
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='10%'" property="numeroDestino" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='9%'" property="dataChamada" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='9%'" property="horaChamada" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='7%'" property="tipoChamada.descricao" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='10%'" property="areaChamada.descricao" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='9%'" property="tipoDetalheChamada.descricao" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='7%'" property="tipoServicoChamada.descricao" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='8%'" property="destinoChamada.descricao" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='8%'" property="tipoTarifa.descricao" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='8%'" property="duracaoFormatada" title=""/>
            <display:column style="border: #D4D7DE 1px solid;height: 25px;color: #000000;vertical-align: middle;padding-left: 5px;padding-right: 5px;cursor: hand; width='5%'" property="valorFormatado" title=""/>
        </display:table>

    </vivo:tbRows>
</vivo:tbTable>
<table width="761" cellpadding="1" cellspacing="0">
    <tr>
        <td width="11%"></td>
        <td width="11%"></td>
        <td width="10%"></td>
        <td width="9%"></td>
        <td width="10%"></td>
        <td width="10%"></td>
        <td width="10%"></td>
        <td width="7%"></td>
        <td width="9%"></td>
        <td width="8%"></td>
        <td width="7%" align="center"><strong>Total:</strong></td>
        <td width="10%" align="center">
            <strong>R$<%=request.getAttribute("total")%></strong>
        </td>
    </tr>
</table>

<script type="text/javascript">
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
    <logic:equal name="Form" property="atualizacaoProtocolo" value="true">
    alert('Protocolo <bean:write name="Form" property="nrProtocolo" /> gerado.');
    top.frameApp.updateProtocolo('<bean:write name="Form" property="nrProtocolo" />');
    top.frameApp.nrProtocoloScreenPop = '';
    </logic:equal>
</script>

<vivo:alert atributo="msgErro" scope="request" />