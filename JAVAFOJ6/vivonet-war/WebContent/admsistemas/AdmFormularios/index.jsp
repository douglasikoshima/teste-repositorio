<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
        <!-- Script para criacao da abas -->
        <script>
            <!--
                var aba = new Abas();
                var abaCampos;

                function desabilitaSubmit(){
                    aba.desabilitaAbas();
                }

                function habilitaSubmit(){
                    aba.habilitaAbas();
                }

                function initPagina(){
                    //top.frameApp.mostrar_div();

                    abaCampos = aba.criaAba("Campos/Valores", "/FrontOfficeWeb/admsistemas/AdmFormularios/loadCamposForm.do");
                    document.getElementById(abaCampos).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");

                    aba.selecionaAba(document.getElementById(abaCampos), true);
                    aba.habilitaAbas();
                }

                function selecionaAbaCampos(){
                    aba.selecionaAba(document.getElementById(abaCampos), false);
                }
            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="manutencao" width="790" height="480" label="Campos Dinâmicos" operacoes="Manutenção de Campos" scroll="no">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="2"></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="0">
                <tr id="trAbas">
            </table>
            <table width="780" height="445" cellpadding="0" cellspacing="0" class="BorderTable1">
                <tr>
                    <td valign="top" class="tbl_bgGray">
                        <iframe id="fraabas" width="777" height="420" frameborder=0 scrolling=no src="about:blank"></iframe>
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>
            <script language="javaScript">
                initPagina();
            </script>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>