<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configuraPrioridadeActionForm" />
<bean:define id="aCanal"		name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configuraPrioridadeActionForm.listaCanal" />
<html>
   <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
		<logic:equal name="Form" property="idAcao" value="3">
			<script>
				parent.abaSelected(parent.btAba, parent.bt01);
				window.location.href="../../Manter/ManterArvoreConfig/ViewScriptCampanha.jsp";
			</script>
		</logic:equal>
		<script language="javascript">
				
				
				function ConsultaIFramePrioridade()
				{
					 
					 var idPrioridade = document.forms["configuraPrioridadeActionForm"].listaSelecionada.options[document.forms["configuraPrioridadeActionForm"].listaSelecionada.selectedIndex].value;
					 if(idPrioridade == 2) 			
					 {
						document.all('canal').style.display = "block";
						
						//Monta o path seleção
						document.forms["configuraPrioridadeActionForm"].target = "iframe";
						document.forms["configuraPrioridadeActionForm"].action = "ObtemListaAction.do?acao=prioridade&idPrioridade="+idPrioridade;
						document.forms["configuraPrioridadeActionForm"].submit();
					 }
					 else
					 {
						document.all('canal').style.display = "none";
					 }
				}
				
				function ConsultaIFrameCanal()
				{
					 
					 var idCampanha = document.forms["configuraPrioridadeActionForm"].listaSelecionada.options[document.forms["configuraPrioridadeActionForm"].listaSelecionada.selectedIndex].value;
								
					 //Monta o path seleção
					 document.forms["configuraPrioridadeActionForm"].target = "iframe";
					 document.forms["configuraPrioridadeActionForm"].action = "ObtemListaAction.do?acao=campanha&idCampanha="+idCampanha;
					 document.forms["configuraPrioridadeActionForm"].submit();
				}
				
				
				
				function submitPesquisar()
				{
					if(document.forms["configuraPrioridadeActionForm"].listaCanalSelecionado.selectedIndex <=0)
					{
						alert("Canal Atendimento não selecionado!");
					}
					else
					{
						document.forms["configuraPrioridadeActionForm"].target = "";
						document.forms["configuraPrioridadeActionForm"].action = "ConfiguraPrioridadeAction.do?acao=carregar";
						document.forms["configuraPrioridadeActionForm"].submit();
                        parent.mostrar_div();
					}	
					
				}
				
				function salvar()
				{
					for(var i = 0; i < document.configuraPrioridadeActionForm.codigoSelecionado.options.length; i++)
					{
							document.configuraPrioridadeActionForm.codigoSelecionado.options[i].selected = true;
					}
						 
					for(var i = 0; i < document.configuraPrioridadeActionForm.ordemSelecionada.options.length; i++)
					{
							document.configuraPrioridadeActionForm.ordemSelecionada.options[i].selected = true;
					}
					
					document.forms["configuraPrioridadeActionForm"].target = "";
					document.forms["configuraPrioridadeActionForm"].action = "ConfiguraPrioridadeAction.do?acao=salvar";
					document.forms["configuraPrioridadeActionForm"].submit();
                    parent.mostrar_div();
				
				}
				
				function sobe(obj){
					index = obj.parentNode.id.split("_");
					index[1] = parseInt(index[1]);
					if(index[1] - 1 != -1){
						oldText1 = document.getElementById('texto_' + index[1]).innerText;
						oldText2 = document.getElementById('texto_' + (index[1] - 1)).innerText;
						oldID1 = document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1]].value;
						oldID2 = document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1] - 1].value;
						document.getElementById('texto_' + index[1]).innerText = oldText2;
						document.getElementById('texto_' + (index[1] - 1)).innerText = oldText1;
						document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1]].value = oldID2;
						document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1]].text = oldID2;
						document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1] - 1].value = oldID1;
						document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1] - 1].text = oldID1;
					}
				}
				
				function desce(obj){
					index = obj.parentNode.id.split("_");
					index[1] = parseInt(index[1]);
					if(document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1] + 1]){
						oldText1 = document.getElementById('texto_' + index[1]).innerText;
						oldText2 = document.getElementById('texto_' + (index[1] + 1)).innerText;
						oldID1 = document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1]].value;
						oldID2 = document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1] + 1].value;
						document.getElementById('texto_' + index[1]).innerText = oldText2;
						document.getElementById('texto_' + (index[1] + 1)).innerText = oldText1;
						document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1]].value = oldID2;
						document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1]].text = oldID2;
						document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1] + 1].value = oldID1;
						document.forms["configuraPrioridadeActionForm"].codigoSelecionado.options[index[1] + 1].text = oldID1;
					}
				}
		</script>
        <SCRIPT FOR=window EVENT=onload>
            parent.oculta_div();
        </script> 
                
    </head>
    
    <acesso:controlHiddenItem nomeIdentificador="cam_cpri_verpagina">
	 <table bgcolor="#ededed" height="100%" width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td valign="top" align="center" > 
			<form action="ConfiguraPrioridadeAction.do" method="post" name="configuraPrioridadeActionForm">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
				<td align="center" width="100%">
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr> 
						<td valign="top" width="100%"> 
							<table border="0"  width="100%" cellpadding="1" cellspacing="5" height="100%">
							<tr class="corpo"> 
								<td align="center">
									<table border="0" cellspacing="0" cellpadding="3" >
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" valign="top">
                                                <netui:label value="Canal Atendimento:"/></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td valign="top">
													<html:select name="Form" property="listaCanalSelecionado" size="1" style="width=400px;height=10px">
															<html:option value=""></html:option>
															<html:options collection="aCanal" property="codigo" labelProperty="descricao"/> 
													</html:select>
												</td>
												<td>
                                                <acesso:controlHiddenItem nomeIdentificador="cam_cpri_pesquisar">
													<img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onClick="submitPesquisar();" border="0" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" style="border:none;cursor:hand;"/>
												</acesso:controlHiddenItem>
                                                </td>	
                                   </table>
								
								</td>
							</tr>	
							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<logic:equal name="Form" property="idAcao" value="1">
						<tr>
							<td align="center">
							 <div class="holderTabela" id="tableTitle" style="width: 1px; height: 1px; overflow: visible">
								  <table border="0" cellpadding="0" cellspacing="0">
								  <tr>
										<td>
											<div class="headerTabela" style="width: 720px;overflow: hidden">
												<table id="tableTitle_header" border="0" cellpadding="0" cellspacing="0" width="700">
												<tr>

													<td class="normal" style="width:80px;">Ordem</td>
													<td class="normal" style="width:80px;">Ação</td>		
			                                        <td class="normal" style="width:460px;">Descrição</td>

												</tr>
												</table>
											</div>	
										</td>
									</tr>
									<tr>			
										<td>
												<div class="corpoTabela" style="width: 720px; height: 150px; overflow: auto" onScroll="sincronizarScrollLateral( this.parentElement.parentElement.parentElement.parentElement.parentElement ) ">
													<table id="tableTitle_body" class="selecionavel" border="0" cellpadding="0" cellspacing="0" width="700" onClick="" >
															 <logic:iterate name="Form" property="listaPergunta" id="Perguntas" indexId="index" >
																<bean:define name="Perguntas" id="idPergunta" property="idPergunta" />
																<bean:define name="Perguntas" id="sqApresentacao" property="sqApresentacao" />
																	<tr class='rowTabela'>		
																		<td align="center" style="width:80px;">&nbsp;<%="" + index + ""%>&nbsp;</td>
																		<td style="width:80px;" align="center" id='linha_<%="" + index + ""%>'  onMouseOver="hoverRow();" onMouseOut="unhoverRow();">
																			<img onClick="sobe(this); return false" src="/FrontOfficeWeb/resources/images/seta_up.gif" style="border:0px" />
																			<img onClick="desce(this); return false" src="/FrontOfficeWeb/resources/images/seta_down.gif" style="border:0px" />
																		</td>
																		<td style="width:460px;" id='texto_<%="" + index + ""%>'>&nbsp;
																			<div style="width: 460px; overflow: hidden;" ><vivo:hint maxLength="60"><bean:write  name="Perguntas" property="dsScriptPergunta"/></vivo:hint></div>
																		</td>
																	</tr>		
															</logic:iterate>
													</table>
												</div>
											</td>
									</tr>
									</table>			
								</div>							
							</td>	
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr> 
							<td>
								<table border="0" cellpadding="0" cellspacing="0" width="95%">
								<tr>
									<td width="85%">&nbsp;</td>
									<td align="right" width="15%">
                                    <acesso:controlHiddenItem nomeIdentificador="cam_cpri_gravar">
											<img src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" border="0" onClick="salvar();" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'" style="border:none;cursor:hand;" />
									</acesso:controlHiddenItem>
                                    </td>
								</tr>
								</table>
							</td>		 
						</tr>
					</logic:equal>	
					</table>
				</td>
			</tr>
			</table>
						<iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="1px" width="1px"></iframe>
						<netui:hidden dataSource="{actionForm.idSubCampanha}" />
						<html:select name="Form" property="codigoSelecionado" size="1" style="width=0px;height=0px;visibility:visible" multiple="true">
							<logic:iterate name="Form" property="listaPergunta" id="Perguntas" indexId="index" >
								<bean:define name="Perguntas" id="idPergunta" property="idPergunta" />
								<html:option value='<%= "" + idPergunta + ""%>'></html:option>
							</logic:iterate>
						</html:select>	
						<html:select name="Form" property="ordemSelecionada" size="1" style="width=0px;height=0px;visibility:visible" multiple="true">
							<logic:iterate name="Form" property="listaPergunta" id="Perguntas" indexId="index" >
								<bean:define name="Perguntas" id="sqApresentacao" property="sqApresentacao" />
								<html:option value='<%= "" + sqApresentacao + ""%>'></html:option>
							</logic:iterate>
						</html:select>	

					</form>
				</td>
		  </tr>
		  </table>
             
    </acesso:controlHiddenItem>

    </html>
