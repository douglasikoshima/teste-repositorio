<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>


<bean:define id="FormManterMenu" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterMenu"/>

<bean:define id="aItemMenu"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterMenu.menuComboVO.itemMenuArray"/>
<bean:define id="aUf"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterMenu.menuComboVO.ufArray"/>
<bean:define id="aTipoLinha"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterMenu.menuComboVO.tipoLinhaArray"/>

    <head>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="JavaScript">
        function transfereSelecaoLista(listaOrigem, listaDestino){
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
                    
            if(listaOrigem.name == 'idCanalAssocArray' 
                || listaDestino.name == 'idCanalAssocArray'
                || listaOrigem.name == 'idTipoLinhaAssocArray'
                || listaDestino.name == 'idTipoLinhaAssocArray'){
                
                var form = document.forms[0];
                
                selecionaCombo();
                form.action = 'filtraItemMenu.do';
                form.target = 'innerBrowserPesquisaMenu';
                parent.mostrar_div();
                form.submit();
                
            }

        }
        
        function valida(){
        
            return true;
        }

        function selecionaCombo()
        {
            for(i = 0; i < document.forms[0].idCanalAssocArray.length; i++){
               document.forms[0].idCanalAssocArray.options[i].selected =true; 
            }

            for(i = 0; i < document.forms[0].idTipoLinhaAssocArray.length; i++){
               document.forms[0].idTipoLinhaAssocArray.options[i].selected =true; 
            }

            for(i = 0; i < document.forms[0].idGrupoAssocArray.length; i++){
               document.forms[0].idGrupoAssocArray.options[i].selected =true; 
            }
            
            for(i = 0; i < document.forms[0].idUFAssocArray.length; i++){
               document.forms[0].idUFAssocArray.options[i].selected =true; 
            }

            for(i = 0; i < document.forms[0].idItemMenuAssocArray.length; i++){
               document.forms[0].idItemMenuAssocArray.options[i].selected =true; 
            }
        
        }
        
        function pesquisar(){
            if(valida()){
                selecionaCombo();
                var form = document.forms[0];
                form.action = 'filtrarPesquisa.do';
                form.target = 'innerBrowserPesquisaMenu';
                parent.abaSelected(parent.btAba, parent.bt02);
                parent.mostrar_div();
                form.submit();
            }
        }
        
        function paginarPesquisa(op, botao){
            var form = document.forms[0];
            if(botao == true){
                var pagAtual = form.paginaAtual.value;
                pagAtual = parseInt(pagAtual)+parseInt(op);
                
                form.paginaAtual.value = pagAtual;
                
            }else{
                form.paginaAtual.value = op;
            }
            
            
            form.action = 'paginarPesquisa.do';
            form.target = 'innerBrowserPesquisaMenu';
            parent.mostrar_div();
            form.submit();

        }
        
        function alteraStatusItemMenu(idUf, idCanal, idTipoLinha, idGrupo, idItemMenu, inAtivo, paginaAtual, idx)
        {
            var strMsg = '';
            if(inAtivo == '1'){
                strMsg = "Deseja desativar esta funcionalidade para este Grupo, UF e Tipo de Linha?";
            }else{
                strMsg = "Deseja ativar esta funcionalidade para este Grupo, UF e Tipo de Linha?";
            }
            
            if(confirm(strMsg)){
            
                var form = document.forms[0];
    
                form.idUf.value         = idUf;
                form.idCanal.value      = idCanal;
                form.idTipoLinha.value  = idTipoLinha;
                form.idGrupo.value      = idGrupo;
                form.idItemMenu.value   = idItemMenu;
                form.inAtivo.value      = inAtivo;
                form.paginaAtual.value  = paginaAtual;
                form.idx.value          = idx;
                
                form.target = 'innerBrowserPesquisaMenu';
                form.action = 'alteraStatusItemMenu.do';
                parent.mostrar_div();
                
                form.submit();
            }
        
        }
        
    </script>
    
    </head>

    <body onload="if( parent.dvAnimarAguarde != null )parent.stopAnimation();" class="tbl_bgBlue">
    <!--acesso:controlHiddenItem nomeIdentificador="usu_skill_verpagina" -->

        <form name="FormManterMenu" onsubmit="return false;" method="post">
        <div id="div_lista_usuario_pag"/>
        
        <div id="div_filtros_menu">
        <table width="100%" height="100%" border="0" class="tbl_bgGray">
            <tr>
                <td>   
                      <vivo:quadro width="370" height="110" id="qdrCanal" scroll="no" label="Canal">
                        <table align="left" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="FormManterMenu" property="idCanalExistArray" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idCanalExistArray, document.forms[0].idCanalAssocArray);">
                                        <html:option value="15">VOL</html:option>
                                        <html:option value="13">TAV</html:option>
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="bottom">
                                    <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idCanalExistArray, document.forms[0].idCanalAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0" /><br><br>
                                    <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idCanalAssocArray, document.forms[0].idCanalExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0" />
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="FormManterMenu" property="idCanalAssocArray" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idCanalAssocArray, document.forms[0].idCanalExistArray);">
                                    </html:select>
                                </td>
                                <tr>
                            </table>                    
                       </vivo:quadro>   
                    </td>    
                    <td>
                      <vivo:quadro width="370" height="110" id="qdrTipoLinha" scroll="no" label="Tipo Linha">
                        <table align="left" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="FormManterMenu" property="idTipoLinhaExistArray" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idTipoLinhaExistArray, document.forms[0].idTipoLinhaAssocArray);">
                                        <html:options collection="aTipoLinha" property="idTipoLinha" labelProperty="dsTipoLinha" /> 
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="bottom">
                                    <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoLinhaExistArray, document.forms[0].idTipoLinhaAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0" /><br><br>
                                    <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoLinhaAssocArray, document.forms[0].idTipoLinhaExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0" />
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="FormManterMenu" property="idTipoLinhaAssocArray" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idTipoLinhaAssocArray, document.forms[0].idTipoLinhaExistArray);">
                                    </html:select>
                                </td>
                                <tr>
                            </table>                    
                       </vivo:quadro>   
                    </td>
                </tr>
                <tr>
                    <td>
                      <vivo:quadro width="370" height="110" id="qdrGrupo" scroll="no" label="Grupo">
                        <table align="left" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="FormManterMenu" property="idGrupoExistArray" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idGrupoExistArray, document.forms[0].idGrupoAssocArray);">
                                        <html:option value="1">PF - Cliente e Usuário</html:option>
                                        <html:option value="3">PJ - Apenas Usuário</html:option>
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="bottom">
                                    <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idGrupoExistArray, document.forms[0].idGrupoAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"/><br><br>
                                    <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idGrupoAssocArray, document.forms[0].idGrupoExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0"/>
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="FormManterMenu" property="idGrupoAssocArray" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idGrupoAssocArray, document.forms[0].idGrupoExistArray);">
                                    </html:select>
                                </td>
                                <tr>
                            </table>                    
                       </vivo:quadro>   
                    </td>
                    <td>
                      <vivo:quadro width="370" height="110" id="qdrUF" scroll="no" label="UF">
                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="FormManterMenu" property="idUFExistArray" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idUFExistArray, document.forms[0].idUFAssocArray);">
                                        <html:options collection="aUf" property="idUf" labelProperty="sgUf" /> 
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="bottom">
                                    <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idUFExistArray, document.forms[0].idUFAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"/><br><br>
                                    <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idUFAssocArray, document.forms[0].idUFExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0"/>
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="FormManterMenu" property="idUFAssocArray" multiple="true" style="width:150px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idUFAssocArray, document.forms[0].idUFExistArray);">
                                    </html:select>
                                </td>
                                <tr>
                            </table>                    
                       </vivo:quadro>   
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="div_item_menu">
                              <vivo:quadro width="745" height="110" id="qdrItemMenu" scroll="no" label="Item Menu">
                                <table align="center" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="310" align="center" valign="top">
                                            Existente<br>
                                            <html:select name="FormManterMenu" property="idItemMenuExistArray" multiple="true" style="width:330px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idItemMenuExistArray, document.forms[0].idItemMenuAssocArray);">
                                                <html:options collection="aItemMenu" property="idItemMenu" labelProperty="nmItemMenu" /> 
                                            </html:select>
                                        </td>
                                        <td width="70" align="center" valign="bottom">
                                            <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idItemMenuExistArray, document.forms[0].idItemMenuAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"/><br><br>
                                            <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idItemMenuAssocArray, document.forms[0].idItemMenuExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0"/>
                                        </td>
                                        <td width="310" align="center" valign="top">
                                            Associada<br>
                                            <html:select name="FormManterMenu" property="idItemMenuAssocArray" multiple="true" style="width:330px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idItemMenuAssocArray, document.forms[0].idItemMenuExistArray);">
                                            </html:select>
                                        </td>
                                        <tr>
                                    </table>                    
                               </vivo:quadro>   
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <acesso:controlHiddenItem nomeIdentificador="pesquisar_voltav_manter_menu">
                            <img style="cursor:hand;border:none" id="btPesquisar" onclick="pesquisar();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />&nbsp;
                        </acesso:controlHiddenItem>
                    </td>
                </tr>

            </table>
                <html:hidden name="FormManterMenu" property="paginaAtual"/>
                
                <html:hidden name="FormManterMenu" property="idUf"/>
                <html:hidden name="FormManterMenu" property="idCanal"/>
                <html:hidden name="FormManterMenu" property="idTipoLinha"/>
                <html:hidden name="FormManterMenu" property="idGrupo"/>
                <html:hidden name="FormManterMenu" property="inAtivo"/>
                <html:hidden name="FormManterMenu" property="idItemMenu"/>
                
                <iframe scrolling="yes"  name="innerBrowserPesquisaMenu" height="0px" width="0px"></iframe>              
        
            </div>

        </form>

    <vivo:alert atributo="msgStatus" scope="request"/>
</body>

