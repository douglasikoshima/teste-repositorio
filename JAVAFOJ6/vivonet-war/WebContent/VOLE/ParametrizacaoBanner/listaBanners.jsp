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
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<vivo:tbTable layoutWidth="746" layoutHeight="160" tableWidth="744" styleId="tableBanners" sortable="true" selectable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="left" width="20%" type="string">Descrição do Banner</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="30%" type="string">Link do Banner</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="15%" type="string">Área</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="10%" type="string">Tipo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="15%" type="string">Autenticação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="banner" name="Form" property="listaBanners" indexId="idx" type="VOLE.ParametrizacaoBanner.formBeans.Banner">
        <vivo:tbRow key="linha">
            <vivo:tbRowColumn><bean:write name="banner" property="dsBanner" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="banner" property="urlBanner" /></vivo:tbRowColumn>
            <vivo:tbRowColumn><%= getDescricaoByIdAreaBanner(Form, banner.getIdAreaBanner()) %></vivo:tbRowColumn>
            <vivo:tbRowColumn><%= getDescricaoByIdTipoBanner(Form, banner.getIdTipoBanner()) %></vivo:tbRowColumn>
            <vivo:tbRowColumn><%= banner.isAutenticacao() ? "COM" : "SEM"%></vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif" onmouseup="mostrarImagemBanner('<bean:write name="banner" property="nmArquivoBanner" />')" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn><img src="<%=request.getContextPath()%>/resources/images/bt_icon_alterar.gif" class="button" onmouseup="alterarBanner(<bean:write name="banner" property="idBanner" format="#" />)" /></vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
<table width="762" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
    <tr>
        <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
        <td>
            <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
        </td>
        <td nowrap>
            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="left">&nbsp;Alterar Banner
        </td>
        <td width="100%">
            <img src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" align="left">&nbsp;Visualizar Banner
        </td>
    </tr>
</table>
<table width="100%" cellpadding="0" height="15">
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
<script type="text/javascript">
    $('nrPagina').value = <bean:write name="Form" property="nrPagina" format="#" />;
</script>
</body>
</html>