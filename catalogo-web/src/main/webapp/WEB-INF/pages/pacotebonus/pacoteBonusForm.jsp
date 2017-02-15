<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
		<jsp:attribute name="headScripts">	
			<script>
			
				var $jq = jQuery.noConflict();
				
				function create() {
					document.getElementById("pacoteBonusForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/create.do"
					document.getElementById("pacoteBonusForm").submit();
				}
				
				function backPage() {
					document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/PacoteBonusAction.do";
				}
				
				function save() {
					if (validarForm()) {
						document.getElementById("idServicoInteratividade").disabled = false;
						
						$jq('[id=idParametroBaseChecked]').each(function(i, element){
							document.all('vlParametro')[i].disabled = false;
						});
						
						document.getElementById("pacoteBonusForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/save.do";
						document.getElementById("pacoteBonusForm").submit();
					}
				}
				
				function abrirCadastroIPpopup(href) {
					abrirPopup1(href);
			    }					
				
				function validarForm() {
					var camposErro = "";
					var retorno = true;
					
					if (document.getElementById("nmServico").value == "") {
						camposErro += "Nome do Serviço";
					}
					
					// Canal Atendimento
					var chkCanal = document.pacoteBonusForm("idCanalAtendimento");
					var canal = false;
					for (var i = 0; i < chkCanal.length; i ++) {
						if (chkCanal[i].checked == true) {
							canal = true;
							break;
						} 
					}	
					if (!canal) {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Canal Atendimento";
					}

					// Plataforma
					var chkPlataforma = document.pacoteBonusForm("idPlataforma");
					var plataforma = false;
					for (var j = 0; j < chkPlataforma.length; j++) {
						if (chkPlataforma[j].checked == true) {
							plataforma = true;
							break;					
						} 
					}
					if (!plataforma) {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Plataforma";
					}
					
					if (document.getElementById("cdFuncionalidade").value == "0") {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Funcionalidade";
					}
					
					if (document.getElementById("url").value == "") {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "URL";
					}
					
					if (document.getElementById("sgTipoLinha").value == "0") {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Tipo Rede";
					}
					
					// Tipo Cliente
					var chkCliente = document.pacoteBonusForm("idTipoCliente");
					var cliente = false;
					for (var k = 0; k < chkCliente.length; k++) {
						if (chkCliente[k].checked == true) {
							cliente = true;							
							break;					
						} 
					}	
					if (!cliente) {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Tipo Pessoa";
					}

					// Tecnologia
					var chkTecno = document.pacoteBonusForm("idTecnologia");
					var tecnologia = false;
					for (var p = 0; p < chkTecno.length; p++ ) {
						if (chkTecno[p].checked == true) {
							tecnologia = true;
							break;				
						} 
					}					
					if (!tecnologia) {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Tecnologia";
					}					

					if (document.getElementById("ativoS").checked == false && document.getElementById("ativoN").checked == false) {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Ativo";
					}
					
					var isEmpty = false;
					
					$jq('[id=idParametroBaseChecked]').each(function(i, element){
						if (element.checked == true && document.all('vlParametro')[i].disabled != true) {
							if (document.all('vlParametro')[i].value == "") {
								isEmpty = true;
							}
						}
					});
					
					if (isEmpty) {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Valor";
					}
					
					if (camposErro != "") {
						document.getElementById('conteudo_divErros').innerHTML = "Favor preencher os campos: " + camposErro;
						document.getElementById('divErros').style.display = "inline";
						retorno = false;
					}					
					return retorno;
				}
			</script>
		</jsp:attribute>
			
		<script>carregaMenu('menu_PacoteBonus');</script>
		<div class="secao_conteudo">
			<catalogo:contentBox title="Cadastro de Serviço Interatividade" doubt="true" requiredFields="true">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/save.do" styleId="pacoteBonusForm" >
					<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;" id="contentIdServico" > 
						<div class="label-form-bold label_required" style="text-align: right; width: 150px;"><label for="nmServico">C&oacute;digo:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
						<html:text property="idServicoInteratividade" styleId="idServicoInteratividade" disabled="true" />
					</div>
					
					<!-- Nome do Serviço -->
					<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 10px;" > 
						<div class="label-form-bold label_required" style="text-align: right; width: 150px;"><label for="nmServico">Nome do Servi&ccedil;o:<font size="1px" color="#EEB422">*</font></label></div>
						<html:text styleId="nmServico" property="nmServico" style="width:393px; text-align: left;" maxlength="200"/>
					</div>
					<!-- Funcionalidade  -->
					<div class="fleft" style="margin-left: 10px; clear: both; width: 70%; margin-bottom: 5px;">
						<div class="label-form-bold label_required" style="text-align: right;"><label for="cdFuncionalidade">Funcionalidade:<font size="1px" color="#EEB422">*</font></label></div>
						<html:select styleClass="required" styleId="cdFuncionalidade" style="width:393px;" property="cdFuncionalidade">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="funcionalidadeListTO" items="${funcionalidadeList}">
								<html:option value="${funcionalidadeListTO.cdFuncionalidade}">${funcionalidadeListTO.nmFuncionalidade}</html:option>
							</c:forEach>						
						</html:select>
					</div>
					<!-- URL  -->
					<div class="fleft" style="margin-left: 10px; width: 90%; margin-bottom: 5px;">
						<div class="label-form-bold label_required" style="text-align: right;"><label for="url">URL:<font size="1px" color="#EEB422">*</font></label></div>
						<html:text styleId="url" property="url" style="width:393px; text-align: left;" maxlength="250"/>					
					</div>
					
					<!-- Tipo de Rede  -->
					<div class="fleft" style="margin-left: 10px; margin-bottom: 5px;">
						<div class="label-form-bold label_required" style="text-align: right; "><label for="sgTipoLinha">Tipo Rede:<font size="1px" color="#EEB422">*</font></label></div>
						<html:select styleClass="required" styleId="sgTipoLinha" style="width:150px;" property="sgTipoLinha">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="tipoLinhaListTO" items="${tipoLinhaList}">
								<html:option value="${tipoLinhaListTO.idTipoLinha}|${tipoLinhaListTO.sgTipoLinha}">${tipoLinhaListTO.dscTipoLinha}</html:option>
							</c:forEach>							
						</html:select>
					</div>
					
					<!-- Ativo  -->
					<div class="fleft" style="width:50%; margin-left: 10px; margin-bottom: 5px;">
						<div class="label-form-bold label_required" style="text-align: right;  width: 80px;"><label>Ativo:<font size="1px" color="#EEB422">*</font></label></div>					
						<label>Sim <html:radio styleId="ativoS" property="ativo" value="S"/></label>
						<label>N&atilde;o <html:radio styleId="ativoN" property="ativo" value="N"/></label>						
						<c:if test="${actionForm.idServicoInteratividade == null}">
							<script>
								document.all("ativo")[0].checked = "checked";
								document.all("contentIdServico").style.display = "none";
							</script>
						</c:if>
					</div>
				
					<!-- Tipo Pessoa  -->
					<div class="fleft" style="width:80%; margin-bottom: 5px; margin-left: 80px;" >
						<div class="label-form-bold label_required" style="text-align: right; width: 80px;">
							<label for="idTipoCliente">Tipo Pessoa:<font size="1px" color="#EEB422">*</font></label>
						</div>
						<div id="lista_tip_pessoa" class="box-dashed-alt" >											
						<c:forEach var="tipoClienteListTO" items="${tipoClienteList}">
							<html:multibox property="idTipoCliente" onClick="" styleId="idTipoCliente" value="${tipoClienteListTO.idTipoCliente}" styleClass="semBorda checkbox_plataforma">
							
								<bean:write bundle="messages" name="tipoClienteListTO" property="idTipoCliente" />
							
							</html:multibox>
								<bean:write bundle="messages" name="tipoClienteListTO" property="nmTipoCliente"/>
						</c:forEach>
						</div>
						<div class="fleft"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<html:link styleId="idPopIP" action="/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/abrirCadastroIPpopup" onClick="abrirCadastroIPpopup(this.href)" title="Incluir IP">
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								<html:button property="botao_incluir_ip" styleId="botao_incluir_ip" styleClass="btNavegacao74" value="Incluir IP" alt="Incluir IP" title="Inclusão de IP's"/>
							</html:link>					
						</div>							                 
					</div>
					<!-- Canal de Atendimento  -->
					<div class="fleft" style="width:30%;">					
						<div class="label-form-bold3 label_required" style="text-align: right;">
							<label for="idCanalAtendimento">Canal:<font size="1px" color="#EEB422">*</font></label>
						</div>
						<div id="lista_canal" class="box-dashed" >
							<div>
								<input type="checkbox" id="ckeckbox_canalAll" name="modelo" class="semBorda" onclick="selectTodosCheckbox('lista_canal', '.checkbox_canal', this.checked);"/>
								<b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>
							</div>						
							<c:forEach var="canalAtendimentoTO" items="${canalAtendimentoList}">
								<html:multibox property="idCanalAtendimento" styleId="idCanalAtendimento" value="${canalAtendimentoTO.idCanalAtendimento}" styleClass="semBorda checkbox_canal">
									
									<bean:write bundle="messages" name="canalAtendimentoTO" property="idCanalAtendimento"/>
									
								</html:multibox>
									<bean:write bundle="messages" name="canalAtendimentoTO" property="nmCanal"/><br>
							</c:forEach>
						</div>
					</div>
					
					<!-- Plataforma  -->
					<div class="fleft" style="width:30%;">
						<div class="label-form-bold label_required" style="text-align: right; width: 80px;">
							<label for="idPlataforma">Plataforma:<font size="1px" color="#EEB422">*</font></label>
						</div>
						<div id="lista_plataforma" class="box-dashed" > 
							<div>
								<input type="checkbox" id="ckeckbox_plataformAll" name="modelo" class="semBorda" onclick="selectTodosCheckbox('lista_plataforma', '.checkbox_plataforma', this.checked);"/>
								<b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>
							</div>									
							<c:forEach var="plataformaListTO" items="${plataformaList}">
								<html:multibox property="idPlataforma" onClick="verificarSelecaoAll(this, 'ckeckbox_plataformAll')" styleId="idPlataforma" value="${plataformaListTO.idPlataforma}" styleClass="semBorda checkbox_plataforma">
								
									<bean:write bundle="messages" name="plataformaListTO" property="idPlataforma"/>
									
								</html:multibox>	
									<bean:write bundle="messages" name="plataformaListTO" property="nmPlataforma"/><br>
															
							</c:forEach>
						</div>
					</div>					
					
					<!-- Tecnologia  -->
					<div class="fleft" style="width:30%;">
						<div class="label-form-bold3 label_required" style="text-align: right">
							<label for="idTecnologia">Tecnologia:<font size="1px" color="#EEB422">*</font></label>
						</div>
						<div id="lista_tecnologia" class="box-dashed" > 
							<div>
								<input type="checkbox" id="ckeckbox_plataformAll" name="modelo" class="semBorda" onclick="selectTodosCheckbox('lista_tecnologia', '.checkbox_tecnologia', this.checked);"/>
								<b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>
							</div>	
							<c:forEach var="tecnologiaListTO" items="${tecnologiaList}">
								<html:multibox property="idTecnologia" styleId="idTecnologia" value="${tecnologiaListTO.idTecnologia}" styleClass="semBorda checkbox_tecnologia">
								
									<bean:write bundle="messages" name="tecnologiaListTO" property="idTecnologia"/>
								
								</html:multibox>	
									<bean:write bundle="messages" name="tecnologiaListTO" property="nmTecnologia"/><br>
														
							</c:forEach>
						</div>
					</div>					

					

					
					<div class="fleft" style="margin-left: 10px; clear: both; width: 97%; margin-bottom: 5px;">
						<fieldset style="padding: 10px 5px 10px 5px; float: left; width: 100%">
							<legend style="font-weight: bold; color: #0364CB; font-size: 11px;">Parâmetros</legend>
							<div style="overflow-y: auto; height: 400px;">
							<display:table name="parametroList" id="parametrosBaseTO" cellspacing="0" cellpadding="0" style="width: 99%; margin-top:10px;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
								<display:column style="text-align: center; width: 15px; " title="&nbsp;" headerClass="sortable" >
									<input type="hidden" name="idParametroBase" id="idParametroBase" value="${parametrosBaseTO.idSrvInteratividadeParamBase}" /> 
									<c:set var="cont" value="0" />
									<c:if test="${srvInteratividadeParametroTOList != null}">
										<c:forEach items="${srvInteratividadeParametroTOList}" var="srvInteratividadeParametroTO" >
											<c:if test="${srvInteratividadeParametroTO.parametroTO.idSrvInteratividadeParamBase == parametrosBaseTO.idSrvInteratividadeParamBase}">
												<input type="checkbox" name="idParametroBaseChecked" id="idParametroBaseChecked" checked="checked" value="${parametrosBaseTO.idSrvInteratividadeParamBase}" /> 
												<c:set var="cont" value="${cont+1}" />
											</c:if>
										</c:forEach>
										<c:if test="${cont == 0}">
											<input type="checkbox" name="idParametroBaseChecked" id="idParametroBaseChecked" value="${parametrosBaseTO.idSrvInteratividadeParamBase}" /> 	
										</c:if>
									</c:if>
									<c:if test="${srvInteratividadeParametroTOList == null}">
										<input type="checkbox" name="idParametroBaseChecked" id="idParametroBaseChecked" value="${parametrosBaseTO.idSrvInteratividadeParamBase}" />
									</c:if>
								</display:column>
								<display:column property="nmSrvInteratividadeParamBase" title="Parâmetro" style="width:190px" />
								<display:column title="Valor"  style="width: 155px;" >
									<c:if test="${parametrosBaseTO.tpSrvInteratividadeParamBase == 'variavel'}">
										<input type="hidden" name="vlParametro"  id="vlParametro" style="width: 150px;" maxlength="254" disabled="disabled" value="${parametrosBaseTO.vlDefaultParam}" />
									</c:if>
									<c:if test="${parametrosBaseTO.tpSrvInteratividadeParamBase == 'constante'}">
										<c:if test="${srvInteratividadeParametroTOList != null}">
											<c:set var="cont" value="0" />
											<c:forEach items="${srvInteratividadeParametroTOList}" var="srvInteratividadeParametroTO" >
												<c:if test="${srvInteratividadeParametroTO.parametroTO.idSrvInteratividadeParamBase == parametrosBaseTO.idSrvInteratividadeParamBase}">
													<input type="text" name="vlParametro"  id="vlParametro" style="width: 150px;" maxlength="254" value="${srvInteratividadeParametroTO.vlParametro}" />		
													<c:set var="cont" value="${cont+1}" />
												</c:if>
											</c:forEach>
											<c:if test="${cont == 0}">
												<input type="text" name="vlParametro"  id="vlParametro" style="width: 150px;" maxlength="254" value="${parametrosBaseTO.vlDefaultParam}" />		
											</c:if>
										</c:if>
										<c:if test="${srvInteratividadeParametroTOList == null}">
											<input type="text" name="vlParametro"  id="vlParametro" style="width: 150px;" maxlength="254" value="${parametrosBaseTO.vlDefaultParam}" />		
										</c:if>
									</c:if>
								</display:column>
								<display:column property="dsSrvInteratividadeParamBase"  title="Descrição" />
							</display:table>
							</div>
						</fieldset>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="save()" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/><span>&nbsp;</span>
						<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="backPage()" styleClass="btNavegacao74" value="Voltar" alt="Voltar" title=""/>	    
					</div>
				</html:form>
			</catalogo:contentBox>
		</div>
</catalogo:defaultTemplate>
