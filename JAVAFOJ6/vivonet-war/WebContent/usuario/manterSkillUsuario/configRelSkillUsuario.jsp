<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Campos"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>
    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
        function CarregaAba(nome){
            var pagina = "";
            if(nome=="bt01") pagina = "/FrontOfficeWeb/usuario/manterSkillUsuario/abaAssocSkillUsuario/begin.do?inPesquisa=0";
            if(nome=="bt04") pagina = "/FrontOfficeWeb/usuario/manterSkillUsuario/abaAssocSkillUsuario/beginSkillContato.do?inPesquisa=0";
            document.getElementById("fraAbas").src = pagina;
            mostrar_div();
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
        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <!--APLICACAO-->
        <vivo:sessao id="qdMain" height="470" width="790" label="Administração de Tráfego" operacoes="Skill do Usuário" scroll="no">
            <table width="97%" border="0" cellspacing="0" cellpadding="0" bgcolor="#e3ecf4" align="center">
                <tr><td height="4"></td></tr>
                <tr>
                    <td valign="top" width="100%">
                        <vivo:abaGrupo id="btAba" width="30%" height="10px" styleClass="abatexto">
                            <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);"  value="Manter skill do Usuário" select="S"/>
                            <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Contato"/>
                      </vivo:abaGrupo>
                    </td>
                </tr>
                <tr>
                    <td valign="top" width="100%" style="border:1px solid #adadad;">
                        <iframe id="fraAbas" width="100%" height="420" frameborder="0" scrolling="no" src="about:blank" style="bgcolor:#e3ecf4;"></iframe>
                    </td>
                </tr>
            </table>
            <img hspace="8" vspace="6" style="border:none;cursor:hand;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"/>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>