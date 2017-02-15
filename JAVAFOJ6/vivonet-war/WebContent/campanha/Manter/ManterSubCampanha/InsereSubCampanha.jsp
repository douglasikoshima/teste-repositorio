<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCampanhaActionForm" />
<bean:define id="aCanalDisp"	name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"	property="manterCampanhaActionForm.listaCanalDisp" />
<bean:define id="aCanalUtil"	name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"	property="manterCampanhaActionForm.listaCanalUtil" />
<bean:define id="aPerfilDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCampanhaActionForm.listaPerfilDisp" />
<bean:define id="aPerfilUtil"	name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCampanhaActionForm.listaPerfilUtil" />
<bean:define id="aTipoCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCampanhaActionForm.listaTipoCampanha" />

<html>
   <head>
        <title>
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
		<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
		<script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
		<link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
		<script language="JavaScript">

				function TransfereCanal(listaOrigem, listaDestino) {
					for(var i = 0; i < listaOrigem.options.length; i++) {
						if(listaOrigem.options[i].selected) {
								var opt = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
								listaOrigem.options[i] = null;
								i--;
								listaDestino.options[listaDestino.options.length] = opt;

						}
					}
				}

				function TransferePerfil(listaOrigem, listaDestino) {
					for(var i = 0; i < listaOrigem.options.length; i++) {
						if(listaOrigem.options[i].selected) {
								var opt = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
								listaOrigem.options[i] = null;
								i--;
								listaDestino.options[listaDestino.options.length] = opt
						}
					}
				}

			function salvar(sAcao)
			{

                /*
                    Pablo 30-09-04
                    Início dos atributos para consistência de datas
                */

                var dia  = new Date();
                var mes  = dia.getMonth() + 1;
                var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

                /*
                    Fim dos atributos para consistência de datas
                */

                if(document.manterCampanhaActionForm.iRecontato.checked)
                {
                    if(document.manterCampanhaActionForm.iAgenda.value == "" || isNaN(document.manterCampanhaActionForm.iAgenda.value))
                    {
                        //iAgenda
                        alert("Favor preencher a Agenda Máxima!")
                        document.manterCampanhaActionForm.iAgenda.focus();
                        return false;
                    }
                    else if(parseInt(document.manterCampanhaActionForm.iAgenda.value) < 0)
                    {
                        alert("Favor preencher a Agenda Máxima com um valor maior que zero!");
                        document.manterCampanhaActionForm.iAgenda.focus();
                        return false;
                    }
                }

                if(trim(document.manterCampanhaActionForm.sgCampanha.value) == "")
                {
                    alert("Favor preencher o campo Sub Campanha!")
                    document.manterCampanhaActionForm.sgCampanha.focus();
					document.manterCampanhaActionForm.sgCampanha.select();
				}
                else if( trim( document.manterCampanhaActionForm.dtInicio.value ) == "" || !validaData( document.manterCampanhaActionForm.dtInicio.value ) )
                {
                    alert("Data de Início inválida!");
                    document.manterCampanhaActionForm.dtInicio.focus();
				}
                else if( trim( document.manterCampanhaActionForm.dtTermino.value ) == "" || !validaData( document.manterCampanhaActionForm.dtTermino.value ) )
                {
					alert("Data de Término inválida!");
                    document.manterCampanhaActionForm.dtTermino.focus();
				}
				else if(trim(document.manterCampanhaActionForm.nmSubCampanha.value) == "")
                {
                    alert("Nome de sub campanha não pode ser vazio, favor corrigir!");
                    document.manterCampanhaActionForm.nmSubCampanha.focus();
                }
                else if(document.manterCampanhaActionForm.tipoCampanhaSelecionado.value == "")
                {
					alert("Favor selecionar um Tipo de Campanha!");
                    document.manterCampanhaActionForm.tipoCampanhaSelecionado.focus();

				}

                else if(trimTextArea(document.manterCampanhaActionForm.dsSubCampanha.value) == "")
                {
					alert("Favor preencher o campo Resumo Sub Campanha!");
					document.manterCampanhaActionForm.dsSubCampanha.select();
                    document.manterCampanhaActionForm.dsSubCampanha.focus();
				}
                else if(document.manterCampanhaActionForm.canalUtilSelecionado.options.length && document.manterCampanhaActionForm.canalUtilSelecionado.options[0] == "")
                {
					alert("Favor incluir um Canal de Atendimento!");
                    document.manterCampanhaActionForm.canalUtilSelecionado.focus();
				}
                else if(!document.manterCampanhaActionForm.canalUtilSelecionado.options.length)
                {
					alert("Favor incluir um Canal de Atendimento!");
                    document.manterCampanhaActionForm.canalUtilSelecionado.focus();
				}
                else if(document.manterCampanhaActionForm.perfilUtilSelecionado.options.length && document.manterCampanhaActionForm.perfilUtilSelecionado.options[0] == "")
                {
					alert("Favor incluir um Grupo!");
                    document.manterCampanhaActionForm.perfilUtilSelecionado.focus();
				}
                else if(!document.manterCampanhaActionForm.perfilUtilSelecionado.options.length)
                {
					alert("Favor incluir um Grupo!");
                    document.manterCampanhaActionForm.perfilUtilSelecionado.focus();
                }
                <logic:equal value="incluir" name="acao">
                /*
                 Se for alteração de sub campanha
                 devo permitir a data inicio menor
                 que a data atual
                */
                else if(!validaDataFinal(hoje, document.manterCampanhaActionForm.dtInicio.value))
                {
                    alert("Data início menor que a data atual, favor corrigir!");
                    document.manterCampanhaActionForm.dtInicio.focus();
				}
                </logic:equal>

                else if(!validaDataFinal(document.manterCampanhaActionForm.dtInicio.value, document.manterCampanhaActionForm.dtTermino.value))
                {
                    alert("Data término menor que a data início, favor corrigir!");
                    document.manterCampanhaActionForm.dtTermino.focus();
                }

                else if(!validaDataFinal(hoje, document.manterCampanhaActionForm.dtTermino.value))
                {
                    alert("Data término menor que a data atual, favor corrigir!");
                    document.manterCampanhaActionForm.dtTermino.focus();
                }
                else
                {
					for(var i = 0; i < document.manterCampanhaActionForm.canalDispSelecionado.options.length; i++){
						document.manterCampanhaActionForm.canalDispSelecionado.options[i].selected = true;
					}
					for(var i = 0; i < document.manterCampanhaActionForm.canalUtilSelecionado.options.length; i++){
						document.manterCampanhaActionForm.canalUtilSelecionado.options[i].selected = true;
					}
					for(var i = 0; i < document.manterCampanhaActionForm.perfilDispSelecionado.options.length; i++){
						document.manterCampanhaActionForm.perfilDispSelecionado.options[i].selected = true;
					}
					for(var i = 0; i < document.manterCampanhaActionForm.perfilUtilSelecionado.options.length; i++){
						document.manterCampanhaActionForm.perfilUtilSelecionado.options[i].selected = true;
					}

                    if(sAcao == "versao")
                    {
                        document.manterCampanhaActionForm.target = "";
                        document.manterCampanhaActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/criarVersao.do";
                    }
                    else
                    {
                        document.manterCampanhaActionForm.action = document.manterCampanhaActionForm.action + "?processa="+sAcao;
                    }
                    top.frameApp.mostrar_div();
					document.manterCampanhaActionForm.submit();
				}
			}
			 function trimTextArea(valor)
			{

				while ((valor.substring(0,1) == ' ') || (valor.substring(0,1) == '\n') || (valor.substring(0,1) == '\r'))
				{
				valor = valor.substring(1,valor.length);
				}
				while ((valor.substring(valor.length-1,valor.length) == ' ') || (valor.substring(valor.length-1,valor.length) == '\n') || (valor.substring(valor.length-1,valor.length) == '\r'))
				{
				valor = valor.substring(0,valor.length-1);
				}
				return valor;
			}

		</script>

