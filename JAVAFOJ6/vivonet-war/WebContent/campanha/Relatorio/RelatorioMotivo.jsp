<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="numerico" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioMotivoForm.relatorioPercentualVO"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Fidelizacao" name="Relatorio"></netui-template:setAttribute>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">

    <acesso:controlHiddenItem nomeIdentificador="cam_rm_verpagina">
    <script>
        function imprimir()
        {
            // default values
           var default_url = "generarImpressao.do?idRelatorio=4";

            // define popup window attributes
            var width  = 800;
            var height = 590;

            var top = screen.availHeight/2 - height/2; // center
            var left = screen.availWidth/2 - width/2; // center

            var scrollbars = true;
            var statusbar  = false;
            var resizable  = true;

            // show a modal (blocking) popup window
            var settings = buildSettings(width, height, top, left, scrollbars, statusbar, resizable);
            window.showModalDialog(default_url, window, settings);
            window.close();
        }
    </script>
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
    </script>
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="idFrelatorio" height="470" width="790" label="Relatório de Percentual por Motivos" operacoes="" scroll="N">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <table width="100%" class="tbl_bggray" cellpadding="5" border="0">
                <tr>
                    <td width="40%">Campanha:</td>
                    <td width="60%"><b><bean:write name="relHeader" property="sgCampanha" /></b></td>

                </tr>
                <tr>
                    <td>Subcampanha:</td>
                    <td><b><bean:write name="relHeader" property="nmSubCampanha" /></b></td>
                </tr>
                <tr>
                    <td>Período:</td>
                    <td>de <b><bean:write name="relHeader" property="filtroDtInicio" /></b> a <b><bean:write name="relHeader" property="filtroDtFim" /></b></td>
                </tr>
                <tr>
                    <td colspan="2">Foram selecionados <b><bean:write name="relHeader" property="total" /></b> registros</td>
                </tr>
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <table  border="0" cellpadding="0" cellspacing="0">
               <tr>
                    <td colspan="3">
                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="765" layoutHeight="230" tableWidth="765" styleId="tableTitle" sortable="true" >
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="30%" type="string">Motivo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="30%" type="string">Sub Motivo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Porcentagem</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                                <logic:iterate id="linha" name="numerico" property="numericoArray">
                                    <vivo:tbRow key="linha1" zebrar="s">
                                        <vivo:tbRowColumn><bean:write name="linha" property="sgTipoMotivoCampanha"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="linha" property="sgTipoSubMotivoCampanha"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="linha" property="nroAtendimento"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="linha" property="porcent"/></vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </td>
            </tr>
            <tr  class='rowTabelaZebradoOn'>
                <td width="30%" align="right"><b>TOTAL</b></td>
                <td width="20%" align="center"><b><bean:write name="numerico" property="totalNroAtendimento"/></b></td>
                <td width="20%" align="center"><b><bean:write name="numerico" property="totalPorcent"/></b></td>
            </tr>
            </table>
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <table width="100%" class="tbl_bgGray">
            <tr>
                <td width="500"><img align="letf" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/campanha/Relatorio/begin.do'; top.frameApp.mostrar_div(); return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5"/></td>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cam_rm_impr">
                        <img id="imprimir" src="/FrontOfficeWeb/resources/images/bt_impressao_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_impressao_over.gif" onClick="imprimir();" style="border:none" hspace="5" vspace="5"/>
                    </acesso:controlHiddenItem>
                    </td>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cam_rm_ga">
                        <img id="gerararquivo" src="/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gerararq_over.gif" onClick="window.location.href='/FrontOfficeWeb/campanha/Relatorio/generarArquivo.do?idRelatorio=4'; return false" style="border:none" hspace="5" vspace="5"/>
                    </acesso:controlHiddenItem>
                    </td>
            </tr>
        </table>

        </vivo:sessao>
        </acesso:controlHiddenItem>
        </netui-template:section>
        </netui-template:template>
