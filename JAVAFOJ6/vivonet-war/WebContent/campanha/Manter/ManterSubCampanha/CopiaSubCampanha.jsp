<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm" />
<bean:define id="aCanalOrig" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaCanalOrig" />
<bean:define id="aCampanhaDest" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaCampanhaDest" />
<bean:define id="aSubCampanhaDest" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaSubCampanhaDest" />
<bean:define id="aVersaoDest" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaVersaoDest" />
<bean:define id="aCanalDest" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="copiaPerguntasActionForm.listaCanalDest" />

<html>
    <head>
        <title> Web Application Page </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
		<script language="javascript">

				/*
                function ConsultaIFrameSubCampanha() {

					 var idCampanha = document.forms["copiaPerguntasActionForm"].campanhaOrigSelecionada.options[document.forms["copiaPerguntasActionForm"].campanhaOrigSelecionada.selectedIndex].value;

					 //Monta o path seleção
					 document.forms["copiaPerguntasActionForm"].target = "iframe";
					 document.forms["copiaPerguntasActionForm"].action = "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/obtemSubCampanha.do?acao=campanha&idCampanha="+idCampanha;
					 document.forms["copiaPerguntasActionForm"].submit();

				}

				function ConsultaIFrameCanal() {

					 var idCampanha		= document.forms["copiaPerguntasActionForm"].campanhaOrigSelecionada.options[document.forms["copiaPerguntasActionForm"].campanhaOrigSelecionada.selectedIndex].value;
					 var idSubCampanha	= document.forms["copiaPerguntasActionForm"].subCampanhaOrigSelecionada.options[document.forms["copiaPerguntasActionForm"].subCampanhaOrigSelecionada.selectedIndex].value;

					 //Monta o path seleção
					 document.forms["copiaPerguntasActionForm"].target = "iframe";
					 document.forms["copiaPerguntasActionForm"].action = "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/obtemSubCampanha.do?acao=subcampanha&idCampanha="+idCampanha + "&idSubCampanha="+idSubCampanha;
					 document.forms["copiaPerguntasActionForm"].submit();
				}
                */

				function ConsultaIFrameSubCampanhaDest() {

                    var objForm = document.forms["copiaPerguntasActionForm"];

                    if(objForm.campanhaDestSelecionada.selectedIndex > 0)
                    {
                         objForm.target = "iframe";
                         objForm.action = "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/obtemSubCampanha.do";
                         objForm.submit();
                         parent.mostrar_div();
                     }
                     else //Nenhum registro selecionado
                     {
                        objForm.subCampanhaDestSelecionada.options.length = 0;
                        objForm.versaoDestSelecionada.options.length = 0;
                        objForm.canalSelecionadoDest.options.length = 0;
                     }
				}

				function consultaIFrameCanalDest()
                {
                    var objForm = document.forms["copiaPerguntasActionForm"];

                    if(objForm.versaoDestSelecionada.selectedIndex >= 0)
                    {
                        objForm.target = "iframe";
                        objForm.action = "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/obtemCanais.do";
                        objForm.submit();
                        parent.mostrar_div();
                    }
                    else //Nenhum registro selecionado
                    {
                        objForm.canalSelecionadoDest.options.length = 0;
                    }

				}

				function ConsultaIFrameVersaoDest()
                {
                    var objForm = document.forms["copiaPerguntasActionForm"];

                    if(objForm.subCampanhaDestSelecionada.selectedIndex > 0)
                    {
                         objForm.target = "iframe";
                         objForm.action = "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/obtemVersoes.do";
                         objForm.submit();
                         parent.mostrar_div();
                     }
                     else //Nenhum registro selecionado
                     {
                        objForm.versaoDestSelecionada.options.length = 0;
                        objForm.canalSelecionadoDest.options.length = 0;
                     }
				}

				function postarDados()
				{
                    var objForm             = document.forms["copiaPerguntasActionForm"];
					var idCanalCampanha     = getIdSelecionado(objForm.canalSelecionadoOrig);
					var idCanalCampanhaDest = getIdSelecionado(objForm.canalSelecionadoDest);
					var idCampanhaDest      = getIdSelecionado(objForm.campanhaDestSelecionada);
					var idSubCampanhaDest   = getIdSelecionado(objForm.subCampanhaDestSelecionada);
                    var idVersaoDest        = objForm.versaoDestSelecionada;

					if(idCanalCampanha < 0)
                    {
						alert("Canal Atendimento de origem não selecionado!");
                        objForm.canalSelecionadoOrig.focus();
					}
                    else if(idCampanhaDest < 0)
                    {
						alert("Campanha destino não selecionada!");
                        objForm.campanhaDestSelecionada.focus();
					}
                    else if(idSubCampanhaDest < 0)
                    {
						alert("Sub Campanha destino não selecionada!");
                        objForm.subCampanhaDestSelecionada.focus();
					}
                    else if(idCanalCampanhaDest < 0)
                    {
						alert("Canal Atendimento de destino não selecionado!");
                        objForm.canalSelecionadoDest.focus();
					}
                    else if(idVersaoDest < 0)
                    {
                        alert("Versão de destino não selecionado!");
                        objForm.versaoDestSelecionada.focus();
                    }
					else
					{
						if(idCanalCampanha != idCanalCampanhaDest)
						{
							objForm.target = "";
							objForm.action = "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/copiaPerguntasSalvar.do";
							objForm.submit();
                            parent.mostrar_div();
						}
						else
						{
							alert("Canal Atendimento origem deve ser diferente do destino!");
						}
					}
				}

				function getIdSelecionado(obj)
				{
					idSelecionado = -1;

					if(obj.selectedIndex >0)
						idSelecionado = obj.options[obj.selectedIndex].value;


					return 	idSelecionado;
				}
		</script>
    <SCRIPT FOR=window EVENT=onload>
        parent.oculta_div();
    </script>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_cscm_verpagina">

    <table bgcolor="#ededed" height="100%" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center" valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
                        <tr>
                            <td valign="top" align="center">
                            <form name="copiaPerguntasActionForm" id="copiaPerguntasActionForm" action="CopiaPerguntasAction.do">
                                <table border="0" width="100%" cellpadding="1" cellspacing="10" >
                                    <tr>
                                        <td colspan="2" valign="top">
                                        <table width="100%" border="0" cellspacing="1" cellpadding="2">
                                            <tr class="corpo">
                                                <td align="center" colspan="2">Origem</td>
                                            </tr>
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" align="right"><netui:label value="Canal de Atendimento:"/>
                                                </td>
                                                <td align="left">
                                                <html:select name="Form" property="canalSelecionadoOrig" size="1" style="width=400px;height=10px">
													<html:option value=""></html:option>
													<html:options collection="aCanalOrig" property="codigo" labelProperty="descricao"/>
												</html:select>
                                                </td>
                                            </tr>
                                            <tr class="corpo">
                                                <td align="center" colspan="2">Destino</td>
                                            </tr>
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" align="right"><netui:label value="Campanha:"/>
                                                </td>
                                                <td align="left">
                                                <html:select name="Form" property="campanhaDestSelecionada" size="1" style="width=400px;height=10px" onchange="ConsultaIFrameSubCampanhaDest();">
													<html:option value=""></html:option>
													<html:options collection="aCampanhaDest" property="codigo" labelProperty="sigla"/>
												</html:select>
                                                </td>
                                            </tr>
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" align="right"><netui:label value="Sub Campanha:"/>
                                                </td>
                                                <td align="left">
                                                <html:select name="Form" property="subCampanhaDestSelecionada" size="1" style="width=400px;height=10px" onchange="ConsultaIFrameVersaoDest();">
													<html:option value=""></html:option>
													<html:options collection="aSubCampanhaDest" property="codigo" labelProperty="nmSubCampanha"/>
												</html:select>
                                                </td>
                                            </tr>
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" align="right"><netui:label value="Versão:"/>
                                                </td>
                                                <td align="left">
                                                <html:select name="Form" property="versaoDestSelecionada" size="1" style="width=400px;height=10px" onchange="consultaIFrameCanalDest();">
													<html:options collection="aVersaoDest" property="codigo" labelProperty="descricao"/>
												</html:select>
                                                </td>
                                            </tr>
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" align="right"><netui:label value="Canal de Atendimento:"/>
                                                </td>
                                                <td align="left">
                                                <html:select name="Form" property="canalSelecionadoDest" size="1" style="width=400px;height=10px">
													<html:option value=""></html:option>
                                                    <html:options collection="aCanalDest" property="codigo" labelProperty="descricao"/>
												</html:select>
                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" valign="top">
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="tbl_bgGray"> </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" valign="top">
                                        <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                            <tr>
                                                <td width="525">&nbsp;</td>
												<td align="left" width="">
                                                <acesso:controlHiddenItem nomeIdentificador="cam_cscm_copi">
													<img
                                                        src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif"
                                                        border="0" onClick="postarDados();"
                                                        onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'"
                                                        onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'"
                                                        style="border:none;cursor:hand;"/>
                                                </acesso:controlHiddenItem>
                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                    </tr>
                                </table>
                                <iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="0px" width="0px"></iframe>
                                </tr> </table>
                            </form>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>
            </acesso:controlHiddenItem>
            <vivo:alert atributo="isErrorCopiaSubCampanha" />
</html>