<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">

<bean:define id="Form"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterDadosForm"/>
<bean:define id="Atributos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterDadosForm.atributos"/>

    <netui-template:section name="headerSection">
        <script type="text/javascript">
            onload = function() {
                parent.parent.parent.oculta_div();
            }
            function salvar(){
                document.forms[0].action="salvar.do?idSubAssunto="+document.getElementById("idSubAssunto").value;
                document.forms[0].target = "frameAtributos";
                parent.parent.parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }
        </script>
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_perg_verpagina">
    <html:form action="/cliente/TelaInicial/DetalheCliente/customer/loadAtributos.do" method="get">
    <html:hidden name="Form" property="idSubAssunto" styleId="idSubAssunto"/>

    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="7"></div>

    <vivo:quadro id="perguntas" height="320" width="470">

        <table width="100%" cellpadding="0" cellspacing="0">
            <% int i = 0; %>
            <logic:iterate name="Atributos" id="ListaAtributos" indexId="idx">
                <% if (i%2 != 1){ %>
                    <tr>
                    <td valign="top" width="50%">
                <% }else{ %>
                    <td valign="top" width="50%">
                <% } %>

                <bean:define id="vlpossiveis" name="ListaAtributos" property="valoresPossiveisArray"/>

                <logic:equal name="ListaAtributos" property="idTipoApresentacao" value="1">
                <!-- ComboBox -->
                    <html:hidden name="ListaAtributos" property="idAtributo"/>
                    <html:hidden name="ListaAtributos" property="idTipoApresentacao"/>
                    <table>
                        <tr>
                            <td width="15" valign="top"><img vspace="3" src="/FrontOfficeWeb/resources/images/icon_seta_right_small.gif"></td>
                            <td valign="top"><b><bean:write name="ListaAtributos" property="dsAtributo"/></b></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <select name="<bean:write name="ListaAtributos" property="idAtributo"/>">
                                    <option value="">-- Sem Resposta --</option>
                                    <logic:iterate id="radioloop" name="vlpossiveis">
                                        <logic:equal name="radioloop" property="inSelecionado" value="1">
                                            <option selected value="<bean:write name="radioloop" property="idValorPossivel"/>"/><bean:write name="radioloop" property="dsValorPossivel"/>
                                        </logic:equal>
                                        <logic:equal name="radioloop" property="inSelecionado" value="0">
                                            <option value="<bean:write name="radioloop" property="idValorPossivel"/>"/><bean:write name="radioloop" property="dsValorPossivel"/>
                                        </logic:equal>
                                    </logic:iterate>
                                </select>
                            </td>
                        </tr>
                    </table>

                </logic:equal>

                <logic:equal name="ListaAtributos" property="idTipoApresentacao" value="2">
                <!-- CheckBox -->
                    <html:hidden name="ListaAtributos" property="idAtributo"/>
                    <html:hidden name="ListaAtributos" property="idTipoApresentacao"/>
                    <table>
                        <tr>
                            <td width="15" valign="top"><img vspace="3" src="/FrontOfficeWeb/resources/images/icon_seta_right_small.gif"></td>
                            <td valign="top"><b><bean:write name="ListaAtributos" property="dsAtributo"/></b></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td align="left">
                                <table align="left">
                                    <logic:iterate id="radioloop" name="vlpossiveis" indexId="ctx">
                                    <tr>
                                        <logic:equal name="radioloop" property="inSelecionado" value="1">
                                            <td align="left"><input checked class="radio" type="checkbox" name="<bean:write name="ListaAtributos" property="idAtributo"/><%=ctx%>" value="<bean:write name="radioloop" property="idValorPossivel"/>"></td>
                                        </logic:equal>
                                        <logic:equal name="radioloop" property="inSelecionado" value="0">
                                            <td align="left"><input class="radio" type="checkbox" name="<bean:write name="ListaAtributos" property="idAtributo"/><%=ctx%>" value="<bean:write name="radioloop" property="idValorPossivel"/>"></td>
                                        </logic:equal>
                                        <td><bean:write name="radioloop" property="dsValorPossivel"/></td>
                                    </tr>
                                    </logic:iterate>
                                </table>
                            </td>
                        </tr>
                    </table>
                </logic:equal>

                <logic:equal name="ListaAtributos" property="idTipoApresentacao" value="3">
                <!-- Memo -->
                    <html:hidden name="ListaAtributos" property="idAtributo"/>
                    <html:hidden name="ListaAtributos" property="idTipoApresentacao"/>
                    <table>
                        <tr>
                            <td width="15" valign="top"><img vspace="3" src="/FrontOfficeWeb/resources/images/icon_seta_right_small.gif"></td>
                            <td valign="top"><b><bean:write name="ListaAtributos" property="dsAtributo"/></b></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <html:hidden name="ListaAtributos" property="valorLivre.idValorLivre"/>
                                <textarea name="<bean:write name="ListaAtributos" property="idAtributo"/>"><bean:write name="ListaAtributos" property="valorLivre.dsValorLivre"/></textarea>
                            </td>
                        </tr>
                    </table>
                </logic:equal>

                <logic:equal name="ListaAtributos" property="idTipoApresentacao" value="4">
                <!-- Radio -->
                    <html:hidden name="ListaAtributos" property="idAtributo"/>
                    <html:hidden name="ListaAtributos" property="idTipoApresentacao"/>
                    <img src="/FrontOfficeWeb/resources/images/icon_seta_right_small.gif" hspace="5"><bean:write name="ListaAtributos" property="dsAtributo"/>
                        <br><logic:iterate id="radioloop" name="vlpossiveis">
                            <br><input class="radio" type="radio" name="<bean:write name="ListaAtributos" property="idAtributo"/>" value="<bean:write name="radioloop" property="idValorPossivel"/>"><bean:write name="radioloop" property="dsValorPossivel"/></br>
                        </logic:iterate></br>
                </logic:equal>

                <logic:equal name="ListaAtributos" property="idTipoApresentacao" value="5">
                <!-- Text Box -->
                    <html:hidden name="ListaAtributos" property="idAtributo"/>
                    <html:hidden name="ListaAtributos" property="idTipoApresentacao"/>
                    <table>
                        <tr>
                            <td width="15" valign="top"><img vspace="3" src="/FrontOfficeWeb/resources/images/icon_seta_right_small.gif"></td>
                            <td valign="top"><b><bean:write name="ListaAtributos" property="dsAtributo"/></b></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <html:hidden name="ListaAtributos" property="valorLivre.idValorLivre"/>
                                <input type="text" name="<bean:write name="ListaAtributos" property="idAtributo"/>" value="<bean:write name="ListaAtributos" property="valorLivre.dsValorLivre"/>"/>
                            </td>
                        </tr>
                    </table>
                </logic:equal>

                <logic:equal name="ListaAtributos" property="idTipoApresentacao" value="6">
                <!-- List Box -->
                    <html:hidden name="ListaAtributos" property="idAtributo"/>
                    <html:hidden name="ListaAtributos" property="idTipoApresentacao"/>
                    <img src="/FrontOfficeWeb/resources/images/icon_seta_right_small.gif" hspace="5"><b><bean:write name="ListaAtributos" property="dsAtributo"/></b>
                    <br><select name="<bean:write name="ListaAtributos" property="idAtributo"/>" multiple="true" size="5">
                        <logic:iterate id="radioloop" name="vlpossiveis" indexId="idx">

                            <logic:equal name="radioloop" property="inSelecionado" value="1">
                                <option selected value="<bean:write name="radioloop" property="idValorPossivel"/>"/><bean:write name="radioloop" property="dsValorPossivel"/>
                            </logic:equal>
                            <logic:equal name="radioloop" property="inSelecionado" value="0">
                                <option value="<bean:write name="radioloop" property="idValorPossivel"/>"/><bean:write name="radioloop" property="dsValorPossivel"/>
                            </logic:equal>

                        </logic:iterate>
                    </select>
                </logic:equal>

                <% if (i%2 != 1){ %>
                    </td>
                <% }else{ %>
                    </td>
                    </tr>
                <% } %>
                <% i++; %>
            </logic:iterate>
            <% if (i%2 != 1){ %>
               </tr>
            <% } %>

            <tr>
                <td height="30" colspan="2" align="right" valign="middle">
                <acesso:controlHiddenItem nomeIdentificador="cli_perg_gravar">
                    <img hspace="10" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="salvar(); return false"/>
                </acesso:controlHiddenItem>
                </td>
            </tr>

        </table>

    </vivo:quadro>
    </html:form>
    </acesso:controlHiddenItem>
    </netui-template:section>

</netui-template:template>