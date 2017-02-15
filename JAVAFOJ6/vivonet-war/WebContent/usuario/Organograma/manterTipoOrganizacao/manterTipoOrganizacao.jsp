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
  
<bean:define id="ManterTipoOrganizacaoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterTipoOrganizacaoForm"/>
<bean:define id="ListaTipoOrganizacaos"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterTipoOrganizacaoForm.listaTipoOrganizacaoVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Tipo Organizacao"/>
    <netui-template:setAttribute name="modulo" value="Organograma"/>

    <netui-template:section name="headerSection">

    <SCRIPT LANGUAGE="JavaScript">
    <!--
        // Simple rollover function which replaces the image.src with the passed image
        function swapImage(control, image) {
            control.src = image;
        }
    
        function salvarItem() {
            if(document.forms[0].dsTipoOrganizacao.value != ""){
                document.forms[0].dsTipoOrganizacao.value = trim(document.forms[0].dsTipoOrganizacao.value);
                document.forms[0].action = "salvaTipoOrganizacao.do?idTipoOrganizacao="+document.forms[0].idTipoOrganizacao.value+"&dsTipoOrganizacao="+document.forms[0].dsTipoOrganizacao.value;
                mostrar_div();
                document.forms[0].submit(); 
                document.forms[0].idTipoOrganizacao.value = '';
                document.forms[0].dsTipoOrganizacao.value = '';
              }else{
                alert("Não há item a ser salvo!");
            }      
        }
        function pesquisaTipoOrganizacao(TipoOrganizacao) {
            var action = 'listaTipoOrganizacao.do';
            document.forms[0].dsTipoOrganizacao.value = trim(TipoOrganizacao);
            document.forms[0].target = '';
            document.forms[0].action = action;
            mostrar_div();
            document.forms[0].submit();
            document.forms[0].idTipoOrganizacao.value = '';
            document.forms[0].dsTipoOrganizacao.value = '';
        }
        
        function selecionaItem() {
            document.all.dvBtIncluir.style.display='none';
            document.all.dvBtAlterar.style.display='block';
            var lista = document.forms[0].listaTipoOrganizacao;
            if(lista.selectedIndex!="-1"){
                document.forms[0].idTipoOrganizacao.value=lista.options[lista.selectedIndex].value;
                document.forms[0].dsTipoOrganizacao.value=lista.options[lista.selectedIndex].text;
            }else{
                alert("Na há item selecionado!");
            }        
        }
        
        function testaCampos() {
            if (document.forms[0].dsTipoOrganizacao.value == "") {
                alert("Você não digitou um nome para o TipoOrganizacao.");
            } else {
                document.forms[0].dsTipoOrganizacao.value = trim(document.forms[0].dsTipoOrganizacao.value);
                document.forms[0].action = "salvaTipoOrganizacao.do?idTipoOrganizacao="+document.forms[0].idTipoOrganizacao.value+"&dsTipoOrganizacao="+document.forms[0].dsTipoOrganizacao.value;
                mostrar_div();
                document.forms[0].submit();
                document.forms[0].idTipoOrganizacao.value = '';
                document.forms[0].dsTipoOrganizacao.value = '';
            }
        }

        
        function removeItem(id) {
    
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].idTipoOrganizacao.value = id;
                document.forms[0].dsTipoOrganizacao.value = trim(document.forms[0].dsTipoOrganizacao.value);
                document.forms[0].action = "removeTipoOrganizacao.do?idTipoOrganizacao="+id;
                mostrar_div();
                document.forms[0].submit(); 
                document.forms[0].idTipoOrganizacao.value = '';
                document.forms[0].dsTipoOrganizacao.value = '';
            }
        }
        
        function alteraTipoOrganizacao(id)
        {
            document.forms[0].idTipoOrganizacao.value = id;
            divAlteraTipoOrganizacao.style.display = '';
            document.forms[0].target = "ifrmAlteraTipoOrganizacao";
            document.forms[0].action = "alteraIncluiTipoOrganizacao.do?operacao=alterar&idTipoOrganizacao="+id;
            mostrar_div();
            document.forms[0].submit();
            document.forms[0].idTipoOrganizacao.value = '';
            document.forms[0].dsTipoOrganizacao.value = '';
        }
        
        function incluiTipoOrganizacao() {
            document.forms[0].dsTipoOrganizacao.value = '';
            document.forms[0].idTipoOrganizacao.value = '';
            divIncluiTipoOrganizacao.style.display = '';
            document.forms[0].target = "ifrmIncluiTipoOrganizacao";
            document.forms[0].action = "incluiTipoOrganizacao.do?operacao=incluir";
            mostrar_div();
            document.forms[0].submit();
        }     
        function limpa()
        {
            document.forms[0].dsTipoOrganizacao.value = '';
            document.forms[0].dsTipoOrganizacao.focus();
        }
    // -->
    </SCRIPT>

    </netui-template:section>

