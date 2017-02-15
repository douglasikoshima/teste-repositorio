<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:setAttribute name="title" value="Ordenar Menu"/>
    <netui-template:setAttribute name="modulo" value="VOL/TAV"/>

    <netui-template:section name="headerSection">

    <script language="Javascript">
    <!--

        function canalChange() {
            document.forms[0].action = 'selecionarSubSistema.do';
            document.forms[0].submit();
        }

        function validarNumero() {
            if (event.keyCode < 48 || event.keyCode > 57)
                event.keyCode = 0;
        }

        function gravar() {
            for(i=0; ; i++) {
                seq = document.forms[0].sqSequencia[i];
                if (seq == null) break;
                if (seq.value == '') {
                    alert('Valor de sequência inválido.');
                    seq.focus();
                    return;
                }
            }
            document.forms[0].action = 'gravar.do';
            document.forms[0].submit();
        }

        function limpar() {
            document.forms[0].action = 'limpar.do';
            document.forms[0].submit();
        }

        function pesquisar() {
            if (document.forms[0].idCanal.value == '0') {
                alert('Selecione um canal.');
                return;
            }
            if (document.forms[0].idSubSistema.value == '0') {
                alert('Selecione um menu.');
                return;
            }
            document.forms[0].submit();
        }

    // -->
    </script>

    </netui-template:section>

    <netui-template:section name="bodySection">

    <acesso:controlHiddenItem nomeIdentificador="ord_menu">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            //mostrar_div();
        </script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="468" width="790" label="Parametrização" operacoes="Ordenação de Menu" scroll="no">

            <table><tr><td height="4"></td></tr></table>

            <table width="100%" border="0" cellspacing="1" cellpadding="1">

            <html:form action="/VOLTAV/ordenarMenu/pesquisarMenu.do" onsubmit="return false;" method="post">

                <tr>
                    <td>
                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center">
                                <table width="98%" border="0" cellspacing="2" cellpadding="0" align="center" class="tbl_bgBlue">
                                    <tr><td height="6"></td></tr>
                                    <tr>
                                        <td class="tblEstatica_campoNome" align="left" width="100">&nbsp;
                                            <netui:label value="Canal:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left" colspan="2">
                                            <html:select property="idCanal" onchange="javascript:canalChange();">
                                                <html:option value="0">Selecione...</html:option>
                                                <html:option value="15">VOL</html:option>
                                                <html:option value="13">TAV</html:option>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;
                                            <netui:label value="Menu:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td>
                                            <bean:define id="comboSubSistema" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="comboSubSistema.itemMenuVOArray"/>
                                            <html:select property="idSubSistema">
                                                <html:option value="0">Selecione...</html:option>
                                                <html:option value="-1">&lt; Menu Principal &gt;</html:option>
                                                <html:options collection="comboSubSistema" property="idItemMenu" labelProperty="nmMenu" />
                                            </html:select>
                                        </td>
                                        <td align="left">
                                            <img style="cursor:hand;border:none" tabindex="3" id="btLimpar" onclick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                        </td>
                                        <td align="left">
                                            <img style="cursor:hand;border:none" tabindex="4" id="btPesquisar" onclick="pesquisar();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                        </td>
                                    </tr>
                                    <tr><td height="6"></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td height="4"></td></tr>
                        <!-- Testa se alguma busca foi realizada -->
                        <logic:present name="listaMenus">
                        <bean:size id="itensLista" name="listaMenus" property="itemMenuVOArray" />
                        <logic:greaterThan value="0" name="itensLista">

                                <td align="center">
                                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                        <tr>
                                            <td width="100%" align="center">
                                                <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="285" tableWidth="705" styleId="tableTitle" sortable="true">
                                                    <vivo:tbHeader>
                                                        <vivo:tbHeaderColumn align="left"   width="200" type="string">Item&nbsp;</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left"   width="15" type="string">Seqüência</vivo:tbHeaderColumn>
                                                    </vivo:tbHeader>
                                                    <vivo:tbRows>
                                                        <logic:iterate name="listaMenus" property="itemMenuVOArray" id="itemMenu">
                                                            <html:hidden name="itemMenu" property="idItemMenu" />
                                                            <vivo:tbRow key="linha">
                                                                <vivo:tbRowColumn>
                                                                    <bean:write name="itemMenu" property="nmMenu"/>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <input type="text" name="sqSequencia" style="text-align:right;" size="3" maxlength="3" onkeypress="validarNumero();" value="<bean:write name="itemMenu" property="sqSequencia"/>">
                                                                </vivo:tbRowColumn>
                                                            </vivo:tbRow>
                                                        </logic:iterate>
                                                    </vivo:tbRows>
                                                </vivo:tbTable>

                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td align="right">
                                    <img hspace="5" style="border:none;cursor:hand;"  onclick="gravar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                                </td>
    
                            </tr>

                        <tr><td height="4"></td></tr>
                        </logic:greaterThan>
                        <logic:equal value="0" name="itensLista">
                            <tr>
                                <td align="center">
                                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                        <tr>
                                            <td width="90%" align="center" class="tblEstatica_campoNome">
                                                Não foi encontrado nenhum item de menu.
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </logic:equal>
                        </logic:present>
                    </table>
                    </td>
                </tr>
            </html:form>
                        <tr>
                            <td>
                                <img align="letf" id="voltar" tabindex="6" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5" />
                            </td>

                        </tr>

            </table>

            <logic:present name="msg">
                <script language="javascript" for="window" event="onload">
                <!--
                    alert('<abean:write name="msg" />');
                    oculta_div();
                // -->
                </script>
            </logic:present>

            </vivo:sessao>
    </acesso:controlHiddenItem>

    <vivo:alert atributo="msgError" scope="request"/>

    </netui-template:section>

</netui-template:template>
