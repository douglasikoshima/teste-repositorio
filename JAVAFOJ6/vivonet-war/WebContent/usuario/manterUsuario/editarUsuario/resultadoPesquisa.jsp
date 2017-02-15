<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormUser"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm"/>
<!-- array de usuarios - contém o resultado da pesquisa -->
<bean:define id="aUsuarios" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.arrayUsuariosVO.usuarioVOArray"/>
<!-- utilizado para percorrer cada item do array de usuarios -->
<bean:define id="usuarioVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.usuarioVO"/>



<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
    <script language="javascript">

        function mostraTabela() {
            document.divtable.visibility='visible';
        }

        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }

        function abreJanela(idUsuario){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/mostraDetalhe.do?userId='+idUsuario;
            mostrar_div();

        }

        function fechaJanela(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'about:blank';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }

        function paginacao(op){
            var form = document.forms[0];
            var pagAtual = '<bean:write name="FormUser" property="pagina"/>';

            if(pagAtual == '0'){
                pagAtual++;
                op == '';
            }

            if(op == 'proximo'){
                pagAtual++;

            }else if(op == 'anterior'){
                pagAtual--;
            }

            form.pagina.value = pagAtual;
            form.paginaAtual.value = pagAtual;
            form.action = '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/pesquisaUsuarios.do';
            mostrar_div();
            form.submit();
        }

        function BloqueiaUsuario(uid){
            if (window.confirm('Confirma bloqueio do login ' + uid + '?')) {
                document.forms[0].login.value = uid;
                document.forms[0].action = '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/bloqueiaUsuario.do';
                mostrar_div();
                document.forms[0].submit();
            }
        }

        function DesbloqueiaUsuario(uid){
            if (window.confirm('Confirma desbloqueio do login ' + uid + '?')) {
                document.forms[0].login.value = uid;
                document.forms[0].action = '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/desbloqueiaUsuario.do';
                mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function ReinicializaSenha(uid){
            if (window.confirm('Confirma reinicializacão de senha do login ' + uid + '?')) {
                document.forms[0].login.value = uid;
                document.forms[0].action = '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/reinicializaSenha.do';
                mostrar_div();
                document.forms[0].submit();
            }

        }

        function enviaVoltar(){
            document.forms[0].action= '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/begin.do?tipo=manterFiltro';
            mostrar_div();
            document.forms[0].submit();
        }

        function enviaCriarNovo(){
            document.forms[0].action= '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/begin.do?tipo=cadastro';
            mostrar_div();
            document.forms[0].submit();
        }

        function focarCampo(){
            if(document.getElementById('btAnterior')){
                document.getElementById('btAnterior').focus();

            }else if(document.getElementById('btProximo')){
                document.getElementById('btProximo').focus();
            }
        }

    </script>
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rpus_verpagina">

        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

            <vivo:quadro id="qdMain" height="478" width="790" label="Manutenção de Usuário > Pesquisar Usuário > Resultado da Pesquisa" scroll="no">

            <html:form action="/usuario/manterUsuario/editarUsuario/pesquisaUsuarios.do" method="post">
                <html:hidden name="FormUser" property="dsDocAtual"/>
                <html:hidden name="FormUser" property="nome"/>
                <html:hidden name="FormUser" property="sobrenome"/>
                <html:hidden name="FormUser" property="login"/>
                <html:hidden name="FormUser" property="loginCti"/>
                <html:hidden name="FormUser" property="loginChefe"/>
                <html:hidden name="FormUser" property="idCargoAtual"/>
                <html:hidden name="FormUser" property="idUFOperadora"/>
                <html:hidden name="FormUser" property="idStatusAtual"/>
                <html:hidden name="FormUser" property="registrosPPagina"/>
                <html:hidden name="FormUser" property="paginaAtual" />
                <html:hidden name="FormUser" property="idUFAtual" />
                <html:hidden name="FormUser" property="pagina" />
                <html:hidden name="FormUser" property="tipoDocAtual" />
                <html:hidden name="FormUser" property="nivelOrganogramaAtual" />
                <html:hidden name="FormUser" property="cargoAtual" />
                <html:hidden name="FormUser" property="organizacaoAtual" />
                <html:hidden name="FormUser" property="unidadeOrganizacaoAtual" />

                <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                    <tr><td height="4"></td></tr>
	                <logic:notEqual name="FormUser" property="arrayUsuariosLength" value="-99">
                    <!-- tr>
                        <td width="100%" class="tblEstatica_campoNome" colspan="5" align="center" height="20">&nbsp;
                            Lista de usuários encontrados
                        </td>
                    </tr -->
                    <tr>
                        <td align="center">
                            <vivo:tbTable selectable="true" layoutWidth="735" layoutHeight="290" tableWidth="735" styleId="tableTitle" sortable="true">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn align="left"   width="17%" type="string">Documento</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="left"   width="14%" type="string">Nome</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="left"   width="8%" type="string">Login</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="left"   width="15%" type="string">Telefone</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="left"   width="15%" type="string">Superior</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="4%" type="string"></vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows>
                                    <logic:iterate name="FormUser" property="arrayUsuariosVO.usuarioVOArray" id="usuarioVO" type="br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO">
                                        <vivo:tbRow key="linha">
                                            <vivo:tbRowColumn>
                                                <logic:iterate name="usuarioVO" property="documentoSimpVOArray" id="documento" indexId="index">
                                                    <logic:notEqual name="documento" property="tipoDocumentoVO.sgTipoDocumento" value="CPF">
                                                        <bean:write name="documento" property="tipoDocumentoVO.sgTipoDocumento"/>-<bean:write name="documento" property="dsDocumento"/><br>
                                                    </logic:notEqual>
                                                    <logic:equal name="documento" property="tipoDocumentoVO.sgTipoDocumento" value="CPF">
                                                        <bean:write name="documento" property="tipoDocumentoVO.sgTipoDocumento"/>-<netui:label  value="{pageContext.documento.dsDocumento}"><netui:formatString pattern="###.###.###-##" /></netui:label><br>
                                                    </logic:equal>
                                                </logic:iterate>
                                            </vivo:tbRowColumn>

                                            <vivo:tbRowColumn>
                                                <bean:write name="usuarioVO" property="nome"/>
                                            </vivo:tbRowColumn>
                                            <!--vivo:tbRowColumn>
                                                < bean:write name="usuarioVO" property="sobrenome"/>
                                            < /vivo:tbRowColumn -->
                                            
                                            <vivo:tbRowColumn>
                                                <bean:write name="usuarioVO" property="login"/>
                                            </vivo:tbRowColumn>
                                            
                                            <vivo:tbRowColumn>
                                                <logic:notEqual name="usuarioVO" property="dsDDD" value="">
                                                    <logic:notEqual name="usuarioVO" property="dsDDD" value="0">
                                                        (<bean:write name="usuarioVO" property="dsDDD"/>) <bean:write name="usuarioVO" property="dsTelefone"/>
                                                    </logic:notEqual>
                                                </logic:notEqual>
                                            </vivo:tbRowColumn>
                                            
                                            <vivo:tbRowColumn>
                                                <bean:write name="usuarioVO" property="nomeChefe"/> (<bean:write name="usuarioVO" property="loginChefe"/>)
                                            </vivo:tbRowColumn>
                                            
                                            <vivo:tbRowColumn>

                                            <logic:notEqual name="usuarioVO" property="login" value="">
                                                <acesso:controlHiddenItem nomeIdentificador="usu_rpus_edvivo">
                                                <html:link onclick="mostrar_div();" page="/usuario/manterUsuario/editarUsuario/editaUsuario.do?tipo=edicao"  paramId="userId" paramName="usuarioVO" paramProperty="idUsuario">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_edit_usuario.gif" border="0">
                                                </html:link>
                                                </acesso:controlHiddenItem>
                                                 </logic:notEqual>
                                                <logic:equal name="usuarioVO" property="login" value="">
                                                <acesso:controlHiddenItem nomeIdentificador="usu_rpus_inclvivo">
                                                <html:link onclick="mostrar_div();" page="/usuario/manterUsuario/editarUsuario/editaUsuario.do?tipo=inclusao&pessoa=true"  paramId="userId" paramName="usuarioVO" paramProperty="idUsuario">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_edit_nusuario.gif" border="0">
                                                </html:link>
                                                </acesso:controlHiddenItem>
                                            </logic:equal>

                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <logic:notEqual name="usuarioVO" property="login" value="">
                                                <acesso:controlHiddenItem nomeIdentificador="usu_rpus_edparam">
                                                    <html:link onclick="mostrar_div();" page="/usuario/manterUsuario/editarUsuario/editaRelUsuario.do"  paramId="userId" paramName="usuarioVO" paramProperty="idUsuario">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" border="0">
                                                    </html:link>
                                                </acesso:controlHiddenItem>
                                                </logic:notEqual>
                                                <logic:equal name="usuarioVO" property="login" value="">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_propried_inverso.gif" style="cursor:default"  border="0">
                                                </logic:equal>
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                            <acesso:controlHiddenItem nomeIdentificador="usu_rpus_info">
                                                <a href="javascript:abreJanela('<bean:write name="usuarioVO" property="idUsuario"/>');">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" border="0">
                                                </a>
                                            </acesso:controlHiddenItem>
                                            </vivo:tbRowColumn>

                                            <vivo:tbRowColumn>
                                                <logic:notEqual name="usuarioVO" property="login" value="">
                                                    <acesso:controlHiddenItem nomeIdentificador="usu_rpus_bloqus">
                                                        <a href="javascript:BloqueiaUsuario('<bean:write name="usuarioVO" property="login"/>');">
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_bloquear.gif" alt="Bloquear Login" style="cursor:hand;border:none;">
                                                        </a>
                                                    </acesso:controlHiddenItem>
                                                </logic:notEqual>
                                                <logic:equal name="usuarioVO" property="login" value="">
                                                    <acesso:controlHiddenItem nomeIdentificador="usu_rpus_bloqus">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_bloquear_inv.gif" alt="Bloquear Login" style="cursor:default;border:none;">
                                                    </acesso:controlHiddenItem>
                                                </logic:equal>

                                            </vivo:tbRowColumn>

                                            <vivo:tbRowColumn>
                                                <logic:notEqual name="usuarioVO" property="login" value="">
                                                    <acesso:controlHiddenItem nomeIdentificador="usu_rpus_desblousu">
                                                        <a href="javascript:DesbloqueiaUsuario('<bean:write name="usuarioVO" property="login"/>');">
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_desbloquear.gif" alt="Desbloquear Login" style="cursor:hand;border:none;">
                                                        </a>
                                                    </acesso:controlHiddenItem>
                                                </logic:notEqual>
                                                <logic:equal name="usuarioVO" property="login" value="">
                                                    <acesso:controlHiddenItem nomeIdentificador="usu_rpus_desblousu">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_desbloquear_inv.gif" alt="Desbloquear Login" style="cursor:default;border:none;">
                                                    </acesso:controlHiddenItem>
                                                </logic:equal>

                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <logic:notEqual name="usuarioVO" property="login" value="">
                                                    <acesso:controlHiddenItem nomeIdentificador="usu_rpus_reinicia">
                                                       <a href="javascript:ReinicializaSenha('<bean:write name="usuarioVO" property="login"/>');">
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_reinicsenha.gif" alt="Reinicializar Senha" style="cursor:hand;border:none;">
                                                        </a>
                                                    </acesso:controlHiddenItem>
                                                </logic:notEqual>
                                                <logic:equal name="usuarioVO" property="login" value="">
                                                    <acesso:controlHiddenItem nomeIdentificador="usu_rpus_reinicia">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_reinicsenha_inv.gif" alt="Reinicializar Senha" style="cursor:default;border:none;">
                                                    </acesso:controlHiddenItem>
                                                </logic:equal>
                                            </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                            </vivo:tbTable>
                          </logic:notEqual>
                        <logic:equal name="FormUser" property="arrayUsuariosLength" value="-99"><br>
                            <span class="tblEstatica_campoNome">
                                <center>A pesquisa não retornou nenhum resultado.</center>
                            </span>
                        </logic:equal>
                        </td>
                    </tr>
                    <tr><td height="4"></td></tr>
                        <tr>
                            <td align="right">
                                <logic:notEqual name="FormUser" property="arrayUsuariosLength" value="-99">
	                                <center class="tblEstatica_campoNome">
	                                <logic:notEqual name="FormUser" property="pagina" value="0">
	                                    Página:&nbsp;<bean:write name="FormUser" property="pagina"/>
	                                </logic:notEqual>
	                                </center>
	                                <logic:notEqual name="FormUser" property="pagina" value="1">
	                                    <img onclick="paginacao('anterior');" id="btAnterior" src="/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_over.gif'" style="border:none;cursor:hand;"/>
	                                </logic:notEqual>
	                                <logic:notEqual name="FormUser" property="paginaAtual" value="0">
                                        <img onclick="paginacao('proximo');" id="btProximo" src="/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_over.gif'" style="border:none;cursor:hand;"/>
	                                </logic:notEqual>
                                </logic:notEqual>
                            </td>
                        </tr>
                        <tr><td height="15"></td></tr>

                    <tr>
                        <td align="center">
                            <table width="745" height="30" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                                <tr>
                                    <logic:notEqual name="FormUser" property="arrayUsuariosLength" value="-99">
                                        <td valign="top"><b>Legenda:</b><br>
                                            <div style="height:3px;"><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"></div>
                                            <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_edit_usuario.gif" align="middle"> &nbsp; Editar Usuário&nbsp;
                                            <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_edit_nusuario.gif" align="middle"> &nbsp; Incluir usuário&nbsp;
                                            <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" align="middle"> &nbsp; Editar Parâmetros&nbsp;
                                            <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" align="middle"> &nbsp; Detalhes
                                            <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_bloquear.gif" align="middle">&nbsp;&nbsp;Bloquear Login
                                            <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_desbloquear.gif" align="middle">&nbsp;&nbsp;Desbloquear Login
                                            <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_reinicsenha.gif" align="middle">&nbsp;&nbsp;Reinicializar Senha
                                        </td>
                                    </logic:notEqual>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr><td height="4"></td></tr>
                    <tr>
                        <td align="center">
                            <table cellpadding="0" cellspacing="0" border="0" width="95%">
                                <tr>
                                    <td align="left">
                                        <img onclick="enviaVoltar();" style="border:none;cursor:hand;" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"/>
                                    </td>
                                    <td align="right" width="15%">
                                    <acesso:controlHiddenItem nomeIdentificador="usu_rpus_novo">
                                        <img onClick="enviaCriarNovo();" id="btCadastrar" src="/FrontOfficeWeb/resources/images/bt_criarnovo_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_criarnovo_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_criarnovo_over.gif'" style="border:none;cursor:hand;" onblur="focarCampo();"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </html:form>
            </vivo:quadro>
            <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:36; left:36; width:710; height:236; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
                <table width="710" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                    <tr>
                        <td id="titleBar" style="cursor:move" width="540" height="21">Pesquisa de Usuários </td>
                        <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                            <div align="right">
                                <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();" style="cursor:hand;" alt="Fechar">
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="710" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <iframe id="frameDetalhe" src="about:blank" frameborder="0" width="710" height="320"></iframe>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>

        <script language="javascript" for="window" event="onload">
            <!--
            oculta_div();
            if(('<%=(String)request.getAttribute("msgError")%>' != "") && ('<%=(String)request.getAttribute("msgError")%>' != 'null')){
                alert('<%=(String)request.getAttribute("msgError")%>');
            }
            focarCampo();
            -->
        </script>

    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
