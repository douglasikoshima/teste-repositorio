<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormOperadoras"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="salvaRelOperadoraForm"/>
<bean:define id="operadorasExistVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="salvaRelOperadoraForm.operadorasExistentesVO"/>
<bean:define id="operadorasRelacVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="salvaRelOperadoraForm.operadorasRelacionadosVO"/>

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
        for ( i = 0; i < document.forms[0].operadorasRelacionados.options.length; i++ )
            document.forms[0].operadorasRelacionados.options[i].selected = true;
    }

    function salvarOperadora() {
        //if(document.forms[0].operadorasRelacionados.length==0){
        //    alert("Não há item selecionado!");
        //}
        salvar();
        document.forms[0].action = "salvaRelOperadora.do";
        parent.mostrar_div();
        document.forms[0].submit();
    }

</script>
<script language="javascript" for="window" event="onload">
<!--
            if('<bean:write name="FormOperadoras" property="msgError" />' != ""){
        alert('<bean:write name="FormOperadoras" property="msgError" />');
    }
     parent.oculta_div();
     document.forms[0].operadorasExistentes.focus();
-->
</script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_ruo_verpagina">
        <form action="salvaRelOperadora.do" onSubmit="return false;" method="post">
        <vivo:quadro id="qdAba" height="100%" width="100%" label="Relacionar Usuário a Operadora">
                <input type="hidden" name="userId" value="<%=request.getParameter("userId")%>">
            <table width="100%" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                    <table width="95%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center" colspan="2">
                                <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" class="tbl_bgGray">
                                    <tr>
                                        <td width="47%" class="tbl_titulo" align="center">Operadoras existentes</td>
                                        <td width="5%">&nbsp;</td>
                                        <td width="47%" class="tbl_titulo" align="center">Operadoras relacionadas</td>
                                    </tr>
                                    <tr><td height="4"></td></tr>
                                    <tr>
                                        <td width="47%" align="center">&nbsp;
                                            <html:select name="FormOperadoras" property="operadorasExistentes" tabindex="1" multiple="true" style="width=250px" size="8">
                                                <html:options collection="operadorasExistVO" property="idOperadora" labelProperty="dsOperadora" />
                                            </html:select>
                                        </td>
                                        <td width="5%" align="center">
                                            <img id="btRightSimp" tabindex="2" onClick="transfereSelecaoLista(document.forms[0].operadorasExistentes, document.forms[0].operadorasRelacionados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                            <br/><br/>
                                            <img id="btLeftSimp" tabindex="3" onClick="transfereSelecaoLista(document.forms[0].operadorasRelacionados, document.forms[0].operadorasExistentes);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                                        </td>
                                        <td width="47%" align="center">&nbsp;
                                            <html:select name="FormOperadoras" property="operadorasRelacionados" tabindex="4" multiple="true" style="width=250px" size="8">
                                                <html:options collection="operadorasRelacVO" property="idOperadora" labelProperty="dsOperadora" />
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr><td height="6"></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="right" colspan="2">
                            <acesso:controlHiddenItem nomeIdentificador="usu_ruo_gravar">
                                <img id="btSalvar" tabindex="5" onclick="salvarOperadora();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;" onBlur="document.forms[0].operadorasExistentes.focus();"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </tr>
                        <tr><td height="2"></td></tr>
                    </table>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
