<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Motivos do Workflow"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>
    <netui-template:section name="headerSection">
<script>
function pesquisar() {
    f = document.forms[0];
    screen_block();
    document.getElementById("dvLegendas").style.display="";
    document.getElementById("dvPesquisa").style.display="";
    f.action='pesquisar.do';
    f.target='ifrmPesquisa';
    f.submit();
}

function limpar() {
    f = document.forms[0];
    f.dsMotivo.value='';
    document.getElementById("dvLegendas").style.display="none";
    document.getElementById("dvPesquisa").style.display="none";
}

function incluir() {
    f = document.forms[0];
    divIncluirAlterar.style.display='block';
    f.target="ifrmIncluirAlterar";
    f.action="lerMotivo.do";
    f.submit();
}

function screen_block() {
    //Liga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
}

function screen_unblock() {
    //Desliga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
}
</script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>    
        <vivo:sessao id="qdMain" height="470" width="790" label="Administração de Sistemas" operacoes="Manter Motivos Workflow" scroll="no">
            <form action="pesquisar.do">
            <table cellpadding="5" cellspacing="0" width="100%" class="tbl_bgGray">
                <tr>
                    <td align="left" valign="middle" width="60%">
                        <b>Pesquisar Motivo: </b> <html:text name="form" property="dsMotivo" size="60" maxlength="250"/>
                    </td>
                    <td align="left" valign="middle">
                        <img hspace="8" vspace="6" style="border:none;" onClick="pesquisar(); return false;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/> 
                        <img hspace="8" vspace="6" style="border:none;" onClick="limpar(); return false;" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif"/>
                    </td>
                </tr>
            </table>
            <div id="dvPesquisa" style="display:none;">
            <iframe id="ifrmPesquisa" name="ifrmPesquisa" src="" width="100%" height="360"></iframe>
            </div>
            <table width="100%">
                <tr>
                <td colspan="2">
                    <div id="dvLegendas" style="display:none;">
                    <table style="background-color:#ededed;border:1px solid #adadad;" width="100%" align="left">
                        <tr>
                            <td align="center" valign="middle"><b>Legendas:</b></td>
                            <td align="center" valign="middle"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0">&nbsp;Editar Motivo</td>
                            <td align="center" valign="middle"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" border="0">&nbsp;Editar Parâmetros</td>
                            <td align="center" valign="middle"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" border="0">&nbsp;Motivo Habilitado</td>
                            <td align="center" valign="middle"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" border="0">&nbsp;Motivo Desabilitado</td>
                        </tr>
                    </table>
                    </div>
                </td>
                </tr>
                <tr>
                    <td align="left" ><img hspace="8" vspace="6" style="border:none;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/></td>
                    <td align="right"><img hspace="8" vspace="6" style="border:none;" onClick="incluir(); return false;" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif"/></td>
                </tr>
            </table>
            </form>
            <vivo:quadroFlutuante id="divIncluirAlterar" idIframe="ifrmIncluirAlterar"  scroll="no" spacesLeft="50" spacesTop="100" url="<%=request.getContextPath()%>" label="Editar Motivo" display="none" height="100" width="650"/>
            <vivo:quadroFlutuante id="divAssoc" idIframe="ifrmAssoc" spacesLeft="120" scroll="no" spacesTop="50" url="<%=request.getContextPath()%>" label="Associar Ações em Motivos" display="none" height="245" width="500"/>

        </vivo:sessao>
    </netui-template:section>
</netui-template:template>
