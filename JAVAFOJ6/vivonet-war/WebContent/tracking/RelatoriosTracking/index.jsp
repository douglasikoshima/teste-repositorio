<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatoriosTrackingForm" /> 

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute value="FrontOffice - Atendimento" name="title"/>
<netui-template:setAttribute value="Atendimento" name="modulo"/>
<netui-template:section name="headerSection">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
    <script type="text/javascript" language="javaScript" src="javascript/RelatoriosTracking.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
	<script type="text/javascript" language="javascript">
		onload = function() {
		    if ($('inMassivo')) $('inMassivo').observe('click', setConsolidado);
		    if ($('inPremium')) $('inPremium').observe('click', setConsolidado);
		}
    </script>
    <style type="text/css">
        .radioCheckbox {border:none;background:none}
        select {width:270px;height:84px}
    </style>
</netui-template:section>
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="bodyRelatoriosTracking" height="470" width="790" label="Tracking" operacoes="Relatórios" scroll="N">
        <form action="<%=request.getContextPath()%>/tracking/RelatoriosTracking/getRelatorio.do" name="getRelatorio" id="getRelatorio" method="post" onsubmit="return false;">
        <input type="hidden" name="pageNumber" value="<bean:write name="Form" property="filtros.paginacao.pageNumber" format="#" />" />
        <table width="100%" class="tbl_bggray" align="center">
            <tr>
                <td width="130" nowrap rowspan="3" style="padding-left:10px;"><strong>Tipos de relatório</strong></td>
                <td valign="top" width="100%">
                    <html:radio name="Form" title="Tipo de Relatório" property="tipoRelatorio" styleId="RSPA" styleClass="radioCheckbox" value="RSPA" /> Relatório de pedidos em aberto<br/>
                </td>
            </tr>
            <tr>
                <td>
                    <html:radio name="Form" title="Tipo de Relatório" property="tipoRelatorio" styleId="RDPA" styleClass="radioCheckbox" value="RDPA" /> Relatório detalhado de pedidos em aberto<br/>
                </td>
            </tr>
            <tr>
                <td>
                    <html:radio name="Form" title="Tipo de Relatório" property="tipoRelatorio" styleId="RDOL" styleClass="radioCheckbox" value="RDOL" /> Relatório detalhado para monitorar o operador logístico<br/>
                </td>
            </tr>
        </table>
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:quadro id="qdMain" height="330" width="780" scroll="no" label="Filtros">
        <table width="100%">
            <tr>
                <td nowrap><strong>Relatório consolidado</strong></td>
                <td><html:checkbox name="Form" property="inConsolidado" styleClass="radioCheckbox" styleId="inConsolidado" onclick="manageConsolidado(this.checked)" /></td>
                <td colspan="2" align="right">
                    <strong>Data</strong>&nbsp;&nbsp;&nbsp;
                    <html:text name="Form" property="dtPeriodoDe" styleId="dtPeriodoDe" size="10" maxlength="10" onblur="validaDataInput(this)" onkeyup="Formatar(this.value, this.form.name, this.name, 'data')" />
                    <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtPeriodoDe', '%d/%m/%Y');">
                </td>
            </tr>
            <tr>
                <td><strong>Regional</strong></td>
                <td align="center">Regionais disponíveis</td>
                <td></td>
                <td align="center">Regionais selecionadas</td>
            </tr>
            <tr>
                <td></td>
                <td valign="top">
                    <html:select name="Form" property="regionaisDisponiveis" styleId="regionaisDisponiveis" multiple="true" styleClass="multipleSelects">
                        <logic:iterate id="itRegionaisDisponiveis" name="Form" property="filtros.regionais.disponiveis.IDValorVOArray">
                            <option value="<bean:write name="itRegionaisDisponiveis" property="valor" />"><bean:write name="itRegionaisDisponiveis" property="valor" /></option>
                        </logic:iterate>
                    </html:select>
                </td>
                <td align="center" valign="middle">
                    <a href="javascript:transfereSelecaoLista($('regionaisDisponiveis'),$('regionaisSelecionadas'), false, false, false);"><img id="imgRightRegionais" src="<%=request.getContextPath()%>/resources/images/bt_rightaln_nrml.gif" style="clear:both;" border="0" /></a>
                    <a href="javascript:transfereSelecaoLista($('regionaisDisponiveis'),$('regionaisSelecionadas'), true, false, false);"><img id="imgLeftRegionais" src="<%=request.getContextPath()%>/resources/images/bt_leftaln_nrml.gif" style="clear:both;margin-bottom:5px;" border="0" /></a><br/>
                </td>
                <td valign="top" style="padding-left:10px;">
                    <html:select title="Regional" name="Form" property="regionaisSelecionadas" styleId="regionaisSelecionadas" multiple="true" styleClass="multipleSelects">
                        <logic:present name="Form" property="filtros.regionais.selecionados">
                            <logic:iterate id="itRegionaisSelecionadas" name="Form" property="filtros.regionais.selecionados.IDValorVOArray">
                                <option value="<bean:write name="itRegionaisSelecionadas" property="valor" />"><bean:write name="itRegionaisSelecionadas" property="valor" /></option>
                            </logic:iterate>
                        </logic:present>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td><strong>Segmento</strong></td>
                <td colspan="3">
                    <html:checkbox name="Form" property="inPremium" title="Segmento" styleClass="radioCheckbox" styleId="inPremium" /> Premium
                    <html:checkbox name="Form" property="inMassivo" title="Segmento" styleClass="radioCheckbox" styleId="inPremium" style="margin-left:30px;" /> Massivo
                </td>
            </tr>
            <tr>
                <td><strong>Canal de Venda</strong></td>
                <td align="center">Canais de Venda disponíveis</td>
                <td></td>
                <td align="center">Canais de Venda selecionados</td>
            </tr>
            <tr>
                <td></td>
                <td valign="top">
                    <html:select name="Form" property="canaisVendaDisponiveis" styleId="canaisVendaDisponiveis" multiple="true" styleClass="multipleSelects">
                        <logic:iterate id="itCanaisVendaDisponiveis" name="Form" property="filtros.canaisVenda.disponiveis.IDValorVOArray">
                            <option value="<bean:write name="itCanaisVendaDisponiveis" property="id" />"><bean:write name="itCanaisVendaDisponiveis" property="valor" /></option>
                        </logic:iterate>
                    </html:select>
                </td>
                <td align="center" valign="middle">
                    <a href="javascript:transfereSelecaoLista($('canaisVendaDisponiveis'),$('canaisVendaSelecionados'), false, false, false);"><img id="imgRightCanaisVenda" src="<%=request.getContextPath()%>/resources/images/bt_rightaln_nrml.gif" style="clear:both;" /></a>
                    <a href="javascript:transfereSelecaoLista($('canaisVendaDisponiveis'),$('canaisVendaSelecionados'), true, false, false);"><img id="imgLeftCanaisVenda" src="<%=request.getContextPath()%>/resources/images/bt_leftaln_nrml.gif" style="clear:both;margin-bottom:5px;" /></a><br/>
                </td>
                <td valign="top" style="padding-left:10px;">
                    <html:select title="Canal de Venda" name="Form" property="canaisVendaSelecionados" styleId="canaisVendaSelecionados" multiple="true" styleClass="multipleSelects">
                        <logic:present name="Form" property="filtros.canaisVenda.selecionados">
                            <logic:iterate id="itCanaisVendaSelecionados" name="Form" property="filtros.canaisVenda.selecionados.IDValorVOArray">
                                <option value="<bean:write name="itCanaisVendaSelecionados" property="id" />"><bean:write name="itCanaisVendaSelecionados" property="valor" /></option>
                            </logic:iterate>
                        </logic:present>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <fieldset>
                        <legend>Agrupador</legend>
                        <input class="radioCheckbox" type="radio" name="agrupadorCanais" value="arrayB2C" id="arrayB2C" onclick="manageCanaisVenda(this.value)" /> B2C
                        <input class="radioCheckbox" type="radio" name="agrupadorCanais" value="arrayB2B" id="arrayB2B" onclick="manageCanaisVenda(this.value)" /> B2B
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="right">
                    <a href="javascript:submitPesquisa()"><img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" /></a>
                    <a href="javascript:manageConsolidado(false);limparDatas()"><img src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif" /></a>
                </td>
            </tr>
        </table>
        </vivo:quadro>
        <a href="/FrontOfficeWeb/index.jsp"><img style="margin:10px 0 0 10px;border:none;" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" /></a>
        </form>
    </vivo:sessao>
</netui-template:section>
</netui-template:template>