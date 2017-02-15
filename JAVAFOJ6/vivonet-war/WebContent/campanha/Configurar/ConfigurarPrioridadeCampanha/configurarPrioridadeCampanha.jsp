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

<bean:define id="form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="salvarPrioridadeCampanhaActionForm" />

   <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
		<script language="javascript">								
				
				function salvar()
				{                    
					for(var i = 0; i < document.salvarPrioridadeCampanhaActionForm.codigoSelecionado.options.length; i++)
					{
							document.salvarPrioridadeCampanhaActionForm.codigoSelecionado.options[i].selected = true;
					}
						 
					for(var i = 0; i < document.salvarPrioridadeCampanhaActionForm.ordemSelecionada.options.length; i++)
					{
							document.salvarPrioridadeCampanhaActionForm.ordemSelecionada.options[i].selected = true;
					}
					
					//document.forms["salvarPrioridadeCampanhaActionForm"].target = "";
					document.forms["salvarPrioridadeCampanhaActionForm"].action = "salvarPrioridadeCampanhaAction.do?acao=salvar";
					document.forms["salvarPrioridadeCampanhaActionForm"].submit();
                    
                    top.frameApp.mostrar_div();
				}
				
				function sobe(obj){
					index = obj.parentNode.id.split("_");
					index[1] = parseInt(index[1]);
					if(index[1] - 1 != -1){
						oldText1 = document.getElementById('texto_' + index[1]).innerText;
						oldText2 = document.getElementById('texto_' + (index[1] - 1)).innerText;
						
						oldDesc1 = document.getElementById('descricao_' + index[1]).innerText;
						oldDesc2 = document.getElementById('descricao_' + (index[1] - 1)).innerText;
						
						oldID1 = document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1]].value;
						oldID2 = document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1] - 1].value;
						
						document.getElementById('texto_' + index[1]).innerText = oldText2;
						document.getElementById('texto_' + (index[1] - 1)).innerText = oldText1;
						
						document.getElementById('descricao_' + index[1]).innerText = oldDesc2;
						document.getElementById('descricao_' + (index[1] - 1)).innerText = oldDesc1;
						
						document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1]].value = oldID2;
						document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1]].text = oldID2;
						document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1] - 1].value = oldID1;
						document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1] - 1].text = oldID1;
					}
				}
				
				function desce(obj){
					index = obj.parentNode.id.split("_");
					index[1] = parseInt(index[1]);
					if(document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1] + 1]){
						oldText1 = document.getElementById('texto_' + index[1]).innerText;
						oldText2 = document.getElementById('texto_' + (index[1] + 1)).innerText;
						
						oldDesc1 = document.getElementById('descricao_' + index[1]).innerText;
						oldDesc2 = document.getElementById('descricao_' + (index[1] + 1)).innerText;
						
						
						oldID1 = document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1]].value;
						oldID2 = document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1] + 1].value;
						
						document.getElementById('texto_' + index[1]).innerText = oldText2;
						document.getElementById('texto_' + (index[1] + 1)).innerText = oldText1;
						
						document.getElementById('descricao_' + index[1]).innerText = oldDesc2;
						document.getElementById('descricao_' + (index[1] + 1)).innerText = oldDesc1;
						
						document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1]].value = oldID2;
						document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1]].text = oldID2;
						document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1] + 1].value = oldID1;
						document.forms["salvarPrioridadeCampanhaActionForm"].codigoSelecionado.options[index[1] + 1].text = oldID1;
					}
				}
		</script>
        
        <SCRIPT FOR=window EVENT=onload>
            top.frameApp.oculta_div();
        </script> 
    </head>
		
    
    <acesso:controlHiddenItem nomeIdentificador="camp_confc_verpagina">

			<form action="salvarPrioridadeCampanhaAction.do" name="salvarPrioridadeCampanhaActionForm" method="post">
					<table bgcolor="#ededed" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
					    <tr>
							<td align="center" valign="top">
							 <div class="holderTabela" id="tableTitle" style="width: 1px; height: 1px; overflow: visible">
								  <table border="0" cellpadding="0" cellspacing="0">
								  <tr>
										<td>
											<div class="headerTabela" style="width: 750px;overflow: hidden">
												<table id="tableTitle_header" border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td class="normal" width="10%">Ordem</td>
													<td class="normal" width="10%">Ação</td>		
			                                        <td class="normal" width="40%">Campanha</td>
													<td class="normal" width="40%">Sub Campanha</td>
												</tr>
												</table>
											</div>	
										</td>
									</tr>
									<tr>			
										<td>
												<div class="corpoTabela" style="width: 750px; height: 350px; overflow: auto" onScroll="sincronizarScrollLateral( this.parentElement.parentElement.parentElement.parentElement.parentElement ) ">
													<table id="tableTitle_body" class="selecionavel" border="0" cellpadding="0" cellspacing="0" width="100%" onClick="" >
															 <logic:iterate name="form" property="listaCampanha" id="campanhas" indexId="index" >
                                                                
                                                                <!--
                                                                Pablo 18-11-04
                                                                Este bean foi removido por não ser usado na iteração
                                                                bean:define name="campanhas" id="sqApresentacao" property="sqApresentacao" /                                                                
                                                                -->
																	<tr class='rowTabela'>		
																		<td align="center" width="10%">&nbsp;<%="" + index + ""%>&nbsp;</td>
																		<td width="10%" align="center" id='linha_<%="" + index + ""%>'  onMouseOver="hoverRow();" onMouseOut="unhoverRow();">
																			<img onClick="sobe(this); return false" src="/FrontOfficeWeb/resources/images/seta_up.gif" style="border:0px" />
																			<img onClick="desce(this); return false" src="/FrontOfficeWeb/resources/images/seta_down.gif" style="border:0px" />
																		</td>
																		<td width="40%" id='texto_<%="" + index + ""%>'>&nbsp;
                                                                            <bean:write name="campanhas" property="sgCampanha"/>	
																		</td>
																		<td width="40%" id='descricao_<%="" + index + ""%>'>&nbsp;
                                                                            <bean:write name="campanhas" property="nmSubCampanha"/>																				
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
							<td>
								<table border="0" cellpadding="5" cellspacing="0" width="100%">
								<tr>									
									<td align="right">
                                    <acesso:controlHiddenItem nomeIdentificador="camp_bt_gravar">
											<img src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_registrar_over.gif';"  onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif';" style="border:none;cursor:hand;" onClick="salvar();"/>
                                    </acesso:controlHiddenItem>                                            
                                    </td>
								</tr>
								</table>
							</td>		 
						</tr>
					</table>
						<iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="1px" width="10px"></iframe>
						<netui:hidden dataSource="{actionForm.idSubCampanha}" />
						<html:select name="form" property="codigoSelecionado" size="1" style="width=0px;height=0px;visibility:visible" multiple="true">
							<logic:iterate name="form" property="listaCampanha" id="campanhas" indexId="index" >
								<bean:define name="campanhas" id="idCampanha" property="idSubCampanhaHistorico" />
								<html:option value='<%= "" + idCampanha + ""%>'></html:option>
							</logic:iterate>
						</html:select>	
						<html:select name="form" property="ordemSelecionada" size="1" style="width=0px;height=0px;visibility:visible" multiple="true">
							<logic:iterate name="form" property="listaCampanha" id="campanhas" indexId="index" >
								<bean:define name="campanhas" id="sqApresentacao" property="sqApresentacao" />
								<html:option value='<%= "" + sqApresentacao + ""%>'></html:option>
							</logic:iterate>
						</html:select>	
						
					</form>
           </acesso:controlHiddenItem>

