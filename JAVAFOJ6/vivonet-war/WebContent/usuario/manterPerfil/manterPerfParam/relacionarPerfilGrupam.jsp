<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormPerfil"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm"/>
<bean:define id="arrayGrupamentosExistentesVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm.arrayGrupamentosExistentes"/>
<bean:define id="arrayGrupamentosRelacionadosVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm.arrayGrupamentosRelacionados"/>
<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
<script language="javascript">
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
            var lista = document.forms[0].arraySelecaoRelacionados;
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].arraySelecaoRelacionados.options[i].selected =true; 
            }
        }
        
        function salvarItem() {
           // if(document.forms[0].arraySelecaoRelacionados.length >0){
                    gravar();
                    document.forms[0].action = "salvaGrupamento.do";
                    parent.mostrar_div();
                    document.forms[0].submit(); 
           // }else{
            //    alert("Não há item a ser salvo!");
           // }      
        }
// -->
    </script>
    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormPerfil" property="msgError" />' != "")
        {
            alert('<bean:write name="FormPerfil" property="msgError" />');
        }
        parent.oculta_div();
        document.forms[0].arraySelecaoExistentes.focus();
    -->
    </script> 
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rpg_verpagina">
        <form action="home.do" onSubmit="return false;" method="post">
        <html:hidden name="FormPerfil" property="exibeUnidade" value="nao"/>
            <script>
                document.body.style.backgroundColor= '#ededed';
            </script>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            <table width="720" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td width="310" align="center"><b>Grupamentos existentes</b></td>
                    <td width="100"></td>
                    <td width="310" align="center"><b>Grupamentos relacionados</b></td>
                </tr>
                <tr>
                    <td align="left" valign="top">
                        <html:select name="FormPerfil" tabindex="1" property="arraySelecaoExistentes" multiple="true" style="width:310px;height:175px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].arraySelecaoExistentes, document.forms[0].arraySelecaoRelacionados);">
                            <html:options collection="arrayGrupamentosExistentesVO" property="idGrupamento" labelProperty="dsGrupamento" /> 
                        </html:select>
                    </td>
                    <td align="center">
                        <img  id="btRightSimp1" tabindex="2" onclick="transfereSelecaoLista(document.forms[0].arraySelecaoExistentes, document.forms[0].arraySelecaoRelacionados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="cursor:hand;border:none" /><br><br>
                        <img id="btRightSimp2" tabindex="3" onclick="transfereSelecaoLista(document.forms[0].arraySelecaoRelacionados, document.forms[0].arraySelecaoExistentes);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="cursor:hand;border:none" />
                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormPerfil" tabindex="4" property="arraySelecaoRelacionados"  multiple="true" style="width:310px;height:175px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].arraySelecaoRelacionados, document.forms[0].arraySelecaoExistentes);">
                            <html:options collection="arrayGrupamentosRelacionadosVO" property="idGrupamento" labelProperty="dsGrupamento" /> 
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="3">
                    <acesso:controlHiddenItem nomeIdentificador="usu_rpg_salvar">
                        <img vspace="5" tabindex="5" hspace="10" id="btSalvar1" onclick="salvarItem();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand" onBlur="document.forms[0].arraySelecaoExistentes.focus();"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
        </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
