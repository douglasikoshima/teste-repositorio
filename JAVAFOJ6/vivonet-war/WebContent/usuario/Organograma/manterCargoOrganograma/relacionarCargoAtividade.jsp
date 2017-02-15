<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/> 
  
<bean:define id="ManterCargoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm"/>
<bean:define id="ListaCargos"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm.listaCargoVO"/>
<bean:define id="CargoVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm.cargoVO"/>
<bean:define id="arrayAtividadesExistentesVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm.arrayAtividadesExistentesVO"/>
<bean:define id="arrayAtividadesRelacionadosVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm.arrayAtividadesRelacionadosVO"/>
 
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
    
            function listaCargo(){
            document.forms[0].target = '_parent';
            document.forms[0].nmCargo.value = '';
            var pagina = '/FrontOfficeWeb/usuario/Organograma/manterCargoOrganograma/begin.do';
            document.forms[0].action = pagina;
            parent.mostrar_div();
            document.forms[0].submit();
        }
    
            function gravar(){
            var lista = document.forms[0].arraySelecaoRelacionados;
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].arraySelecaoRelacionados.options[i].selected =true; 
            }
        }

            function salvarItem(){
            gravar();
            document.forms[0].action = "salvaRelacion.do";
            parent.mostrar_div();
            document.forms[0].submit(); 
        }
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rca_verpagina">
    	<form action="<%=request.getContextPath()%>/usuario/Organograma/manterCargoOrganograma/begin.do"  id="begin" name="begin" method="POST">        
            <script>
                document.body.style.backgroundColor= '#ededed';
            </script>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            <table width="720" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td width="310" align="center"><b>Atividades existentes</b></td>
                    <td width="100"></td>
                    <td width="310" align="center"><b>Atividades relacionados</b></td>
                </tr>
                <tr>
                    <td align="left" valign="top">
                        <html:select name="ManterCargoForm" property="arraySelecaoExistentes" multiple="true" style="width:310px;height:175px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].arraySelecaoExistentes, document.forms[0].arraySelecaoRelacionados);">
                            <html:options collection="arrayAtividadesExistentesVO" property="idAtividade" labelProperty="nmAtividade" /> 
                        </html:select>
                    </td>
                    <td align="center">
                        <img id="btRightSimp1" onclick="transfereSelecaoLista(document.forms[0].arraySelecaoExistentes, document.forms[0].arraySelecaoRelacionados); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"/><br><br>
                        <img id="btRightSimp2" onclick="transfereSelecaoLista(document.forms[0].arraySelecaoRelacionados, document.forms[0].arraySelecaoExistentes); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" style="border:none;cursor:hand;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"/>
                    </td>
                    <td align="left" valign="top">
                        <html:select name="ManterCargoForm" property="arraySelecaoRelacionados"  multiple="true" style="width:310px;height:175px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].arraySelecaoRelacionados, document.forms[0].arraySelecaoExistentes);">
                            <html:options collection="arrayAtividadesRelacionadosVO" property="idAtividade" labelProperty="nmAtividade" /> 
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="3">
                    <acesso:controlHiddenItem nomeIdentificador="usu_rca_gravar">
                        <img vspace="5" hspace="10" id="btSalvar1" onclick="salvarItem();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
        </form>
        <script language="javascript" for="window" event="onload">
        <!--   
            if('<bean:write property="msgError" name="ManterCargoForm"/>' != ''){
                alert('<bean:write property="msgError" name="ManterCargoForm"/>');
            }
            parent.oculta_div();
        -->
        </script> 
    </script> 
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
