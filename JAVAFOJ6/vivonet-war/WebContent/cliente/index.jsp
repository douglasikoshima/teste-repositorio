<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Bem-Vindo"/>
    <netui-template:setAttribute name="modulo" value="Bem-Vindo"/>
    <netui-template:section name="headerSection">
    <%
    String isSql = (String) request.getAttribute("SQL");
    if(isSql!=null && ConstantesCRM.SONE.equals(isSql)){%>
        <script language="javascript">
            pesquisar = function() {
                if ($F('sqlCommand').blank()) {
                    alert("Favor especificar o comando a ser executado.");
                } else {
                    if (self.$('dvAnimarAguarde')) self.startAnimation();
                    var params = $H();
                    params.set('sqlCommand', $F('sqlCommand'));
                    $('containerPesquisa').update('');
                    new Ajax.Updater('containerPesquisa', 'pesquisar.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();

                        }, onFailure: function(e) {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                        }
                    });
                }
            }
        </script>
    <%}%>
    </netui-template:section>
    <netui-template:section name="bodySection">
	<!-- acesso : controlHiddenItem nomeIdentificador="cli_idxcliente_verpagina"-->
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <!--APLICACAO-->
    <%
    String isSql = (String) request.getAttribute("SQL");
    if(isSql!=null && ConstantesCRM.SONE.equals(isSql)){%>
    <form action="pesquisar.do" id="formPesquisa" style="margin:0;">
        <table width="790" height="" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">
                    <textarea cols="80" rows="10" id="sqlCommand" name="sqlCommand"></textarea>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="center">
                    <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisar();" style="border:none;cursor:pointer;">
                </td>
            </tr>
        </table>
    </form>
    <div id="containerPesquisa" style="border:solid 1px #000;width:790px;height:315px;overflow:scroll;"></div>
    <%}%>
	<!-- /acesso : controlHiddenItem-->
  </netui-template:section>
</netui-template:template>
