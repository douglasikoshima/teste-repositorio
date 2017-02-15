<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<catalogo:defaultTemplate titulo="Home Catalogo">
    <jsp:attribute name="headScripts">
        <link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/matrizofertaprodutopreco.css"/>
		<script type="text/javascript" src="/catalogo/static_server/js/matrizofertaprodutopreco.js"></script>
    </jsp:attribute>
   <jsp:body>
        <div id="conteudo_success" style="color: #299B00;display: none" ></div>
        <div class="breadcrumb"> Parametriza&ccedil;&atilde;o > Matriz Oferta > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/produto/MatrizOfertaProdutoController.jpf';" style="cursor: pointer;">Produto Matriz Oferta</a><span></div>
        <script>carregaMenu('mn_parametrizacao_matriz_oferta_produto_preco');</script>
<!--    <netui:form action="search" tagId="acaoForm" genJavaScriptFormSubmit="false"> -->
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/produto/search.do" styleId="produtoForm">
			<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
			<c:if test="${labelError != null}">
				<script>
					generateContentErrorForm("${labelError}")
				</script>
			</c:if>
            <div class="secao_conteudo">
				<catalogo:contentBox title="Pesquisar" doubt="false" requiredFields="true">
					<div class="line" >
					    <div class="linerow bold"><label for="cdProduto">C&oacute;digo do Produto:</label><span class="required">*</span></div>
					    <div class="linerow bold"><label for="nmProduto">Nome do Produto:</label></div>
					    <div class="linerow bold"><label for="idCanalAtendimento">Canal Atend.:</label><span class="required">*</span></div>
					</div>
					<div class="line">
<!-- 					<div class="linerow bold"><netui:textBox tagId="cdProduto" dataSource="actionForm.cdProduto" /></div> -->
<!-- 					<div class="linerow bold"><netui:textBox tagId="nmProduto" dataSource="actionForm.nmProduto" /></div> -->
						<div class="linerow bold"><html:text property="cdProduto" styleId="cdProduto"/></div>
						<div class="linerow bold"><html:text property="nmProduto" styleId="nmProduto"/></div>
						<div class="linerow bold">
							<%-- <netui:select styleClass="required" style="width:140px;" tagId="idCanalAtendimento" dataSource="actionForm.idCanalAtendimento" defaultValue="-- selecione --"
							optionsDataSource="${canalAtendimentoTOList}" repeater="true" repeatingOrder="default, option">
			                   	<c:if test="${container.metadata.optionStage}">
				                    <netui:selectOption repeatingType="option" value="${container.item.idCanalAtendimento}">
				                        ${container.item.nmCanal} 
				                    </netui:selectOption>
				                </c:if>
								<c:if test="${container.metadata.defaultStage}">
									<netui:selectOption repeatingType="default" value="">
										${container.item}
									</netui:selectOption>
								</c:if>
			               	</netui:select> --%>
			               	<html:select property="idCanalAtendimento" styleClass="required" style="width:140px;" styleId="idCanalAtendimento">
			               		<html:option value="">-- selecione --</html:option>
			               		<c:forEach var="canalAtendimentoTO" items="${canalAtendimentoTOList}">
			               			<html:option value="${canalAtendimentoTO.idCanalAtendimento}">
			               				${canalAtendimentoTO.nmCanal}
			               			</html:option>
			               		</c:forEach>
			               	</html:select>
			            </div>
					</div>
					<div class="line">
					    <div class="linerow2 bold"><label for="idOfertaSAPList">Oferta SAP:</label><span class="required">*</span></div>
					    <div class="linerow2 bold"><label for="idOrganizacaoVendasList">Org. de Venda:</label><span class="required">*</span></div>
					</div>
					<div class="line">
						<div class="linerow2 bold">
							<%-- <netui:select styleClass="required" style="width:300px;" tagId="idOfertaSAPList" dataSource="actionForm.idOfertaSAPList" multiple="true" defaultValue="ofteste2"
							optionsDataSource="${ofertaSAPTOList}" repeater="true" repeatingOrder="null, option" nullable="true">
			                   	<c:if test="${container.metadata.optionStage}">
				                    <netui:selectOption styleClass="checkElemOfertaSAP" repeatingType="option" value="${container.item.idOfertaSAP}">
				                        ${container.item.cdOfertaSAP}
				                    </netui:selectOption>
				                </c:if>
			               	</netui:select> --%>
			               	<html:select property="idOfertaSAPList" styleClass="required" style="width:300px;" styleId="idOfertaSAPList" multiple="true">
