<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<acesso:controlInitEnv/>

<bean:define id="FormGrupo"  name="lGrpForm" scope="session" />
<bean:define id="itemGrupo"  name="FormGrupo" property="itemGrupo"/>

<% request.getSession().removeAttribute("lGrpForm"); %>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Grupo"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
        function CarregaAba(nome){
            var pagina = "";
            mostrar_div();
            if(nome=="bt01") pagina = "/FrontOfficeWeb/usuario/manterGrupo/manterGrupoParam/relacionarGrupoCanal.do";
            if(nome=="bt02") pagina = "/FrontOfficeWeb/usuario/manterGrupo/manterGrupoParam/relacionarGrupoPerfil.do";
            if(nome=="bt03") pagina = "/FrontOfficeWeb/usuario/manterGrupo/manterGrupoParam/relacionarGrupoItemMenu.do";
            if(nome=="bt04") pagina = "/FrontOfficeWeb/usuario/manterGrupo/manterGrupoParam/manutencaoSkill.do";

            document.getElementById("ifrmAbas").src = pagina;
        }

        function envia(){
            document.forms[0].action = '/FrontOfficeWeb/usuario/manterGrupo/manterGrupoParam/begin.do?isMenu=false&voltar=true';
            mostrar_div();
            document.forms[0].submit();
        }

        //Para evitar loops de chamadas iniciais
        contadorChamada = 0;

        // -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            abaCarregada = "<%=request.getAttribute("abaSource")%>";
            if (abaCarregada=="" || abaCarregada=="null") abaCarregada = "bt01";
            CarregaAba(abaCarregada);
        -->
        </SCRIPT>
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_erg_verpagina">
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>mostrar_div();</script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="468" width="790" label="Grupos" operacoes="Relacionamentos">
        <form id="frmSelecao" name="frmSelecao" method="post">
            <html:hidden name="FormGrupo" property="idGrupo"/>
            <table><tr><td></td></tr></table>
            <table width="98%" cellspacing="1" cellpadding="1" class="tbl_bgGray" align="center">
                <tr>
                    <td>
                    <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr>
                            <td width="20%" class="tblEstatica_campoNome">&nbsp;
                                <netui:label value="Nome do Grupo:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="80%">
                                <bean:write name="FormGrupo" property="itemGrupo.dsGrupo"/>
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
                        <vivo:abaGrupo id="btAba" width="600" height="10px" styleClass="abatexto">
                            <acesso:controlHiddenItem nomeIdentificador="usu_erg_abacanais"><vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);"  value="Relacionar Grupo a Canais" select="S"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_erg_abaperfis"><vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Relacionar Grupo a Perfis"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_erg_menu"><vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Relacionar Item do Menu ao Grupo" /></acesso:controlHiddenItem>
                                                                                       <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Manutenção de Skill" />
                        </vivo:abaGrupo>
                    </td>
                </tr>
                <tr>
                    <td valign="top" align="center">
                        <table width="100%" cellpadding="0" cellspacing="0" align="center" style="border:1px solid #adadad;">
                            <tr>
                                <td valign="top" align="center" width="100%">
                                    <iframe id="ifrmAbas" width="100%" height="265" frameborder=0 scrolling="no" src="about:blank"></iframe>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        <img onClick="envia();" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'"  onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;" hspace="5" vspace="10"/>
                    </td>
                </tr>
            </table>
        </form>
        </vivo:sessao>
        <vivo:quadroFlutuante label="Regras Encaminhamento" scroll="false" src="" idIframe="ifrmParametrizarSkill" id="divParametrizarSkill" spacesLeft="5" height="470" spacesTop="110" url="<%=request.getContextPath()%>" display="none" width="790"></vivo:quadroFlutuante>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
