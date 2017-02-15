<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="trackingForm" type="cliente.TelaInicial.TelaInicialController.TrackingForm"/>

<%!
private boolean isURL(String url) {
    String patternString = "^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$";
    Pattern VALID_PATTERN = Pattern.compile(patternString);
    return (url != null) && VALID_PATTERN.matcher(url).matches();
}
%>

<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script language="javascript">
            <!--

            openURL = function(dsURL) {
                window.open(dsURL);
            }

            obtemDetalhes = function(nrDoc, nrPed, idSO, nmSO, nrOV, nrFor, nrPic, nrNF, sgUF, isOVParcial, dsEndereco, isURL, dsURL) {

                if (nmSO != 'EXATA' && nmSO != 'TGESTIONA' && !isURL) {

                    if(isOVParcial!='1' && isOVParcial!='2'){
                        var elt = Event.findElement(window.event, 'td');
                        if(!dsEndereco.strip().empty()){
                            if(elt.key=="dtEnt"){
                                top.frameApp.createNewPopUpText('winPrevEntrega','Previsão de Entrega', 300, 100, null, true, dsEndereco,"Endereço de Entrega:");
                                return;
                            }
                        }
                        top.frameApp.startAnimation();
                        var param = "nrDoc="+nrDoc+"&nrPedido="+nrPed+"&idSistemaOrigem="+idSO+"&nmSistemaOrigem="+nmSO+"&nrOrdemVenda="+nrOV+"&nrFornecimento="+nrFor+"&nrPicking="+nrPic+"&nrNotaFiscal="+nrNF+"&sgUF="+sgUF;
                        location.href="trackingDetalhe.do?"+param;
                    }
                }
            }

            function getNextPage(){
                var f = document.forms[0];
                f.pageNumber.value = parseInt(f.pageNumber.value)+1;
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                f.submit();
            }

            function getPreviusPage(){
                var f = document.forms[0];
                f.pageNumber.value = parseInt(f.pageNumber.value)-1;
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                f.submit();
            }
            -->
        </script>
        <script FOR=window event=onload LANGUAGE="JScript">
                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

        </script>
    </head>
    <body>
        <form action="trackingLista.do" name="trackingForm" method="post" style="margin:0;" onSubmit="return false;">
            <input type="hidden" name="pageNumber" value="<bean:write name="Form" property="pageNumber"/>">
            <html:hidden name="Form" property="nrDoc" styleId="nrDocumento" />
            <html:hidden name="Form" property="dsTipoDocumento" styleId="dsTipoDocumento" />
            <table style="border:1px solid #adadad; background-color:#E7E7E7;" width="762" border="0" cellpadding="2" cellspacing="2">
                <tr>
                    <td><b>Tracking de Aparelhos</b></td>
                    <td></td>
                    <td align="right">
                        <logic:greaterThan value="0"name="Form" property="pageNumber">
                            <img onclick="getPreviusPage();" src="/FrontOfficeWeb/resources/images/bt_anterior_nrml.gif" style="cursor:pointer;">
                        </logic:greaterThan>
                        &nbsp;
                        <logic:equal value="1" name="Form" property="hasNext">
                            <img onclick="getNextPage();" src="/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif" style="cursor:pointer;">
                        </logic:equal>
                    </td>
                </tr>
            </table>
            <table style="border:1px solid #adadad; background-color:#E7E7E7;margin-top:5px;" width="762" border="0" cellpadding="2" cellspacing="2">
                <tr>
                    <td width="12%">Nome do Cliente:</td>
                    <td align="left"><b><bean:write name="Form" property="listaTrackingAparelhoVO.nmCliente"/></b></td>
                    <td width="5%"><bean:write name="Form" property="listaTrackingAparelhoVO.tpDoc"/></td>
                    <td align="left">
                        <b><bean:write name="Form" property="listaTrackingAparelhoVO.nrDoc"/></b>
                        <input type="hidden" name="nrDoc" value="<bean:write name='Form' property='listaTrackingAparelhoVO.nrDoc'/>">
                    </td>
                </tr>
            </table>
            <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="278" tableWidth="760" styleId="tableTracking" sortable="true" onRowClick="void(0);">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Origem</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="7%" type="string">UF</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Data Pedido</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Pedido</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem Venda</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Fornecimento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Picking</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Nota Fiscal</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Previsão Entrega</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="13%" type="string">Status Entrega</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows scroll="false">
                <logic:equal value="L" name="Form" property="tpRetorno">
                    <logic:iterate id="lista" name="Form" property="listaTrackingAparelhoVO.listaPedidoAparelhosVOArray" indexId="idx" type="br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO.ListaTrackingAparelhoVO.ListaPedidoAparelhosVO">
                    <%
                    String dsEndereco = (lista.getDtPrevisaoEntrega()==null || "".equals(lista.getDtPrevisaoEntrega().trim()))?"":lista.getDsEnderecoEntrega();
                    boolean isValidURL = isURL(lista.getSgStatus());
                    String url = isValidURL
                        ? lista.getSgStatus().replaceAll("[\t]", ConstantesCRM.SVAZIO).replaceAll("[\n]", ConstantesCRM.SVAZIO).replaceAll("[\r]", ConstantesCRM.SVAZIO)
                        : ConstantesCRM.SVAZIO;
                    String jsParam = "'"+Form.getListaTrackingAparelhoVO().getNrDoc()+"','"+lista.getNrPedido()+"','"+lista.getIdSistemaOrigem()+"','"+lista.getDsOrigem()+"','"+lista.getNrOrdemVenda()+"','"+lista.getNrFornecimento()+"','"+lista.getNrPicking()+"','"+lista.getNrNotaFiscal()+"','"+lista.getSgUF()+"','"+lista.getIsOVParcial() + "','" + dsEndereco + "', " + String.valueOf(isValidURL) + ", '" + url + "'";
                    %>
                    <vivo:tbRow id='<%="linha_"+idx+""%>' key="linha1" onRowClick='<%="obtemDetalhes("+jsParam+");"%>'>
                        <vivo:tbRowColumn><bean:write name="lista" property="dsOrigem"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="lista" property="sgUF"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="lista" property="dtPedido"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="lista" property="nrPedido"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="lista" property="nrOrdemVenda"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <logic:notEqual name="lista" property="isOVParcial" value="1">
                            <logic:notEqual name="lista" property="isOVParcial" value="2">
                                <bean:write name="lista" property="dtFornecimento"/>
                            </logic:notEqual>
                            </logic:notEqual>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <logic:notEqual name="lista" property="isOVParcial" value="1">
                            <logic:notEqual name="lista" property="isOVParcial" value="2">
                                <bean:write name="lista" property="dtPicking"/>
                            </logic:notEqual>
                            </logic:notEqual>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <logic:notEqual name="lista" property="isOVParcial" value="1">
                            <logic:notEqual name="lista" property="isOVParcial" value="2">
                                <bean:write name="lista" property="dtNotaFiscal"/>
                            </logic:notEqual>
                            </logic:notEqual>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn key="dtEnt">
                            <logic:notEqual name="lista" property="isOVParcial" value="1">
                            <logic:notEqual name="lista" property="isOVParcial" value="2">
                                <a href="javascript:void(0);"><bean:write name="lista" property="dtPrevisaoEntrega"/></a>
                            </logic:notEqual>
                            </logic:notEqual>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                                <%=
                                isValidURL
                                ? "<span style=\"color:#000;text-decoration:underline;\" onmouseup=\"openURL('" + url + "')\">Mais informações</span>"
                                : ConstantesCRM.SONE.equals(lista.getIsOVParcial())
                                    ? "INFO PARCIAL. CONSULTE SAP"
                                    : ConstantesCRM.STWO.equals(lista.getIsOVParcial())
                                        ? "ORDEM DEVOLVIDA. CONSULTE SAP"
                                        : lista.getSgStatus()
                                %>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                    </logic:iterate>
                </logic:equal>
                <logic:equal value="E" name="Form" property="tpRetorno">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td align="center" style="font-color:red;"><bean:write name="Form" property="msgError"/></td>
                        </tr>
                    </table>
                </logic:equal>
                </vivo:tbRows>
            </vivo:tbTable>
        </form>
        <div style="margin0;font-size:8pt;">Para exibir os detalhes de cada pedido, basta selecionar a linha</div>
    </body>
</html>
