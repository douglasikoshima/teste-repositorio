<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="pageFlow" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Executar Campanha"/>
    <netui-template:setAttribute name="modulo" value="Campanha"/>
    <netui-template:section name="headerSection">
    <head>
        <title></title>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/executarCampanha.js" charset="ISO-8859-1"></script>
    </head>
    <form action="LigarManualmente.do" method="post" name="ligarManualmenteForm">
        <input type="hidden" name="campanha" value="">
        <input type="hidden" name="idSubCampanhaHistoricoSel" value="">
    </form>
    </netui-template:section> <netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <acesso:controlHiddenItem nomeIdentificador="cam_atc_verpagina">
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <vivo:sessao id="correpDevolvida" width="790" height="470" label="Campaha" operacoes="Atendimento" scroll="no">
    <form name="viewScriptActionForm" action="ViewScriptAction.do" method="post">

        <html:hidden name="pageFlow" property="idListaConteudo" />

        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tbl_bgGray">
            <tr class="corpo" >
                <td valign="top">
                <table border="0" cellpadding="0" width="100%">
                    <tr>
                        <td class="tbl_bgblue" align="center" width="50%"><b><netui:label value="Número"/></b></td>
                        <td class="tbl_bgblue" align="center" width="50%"><b><netui:label value="Contato"/></b></td>
                    </tr>
                    <tr>
                        <td align="center">
                        &nbsp;<bean:write name="pageFlow" property="numeroLinha" />&nbsp;
                        </td>
                        <td align="center">
                        &nbsp;<bean:write name="pageFlow" property="propietarioLinha" />&nbsp;
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

        <iframe src="/FrontOfficeWeb/campanha/ExecutarCampanha/getCampanhasRelacionadas.do"
                frameborder="0"
                width="780"
                height="360"
                id="dvQuest"
                name="frmQuest"
                scrolling="no"></iframe>

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <table id="lyrBotoes" width="100%" border="0" cellspacing="5" cellpadding="2" class="tbl_bgGray">
                <tr>
                    <td align="left">
                        <img align="letf" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="top.location.href='/FrontOfficeWeb/index.jsp'; return false" hspace="5" vspace="5" style="border:none;cursor:hand;"/>
                    </td>
                    <td align="center">
                    <acesso:controlHiddenItem nomeIdentificador="cam_atc_ligarmanu">
                        <img src="/FrontOfficeWeb/resources/images/bt_ligarman_nrml.gif" border="0" onMouseOver=" this.src = '/FrontOfficeWeb/resources/images/bt_ligarman_over.gif';" style="cursor:hand; border:none;"  onMouseOut=" this.src = '/FrontOfficeWeb/resources/images/bt_ligarman_nrml.gif';" onClick="atendimentoCampanha_ligarManualmente();"/>
                    </acesso:controlHiddenItem>
                    </td>
                    <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cam_atc_ok">
                     <img src="/FrontOfficeWeb/resources/images/bt_atendeu_nrml.gif" onMouseOut=" this.src = '/FrontOfficeWeb/resources/images/bt_atendeu_nrml.gif';" border="0" onMouseOver=" this.src = '/FrontOfficeWeb/resources/images/bt_atendeu_over.gif';" style=" cursor:hand; border:none;" onClick="atendimentoCampanha_executarCampanha();"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
        <table id="lyrBotaoVoltar" width="100%" border="0" cellspacing="5" cellpadding="2" class="tbl_bgGray" style="display:none;">
                    <tr>
                        <td align="left">
                            <img align="letf" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="top.location.href='/FrontOfficeWeb/index.jsp'; return false" hspace="5" vspace="5" style="border:none;cursor:hand;"/>
                        </td>
                    </tr>
            </table>

        </td>
        </tr>
        </table>

    </form>
    </vivo:sessao> </acesso:controlHiddenItem> </netui-template:section>
</netui-template:template>