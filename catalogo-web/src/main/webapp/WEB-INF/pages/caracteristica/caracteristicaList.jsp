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
		<script type="text/javascript" src="/catalogo/static_server/js/caracteristica.js"></script>													
	</jsp:attribute>
	
		<jsp:body>
			<div class="breadcrumb">Parametrização > Produtos > <span><a onclick="document.location.href='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/CaracteristicaAction.do';" style="cursor: pointer;">Caracter&iacute;sticas</a></span></div>
			<script>carregaMenu('menu_CaracteristicasController');</script>
			<div class="secao_conteudo">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/search.do" styleId="caracteristicaForm" onsubmit="false">
				<catalogo:contentBox title="Característica" doubt="true">
					<html:hidden styleId="idCaracteristica" property="idCaracteristica"/>
					
					<div class="fleft" style="width:35%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="text-align: right; width: 90px;"><label for="nmCaracteristicaSearch">Característica:&nbsp;</label></div>
						<html:text property="nmCaracteristicaSearch" styleId="nmCaracteristicaSearch" style="width: 150px;" styleClass="required" maxlength="254"/>
					</div>
					<div class="fleft" style="margin-bottom: 5px; width:40%;"> 
						<div class="label-form-bold label_required" style="text-align: right; width: 140px;"><label for="idGrupoCaracteristicaSearch">Grupo de Característica:&nbsp;</label></div>
                    	<html:select property="idGrupoCaracteristicaSearch" styleId="idGrupoCaracteristicaSearch" styleClass="required" style="width:150px;">
                    		<html:option value="">-- Selecione --</html:option>
                    		<c:forEach var="grupoCaracteristicaList" items="${grupoCaracteristicaList}">
                    			<html:option value="${grupoCaracteristicaList.idGrupoCaracteristica}">${grupoCaracteristicaList.nmGrupoCaracteristica}</html:option>
                    		</c:forEach>
                    	</html:select>
					</div>
					<div class="fleft" style="width:20%; ">
						<div class="label-form-bold label_required" style="text-align: right; width: 40px;"><label>Disponível:&nbsp;</label></div>
						<label><html:radio property="inDisponivelSearch" styleId="inDisponivelSearch" value="S"/>&nbsp;Sim</label>
						<label><html:radio property="inDisponivelSearch" styleId="inDisponivelSearch" value="N"/>&nbsp;Não</label>
					</div>
					<br />
					<div class="fleft" style="width:35%; clear: both; margin-left: 5px;">
						<div class="label-form-bold label_required" style="text-align: right; width: 90px;"><label>Comparável:&nbsp;</label></div>
						<label><html:radio property="indComparavelSearch" styleId="indComparavelSearch" value="S"/>&nbsp;Sim</label>
						<label><html:radio property="indComparavelSearch" styleId="indComparavelSearch" value="N"/>&nbsp;Não</label>
					</div>
					<div class="fleft" style="width:40%;">
						<div class="label-form-bold label_required" style="text-align: right; width: 140px;"><label>Visível para o Cliente:&nbsp;</label></div>
						<label><html:radio property="indExibivelSearch" styleId="indExibivelSearch" value="S"></html:radio>&nbsp;Sim</label>
						<label><html:radio property="indExibivelSearch" styleId="indExibivelSearch" value="N"></html:radio>&nbsp;Não</label>
					</div>
					<div class="fleft" style="width:20%;">
						<div class="label-form-bold label_required" style="text-align: right; width: 66px;"><label>Filtro:&nbsp;</label></div>
						<label><html:radio property="inSimuladorSearch" styleId="inSimuladorSearch" value="S"></html:radio>&nbsp;Sim</label>
						<label><html:radio property="inSimuladorSearch" styleId="inSimuladorSearch" value="N"></html:radio>&nbsp;Não</label>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<cata:temPermissao acao="adicionarCaracteristica">
							<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onclick="create()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/>
							<span>&nbsp;</span>
						</cata:temPermissao>
						<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onclick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
						<span>&nbsp;</span>	
					    <html:button property="botao_limpar_form" styleId="botao_limpar_form" onclick="clearPage()" styleClass="btNavegacao74" value="Limpar" alt="Limpar" title=""/>
					</div>
				</catalogo:contentBox>
				
				<c:if test="${caracteristicaList != null}">
					<catalogo:contentBox title="Resultado da Pesquisa">
						<display:table name="caracteristicaList" id="caracteristicaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="Característica" sortable="true" headerClass="sortable" >
								<cata:temPermissao acao="alterarCaracteristica"><a href="javascript:editar(${caracteristicaTO.idCaracteristica})"></cata:temPermissao> ${caracteristicaTO.nmCaracteristica}<cata:temPermissao acao="alterarCaracteristica"></a></cata:temPermissao>
							</display:column>
							<display:column property="grupoCaracteristicaTO.nmGrupoCaracteristica"  title="Grupo de Característica" sortable="true" headerClass="sortable"/>
							<display:column title="Exibir" style="text-align:center;" sortable="true">
								<catalogo:flagDefault test="${caracteristicaTO.indExibivel=='S'}" />
							</display:column>
							<display:column title="Comparável" style="text-align:center;" sortable="true">
								<catalogo:flagDefault test="${caracteristicaTO.indComparavel=='S'}" />
							</display:column>
							<display:column title="Disp" style="text-align:center;" sortable="true">
								<catalogo:flagDefault test="${caracteristicaTO.inDisponivel=='S'}" />
							</display:column>
							<display:column title="Filtro" style="text-align:center;" sortable="true">
								<catalogo:flagDefault test="${caracteristicaTO.inSimulador=='S'}" />
							</display:column>
							<cata:temPermissao acao="excluirCaracteristica">
								<display:column title="Excluir" style="text-align:center;">
									<c:choose>
										<c:when test="${caracteristicaTO.existGrupoProdutoCaracteristica}">
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/verificarAssociacaoExclusao.do?idCaracteristica=${caracteristicaTO.idCaracteristica}"
												onclick="abrirPopup1(this.href);return false;">
												<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Característica" width="15" style="cursor: pointer;"/>
											</html:link>
										</c:when>
										<c:otherwise>
											<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Característica" width="15" onclick="remove(${caracteristicaTO.idCaracteristica})" style="cursor: pointer;"/>
										</c:otherwise>
									</c:choose>
								</display:column>
							</cata:temPermissao>
							<cata:temPermissao acao="consultarValoresCaracteristica">
								<display:column title="Valores" style="text-align:center;">
									<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/findValores.do?idCaracteristica=${caracteristicaTO.idCaracteristica}"
										onclick="abrirPopup1(this.href);return false;" title="Valores da Característica">
										<img alt="Valores da Característica" src="/catalogo/static_server/img/botoes/valorCarac.gif" />
									</html:link>
								</display:column>
							</cata:temPermissao>
							<display:footer>
								<tr>
									<td colspan="8">
									 	<table class="tabelaIcones">
											<tr>
												<td width="50px;">Ícones:</td>
												<td width="15" align="center"><img src="/catalogo/static_server/img/bullets/icon-available.png" alt="Sim" /></td>
												<td width="30">Sim</td>
												<td width="15" align="center"><img src="/catalogo/static_server/img/bullets/icon-unavailable.png" alt="Não" /></td>
												<td width="30">Não</td>
												<td width="15" align="center"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Ação" width="15"/></td>
												<td width="130">Excluir Característica</td>
												<td width="15" align="center"><img src="/catalogo/static_server/img/botoes/valorCarac.gif" alt="Valores da Característica" /></td>
												<td>Valores da Característica</td>
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
					<catalogo:contentBox title="Cadastro de Característica" requiredFields="true">	
						<div class="fleft" style="width:50%; margin-bottom: 5px; margin-left: 5px;" id="contentForm"> 
							<div class="label-form-bold label_required" style="text-align: right;"><label for="idGrupoCaracteristicaCadastro">Grupo de Característica:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
	                    	<html:select property="idGrupoCaracteristicaCadastro" styleId="idGrupoCaracteristicaCadastro" styleClass="required" style="width:150px;">
	                    		<html:option value="">-- Selecione --</html:option>
	                    		<c:forEach var="grupoCaracteristicaList" items="${grupoCaracteristicaList}">
	                    			<html:option value="${grupoCaracteristicaList.idGrupoCaracteristica}">${grupoCaracteristicaList.nmGrupoCaracteristica}</html:option>
	                    		</c:forEach>
	                    	</html:select>
						</div>
						<div class="fleft" style="width:35%; margin-right: 10px;">
							<div class="label-form-bold label_required" style="text-align: right; width: 150px; margin-left: 5px;"><label>Disponível:<font size="1px" color="#EEB422">*</font></label></div>
							<label><html:radio property="inDisponivelCadastro" styleId="inDisponivelCadastro" value="S"/>&nbsp;Sim</label>
							<label><html:radio property="inDisponivelCadastro" styleId="inDisponivelCadastro" value="N"/>&nbsp;Não</label>
						</div>
						<div class="fleft" style="width:50%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right; "><label for="nmCaracteristicaCadastro">Nome Característica:<font size="1px" color="#EEB422">*</font></label></div>
							<html:text property="nmCaracteristicaCadastro" styleId="nmCaracteristicaCadastro" style="width:250px;" maxlength="254"/>
						</div>
						<div class="fleft" style="width:35%; margin-right: 10px;">
							<div class="label-form-bold label_required" style="text-align: right; width: 150px; margin-left: 5px;"><label>Filtro:<font size="1px" color="#EEB422">*</font></label></div>
							<label><html:radio property="inSimuladorCadastro" styleId="inSimuladorCadastro" value="S"/>&nbsp;Sim</label>
							<label><html:radio property="inSimuladorCadastro" styleId="inSimuladorCadastro" value="N"/>&nbsp;Não</label>
						</div>
						<div class="fleft" style="width:50%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right; margin-right: 0px;"><label for="nmAcao">Valores:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
							<input type="text" style="width:150px; margin-right: 0px;" disabled="disabled"/>
							<html:link styleId="idPopUpValores" action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/abrirValores.do?idCaracteristica=${caracteristicaForm.idCaracteristica}"
								onclick="abrirPopUpValores(this.href)" title="Valores da Característica">
								<input type="button" value="..." style="margin-left: 0px;"/>
							</html:link>
						</div>
						<div class="fleft" style="width:35%; margin-right: 10px;">
							<div class="label-form-bold label_required" style="text-align: right; width: 150px; margin-left: 5px;"><label>Visível para o Cliente:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
							<label><html:radio property="indExibivelCadastro" styleId="indExibivelCadastro" value="S"/>&nbsp;Sim</label>
							<label><html:radio property="indExibivelCadastro" styleId="indExibivelCadastro" value="N"/>&nbsp;Não</label>
						</div>
						<div class="fleft" style="width:50%; margin-bottom: 5px; margin-left: 5px;"> 
							<div class="label-form-bold label_required" style="text-align: right;"><label for="descricaoCadastro">Observação:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
							<html:textarea property="descricaoCadastro" styleId="descricaoCadastro" style="width:250px;" rows="5" onkeydown="maxLengthTextArea(this, 254)" onkeyup="maxLengthTextArea(this, 254)"/>
						</div>
						<div class="fleft" style="width:35%; margin-right: 10px;">
							<div class="label-form-bold label_required" style="text-align: right; width: 150px; margin-left: 5px;"><label>Comparável:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
							<label><html:radio property="indComparavelCadastro" styleId="indComparavelCadastro" value="S"/>&nbsp;Sim</label>
							<label><html:radio property="indComparavelCadastro" styleId="indComparavelCadastro" value="N"/>&nbsp;Não</label>
						</div>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onclick="cancel()" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title=""/><span>&nbsp;</span>
							<c:choose>
								<c:when test="${caracteristicaForm.existGrupoProdutoCaracteristica}">
									<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/verificarAssociacaoAlteracao.do?idCaracteristica=${caracteristicaForm.idCaracteristica}"
										onclick="if(validarForm()){abrirPopup1(this.href);}return false;">
										<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
									</html:link>
								</c:when>
								<c:otherwise>
									<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onclick="save()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
								</c:otherwise>
							</c:choose>
							
						</div>
					</catalogo:contentBox>
					<c:if test="${abrirPopUp}">
						<script>
							abrirPopUpValores(document.getElementById('idPopUpValores').href);
						</script>
					</c:if>
				</c:if>
				
				</html:form>
			</div>
	</jsp:body>	
</catalogo:defaultTemplate>
