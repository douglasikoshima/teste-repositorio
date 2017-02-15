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
		<script type="text/javascript" src="/catalogo/jquery/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="/catalogo/jquery/js/jquery-ui-1.8.17.custom.min.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/cadastroacoesvendas.js"></script>
		<script>
		
		var $jq = jQuery.noConflict();
		
		<cata:temPermissaoPorSubSistema acao="produtoAcaoTelevendas">
		
		$jq( "#dtInicialSearch" ).datepicker( "option", $jq.datepicker.regional[ 'pt-BR' ] );
		$jq( "#dtFinalSearch" ).datepicker( "option", $jq.datepicker.regional[ 'pt-BR' ] );
		$jq( "#dtInicial" ).datepicker( "option", $jq.datepicker.regional[ 'pt-BR' ] );
		$jq( "#dtFinal" ).datepicker( "option", $jq.datepicker.regional[ 'pt-BR' ] );
		
		$jq(function() {
			var dates = $jq( "#dtInicial, #dtFinal" ).datepicker({
				changeMonth: true,
				numberOfMonths: 1,
				showOn: "button",
				buttonImage: "/catalogo/static_server/img/botoes/calendar.gif",
				buttonImageOnly: true,
				dateFormat : "dd/mm/yy",
				navigationAsDateFormat : true,
				onSelect: function( selectedDate ) {
					var option = this.id == "dtInicial" ? "minDate" : "maxDate",
						instance = $jq( this ).data( "datepicker" ),
						date = $jq.datepicker.parseDate(
							instance.settings.dateFormat ||
							$jq.datepicker._defaults.dateFormat,
							selectedDate, instance.settings );
					dates.not( this ).datepicker( "option", option, date );
				}
			});
		});
		
		$jq(function() {
			var dates = $jq( "#dtInicialSearch, #dtFinalSearch" ).datepicker({
				changeMonth: true,
				numberOfMonths: 1,
				showOn: "button",
				buttonImage: "/catalogo/static_server/img/botoes/calendar.gif",
				buttonImageOnly: true,
				dateFormat : "dd/mm/yy",
				navigationAsDateFormat : true,
				onSelect: function( selectedDate ) {
					var option = this.id == "dtInicialSearch" ? "minDate" : "maxDate",
						instance = $jq( this ).data( "datepicker" ),
						date = $jq.datepicker.parseDate(
							instance.settings.dateFormat ||
							$jq.datepicker._defaults.dateFormat,
							selectedDate, instance.settings );
					dates.not( this ).datepicker( "option", option, date );
				}
			});
		});
		
		
	
		</cata:temPermissaoPorSubSistema>
		
		function search() {
			
			if (validarFormSearch()) {
				<cata:temPermissaoPorSubSistema acao="produtoAcaoTelevendas">
					document.getElementById('dtInicialSearch').disabled = false;
					document.getElementById('dtFinalSearch').disabled = false;
				</cata:temPermissaoPorSubSistema>
				document.getElementById('isPesquisa').value = 'S';
				document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acao/search.do";
				document.getElementById("acaoForm").submit();
				
			}
		}
		
		function validarForm() {
			var camposErro = "";
			var retorno = true;

			if (document.getElementById("nmAcao").value == "" ) {
				if (camposErro != "") {
					camposErro += ", ";	
				}
				camposErro += "Nome";
			} else	if (document.getElementById('nmAcao').value != "" && document.getElementById('nmAcao').value.length < 3) {
				generateContentErrorForm("Favor informar pelo menos 3 caracteres para o campo Nome da Ação.<br />");
				retorno = false;
			}
			
			if (document.getElementById("sgAcao").value == "" ) {
				if (camposErro != "") {
					camposErro += ", ";	
				}
				camposErro += "Sigla";
			}
			<cata:temPermissaoPorSubSistema acao="produtoAcaoTelevendas">
			if (document.getElementById("dtInicial").value == "" ) {
				if (camposErro != "") {
					camposErro += ", ";	
				}
				camposErro += "Inicio";
			}
							
			if (document.getElementById("dtFinal").value == "" ) {
				if (camposErro != "") {
					camposErro += ", ";	
				}
				camposErro += "Fim";
			}
			
			if (document.getElementById("idPerfis").value == "" ) {
				if (camposErro != "") {
					camposErro += ", ";	
				}
				camposErro += "Perfil";
			}
			
			if (document.getElementById("idFornecedores").value == "" ) {
				if (camposErro != "") {
					camposErro += ", ";	
				}
				camposErro += "Fornecedores";
			}
			</cata:temPermissaoPorSubSistema>
			
			if (camposErro != "") {
				generateContentErrorForm("Favor preencher os campos: " + camposErro);
				retorno = false;
			}
			
			return retorno;
		}
		
		function save() {
			if (validarForm()) {
				document.getElementById('sgAcao').disabled = false;
				<cata:temPermissaoPorSubSistema acao="produtoAcaoTelevendas">
				document.getElementById('inDisponivel').value = "";
				document.getElementById('dtInicial').disabled = false;
				document.getElementById('dtFinal').disabled = false;
				</cata:temPermissaoPorSubSistema>
				document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acao/save.do";
				document.getElementById("acaoForm").submit();
			}
		}
		
		</script>
	</jsp:attribute>
   <jsp:body>
  		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acao/search.do"  styleId="acaoForm">
			<script>carregaMenu('mn_parametrizacao_matriz_oferta_acao');</script>
			<div class="secao_conteudo">
				<catalogo:contentBox title="Cadastro Ações de Vendas" doubt="true">
					
					<html:hidden property="idAcao" styleId="idAcao"/>
					<html:hidden property="isPesquisa" styleId="isPesquisa"/>
					<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="text-align: left; width: 40px;"><label for="nmAcaoSearch">Nome:</label></div>
						<html:text property="nmAcaoSearch" styleId="nmAcaoSearch" style="width:300px;" styleClass="required" maxlength="254" />
					</div>
					<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="text-align: right; width: 40px;"><label for="sgAcaoSearch">Sigla:</label></div>
						<html:text property="sgAcaoSearch" styleId="sgAcaoSearch" style="width:150px;" styleClass="required" maxlength="10" />
					</div>
					<cata:temPermissaoPorSubSistema acao="produtoAcaoTelevendas">
						<html:hidden property="idCanalAtendimentoSearch" styleId="idCanalAtendimentoSearch" value="23611"/>
						<div class="fleft" style="width:23%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: left; width: 40px;"><label for="dtInicialSearch">Inicio:</label></div>
							<html:text styleId="dtInicialSearch" property="dtInicialSearch" style="width:115px;" styleClass="required" disabled="true" maxlength="10" onKeyDown="mascara(this,data);" onKeyPress="mascara(this,data);" onKeyUp="mascara(this,data);" />
						</div>
						<div class="fleft" style="width:22%; margin-bottom: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 35px;"><label for="dtFinalSearch">Fim:</label></div>
					        <html:text styleID="dtFinalSearch" property="dtFinalSearch" style="width:115px;" styleClass="required" disabled="true" maxlength="10" onKeyDown="mascara(this,data);" onKeyPress="mascara(this,data);" onKeyUp="mascara(this,data);"/> 	
						</div>
						<div class="fleft" style="width:45%;">
							<div class="label-form-bold label_required" style="text-align: right; width: 40px;"><label>Vigente:</label></div>
								<label>Sim <html:radio property="inDisponivelSearch" styleId="inDisponivelSearch" value="S"/></label>
								<label>Nâo <html:radio property="inDisponivelSearch" styleId="inDisponivelSearch" value="N"/></label>
						</div>
					</cata:temPermissaoPorSubSistema>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<cata:temPermissao acao="produtoAcao,novoProdutoAcaoTelevendas">
							<html:button property="botao_criar_form" styleId="botao_criar_form" onClick="create()" styleClass="btNavegacao74" value="Novo" alt="Criar Novo Serviço do Parceiro" title=""/>
							<span>&nbsp;</span>
						</cata:temPermissao>
						<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
						<span>&nbsp;</span>	
						<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="clearPage()" styleClass="btNavegacao74" value="Limpar" alt="Limpar" title="" />
					</div>
					<c:if test="${labelErrorSearch != null}">
						<script>
							generateContentError("${labelErrorSearch}")
						</script>
					</c:if>
				</catalogo:contentBox>
				
				<c:if test="${acaoList != null}">
					<catalogo:contentBox title="Resultado da Pesquisa">	
						<display:table name="acaoList" id="acaoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="Sigla" sortable="true" headerClass="sortable" >
								<a href="javascript:editar(${acaoTO.idAcao})">${acaoTO.sgAcao}</a>
							</display:column>
							<display:column title="Nome" sortable="true" headerClass="sortable">
								<a href="javascript:editar(${acaoTO.idAcao})">${acaoTO.nmAcao}</a>
							</display:column>
							<display:column property="dtInicial"  title="Inicio" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
							<display:column property="dtFinal"  title="Fim" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
							<display:column title="Indicador" style="text-align:center;" sortable="true">
								<catalogo:flagDefault test="${acaoTO.isVigente=='S'}" />
							</display:column>
							<cata:temPermissao acao="produtoAcao,excluirProdutoAcaoTelevendas">
								<display:column title="Excluir" style="text-align:center;"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Ação de Venda" width="15" onclick="remove(${acaoTO.idAcao})" style="cursor: pointer;"/></display:column>
							</cata:temPermissao>
							<display:footer >
								<tr>
									<td colspan="5">
									 	<table class="tabelaIcones" cellpadding="0" cellspacing="0">
											<tr>
												<td width="30px;">Ícones:</td>
												<td width="15px;"><img src="/catalogo/static_server/img/bullets/icon-available.png" alt="Sim" /></td>
												<td width="30px;">Sim</td>
												<td width="15px;"><img src="/catalogo/static_server/img/bullets/icon-unavailable.png" alt="Não" /></td>
												<td width="30px;">Não</td>
												<td width="15px;"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Ação de Venda" width="15"/></td>
												<td >Excluir Ação de Venda</td>
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
					<catalogo:contentBox title="Cadastro Ações de Vendas" requiredFields="true">	
						<div class="fleft" style="width:50%; margin-bottom: 5px; margin-left: 5px;" id="contentForm"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 80px;"><label for="nmAcao">Nome:<font size="1px" color="#EEB422">*</font></label></div>
							<html:text property="nmAcao" styleId="nmAcao" style="width:300px;" maxlength="254"/>
						</div>
						<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;" > 
							<div class="label-form-bold label_required" style="text-align: right; width: 150px; margin-left: 10px;"><label for="sgAcao">Sigla:<font size="1px" color="#EEB422">*</font></label></div>
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${acaoForm.idAcao == null}"> --%>
<%-- 									<html:text property="sgAcao" styleId="sgAcao" style="width:100px;" maxlength="10"/> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<%-- 									<html:text property="sgAcao" styleId="sgAcao" style="width:100px;" maxlength="10" disabled="true" /> --%>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
								<c:if test="${acaoForm.sgAcao != null}">
									<html:text property="sgAcao" styleId="sgAcao" style="width:100px;" maxlength="10" disabled="true"/>
								</c:if>
								<c:if test="${acaoForm.sgAcao == null}">
									<html:text property="sgAcao" styleId="sgAcao" style="width:100px;" maxlength="10"/>
								</c:if>
						</div>
						<cata:temPermissaoPorSubSistema acao="produtoAcaoTelevendas">
							<html:hidden property="idCanalAtendimento" styleId="idCanalAtendimento" value="23611"/>
							<div class="fleft" style="width:27%; margin-bottom: 5px; margin-left: 5px;"> 
								<div class="label-form-bold label_required" style="text-align: right; width: 80px;"><label for="dtInicial">Inicio:<font size="1px" color="#EEB422">*</font></label></div>
								<html:text property="dtInicial" styleId="dtInicial" style="width:120px;" styleClass="required" maxlength="10" disabled="true" onKeyDown="mascara(this,data);" onKeyPress="mascara(this,data);" onKeyUp="mascara(this,data);"/>
							</div>
							<div class="fleft" style="width:25%; margin-bottom: 5px; margin-left: 5px;"> 
								<div class="label-form-bold label_required" style="text-align: right; width: 35px;"><label for="dtFinal">Fim:<font size="1px" color="#EEB422">*</font></label></div>
							    <html:text property="dtFinal" styleId="dtFinal" style="width:120px;" styleClass="required" maxlength="10" disabled="true" onKeyDown="mascara(this,data);" onKeyPress="mascara(this,data);" onKeyUp="mascara(this,data);"/>
							</div>
							<div class="fleft" style="width:45%;">
								<div class="label-form-bold label_required" style="text-align: right; width: 145px;"><label>Realizar Análise Fraude:<font size="1px" color="#EEB422">*</font></label></div>
									<label> Sim <html:radio property="indAnaliseFraude" styleId="indAnaliseFraude" value="S" /></label>
									<label> Não <html:radio property="indAnaliseFraude" styleId="indAnaliseFraude" value="N"/></label>
							</div>
							<div class="fleft" style="width:50%; margin-bottom: 5px; margin-left: 5px;"> 
								<div class="label-form-bold label_required" style="text-align: right; width: 80px;"><label for="idPerfis">Perfil:<font size="1px" color="#EEB422">*</font></label></div>
								<html:select property="idPerfis" id="idPerfis" style="width:300px; height: 70px;" multiple="true" styleId="idPerfis" onClick="selecionarTodos('idPerfis')">
		                    				<html:option value="">Todos</html:option>
		                    			<c:forEach var="perfilListTO" items="${perfilList}" >
		                    				<html:option value="${perfilListTO.id}">${perfilListTO.name}</html:option>
		                    			</c:forEach>
		       		             </html:select>
							</div>
							<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 5px;" > 
								<div class="label-form-bold label_required" style="text-align: right; width: 150px; margin-left: 10px;"><label for="idFornecedores">Fornecedores:<font size="1px" color="#EEB422">*</font></label></div>
								<html:select property="idFornecedores" style="width:200px; height: 70px;" styleId="idFornecedores" onClick="selecionarTodos('idFornecedores')" multiple="true">
		                    			<html:option value=""> Todos </html:option>
		                    		<c:forEach var="fornecedorListTO" items="${fornecedorList}">
		                    			<html:option value="${fornecedorListTO.idFornecedorSCA}">${fornecedorListTO.nmFornecedorSCA}</html:option>
		                    		</c:forEach>
		                    	</html:select>
							</div>
						</cata:temPermissaoPorSubSistema>
						<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 80px;"><label for="dsAcao">Descrição:&nbsp;&nbsp;&nbsp;</label></div>
							<html:textarea property="dsAcao" styleId="dsAcao" style="width:300px;" rows="5" onKeyDown="maxLengthTextArea(this, 254)" onKeyUp="maxLengthTextArea(this, 254)"/>
						</div>
						<div class="fleft" style="width:90%; margin-left: 10px; clear: both;" id="contentDisponivel">
							<div class="label-form-bold label_required" style="text-align: right; width: 80px;"><label>Disponivel:<font size="1px" color="#EEB422">*</font></label></div>
							<label> Sim <html:radio property="inDisponivel" styleId="inDisponivel" value="S"/></label>
							<label> Não <html:radio property="inDisponivel" styleId="inDisponivel" value="N"/></label>
						</div>
						<cata:temPermissaoPorSubSistema acao="produtoAcaoTelevendas">
							<script>
								document.getElementById("contentDisponivel").style.display = "none";
							</script>
						</cata:temPermissaoPorSubSistema>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
						<html:button property="botao_cancelar_form" styleId="botao_cancelar_form" styleClass="btNavegacao74" onClick="cancel()" value="Cancelar" alt="Cancelar" title=""/><span>&nbsp;</span> 
							<cata:temPermissao acao="produtoAcao,gravarProdutoAcaoTelevendas">
						<html:button property="botao_salvar_form" styleId="botao_salvar_form" onClick="save()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span> 
								<input type="hidden" name="labelSucess" id="labelSucess"/>
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</c:if>
			</div>
			</html:form>
   </jsp:body>        
</catalogo:defaultTemplate>