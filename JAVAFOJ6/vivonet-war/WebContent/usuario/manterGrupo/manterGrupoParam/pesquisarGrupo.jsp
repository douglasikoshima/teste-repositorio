<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm"/>
<bean:define id="grupoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.grupoNovoVO"/>
<bean:define id="itemGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.itemGrupo"/>
<bean:define id="ComboTipoGrupo"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.tiposGrupo"/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Grupo"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">
    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_pg_verpagina">
    <script language="Javascript">

        function removeItem(id) {
            document.forms[0].target = '';
            document.forms[0].action = "removeGrupo.do?idGrupo=" + id;
            if (window.confirm("Confirma remoção do item?")) {
                mostrar_div();
                document.forms[0].submit();
            }
        }

        function alteraItem(id, descr){
            document.forms[0].dsGrupo.value = descr;
            document.forms[0].indicativoAlt.value = "sim";
            document.forms[0].idAlt.value = id;
            testaBotao();

        }

        function testaBotao() {

            if ((trim(document.forms[0].dsGrupo.value) == "") || (document.forms[0].idAlt.value == "")) {
               // document.all.dvBtIncluir.style.display='block';
               // document.all.dvBtAlterar.style.display='none';
            } else {
               // document.all.dvBtIncluir.style.display='none';
               // document.all.dvBtAlterar.style.display='block';
            }
        }

        function lista()
        {
            document.forms[0].target = '';
            document.forms[0].dsGrupo.value = trim(document.forms[0].dsGrupo.value);
            document.forms[0].action = "listaGrupos.do";
            mostrar_div();
            document.forms[0].submit();
        }

        // limpa campos.
        function limpar()
        {
            document.forms[0].target = '';
            document.forms[0].action = 'limpaForm.do';
            document.forms[0].submit();
        }

        function salvaItem(){
            document.forms[0].action = "salvaItemPesquisa.do";
            if(trim(document.forms[0].dsGrupo.value) != ""){
                mostrar_div();
                document.forms[0].submit();
            }else{
                alert("Não existe um item para ser incluído.");
                return;
            }
        }

        function salvaItemAlterado(){
            document.forms[0].action = "salvaItemAlterado.do";
            if(trim(document.forms[0].dsGrupo.value) != ""){
                mostrar_div();
                document.forms[0].submit();
            }else{
                alert("Não existe um item para ser incluído.");
                return;
            }
        }

        function alteraGrupo(id) {
            divAlteraGrupo.style.display = '';
            document.forms[0].idGrupo.value = id;
            document.forms[0].target = "ifrmAlteraGrupo";
            document.forms[0].action = 'salvaEditGrupo.do?operacao=alterar';
            mostrar_div();
            document.forms[0].submit();
            ifrmAlteraGrupo.src = 'IncluirAlterarGrupo.jsp';
        }

        function incluiGrupo() {
            divIncluiGrupo.style.display = '';
            document.forms[0].target = "ifrmIncluiGrupo";
            //document.forms[0].action = "IncluirAlterarGrupo.jsp?operacao=incluir";
            document.forms[0].action = 'salvaEditGrupo.do?operacao=incluir';
            mostrar_div();
            document.forms[0].submit();
            ifrmIncluiGrupo.src = 'IncluirAlterarGrupo.jsp';
        }

        function listaEnter(ev)
        {
            if(ev.keyCode == 13)
                lista();
            else
                return false;
        }


    </script>

        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="460" width="790" label="Usu&aacute;rios" operacoes="Manutenção de Grupos" scroll="no">
            <form action="listaGrupos.do" onsubmit="return false;" method="post">
            <html:hidden name="FormGrupo" property="indicativoAlt"/>
            <html:hidden name="FormGrupo" property="idGrupo"/>
            <html:hidden name="FormGrupo" property="idAlt"/>
            <table width="100%" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>
                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center">
                                <table width="98%" border="0" cellspacing="2" cellpadding="0" align="center" class="tbl_bgBlue">
                                    <tr><td height="6"></td></tr>
                                    <tr>
                                        <td class="tblEstatica_campoNome" align="left">
                                            <netui:label value="Nome do Grupo:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td align="left">
                                            &nbsp; <html:text name="grupoVO" property="dsGrupo" tabindex="1" style="width:400px" styleClass="input" maxlength="200" onkeypress="listaEnter(event);"/>
                                        </td>
                                        <td align="left">&nbsp;
                                        <acesso:controlHiddenItem nomeIdentificador="usu_pg_limpar">
                                            <img style="cursor:hand;border:none" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                        </acesso:controlHiddenItem>
                                        </td>
                                        <td align="left">
                                        <acesso:controlHiddenItem nomeIdentificador="usu_pg_listar">
                                            <img style="cursor:hand;border:none" id="btPesquisar" onclick="lista();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                        </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            &nbsp;<netui:label value="Tipo do Grupo:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td colspan="3"> &nbsp;
                                            <%-- html:select name="FormGrupo" property="idTipoGrupoPesquisa" styleId="idTipoGrupoSelecionado">
                                                <html:option value="">--Selecione--</html:option>
                                                <html:options collection="ComboTipoGrupo" property="idTipoGrupo" labelProperty="dsTipoGrupo"/>
                                            </html:select--%>
                                            <html:select name="FormGrupo" property="idTipoGrupoPesquisa" styleId="idTipoGrupoSelecionado">
                                                <html:option value="">--Selecione--</html:option>
                                                <logic:notEmpty property="tiposGrupo" name="FormGrupo">
                                                    <bean:define id="cc" name="FormGrupo" property="idTipoGrupoPesquisa" type="java.lang.String"/>
                                                    <logic:iterate property="tiposGrupo" name="FormGrupo" id="lista">
                                                        <logic:equal name="lista" property="idTipoGrupo" value="<%=cc%>">
                                                            <option value='<bean:write name="lista" property="idTipoGrupo" />' selected="selected"><bean:write name="lista" property="dsTipoGrupo" /></option>
                                                        </logic:equal>
                                                        <logic:notEqual name="lista" property="idTipoGrupo" value="<%=cc%>">
                                                            <option value='<bean:write name="lista" property="idTipoGrupo" />'><bean:write name="lista" property="dsTipoGrupo" /></option>
                                                        </logic:notEqual>
                                                    </logic:iterate>
                                                </logic:notEmpty>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr><td height="6"></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td height="4"></td></tr>
                        <!-- Testa se alguma busca foi realizada -->
                        <!-- Caso haja uma lista de usuarios como retorno, o cpf será diferente de -1 -->
                        <logic:greaterThan name="FormGrupo" property="arrayGrupoLength" value="0">
                            <tr>
                                <td align="center">
                                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                        <tr>
                                            <td width="100%" align="center">
                                                <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="245" tableWidth="705" styleId="tableTitle" sortable="true">
                                                    <vivo:tbHeader>
                                                        <vivo:tbHeaderColumn align="left"   width="" type="string">Grupos encontrados</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="center" width="100" type="string">Tipo</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>
                                                        <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>
                                                    </vivo:tbHeader>
                                                    <vivo:tbRows>
                                                        <logic:iterate name="FormGrupo" property="arrayGruposVO.grupoUsuarioVOArray" id="itemGrupo">
                                                            <vivo:tbRow key="linha">
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="80"> <bean:write name="itemGrupo" property="dsGrupo"/></vivo:hint>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <vivo:hint maxLength="80"> <bean:write name="itemGrupo" property="dsTipoGrupoSelecionado"/></vivo:hint>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <acesso:controlHiddenItem nomeIdentificador="usu_pg_editargrupo">
                                                                    <a href="javascript:alteraGrupo('<bean:write name="itemGrupo" property="idGrupo" />');">
                                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Grupo">
                                                                    </a>
                                                                    </acesso:controlHiddenItem>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <acesso:controlHiddenItem nomeIdentificador="usu_pg_excluirgrupo">
                                                                    <a href="#" onclick="removeItem('<bean:write name="itemGrupo" property="idGrupo" />'); return false">
                                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Grupo">
                                                                    </a>
                                                                    </acesso:controlHiddenItem>
                                                                </vivo:tbRowColumn>
                                                                <vivo:tbRowColumn>
                                                                    <acesso:controlHiddenItem nomeIdentificador="usu_pg_editarparams">
                                                                    <a onclick="mostrar_div()" href="editaRelGrupo.do?grupoId=<bean:write name="itemGrupo" property="idGrupo" />">
                                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" alt="Editar Parametros" border="0">
                                                                    </a>
                                                                    </acesso:controlHiddenItem>
                                                                </vivo:tbRowColumn>
                                                            </vivo:tbRow>
                                                        </logic:iterate>
                                                    </vivo:tbRows>
                                                </vivo:tbTable>
                                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                                                <table width="720" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                                                    <tr>
                                                        <td valign="middle" width="70">
                                                            <b>&nbsp;Legendas:</b>
                                                        </td>
                                                        <td>
                                                            <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                                                        </td>
                                                        <td>
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Editar Grupo
                                                        </td>
                                                        <td>
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Remover Grupo
                                                        </td>
                                                        <td>
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" align="middle"> &nbsp;Editar Parâmetros
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </logic:greaterThan>
                        <logic:equal name="FormGrupo" property="arrayGrupoLength" value="0">
                            <tr>
                                <td align="center">
                                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                        <tr>
                                            <td width="90%" align="center" class="tblEstatica_campoNome">
                                                Não foi encontrado nenhum grupo com a descrição fornecida.
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </logic:equal>
                        <tr>
                            <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="usu_pg_salvaitem">
                                 <img align="right" id="btIncluir" onClick="incluiGrupo();return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" hspace="5" vspace="5" />
                            </acesso:controlHiddenItem>
                            </td>

                        </tr>

                        <tr><td height="4"></td></tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <img align="left" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5"/>
                    </td>

                </tr>
            </table>
            </form>
            <vivo:quadroFlutuante label="Alterar Grupo" scroll="false" src="" idIframe="ifrmAlteraGrupo" id="divAlteraGrupo" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante label="Incluir Grupo" scroll="false" src="" idIframe="ifrmIncluiGrupo" id="divIncluiGrupo" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            <script language="javascript" for="window" event="onload">
            <!--
                if('<bean:write name="FormGrupo" property="msgError" />' != ""){
                    alert('<bean:write name="FormGrupo" property="msgError" />');
                }
                oculta_div();
                document.forms[0].dsGrupo.focus();
            -->
            </script>
            </vivo:sessao>
    </acesso:controlHiddenItem>
    <vivo:alert atributo="msgError" scope="request"/>
    </netui-template:section>
</netui-template:template>