<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm" />

<html>
   <head>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <link href="/FrontOfficeWeb/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
		<script language="javascript" src="/FrontOfficeWeb/resources/scripts/xtree.js"></script>
		<script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/manterArvoreCampanha.js" charset="ISO-8859-1"></script>

        <SCRIPT FOR=window EVENT=onload>
            parent.oculta_div();

            window.name = "ifrmAbasCampanha";

            var idCanalAux = document.frmSelecao.idCanalCampanha.value;
            idCanal = <%= request.getAttribute("idCanalCampanha") %>;
            if(idCanalAux ==idCanal) {
                document.getElementById("Acao").style.display = "block";
                document.frmSelecao.idCanalCampanha.value = idCanal;
            }

            document.getElementById("btnIncluir").style.display = "none";
            document.getElementById("btnAlterar").style.display = "none";
            document.getElementById("btnExcluir").style.display = "none";
        </script>
        <vivo:alert atributo="msgErrorViewScript" />
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_vsc_verpagina">
    <vivo:quadroFlutuante scroll="yes" id="dvAuxiliar" idIframe="ifrmAuxiliar" height="335" width="770" spacesTop="2" spacesLeft="2" display="none" url="<%=request.getContextPath()%>" label=""/>
        <form id="frmSelecao" name="frmSelecao" method="post"/>
            <html:hidden name="Form" property="idCampanha"/>
            <html:hidden name="Form" property="idSubCampanha"/>
            <html:hidden name="Form" property="idCanalCampanha"/>
            <html:hidden name="Form" property="dsCanalCampanha"/>
            <input type="hidden" name="idPergunta" value="">
            <input type="hidden" name="idResposta" value="">
            <input type="hidden" name="idStatusCampanha" value="">
            <input type="hidden" name="idTipoApresentacao" value="">
            <input type="hidden" name="sgTipoApresentacaoPergunta" value="">
        </form>
	<form action="CarregarAction">
	</form>
	 <table bgcolor="#ededed" height="100%" width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td align="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td align="center">
				<tr valign="top">
                    <td>
                        <div style="top: 0px; left: 0px; height: 340px;  width: 720px; padding: 5px; overflow: auto; scrolling=true;">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <script  charset="ISO-8859-1">
                                    <%= request.getSession().getAttribute(ConstantesCRM.CT_ARVORE_CAMPANHA) %>
                                    <%  request.getSession().removeAttribute(ConstantesCRM.CT_ARVORE_CAMPANHA); %>
                                </script>
                            </td>
                        </tr>
                        </table>
                        </div>
                    </td>
                    <td width="30%">
                            <div id="Acao" style="position:absolute; left:500px; top:320px; display:none">
                                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td align="center" valign="middle">
                                    <acesso:controlHiddenItem nomeIdentificador="cam_vsc_incl">
                                        <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif" style="border:none" onClick="Insert(); parent.mostrar_div();" id="btnIncluir" />
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td align="center" valign="middle">
                                    <acesso:controlHiddenItem nomeIdentificador="cam_vsc_alt">
                                        <img src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_alterar_over.gif" style="border:none" onClick="Update(); parent.mostrar_div();" id="btnAlterar"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td align="center" valign="middle">
                                    <acesso:controlHiddenItem nomeIdentificador="cam_vsc_exc">
                                        <img src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_excluir_over.gif" style="border:none" onClick="Delete(); " id="btnExcluir" />
                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                                </table>
                            </div>
                    </td>
                </tr>
                </table>
				</td>
		  </tr>
	</table>
<iframe name="ifrArvore" id="ifrArvore" width="0" height="0" style="display:none" scrolling="no" src="iframeArvore.jsp"/>
</acesso:controlHiddenItem>
</html>