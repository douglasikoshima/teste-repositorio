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

    <netui-template:setAttribute name="title" value="Configuração de Sid"/>
    <netui-template:setAttribute name="modulo" value="VOL/TAV"/>

    <netui-template:section name="headerSection">

    <script language="Javascript">
    <!--

        var numSelecionados = 0;

        function computarSelecionados(chk) {
            if (chk.checked)
                numSelecionados++;
            else
                numSelecionados--;
        }

        function gravar(sid) {
            document.forms[0].target = '';
            document.forms[0].nrSid.value = sid;
            document.forms[0].action = 'alterarSid.do';
            document.forms[0].submit();
        }

        function validarNumero() {
            if (event.keyCode < 48 || event.keyCode > 57)
                event.keyCode = 0;
        }

        function alterar() {
            if (numSelecionados == 0) {
                alert('Selecione ao menos um item.');
                return;
            }
            divAlteraSid.style.display = '';
            document.forms[0].target = "ifrmAlteraSid";
            document.forms[0].action = "alteraSid.jsp";
            document.forms[0].submit();
            ifrmAlteraSid.src = 'alteraSid.jsp';
        }

        function limpar() {
            document.forms[0].action = 'limpar.do';
            document.forms[0].submit();
        }

        function pesquisar() {
            document.forms[0].target = '';
            document.forms[0].action = 'pesquisarInSid.do';
            document.forms[0].submit();
        }

        function applyAll(valor) {
            count = document.forms[0].selecionados.length;
            if (count) {
                for(i=0; i<count; i++) {
                    document.forms[0].selecionados[i].checked = valor.checked;
                    computarSelecionados(document.forms[0].selecionados[i]);
                }
            } else {
                document.forms[0].selecionados.checked = valor.checked;
                computarSelecionados(document.forms[0].selecionados);
            }
        }

    // -->
    </script>

    </netui-template:section>

    <netui-template:section name="bodySection">

    <acesso:controlHiddenItem nomeIdentificador="in_sid">
        <!--APLICAÇÃO->MENU-->        
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />

        <script>
            //mostrar_div();
        </script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="468" width="790" label="Configuração" operacoes="Configuração de InSid" scroll="no">

            <table><tr><td height="4"></td></tr></table>

            <table width="100%" border="0" cellspacing="1" cellpadding="1">

            <html:form action="/VOLTAV/manterInSid/pesquisarInSid.do" method="post" onsubmit="return false;">
                <html:hidden property="nrSid" />

                <tr>
                    <td>
                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center">
                                <table width="98%" border="0" cellspacing="2" cellpadding="0" align="center" class="tbl_bgBlue">
                                    <tr><td height="15"></td></tr>
                                    <tr>
                                        <td class="tblEstatica_campoNome" align="left" width="100">
                                            <netui:label value="Canal:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left" colspan="2">
                                            <html:select property="idCanal">
                                                <html:option value="0">Selecione...</html:option>
                                                <html:option value="1">CRC (Call Center)</html:option>
                                                <html:option value="15">VOL</html:option>
                                                <html:option value="13">TAV</html:option>
                                                <html:option value="9">URA</html:option>                                             
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;
                                            <netui:label value="Regional:" styleClass="tblEstatica_campoNome"/>
                                        </td>                                                                               
                                        <td align="left" colspan="2">                                              
                                            <bean:define id="cmbRegional" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="comboRegional.VOLTAVRegionalVOArray"/>
                                            <html:select property="idOperadora">
                                                <html:option value="0">Selecione...</html:option>
                                                <html:options collection="cmbRegional" property="idOperadora" labelProperty="dsOperadora" />
                                            </html:select>
                                        </td>                                                                                                        
                                    </tr>
                                       <tr>
                                        <td>&nbsp;
                                            <netui:label value="Funcionalidade:" styleClass="tblEstatica_campoNome"/>
                                        </td>                                       
                                        <td align="left" colspan="2">
                                            <bean:define id="comboFuncionalidade" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="comboFuncionalidade.funcionalidadeInSidVOArray"/>
                                            <html:select property="idFuncionalidade">
                                                <html:option value="0">Selecione...</html:option>
                                                <html:options collection="comboFuncionalidade" property="idApi" labelProperty="nmFuncionalidade" />
                                            </html:select>
                                        </td>                                                                                                        
                                    </tr>
                                    <tr><td height="15"></td></tr>
                                    <tr>                                     
                                       <td align="left">
                                        </td>
                                        <td align="left">
                                            <acesso:controlHiddenItem nomeIdentificador="limpar_in_sid">
                                                <img style="cursor:hand;border:none" tabindex="3" id="btLimpar" onclick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                            </acesso:controlHiddenItem>
                                            <acesso:controlHiddenItem nomeIdentificador="pesquisar_in_sid">
                                                <img style="cursor:hand;border:none" tabindex="5" id="btPesquisar" onclick="pesquisar();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                            </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                    <tr><td height="15"></td></tr>

                                </table>
                            </td>
                        </tr>
                        <tr><td height="4"></td></tr>
                        <!-- Testa se alguma busca foi realizada -->
                        <logic:present name="listaInSid">
                        <bean:size id="itensLista" name="listaInSid" property="pesquisaInSidVOArray" />
                        <logic:greaterThan value="0" name="itensLista">

                                <td align="center">
                                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                        <tr>
                                            <td width="100%" align="center">
                                                <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="220" tableWidth="705" styleId="tableTitle" sortable="false">
                                                    <vivo:tbHeader>
                                                        <vivo:tbHeaderColumn align="left" width="10" type="string">
                                                            <input type="checkbox" class="checkbox" onclick="applyAll(this);">
                                                        </vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left" width="140" type="string">Funcionalidade</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left" width="100" type="string">Canal</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left" width="100" type="string">Regional</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left" width="50"  type="string">No. SID</vivo:tbHeaderColumn>
                                                    </vivo:tbHeader>
                                                    <vivo:tbRows>
                                                        <logic:iterate name="listaInSid" property="pesquisaInSidVOArray" id="itemSid">
                                                            <vivo:tbRow key="linha">
                                                                <vivo:tbRowColumn>
                                                                    <input type="checkbox" class="checkbox" style="border:none;background:none;"
                                                                           name="selecionados"
                                                                           value="<bean:write name="itemSid" property="idCanal"/>|<bean:write name="itemSid" property="idGrupoOperadora"/>|<bean:write name="itemSid" property="nrInSid"/>|<bean:write name="itemSid" property="idApi"/>"
                                                                           onclick="computarSelecionados(this);">
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <bean:write name="itemSid" property="nmFuncionalidade"/>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <bean:write name="itemSid" property="nmCanal"/>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <bean:write name="itemSid" property="nmGrupoOperadora"/>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <bean:write name="itemSid" property="nrInSid"/>
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
                                <acesso:controlHiddenItem nomeIdentificador="alterar_in_sid">
                                    <img hspace="5" style="border:none;cursor:hand;"  onclick="alterar();" src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_alterar_over.gif'"/>
                                </acesso:controlHiddenItem>
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
                                                Não foi encontrado nenhuma funcionalidade.
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

        <vivo:quadroFlutuante label="Alterar Sid" scroll="false" src="" id="divAlteraSid" idIframe="ifrmAlteraSid" spacesTop="150" spacesLeft="300" height="100" width="270" url="<%=request.getContextPath()%>" display="none"></vivo:quadroFlutuante>

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
