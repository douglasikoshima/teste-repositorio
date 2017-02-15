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
  
<bean:define id="ManterUnidadeForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterUnidadeForm"/>
<bean:define id="ListaUnidades"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterUnidadeForm.listaUnidadeVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Departamento"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">

    <SCRIPT LANGUAGE="JavaScript">
    <!--
        // Simple rollover function which replaces the image.src with the passed image
        function swapImage(control, image) {
            control.src = image;
        }
        
        function salvarItem() {
            if(document.forms[0].nmUnidade.value != ""){
                document.forms[0].nmUnidade.value = trim(document.forms[0].nmUnidade.value);
                document.forms[0].action = "salvaUnidade.do";
                mostrar_div();
                document.forms[0].submit(); 
                document.forms[0].idUnidade.value = '';
                document.forms[0].nmUnidade.value = '';
              }else{
                alert("Não há item a ser salvo!");
            }      
        }
        function pesquisaUnidade(Departamento) {
            document.forms[0].nmUnidade.value = trim(Departamento);
            var action = 'listaUnidade.do';
            document.forms[0].target = '';
            document.forms[0].action = action;
            mostrar_div();
            document.forms[0].submit();
            document.forms[0].idUnidade.value = '';
            document.forms[0].nmUnidade.value = '';
        }
        
        function selecionaItem() {
            document.all.dvBtIncluir.style.display='none';
            document.all.dvBtAlterar.style.display='block';
            var lista = document.forms[0].listaUnidade;
            if(lista.selectedIndex!="-1"){
                document.forms[0].idUnidade.value=lista.options[lista.selectedIndex].value;
                document.forms[0].nmUnidade.value=lista.options[lista.selectedIndex].text;
            }else{
                alert("Na há item selecionado!");
            }        
        }
        
        function testaCampos() {
            if (document.forms[0].nmUnidade.value == "") {
                alert("Você não digitou um nome para o Departamento.");
            } else {
                document.forms[0].nmUnidade.value = trim(document.forms[0].nmUnidade.value);
                document.forms[0].action = "salvaUnidade.do";
                mostrar_div();
                document.forms[0].submit();
                document.forms[0].idUnidade.value = '';
                document.forms[0].nmUnidade.value = '';
            }
        }

        
        function removeItem(id) 
        {
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].idUnidade.value = id;
                document.forms[0].nmUnidade.value = trim(document.forms[0].nmUnidade.value);
                document.forms[0].action = "removeUnidade.do";
                document.forms[0].target = '';
                mostrar_div();
                document.forms[0].submit(); 
                document.forms[0].idUnidade.value = '';
                document.forms[0].nmUnidade.value = '';
            }
        }

        function alteraUnidade(id)
        {
            document.forms[0].idUnidade.value = id;
            divAlteraUnidade.style.display = '';
            document.forms[0].target = "ifrmAlteraUnidade";
            document.forms[0].action = "alteraIncluiUnidade.do?operacao=alterar&idUnidade="+id;
            mostrar_div();
            document.forms[0].submit();
            document.forms[0].idUnidade.value = '';
            document.forms[0].nmUnidade.value = '';
        }
        
        function incluiUnidade() {
            document.forms[0].idUnidade.value = '';
            document.forms[0].nmUnidade.value = '';
            divIncluiUnidade.style.display = '';
            document.forms[0].target = "ifrmIncluiUnidade";
            document.forms[0].action = "incluiUnidade.do?operacao=incluir";
            mostrar_div();
            document.forms[0].submit();
        }     

        function limpa()
        {
            document.forms[0].nmUnidade.value = '';
            document.forms[0].nmUnidade.focus();
        }
    // -->
    </SCRIPT>
    </netui-template:section>