<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="usu_mtiporg_verpagina">

        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="468" width="790" label="Sistemas" operacoes="Manuten&ccedil;&atilde;o de Tipo Organizacao" scroll="no">
            
            <form action="<%=request.getContextPath()%>/usuario/Organograma/manterTipoOrganizacao/salvaTipoOrganizacao.do" id="salvaTipoOrganizacao" name="salvaTipoOrganizacao" onSubmit="return false;" method="POST">            
            <html:hidden property="idTipoOrganizacao" name="ManterTipoOrganizacaoForm"/>

            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
                        
            <center>
            <vivo:quadro height="410" id="listaTipoOrganizacaos" width="780" label="Tipo Organizacao cadastrados" scroll="no">
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="2"></div>
                
                <table width="750" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                        <td width="155" style="text-indent:10px;">Pesquisa de Tipo Organizacao
                        </td>
                        <td><html:text name="ManterTipoOrganizacaoForm" property="dsTipoOrganizacao" style="width:330px" styleClass="input" maxlength="254"/>
                        </td>
                        <td>
                                <img vspace="5" onclick="limpa(); return false" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                       
                            <acesso:controlHiddenItem nomeIdentificador="usu_mtiporg_pesquisar">
                                <img vspace="5" onclick="pesquisaTipoOrganizacao(document.forms[0].dsTipoOrganizacao.value);" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"/>                        
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                <br>
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="4"></div>

                <logic:greaterThan name="ManterTipoOrganizacaoForm" property="listaTipoOrganizacaoLength" value="0">
                    <vivo:tbTable styleId="idTipoOrganizacaos" layoutHeight="270" layoutWidth="735" tableWidth="735" selectable="true" sortable="true" >
    
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn width="" align="left" type="String">Tipo Organizacao</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="20" type="String"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="20" type="String"></vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        
                        <vivo:tbRows>
                            <logic:iterate name="ManterTipoOrganizacaoForm" property="listaTipoOrganizacaoVO" id="itemTipoOrganizacao">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn>
                                   <vivo:hint maxLength="80"> <bean:write name="itemTipoOrganizacao" property="dsTipoOrganizacao"/></vivo:hint>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_mtiporg_alterar">
                                    <a href="Javascript:alteraTipoOrganizacao(<bean:write name="itemTipoOrganizacao" property="idTipoOrganizacao" />);">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Tipo Organizacao" border="0">
                                    </a>
                                </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_mtiporg_excluir">
                                    <a href="#" onclick="removeItem('<bean:write name="itemTipoOrganizacao" property="idTipoOrganizacao" />'); return false">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Tipo Organizacao">
                                    </a> 
                                </acesso:controlHiddenItem>                                                            
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                        
                    </vivo:tbTable>
                 </logic:greaterThan>

                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

                <logic:greaterThan name="ManterTipoOrganizacaoForm" property="listaTipoOrganizacaoLength" value="0">

                    <table width="750" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                        <tr>
                            <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Tipo Organizacao
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Tipo Organizacao
                            </td>
                            <td width="320">
                            </td>
                        </tr>
                    </table>
                
                </logic:greaterThan>

                <logic:equal name="ManterTipoOrganizacaoForm" property="listaTipoOrganizacaoLength" value="0">
                    <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td align="center">
                                <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                                    <tr>
                                        <td width="90%" align="center" class="tblEstatica_campoNome">
                                            Não foi encontrado nenhum Tipo Organização com a descrição fornecida.
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </logic:equal>

            <div align="right">
            <acesso:controlHiddenItem nomeIdentificador="usu_mtiporg_incluir">
                <img hspace="8" vspace="10" id="btVoltar" onClick="incluiTipoOrganizacao();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>        
            </acesso:controlHiddenItem>
            <div>

                
            </vivo:quadro>
            
            </center>
            
            <img hspace="8" vspace="10" id="btVoltar" onClick="window.location.href='/FrontOfficeWeb/index.jsp';" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>

            </form>
            
            <vivo:quadroFlutuante label="Alterar Tipo Organizacao" scroll="false" src="/FrontOfficeWeb/usuario/nada.html" idIframe="ifrmAlteraTipoOrganizacao" id="divAlteraTipoOrganizacao" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            
            <vivo:quadroFlutuante label="Incluir Tipo Organizacao" scroll="false" src="/FrontOfficeWeb/usuario/nada.html" idIframe="ifrmIncluiTipoOrganizacao" id="divIncluiTipoOrganizacao" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

            <script language="javascript" for="window" event="onload">
            <!--   
                if('<bean:write name="ManterTipoOrganizacaoForm" property="msgError" />' != '')
                {
                    alert('<bean:write name="ManterTipoOrganizacaoForm" property="msgError" />');
                }
                oculta_div();
            -->
            </script>          

            
            </vivo:sessao>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
