<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="FormItemMenu"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formItemMenu"/>
<bean:define id="listaSistemas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formItemMenu.listaSistemas"/>
<logic:equal name="FormItemMenu" property="exibeSubSistema" value="sim">
    <bean:define id="listaSubSistemas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formItemMenu.listaSubSistemas"/>
</logic:equal>
<logic:equal name="FormItemMenu" property="exibeItem" value="sim">
    <bean:define id="listaItensExistentes" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formItemMenu.itemMenuExistentesVO"/>
    <bean:define id="listaItensRelacionados" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formItemMenu.itemMenuRelacionadosVO"/>
</logic:equal>
<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
<script language="javascript">
        function transfereSelecaoLista(listaOrigem, listaDestino){
        var i;
        for(i = 0; i<listaOrigem.options.length; i++)
            if(listaOrigem.options[i].selected)
                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                
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
    
        function valida(){
        var form = document.forms[0];
            if(form.idSistema.selectedIndex==0){
            alert('Selecione um Sistema.');
            form.idSistema.focus();
            return false;
            }else if(form.idSubSistema.selectedIndex == 0){
            alert('Selecione um SubSistema.');
            form.idSubSistema.focus();
            return false;
        }
        return true;
    }
    
        function salvarItem(){
            if(valida()){
                if(document.forms[0].arrayItensExistentes.options.length == 0 && document.forms[0].arrayItensRelacionados.options.length == 0){
                alert("Não existe nenhum item \"existente\" ou \"relacionada\" para gravação!");
                return false;
            }  
            gravar();
            document.forms[0].action = "salvaItem.do?userId=<%=request.getParameter("userId")%>";
            parent.mostrar_div();
            document.forms[0].submit(); 
       }      
    }
        
        function carregaSubSistemas(){
            if(document.forms[0].idSistema.selectedIndex != 0){
            document.forms[0].target = "_self";
            document.forms[0].action = "obtemSubSistemas.do?userId=<%=request.getParameter("userId")%>";
            parent.mostrar_div();
            document.forms[0].submit();
            }else{
                while(document.forms[0].elements["idSubSistema"].options.length>1){
              document.forms[0].elements["idSubSistema"].options[1]     = null;  
          }         
            document.forms[0].elements["idSubSistema"].options[0] = new Option('Escolha uma opção...' , '000');
        }
    }
    
        function carregaItemMenu(){
            if(valida()){
            document.forms[0].target = "_self";
            document.forms[0].action = "obtemItemMenu.do?userId=<%=request.getParameter("userId")%>";
            parent.mostrar_div();
            document.forms[0].submit();
        }
    }
    
        function clearOptions(){
            if(document.forms[0].arrayItensExistentes && document.forms[0].arrayItensRelacionados){
            document.forms[0].arrayItensExistentes.options.length   = 0; 
            document.forms[0].arrayItensRelacionados.options.length = 0;
        }        
    }
</script>
    <script language="javascript" for="window" event="onload">
    <!--   
            if('<bean:write name="FormItemMenu" property="msgError" />' != ""){
            alert('<bean:write name="FormItemMenu" property="msgError" />');
        }
          parent.oculta_div();
          document.forms[0].idSistema.focus();
    -->
    </script> 
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_ruim_verpagina">
        <form action="begin.do" onSubmit="return false;" method="post">
        <html:hidden name="FormItemMenu" property="exibeSubSistema" value="nao"/>
        <html:hidden name="FormItemMenu" property="exibeItem" value="nao"/>
         <vivo:quadro id="qdAba" height="100%" width="100%" label="Relacionar Item do Menu ao Grupo">
                     <input type="hidden" name="userId" value="<%=request.getParameter("userId")%>">
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
                            <td width="50%">
                                <html:select name="FormItemMenu" property="idSistema" tabindex="1"  style="width:250" styleClass="SELECT" size="1" onchange="carregaSubSistemas();">
                                    <html:option value="000">Escolha uma opçao...</html:option>
                                    <html:options collection="listaSistemas" property="idSistema" labelProperty="dsSistema" /> 
                                </html:select>
                            </td>
                            <td align="right">
                                &nbsp;                                
                            </td>                        
                        </tr>
                        <tr><td height="2"></td></tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <logic:equal name="FormItemMenu" property="exibeSubSistema" value="sim">
                            <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
                            <tr><td height="2"></td></tr>
                            <tr>
                                <td width="25%" class="tblEstatica_campoNome">
                                    <netui:label value="Nome do Subsistema: " styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="50%">
                                    
                                    <html:select name="FormItemMenu" property="idSubSistema" tabindex="2"  style="width:250" styleClass="SELECT" size="1" onchange="clearOptions();">
                                        <html:option value="000">Escolha uma opçao...</html:option>
                                        <html:options collection="listaSubSistemas" property="idSubSistema" labelProperty="dsSubSistema" /> 
                                    </html:select>
                                </td>
                                <td align="right">
                                    <img id="btPesquisar" tabindex="3" onclick="carregaItemMenu();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="cursor:hand;border:none;"/>
                                </td>
                            </tr>
                            <tr><td height="2"></td></tr>
                        </table>
                    </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td>
                    <logic:equal name="FormItemMenu" property="exibeItem" value="sim">
                        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="tbl_bgGray">
                            <tr>
                                <td align="center" colspan="2">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                        <tr>
                                            <td width="47%" class="tbl_titulo" align="center">Itens existentes</td>
                                            <td width="5%">&nbsp;</td>
                                            <td width="47%" class="tbl_titulo" align="center">Itens relacionados</td>
                                        </tr>
                                        <tr><td height="6"></td></tr>
                                        <tr>
                                            <td width="47%" align="center">
                                                <html:select name="FormItemMenu" property="arrayItensExistentes" tabindex="4" multiple="true" style="width:250" styleClass="SELECT" size="6" >
                                                    <html:options collection="listaItensExistentes" property="idItemMenu" labelProperty="nmMenu" /> 
                                                </html:select>
                                            </td>
                                            <td width="5%" align="center">
                                                <img id="btRightSimp1" tabindex="5" onclick="transfereSelecaoLista(document.forms[0].arrayItensExistentes, document.forms[0].arrayItensRelacionados); return false" style="border:none;cursor:hand;" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"/>
                                                <br/><br/>
                                                
                                                <img id="btRightSimp" tabindex="6" onclick="transfereSelecaoLista(document.forms[0].arrayItensRelacionados, document.forms[0].arrayItensExistentes); return false" style="border:none;cursor:hand;" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"/>
                                            </td>
                                            <td width="47%" align="center">
                                                <html:select tabindex="7" name="FormItemMenu" property="arrayItensRelacionados" multiple="true" style="width:250" styleClass="SELECT" size="6" >
                                                    <html:options collection="listaItensRelacionados" property="idItemMenu" labelProperty="nmMenu" /> 
                                                </html:select>
                                            </td>
                                    
                                        </tr>
                                        <tr><td height="6"></td></tr>
                                    </table>
                                </td>
                            </tr>
                            <tr><td height="6"></td></tr>
                            <tr><td height="4"></td></tr>
                        </table>
                        <tr><td height="2"></td></tr>
                    </td>
                            <tr>
                                <td align="right" colspan="2">
                                <acesso:controlHiddenItem nomeIdentificador="usu_ruim_gravar">
                                    <img id="btSalvar1" tabindex="8" onclick="salvarItem();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;" onBlur="document.forms[0].idSistema.focus();"/>
                                </acesso:controlHiddenItem>
                                </td>
                            </tr>
                    </logic:equal>
                <tr><td height="4"></td></tr>
            </table>
        </vivo:quadro>
        </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>

