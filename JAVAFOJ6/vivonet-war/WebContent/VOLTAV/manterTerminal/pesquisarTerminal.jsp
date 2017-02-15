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

<bean:define id="cadastroTerminalForm"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Terminal"/>
    <netui-template:setAttribute name="modulo" value="VOL/TAV"/>
    <netui-template:section name="headerSection">
        <script language="Javascript">
            function ufChange() {
                document.forms[0].target = '';
                document.forms[0].action = 'obterComboMunicipio.do';
                document.forms[0].submit();
            }
    
            function municipioChange() {
                document.forms[0].target = '';
                document.forms[0].action = 'obterComboLoja.do';
                document.forms[0].submit();
            }
    
            function lojaChange() {
                document.forms[0].target = '';
                document.forms[0].action = 'obterComboTerminal.do';
                document.forms[0].submit();
            }
    
            function reiniciarSenha(id) {
                if (window.confirm('Confirma reinicialização da senha para este terminal?')) {
                    document.forms[0].target = '';
                    document.forms[0].idTerminal.value = id;
                    document.forms[0].action = 'reiniciarSenhaLojista.do';
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
    
            function removerTerminal(idTerminal, idPessoaDePara, nmMunicipio, nmLoja) {
                if (window.confirm('Deseja realmente excluir o cadastro deste terminal TAV?')) {
                    document.forms[0].target = '';
                    document.forms[0].idTerminal.value = idTerminal;
                    document.forms[0].idPessoaDePara.value = idPessoaDePara;
                    document.forms[0].nmMunicipio.value = nmMunicipio;
                    document.forms[0].nmPessoa.value = nmLoja;
                    document.forms[0].action = 'removerTerminal.do';
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
    
            function limpar() {
                document.forms[0].target = '';
                document.forms[0].action = 'limpaForm.do';
                document.forms[0].submit();
            }
    
            function alterarTerminal(id, idPessoaDePara) {
                divAlteraTerminal.style.display = '';
                document.forms[0].idTerminal.value = id;
                document.forms[0].idPessoaDePara.value = idPessoaDePara;
                document.forms[0].target = 'ifrmAlteraTerminal';
                document.forms[0].action = 'alterarTerminal.do';
                mostrar_div();
                document.forms[0].submit();
                ifrmAlteraTerminal.src = 'incluirAlterarTerminal.jsp';
            }
    
            function incluirTerminal() {
                divIncluiTerminal.style.display = '';
                document.forms[0].idTerminal.value = '';
                document.forms[0].target = 'ifrmIncluiTerminal';
                document.forms[0].action = 'incluirTerminal.do';
                mostrar_div();
                document.forms[0].submit();
                ifrmIncluiTerminal.src = 'incluirAlterarTerminal.jsp';
            }
    
            function listar() {
                document.forms[0].target = '';
                document.forms[0].action = "pesquisarTerminais.do";
                mostrar_div();
                document.forms[0].submit();
            }
    
            function pesquisar() {
                document.forms[0].nrPagina.value = 1;
                listar();
            }
    
            function paginaAnterior() {
                pag = document.forms[0].nrPagina.value;
                document.forms[0].nrPagina.value = parseInt(pag) - 1;
                listar();
            }
    
            function proximaPagina() {
                pag = document.forms[0].nrPagina.value;
                document.forms[0].nrPagina.value = parseInt(pag) + 1;
                listar();
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <!--acesso:controlHiddenItem nomeIdentificador="term_pesq"-->
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            //mostrar_div();
        </script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="468" width="790" label="Parametrização" operacoes="Manutenção de TAV" scroll="no">
            <table><tr><td height="4"></td></tr></table>
            <table width="100%" border="0" cellspacing="1" cellpadding="1">
            <html:form action="/VOLTAV/manterTerminal/pesquisarTerminais.do" onsubmit="return false;" method="post">
            <html:hidden property="idTerminal"/>
            <html:hidden property="idPessoaDePara"/>
            <html:hidden property="nmMunicipio"/>
            <html:hidden property="nmPessoa"/>
            <html:hidden property="nrPagina"/>
                <tr>
                    <td>
                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center">
                                <table width="98%" border="0" cellspacing="2" cellpadding="0" align="center" class="tbl_bgBlue">
                                    <tr><td height="6"></td></tr>

                                    <tr>
                                        <td class="tblEstatica_campoNome" align="left" style="width:30pt;">&nbsp;
                                            <netui:label value="UF:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left" style="width:180pt;">
                                            <bean:define id="comboUF" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm.comboUF"/>
                                            <html:select property="idUF" onchange="javascript:ufChange();">
                                                <html:option value="0">Todas</html:option>
                                                <html:options collection="comboUF" property="idUF" labelProperty="sgUF" />
                                            </html:select>
                                        </td>
                                        <td style="width:50pt;">&nbsp;
                                            <netui:label value="Município:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td style="width:180pt;">
                                            <bean:define id="comboMunicipio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm.comboMunicipio"/>
                                            <html:select property="municipioSelecionado" onchange="javascript:municipioChange();">
                                                <html:option value="0">Todos</html:option>
                                                <html:options collection="comboMunicipio" property="nmMunicipio" labelProperty="nmMunicipio" />
                                            </html:select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>&nbsp;
                                            <netui:label value="Loja:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td>
                                            <bean:define id="comboLoja" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm.comboLoja"/>
                                            <html:select property="lojaSelecionada" onchange="lojaChange();">
                                                <html:option value="0">Todas</html:option>
                                                <html:options collection="comboLoja" property="idPessoaDePara" labelProperty="nmLoja" />
                                            </html:select>
                                        </td>
                                        <td>&nbsp;
                                            <netui:label value="Terminal:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td>
                                            <bean:define id="comboTerminal" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm.comboTerminal"/>
                                            <html:select property="idTerminalSelecionado" styleId="idTipoGrupoSelecionado">
                                                <html:option value="0">Todos</html:option>
                                                <html:options collection="comboTerminal" property="idTerminal" labelProperty="nrTerminal" />
                                            </html:select>
                                        </td>
                                    </tr>

                                    <tr style="height:1pt;">
                                        <td colspan="4">&nbsp;</td>
                                    </tr>

                                    <tr>
                                        <td colspan="2">&nbsp;
                                            <netui:label value="Recarga:" styleClass="tblEstatica_campoNome"/>
                                            <html:checkbox property="filtroRecarga" value="1" styleClass="radio" />&nbsp;
											<netui:label value="pagamento:" styleClass="tblEstatica_campoNome"/>
                                            <html:checkbox property="filtroPagamento" value="1" styleClass="radio" />
                                        </td>
                                        <td align="right" colspan="2">
                                            <acesso:controlHiddenItem nomeIdentificador="term_pesq_limpar">
                                                <img style="cursor:hand;border:none" tabindex="3" id="btLimpar" onclick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                            </acesso:controlHiddenItem>
                                            <acesso:controlHiddenItem nomeIdentificador="term_pesq_listar">
                                                <img style="cursor:hand;border:none" tabindex="4" id="btPesquisar" onclick="pesquisar();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                            </acesso:controlHiddenItem>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </td>
                                    </tr>

                                    <tr><td height="6"></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td height="4"></td></tr>
                        <!-- Testa se alguma busca foi realizada -->
                        <!-- Caso haja uma lista de usuarios como retorno, o cpf será diferente de -1 -->
                        <logic:present name="listaTerminais">
                        <bean:size id="itensLista" name="listaTerminais" property="terminalVOArray" />
                        <logic:greaterThan value="0" name="itensLista">
                            <tr>
                                <td align="center">
                                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                        <tr>
                                            <td width="100%" align="center">
                                                <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="180" tableWidth="705" styleId="tableTitle" sortable="true">
                                                    <vivo:tbHeader>
                                                        <vivo:tbHeaderColumn align="left"   width="15" type="string">UF&nbsp;</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left"   width="80" type="string">Município</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left"   width="100" type="string">Loja</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left"   width="30" type="string">Terminal</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left"   width="20" type="string">Recarga</vivo:tbHeaderColumn>
														<vivo:tbHeaderColumn align="left"   width="20" type="string">Pagto.</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left"   width="30" type="string">Código SITEF</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="left"   width="30" type="string">Senha Lojista</vivo:tbHeaderColumn>

                                                        <vivo:tbHeaderColumn align="center" width="15" type="string">&nbsp;</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="center" width="15" type="string">&nbsp;</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="center" width="15" type="string">&nbsp;</vivo:tbHeaderColumn>
                                                    </vivo:tbHeader>
                                                    <vivo:tbRows>
                                                        <logic:iterate name="listaTerminais" property="terminalVOArray" id="itemTerminal">
                                                            <vivo:tbRow key="linha">
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="15"> <bean:write name="itemTerminal" property="sgUF"/></vivo:hint>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="80"> <bean:write name="itemTerminal" property="nmMunicipio"/></vivo:hint>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="100"> <bean:write name="itemTerminal" property="nmLoja"/></vivo:hint>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="30"> <bean:write name="itemTerminal" property="nrTerminal"/><br><bean:write name="itemTerminal" property="nrIpTerminal"/></vivo:hint>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="40">
                                                                        <logic:equal value="1" name="itemTerminal" property="inLiberadoRecarga">
                                                                            Sim
                                                                        </logic:equal>
                                                                        <logic:notEqual value="1" name="itemTerminal" property="inLiberadoRecarga">
                                                                            N&atilde;o
                                                                        </logic:notEqual>
                                                                    </vivo:hint>
                                                                </vivo:tbRowColumn>
																<vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="40">
                                                                        <logic:equal value="1" name="itemTerminal" property="inLiberadoPagamento">
                                                                            Sim
                                                                        </logic:equal>
                                                                        <logic:notEqual value="1" name="itemTerminal" property="inLiberadoPagamento">
                                                                            N&atilde;o
                                                                        </logic:notEqual>
                                                                    </vivo:hint>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="30"> <bean:write name="itemTerminal" property="cdLojaOperadoraCartao"/></vivo:hint>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="30"> <bean:write name="itemTerminal" property="cdSitefSenha"/></vivo:hint>
                                                                </vivo:tbRowColumn>

                                                                <vivo:tbRowColumn>
                                                                    <acesso:controlHiddenItem nomeIdentificador="term_pesq_alterar">
                                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" style="cursor: hand;" alt="Alterar Terminal" onclick="alterarTerminal('<bean:write name="itemTerminal" property="idTerminal" />', '<bean:write name="itemTerminal" property="idPessoaDePara" />');">
                                                                    </acesso:controlHiddenItem>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <acesso:controlHiddenItem nomeIdentificador="term_pesq_excluir">
                                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor: hand;" border="0" alt="Excluir Terminal" onclick="removerTerminal('<bean:write name="itemTerminal" property="idTerminal" />', '<bean:write name="itemTerminal" property="idPessoaDePara" />', '<bean:write name="itemTerminal" property="nmMunicipio"/>', '<bean:write name="itemTerminal" property="nmLoja"/>');">
                                                                    </acesso:controlHiddenItem>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <acesso:controlHiddenItem nomeIdentificador="term_pesq_reset">
                                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_reinicsenha.gif" style="cursor: hand;" alt="Reiniciar Senha" border="0" onclick="reiniciarSenha('<bean:write name="itemTerminal" property="idTerminal" />');">
                                                                    </acesso:controlHiddenItem>
                                                                </vivo:tbRowColumn>
                                                            </vivo:tbRow>
                                                        </logic:iterate>
                                                    </vivo:tbRows>
                                                </vivo:tbTable>

                                                <%-- Botoes de Paginacao --%>
                                                <table width="100%">
                                                    <tr align="right">
                                                        <td>
                                                            <logic:greaterThan value="1" name="cadastroTerminalForm" property="nrPagina">
                                                                <a href="javascript:paginaAnterior();">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_over.gif'" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif'" border="0" align="middle">
                                                                </a>
                                                            </logic:greaterThan>
                                                            <logic:equal value="1" name="listaTerminais" property="temProximo">
                                                                <a href="javascript:proximaPagina();">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_over.gif'" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif'" border="0" align="middle">
                                                                </a>
                                                            </logic:equal>
                                                        </td>
                                                    </tr>
                                                </table>

                                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                                                <table width="720" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                                                    <tr>
                                                        <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                                                        <td>
                                                            <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                                                        </td>
                                                        <td>
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Terminal
                                                        </td>
                                                        <td>
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Terminal
                                                        </td>
                                                        <td>
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_reinicsenha.gif" align="middle"> &nbsp;Reiniciar Senha</td>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </logic:greaterThan>
                        <logic:equal value="0" name="itensLista">
                            <tr>
                                <td align="center">
                                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                        <tr>
                                            <td width="90%" align="center" class="tblEstatica_campoNome">
                                                Pesquisa não encontrou resultado(s).
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </logic:equal>
                        </logic:present>
                        <tr>
                            <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="term_pesq_incluir">
                                 <img align="rigth" tabindex="5" id="btIncluir" onClick="incluirTerminal();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" hspace="5" vspace="5" />
                            </acesso:controlHiddenItem>
                            </td>

                        </tr>

                        <tr><td height="4"></td></tr>
                    </table>
                    </td>
                </tr>
            </html:form>
                <tr>
                    <td>
                        <img align="letf" id="voltar" tabindex="6" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5" onBlur="document.forms[0].idUF.focus();"/>
                    </td>
                </tr>
            </table>
            <vivo:quadroFlutuante label="Alterar Terminal" scroll="false" src="" idIframe="ifrmAlteraTerminal" id="divAlteraTerminal" spacesLeft="20" height="300" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="740"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante label="Incluir Terminal" scroll="false" src="" idIframe="ifrmIncluiTerminal" id="divIncluiTerminal" spacesLeft="20" height="300" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="740"></vivo:quadroFlutuante>
            <logic:present name="msg">
                <script language="javascript" for="window" event="onload">
                <!--
                    alert('<abean:write name="msg" />');
                    oculta_div();
                // -->
                </script>
            </logic:present>
            </vivo:sessao>
    <!--/acesso:controlHiddenItem-->
    <vivo:alert atributo="msgError" scope="request"/>
    </netui-template:section>
</netui-template:template>