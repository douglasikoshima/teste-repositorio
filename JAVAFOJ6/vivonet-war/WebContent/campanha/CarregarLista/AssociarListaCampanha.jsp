<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associarListaActionForm" />
<bean:define id="aListaCanal"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associarListaActionForm.listaCanal" />
<bean:define id="aListaDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associarListaActionForm.listaDisp" />
<bean:define id="aListaUtil"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associarListaActionForm.listaUtil" />

<html>
   <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
		<script language="javascript">

			  function TransfereSelecaoLista(listaOrigem, listaDestino)
			  {

				for(var i = 0; i < listaOrigem.options.length; i++) {
						if(listaOrigem.options[i].selected) {
								var opt = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
								listaOrigem.options[i] = null;
								i--;
								listaDestino.options[listaDestino.options.length] = opt;

						}
					}
			   }

			   function salvar()
			   {
					var tmp = document.associarListaActionForm.elements["canalSelecionado"];
					if(isNaN(tmp.options[tmp.selectedIndex].value) || tmp.options[tmp.selectedIndex].value == "")
					{
						alert("Favor selecionar um Canal Atendimento!");
					}else{

							 for(var i = 0; i < document.associarListaActionForm.listaSelecionadaUtil.options.length; i++)
							 {
								document.associarListaActionForm.listaSelecionadaUtil.options[i].selected = true;
							 }

							 for(var i = 0; i < document.associarListaActionForm.listaSelecionadaDisp.options.length; i++)
							 {
								document.associarListaActionForm.listaSelecionadaDisp.options[i].selected = true;
							 }

							 document.associarListaActionForm.action = document.associarListaActionForm.action + "?acao=salvar";
							 parent.mostrar_div();
							 document.associarListaActionForm.submit();
					}
			   }

         function changeCanal()
         {
            var tmp = document.associarListaActionForm.elements["canalSelecionado"];
			if(isNaN(tmp.options[tmp.selectedIndex].value) || tmp.options[tmp.selectedIndex].value == "")
			{
				alert("Canal Atendimento não selecionado!");
			}
			else
			{
				parent.mostrar_div();
				document.associarListaActionForm.submit();
			}
         }

		</script>

    <SCRIPT FOR=window EVENT=onload>
        parent.oculta_div();
    </script>

    </head>

    <acesso:controlHiddenItem nomeIdentificador="cam_alca_verpagina">

	<table height="100%" bgcolor="#ededed" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>

							<form name="associarListaActionForm" action="AssociarListaAction.do" method="post">
							<table border="0"  width="100%" cellpadding="1" cellspacing="10" >
							<tr>
								<td colspan="2" valign="top" align="center">
									<table border="0" cellspacing="0" cellpadding="3" >
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" valign="top">
                                                <netui:label value="Canal Atendimento:"/></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td valign="top">
													<html:select name="Form" property="canalSelecionado" size="1" style="width=220px;height=10px">
															<html:option value=""></html:option>
															<html:options collection="aListaCanal" property="codigo" labelProperty="descricao"/>
													</html:select>
												</td>
												<td>
                                                <acesso:controlHiddenItem nomeIdentificador="cam_alca_pesq">
													<img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="changeCanal();" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" border="0" style="border:none;cursor:hand;"/>
												</acesso:controlHiddenItem>
                                                </td>
                                   </table>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" valign="top">
											<table width="100%" border="0" cellspacing="1" cellpadding="2">
											 <tr>
												<td align="center" class="tblEstatica_campoNome" valign="top">Listas Disponíveis</td>
												<td>&nbsp;</td>
												<td align="center" class="tblEstatica_campoNome" valign="top">Listas Usadas</td>
											</tr>
											<tr>
												<td align="center">
													 <html:select name="Form" property="listaSelecionadaDisp" size="10" style="width=200px;height=110px" multiple="true" ondblclick="javascript: TransfereSelecaoLista(associarListaActionForm.listaSelecionadaDisp, associarListaActionForm.listaSelecionadaUtil);">
															<html:options collection="aListaDisp" property="codigo" labelProperty="descricao"/>
													</html:select>
												</td>
												<td align="center">
														<table width="100%" border="0" cellspacing="1" cellpadding="1">
														<tr>
															<td align="center">
																<img id="btRightSimp2" onclick=" TransfereSelecaoLista(associarListaActionForm.listaSelecionadaDisp, associarListaActionForm.listaSelecionadaUtil);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"  style="boder:none"/>
															</td>
														</tr>
														<tr>
															<td align="center">
																<img id="btLeftSimp2" onclick=" TransfereSelecaoLista(associarListaActionForm.listaSelecionadaUtil, associarListaActionForm.listaSelecionadaDisp);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"  style="boder:none"/>
															</td>
														</tr>
														</table>
												  </td>
												  <td align="center">
														 <html:select name="Form" property="listaSelecionadaUtil" size="10" style="width=200px;height=110px" multiple="true" ondblclick="javascript: TransfereSelecaoLista(associarListaActionForm.listaSelecionadaUtil, associarListaActionForm.listaSelecionadaDisp);">
																<html:options collection="aListaUtil" property="codigo" labelProperty="descricao"/>
														</html:select>
												  </td>
											</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td colspan="3">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" valign="top">
											<table width="100%" border="0" cellspacing="1" cellpadding="0">
											<tr>
													<td colspan="4">&nbsp;</td>
											</tr>
											<tr>
													<td width="10">&nbsp;</td>
													<td width="285">&nbsp;</td>
													<td align="right" width="30">
                                                    <acesso:controlHiddenItem nomeIdentificador="cam_alca_grav">
														<img onclick=" salvar();" src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_registrar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif';" border="0" style="border:none;cursor:hand;"/>
													</acesso:controlHiddenItem>
                                                    </td>

											</tr>
											</table>
										</td>
									</tr>
									</table>
									<html:hidden name="Form" property="idCanalCampanha" />
							</form>
				</td>
		  </tr>
		  </table>
     </acesso:controlHiddenItem>
</html>