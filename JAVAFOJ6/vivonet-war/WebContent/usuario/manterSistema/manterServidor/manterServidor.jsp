<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormManterServ" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm"/>
<!-- array de grupos - contém a lista de grupos existentes -->
<bean:define id="aServidores"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm.listaServidorVO"/>
<bean:define id="servidorVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm.servidorVO"/>
<bean:define id="servidor"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterServidorForm.dsServidor"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">

    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <SCRIPT LANGUAGE="JavaScript">
    <!--
        // Simple rollover function which replaces the image.src with the passed image
        function swapImage(control, image) {
            control.src = image;
        }
    
        function salvarItem() {
            document.forms[0].dsServidor.value = trim(document.forms[0].dsServidor.value);
            if(document.forms[0].dsServidor.value != ""){
                document.forms[0].action = "salvaServidor.do";
                mostrar_div();
                document.forms[0].submit(); 
              }else{
                alert("Não há item a ser salvo!");
            }      
        }
        function pesquisaServidor(servidor) {
            var action = 'listaServidorAction.do?dsServidor='+servidor;
            document.forms[0].target = '_top';
            document.forms[0].action = action;
            mostrar_div();
            document.forms[0].submit();
        }
        
        function selecionaItem() {
            document.all.dvBtIncluir.style.display='none';
            document.all.dvBtAlterar.style.display='block';
            var lista = document.forms[0].listaServidor;
            if(lista.selectedIndex!="-1"){
                document.forms[0].idServidor.value=lista.options[lista.selectedIndex].value;
                document.forms[0].dsServidor.value=lista.options[lista.selectedIndex].text;
            }else{
                alert("Na há item selecionado!");
            }        
        }
        
        function testaCampos() {
            document.forms[0].dsServidor.value = trim(document.forms[0].dsServidor.value);
            
            if (trim(document.forms[0].dsServidor.value) == "") {
                alert("Servidor é um campo obrigatório.");
            } else {
                document.forms[0].action = "salvaGrupam.do";
                mostrar_div();
                document.forms[0].submit();
            }
        }

        function removeItem() {
            if (window.confirm("Confirma remoção do item?")) {
                mostrar_div();
                return true;
            }else{
                return false;
            }
        }
        
        function alteraServidor(id) {
            divAlteraServidor.style.display = '';
            document.forms[0].idServidor.value = id;
            document.forms[0].target = "ifrmAlteraServidor";
            document.forms[0].action = "carregaEdita.do?operacao=alterar";
            mostrar_div();
            document.forms[0].submit();
            ifrmAlteraServidor.src = 'carregaEdita.do';
        }
        
        function incluiServidor() {
            divIncluiServidor.style.display = '';
            document.forms[0].target = "ifrmIncluiServidor";
            document.forms[0].action = "carregaEdita.do?operacao=incluir";
            mostrar_div();
            document.forms[0].submit();
            ifrmIncluiServidor.src = 'carregaEdita.do';
        }
        
        function limpa()
        {
            document.forms[0].dsServidor.value = '';
            document.forms[0].dsServidor.focus();
        }
    // -->
    </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_mser_verpagina">
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="468" width="790" label="Sistemas" operacoes="Manuten&ccedil;&atilde;o de Servidores" scroll="no">
            <form action="salvaServidor.do" onSubmit="return false;" method="post">
            <html:hidden name="servidorVO" property="idServidor"/>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <center>
            <vivo:quadro height="410" id="listaservidores" width="760" label="Servidores cadastrados" scroll="no">
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="2"></div>
                <table width="750" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                        <td width="155" style="text-indent:10px;"><b>Nome do Servidor</b>
                        </td>
                        <td><html:text name="FormManterServ" property="dsServidor" style="width:330px" styleClass="input" maxlength="254" onkeypress="return ValidarTeclaURL()"/>
                        </td>
                        <td>
                            <img vspace="5" onclick="limpa(); return false" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none;cursor:hand;" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                       
                            <acesso:controlHiddenItem nomeIdentificador="usu_mser_pesq">
                                <img vspace="5" onclick="pesquisaServidor(document.forms[0].dsServidor.value);" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"/>
                            </acesso:controlHiddenItem>                        
                        </td>
                    </tr>
                </table>
                <br>

                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="4"></div>

                <logic:greaterThan name="FormManterServ" property="listaServidorLength" value="0">
    
                <logic:notEqual name="FormManterServ" property="listaServidorLength" value="-1">
                
                <vivo:tbTable styleId="idServidores" layoutHeight="270" layoutWidth="735" tableWidth="735" selectable="true" sortable="true" >

                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn width="" align="left" type="String">Servidor</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="20" type="String"></vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="20" type="String"></vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    
                    <vivo:tbRows>
                        <logic:iterate id="listaServidores" name="aServidores">
                        <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn><bean:write name="listaServidores" property="dsServidor"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_mser_alte">
                                <a href="Javascript:alteraServidor(<bean:write name="listaServidores" property="idServidor" />);">
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Servidor" border="0">
                                </a>
                                </acesso:controlHiddenItem>
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_mser_exclu">
                                <html:link page="/usuario/manterSistema/manterServidor/removeServidor.do" paramId="servidorId" paramName="listaServidores" paramProperty="idServidor">
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" alt="Excluir Servidor" border="0" onclick="return removeItem()">
                                </html:link>
                                </acesso:controlHiddenItem>                            
                            </vivo:tbRowColumn>
                        </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                   
                </vivo:tbTable>
                </logic:notEqual>
             </logic:greaterThan>
        <logic:equal name="FormManterServ" property="listaServidorLength" value="-1">
                <div align="center" style="font-size:11px;">
                    Não foi encontrado nenhum registro com a descrição fornecida.
                </div>
        </logic:equal>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                <logic:greaterThan name="FormManterServ" property="listaServidorLength" value="0">
                    <table width="750" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                        <tr>
                            <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Servidor
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Servidor
                            </td>
                            <td width="320">
                            </td>
                        </tr>
                    </table>
                </logic:greaterThan>
            <div align="right">
                <acesso:controlHiddenItem nomeIdentificador="usu_mser_incl">
                <img hspace="8" vspace="10" id="btVoltar" onClick="incluiServidor(); return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'"  onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>        
                </acesso:controlHiddenItem>
            </div>
            </vivo:quadro>
            </center>
                <img hspace="8" vspace="10" id="btVoltar" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
            <vivo:quadroFlutuante label="Alterar Servidor" scroll="false" src="" idIframe="ifrmAlteraServidor" id="divAlteraServidor" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            <vivo:quadroFlutuante label="Incluir Servidor" scroll="false" src="" idIframe="ifrmIncluiServidor" id="divIncluiServidor" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormManterServ" property="msgError" />' != ""){
            alert('<bean:write name="FormManterServ" property="msgError" />');
        }
        oculta_div();
    -->
    </script> 
            </form>
        </vivo:sessao>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>