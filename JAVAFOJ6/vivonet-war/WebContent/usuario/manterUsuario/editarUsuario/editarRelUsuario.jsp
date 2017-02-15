<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<!-- variavel que vai armazenar os dados do usuario a serem editados -->
<bean:define id="FormUser"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm"/>
<bean:define id="usuarioVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.usuarioVO"/>
<bean:define id="aUsuarios" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaUsuariosForm.arrayUsuariosVO.usuarioVOArray"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
        
        function CarregaAba(nome){
            var pagina = "";
            if(nome=="bt01") pagina = "/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/relacionarGrupo/begin.do?userId="+"<%=request.getParameter("userId")%>";
            if(nome=="bt02") pagina = "/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/relacionarPerfil/begin.do?userId="+"<%=request.getParameter("userId")%>";
            if(nome=="bt03") pagina = "/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/relacionarOperadora/begin.do?userId="+"<%=request.getParameter("userId")%>";
            if(nome=="bt04") pagina = "/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/relacionarItemMenu/begin.do?userId=<%=request.getParameter("userId")%>";
            if(nome=="bt05") pagina = "/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/relacionarSkill/begin.do?userId=<%=request.getParameter("userId")%>";

            mostrar_div();
            document.getElementById("ifrmAbas").src = pagina;
        }
        
        function envia(){
            document.forms[0].action = '/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/begin.do?tipo=resultado';
            mostrar_div();
            document.forms[0].submit();
        }
        
        // -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            CarregaAba('bt01');
        -->
        </SCRIPT>    
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_erus_verpagina">

        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
                
        <vivo:body idTable="tbMain" idDiv="dvMain" height="495" width="790">
            <vivo:quadro id="qdMain" height="468" width="780" label="Editar Usuario > Editar Relacionamentos do Usuario">
            <form id="frmSelecao" name="frmSelecao" method="post">
            </form>
            <table><tr><td></td></tr></table>
            <table width="100%" cellspacing="1" cellpadding="1">
                <tr>
                    <td>
                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr><td height="2"></td></tr>
                        <tr>
                            <td align="center">
                                <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                                    <tr><td height="4" colspan="4"></td></tr>
                                    <tr>
                                        <td width="20%" class="tblEstatica_campoNome" onblur="mostraTipoDoc()">&nbsp;
                                            <netui:label value="CPF:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor" colspan="3">
                                            <bean:write name="FormUser" property="dsDoc0"/>
                                        </td>
                                    <!--</tr>
                                    <tr>>
                                        <td width="25%" class="tblEstatica_campoNome" onblur="mostraTipoDoc()">&nbsp;
                                            < netui:label value="Nome:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="25%" class="tblEstatica_campoValor">
                                            < bean:write name="usuarioVO" property="nome"/>
                                        </td -->
                                    </tr>
                                    <tr>
                                        <td class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Nome:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor"  colspan="3">
                                            <bean:write name="usuarioVO" property="nome"/>
                                        </td>
                                    <!--</tr>
                                    <tr>-->
                                    </tr>
                                    <tr>
                                        <td class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Login CTI:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor">
                                            <bean:write name="usuarioVO" property="loginCti"/>
                                        </td>
                                    <!--</tr>
                                    <tr>-->
                                        <td class="tblEstatica_campoNome" width="25%">&nbsp;
                                            <netui:label value="Status:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor">
                                            <bean:write name="usuarioVO" property="descricaoStatusAtual"/>
                                        </td>
                                    </tr>
                                    <!-- tr>
                                        <td width="25%" class="tblEstatica_campoNome">&nbsp;
                                            < netui:label value="Cargo:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="75%" class="tblEstatica_campoValor">
                                            < bean:write name="usuarioVO" property="descricaoCargoAtual"/>
                                        </td>
                                    </tr -->
                                    <tr>
                                        <td class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Login FrontOffice:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor">
                                            <bean:write name="usuarioVO" property="login"/>                    
                                        </td>
                                        <td class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Login do superior imediato:" styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor">
                                            <bean:write name="usuarioVO" property="loginChefe"/>                    
                                        </td>
                                    <tr><td height="4" colspan="4"></td></tr>                                        
                                </table>
                            </td>
                        </tr>
                        <tr><td height="2"></td></tr>
                    </table>
                    </td>
                </tr>
            </table>
            <table><tr><td height="6"></td></tr></table>
            
            <table width="98%" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td valign="top">
                        <vivo:abaGrupo id="btAba" width="80%" height="10px" styleClass="abatexto">
                            <acesso:controlHiddenItem nomeIdentificador="usu_erus_rgru"><vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);" value="Relacionar Grupo" select="S"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_erus_rper"><vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Relacionar Perfil"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_erus_ope"><vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Relacionar Operadora"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_erus_rime"><vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Relacionar Item Menu"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_erus_skill"><vivo:abaItem id="bt05" onclick="abaSelected(btAba, bt05);CarregaAba(this.id);" value="Relacionar Skill"/></acesso:controlHiddenItem>
                        </vivo:abaGrupo>
                    </td>
                </tr>
                <tr>
                    <td valign="top" width="100%">
                        <table width="100%" cellpadding="0" cellspacing="0" align="center">
                            <tr>
                                <td valign="top" align="center" width="100%">
                                    <IFRAME id="ifrmAbas" width="100%" height="310px" frameborder=0 scrolling="no" src="/FrontOfficeWeb/usuario/nada.html"></IFRAME>
                                </td>
                            </tr>
                        </table>
                    <img onclick="envia();" style="border:none;cursor:hand;" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"/>
                    </td>
                </tr>
            </table>
            </vivo:quadro>
    
        </vivo:body>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
