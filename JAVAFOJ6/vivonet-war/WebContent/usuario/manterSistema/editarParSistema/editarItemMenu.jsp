<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormArvore"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvore"/>
<bean:define id="subSistema"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvore.subSistemaPaginasVO"/>
<bean:define id="subSistemaListaPaginas"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvore.subSistemaListaPaginas"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript">
            function exibeCampos(){
            }

            function ocultaCampos(){
                document.forms[0].subSistemaNovo.value = '000';
                document.forms[0].paginaNova.value = '';
            }

            function montaSelecao(){
                if(document.forms[0].subSistemaNovo){
                    for(var i=0;i<document.forms[0].subSistemaNovo.options.length;i++){
                        if(document.forms[0].subSistemaNovo.options[i].value == '<bean:write name="FormArvore" property="idSubSistema"/>'){
                                document.forms[0].subSistemaNovo.options[i].selected=true;
                            }
                        }
                        verificaPagina();
                        montaSelecaoPagina();
                    }                        
            }
                            
            // limpa conteudo do combo de paginas(Pagina Nova).
            function limpaPaginas(){
                while(document.forms[0].elements["paginaNova"].options.length > 0){
                      document.forms[0].elements["paginaNova"].options[0]     = null;  
                }    
                document.forms[0].paginaNova.options[0] = new Option("Selecione um Subsistema", "000");
            }                
            
            function montaSelecaoPagina(){
                if(document.forms[0].paginaNova){
                    for(var i=0;i<document.forms[0].paginaNova.options.length;i++){
                        if(document.forms[0].paginaNova.options[i].value == '<bean:write name="FormArvore" property="idPagina"/>'){
                            document.forms[0].paginaNova.options[i].selected=true;
                        }
                    }
                }
            }

            function verificaPagina(){
                if(document.forms[0].tipoItem[1].checked){
                    if(document.forms[0].subSistemaNovo.value!= "000"){
                        //Lista dinamica entre subSistema e paginas correspondentes
                        montaLista();
                    }else{
                        limpaPaginas();
                    }
                }
            }
            
            // Válida campos.
            function valida(){
                if(document.forms[0].tipoItem[0].checked == false && document.forms[0].tipoItem[1].checked == false){
                    alert("Selecione tipo de item.");
                    return false;

                }else if(document.forms[0].nmMenuNovo.value == ""){
                    alert("Digite um nome para o item.");
                    return false;

                }else if(document.forms[0].dsHintNovo.value == ""){
                    alert("Digite um hint para o item.");
                    return false;

                }else if(document.forms[0].visivelNovo.value == ""){
                    alert("Selecione uma opção para Visivel.");
                    return false;
                }
                // Se for FOLHA = "F".
                else if(document.forms[0].tipoItem[1].checked){
                    if(document.forms[0].subSistemaNovo.value == "000"){
                        alert("Selecione um Subsistema.");
                        return false;
                    }else if(document.forms[0].paginaNova.value == "" || document.forms[0].paginaNova.value == "000"){
                        alert("Selecione uma Página.");
                        return false;
                    }
                }
                return true;
            }
            
            function salvarItem(){
                if(valida()){
                    document.forms[0].action = "salvaItemEditado.do?sistemaId=" + document.forms[0].idSistema.value;
                    document.forms[0].target = "ifrmAbas";
                    parent.mostrar_div();
                    document.forms[0].submit(); 
                    var x = parent.document.getElementById("detalheEditar");
                    x.style.visibility = 'hidden';
                    var b = parent.document.getElementById("bg");
                    b.style.visibility = 'hidden';
                }                    
            }
        </script>
        <SCRIPT FOR=window EVENT="onload" LANGUAGE="JScript">
            parent.parent.oculta_div();
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_eim_verpagina">
        <form action="salvaItemEditado.do" method="post">
        <html:hidden name="FormArvore" property="idSubSistemaEscolhido"/>
        <html:hidden name="FormArvore" property="idSistema"/>
        <html:hidden name="FormArvore" property="idItemMenuPai"/>
        <html:hidden name="FormArvore" property="inFolha"/>
        <html:hidden name="FormArvore" property="idItemMenu"/>
        <%=(String)request.getSession().getAttribute("lista_menu_pagina")%>
        <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
            <tr>
                <td height="4"><br><br></td>
            </tr>
            <tr align="center">
                <td align="center">
                <table width="100%" border="0" cellspacing="2" cellpadding="2" class="tbl_bgGray">
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Nome do Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td align="left" style="tblEstatica_campoValor">&nbsp;
                            <bean:write name="FormArvore" property="dsSistema"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Criar na Pasta: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td align="left" style="tblEstatica_campoValor">&nbsp;
                            <bean:write name="FormArvore" property="nmMenu"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Tipo do item: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td align="left">&nbsp;
                        <logic:equal name="FormArvore" property="inFolha" value="1">
                            <input type="radio" value="P" name="tipoItem" class="radio" onclick="ocultaCampos()" disabled>Pasta &nbsp;
                            <input type="radio" value="F" name="tipoItem" class="radio" onclick="exibeCampos()" checked disabled>Folha
                        </logic:equal>
                        <logic:equal name="FormArvore" property="inFolha" value="0">
                            <input type="radio" value="P" name="tipoItem" class="radio" onclick="ocultaCampos()" checked disabled>Pasta &nbsp;
                            <input type="radio" value="F" name="tipoItem" class="radio" onclick="exibeCampos()" disabled>Folha
                        </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Nome do Item: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td align="left">&nbsp;
                            <html:text name="FormArvore" property="nmMenuNovo" style="input" size="50" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Hint do Item: " styleClass="tblEstatica_campoNome" />
                        </td>
                        <td align="left">&nbsp;
                            <html:text name="FormArvore" property="dsHintNovo" style="input" size="50" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Visível: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td align="left">&nbsp;
                            <logic:equal name="FormArvore" property="inVisibilidade" value="1">
                                <html:select name="FormArvore" property="visivelNovo" style="width:250" value="Sim" styleClass="SELECT" size="1" >
                                <html:option value="Sim" /> <html:option value="Não"/> </html:select>
                            </logic:equal>
                            <logic:notEqual name="FormArvore" property="inVisibilidade" value="1">
                                <html:select name="FormArvore" property="visivelNovo" style="width:250" value="Não" styleClass="SELECT" size="1" >
                                <html:option value="Sim" /> <html:option value="Não"/> </html:select>
                            </logic:notEqual>
                        </td>
                    </tr>
                    <logic:equal name="FormArvore" property="inFolha" value="1">
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Sub-Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td align="left">&nbsp;
                            <html:select name="FormArvore" property="subSistemaNovo" style="width:250" value="" styleClass="SELECT" size="1" onchange="JavaScript:verificaPagina();">
                                <html:options collection="subSistema" property="idSubSistema" labelProperty="dsSubSistema"></html:options>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Página: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td align="left">&nbsp;
                            <select name="paginaNova">
                                <option value="">Escolha uma opção...</option> 
                            </select>
                        </td>
                    </tr>
                    </logic:equal>
                    <tr>
                        <td colspan="2" align="center">
                            <acesso:controlHiddenItem nomeIdentificador="usu_eim_salvar">
                                <img id="btSalvar1" onclick="salvarItem();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand"/>
                            </acesso:controlHiddenItem>
                            <html:hidden name="FormArvore" property="idSubSistemaEscolhido" />
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table>
        <table><tr><td height="4"></td></tr></table>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript"> 
            montaSelecao();
            parent.oculta_div();
        </SCRIPT>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
    </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
