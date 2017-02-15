<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormServidor"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relServidorForm"/>
<bean:define id="servidExistVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relServidorForm.servidoresExistentesVO"/>
<bean:define id="servidRelacVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relServidorForm.servidoresRelacionadosVO"/>

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
    
    function salvar() {
        //    if (document.forms[0].servidoresRelacionados.options.length > 0){
                for ( i = 0; i < document.forms[0].servidoresRelacionados.options.length; i++ )
                    document.forms[0].servidoresRelacionados.options[i].selected = true;
                parent.mostrar_div();
                document.forms[0].submit();
         //   }else{
         //       alert("Favor selecionar um Servidor Relacionado!")
        //    }
    }
</script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rs_verpagina">
        <html:form action="/usuario/manterSistema/editarParSistema/relacionarServidor/salvaRelServidor.do" method="post">        
        <SCRIPT>
            parent.mostrar_div();
        </script>
        <center>
        <table width="730" border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
                <td width="295" align="center"><b>Servidores existentes</b></td>
                <td width="140">&nbsp;</td>
                <td width="295" align="center"><b>Servidores relacionadas</b></td>
            </tr>
            <tr>
                <td>
                    <html:select name="FormServidor" property="servidoresExistentes" multiple="true" style="width:295px;height:319px;" size="8" ondblclick="transfereSelecaoLista(document.relServidorForm.servidoresExistentes, document.relServidorForm.servidoresRelacionados);">
                        <html:options collection="servidExistVO" property="idServidor" labelProperty="dsServidor" /> 
                    </html:select>
                </td>
                <td align="center">
                    <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].servidoresExistentes, document.forms[0].servidoresRelacionados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="cursor:hand;border:none" />
                    <br/><br/>
                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].servidoresRelacionados, document.forms[0].servidoresExistentes);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="cursor:hand;border:none" />                </td>
                <td>
                    <html:select name="FormServidor" property="servidoresRelacionados" multiple="true" style="width:295px;height:319px;" size="8" ondblclick="transfereSelecaoLista(document.relServidorForm.servidoresRelacionados, document.relServidorForm.servidoresExistentes);">
                        <html:options collection="servidRelacVO" property="idServidor" labelProperty="dsServidor" /> 
                    </html:select>
                </td>
            </tr>
            <tr><td height="6"></td></tr>
        </table>
        </center>

        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="usu_rs_salvar">
                    <img hspace="10" id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" onClick="salvar(); return false" style="border:none;cursor:hand" />
                </acesso:controlHiddenItem>
                </td>
            </tr>
            <tr><td height="2"></td></tr>
        </table>
        <script>
            document.body.style.backgroundColor = '#ededed';
            parent.oculta_div();
        </script>
        <vivo:alert atributo="msgError" scope="request"/>
        </html:form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
