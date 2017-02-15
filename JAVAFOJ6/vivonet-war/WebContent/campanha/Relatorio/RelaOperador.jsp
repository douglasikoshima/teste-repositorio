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
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>

<acesso:controlInitEnv/>
<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relaOperadorForm"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>
<bean:define id="numerico" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relaOperadorForm.numerico"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Fidelizacao" name="Relatorio"></netui-template:setAttribute>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">

    <acesso:controlHiddenItem nomeIdentificador="cam_ro_verpagina">
    <script>
        function imprimir()
        {
            // default values
            var default_url = "relaOperadorImpressao.jsp";

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
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="idFrelatorio" height="430" width="790" label="Relatório Operador" operacoes="" scroll="Y">

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
                    <td>Operador:</td>
                    <td><b><bean:write name="relHeader" property="sgUsuario" /></b></td>
                </tr>
                <tr>
                    <td colspan="2">Foram selecionados <b><bean:write name="relHeader" property="total" /></b> registros</td>
                </tr>
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

            <fid:table>
                <fid:tr bgColor="#545454">
                    <fid:headerTd>Operador</fid:headerTd>
                    <fid:headerTd>Publico Total</fid:headerTd>
                    <fid:headerTd>Meta Diária</fid:headerTd>
                    <fid:headerTd>Contatos Efetivos</fid:headerTd>
                    <fid:headerTd>Tempo Médio Operador(seg)</fid:headerTd>
                    <fid:headerTd>Motivos</fid:headerTd>
                    <fid:headerTd>Qtd.</fid:headerTd>
                    <fid:headerTd>Não Adesões</fid:headerTd>
                    <fid:headerTd>Reagendados</fid:headerTd>
                    <fid:headerTd>Adesões</fid:headerTd>
                    <fid:headerTd>Submotivo Oferta</fid:headerTd>
                    <fid:headerTd>Qtd.</fid:headerTd>
                </fid:tr>
                <logic:iterate id="linha" name="relatorio" property="numerico">
                    <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                        <fid:dataTd><bean:write name="linha" property="operador"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="publicoTotal"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="metaDiaria"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="contatosEfetivos"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="tmo"/></fid:dataTd>

                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="motivos">
                                <logic:iterate id="motivodescricao" name="linha" property="motivos.dsMotivoArray" offset="0" length="1">
                                    <bean:write name="motivodescricao"/>
                                </logic:iterate>
                            </logic:notEmpty>
                        </fid:dataTd>
                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="motivos">
                                <logic:iterate id="motivoquantidade" name="linha" property="motivos.qtdMotivoArray" offset="0" length="1">
                                    <bean:write name="motivoquantidade"/>
                                </logic:iterate>
                            </logic:notEmpty>
                        </fid:dataTd>

                        <fid:dataTd><bean:write name="linha" property="naoAdesoes"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="reagendados"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="adesoes"/></fid:dataTd>

                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="ofertas">
                                <logic:iterate id="ofertadescricao" name="linha" property="ofertas.dsOfertaArray" offset="0" length="1">
                                    <bean:write name="ofertadescricao"/>
                                </logic:iterate>
                            </logic:notEmpty>
                        </fid:dataTd>
                        <fid:dataTd>
                            <logic:notEmpty name="linha" property="ofertas">
                                <logic:iterate id="ofertaquantidade" name="linha" property="ofertas.qtdOfertaArray" offset="0" length="1">
                                  <bean:write name="ofertaquantidade"/>
                                </logic:iterate>
                            </logic:notEmpty>
                        </fid:dataTd>

                        <logic:greaterThan name="linha" property="motivos.dsMotivoArray" value="1">
                            <logic:iterate id="linhaofertadescricao" name="linha" property="ofertas.dsOfertaArray" offset="1" indexId="index">
                                <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                                    <fid:dataTd></fid:dataTd>
                                    <fid:dataTd></fid:dataTd>
                                    <fid:dataTd></fid:dataTd>
                                    <fid:dataTd></fid:dataTd>
                                    <fid:dataTd></fid:dataTd>
                                    <fid:dataTd>
                                        <logic:iterate id="linhamotivodescricao" name="linha" property="motivos.dsMotivoArray" offset="index" length="1">
                                            <bean:write name="linhamotivodescricao"/>
                                        </logic:iterate>
                                    </fid:dataTd>
                                    <fid:dataTd>
                                        <logic:iterate id="linhamotivoquantidade" name="linha" property="motivos.qtdMotivoArray" offset="index" length="1">
                                            <bean:write name="linhamotivoquantidade"/>
                                        </logic:iterate>
                                    </fid:dataTd>
                                    <fid:dataTd></fid:dataTd>
                                    <fid:dataTd></fid:dataTd>
                                    <fid:dataTd></fid:dataTd>
                                    <fid:dataTd><bean:write name="linhaofertadescricao"/></fid:dataTd>
                                    <fid:dataTd>
                                        <logic:iterate id="linhaofertaquantidade" name="linha" property="ofertas.qtdOfertaArray" offset="index" length="1">
                                            <bean:write name="linhaofertaquantidade"/>
                                        </logic:iterate>
                                    </fid:dataTd>
                                </fid:tr>
                            </logic:iterate>
                        </logic:greaterThan>
                    </fid:tr>
                </logic:iterate>
           </fid:table>
        </vivo:sessao>

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <table width="790" class="tbl_bgGray">
            <tr>
                <td width="500"><img align="letf" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/campanha/Relatorio/begin.do'; top.frameApp.mostrar_div(); return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5"/></td>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cam_ro_impr">
                        <img id="imprimir" src="/FrontOfficeWeb/resources/images/bt_impressao_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_impressao_over.gif" onClick="imprimir();" style="border:none" hspace="5" vspace="5"/>
                    </acesso:controlHiddenItem>
                    </td>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cam_ro_ga">
                        <img id="gerararquivo" src="/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gerararq_over.gif" onClick="window.location.href='/FrontOfficeWeb/campanha/Relatorio/generarArquivo.do?idRelatorio=3'; return false" style="border:none" hspace="5" vspace="5"/>

                    </acesso:controlHiddenItem>
                    </td>
            </tr>
        </table>

        </acesso:controlHiddenItem>
        </netui-template:section>
        </netui-template:template>
