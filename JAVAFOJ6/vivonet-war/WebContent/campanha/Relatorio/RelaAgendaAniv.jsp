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
<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relaAgendaAnivForm"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Campanha" name="Relatorio"></netui-template:setAttribute>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cam_aniversario_verpagina">
    <script>
        function imprimir()
        {
            // default values
            var default_url = "relaAgendaAnivImpressao.jsp";

            // define popup window attributes
            var width  = 750;
            var height = 590;

            var top = screen.availHeight/2 - height/2; // center
            var left = screen.availWidth/2 - width/2; // center

            var scrollbars = true;
            var statusbar  = false;
            var resizable  = true;

            // show a modal (blocking) popup window
            var settings = buildSettings(width, height, top, left, scrollbars, statusbar, resizable);
            window.showModalDialog(default_url, window, settings);
        }
        </script>
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
    </script>
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="idFrelatorio" height="470" width="790" label="Agenda de Aniversário" operacoes="" scroll="N">
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <table width="100%" class="tbl_bggray" cellpadding="5" border="0">
                <tr>
                    <td width="20%">Período:</td>
                    <td width="80%">de <b><bean:write name="relHeader" property="filtroDtInicio" /></b> a <b><bean:write name="relHeader" property="filtroDtFim" /></b></td>
                </tr>
                <tr>
                    <td colspan="2">Foram selecionados <b><bean:write name="relHeader" property="total" /></b> registros</td>
                </tr>
            </table>
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="765" layoutHeight="300" tableWidth="765" styleId="tableTitle" sortable="true" >
                <!--
                    header
                -->
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="12%" type="string">Linha</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="15%" type="string">Data de Aniversário</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="21%" type="string">Aniversariante</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="21%" type="string">Operador</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="15%" type="string">Data de Atendimento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="15%" type="string">Período do Dia</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                        <logic:iterate id="linha" name="relatorio" property="linhaAniversario">
                            <vivo:tbRow key="linha1" zebrar="s">
                                <vivo:tbRowColumn><bean:write name="linha" property="linha"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="linha" property="dataAniversario"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="linha" property="aniversariante"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="linha" property="operador"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="linha" property="dataAtendimento"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="linha" property="periodoDia"/></vivo:tbRowColumn>
                        </vivo:tbRow>
                        </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>

        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <table width="100%" class="tbl_bgGray">
            <tr>
                <td width="500"><img align="letf" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/campanha/Relatorio/begin.do'; top.frameApp.mostrar_div(); return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5"/></td>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cam_aniversario_imp">
                        <img id="imprimir" src="/FrontOfficeWeb/resources/images/bt_impressao_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_impressao_over.gif" onClick="imprimir();" style="border:none" hspace="5" vspace="5"/>
                    </acesso:controlHiddenItem>
                    </td>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cam_aniversario_arq">
                        <img id="gerararquivo" src="/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gerararq_over.gif" onClick="window.location.href='/FrontOfficeWeb/campanha/Relatorio/generarArquivo.do?idRelatorio=6'; return false" style="border:none" hspace="5" vspace="5"/>
                    </acesso:controlHiddenItem>
                    </td>
            </tr>
        </table>

        <vivo:quadroFlutuante onclick="cerrar();" id="dvEfetividade" idIframe="ifrmEfetividade" width="767" height="405" spacesTop="40" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Relatorio Efetividade" />

        </vivo:sessao>
        </acesso:controlHiddenItem>
    </netui-template:section>
    </netui-template:template>