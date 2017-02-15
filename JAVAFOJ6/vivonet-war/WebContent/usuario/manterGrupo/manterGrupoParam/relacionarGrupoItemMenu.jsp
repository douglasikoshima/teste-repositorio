<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormGrupo"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm"/>
<bean:define id="listaSistemas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.listaSistemas"/>
<logic:equal name="FormGrupo" property="exibeSubSistema" value="sim">
    <bean:define id="listaSubSistemas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.listaSubSistemas"/>
</logic:equal>
<logic:equal name="FormGrupo" property="exibeItem" value="sim">
    <bean:define id="listaItensExistentes" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.itemMenuExistentesVO"/>
    <bean:define id="listaItensRelacionados" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.itemMenuRelacionadosVO"/>
</logic:equal>
<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
<script language="javascript">
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
        var lista = document.forms[0].arrayItensRelacionados;
        //Processa gravação
        for(i = 0;i<lista.length;i++){
           document.forms[0].arrayItensRelacionados.options[i].selected =true; 
        }
        
    }
    
    function salvarItem() {
      //  if(document.forms[0].arrayItensRelacionados.length >0){
                gravar();
                document.forms[0].action = "salvaItem.do";
                parent.mostrar_div();
                document.forms[0].submit(); 
      //  }else{
       //     alert("Não há item a ser salvo!");
      //  }      
    }
        
    function carregaSubSistemas(){
                if(document.forms[0].idSistema.selectedIndex == 0){
            alert('Selecione um Sistema.');
                }else{
            document.forms[0].target = "_self";
            document.forms[0].action = "obtemSubSistemas.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }
    }
    
    function carregaItemMenu(){
                if(document.forms[0].idSistema.selectedIndex == 0){
            alert('Selecione um Sistema.');
            
                }else if(document.forms[0].idSubSistema.selectedIndex == 0 || document.forms[0].idSubSistema.value == ''){
            alert('Selecione um Sub Sistema.');        
            
                }else{
            document.forms[0].target = "_self";
            document.forms[0].action = "obtemItemMenu.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }   
    }
</script>
<script FOR=window EVENT=onload LANGUAGE="JScript">
    parent.oculta_div();
            if('<bean:write name="FormGrupo" property="msgError" />' != ""){
        alert('<bean:write name="FormGrupo" property="msgError" />');
    }
</script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rgi_verpagina">
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
        <form action="salvaItem.do" method="post">
            <table align="center" width="100%" border="0" cellspacing="1" cellpadding="1">
                <tr align="left">
                </tr>
                <tr><td height="2"></td></tr>
                <tr>
                    <td>
                    <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
                        <tr><td height="2"></td></tr>
                        <tr>
                            <td width="25%" class="tblEstatica_campoNome">
                                <netui:label value="Nome do Sistema: " styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="75%" colspan="2">
                                <html:select name="FormGrupo" property="idSistema"  style="width:250" styleClass="SELECT" size="1" onchange="carregaSubSistemas();">
                                    <html:option value="000">Escolha uma opção...</html:option>
                                    <html:options collection="listaSistemas" property="idSistema" labelProperty="dsSistema" /> 
                                </html:select>
                        </tr>
                        <tr><td height="2"></td></tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <logic:equal name="FormGrupo" property="exibeSubSistema" value="sim">
                            <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
                            <tr><td height="2"></td></tr>
                            <tr>
                                <td width="25%" class="tblEstatica_campoNome">
                                    <netui:label value="Nome do Subsistema: " styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="75%" colspan="2">
                                    <html:select name="FormGrupo" property="idSubSistema"  style="width:250" styleClass="SELECT" size="1" onchange="carregaItemMenu();">
                                        <html:option value="000">Escolha uma opção...</html:option>
                                        <html:options collection="listaSubSistemas" property="idSubSistema" labelProperty="dsSubSistema" /> 
                                    </html:select>
                                </td>
                            </tr>
                            <tr><td height="2"></td></tr>
                        </table>
                    </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td>
                    <logic:equal name="FormGrupo" property="exibeItem" value="sim">
                        <table width="720" cellpadding="0" cellspacing="0" align="center">
                            <tr>
                                <td width="310" align="center"><b>Itens existentes</b></td>
                                <td width="100"></td>
                                <td width="310" align="center"><b>Itens relacionados</b></td>
                            </tr>
                            <tr>
                                <td align="left" valign="top">
                                    <html:select name="FormGrupo" property="arrayItensExistentes" multiple="true" style="width:310px;height:110px;" ondblclick="transfereSelecaoLista(document.forms[0].arrayItensExistentes, document.forms[0].arrayItensRelacionados);">
                                        <html:options collection="listaItensExistentes" property="idItemMenu" labelProperty="nmMenu" /> 
                                    </html:select>
                                </td>
                                <td align="center">
                                    <img  id="btRightSimp1" onclick="transfereSelecaoLista(document.forms[0].arrayItensExistentes, document.forms[0].arrayItensRelacionados);" border="0" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="cursor:hand;border:none;"/><br><br>
                                    <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].arrayItensRelacionados, document.forms[0].arrayItensExistentes);" border="0" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"  style="cursor:hand;border:none;" />
                                </td>
                                <td align="left" valign="top">
                                    <html:select name="FormGrupo" property="arrayItensRelacionados" multiple="true" style="width:310px;height:110px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].arrayItensRelacionados, document.forms[0].arrayItensExistentes);">
                                        <html:options collection="listaItensRelacionados" property="idItemMenu" labelProperty="nmMenu" /> 
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" colspan="3">
                                <acesso:controlHiddenItem nomeIdentificador="usu_rgi_salvar">
                                    <img id="btSalvar1" onclick="salvarItem();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" vspace="10" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="cursor:hand;border:none;" />
                                </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
                    </logic:equal>
                    <html:hidden name="FormGrupo" property="idGrupo"/>
                    <html:hidden name="FormGrupo" property="exibeSubSistema" value="nao"/>
                    <html:hidden name="FormGrupo" property="exibeItem" value="nao"/>
                    </td>
                </tr>
                <tr><td height="4"></td></tr>
            </table>
            <script language="javascript" type="text/javascript">
                idGrupo = parent.document.forms[0].idGrupo.value;
                if (parent.contadorChamada==0 && document.forms[0].idSistema.length==2){
                    parent.contadorChamada = 1;
                    window.top.frameApp.location.href = "/FrontOfficeWeb/usuario/manterGrupo/manterGrupoParam/begin.do?idGrupo="+idGrupo+"&abaSource=bt03";
                }
                parent.abaSelected(parent.btAba, parent.bt03);
            </script>
        </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>