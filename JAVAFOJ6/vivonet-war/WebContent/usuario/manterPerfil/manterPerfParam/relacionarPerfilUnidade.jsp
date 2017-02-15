<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormPerfilRel"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relPerfilUnidForm"/>
<bean:define id="aSistemas"      name="FormPerfilRel" property="listaSistemas"/>
<bean:define id="aSisSubSisPag"  name="FormPerfilRel" property="listaSistemasSubSistemasPaginas"/>
<bean:define id="aUnidadesRel"   name="FormPerfilRel" property="listaUnidadesRel"/>
<bean:define id="aUnidadesExist" name="FormPerfilRel" property="listaUnidadesExist"/>

<logic:equal name="FormPerfilRel" property="exibeStruts" value="sim">
    <bean:define id="aSubSistemas"   name="FormPerfilRel" property="listaSubSistemas"/>
</logic:equal>

<logic:equal name="FormPerfilRel" property="exibePagina" value="sim">
    <bean:define id="aPaginas"       name="FormPerfilRel" property="listaPaginas"/>
</logic:equal>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
<SCRIPT LANGUAGE="JavaScript">
<!--
        function transfereSelecaoLista(listaOrigem, listaDestino){
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

    function gravar(){
        var lista = document.forms[0].unidadesRel;
        //Processa gravação
        for(i = 0;i<lista.length;i++){
           document.forms[0].unidadesRel.options[i].selected =true;
        }
    }

    // Valida campos obrigatorios.
        function valida(){
            if(document.forms[0].idSistema.selectedIndex == 0){
            alert('Sistema é obrigatório, favor selecionar.');
            return false;

            }else if(document.forms[0].idSubSistema.selectedIndex == 0){
            alert('Sub-Sistema é obrigatório, favor selecionar.');
            return false;

            }else if(document.forms[0].idPagina.selectedIndex == 0){
            alert('Página é obrigatória, favor selecionar.');
            return false;

            }else if(document.forms[0].unidadesRel.options.length == 0 && document.forms[0].unidadesExist.options.length == 0){
            alert("Não existe nenhuma unidade \"existente\" ou \"relacionada\" para gravação!");
            return false;
        }
        return true;
    }

    // envia dados.
        function salvarItem(){
            if(valida()){
            gravar();
            document.forms[0].action = "salvaUnidade.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }
    }

    function carregaUnidades() {
        if ((document.forms[0].idSistema.value == "000") || (document.forms[0].idSistema.value == "")) {
            alert("É necessário escolher um sistema.");
        } else if ((document.forms[0].idSubSistema.value == "000") || (document.forms[0].idSubSistema.value == "")) {
            alert("É necessário escolher um sub-sistema.");
        } else if ((document.forms[0].idPagina.value == "000") || (document.forms[0].idPagina.value == "")) {
            alert("É necessario escolher uma página.");
        } else {
            document.forms[0].target = "_self";
            document.forms[0].action = "/FrontOfficeWeb/usuario/manterPerfil/manterPerfParam/obtemUnidades.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }
    }

    function listaSubSistema(){
            if(document.forms[0].idSistema.selectedIndex == 0){
            alert('Selecione um Sistema.');
            }else{
            document.forms[0].target = "_self";
            document.forms[0].action = "obtemSubSistemas.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }
        clearOptions();
        //Modificacao atual starts
        // esvazia o combo de paginas
        //document.forms[0].idPagina.options.length=0;
        // Testa se o elemento seleciondo é válido
        //if ((document.forms[0].idSistema.value != "000") && (document.forms[0].idSistema.value != "")) {
        //    //Essa funcao é gerada por uma chamada a um objeto do jsp chamado lista
        //    montaListaSubSistemas();
        //}
        //else {
        //    document.forms[0].idSubSistema.options.length=0;
        //}
        //Modificacao atual finish
    }

    // carrega combo de sub-sistema.
    function listaPagina() {
        // Testa se o elemento seleciondo é válido
        if ((document.forms[0].idSubSistema.value != "000") && (document.forms[0].idSubSistema.value != "")) {
            //Essa funcao é gerada por uma chamada a um objeto do jsp chamado lista
            document.forms[0].action = "listaPaginasSub.do";
            parent.mostrar_div();
            document.forms[0].submit();
            //Modificacao atual starts
            //montaListaSubSistemas();
            //Modificacao atual finish
            }else{
            document.forms[0].idSubSistema.options.length=0;
        }
        clearOptions();
    }

        function clearOptions(){
        document.forms[0].unidadesRel.options.length   = 0;
        document.forms[0].unidadesExist.options.length = 0;
    }
// -->
</SCRIPT>
    <script language="javascript" for="window" event="onload">
    <!--
            if('<bean:write name="FormPerfilRel" property="msgError" />' != ""){
            alert('<bean:write name="FormPerfilRel" property="msgError" />');
        }
        document.forms[0].idSistema.focus();
        parent.oculta_div();
    -->
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rpu_verpagina">
        <script>
            document.body.style.backgroundColor= '#ededed';
        </script>
        <form action="home.do" onSubmit="return false;" method="post">
            <!-- Monta o javascript que recarrega a lista de subsistemas -->
            <!--%=(String)request.getParameter("lista")%-->
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            <center>
            <vivo:quadro scroll="no" id="" width="720" height="83" label="Filtros para listagem de Unidades Existentes">
                <table width="100%" align="center" cellpadding="3">
                    <tr>
                        <td width="10%" class="tblEstatica_campoNome">
                            <netui:label value="Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="40%" class="tblEstatica_campoValor">
                            <html:select name="FormPerfilRel" tabindex="1" property="idSistema" style="width=250px" onchange="listaSubSistema();" indexed="0">
                                <html:option value="000">Escolha uma opção...</html:option>
                                <html:options collection="aSistemas" property="idSistema" labelProperty="dsSistema"/>
                            </html:select>
                        </td>
                        <td width="13%" class="tblEstatica_campoNome">
                            <netui:label value="Sub-Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="37%" class="tblEstatica_campoValor">
                        <html:select name="FormPerfilRel" tabindex="2" property="idSubSistema" style="width=250px" onchange="listaPagina();" indexed="0">
                            <html:option value="000">Escolha uma opção...</html:option>
                            <logic:equal name="FormPerfilRel" property="exibeStruts" value="sim">
                                <html:options collection="aSubSistemas" property="idSubSistema" labelProperty="dsSubSistema"/>
                            </logic:equal>
                        </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td width="10%" class="tblEstatica_campoNome">
                            <netui:label value="Página: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="40%" class="tblEstatica_campoValor">
                            <html:select name="FormPerfilRel" property="idPagina" tabindex="3" style="width=250px" indexed="0" onchange="clearOptions();">
                                <html:option value="000">Escolha uma opçao...</html:option>
                                <logic:equal name="FormPerfilRel" property="exibePagina" value="sim">
                                    <html:options collection="aPaginas" property="idPagina" labelProperty="nmPagina"/>
                                </logic:equal>
                            </html:select>
                        </td>
                        <td width="50%" align="left" colspan="2">
                        </td>
                    </tr>
                </table>
                <table width="100%">
                    <tr>
                        <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="usu_rpu_carregarr">
                            <img tabindex="4" onclick="carregaUnidades();" id="btSelecionar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none;cursor:hand;"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
            </vivo:quadro>
            </center>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            <table width="720" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td width="310" align="center"><b>Unidades existentes</b></td>
                    <td width="100"></td>
                    <td width="310" align="center"><b>Unidades relacionadas</b></td>
                </tr>
                <tr>
                    <td align="left" valign="top">
                        <html:select name="FormPerfilRel" property="unidadesExist" tabindex="5" multiple="true" style="width:310px;height:97px;" styleClass="SELECT" size="6" ondblclick="transfereSelecaoLista(document.forms[0].unidadesExist, document.forms[0].unidadesRel);">
                            <html:options collection="aUnidadesExist" property="idUnidade" labelProperty="nmUnidade" />
                        </html:select>
                    </td>
                    <td align="center">
                        <img tabindex="6"  id="btRightSimp1" onclick="transfereSelecaoLista(document.forms[0].unidadesExist, document.forms[0].unidadesRel);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="cursor:hand;border:none"/><br><br>
                        <img tabindex="7" id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].unidadesRel, document.forms[0].unidadesExist);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"  style="cursor:hand;border:none"/>

                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormPerfilRel" tabindex="8" property="unidadesRel" multiple="true" style="width:310px;height:97px;" styleClass="SELECT" size="6" ondblclick="transfereSelecaoLista(document.forms[0].unidadesRel, document.forms[0].unidadesExist);">
                            <html:options collection="aUnidadesRel" property="idUnidade" labelProperty="nmUnidade" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="3">
                    <acesso:controlHiddenItem nomeIdentificador="usu_rpu_salvar">
                        <img tabindex="9" vspace="5" hspace="10" id="btSalvar1" onclick="salvarItem();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;" onBlur="document.forms[0].idSistema.focus();"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
        </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>