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

<bean:define id="FormPerfil"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm"/>
<bean:define id="idPerfil"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm.idPerfil"/>
<bean:define id="perfilUsuarioVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaPerfisForm.perfilUsuarioVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Grupo"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--

        function CarregaAba(nome){
            var pagina = "";
            mostrar_div();
            if(nome=="bt01") pagina = "/FrontOfficeWeb/usuario/manterPerfil/manterPerfParam/relacionarPerfilGrupam.do";
            //if(nome=="bt01") pagina = "/FrontOfficeWeb/usuario/manterPerfil/manterPerfParam/editarRelPerfil.do?perfilID=<%=idPerfil%>";

            if(nome=="bt02") pagina = "/FrontOfficeWeb/usuario/manterPerfil/manterPerfParam/listaSistemas.do";

            document.getElementById("ifrmAbas").src = pagina;
        }

        function envia()
        {
            var form = document.forms[0];
            form.action = 'begin.do';
            mostrar_div();
            form.submit();

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
    <acesso:controlHiddenItem nomeIdentificador="usu_erp_verpagina">
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>mostrar_div();</script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

            <html:hidden name="FormPerfil" property="idPerfil"/>

                <vivo:sessao id="qdMain" height="470" width="790" label="Relacionamentos do Perfil" operacoes="Manuten&ccedil;&atilde;o de Par&acirc;metros">

                <form action="home.do">

                <table><tr><td></td></tr></table>
                <table width="98%" cellspacing="1" cellpadding="1" class="tbl_bgGray" align="center">
                    <tr>
                        <td>
                        <table width="95%" border="0" cellspacing="1" cellpadding="1" align="center">
                            <tr>
                                <td width="20%" class="tblEstatica_campoNome">&nbsp;
                                    <netui:label value="Nome do Perfil:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="80%" style="tblEstatica_campoValor">
                                    <bean:write name="FormPerfil" property="dsPerfil" />
                                </td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                </table>
                <table><tr><td height="6"></td></tr></table>

                <table width="98%" cellpadding="0" cellspacing="0" align="center">
                    <tr>
                        <td valign="top">
                            <vivo:abaGrupo id="btAba" width="400" height="10px" styleClass="abatexto">
                                <acesso:controlHiddenItem nomeIdentificador="usu_erp_abagp"><vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);"  value="Relacionar Grupamentos ao Perfil" select="S"/></acesso:controlHiddenItem>
                                <acesso:controlHiddenItem nomeIdentificador="usu_erp_abaup"><vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Relacionar Unidades ao Perfil"/></acesso:controlHiddenItem>
                            </vivo:abaGrupo>
                        </td>
                    </tr>
                    <tr>
                        <logic:equal name="FormPerfil" property="indicativoOperacao" value="grupamento">
                            <td valign="top" align="center">
                                <table width="100%" cellpadding="0" cellspacing="0" align="center" style="border:1px solid #adadad;">
                                    <tr>
                                        <td valign="top" align="center" width="100%">
                                            <IFRAME id="ifrmAbas" name="ifrmAbas" width="100%" height="250" frameborder=0 scrolling="no" src="about:blank"></IFRAME>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </logic:equal>

                        <logic:equal  name="FormPerfil" property="indicativoOperacao" value="unidade">
                            <td valign="top" align="center">
                                <table width="100%" cellpadding="0" cellspacing="0" align="center">
                                    <tr>
                                        <td valign="top" align="center" width="100%">
                                            <IFRAME id="ifrmAbas" name="ifrmAbas" width="100%" height="250" frameborder=0 scrolling="no" src="relacionarPerfilUnidade.jsp"></IFRAME>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </logic:equal>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <img hspace="5" onClick="envia();" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
                        </td>
                    </tr>
                </table>
                </form>
                </vivo:sessao>



    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>