<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="VivoNet"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script language="JavaScript">
            <!--
                var aba = new Abas();
                var abaResultado;
                var abaFiltro;

                function desabilitaSubmit(){
                    aba.desabilitaAbas();
                }

                function habilitaSubmit(){
                    aba.habilitaAbas();
                }

                function initPagina(){
                    top.frameApp.mostrar_div();

                    abaFiltro = aba.criaAba("Filtros para Pesquisa", "/FrontOfficeWeb/VOLE/Parametrizacao/abaFiltro.do");
                    $(abaFiltro).onclick = new Function("selecionaAbaFiltro(true);");

                    abaResultado = aba.criaAba("Resultado de Pesquisa", "/FrontOfficeWeb/VOLE/Parametrizacao/resultadoPesquisa.do");
                    $(abaResultado).onclick = new Function("showMessage();");

                    aba.selecionaAba($(abaFiltro), true);
                }

                function showMessage(){
                    alert('Antes de configurar os itens de menu efetue uma pesquisa. Utilize o botão pesquisar');
                }

                function selecionaAbaResultado(bool){
                    aba.selecionaAba($(abaResultado), bool);
                }

                function selecionaAbaFiltro(bool){
                    top.frameApp.mostrar_div();
                    aba.selecionaAba($(abaFiltro), bool);
                }

                onload = function(){
                    initPagina();
                }

            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <!-- Menu de Administração de Sistemas -->
        <div id="menuPrincipal"><jsp:include page="/resources/menus/MenuPrincipal.jsp" /></div>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <!--APLICACAO-->
        <vivo:body idTable="tbMain" idDiv="dvMain" height="498" width="790">
            <vivo:quadro id="qdMain" height="473" width="780" label="VOL-E > Manutenção de Menu">

                <table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;margin-left:5px;">
                    <tr id="trAbas">
                </table>
                <table width="760" height="410" cellpadding="0" cellspacing="0" class="BorderTable1" style="margin-left:5px;">
                    <tr>
                        <td valign="top" class="tbl_bgGray">
                            <iframe id="fraAbas" width="760" height="410" frameborder="0" scrolling="no" src="about:blank"></iframe>
                        </td>
                    </tr>
                </table>
                <img hspace="8" vspace="6" style="border:none;" onclick="window.location.href='/FrontOfficeWeb/index.jsp';return false;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'"/>
            </vivo:quadro>
        </vivo:body>

    </netui-template:section>
</netui-template:template>
