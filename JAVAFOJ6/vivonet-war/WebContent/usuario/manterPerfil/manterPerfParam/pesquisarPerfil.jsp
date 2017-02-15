<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<acesso:controlInitEnv/>
<bean:define id="FormPerfil"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm"/>
<bean:define id="perfilUsuarioVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm.perfilUsuarioVO"/>
<bean:define id="arrayPerfilVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm.arrayPerfisVO"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Perfis"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">

    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_pp_verpagina">
    <script language="javascript">
    <!--
        function removeItem(id) {
            document.forms[0].target = '';
            document.forms[0].idPerfil.value = id;
            if (window.confirm("Confirma remoção do item?")) {
                mostrar_div();
                document.forms[0].action = 'removePerfil.do'; 
                document.forms[0].submit();
                //return true;
            }else{
                return false;
            }
        }
        
        function lista() 
        {
            document.forms[0].target = '';
            document.forms[0].dsPerfil.value = trim(document.forms[0].dsPerfil.value);
            document.forms[0].action = "listaPerfis.do";
            mostrar_div();
            document.forms[0].submit();
        }
        
        function limpar() 
        {
            document.forms[0].target = '';
            document.forms[0].dsPerfil.value = "";
            document.forms[0].idPerfil.value = "";
            document.forms[0].dsPerfil.focus();
        }
        
        function alteraPerfil(idArray) 
        {
            divAlteraPerfil.style.display = '';
            document.forms[0].target = "ifrmAlteraPerfil";
            document.forms[0].action = "alterarDsPerfil.do?idArray="+idArray;
            mostrar_div();
            document.forms[0].submit();            
        }
        
        function incluiPerfil() {
            divIncluiPerfil.style.display = '';
            document.forms[0].target = "ifrmIncluiPerfil";
            document.forms[0].action = "alterarDsPerfil.do?acao=incluir";
            mostrar_div();
            document.forms[0].submit();
            //ifrmIncluiPerfil.src = 'IncluirAlterarPerfil.jsp';
        }
        
        function listaEnter(ev){
            if(ev.keyCode == 13)
                lista();
            else
                return false;    
        }
        
        function editarParametros(id){
            document.forms[0].idPerfil.value = id;
            document.forms[0].action = 'editarRelPerfil.do?perfilID='+id;            
            document.forms[0].target = '';
            mostrar_div();
            document.forms[0].submit();
        }
        
    // -->
    </script>  
    	<!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>   
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            
            <vivo:sessao id="qdMain" height="460" width="790" label="Perfis" operacoes="Manutenção">
            
            <form action="listaPerfis.do" onSubmit="return false;" method="post">
                <html:hidden  name="FormPerfil" property="parametroPesquisa"/>
                <html:hidden name="FormPerfil" property="idPerfil"/>
                            
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                        
                                <table width="760" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                                    <tr>
                                        <td>
                                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                            <table width="740" border="0" cellspacing="0" cellpadding="0" align="center" class="tbl_bgBlue">
                                                <tr><td height="6"></td></tr>
                                                <tr>
                                                    <td width="15%">&nbsp;
                                                        <b><netui:label value="Nome do Perfil:" styleClass="tblEstatica_campoNome"/></b>
                                                    </td>
                                                    <td width="47%" align="left">
                                                        <html:text name="FormPerfil" property="dsPerfil" tabindex="1" style="width:440px" styleClass="input" maxlength="254" onkeypress="listaEnter(event);" onkeyup="checaStrEspecial(this);"/>                    
                                                    </td>
                                                    <td width="10%" align="center">
                                                        <img style="border:none;cursor:hand;" tabindex="2" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                                    </td>
                                                    <td width="18%" align="left">&nbsp;
                                                    <acesso:controlHiddenItem nomeIdentificador="usu_pp_pesq">
                                                        <img id="btPesquisar" tabindex="3" onclick="lista();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="cursor:hand;border:none;"/>
                                                    </acesso:controlHiddenItem>
                                                    </td>
                                                </tr>
                                                <tr><td height="6"></td></tr>
                                            </table>
                                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                        </td>
                                    </tr>
                                    <logic:notEqual name="perfilUsuarioVO" property="idPerfil" value="-1"> 
                                    <tr><td height="4"></td></tr>
                                    <tr>
                                        <td width="20%" class="tblEstatica_campoNome" align="center">
                                        
                                        <logic:equal name="FormPerfil" property="indicativoOperacao" value="Resultado">
                                            <vivo:tbTable selectable="true" layoutWidth="705" layoutHeight="250" tableWidth="700" styleId="tableTitle" sortable="true">
                                                <vivo:tbHeader>
                                                    <vivo:tbHeaderColumn align="left"   width="" type="string">Perfis encontrados</vivo:tbHeaderColumn>
                                                    <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                                    <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                                    <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                                </vivo:tbHeader>
                                                <vivo:tbRows>
                                                    <logic:iterate name="FormPerfil" property="arrayPerfisVO.perfilUsuarioVOArray" id="perfilUsuarioVO" indexId="idx">
                                                        <vivo:tbRow key="linha1">
                                                            <vivo:tbRowColumn>
                                                                <bean:write name="perfilUsuarioVO" property="dsPerfil"/>
                                                            </vivo:tbRowColumn>                                 
                                                            <vivo:tbRowColumn>
                                                                <acesso:controlHiddenItem nomeIdentificador="usu_pp_alterar">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alteraPerfil(<%=idx%>);" border="0" alt="Alterar Perfil">
                                                                </acesso:controlHiddenItem>
                                                            </vivo:tbRowColumn>
                                                            <vivo:tbRowColumn>
                                                                <acesso:controlHiddenItem nomeIdentificador="usu_pp_remove">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onclick="return removeItem('<bean:write name="perfilUsuarioVO" property="idPerfil"/>');" alt="Excluir Perfil">
                                                                </acesso:controlHiddenItem>
                                                            </vivo:tbRowColumn>
                                                            <vivo:tbRowColumn>
                                                                <acesso:controlHiddenItem nomeIdentificador="usu_pp_altpam">
                                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" onclick="editarParametros('<bean:write name='perfilUsuarioVO' property='idPerfil'/>');" border="0" alt="Alterar Parâmetros">
                                                                </acesso:controlHiddenItem>
                                                            </vivo:tbRowColumn>
                                                        </vivo:tbRow>
                                                    </logic:iterate>
                                                </vivo:tbRows>
                                            </vivo:tbTable>
                                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                                                <table width="720" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                                                    <tr>
                                                        <td valign="middle"><b>&nbsp;Legendas:</b></td>
                                                        <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Perfil</td>
                                                        <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Excluir Perfil</td>
                                                        <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif"   align="absmiddle"> &nbsp;Alterar Parâmetros</td>
                                                    </tr>
                                                </table>
                                                
                                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                        </logic:equal>
                                        <logic:equal name="FormPerfil" property="indicativoOperacao" value="Vazio">
                                            Não foi encontrado nenhum perfil com a descrição fornecida.
                                        </logic:equal>
                                    </tr>
                    </logic:notEqual>
                    <tr>
                        <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="usu_pp_salvar">
                                <img id="btIncluir" tabindex="4" onClick="incluiPerfil();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>

                </table>
                <table>
                    <tr>
                        <td>
                            <img id="voltar" tabindex="5" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;" vspace="5" hspace="5"  onBlur="document.forms[0].dsPerfil.focus();"/>
                        </td>
                    </tr>
                </table>
                
                </form>
                    
                    <vivo:quadroFlutuante onclick="" label="Alterar Perfil" scroll="false" src="" idIframe="ifrmAlteraPerfil" id="divAlteraPerfil" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
                    <vivo:quadroFlutuante onclick="" label="Incluir Perfil" scroll="false" src="" idIframe="ifrmIncluiPerfil" id="divIncluiPerfil" spacesLeft="150" height="80" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>

    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormPerfil" property="msgError" />' != ""){
            alert('<bean:write name="FormPerfil" property="msgError" />');
        }
        oculta_div();
        document.forms[0].dsPerfil.focus();
    -->
    </script> 
            </vivo:sessao>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