<%--   			               	<html:option value="">-- Selecione --</html:option>   --%>
			               		<c:forEach var="ofertaSAPTO" items="${ofertaSAPTOList}">
			               			<html:option styleClass="checkElemOfertaSAP" value="${ofertaSAPTO.idOfertaSAP}"> ${ofertaSAPTO.cdOfertaSAP} </html:option>
			               		</c:forEach>
			               	</html:select>
			            </div>

						<div class="linerow2 bold">
							<%-- <netui:select styleClass="required" style="width:60px;" tagId="idOrganizacaoVendasList" dataSource="actionForm.idOrganizacaoVendasList" multiple="true" 
							optionsDataSource="${organizacaoVendaTOList}" repeater="true" repeatingOrder="null, option" nullable="true">
			                   	<c:if test="${container.metadata.optionStage}">
				                    <netui:selectOption styleClass="checkElemOrganizacaoVendas" repeatingType="option" value="${container.item.idOrganizacaoVendas}">
				                        ${container.item.sgOrganizacaoVendas}
				                    </netui:selectOption>
				                </c:if>
			               	</netui:select> --%>
			               	<html:select property="idOrganizacaoVendasList" styleClass="required" style="width:60px;" styleId="idOrganizacaoVendasList" multiple="true">
<%-- 			               	<html:option value="">-- Selecione --</html:option> --%>
			               		<c:forEach var="organizacaoVendaTO" items="${organizacaoVendaTOList}">
			               			<html:option styleClass="checkElemOrganizacaoVendas" value="${organizacaoVendaTO.idOrganizacaoVendas}"> ${organizacaoVendaTO.sgOrganizacaoVendas} </html:option>
			               		</c:forEach>
			               	</html:select>
			            </div>
					</div>
					<br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
<!-- 					<netui:button type="button" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Gravar" title=""/> -->
						<html:button property="gravar" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Gravar" title=""/>
						<span>&nbsp;</span>
						</br></br>
					</div>
				</catalogo:contentBox>
				<div id="mensagemLoadSearch" class="linerow bold">Aguarde, carregando dados...</div>
				<div id="resultado_pesquisa">
					<c:if test="${mensagemResultadoSearch != null}">
						<catalogo:contentBox title="Resultado da Pesquisa" doubt="false" requiredFields="false">
							<table class="tabela-padrao-new" id="precoTable" style="width: 99%">
								<thead>
									<tr>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${mensagemResultadoSearch}</td>
									</tr>
								</tbody>
							</table>
							<div class="botao">
								<div class="barra"></div>
								<cata:temPermissao acao="excluirProdutoMatrizOferta">
<!-- 								<netui:button type="button" onClick="removePrecoList()" styleClass="btNavegacao74" value="Remover" alt="Remover" title=""/> -->
									<html:button property="bt_remover" onClick="removePrecoList()" styleClass="btNavegacao74" value="Remover" alt="Remover" title=""/>
								</cata:temPermissao>
								<span>&nbsp;</span>
<!-- 								<netui:button type="button" onClick="exportar()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title=""/> -->
									<html:button property="bt_exportar" onClick="exportar()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title=""/>
								</br></br>
							</div>
						</catalogo:contentBox>
						<script type="text/javascript">
							hideSpinner();
						</script>
					</c:if>
				</div>
			</div>
	     </html:form>
   </jsp:body>    
 </catalogo:defaultTemplate>