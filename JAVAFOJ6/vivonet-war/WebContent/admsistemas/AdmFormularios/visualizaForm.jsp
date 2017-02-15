<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="admsistemas.AdmFormularios.Campo"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm" type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm"/>

<html>
    <head>
        <script language="javascript" for="window" event="onload">
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        </script>
    </head>
    <body bgcolor="#ededed">
        <table id="viewTable" border="0" cellpadding="3" cellspacing="3" height="100%" width="100%" bgcolor="#ededed">
            <tr>
                <td align="left" valign="top">
                    <table id="tabelaFormulario" border="0" cellpadding="3" cellspacing="3" width="99%" bgcolor="#ededed" style="margin-top:10px;">
                        <tr>
                        <%
                        Campo[] lista = Form.getListaCamposFormulario();

                        if (lista.length>0) {
                            for (int i = 0; i < lista.length; i++) {
                                String layout = lista[i].getLayoutApresentacao();
                                String lbCampo = lista[i].getLbCampo();
                                boolean isRequerido = lista[i].isRequerido();

                                if ("TEXT".equals(layout)) { %>
                                    <td valign="top"><%=lbCampo%>:</td>
                                    <td valign="top"><input type="text" value="" size="10" <%= lista[i].getFuncaoValidacao() %>/></td>
                                <%} else if (ConstantesCRM.CT_Select.equals(layout)) { %>
                                    <td valign="top"><%=lbCampo%>:</td>
                                    <td valign="top">
                                        <select>
                                            <% for(int j = 0; j < lista[i].getDominio().length;j++) { %>
                                                <option value=""><%=lista[i].getDominio()[j].getVlDominio()%></option>
                                            <% } %>
                                        </select>
                                    </td>
                                <% } else if ("LABEL".equals(layout)) { %>
                                    <td valign="top"><%=lbCampo%>:</td>
                                    <td valign="top">
                                        <% for(int j = 0; j < lista[i].getDominio().length;j++) { %>
                                                <%=lista[i].getDominio()[j].getVlDominio()%><br/>
                                        <% } %>
                                    </td>
                                <% } else if ("MEMO".equals(layout)) { %>
                                </tr><tr>
                                    <td valign="top"><%=lbCampo%>:</td>
                                    <td valign="top"><textarea cols="20" rows="8"></textarea></td>
                                </tr><tr>
                                <% } else if ("SELECTMULTI".equals(layout)) { %>
                                </tr><tr>
                                    <td valign="top"><%=lbCampo%>:</td>
                                    <td>
                                        <table>
                                        <tr>
                                            <td valign="top">
                                                <select size="3" style="width:100px;" name="idCampoExistente_<%= i %>" id="idCampoExistente_<%= i %>" multiple="true">
                                                    <% for(int j = 0; j < lista[i].getDominio().length;j++) { %>
                                                        <option value=""><%=lista[i].getDominio()[j].getVlDominio()%></option>
                                                    <% } %>
                                                </select>
                                            </td>
                                            <td width="40" align="">
                                                <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="if ($('idCampoExistente_<%= i %>').selectedIndex >=0) {transfereSelecaoLista($('idCampoExistente_<%= i %>'),$('listaCamposSelecionados_<%= i %>'),false,false);}" style="cursor:pointer;"/>
                                                <br/><br/>
                                                <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onclick="if ($('listaCamposSelecionados_<%= i %>').selectedIndex >=0) {transfereSelecaoLista($('idCampoExistente_<%= i %>'),$('listaCamposSelecionados_<%= i %>'),true,false);}" style="cursor:pointer;"/>
                                            </td>
                                            <td valign="top">
                                                <select size="3" style="width:100px;" name="listaCamposSelecionados_<%= i %>" id="listaCamposSelecionados_<%= i %>" multiple="true"></select>
                                            </td>
                                        </tr>
                                        </table>
                                    </td>
                                </tr><tr>
                                 <% } else if ("SELECTPREENCHIDO".equals(layout)) { %>
                                </tr><tr>
                                    <td valign="top"><%=lbCampo%>:</td>
                                    <td>
                                        <table>
                                        <tr>
                                            <td valign="top">
                                                <select size="3" style="width:100px;" name="idCampoExistente_<%= i %>" id="idCampoExistente_<%= i %>" multiple="true">
                                                   <option value=""></option>
                                                </select>
                                            </td>
                                            <td width="40" align="">
                                                <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="if ($('idCampoExistente_<%= i %>').selectedIndex >=0) {transfereSelecaoLista($('idCampoExistente_<%= i %>'),$('listaCamposSelecionados_<%= i %>'),false,false);}" style="cursor:pointer;"/>
                                                <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onclick="if ($('listaCamposSelecionados_<%= i %>').selectedIndex >=0) {transfereSelecaoLista($('idCampoExistente_<%= i %>'),$('listaCamposSelecionados_<%= i %>'),true,false);}" style="cursor:pointer;"/>
                                            </td>
                                            <td valign="top">
                                                <select size="3" style="width:100px;" name="listaCamposSelecionados_<%= i %>" id="listaCamposSelecionados_<%= i %>" multiple="true"></select>
                                            </td>
                                        </tr>
                                        </table>
                                    </td>
                                </tr><tr>
                                <%
                                }

                                if ((i%2) == 0) { %>
                                    </tr><tr>
                                <% } %>
                            <% } %>
                        <% } %>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>