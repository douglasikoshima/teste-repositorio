<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/> 
  
<bean:define id="ManterAtividadeForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterAtividadeForm"/>
<bean:define id="ListaAtividades"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterAtividadeForm.listaAtividadeVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Atividade"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">

    <SCRIPT LANGUAGE="JavaScript">
    <!--
        // Simple rollover function which replaces the image.src with the passed image
        function swapImage(control, image) {
            control.src = image;
        }
    
        function salvarItem(){
            if(document.forms[0].nmAtividade.value != ""){
                document.forms[0].nmAtividade.value = trim(document.forms[0].nmAtividade.value);
                document.forms[0].action = '<%=request.getContextPath()%>/usuario/Organograma/manterAtividadeOrganograma/salvaAtividade.do';
                mostrar_div();
                document.forms[0].submit(); 
                document.forms[0].idAtividade.value = '';
                document.forms[0].nmAtividade.value = '';
              }else{
                alert("Não há item a ser salvo!");
            }      
        }
        
        function pesquisaAtividade(Atividade){
            document.forms[0].nmAtividade.value = trim(Atividade);
            var action = '<%=request.getContextPath()%>/usuario/Organograma/manterAtividadeOrganograma/listaAtividade.do';
            document.forms[0].target = '';
            document.forms[0].action = action;
            mostrar_div();
            document.forms[0].submit();
            document.forms[0].idAtividade.value = '';
            document.forms[0].nmAtividade.value = '';
        }
        
        function selecionaItem(){
            document.all.dvBtIncluir.style.display='none';
            document.all.dvBtAlterar.style.display='block';
            var lista = document.forms[0].listaAtividade;
            if(lista.selectedIndex!="-1"){
                document.forms[0].idAtividade.value=lista.options[lista.selectedIndex].value;
                document.forms[0].nmAtividade.value=lista.options[lista.selectedIndex].text;
            }else{
                alert("Na há item selecionado!");
            }        
        }
        
        function testaCampos(){
            if (document.forms[0].nmAtividade.value == "") {
                alert("Você não digitou um nome para o Atividade.");
            } else {
                document.forms[0].nmAtividade.value = trim(document.forms[0].nmAtividade.value);            
                document.forms[0].action = '<%=request.getContextPath()%>/usuario/Organograma/manterAtividadeOrganograma/salvaAtividade.do';
                mostrar_div();
                document.forms[0].submit();
                document.forms[0].idAtividade.value = '';
                document.forms[0].nmAtividade.value = '';
            }
        }

        
        function removeItem(id){
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].idAtividade.value = id;
                document.forms[0].action = '<%=request.getContextPath()%>/usuario/Organograma/manterAtividadeOrganograma/removeAtividade.do?idAtividade='+id;
                mostrar_div();
                document.forms[0].submit(); 
                document.forms[0].idAtividade.value = '';
                document.forms[0].nmAtividade.value = '';
            }
        }
        
        function alteraAtividade(id){
            document.forms[0].idAtividade.value = id;
            divAlteraAtividade.style.display = '';
            document.forms[0].target = 'ifrmAlteraAtividade';
            document.forms[0].action = "<%=request.getContextPath()%>/usuario/Organograma/manterAtividadeOrganograma/alteraIncluiAtividade.do?operacao=alterar&idAtividade="+id;
            mostrar_div();
            document.forms[0].submit();
            document.forms[0].idAtividade.value = '';
            document.forms[0].nmAtividade.value = '';
        }
        
        function incluiAtividade(){
            document.forms[0].idAtividade.value = '';
            document.forms[0].nmAtividade.value = '';
            divIncluiAtividade.style.display = '';
            document.forms[0].target = 'ifrmIncluiAtividade';
            document.forms[0].action = '<%=request.getContextPath()%>/usuario/Organograma/manterAtividadeOrganograma/incluiAtividade.do?operacao=incluir';
            mostrar_div();
            document.forms[0].submit();
        }     
        
        function limpa(){
            document.forms[0].nmAtividade.value = '';
            document.forms[0].nmAtividade.focus();
        }
    // --> 
    </SCRIPT>
    </netui-template:section>

