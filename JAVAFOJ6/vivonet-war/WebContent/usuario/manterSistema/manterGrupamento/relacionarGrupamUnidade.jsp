<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="RelGrupamForm"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGrupamForm"/>
<bean:define id="aSistemas"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGrupamForm.listaSistemas"/>
<bean:define id="aSubSistemas"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGrupamForm.listaSubSistemas"/>
<bean:define id="salvaUnidade"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGrupamForm.salvaUnidadeGrupam"/>
<bean:define id="aPaginas"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGrupamForm.listaPaginas"/>
<bean:define id="aUnidadesRel"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGrupamForm.listaUnidadesRel"/>
<bean:define id="aUnidadesExist" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relGrupamForm.listaUnidadesExist"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
        <SCRIPT LANGUAGE="JavaScript">
        <!--
            // Simple rollover function which replaces the image.src with the passed image
            function swapImage(control, image) {
              control.src = image;
            }

            function listaSubSistema() {
                limpaUnidades();
                // esvazia o combo de paginas
                document.forms[0].idPagina.options.length=0;
                
                // Testa se o elemento seleciondo é válido
                if ((document.forms[0].idSistema.value != "000") && (document.forms[0].idSistema.value != "")) {
                    //Essa funcao é gerada por uma chamada a um objeto do jsp chamado lista
                    montaListaSubSistemas();
                }
                else {
                    document.forms[0].idSubSistema.options.length=0;
                }    
            }
            
            function listaPagina() {
                limpaUnidades();
                // Testa se o elemento seleciondo é válido
                if ((document.forms[0].idSubSistema.value != "000") && (document.forms[0].idSubSistema.value != "")) {
                    //Essa funcao é gerada por uma chamada a um objeto do jsp chamado lista
                    document.forms[0].action = "listaPaginasSub.do";
                    mostrar_div();
                    document.forms[0].submit();
                    montaListaSubSistemas();
                }
                else{
                    document.forms[0].idSubSistema.options.length=0;
                }
            }
            
            // Esvazia o lista de unidades existentes e relacionadas
            function limpaUnidades() 
            {
                while(document.forms[0].elements["unidadesExist"].options.length > 0)
                {
                      document.forms[0].elements["unidadesExist"].options[0]     = null;  
                }    

                while(document.forms[0].elements["unidadesRel"].options.length > 0)
                {
                      document.forms[0].elements["unidadesRel"].options[0]     = null;  
                }    

            }
            
            // lista as unidades existentes e relacionadas
            function listaUnidades() 
            {   
                if(valida())
                {
                    document.forms[0].action = "listaRelacaoUnid.do";
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
            
           function valida()
            {
                if ((document.forms[0].idSistema.value == "000") 
                    || (document.forms[0].idSistema.value == "")) 
                {
                    alert("É necessário escolher um Sistema.");
                    return false;
                    
                } else if ((document.forms[0].idSubSistema.value == "000") 
                    || (document.forms[0].idSubSistema.value == "")) 
                {
                    alert("É necessário escolher um Sub-Sistema.");
                    return false;
                    
                } else if ((document.forms[0].idPagina.value == "000") 
                    || (document.forms[0].idPagina.value == "")) 
                {
                    alert("É necessario escolher uma Página.");
                    return false;
                    
                }
                return true;   

            }

            function transfereSelecaoLista(listaOrigem, listaDestino) {
                var i;
                for(i = 0; i<listaOrigem.options.length; i++)
                    if(listaOrigem.options[i].selected)
                        if ((listaDestino.options.length == 1) && (trim(listaDestino.options[0].text) == "")) {
                            listaDestino.options[0] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        } else {
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        }
                        
                for(i = listaOrigem.options.length-1; i>=0; i--)
                    if(listaOrigem.options[i].selected)
                        listaOrigem.options[i] = null;
        
            }
        
            function gravar() {
                var lista = document.forms[0].unidadesRel;
                //Processa gravação
                for(i = 0;i<lista.length;i++){
                   document.forms[0].unidadesRel.options[i].selected = true; 
                }
                
            }


            // salva as unidades relacionadas
            function salvaUnidadesRel() 
            {
                if(valida())
                {
                    gravar();
                    document.forms[0].action = "relacionaUnid.do";
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
            
            function enviaVoltar()
            {
                document.forms[0].action = 'begin.do?isMenu=false';
                mostrar_div();
                document.forms[0].submit();
            }

        // -->
        </SCRIPT>

        <script language="javascript" for="window" event="onload">
        <!--   
            if('<bean:write name="RelGrupamForm" property="msgError" />' != "")
            {
                alert('<bean:write name="RelGrupamForm" property="msgError" />');
            }
            oculta_div();
        -->
        </script> 

    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rgu_verpagina">
        
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
        <script>        
            mostrar_div();
        </script>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <!-- Monta o javascript que recarrega a lista de subsistemas -->
        <%=(String)request.getAttribute("lista")%>
        <vivo:sessao id="qdMain" height="468" width="790" label="Sistemas" operacoes="Relacionar Grupamento às Unidades">
        <form action="listaRelacaoUnid.do" method="post">
        
        <table border="0" cellspacing="0" cellpadding="0"><tr><td height="6"></td></tr></table>
        <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tbl_bgGray" align="center">
            <tr><td height="2"></td></tr>
            <tr>
                <td>
                <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center">
                    <tr><td height="2"></td></tr>
                    <tr>
                        <td width="20%" class="tblEstatica_campoNome">
                            <netui:label value="Nome do Grupamento: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="80%" class="tblEstatica_campoValor">
                            <bean:write name="RelGrupamForm" property="dsGrupamento"/>
                        </td>
                    </tr>
                    <tr><td height="4"></td></tr>
                </table>
                </td>
            </tr>
            <tr>
                <td>
                <table width="97%" border="0" cellspacing="2" cellpadding="1" align="center" class="tbl_bgBlue">
                    <tr><td colspan="4" class="tblEstatica_campoNome" align="center">Filtros para listagem de Unidades Existentes</td></tr>
                    <tr><td height="3"></td></tr>
                    <tr>
                        <td width="10%" class="tblEstatica_campoNome">
                            <netui:label value="Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="40%" class="tblEstatica_campoValor">
                            <html:select name="RelGrupamForm" property="idSistema" style="width=250px" onchange="listaSubSistema();">
                                <html:option value="000">Escolha uma opção...</html:option>
                                <html:options collection="aSistemas" property="idSistema" labelProperty="dsSistema"/> 
                            </html:select>
                        </td>
                        <td width="13%" class="tblEstatica_campoNome">
                            <netui:label value="Sub-Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="37%" class="tblEstatica_campoValor">
                        <logic:equal name="RelGrupamForm" property="exibeStruts" value="0">
                            <select name="idSubSistema" style="width=250px" onchange="listaPagina();">
                                <option value="">
                            </select>
                        </logic:equal>
                        <logic:equal name="RelGrupamForm" property="exibeStruts" value="1">
                            <html:select name="RelGrupamForm" property="idSubSistema" style="width=250px" onchange="listaPagina();">
                                <html:option value="000">Escolha uma opçao...</html:option>
                                <html:options collection="aSubSistemas" property="idSubSistema" labelProperty="dsSubSistema"/> 
                            </html:select>
                        </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <td width="10%" class="tblEstatica_campoNome">
                            <netui:label value="Página: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="40%" class="tblEstatica_campoValor">
                            <html:select name="RelGrupamForm" property="idPagina" style="width=250px" onchange="limpaUnidades();">
                                <html:option value="000">Escolha uma opçao...</html:option>
                                <html:options collection="aPaginas" property="idPagina" labelProperty="nmPagina"/> 
                            </html:select>
                        </td>
                        <td width="50%" align="right" colspan="2">
                            <acesso:controlHiddenItem nomeIdentificador="usu_rgu_pesq">
                                <img id="btSelecionar" onclick="listaUnidades();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none;cursor:hand;"/>
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                    <tr><td height="4" colspan="3">
                    </td></tr>
                </table>
                </td>
            </tr>
            <tr><td height="4"></td></tr>
            <tr>
                <td>
                <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center">
                    <tr>
                        <td align="center" colspan="2">
                            <table width="730" border="0" cellspacing="0" cellpadding="0" align="center">
                                <tr>
                                    <td width="295" align="center"><b>Unidades existentes</b></td>
                                    <td width="140">&nbsp;</td>
                                    <td width="295" align="center"><b>Unidades relacionadas</b></td>
                                </tr>
                                <tr>
                                    <td>
                                        <html:select name="RelGrupamForm" multiple="true" property="unidadesExist" style="width:295px;" size="8" ondblclick="transfereSelecaoLista(document.forms[0].unidadesExist, document.forms[0].unidadesRel);">
                                            <html:options collection="aUnidadesExist" property="idUnidade" labelProperty="dsUnidade"/> 
                                        </html:select>
                                    </td>
                                    <td align="center">
                                        <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].unidadesExist, document.forms[0].unidadesRel);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                        <br/><br/>
                                        <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].unidadesRel, document.forms[0].unidadesExist);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                                    </td>
                                    <td>
                                        <html:select name="RelGrupamForm" multiple="true" property="unidadesRel" style="width:295px;" size="8" ondblclick="transfereSelecaoLista(document.forms[0].unidadesRel, document.forms[0].unidadesExist);">
                                            <html:options collection="aUnidadesRel" property="idUnidade" labelProperty="dsUnidade"/> 
                                        </html:select>
                                    </td>
                                </tr>
                                <tr><td height="6"></td></tr>
                            </table>
                            <div align="right">
                            <acesso:controlHiddenItem nomeIdentificador="usu_rgu_gravar">
                            <img vspace="10" onClick="salvaUnidadesRel(); return false" id="btSalvar1" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                            </acesso:controlHiddenItem>
                            </div>
                        </td>
                    </tr>
                    <tr><td height="4"></td></tr>
                </table>
                </td>
            </tr>
        </table>
        <table width="770">
            <tr>
                <td>
                    <img vspace="10" hspace="5" onClick="enviaVoltar();" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
                </td>
                <td align="right">
                    
                </td>
            </tr>
            <tr><td height="2"></td></tr>
        </table>
        
        </form>
        </vivo:sessao>
    
       
    
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
