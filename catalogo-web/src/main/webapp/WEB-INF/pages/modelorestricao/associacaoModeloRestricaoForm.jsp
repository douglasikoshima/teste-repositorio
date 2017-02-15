<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
	<jsp:attribute name="headScripts">
		<style>
			.removerCampo:hover .removerCampo:visited .removerCampo:link {color: blue;}
			.adicionarCampo:hover .adicionarCampo:visited .adicionarCampo:link {color: blue;}
		</style>
		<script>
			var $jq = jQuery.noConflict();
			
			$jq(function () { 
				function removeCampo() { 
					$jq(".removerCampo").unbind("click"); 
					$jq(".removerCampo").bind("click", 
						function () {
						i=0; 
						$jq(".condicoes p.campoCondicao").each(function () { 
						i++;
						});
						if (i>1) { 
							$jq(this).parent().remove(); 
						} else {
						
							if (confirm("Tem certeza que deseja excluir todas as restrições?")) {
								$jq('#idTipoRestricaoArray').val('');
								$jq('#idTipocompativelArray').find('option').remove().end();								
								document.getElementById("associacaoModeloRestricaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/save.do";
								document.getElementById("associacaoModeloRestricaoForm").submit();
							}

						}
					}); 
				} 
				removeCampo(); 
				$jq(".adicionarCampo").click(function () { 
					novoCampo = $jq(".condicoes p.campoCondicao:first").clone(); 
					novoCampo.find("select").val("");
					novoCampo.find("#idTipocompativelArray").find("option").remove();
					novoCampo.insertAfter(".condicoes p.campoCondicao:last"); 
					removeCampo(); 
				}); 
			}); 
			
			function editar(id) {		
				document.getElementById("idGrupoProduto").value = id;
				document.getElementById("associacaoModeloRestricaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/edit.do#contentForm";
				document.getElementById("associacaoModeloRestricaoForm").submit();
			}

			function clearPage() {
				document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/AssociacaoModeloRestricaoAction.do";
			}	

			function search() {
				if (validarFormSearch()) {
					document.getElementById("associacaoModeloRestricaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/search.do";
					document.getElementById("associacaoModeloRestricaoForm").submit();
				}
			}
			
			function carregar() {
				document.getElementById("associacaoModeloRestricaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/carregar.do"
				document.getElementById("associacaoModeloRestricaoForm").submit();
			}
			
			function validarFormSearch() {
				var camposErro = "";
				var retorno = true;
				
				if (document.getElementById("idTipoProdutoSearch").value == "" || document.getElementById("idFabricanteSearch").value == "") {
					camposErro += "É obrigatório informar os campos Tipo de Produto e Fabricante";
				}

				if (camposErro != "") {
					generateContentError(camposErro);
					retorno = false;
				}
				
				return retorno;
			}
			
			function init() {
			
			}
			
			function save() {
				
				if (validarForm()) {
					var valores = "";
					
					
					if (document.all("idTipoCompativeisSelecionados").value == null) {
						
						for (var i = 0; i < document.all('idTipocompativelArray').length; i++) {
							valores = "";
							for (var j = 0; j < document.all('idTipocompativelArray')[i].length; j++) {
								if (document.all('idTipocompativelArray')[i][j].selected == true) {
									valores += document.all('idTipocompativelArray')[i][j].value + ";";
								}
							}
							if (document.all("idTipoCompativeisSelecionados").value == null ) {
								document.all("idTipoCompativeisSelecionados")[i].value = valores;
							} else {
								document.all("idTipoCompativeisSelecionados").value = valores;
							}
						}
						
					} else {	
					
						valores = "";
						for (var j = 0; j < document.all('idTipocompativelArray').length; j++) {
							if (document.all('idTipocompativelArray')[j].selected == true) {
								valores += document.all('idTipocompativelArray')[j].value + ";";
							}
						}
						if (document.all("idTipoCompativeisSelecionados").value == null ) {
							document.all("idTipoCompativeisSelecionados")[i].value = valores;
						} else {
							document.all("idTipoCompativeisSelecionados").value = valores;
						}
					}
					
					document.getElementById("associacaoModeloRestricaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/save.do";
					document.getElementById("associacaoModeloRestricaoForm").submit();
				}
			}
			
			function validarForm() {
				var camposErro = "";
				var retorno = true;
				
				var checkTipoRestricao = true;
			
				if (document.all('idTipoRestricaoArray').value == "") {
					checkTipoRestricao = false;
				} else if (document.all('idTipoRestricaoArray').value == null) {
					for (var j = 0; j < document.all('idTipoRestricaoArray').length; j++) {
						if (document.all('idTipoRestricaoArray')[j].value == "") {
							checkTipoRestricao = false;
							break;
						} else {
							checkTipoRestricao = true;
						}
					}
				}
				
				var checkTipoCompativel = true;
				
				if(document.all('idTipocompativelArray').value == "") {
					checkTipoCompativel = false;
				} else if (document.all('idTipocompativelArray').value == null) {
					for (var i = 0; i < document.all('idTipocompativelArray').length; i++) {
						if (document.all('idTipocompativelArray')[i].value == "") {
							checkTipoCompativel = false;
							break;
						} else {
							checkTipoCompativel = true;
						}
					}
				}

				
				if (!checkTipoCompativel || !checkTipoRestricao) {
					camposErro += "Obrigatório preencher os campos Tipo Restrição e Tipo Compatível";
				}
				
				if (camposErro == "") {
				
					for (var i = 0; i < document.all('idTipoRestricaoArray').length; i++) {
						var contador = 0;
						
						for (var j = 0; j < document.all('idTipoRestricaoArray').length; j++) {
							if (document.all('idTipoRestricaoArray')[i].value == document.all('idTipoRestricaoArray')[j].value) {
								contador++;
							}
						}
						
						if (contador > 1) {
							camposErro += "Há duplicidade de valores do campo Tipo Restrição.";
							break;
						}
					}
				
				}
				
				if (camposErro != "") {
					generateContentErrorForm(camposErro);
					retorno = false;
				}
				
				return retorno;
			}
			
			function cancel() {
				document.getElementById("associacaoModeloRestricaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/search.do";
				document.getElementById("associacaoModeloRestricaoForm").submit();
			}

			function carregarTipoRestricao() {
				var valores = "";
					
				
				for (var i = 0; i < document.all('idTipocompativelArray').length; i++) {
					valores = "";
					for (var j = 0; j < document.all('idTipocompativelArray')[i].length; j++) {
						if (document.all('idTipocompativelArray')[i][j].selected == true) {
							valores += document.all('idTipocompativelArray')[i][j].value + ";";
						}
					}
					if (document.all("idTipoCompativeisSelecionados").value == null ) {
						document.all("idTipoCompativeisSelecionados")[i].value = valores;
					} else {
						document.all("idTipoCompativeisSelecionados").value = valores;
					}
				}
				document.getElementById("associacaoModeloRestricaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/carregarTipoRestricao.do";
				document.getElementById("associacaoModeloRestricaoForm").submit();
			}
			
		</script>
	</jsp:attribute>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/search.do" styleId="associacaoModeloRestricaoForm" >
			<script>carregaMenu('mn_parametrizacao_configurar_restricao');</script>
			<div class="secao_conteudo">
				<catalogo:contentBox title="Configurar Restrição">
					<html:hidden property="idGrupoProduto" styleId="idGrupoProduto"/>
					<div class="fleft" style="width:30%; margin-bottom: 5px; margin-left: 5px;"  id="contentForm"> 
						<div class="label-form-bold " style="width: 80px;"><label for="idTipoProdutoSearch">Tipo Produto:<font size="1px" color="#EEB422">*</font></label></div>
						<html:select property="idTipoProdutoSearch" styleId="idTipoProdutoSearch" style="width: 160px;" onChange="carregar()">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="tipoProdutoTO" items="${tipoProdutoList}">
								<html:option value="${tipoProdutoTO.idTipoProduto}">${tipoProdutoTO.nmTipoProduto}</html:option>
							</c:forEach>
						</html:select>
					</div>
					<div class="fleft" style="width:36%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold " style="width: 110px;"><label for=idFabricanteSearch>Fabricante:<font size="1px" color="#EEB422">*</font></label></div>
						<html:select property="idFabricanteSearch" styleId="idFabricanteSearch" style="width: 180px;" onChange="carregar()">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="fabricanteTO" items="${fabricanteList}">
								<html:option value="${fabricanteTO.idFabricante}">${fabricanteTO.nmFabricante}</html:option>
							</c:forEach>							
						</html:select>
					</div>
					<div class="fleft" style="width: 31%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold " style="width: 60px;"><label for="idModeloSearch">Modelo:</label></div>
						<html:select property="idModeloSearch" styleId="idModeloSearch" style="width: 180px;">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="grupoProdutoTO" items="${grupoProdutoList}">
								<html:option value="${grupoProdutoTO.idGrupoProduto}">${grupoProdutoTO.nmGrupoProduto}</html:option>
							</c:forEach>
						</html:select>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>					    				    
						<span>&nbsp;</span>	
					    <html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="clearPage()" styleClass="btNavegacao74" value="Limpar" alt="Limpar" title=""/>					    				    
					</div>
				</catalogo:contentBox>
				<c:if test="${grupoProdutoResultList != null}">
					<catalogo:contentBox title="Resultado da Pesquisa">	
						<display:table name="grupoProdutoResultList" id="grupoProdutoResultTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="Tipo Produto" sortable="true" headerClass="sortable" style="width: 150px;">
								<html:link href="javascript:editar(${grupoProdutoResultTO.idGrupoProduto})">${grupoProdutoResultTO.tipoProdutoTO.nmTipoProduto}</html:link>
							</display:column>
							<display:column title="Fabricante" sortable="true" headerClass="sortable" style="width: 150px;">
								<html:link href="javascript:editar(${grupoProdutoResultTO.idGrupoProduto})">${grupoProdutoResultTO.fabricanteTO.nmFabricante}</html:link>
							</display:column>
							<display:column title="Modelo" sortable="true" headerClass="sortable" >
								<html:link href="javascript:editar(${grupoProdutoResultTO.idGrupoProduto})" >${grupoProdutoResultTO.nmGrupoProduto}</html:link>
							</display:column>
							<display:column title="Indicador" sortable="true" style="text-align: center; width: 60px;" >
								<c:choose>
									<c:when test="${grupoProdutoResultTO.modeloTipoProdutoRestricaoTOList!=null}">
										<html:img alt="Com Restrição" src="/catalogo/static_server/img/bullets/icon-available.png"/>
									</c:when>
									<c:otherwise>
										<html:img alt="Sem Restrição" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:footer >
								<tr>
									<td colspan="5" style="text-align: left;">
									 	<table class="tabelaIcones" cellpadding="0" cellspacing="0" style="width: 280px;">
											<tr>
												<td width="30px;">&Iacute;cones:</td>
												<td width="15px;"><html:img src="/catalogo/static_server/img/bullets/icon-available.png" alt="Com Restrição"/></td>
												<td width="150px;">Com Restri&ccedil;&atilde;o</td>
												<td width="15px;"><html:img src="/catalogo/static_server/img/bullets/icon-unavailable.png" alt="Sem Restrição"/></td>
												<td width="150px;">Sem Restri&ccedil;&atilde;o</td>
											</tr>
										</table>
									</td>
								</tr>
							</display:footer>
						</display:table>
					</catalogo:contentBox>
				</c:if>
				<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
				<c:if test="${labelError != null}">
					<script>
						generateContentErrorForm("${labelError}")
					</script>
				</c:if>
				<c:if test="${cadastrar}">
					<catalogo:contentBox title="Configurar Restrição" requiredFields="true">
						<div id="teste" ></div>
						<div class="fleft" style="width: 31%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold " style="width: 85px;"><label for="idModeloSearch">Modelo:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div>
						&nbsp;${nmModelo}
						</div>
						<div class="condicoes" style="width: 700px; float: left; margin-top: 5px; height: 40px;"> 
							<c:if test="${tipoProdutoSearch == null}">
								<p class="campoCondicao" style="float: left; display: inline; "> 
									<html:hidden property="idTipoCompativeisSelecionados" styleId="idTipoCompativeisSelecionados" value=""/>
									<span style="float: left; height: 40px;" >
										<b><label>Tipo Restri&ccedil;&atilde;o:<font size="1px" color="#EEB422">*</font></label></b>
										<html:select property="idTipoRestricaoArray" styleId="idTipoRestricaoArray" style="width: 150px; height: 60px;" onchange="carregarTipoRestricao()">
											<html:option value="">-- Selecione --</html:option>
											<c:forEach items="${tipoProdutoList}" var="tipoRestricaoTOList" >
												<html:option value="${tipoRestricaoTOList.idTipoProduto}">${tipoRestricaoTOList.nmTipoProduto}</html:option>
											</c:forEach>
										</html:select>
				                    </span>
				                    <span style="float: left; height: 40px; padding-top: 3px; margin-left: 5px;" >
										<b><label>Tipo Compat&iacute;vel:<font size="1px" color="#EEB422">*</font></label></b>
									</span>
									<html:select property="idTipocompativelArray" styleId="idTipocompativelArray" style="width: 200px; height: 60px;" multiple="true" >
									
									</html:select>
				                    <cata:temPermissao acao="removerConfiguraRestricao">
										<html:link href="#botao" styleClass="removerCampo">Remover&nbsp;</html:link>
									</cata:temPermissao>
									<cata:temPermissao acao="adicionarConfiguraRestricao">
									<p style="float:left; padding-top: 47px;">
										<html:link href="#botao" styleClass="adicionarCampo">/ Adicionar</html:link>
									</p>
									</cata:temPermissao>
								</p>
							</c:if>
							<c:if test="${tipoProdutoSearch != null}">
								<c:forEach items="${tipoProdutoSearch}" var="tipoRestricaoTO">
									<p class="campoCondicao" style="float: left; display: inline;"> 
										<html:hidden property="idTipoCompativeisSelecionados" styleId="idTipoCompativeisSelecionados" value=""/>
										<span style="float: left; height: 40px;" >
											<b><label>Tipo Restri&ccedil;&atilde;o:<font size="1px" color="#EEB422">*</font></label></b>
											<html:select property="idTipoRestricaoArray" styleId="idTipoRestricaoArray" style="width: 150px; height: 60px;" onchange="carregarTipoRestricao()">
												<html:option value="">-- Selecione --</html:option>
												<c:forEach items="${tipoProdutoList}" var="tipoRestricaoTOList">
													<html:option value="${tipoRestricaoTOList.idTipoProduto}">${tipoRestricaoTOList.nmTipoProduto}</html:option>
												</c:forEach>
											
											</html:select>
											&nbsp;&nbsp;&nbsp;
										</span>
					                    <span style="float: left; height: 40px; padding-top: 3px; margin-left: 5px;" >
											<b><label>Tipo Compat&iacute;vel:<font size="1px" color="#EEB422">*</font></label></b>
										</span>
										<html:select property="idTipocompativelArray" styleId="idTipocompativelArray" style="width: 200px; height: 60px;" multiple="true">
											<c:forEach items="${tipoRestricaoTO.associaRestricaoCompativelTOList}" var="tipoCompativelTO" >
												<html:option value="${tipoCompativelTO.tipoProdutoCompativelTO.idTipoProduto}">${tipoCompativelTO.tipoProdutoCompativelTO.nmTipoProduto}</html:option>
											</c:forEach>
										</html:select>
					                    <cata:temPermissao acao="removerConfiguraRestricao">
					                    	<html:link href="#botao" styleClass="removerCampo">Remover&nbsp;</html:link>
										</cata:temPermissao>
									</p>
								</c:forEach>
								<c:set var="contador" value="0" />
								<c:forEach items="${tipoProdutoSearch}" var="modeloTipoRestricaoTesteTO">
									<script>
				                    	var cont = ${contador};
				                    	
				                    	$jq('[id=idTipoRestricaoArray]').each(function(i){
				                    		if(i == cont) {
												$jq(this).val('${modeloTipoRestricaoTesteTO.idTipoProduto}');
											}
										});
										
										
				                    </script>
				                    
				                    <c:set var="contadorSelecionados" value="0" />
				                    <c:forEach items="${idTipoCompativeisSelecionados}" var="idTipoCompativeisSelecionados" >
				                    	<c:if test="${contador == contadorSelecionados}">
				                    		<script>
				                    			
				                    			var idTipoCompativeisSelecionados = '${idTipoCompativeisSelecionados}'
				                    			var idTipoCompativeisSelecionadosArray = idTipoCompativeisSelecionados.split(';');
				                    			var cont = ${contador}; 
				                    			if (document.getElementById("idTipocompativelArray").value == null) {				          
					                    			for (var m = 0; m < idTipoCompativeisSelecionadosArray.length; m++ ) {
					                    				for (var k = 0; k < document.getElementById("idTipocompativelArray")[cont].length; k++ ) {
							                    			if (document.getElementById("idTipocompativelArray")[cont][k].value == idTipoCompativeisSelecionadosArray[m]) {							                    				
							                    				document.getElementById("idTipocompativelArray")[cont][k].selected = 'true';
							                    			}
							                    		}
					                    			}
				                    			} else {
				                    				for (var m = 0; m < idTipoCompativeisSelecionadosArray.length; m++ ) {
					                    				for (var k = 0; k < document.getElementById("idTipocompativelArray").length; k++ ) {
							                    			if (document.getElementById("idTipocompativelArray")[k].value == idTipoCompativeisSelecionadosArray[m]) {
							                    				document.getElementById("idTipocompativelArray")[k].selected = 'true';
							                    			}
							                    		}
					                    			}
				                    			}
				                    			
				                    		</script>
				                    	</c:if>
				                    	<c:set var="contadorSelecionados" value="${contadorSelecionados + 1}" />
				                    </c:forEach>
				                    
				                  	<c:set var="contador" value="${contador + 1}" />
								</c:forEach>
								<cata:temPermissao acao="adicionarConfiguraRestricao">
								<p style="float:left; padding-top: 47px;">
									<html:link href="#botao" styleClass="adicionarCampo">/ Adicionar</html:link>
								</p>
								</cata:temPermissao>
							</c:if>
						</div>
						
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao" id="botao">
							<cata:temPermissao acao="gravarConfiguraRestricao">
								<html:button property="btn_salvar" styleId="botao_pesquisar_form" onClick="save()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>					    				    
								<span>&nbsp;</span>	
						    </cata:temPermissao>
						    <html:button property="btn_limpar" styleId="botao_limpar_form" onClick="cancel()" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title=""/>					    				    
						</div>
					</catalogo:contentBox>
				</c:if>
			</div>
			</html:form>
</catalogo:defaultTemplate>