<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="parametrizacaoBannerForm"
             type="VOLE.ParametrizacaoBanner.formBeans.ParametrizacaoBannerForm" />

<%!
private String getDescricaoByIdAreaBanner(VOLE.ParametrizacaoBanner.formBeans.ParametrizacaoBannerForm form, long idAreaBanner) {
    try {
        Set entries = form.getListaAreas().entrySet();
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (Long.parseLong((String)entry.getKey()) == idAreaBanner) {
                return (String) entry.getValue();
            }
        }
        return ConstantesCRM.SVAZIO;
    } catch (Exception e) {
        return ConstantesCRM.SVAZIO;
    }
}
private String getDescricaoByIdTipoBanner(VOLE.ParametrizacaoBanner.formBeans.ParametrizacaoBannerForm form, long idTipoBanner) {
    try {
        Set entries = form.getListaTiposBanner().entrySet();
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (Long.parseLong((String)entry.getKey()) == idTipoBanner) {
                return (String) entry.getValue();
            }
        }
        return ConstantesCRM.SVAZIO;
    } catch (Exception e) {
        return ConstantesCRM.SVAZIO;
    }
}
%>

<html>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript">
        var idBannerSelecionado = 0;
        var tipoBannerSelecionado = '';
        updateBanner = function(idBanner, dsURL, tipoBanner) {
            idBannerSelecionado = idBanner;
            tipoBannerSelecionado = tipoBanner.toUpperCase();
            if (tipoBannerSelecionado == 'FLASH'
                    || tipoBannerSelecionado == 'SWF') {
                $('div-link-banner').hide();
                $('dsLinkBanner').value = '';
            } else {
                $('div-link-banner').show();
            }
            $('dsLinkBanner').value = dsURL;
        }
        paginacao = function(pagina) {
            var nrPagina = parseInt($F('nrPagina'));
            if (pagina == 'proxima') {
                nrPagina = nrPagina + 1;
            } else if (pagina == 'anterior') {
                nrPagina = nrPagina - 1;
            } else {
                nrPagina = pagina;
            }
            $('nrPagina').value = nrPagina;
            selectAllOptions($('relacionamentosSelecionados'));
            selectAllOptions($('filtroAreas'));
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            $('formAssociacao').submit();
        }
        submitAssociacao = function() {
            if (idBannerSelecionado == 0) {
                alert('Por favor, selecione um banner para associação.');
            } else if (tipoBannerSelecionado != 'FLASH' && tipoBannerSelecionado != 'SWF' && !isValidURL($F('dsLinkBanner'))) {
                alert('Por favor, digite um valor de URL válido.');
            } else {
                new Ajax.Request('/FrontOfficeWeb/VOLE/ParametrizacaoBanner/salvarAssociacaoDesassociacaoBanner.do', {
                    method: 'get',
                    parameters: {
                        urlBanner : (tipoBannerSelecionado == 'FLASH' || tipoBannerSelecionado == 'SWF') ? '' : $F('dsLinkBanner').strip(),
                        operacao  : 'associacao',
                        idBanner  : idBannerSelecionado
                    }, onSuccess: function(e) {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.stopAnimation();
                        alert('Associação realizada com sucesso.');
                        parent.$('associacaoBanner').hide();
                        parent.pesquisar();
                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }, onFailure: function(e) {
                        window.top.frameApp.createErrorPopUp('erroAssociacaoBanner', 'Ocorreu um problema durante associação de banner.', e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }
        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.stopAnimation();
        }
    </script>
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<form id="formAssociacao" name="formAssociacao" style="margin:0;padding:0" method="post">
<html:hidden name="Form" property="nrPagina" styleId="nrPagina" />
<html:select name="Form" property="relacionamentosSelecionados" styleId="relacionamentosSelecionados" multiple="true" style="display:none">
    <% for (int i = 0; i < Form.getRelacionamentosSelecionados().length; i++) { %><option value="<%=Form.getRelacionamentosSelecionados()[i]%>"><%=Form.getRelacionamentosSelecionados()[i]%></option><% } %>
</html:select>
<html:select name="Form" property="filtroAreas" styleId="filtroAreas" style="display:none" multiple="true">
    <% for (int i = 0; i < Form.getFiltroAreas().length; i++) { %><option value="<%=Form.getFiltroAreas()[i]%>"><%=Form.getFiltroAreas()[i]%></option><% } %>
</html:select>
<vivo:tbTable layoutWidth="730" layoutHeight="300" tableWidth="728" styleId="tableBanners" sortable="true" selectable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="25%" type="string">Descrição do Banner</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="35%" type="string">Link do Banner</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="10%" type="string">Área</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="5%" type="string">Tipo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="banner" name="Form" property="listaBanners" indexId="idx" type="VOLE.ParametrizacaoBanner.formBeans.Banner">
        <vivo:tbRow key="linha">
            <vivo:tbRowColumn>
                <input type="radio"
                       name="banner-selecionado"
                       class="radio"
                       onclick="updateBanner('<bean:write name="banner" property="idBanner" format="#" />', '<bean:write name="banner" property="urlBanner" />', '<%= getDescricaoByIdTipoBanner(Form, banner.getIdTipoBanner()) %>')" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="banner" property="dsBanner" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="banner" property="urlBanner" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><%= getDescricaoByIdAreaBanner(Form, banner.getIdAreaBanner()) %></vivo:tbRowColumn>
            <vivo:tbRowColumn><%= getDescricaoByIdTipoBanner(Form, banner.getIdTipoBanner()) %></vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif"
                     class="button"
                     onmouseup="parent.mostrarImagemBanner('<bean:write name="banner" property="nmArquivoBanner" />')" />
            </vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
<table width="100%" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
    <tr>
        <td nowrap valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
        <td width="100%">
            <img src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" align="left">&nbsp;Visualizar Banner
        </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:3px;">
    <tr>
        <td width="20%" align="left">
            <logic:notEqual name="Form" property="nrPagina" value="1">
            <img src="<%=request.getContextPath()%>/resources/images/bt_anterior_nrml.gif" class="button" onmouseup="paginacao('anterior')" />
            </logic:notEqual>
        </td>
        <td width="60%" align="center">
            <%
            int totalPaginas = (int) Math.round(
                Form.getTotalItens() % Form.getItensPorPagina() == 0 ?
                ((double)Form.getTotalItens() / Form.getItensPorPagina())
                : ((double)Form.getTotalItens() / Form.getItensPorPagina()) + 0.5d
            );
            for (int i = 0; i < totalPaginas; i++){
                if(Form.getNrPagina() == (i+1)) { %>
                    <%= i+1 %>
                <% } else { %>
                    <a href="javascript:paginacao(<%=i+1%>);" style="color:#006699"><%=i+1%></a>
                <% }
            }%>
        </td>
        <td width="20%" align="right">
            <logic:notEqual name="Form" property="ultimaPagina" value="true">
            <img src="<%=request.getContextPath()%>/resources/images/bt_proxima_nrml.gif" class="button" onmouseup="paginacao('proxima')" />
            </logic:notEqual>
        </td>
    </tr>
</table>
<table width="100%" cellspacing="0">
    <tr>
        <td>
            <div id="div-link-banner">
                <strong>Link do Banner</strong>:&nbsp;
                <input type="text" id="dsLinkBanner" style="width:350px;" />
            </div>
        </td>
        <td align="right">
            <img src="<%=request.getContextPath()%>/resources/images/bt_associar_nrml.gif" class="button" onmouseup="submitAssociacao()" />
        </td>
    </tr>
</table>
</form>
</body>
</html>