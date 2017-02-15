<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormArvore"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvore"/>
<bean:define id="subSistemaUsuarioVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvore.subSistemaUsuarioVO"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
            <script language="javascript">
                function montaArvoreSubsistema(){
                    if(document.forms[0].idSubSistemaEscolhido.value=='0000'){
                        return;
                    }else{
                        document.forms[0].msgError.value = '';
                        document.forms[0].action='iniciaArvore.do?idSubSistemaEscolhido=' + document.forms[0].idSubSistemaEscolhido.value ;
                        parent.mostrar_div();
                        document.forms[0].submit();
                    }
                }
                
                function limpaCampos(){
                    document.forms[0].idItemMenu.value = "";            
                    document.forms[0].nmMenu.value = "";
                    document.forms[0].dsHint.value = "";
                    document.forms[0].nrNivel.value = "";
                    document.forms[0].idItemMenuPai.value = "";
                    document.forms[0].inVisibilidade.value = "";
                    document.forms[0].sqSequencia.value = "";
                    document.forms[0].idPagina.value = "";
                    document.forms[0].dsSistema.value = document.forms[0].dsSistema.value;
                    document.forms[0].nmPagina.value = "";
                    document.forms[0].nmURL.value = "";
                    document.forms[0].idSubSistema.value="";
                }
                
                
                function capturaParametrosComPagina (idItemMenu, nrNivel, idItemMenuPai, inVisibilidade, sqSequencia, idPagina, idSubSistema, inFolha){
                    document.forms[0].idItemMenu.value = idItemMenu;            
                    document.forms[0].nrNivel.value = nrNivel;
                    document.forms[0].idItemMenuPai.value = idItemMenuPai;
                    document.forms[0].inVisibilidade.value = inVisibilidade;
                    document.forms[0].sqSequencia.value = sqSequencia;
                    document.forms[0].idPagina.value = idPagina;
                    document.forms[0].inFolha.value= inFolha;
                    document.forms[0].idSubSistema.value=idSubSistema;
                }
                
                function capturaParametrosSemPagina (idItemMenu, nrNivel, idItemMenuPai, inVisibilidade, sqSequencia, inFolha){
                    document.forms[0].idItemMenu.value = idItemMenu;            
                    document.forms[0].nrNivel.value = nrNivel;
                    document.forms[0].idItemMenuPai.value = idItemMenuPai;
                    document.forms[0].inVisibilidade.value = inVisibilidade;
                    document.forms[0].sqSequencia.value = sqSequencia;
                    document.forms[0].inFolha.value = inFolha;
                }
                
                function capturaRaiz (idItemMenu, nrNivel, idItemMenuPai, inVisibilidade, sqSequencia, inFolha){
                    document.forms[0].idItemMenu.value = idItemMenu;            
                    document.forms[0].nrNivel.value = nrNivel;
                    document.forms[0].idItemMenuPai.value = idItemMenuPai;
                    document.forms[0].inVisibilidade.value = inVisibilidade;
                    document.forms[0].sqSequencia.value = sqSequencia;
                    document.forms[0].inFolha.value = inFolha;
                }
                
                function inserirItem(){
                    if(document.forms[0].idItemMenu.value !="" ){
                        var tela = document.forms[0];
                        parent.abreJanelaInserir(tela);
                        parent.mostrar_div();
                        limpaCampos();
                    }else{
                        alert("Selecione um item pai para adicionar o item.");
                    }                    
                }

                function removerItem(){
                    if(document.forms[0].idItemMenu.value == ""){
                        alert("Selecione um Ítem para ser removido.");
                        return;
                    }                    
                    if (window.confirm("Confirma remoção do item?")){
                        document.forms[0].action = "removeItem.do?sistemaId=" + document.forms[0].idSistema.value+"&idItemMenu="+document.forms[0].idItemMenu.value;
                        parent.mostrar_div();
                        document.forms[0].submit();
                        limpaCampos(); 
                    }
                }
                
                function editarItem(){
                    if(document.forms[0].idItemMenu.value != ""){
                        var tela = document.forms[0];
                        parent.abreJanelaEditar(tela);
                        parent.mostrar_div();
                        limpaCampos();
                    }else{
                        alert("Selecione um item para ser editado.");
                    }   
                }

                function enviaSequenciaUp(){
                    if(document.forms[0].idItemMenu.value != ""){
                        var tela = document.forms[0];
                        parent.mostrar_div();
                        document.forms[0].inMoveUp.value = '1';
                        document.forms[0].action = 'decrementaIncrementaSequencia.do?idItemMenu='+document.forms[0].idItemMenu.value+'&inMoveUp=1';
                        document.forms[0].submit();
                        limpaCampos();
                    }else{
                        alert("Selecione um item para ser editado.");
                    }   
                }     
                
                function enviaSequenciaDown(){
                    if(document.forms[0].idItemMenu.value != ""){
                        var tela = document.forms[0];
                        parent.mostrar_div();
                        document.forms[0].inMoveUp.value = '0';
                        document.forms[0].action = 'decrementaIncrementaSequencia.do?idItemMenu='+document.forms[0].idItemMenu.value+'&inMoveUp=0';
                        document.forms[0].submit();
                        limpaCampos();
                    }else{
                        alert("Selecione um item para ser editado.");
                    }   
                }           
                
                function carregaComboSubSistema(){
                    var op = '<bean:write name="FormArvore" property="idSubSistemaEscolhido"/>';
                    if(op != '' && op != 'null')
                        document.forms[0].idSubSistemaEscolhido.value = '<bean:write name="FormArvore" property="idSubSistemaEscolhido"/>';
                }
            </SCRIPT>
            
            <script language="javascript" for="window" event="onload">
                if('<bean:write name="FormArvore" property="msgError" />' != ""){
                    alert('<bean:write name="FormArvore" property="msgError" />');
                }
                limpaCampos();
                carregaComboSubSistema();
                parent.oculta_div();
            </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_mm_verpagina">
        <html:form action="/usuario/manterSistema/editarParSistema/removeItem.do" method="post">
            <html:hidden name="FormArvore" property="idItemMenu"/>
            <html:hidden name="FormArvore" property="nmMenu"/>
            <html:hidden name="FormArvore" property="dsHint"/>
            <html:hidden name="FormArvore" property="nrNivel"/>
            <html:hidden name="FormArvore" property="idItemMenuPai"/>
            <html:hidden name="FormArvore" property="inVisibilidade"/>
            <html:hidden name="FormArvore" property="sqSequencia"/>
            <html:hidden name="FormArvore" property="idPagina"/>
            <html:hidden name="FormArvore" property="idSistema"/>
            <html:hidden name="FormArvore" property="idSubSistema"/>
            <html:hidden name="FormArvore" property="dsSistema"/>
            <html:hidden name="FormArvore" property="nmPagina"/>
            <html:hidden name="FormArvore" property="nmURL"/>
            <html:hidden name="FormArvore" property="inFolha"/>
            <html:hidden name="FormArvore" property="inMoveUp"/>
            <html:hidden name="FormArvore" property="msgError"/>
                <table width="100%" cellpadding="0" cellspacing="1" border="0">
                    <tr>
                        <td>
                        <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                            <tr>
                                <td height="4" colspan="2" valign="middle">
                                    <netui:label value="Selecione um Subsistema: " styleClass="tblEstatica_campoNome"/>
                                    &nbsp;&nbsp;
                                    <html:select name="FormArvore" property="idSubSistemaEscolhido"  value="idSubSistemaEscolhido" style="width:250" onchange="Javascript:montaArvoreSubsistema();" styleClass="SELECT" size="1" >
                                        <html:options collection="subSistemaUsuarioVO" property="idSubSistema" labelProperty="dsSubSistema" /> 
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td width="75%" align="left">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="tbl_bgGray">
                                        <tr>
                                            <td>
                                                <div style="top: 0px; left: 0px; height: 310px; padding: 5px; overflow: auto;background-color:#ffffff;">
                                                     <script>
                                                        <%=(String)request.getAttribute("arvore")%>
                                                        <%request.setAttribute("arvore",(String)request.getAttribute("arvore"));%>
                                                    </script>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="25%" align="center">
                                    <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
                                        <tr>
                                            <td align="center">
                                            <acesso:controlHiddenItem nomeIdentificador="usu_mm_inserir">
                                                <img onclick="inserirItem();" id="btincluir" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;" />
                                            </acesso:controlHiddenItem>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                            <acesso:controlHiddenItem nomeIdentificador="usu_mm_editar">
                                                <img onclick="editarItem();" id="btalterar" src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_alterar_over.gif'" style="border:none;cursor:hand"/>
                                            </acesso:controlHiddenItem>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                            <acesso:controlHiddenItem nomeIdentificador="usu_mm_remover">
                                                <img onclick="removerItem();" id="btremover" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif'" style="border:none;cursor:hand" />
                                            </acesso:controlHiddenItem>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                                <img onclick="enviaSequenciaUp();" id="btup" src="/FrontOfficeWeb/resources/images/bt_up_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_up_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_up_over.gif'" style="border:none;cursor:hand"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                                <img onclick="enviaSequenciaDown();" id="btdown" src="/FrontOfficeWeb/resources/images/bt_down_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_down_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_down_over.gif'" style="border:none;cursor:hand"/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>                            
                        </table>
                        </td>
                    </tr>
                    <tr><td height="4" colspan="2"></td></tr>
                </table>
        <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:60; left:260; width:450; height:530; border-style:solid; border-width:1px; border-color:#adadad; background-color:#ededed;" class="tbl_bgBlue">
            <table width="450" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                <tr>
                    <td id="titleBar" style="cursor:move" width="450" height="21">Cadastrar Ítem da árvore de baixa</td>
                    <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                        <div align="right">
                            <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();">
                        </div>
                    </td>
                </tr>
            </table>
            <table width="440" cellpadding="0" cellspacing="5">
                <tr>
                    <td>
                        <iframe id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="300" height="300"></iframe>
                    </td>
                </tr>
            </table>
        </div>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
        </html:form>
    </acesso:controlHiddenItem>   
    </netui-template:section>
</netui-template:template>