<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="usu_mativ_verpagina">

        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="468" width="790" label="Sistemas" operacoes="Manuten&ccedil;&atilde;o de Atividades" scroll="no">
            
            <form action="<%=request.getContextPath()%>/usuario/Organograma/manterAtividadeOrganograma/salvaAtividade.do"  id="salvaAtividade" name="salvaAtividade" method="POST">
            <html:hidden property="idAtividade" name="ManterAtividadeForm"/>

            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="10"></div>
                        
            <center>
            <vivo:quadro height="410" id="listaAtividades" width="760" label="Atividades cadastrados" scroll="no">
                
                <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="2"></div>
                
                <table width="750" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                        <td width="155" style="text-indent:10px;">Pesquisa de Atividades
                        </td>
                        <td><html:text name="ManterAtividadeForm" property="nmAtividade" style="width:330px" styleClass="input" maxlength="254"/>
                        </td>
                        <td>
                                <img vspace="5" onclick="limpa();return false;" id="btLimpar" src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif" style="border:none;cursor:hand;" onMouseOut="this.src='<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='<%=request.getContextPath()%>/resources/images/bt_limpar_over.gif'"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                       
                            <acesso:controlHiddenItem nomeIdentificador="usu_mativ_pesquisar">
                                <img vspace="5" onclick="pesquisaAtividade(document.forms[0].nmAtividade.value);" id="btLimpar" src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand;" onMouseOut="this.src='<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='<%=request.getContextPath()%>/resources/images/bt_pesquisar_over.gif'"/>                        
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                <br>
                
                <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="4"></div>

                <logic:greaterThan name="ManterAtividadeForm" property="listaAtividadeLength" value="0">
                    <vivo:tbTable styleId="idAtividades" layoutHeight="270" layoutWidth="735" tableWidth="735" selectable="true" sortable="true" >
    
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn width="" align="left" type="String">Atividade</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="20" type="String"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="20" type="String"></vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        
                        <vivo:tbRows>
                            <logic:iterate name="ManterAtividadeForm" property="listaAtividadeVO" id="itemAtividade">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn><bean:write name="itemAtividade" property="nmAtividade"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_mativ_alterar">
                                    <a href="javascript:alteraAtividade(<bean:write name="itemAtividade" property="idAtividade"/>);">
                                        <img src="<%=request.getContextPath()%>/resources/images/bt_icon_alterar.gif" alt="Alterar Atividade" border="0">
                                    </a>
                                </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_mativ_remover">
                                    <a href="#" onclick="removeItem('<bean:write name="itemAtividade" property="idAtividade" />'); return false">
                                        <img src="<%=request.getContextPath()%>/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Atividade">
                                    </a>
                                </acesso:controlHiddenItem>                                                             
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                        
                    </vivo:tbTable>
                </logic:greaterThan>
                
                <logic:equal name="ManterAtividadeForm" property="listaAtividadeLength" value="0">
                    <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td align="center">
                                <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                    <tr>
                                        <td width="90%" align="center" class="tblEstatica_campoNome">
                                            Não foi encontrado nenhuma Atividade com a descrição fornecida.
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </logic:equal>
                
                <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>

                <logic:greaterThan name="ManterAtividadeForm" property="listaAtividadeLength" value="0">
                    <table width="750" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                        <tr>
                            <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                            <td>
                                <img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="4">
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Atividade
                            </td>
                            <td>
                                <img src="<%=request.getContextPath()%>/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Atividade
                            </td>
                            <td width="320">
                            </td>
                        </tr>
                    </table>
                </logic:greaterThan>

            <div align="right">
            <acesso:controlHiddenItem nomeIdentificador="usu_mativ_incluir">
                <img hspace="8" vspace="10" id="btVoltar" onClick="incluiAtividade();" src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='<%=request.getContextPath()%>/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>
            </acesso:controlHiddenItem>
            </div>

            </vivo:quadro>
            </center>
                <img hspace="8" vspace="10" id="btVoltar" onClick="window.location.href='<%=request.getContextPath()%>/index.jsp';" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='<%=request.getContextPath()%>/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
            </form>
            
            <vivo:quadroFlutuante label="Alterar Atividade" scroll="false" src="about:blank" idIframe="ifrmAlteraAtividade" id="divAlteraAtividade" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante label="Incluir Atividade" scroll="false" src="about:blank" idIframe="ifrmIncluiAtividade" id="divIncluiAtividade" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
        </vivo:sessao>
    </acesso:controlHiddenItem>

	    <script language="javascript" for="window" event="onload">
	    <!--   
	        if('<bean:write name="ManterAtividadeForm" property="msgError" />' != ''){
	            alert('<bean:write name="ManterAtividadeForm" property="msgError" />');
	        }
	        oculta_div();
	    -->
	    </script> 
    </netui-template:section>
</netui-template:template>
