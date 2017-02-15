<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>
<bean:define id="FormGrupos"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="salvaRelGrupoForm"/>
<bean:define id="gruposExistVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="salvaRelGrupoForm.gruposExistentesVO"/>
<bean:define id="gruposRelacVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="salvaRelGrupoForm.gruposRelacionadosVO"/>

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
        for ( i = 0; i < document.forms[0].gruposRelacionados.options.length; i++ )
            document.forms[0].gruposRelacionados.options[i].selected = true;

        document.forms[0].action = 'salvaRelGrupo.do';
        parent.mostrar_div();
        document.forms[0].target = "";
        document.forms[0].method = "POST";
        document.forms[0].submit();
    }

            function setSupervisor(id){
        document.getElementById("divSupervisor").style.display = 'block';
        document.forms[0].target = "ifrmSupervisor";
        document.forms[0].action = "supervisor.do?idGrupo=" + id + "&idUsuario=" + document.forms[0].userId.value;
        document.forms[0].method = "POST";
        document.forms[0].submit();
    }
</script>
    <script language="javascript" for="window" event="onload">
    <!--
            if('<bean:write name="FormGrupos" property="msgError" />' != ""){
            alert('<bean:write name="FormGrupos" property="msgError" />');
        }
        parent.oculta_div();
        document.forms[0].gruposExistentes.focus();
    -->
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rug_verpagina">
        <form name="salvaRelGrupoForm" action="salvaRelGrupo.do" method="POST">
        <vivo:quadro id="qdAba" height="100%" width="100%" label="Relacionar Usuário ao Grupo" scroll="no">
            <input type="hidden" name="userId" value="<%=request.getParameter("userId")%>">
            <table width="100%" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                    <table width="95%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center" colspan="2">
                                <table width="100%" cellspacing="1" cellpadding="0" align="center" class="tbl_bgGray">
                                    <tr>
                                        <td width="47%" class="tbl_titulo" align="center">Grupos existentes</td>
                                        <td width="5%">&nbsp;</td>
                                        <td width="47%" class="tbl_titulo" align="center">Grupos relacionados</td>
                                    </tr>
                                    <tr><td height="4"></td></tr>
                                    <tr>
                                        <td width="47%" align="center">&nbsp;
                                            <html:select name="FormGrupos" property="gruposExistentes" tabindex="1" multiple="true" style="width=250px" size="8">
                                                <html:options collection="gruposExistVO" property="idGrupo" labelProperty="dsGrupo" />
                                            </html:select>
                                        </td>
                                        <td width="5%" align="center">
                                            <img id="btRightSimp" tabindex="2" onclick="transfereSelecaoLista(document.salvaRelGrupoForm.gruposExistentes, document.salvaRelGrupoForm.gruposRelacionados); return false" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                            <br/><br/>
                                            <img id="btLeftSimp" tabindex="3" onclick="transfereSelecaoLista(document.salvaRelGrupoForm.gruposRelacionados, document.salvaRelGrupoForm.gruposExistentes); return false" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                                        </td>
                                        <td width="47%" align="center">&nbsp;
                                            <html:select name="FormGrupos" property="gruposRelacionados" tabindex="2" multiple="true" style="width=250px" size="8">
                                                <html:options collection="gruposRelacVO" property="idGrupo" labelProperty="dsGrupo" />
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr><td height="6"></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="right" colspan="4">
                            <acesso:controlHiddenItem nomeIdentificador="usu_rug_gravar">
                                <img id="btSalvar" tabindex="4" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" onClick="salvar();" style="border:none;cursor:hand;" onBlur="document.forms[0].gruposExistentes.focus();"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </tr>
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td colspan="4" align="center">
                            <vivo:tbTable selectable="true" layoutWidth="700" layoutHeight="80" tableWidth="700" styleId="tableTitle" sortable="true">
                                    <vivo:tbHeader>
                                        <vivo:tbHeaderColumn align="rigth"  width="70%" type="string">Grupo</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center"  width="20%" type="string">Supervisor</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="10%" type="">&nbsp;</vivo:tbHeaderColumn>
                                    </vivo:tbHeader>
                                    <vivo:tbRows>
                                    <logic:iterate id="item"  name="gruposRelacVO" >
                                                        <vivo:tbRow key="linha1">
                                                            <vivo:tbRowColumn><bean:write name="item" property="dsGrupo" />&nbsp;</vivo:tbRowColumn>
                                                            <vivo:tbRowColumn>
                                                <logic:equal value="1" name="item" property="inSupervisor">Sim</logic:equal>
                                                <logic:equal value="0" name="item" property="inSupervisor">Não</logic:equal>
                                                            </vivo:tbRowColumn>
                                                            <vivo:tbRowColumn>
                                                                <acesso:controlHiddenItem nomeIdentificador="usu_rug_btSupervisor">
                                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" border="0" onclick="setSupervisor('<bean:write name="item" property="idGrupo" />');">
                                                                </acesso:controlHiddenItem>
                                                            </vivo:tbRowColumn>
                                                        </vivo:tbRow>
                                    </logic:iterate>
                                    </vivo:tbRows>
                                </vivo:tbTable>
                            </td>
                        </tr>
                        <tr><td height="2"></td></tr>
                    </table>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        </form>
        <vivo:quadroFlutuante label="Alterar status supervisor" scroll="false" src="/FrontOfficeWeb/usuario/nada.html" idIframe="ifrmSupervisor" id="divSupervisor" spacesLeft="150" height="100" spacesTop="180" url="<%=request.getContextPath()%>" display="none" width="500"></vivo:quadroFlutuante>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>