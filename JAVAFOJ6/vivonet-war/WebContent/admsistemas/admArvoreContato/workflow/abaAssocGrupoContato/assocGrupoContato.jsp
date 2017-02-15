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
<bean:define id="Form"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="assocGrupoContatoForm"/>
<bean:define id="GruposDisponiveis" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="assocGrupoContatoForm.gruposDisponiveis"/>
<bean:define id="GruposAssociados" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="assocGrupoContatoForm.gruposAssociados"/>




 
<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <netui-template:section name="bodySection">
    

        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="Javascript">
        function salvar() {
          if((document.forms[0].aGruposAssociados.options.length==0)){
            document.forms[0].action='gravaAssocGrupoContato.do';
            parent.mostrar_div();
            document.forms[0].submit();    
            return;
          }else{
            if(document.forms[0].aGruposAssociados.options.length<1){
            alert('Não existem grupos(s) associada(s) a ser(em) salva(s).');
              return false;
            }
            for ( i = 0; i < document.forms[0].aGruposAssociados.options.length; i++ ){
              document.forms[0].aGruposAssociados.options[i].selected = true;
            }
          }
          if(document.forms[0].aGruposAssociados.options.length>0){
            document.forms[0].action='gravaAssocGrupoContato.do';
            parent.mostrar_div();
            document.forms[0].submit();    
          }
        }
            
        function transfereSelecaoLista(listaOrigem, listaDestino){
          var i;
          for(i = 0; i<listaOrigem.options.length; i++)
            if(listaOrigem.options[i].selected)
              listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
          for(i = listaOrigem.options.length-1; i>=0; i--)
            if(listaOrigem.options[i].selected)
              listaOrigem.options[i] = null;
        }

        document.body.style.backgroundColor = '#ededed';    
                        
        </script>
        <acesso:controlHiddenItem nomeIdentificador="adm_aba_agc_verpagina">
        <center>
            <!-- vivo:sessao id="regional" width="790" height="470" label="Árvore de Contatos" operacoes="regional" scroll="no"-->    
            <form action="gravaAssocGrupoContato.do" id="assocGrupoContatoForm" name="assocGrupoContatoForm" method="POST">
            <html:hidden name="Form" property="idContato"/>
            <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr><td height="6"></td></tr>
                <tr>
                    <td width="310" align="center" valign="top">
                        Grupos Existentes<br>
                        <html:select name="Form" property="aGruposDisponiveis" multiple="true" style="width:310px;height:201px;" size="4">
                            <html:options collection="GruposDisponiveis" property="idGrupo" labelProperty="nmGrupo" /> 
                        </html:select>
                    </td>
                    <td width="70" align="center" valign="middle">
                        <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].aGruposDisponiveis, document.assocGrupoContatoForm.aGruposAssociados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"/><br><br>
                        <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].aGruposAssociados, document.assocGrupoContatoForm.aGruposDisponiveis);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0"/>
                    </td>
                    <td width="310" align="center" valign="top">
                        Grupos Associadas<br>
                        <html:select name="Form" property="aGruposAssociados" multiple="true" style="width:310px;height:203px;" size="4">
                            <html:options collection="GruposAssociados" property="idGrupo" labelProperty="nmGrupo" /> 
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td align="right" height="12px" colspan="3">
                       <acesso:controlHiddenItem nomeIdentificador="adm_aba_agc_gravar">
                        <img vspace="3" id="btSalvar1" onClick="salvar();return false" style="cursor:hand" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" border="0" align="right"/>
                      </acesso:controlHiddenItem >
                    </td>
                </tr>
                
            </table>

        </form>
    
        <!--/vivo:sessao-->
        </acesso:controlHiddenItem>
        </center>            
    <script language="javascript" for="window" event="onload">
        <!--   
        if('<bean:write name="Form" property="msgError" />' != "")
        {
            alert('<bean:write name="Form" property="msgError" />');
        }
        parent.oculta_div();
        document.body.style.backgroundColor = '#ededed';
        -->
    </script> 

  
    </netui-template:section>
  </netui-template:template>
 