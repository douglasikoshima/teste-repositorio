<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
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
                var a = document.getElementById("div1");
                a.style.visibility = 'visible';
                document.forms[0].inFolha.value = '1';
            }
            
            function verificaPagina(){
                if(document.forms[0].tipoItem[1].checked){
                    if(document.forms[0].subSistemaNovo.value!= "000"){
                        var a = document.getElementById("div1");
                        a.style.visibility = 'visible';
                        
                        //Lista dinamica entre subSistema e paginas correspondentes
                        montaLista();
                    }else{
                        limpaPaginas();
                    }
                }else{
                    ocultaCampos();
                }
            }
            
            // limpa conteudo do combo de paginas(Pagina Nova).
            function limpaPaginas(){
                while(document.forms[0].elements["paginaNova"].options.length > 0){
                      document.forms[0].elements["paginaNova"].options[0]     = null;  
                }    
                document.forms[0].paginaNova.options[0] = new Option("Escolha uma opção...", "000");
            }
            
            function ocultaCampos(){
                var a = document.getElementById("div1");
                a.style.visibility = 'hidden';
                document.forms[0].subSistemaNovo.value = '000';
                document.forms[0].paginaNova.value = '';
                document.forms[0].inFolha.value = '0';
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

            // salva dados.      
            function salvarItem(){
                if(valida()){
                    document.forms[0].action = "insereItem.do?sistemaId=" + document.forms[0].idSistema.value;
                    document.forms[0].target = "ifrmAbas";
                    parent.mostrar_div();
                    document.forms[0].submit(); 
                    var x = parent.document.getElementById("detalheInserir");
                    x.style.visibility = 'hidden';
                    var b = parent.document.getElementById("bg");
                    b.style.visibility = 'hidden';
                } 
            } 
        </script>
        <script language="javascript" for="window" event="onload">
            if('<bean:write name="FormArvore" property="msgError" />' != ""){
                alert('<bean:write name="FormArvore" property="msgError" />');
            }                
            parent.oculta_div();
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_iim_verpagina">
        <form action="insereItem.do" method="post">
        <html:hidden name="FormArvore" property="idSubSistemaEscolhido"/>
        <html:hidden name="FormArvore" property="idSistema"/>
        <html:hidden name="FormArvore" property="idItemMenuPai"/>
        <html:hidden name="FormArvore" property="idItemMenu"/>
        <html:hidden name="FormArvore" property="inFolha"/>
        <%=(String)request.getSession().getAttribute("lista_menu_pagina")%>
        <table width="532" height="285">
            <tr>
                <td align="center">
                <table width="100%" border="0" cellspacing="2" cellpadding="2" align="center" class="tbl_bgGray">
                    <tr>
                        <td width="30%" class="tblEstatica_campoNome">
                            <netui:label value="Nome do Sistema: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" align="left" style="tblEstatica_campoValor">&nbsp;
                            <bean:write name="FormArvore" property="dsSistema"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Criar na Pasta: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" align="left" style="tblEstatica_campoValor">&nbsp;
                            <bean:write name="FormArvore" property="nmMenu"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Tipo do item: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" class="tblEstatica_campoValor">&nbsp;
                        <logic:equal name="FormArvore" property="inFolha" value="1">
                            <input type="radio" value="P" name="tipoItem" class="radio" onclick="ocultaCampos()">Pasta &nbsp;
                            <input type="radio" value="F" name="tipoItem" class="radio" onclick="exibeCampos()" checked>Folha
                        </logic:equal>
                        <logic:equal name="FormArvore" property="inFolha" value="0">
                            <input type="radio" value="P" name="tipoItem" class="radio" onclick="ocultaCampos()" checked>Pasta &nbsp;
                            <input type="radio" value="F" name="tipoItem" class="radio" onclick="exibeCampos()">Folha
                        </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Nome do Item: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" align="left">&nbsp;
                            <html:text name="FormArvore" property="nmMenuNovo" styleClass="input" size="50" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tblEstatica_campoNome">
                            <netui:label value="Hint do Item: " styleClass="tblEstatica_campoNome" />
                        </td>
                        <td width="70%" align="left">&nbsp;
                            <html:text name="FormArvore" property="dsHintNovo" styleClass="input" size="50" maxlength="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" class="tblEstatica_campoNome">
                            <netui:label value="Visível: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="70%" align="left">&nbsp;
                            <html:select name="FormArvore" property="visivelNovo" style="width:250" value="" styleClass="SELECT" size="1" >
                                <html:option value="">Escolha uma opção...</html:option>
                                <html:option value="Sim"/>
                                <html:option value="Não"/> 
                            </html:select>
                        </td>
                    </tr>
                    <tr>     
                        <td colspan="2">                   
                            <div id="div1" style="visibility:'hidden';">
                                <table width="100%" border="0" cellspacing="0" cellpadding="2" align="center">
                                    <tr>                        
                                        <td width="30%" class="tblEstatica_campoNome">
                                            <netui:label value="Sub-Sistema: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" align="left">&nbsp;
                                            <html:select name="FormArvore" property="subSistemaNovo" style="width:250" value="" styleClass="SELECT" size="1" onchange="JavaScript:verificaPagina();">
                                                <html:options collection="subSistema" property="idSubSistema" labelProperty="dsSubSistema"></html:options>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%" class="tblEstatica_campoNome">
                                            <netui:label value="Página: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" align="left">&nbsp;
                                            <select name="paginaNova">
                                                <option value="">Escolha uma opção...</option> 
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <acesso:controlHiddenItem nomeIdentificador="usu_iim_salvar">
                                <img id="btSalvar1" onclick="salvarItem();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand"/>
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
        </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