<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="usu_muni_verpagina">
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="468" width="790" label="Sistemas" operacoes="Manuten&ccedil;&atilde;o de Departamentos" scroll="no">
            
            <form action="<%=request.getContextPath()%>/usuario/Organograma/manterUnidadeOrganizacao/salvaUnidade.do" id="salvaUnidade" name="salvaUnidade" onSubmit="return false;" method="POST" >            
            <html:hidden property="idUnidade" name="ManterUnidadeForm"/>

            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
                        
            <center>
            <vivo:quadro height="410" id="listaUnidades" width="760" label="Departamentos cadastrados" scroll="no">
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="2"></div>
                
                <table width="750" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                        <td width="155" style="text-indent:10px;">Pesquisa de Departamentos
                        </td>
                        <td><html:text name="ManterUnidadeForm" property="nmUnidade" style="width:330px" styleClass="input" maxlength="254"/>
                        </td>
                        <td>
                                <img vspace="5" onclick="limpa(); return false" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                       
                                <acesso:controlHiddenItem nomeIdentificador="usu_muni_pesquisar">
                                <img vspace="5" onclick="pesquisaUnidade(document.forms[0].nmUnidade.value);" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'"/>                        
                                </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                <br>
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="4"></div>

                <logic:greaterThan name="ManterUnidadeForm" property="listaUnidadeLength" value="0">
                    <vivo:tbTable styleId="idUnidades" layoutHeight="270" layoutWidth="735" tableWidth="735" selectable="true" sortable="true" >
    
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn width="" align="left" type="String">Departamento</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="20" type="String"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="20" type="String"></vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        
                        <vivo:tbRows>
                            <logic:iterate name="ManterUnidadeForm" property="listaUnidadeVO" id="itemUnidade">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn>
                                   <vivo:hint maxLength="80"> <bean:write name="itemUnidade" property="nmUnidade"/></vivo:hint>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_muni_alterar">
                                    <a href="Javascript:alteraUnidade(<bean:write name="itemUnidade" property="idUnidade"/>);">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Departamento" border="0">
                                    </a>
                                </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                <acesso:controlHiddenItem nomeIdentificador="usu_muni_excluir">
                                    <a href="#" onclick="removeItem('<bean:write name="itemUnidade" property="idUnidade" />'); return false">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Departamento">
                                    </a> 
                                </acesso:controlHiddenItem>                                                            
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                        
                    </vivo:tbTable>
                 </logic:greaterThan>
                    <logic:equal name="ManterUnidadeForm" property="listaUnidadeLength" value="0">
                        <div align="center" style="font-size:11px;">
                            Não foi encontrado nenhum departamento com a descrição fornecida.
                        </div>
                    </logic:equal>
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

                <logic:greaterThan name="ManterUnidadeForm" property="listaUnidadeLength" value="0">

                    <table width="750" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                        <tr>
                            <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Departamento
                            </td>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Departamento
                            </td>
                            <td width="320">
                            </td>
                        </tr>
                    </table>
                
                </logic:greaterThan>

            <div align="right">
            <acesso:controlHiddenItem nomeIdentificador="usu_muni_incluir">
                <img hspace="8" vspace="10" id="btVoltar" onClick="incluiUnidade();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>        
            </acesso:controlHiddenItem>
            <div>

                
            </vivo:quadro>
        
            </center>
            
            <img hspace="8" vspace="10" id="btVoltar" onClick="window.location.href='/FrontOfficeWeb/index.jsp';" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;" align="left"/>

            <script language="javascript" for="window" event="onload">
            <!--   
                if('<bean:write name="ManterUnidadeForm" property="msgError" />' != '')
                {
                    alert('<bean:write name="ManterUnidadeForm" property="msgError" />');
                }
                oculta_div();
            -->
            </script>  
                
            </form>

            <vivo:quadroFlutuante label="Alterar Departamento" scroll="false" src="/FrontOfficeWeb/usuario/nada.html" idIframe="ifrmAlteraUnidade" id="divAlteraUnidade" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
            
            <vivo:quadroFlutuante label="Incluir Departamento" scroll="false" src="/FrontOfficeWeb/usuario/nada.html" idIframe="ifrmIncluiUnidade" id="divIncluiUnidade" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

        </vivo:sessao>
    </acesso:controlHiddenItem>    
    </netui-template:section>
</netui-template:template>
