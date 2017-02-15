<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilForm" type="admsistemas.PerfilCRI.PerfilCRIController.PerfilForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Perfil CRI"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
        
        function CarregaAba(nome){
            var pagina = "";
            mostrar_div();
            if(nome=="bt01") pagina = "/FrontOfficeWeb/admsistemas/PerfilCRI/carregaPerfil.do";

            document.getElementById("ifrmAbas").src = pagina;
        }
        
        function voltar()
        {
            document.forms[0].action = 'voltaPesquisa.do?isMenu=false';
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
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>mostrar_div();</script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="468" width="790" label="Parametros" operacoes="Relacionamentos" scroll="no">
        <form id="frmSelecao" name="frmSelecao" method="post"/>
            <html:hidden name="Form" property="idPerfil"/>
            <table><tr><td></td></tr></table>
            <table width="100%" cellspacing="1" cellpadding="1" class="tbl_bgGray" align="center">
                <tr>
                    <td>
                    <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr>
                            <td width="20%" class="tblEstatica_campoNome">&nbsp;
                                <netui:label value="Perfil CRI:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="80%">
                                <bean:write name="Form" property="nmPerfil"/>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>
            <table><tr><td height="6"></td></tr></table>
            <table width="100%" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td valign="top">
                        <vivo:abaGrupo id="btAba" width="100%" height="10px" styleClass="abatexto">
                            <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);"  value="Relacionar Perfil CRI" select="S"/>
                        </vivo:abaGrupo>
                    </td>
                </tr>
                <tr>
                    <td valign="top" align="center">
                        <table width="100%" cellpadding="0" cellspacing="0" align="center" style="border:1px solid #adadad;">
                            <tr>
                                <td valign="top" align="center" width="100%">
                                    <iframe id="ifrmAbas" width="100%" height="360" frameborder=0 scrolling="yes" src=""></iframe>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        <img onClick="voltar();" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'"  onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;" hspace="5" vspace="10"/>
                    </td>
                </tr>
            </table>
        </form>
        </vivo:sessao>
        
    </netui-template:section>
</netui-template:template>
