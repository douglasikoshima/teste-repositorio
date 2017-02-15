<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="FormLdap"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formLdap"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Manutenção LDAP" name="title"></netui-template:setAttribute>
    <netui-template:setAttribute value="Gestão de Usuários" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="Javascript">
            function limpaCampoPesquisa(){
                document.forms[0].campoPesquisa.value = '';
            }

            function pesquisaUsuario(){
                if(document.forms[0].filtro.value=='000'){
                   alert('Selecione uma opção válida de filtro.');
                    return; 
                }
                if(document.forms[0].campoPesquisa.value== ''){
                    alert('É necessário o preenchimento do campo de pesquisa.');
                    return;
                }
                document.forms[0].action = 'pesquisaUsuario.do';
                document.forms[0].submit();
            }

            function BloqueiaUsuario(uid){
                if (window.confirm('Confirma bloqueio do login ' + uid + '?')) {
                    document.forms[0].uid.value = uid;
                    document.forms[0].action = 'bloqueiaUsuario.do';
                    document.forms[0].submit();
                }
            }

            function DesbloqueiaUsuario(uid){
                if (window.confirm('Confirma desbloqueio do login ' + uid + '?')) {
                    document.forms[0].uid.value = uid;
                    document.forms[0].action = 'desbloqueiaUsuario.do';
                    document.forms[0].submit();
                }
            }

            function ReinicializaSenha(uid){
                if (window.confirm('Confirma reinicializacão de senha do login ' + uid + '?')) {
                    document.forms[0].uid.value = uid;
                    document.forms[0].action = 'reinicializaSenha.do';
                    document.forms[0].submit();
                }
            }
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="ldap" height="450" width="790" label="Usuários" operacoes="Manutenção LDAP" scroll="no">
        <form action="begin.do" onSubmit="return false;" method="post" name="formLdap">
        <html:hidden name="FormLdap" property="uid"/>  
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            <table width="770" height="" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td width="40">Filtro:</td>
                    <td width="120">
                        <html:select name="FormLdap" property="filtro" style="width:120px;" styleClass="SELECT">
                            <option value="000">Escolha uma opção...</option>
                            <option value="login">Login</option>
                            <option value="nome">Nome</option>
                        </html:select>
                    </td>
                    <td width="50">Descrição:</td>
                    <td width="540">
                        <html:text name="FormLdap" property="campoPesquisa" style="width:540px;" styleClass="input" maxlength="100"/>
                    </td>
                </tr>
                <tr><td height="5"></td></tr>
                <tr>
                    <td align="right" colspan="4">
                        <img onclick="limpaCampoPesquisa(); return false" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'" style="border:none;cursor:hand;"/>
                        <img onclick="pesquisaUsuario();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none;cursor:hand;"/>
                    </td>
                </tr>
                <tr><td height="5"></td></tr>
                <tr>
                    <logic:equal name="FormLdap" property="arrayUsuarioLdap" value="0">
                        <td colspan="4" class="tblEstatica_campoNome" align="center">
                             Não foi encontrado nenhum Usuário com a descrição fornecida.
                        <td>
                    </logic:equal>
                    <logic:notEqual name="FormLdap" property="arrayUsuarioLdap" value="0">
                    <logic:notEqual name="FormLdap" property="arrayUsuarioLdap" value="-1">
                        <td colspan="4">
                            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="755" layoutHeight="290" tableWidth="755" styleId="tableTitle" sortable="true">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn align="left" width="25%" type="string">Login</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="left" width="30%" type="string">Nome</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="left" width="30%" type="string">Sobrenome</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows>
                                    <logic:iterate name="FormLdap" property="usrUsuarioLdapVO" id="itemGrupo">
                                        <vivo:tbRow key="Linha">
                                            <vivo:tbRowColumn> <bean:write name="itemGrupo" property="user.uid"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="itemGrupo" property="user.givenName"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="itemGrupo" property="user.sn"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <a href="Javascript:BloqueiaUsuario('<bean:write name="itemGrupo" property="user.uid"/>');">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_bloquear.gif" alt="Bloquear Login" border="none">
                                                </a>
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <a href="Javascript:DesbloqueiaUsuario('<bean:write name="itemGrupo" property="user.uid"/>');">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_desbloquear.gif" alt="Desbloquear Login" border="none">
                                                </a>
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <a href="Javascript:ReinicializaSenha('<bean:write name="itemGrupo" property="user.uid"/>');">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_reinicsenha.gif" alt="Reinicializar Senha" border="none">
                                                </a>
                                            </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                            </vivo:tbTable>
                        </td>
                    </logic:notEqual>
                    </logic:notEqual>
                </tr>
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <logic:notEqual name="FormLdap" property="arrayUsuarioLdap" value="0">
            <logic:notEqual name="FormLdap" property="arrayUsuarioLdap" value="-1">
                <table width="770" height="30" cellpadding="0" cellspacing="0" align="center" class="tbl_bgGray">
                    <tr>
                        <td width="100">&nbsp;&nbsp;Legendas:</td>
                        <td width="150"><img src="/FrontOfficeWeb/resources/images/bt_icon_bloquear.gif" align="middle">&nbsp;&nbsp;Bloquear Login</td>
                        <td width="150"><img src="/FrontOfficeWeb/resources/images/bt_icon_desbloquear.gif" align="middle">&nbsp;&nbsp;Desbloquear Login</td>
                        <td width="370"><img src="/FrontOfficeWeb/resources/images/bt_icon_reinicsenha.gif" align="middle">&nbsp;&nbsp;Reinicializar Senha</td>
                    </tr>
                </table>
             </logic:notEqual>
             </logic:notEqual>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table width="770" height="" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td><img onClick="window.location.href='/FrontOfficeWeb/index.jsp';" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/></td>
                </tr>
            </table>
            <logic:notEqual name="FormLdap" property="msgErro" value="">
                <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                    alert('<bean:write name="FormLdap" property="msgErro"/>');
                </SCRIPT>
            </logic:notEqual>
        </form>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>
