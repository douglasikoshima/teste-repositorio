<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
		<jsp:attribute name="headScripts">			
			<script>
				function create() {
					document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/create.do";
				}
				
				function editar(id){
					
					document.getElementById("idServicoInteratividade").value = id;
					document.getElementById("pacoteBonusForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/create.do";
					document.getElementById("pacoteBonusForm").submit();
				}
				
				function clearPage() {
					document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/PacoteBonusAction.do";
				}	
				
				function search() {
					if (validarForm()) {						
						document.getElementById("pacoteBonusForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/search.do";
						document.getElementById("pacoteBonusForm").submit();
					}
				}
				
				function validarForm() {
					
					var camposErro = "";
					var retorno = true;
					
					if (document.getElementById("nmServico").value == ""
						&& document.getElementById("cdFuncionalidade").value == "0"
						&& document.getElementById("sgTipoLinha").value == "0"
						&& document.getElementById("url").value == "") {
						camposErro += "Favor preencher pelo menos um parâmetro da Pesquisa.";
					}
				
					if (camposErro != "") {
						generateContentError(camposErro);
						retorno = false;
					}
					
					return retorno;
				}
				
				function init(){
				
				}
			</script>
		</jsp:attribute>	
			
			<script>carregaMenu('menu_PacoteBonus');</script>
				<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
					<c:if test="${labelError != null}">
						<script>
							generateContentErrorForm("${labelError}")
						</script>
					</c:if>
			<catalogo:contentBox title="Serviço Interatividade" doubt="true">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/search.do" styleId="pacoteBonusForm">
					<fmt:bundle basename="catalogoprs_messages" >				
					<html:hidden styleId="idServicoInteratividade" property="idServicoInteratividade" />
					<div class="fleft" style="width:50%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="text-align: right; margin-left: 5px; width: 100px; ">
							<label for="nmServico">Servi&ccedil;o:</label>
						</div>
						<html:text styleId="nmServico" property="nmServico" style="width:300px;" styleClass="required" value="" maxlength="200"/>
					</div>
					<div class="fleft" style="margin-left: 10px; width:50%; clear: both; margin-bottom: 5px;">
						<div class="label-form-bold label_required" style="text-align: right; width: 100px;">
							<label for="cdFuncionalidade">Funcionalidade:</label>
						</div>
						<html:select styleClass="required" styleId="cdFuncionalidade" style="width:300px;" property="cdFuncionalidade">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="funcionalidadeTO" items="${funcionalidadeList}">
								<html:option value="${funcionalidadeTO.cdFuncionalidade}">${funcionalidadeTO.nmFuncionalidade}</html:option>
							</c:forEach>						
						</html:select>
					</div>					
					<div class="fleft" style="margin-left: 10px; width:50%; clear: both; margin-bottom: 5px;">
						<div class="label-form-bold label_required" style="text-align: right; width: 100px;">
							<label for="url">URL:</label>
						</div>
						<html:text styleId="url" property="url" style="width:300px;" maxlength="250"/>					
					</div>
					<div class="fleft" style="margin-left: 10px; width:50%; margin-bottom: 5px;">
						<div class="label-form-bold label_required" style=" text-align: right; width: 100px;">
							<label for="sgTipoLinha">Tipo Rede:</label>
						</div>
						<html:select styleClass="required" styleId="sgTipoLinha" style="width:150px;" property="sgTipoLinha">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="tipoLinhaTO" items="${tipoLinhaList}" >
								<html:option value="${tipoLinhaTO.idTipoLinha}|${tipoLinhaTO.sgTipoLinha}">${tipoLinhaTO.dscTipoLinha}</html:option>
							</c:forEach>
						</html:select>
					</div>					
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<cata:temPermissao acao="novoServicos">
							<html:button bundle="messages" styleId="botao_pesquisar_form" property="btn_pesquisar" onClick="create()" styleClass="btNavegacao74" value="Novo" altKey="catalogo.servicoInteratividade.novo" title=""/>
							<span>&nbsp;</span>
						</cata:temPermissao>
						<html:button bundle="messages" styleId="botao_pesquisar_form" property="btn_pesquisar" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" altKey="Pesquisar" title=""/>										    				    
						<span>&nbsp;</span>	
					    <html:button bundle="messages" styleId="botao_limpar_form" property="btn_limpar" onClick="clearPage()" styleClass="btNavegacao74" value="Limpar" altKey="Limpar" title=""/>					    				    
					</div>
					</fmt:bundle>
				</html:form>
			</catalogo:contentBox>
			<c:if test="${servicoInteratividadeList != null}">
				<catalogo:contentBox title="Resultado da Pesquisa">
					<display:table name="servicoInteratividadeList" id="servicoInteratividadeTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
						<display:column property="idServicoInteratividade" style="text-align: center;" title="Código" sortable="true" headerClass="sortable" />
						<display:column title="Nome do Serviço" sortable="true" headerClass="sortable" >
							<cata:temPermissao acao="detalheServicos">
								<a href="#" onclick="javascript:editar(${servicoInteratividadeTO.idServicoInteratividade})" >
							</cata:temPermissao>${servicoInteratividadeTO.nmServico}<cata:temPermissao acao="detalheServicos"></a></cata:temPermissao>
						</display:column>
						<display:column property="funcionalidadeTO.nmFuncionalidade"  title="Funcionalidade" sortable="true" headerClass="sortable"/>
						
						<display:column title="URL" sortable="true" headerClass="sortable" >
							<div class="noSroll">${servicoInteratividadeTO.url}</div>
						</display:column>
						<display:column title="Ativo" style="text-align:center;" sortable="true">
							<c:choose>
								<c:when test="${servicoInteratividadeTO.ativo=='S'}">
									<img alt="Serviço Ativo" src="/catalogo/static_server/img/bullets/icon-available.png"/>
								</c:when>
								<c:otherwise>
									<img alt="Serviço Inativo" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:footer >
							<tr>
								<td colspan="7">
								 	<table class="tabelaIcones">
										<tr>
											<td width="50px;"><label>&Iacute;cones:</label></td>
											<td width="15px"><img src="/catalogo/static_server/img/bullets/icon-available.png" alt="Serviço Ativo" /></td>
											<td width="90px">Servi&ccedil;o Ativo</td>
											<td width="15px"><img src="/catalogo/static_server/img/bullets/icon-unavailable.png" alt="Serviço Inativo" /></td>
											<td>Servi&ccedil;o Inativo</td>
										</tr>
									</table>
								</td>
							</tr>
						</display:footer>
					</display:table>
				</catalogo:contentBox>
			</c:if>
</catalogo:defaultTemplate>	
