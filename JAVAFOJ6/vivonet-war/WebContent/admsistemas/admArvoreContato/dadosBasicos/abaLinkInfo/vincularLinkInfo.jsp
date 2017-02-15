<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormLink"                       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink"/>
<bean:define id="AdmTipoClienteDisponivelVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink.admTipoClienteDisponivelVO"/>
<bean:define id="AdmTipoClienteSelecionadoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink.admTipoClienteSelecionadoVO"/>
<bean:define id="AdmTipoLinhaDisponivelVO"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink.admTipoLinhaDisponivelVO"/>
<bean:define id="AdmTipoLinhaSelecionadoVO"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink.admTipoLinhaSelecionadoVO"/>
<bean:define id="AdmTipoOperadoraDisponivelVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink.admTipoOperadoraDisponivelVO"/>
<bean:define id="AdmTipoOperadoraSelecionadaVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink.admTipoOperadoraSelecionadaVO"/>
<bean:define id="AdmObjetoLinkVO"                name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLink.admObjetoLinkVO"/>

   

<netui-template:template templatePage="../../../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Árvore"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
    </netui-template:section>

    <netui-template:section name="bodySection">

    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="Javascript">
    
        // Globais

        //Controle max min
        var up = true;

        function resizeMaxMin() 
        {
            alert('entro');
            //Controle da visibilidade
            //tbConfiguracoes.style.display = (up ? "none" : "");

            //Rezise dos objetos
            document.getElementById("dvContato").style.height = (up ? "309px" : "170px");
            document.getElementById("tableTitle").style.height = (up ? "309px" : "170px");

            //Atualiza a imagem de exibição                    
            document.forms[0].elements["btMaxMin"].src = "<%=request.getContextPath()%>/resources/images/" + (up ? "bt_lupa_aba_down.gif" : "bt_lupa_aba.gif");
            
            //Atualiza variável de controle
            up = !up;
            alert('sair');

        }
    
        function alterarLinkOriginal(idTipoRelacionamento, idUFOperadora, idTipoLinha, idContato, nmLink){
            document.forms[0].idContatoEditado.value = idContato;  
            document.forms[0].idTipoLinhaEditado.value = idTipoLinha;
            document.forms[0].idTipoRelacionamentoEditado.value = idTipoRelacionamento;
            document.forms[0].idUFOperadoraEditado.value = idUFOperadora;
            document.forms[0].nmLinkEditado.value = nmLink;
            document.forms[0].nmLink.value = nmLink;
            exibeAlterar();
        }
        function alterarLink(idContatoInformacao,idUFOperadora)
        {
            document.forms[0].idContatoInformacao.value = idContatoInformacao;
            document.forms[0].idUFOperadora.value = idUFOperadora;
            divAlterar.style.display = '';
            document.forms[0].target = 'ifrmAlterar';
            document.forms[0].action = 'carregaAltera.do';
            document.forms[0].submit();            
            mostrar_div();
        }
        
        
        function removeLink(idContatoInformacao, idContato){
            document.forms[0].target = '';
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].action = 'removeLink.do?idContatoInformacao='+idContatoInformacao+'&idContato='+idContato;
                mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function incluiLink()
        {
            if(valida())
            {
                seleciona();
                document.forms[0].target = '';
                document.forms[0].action = 'insereLink.do';
                mostrar_div();
                document.forms[0].submit();
                limpaCampos();
            }
            
        }
        
        function valida()
        {
            if(document.forms[0].arrayAdmTipoOperadoraSelecionada.length <= 0)
            {
                alert('Não existem Operadora(s) associada(s) a ser(em) salva(s).');
                return false;
                
            }else if(document.forms[0].arrayTipoLinhaSelecionado.length <= 0)
            {
                alert('Não existem tipo Linha(s) a ser(em) salva(s).');
                return false;
            
            }else if(document.forms[0].arrayAdmTipoClienteSelecionado.length <= 0)
            {
            
                alert('Não existem Cliente(s) a ser(em) salva(s).');
                return false;
                
           }else if(trim(document.forms[0].nmLink.value) == '')
           {
                alert('Preencha o campo Link.');
                return false;
            }
            
            return true;
        }

        function salvaLink()
        {
            if(valida())
            {
                seleciona();
                document.forms[0].target = '';
                document.forms[0].action = 'insereLink.do';
                mostrar_div();
                document.forms[0].submit();
                limpaCampos();
            }
        }
        
        function alteraLink(){
            if(trim(document.forms[0].nmLink.value) == '' )
            {
                alert('Preencha o campo link.');
                return;
            }
            document.forms[0].action = 'salvaEditaLink.do';
            mostrar_div();
            document.forms[0].submit();
            limpaCampos();
        }
        
        function limpaCampos()
        {
            document.forms[0].target = '';
            document.forms[0].idContato.value = '';  
            document.forms[0].idTipoLinhaEditado.value = '';
            document.forms[0].idTipoRelacionamentoEditado.value = '';
            document.forms[0].idUFOperadora.value = '';
            document.forms[0].nmLink.value = '';
        }
        
            
        function exibeIncluir(){    
            document.all.dvBtIncluir.style.display='block';
            document.all.dvBtAlterar.style.display='none';
            
        }
        function exibeAlterar(){   
            document.all.dvBtIncluir.style.display='none';
            document.all.dvBtAlterar.style.display='block';
        }
        
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
            exibeIncluir();
            //limpaCampos();
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    // Trata a primeira posicao vazia do list, se houver
                    if ((listaDestino.options.length == 1) && (trim(listaDestino.options[0].text) == "")) {
                        listaDestino.options[0] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    } else {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        }
        
        function seleciona() {
      
                for ( i = 0; i < document.forms[0].arrayAdmTipoOperadoraSelecionada.options.length; i++ ){
                    document.forms[0].arrayAdmTipoOperadoraSelecionada.options[i].selected = true;
                }
                for ( i = 0; i < document.forms[0].arrayTipoLinhaSelecionado.options.length; i++ ){
                    document.forms[0].arrayTipoLinhaSelecionado.options[i].selected = true;
                }
                for ( i = 0; i < document.forms[0].arrayAdmTipoClienteSelecionado.options.length; i++ ){
                    document.forms[0].arrayAdmTipoClienteSelecionado.options[i].selected = true;
                }
        }
        
        function enviaVoltar()
        {
            //window.location.href='/FrontOfficeWeb/admsistemas/admArvoreContato/begin.do'; return false
            document.forms[0].action = '/FrontOfficeWeb/admsistemas/admArvoreContato/begin.do';
            mostrar_div();
            document.forms[0].submit();
        }        

    </script>

        <script language="javascript" for="window" event="onload">
        <!--   
            oculta_div();
            document.forms[0].nmLink.value = '';
            if('<bean:write name="FormLink" property="msgError" />' != "")
            {
                alert('<bean:write name="FormLink" property="msgError" />');
            }
        -->
        </script> 

    <acesso:controlHiddenItem nomeIdentificador="adm_vlin_verpagina">

        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <vivo:sessao id="link" width="790" height="470" label="Árvore de Contatos" operacoes="Link" scroll="no">
        
        <form action="begin" id="begin" name="begin" method="post">
        
            <html:hidden name="FormLink" property="idContato"/>
            <html:hidden name="FormLink" property="idTipoLinhaEditado"/>
            <html:hidden name="FormLink" property="idTipoRelacionamentoEditado"/>
            <html:hidden name="FormLink" property="idUFOperadora"/>
            <html:hidden name="FormLink" property="nmLinkEditado"/>
            <html:hidden name="FormLink" property="idContatoInformacao"/>
            
            <table width="780" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td align="center">
                    
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                        
                        <table width="760" border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray">
                            <tr>
                                <td class="tblEstatica_campoNome" align="left" width="20%">&nbsp;
                                    <netui:label value="Ítem Selecionado:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td class="tblEstatica_campoValor" align="left" width="80%">&nbsp;
                                    <bean:write name="FormLink" property="nmContato" />
                                </td>
                            <tr>
                        </table>
                        
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                        
                        <table width="760" border="0" cellspacing="0" cellpadding="1">
                            <tr>
                                <td width="345" valign="top" align="center">
                                    Operadoras Disponíveis<br>
                                    <html:select name="FormLink" property="arrayAdmTipoOperadoraDisponivel" multiple="true" style="width:345px;" size="4">
                                        <html:options collection="AdmTipoOperadoraDisponivelVO" property="idUFOperadora" labelProperty="nmUFOperadora" />
                                    </html:select>
                                </td>
                                <td width="70" valign="middle" align="center" style="padding-top:10px;">
                                    <img vspace="5" id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].arrayAdmTipoOperadoraDisponivel, document.forms[0].arrayAdmTipoOperadoraSelecionada);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].arrayAdmTipoOperadoraSelecionada, document.forms[0].arrayAdmTipoOperadoraDisponivel);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                                </td>
                                <td width="345" valign="top" align="center">
                                    Operadoras Selecionadas<br>
                                    <html:select name="FormLink" property="arrayAdmTipoOperadoraSelecionada" multiple="true" style="width:345px;" size="4">
                                        <html:options collection="AdmTipoOperadoraSelecionadaVO" property="idUFOperadora" labelProperty="nmUFOperadora" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td width="345" valign="top" align="center">
                                    Linhas Disponíveis<br>
                                    <html:select name="FormLink" property="arrayTipoLinhaDisponivel" multiple="true" style="width:345px;" size="4">
                                        <html:options collection="AdmTipoLinhaDisponivelVO" property="idTipoLinha" labelProperty="dsTipoLinha" />
                                    </html:select>
                                </td>
                                <td width="70" valign="middle" align="center" style="padding-top:10px;">
                                    <img vspace="5" id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].arrayTipoLinhaDisponivel, document.forms[0].arrayTipoLinhaSelecionado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].arrayTipoLinhaSelecionado, document.forms[0].arrayTipoLinhaDisponivel);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                                </td>
                                <td width="345" valign="top" align="center">
                                    Linhas Selecionados<br>
                                    <html:select name="FormLink" property="arrayTipoLinhaSelecionado" multiple="true" style="width:345px;" size="4">
                                        <html:options collection="AdmTipoLinhaSelecionadoVO" property="idTipoLinha" labelProperty="dsTipoLinha" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td width="345" valign="top" align="center">
                                    Clientes Disponíveis<br>
                                    <html:select name="FormLink" property="arrayAdmTipoClienteDisponivel" multiple="true" style="width:345px;" size="4">
                                        <html:options collection="AdmTipoClienteDisponivelVO" property="idTipoCliente" labelProperty="nmTipoCliente" />
                                    </html:select>
                                </td>
                                <td width="70" valign="middle" align="center" style="padding-top:10px;">
                                    <img vspace="5" id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].arrayAdmTipoClienteDisponivel, document.forms[0].arrayAdmTipoClienteSelecionado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].arrayAdmTipoClienteSelecionado, document.forms[0].arrayAdmTipoClienteDisponivel);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                                </td>
                                <td width="345" valign="top" align="center">
                                    Clientes Selecionados<br>
                                    <html:select name="FormLink" property="arrayAdmTipoClienteSelecionado" multiple="true" style="width:345px;" size="4">
                                        <html:options collection="AdmTipoClienteSelecionadoVO" property="idTipoCliente" labelProperty="nmTipoCliente" />
                                    </html:select>
                                </td>
                            </tr>
                        </table>
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>

                        <logic:notEqual name="FormLink" property="listaLinkLegth" value="0">
                        <!--aqui é o sub-->
                    <SPAN id="dvContato" style="overflow:hidden;width:765px;height:170px;">
                            <!-- div align="right" >
                            <img  id="btMaxMin" name="btMaxMin" title="Click para minimizar/maximizar os parâmetros de pesquisa" src="<//%=request.getContextPath()%>/resources/images/bt_lupa_aba.gif" style="cursor:hand" onclick="resizeMaxMin();">
                            </div -->
                        <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="90" tableWidth="745" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="20%" type="string">Operadora</vivo:tbHeaderColumn>					
                                <vivo:tbHeaderColumn align="left"   width="20%" type="string">Tipo de Linha</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left"   width="20%" type="string">Tipo de Cliente</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left"   width="30%" type="string">Link</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                <!--vivo:tbHeaderColumn align="center" width="5%" type="string">< /vivo:tbHeaderColumn-->
                            </vivo:tbHeader>
                            <vivo:tbRows>

                            <logic:iterate id="list" name="FormLink" property="admObjetoLinkVO">
                                    <vivo:tbRow key="linha">
                                        <vivo:tbRowColumn>
                                            <bean:write name="list" property="nmUFOperadora"/>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="list" property="nmTipoLinha"/>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="list" property="nmTipoRelacionamento"/>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="list" property="nmLink"/>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="adm_vlin_alterarlink">
                                            <a onClick="alterarLink('<bean:write name="list" property="idContatoInformacao"/>','<bean:write name="list" property="idUFOperadora"/>');">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Alterar Link">
                                            </a>
                                        </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="adm_vlin_excluirlink">
                                            <a onClick="removeLink('<bean:write name="list" property="idContatoInformacao"/>', '<bean:write name="list" property="idContato"/>');">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Link">
                                            </a>
                                        </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                            </logic:iterate>

                            </vivo:tbRows>
                        

                        </vivo:tbTable>

                        <!--aqui é o sub-->
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                        
                        <table class="tbl_bgGray" width="760">
                            <tr>
                                <td width="100"><b>&nbsp;&nbsp;Legendas:</b></td>
                                <td width="100"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0"> Alterar Link</td>
                                <td width="100"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0"> Excluir Link</td>
                                <td width="400">&nbsp;</td>
                            </tr>
                        </table>
                        
                        </SPAN>

                        </logic:notEqual>
                        
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                            <table cellpadding="0" cellspacing="0" width="760">
                                <tr>
                                    <td width="370"><img align="middle" id="btVoltar" onClick="enviaVoltar();" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/></td>
                                        <td width="340" style="tblEstatica_campoNome"><b>Link:</b>&nbsp;<html:text name="FormLink" property="nmLink" style="width:305px;" styleClass="input" maxlength="254"/></td>
    
                                        <td width="60" align="right">
                                        <div id="dvBtIncluir">
                                            <%if ((String)request.getAttribute("litleFlag") != null && ((String)request.getAttribute("litleFlag")).equals("true")){%>
                                                <acesso:controlHiddenItem nomeIdentificador="adm_vlin_incluirlink">
                                                <img align="middle" id="btIncluir" onClick="incluiLink(); return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>
                                                </acesso:controlHiddenItem>
                                            <%}else{%>
                                                <acesso:controlHiddenItem nomeIdentificador="adm_vlin_gravarlink">
                                                <img align="middle" id="btGravar" onClick="salvaLink(); return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>
                                                </acesso:controlHiddenItem>
                                            <%}%>
                                        </div>
                                        <div id="dvBtAlterar" style="display:none;">
                                        <acesso:controlHiddenItem nomeIdentificador="adm_vlin_alterarlink">
                                            <img align="middle" id="btAlterar" onClick="alteraLink(); return false" src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_alterar_over.gif'" style="border:none;cursor:hand;"/>
                                        </acesso:controlHiddenItem>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                    </td>
                </tr>
            </table>
            <vivo:quadroFlutuante id="divAlterar" idIframe="ifrmAlterar" spacesLeft="130" spacesTop="20" url="<%=request.getContextPath()%>" display="none" height="150" width="540" label="Alteração de Link"/>        
        </form>
        </vivo:sessao>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