<SCRIPT FOR=window EVENT=onload>
    top.frameApp.oculta_div();


    //Mensagem,  caso já existir sub campanha com aquele
    //mesmo nome, na alteração ou inclusão de uma sub campanha.

    <logic:notEmpty name="form" property="erroMsg">
        alert("<bean:write name="form" property="erroMsg" />");
        document.manterCampanhaActionForm.nmSubCampanha.focus();
        document.manterCampanhaActionForm.nmSubCampanha.select();
    </logic:notEmpty>
</script>

</head>

<acesso:controlHiddenItem nomeIdentificador="cam_isca_verpagina">



    <table bgcolor="#ededed" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
			<form name="manterCampanhaActionForm" id="manterCampanhaActionForm" action="ProcessaSubCampanhaAction.do" method="post">
            <table width="100%" border="0" cellspacing="0" cellpadding="1" >
            <tr>
                 <td>
					 <table border="0"  width="100%" cellpadding="5" cellspacing="0">
                     <tr>
						 <td colspan="2" valign="top">
							        <table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
                                        <logic:greaterEqual name="form" property="idAcao" value="1" >
											<logic:greaterThan name="form" property="idVersao" value="0" >
												<td  width="20%" height="23" valign="middle"><b><netui:label value="Versão:"/></b></td>
												<td valign="top" width="30%">
													<bean:write name="form" property="idVersao" format="##"/>
												</td>
											</logic:greaterThan>
											<logic:equal name="form" property="idVersao" value="0" >
												<td  width="20%" height="23" valign="middle">&nbsp;</td>
												<td valign="top" width="30%">
													&nbsp;
												</td>
											 </logic:equal>
                                        </logic:greaterEqual>

                                        <logic:notEqual  name="form" property="idAcao" value="1" >
                                            <td  width="20%" height="23" valign="middle">&nbsp;</td>
                                            <td valign="top" width="30%">
                                                &nbsp;
                                            </td>
                                        </logic:notEqual>

                                        <td colspan="2">
                                            <table align="right">
                                                <tr valign="middle">
                                                    <td height="23"><b><netui:label value="Data Início:"/></b></td>
                                                    <td>
                                                    <logic:equal value="incluir" name="acao">
                                                        <html:text property="dtInicio" tabindex="1" name="form" styleClass="textfield" maxlength="10" onkeypress="this.value = Format(this.value,'##/##/####');" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');">
                                                    </logic:equal>
                                                    <logic:notEqual value="incluir" name="acao">
                                                        <html:text readonly="true" property="dtInicio" tabindex="1" name="form" styleClass="textfield" maxlength="10" size="10"/>&nbsp;&nbsp;
                                                    </logic:notEqual>
                                                    </td>
                                                    <td><b><netui:label value="Data Término:"/></b></td>
                                                    <td><html:text property="dtTermino" tabindex="2" name="form" styleClass="textfield" maxlength="10" onkeypress="this.value = Format(this.value,'##/##/####');" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtTermino', '%d/%m/%Y');"></td>
                                                </tr>
                                                </table>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td  width="20%" height="23" valign="middle"><b><netui:label value="Campanha:"/></b></td>
										<td valign="top" width="30%" colspan="3">
											<html:hidden property="sgCampanha" name="form"/>
											<bean:write name="form" property="sgCampanha" />
	                                    </td>
                                    </tr>
                                    <tr class="corpo">
                                        <td width="20%" valign="middle" height="23"><b><netui:label value="Tipo Campanha:"/></b></td>
                                        <td valign="top" width="30%">
											 <html:select name="form" tabindex="3" property="tipoCampanhaSelecionado" size="1" style="width=210px;height=10px">
												<html:options collection="aTipoCampanha" property="codigo" labelProperty="descricao"/>
											</html:select>
                                        </td>
                                        <td align="right" valign="middle" width="18%"><b><netui:label value="Público Alvo:"/></b></td>
                                        <td valign="top" width="30%" align="left">
                                                    <table border="0" cellspacing="0" cellpadding="0" width="80%">
                                                    <tr class="corpo">
                                                       <td width="50%" align="left" valign="middle">
															<html:radio name="form" tabindex="4" style="border:none;background-color:#ededed;" property="iCliente" value="1"/>Clientes
														</td>
														<td width="50%" align="left" valign="middle">
															<html:radio name="form" tabindex="5" style="border:none;background-color:#ededed;" property="iCliente" value="0"/>Não Clientes
                                                        </td>
                                                    </tr>
                                                    </table>
                                            </td>
                                    </tr>
									<tr>
										<td  width="20%"><b><netui:label value="Agenda Máxima:"/></b></td>
                                        <td valign="top" width="30%">
											<logic:equal name="form" property="iRecontato" value="1" >
											    <html:text property="iAgenda" name="form" styleClass="textfield" size="10"  maxlength="4" onkeyup="checaInteiro(this);"/>
											</logic:equal>
											<logic:equal name="form" property="iRecontato" value="0" >
											    <html:text property="iAgenda" name="form" styleClass="textfield" size="10" disabled="true"  maxlength="4" onkeyup="checaInteiro(this);"/>
											</logic:equal>
											&nbsp;
											<html:checkbox name="form" style="border:none;background-color:#ededed;" property="iRecontato" value="1" onclick="if(document.forms[0].iRecontato.checked){document.forms[0].iAgenda.disabled = false;}else{document.forms[0].iAgenda.disabled = true;document.forms[0].iAgenda.value='';}"/>&nbsp;<b><netui:label value="Aceita Recontato"/></b>
                                        </td>
										<td class="tblEstatica_campoNome" align="right"  width="20%">
                                        <netui:label value="Ativa:"/>
                                        </td>
                                        <td  width="30%" valign="top" align="left">
                                            <table border="0" cellspacing="0" cellpadding="0" width="80%">
                                            <tr class="corpo">
                                                <td width="50%" align="left">
													<html:radio name="form" style="border:none;background-color:#ededed;" property="iStatus" value="1"/>Sim
												</td>
												<td width="50%" align="left">
													<html:radio name="form" style="border:none;background-color:#ededed;" property="iStatus" value="0"/>Não
                                                </td>
                                            </tr>
                                            </table>
                                        </td>
									</tr>
									<tr>
										<td height="23" width="20%" valign="middle"><b><netui:label value="Nome Sub Campanha:"/></b></td>
										<td height="23" width="30%">
											<html:text property="nmSubCampanha" name="form" style="font: arial;color: #006699;width=210px;" maxlength="255"/>
										</td>
										<td height="23" width="20%" align="right">
											<b><netui:label value="Fidelização"/></b>&nbsp;
										</td>
                                        <td height="23" width="30%">
                                            <html:checkbox name="form" style="border:none;background-color:#ededed;" property="inFidelizacao" value="1"/>
                                        </td>
									</tr>
									<tr>
										<td valign="middle"  width="20%"><b><netui:label value="Resumo Sub Campanha:"/></b><td width="80" colspan="3">
											<html:textarea property="dsSubCampanha" name="form" rows="2" cols="61" style="font: arial;color: #006699;" onkeyup="checaTextarea(this,400);"/>
										</td>
									</tr>
                                    </table>
								</td>
						  </tr>
						  <tr>
								<td colspan="2" valign="top">
                                     <table width="100%" border="0" cellspacing="1" cellpadding="2" >
                                       <tr>

                                            <td align="center">
                                                <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                                <tr class="corpo">
                                                        <td class="tblEstatica_campoNome" valign="top"> <netui:label value="Canais Existentes:"/></td>
                                                        <td>&nbsp;</td>
                                                        <td class="tblEstatica_campoNome" valign="top"> <netui:label value="Canais Usados:"/></td>
                                                </tr>
                                                <tr>
                                                        <td valign="top">

                                                           <html:select name="form" property="canalDispSelecionado" size="2" style="width=320px;height=45px;" multiple="true"  ondblclick="javascript: TransfereCanal(manterCampanhaActionForm.canalDispSelecionado, manterCampanhaActionForm.canalUtilSelecionado);">
																<html:options collection="aCanalDisp" property="codigo"  labelProperty="descricao"/>
															</html:select>
                                                        </td>
                                                        <td align="center" width="90">
                                                            <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                                            <tr>
																<td align="center">
																	<img id="btRightSimp2" onclick=" TransfereCanal(manterCampanhaActionForm.canalDispSelecionado, manterCampanhaActionForm.canalUtilSelecionado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"  style="boder:none"/>
																</td>
															</tr>
															<tr>
																<td align="center">
																	<img id="btLeftSimp2" onclick=" TransfereCanal(manterCampanhaActionForm.canalUtilSelecionado,manterCampanhaActionForm.canalDispSelecionado);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"  style="boder:none"/>
																</td>
															</tr>
															</table>
                                                        </td>
                                                        <td valign="top">

                                                            <html:select name="form" property="canalUtilSelecionado" size="2" style="width=320px;height=45px" multiple="true" ondblclick="javascript: TransfereCanal(manterCampanhaActionForm.canalUtilSelecionado,manterCampanhaActionForm.canalDispSelecionado);">
																<html:options collection="aCanalUtil" property="codigo" labelProperty="descricao"/>
														   </html:select>
                                                        </td>
                                                </tr>
                                                </table>
                                            </td>
                                     </tr>
                                     <tr valign="top">

                                            <td align="center">
                                                <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                                <tr class="corpo">
                                                    <td class="tblEstatica_campoNome" valign="top"><netui:label value="Grupos Existentes:"/></td>
                                                    <td>&nbsp;</td>
                                                    <td class="tblEstatica_campoNome" valign="top"><netui:label value="Grupos que Podem Acessar a Sub Campanha:"/></td>
                                                </tr>
                                                <tr>
                                                        <td valign="top">
                                                           <html:select name="form" property="perfilDispSelecionado" size="2" style="width=320px;height=45px" multiple="true" ondblclick="javascript: TransferePerfil(manterCampanhaActionForm.perfilDispSelecionado, manterCampanhaActionForm.perfilUtilSelecionado);">
																<html:options collection="aPerfilDisp" property="codigo" labelProperty="descricao"/>
														   </html:select>
                                                        </td>
                                                        <td width="90" align="center">
                                                            <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                                            <tr>
																<td align="center">
																	<img id="btRightSimp2" onclick=" TransferePerfil(manterCampanhaActionForm.perfilDispSelecionado, manterCampanhaActionForm.perfilUtilSelecionado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"  style="boder:none"/>
																</td>
															</tr>
															<tr>
																<td align="center">
																	<img id="btLeftSimp2" onclick=" TransferePerfil(manterCampanhaActionForm.perfilUtilSelecionado,manterCampanhaActionForm.perfilDispSelecionado);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"  style="boder:none"/>
																</td>
															</tr>
															</table>
                                                        </td>
                                                        <td valign="top">
                                                            <html:select name="form" property="perfilUtilSelecionado" size="2" style="width=320px;height=45px" multiple="true" ondblclick="javascript: TransferePerfil(manterCampanhaActionForm.perfilUtilSelecionado,manterCampanhaActionForm.perfilDispSelecionado);">
																<html:options collection="aPerfilUtil" property="idGrupo" labelProperty="nmGrupo"/>
														   </html:select>
                                                        </td>
                                                </tr>
                                                </table>
                                            </td>
                                   </tr>
                                   </table>
								</td>
						  </tr>
						  <tr>
								<td colspan="2" valign="top">
                                      <table width="93%" border="0" cellspacing="1" cellpadding="0">
									  <tr>
                                            <td align="right">
											<logic:equal name="form" property="idAcao" value="0" >
													&nbsp;&nbsp;&nbsp;
                                                <acesso:controlHiddenItem nomeIdentificador="cam_isca_incl">
													<img id="Registrar" src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" onClick="salvar('salvar');" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'" style="border:none;cursor:hand;" />
                                                </acesso:controlHiddenItem>
											</logic:equal>
											<logic:greaterEqual name="form" property="idAcao" value="1" >
                                                <acesso:controlHiddenItem nomeIdentificador="cam_isca_incl">
													<img id="Registrar" src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" onClick="salvar('salvar');" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'" style="border:none;cursor:hand;" />
                                                </acesso:controlHiddenItem>
											</logic:greaterEqual>
											<logic:greaterEqual name="form" property="idAcao" value="1" >
												<logic:equal name="form" property="idVersao" value="1">
                                                    <acesso:controlHiddenItem nomeIdentificador="cam_isca_criar">
														<img id="Versao" src="/FrontOfficeWeb/resources/images/bt_criarversao_nrml.gif" onClick="salvar('versao');" onMouseOver="this.src = '/FrontOfficeWeb/resources/images//bt_criarversao_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_criarversao_nrml.gif'" style="border:none;cursor:hand;"/>
                                                    </acesso:controlHiddenItem>
												</logic:equal>
                                            </logic:greaterEqual>

                                            <logic:equal name="form" property="idAcao" value="0">



												<a href="javascript:window.location.href='voltarAction.do?voltarPara=nada'">
													<img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" border="0" style="border:none" />
												</a>



                                            </logic:equal>

                                        	</td>
									  </tr>
                                      </table>
								</td>
						  </tr>
						  </table>
				  </td>
	        </tr>
		</table>
		<html:hidden name="form" property="idCampanha" />
        <html:hidden name="form" property="idAcao" />
        <html:hidden name="form" property="idSubCampanha" />
        <html:hidden name="form" property="idSubCampanhaFixa" />
        <html:hidden name="form" property="idVersao" />

		  </form>
		</td>
  </tr>
  </table>


 </acesso:controlHiddenItem>
 </html>