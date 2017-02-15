<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="parametrizacaoBannerForm"
             type="VOLE.ParametrizacaoBanner.formBeans.ParametrizacaoBannerForm" />

<html>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<vivo:tbTable layoutWidth="746" layoutHeight="176" tableWidth="745" styleId="tableBanners" sortable="true" selectable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="left" width="5%" type="string"><input type="checkbox" class="checkbox" onclick="manageCheckboxes(this);" /></vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="5%" type="string">UF</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="20%" type="string">Área do Banner</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="35%" type="string">Link do Banner</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="30%" type="string">Descrição do Banner</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="relacionamento" name="Form" property="listaRelacionamentos" indexId="idx" type="br.com.vivo.fo.dbclasses.RelacionamentoBannerVOLE">
        <vivo:tbRow key="linha">
            <vivo:tbRowColumn>
                <input type="checkbox"
                       name="cb_<%=idx%>"
                       value="<bean:write name="relacionamento" property="idRelacionamentoBannerVOLE" format="#" />"
                       class="checkbox-relacionamento checkbox" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="relacionamento" property="sgUF" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="relacionamento" property="dsAreaBannerVOLE" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="relacionamento" property="urlBannerVOLE" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <bean:write name="relacionamento" property="dsBannerVOLE" />
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <% if (relacionamento.getIdBannerVOLE() != 0) { %>
                <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif"
                     class="button"
                     onmouseup="mostrarImagemBanner('<bean:write name="relacionamento" property="nmBannerVOLE" />')" />
                <% } %>
            </vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
<table width="762" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
    <tr>
        <td nowrap valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
        <td width="100%">
            <img src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" align="left">&nbsp;Visualizar Banner
        </td>
    </tr>
</table>
</body>
</html>