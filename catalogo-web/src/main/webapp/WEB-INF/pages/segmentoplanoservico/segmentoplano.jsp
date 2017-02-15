<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<catalogo:defaultTemplate titulo="Home Catalogo">
	
	<jsp:attribute name="headScripts">
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/segmentoplanoservico.css"/>
		<script type="text/javascript" src="/catalogo/static_server/js/segmentoplanoservico.js"></script>
	</jsp:attribute>
	
	<jsp:body>
		<script>carregaMenu('mn_parametrizacao_segmento_planos_servicos');</script>
		<div id="message" style="margin-left: 5px;"/>
		<div class="breadcrumb">Parametriza&ccedil;&atilde;o > Planos Servi&ccedil;os > <span><a onclick="document.location.href = '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/SegmentoPlanoServicoAction.do'" style="cursor: pointer;">Configura&ccedil;&atilde;o de SubSegmento</a><span></div>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/searchPlano.do" styleId="segmentoPlanoServicoForm" onsubmit="false">
			<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
			<c:if test="${labelError != null}">
				<script>
					generateContentErrorForm("${labelError}")
				</script>
			</c:if>
			<html:hidden styleId="enabledCfgSegmento" property="enabledCfgSegmento"/>
			<div class="secao_conteudo">
				<catalogo:contentBox title="Pesquisar Ofertas" doubt="false" requiredFields="true">
					<div class="contentGroup">
						<fieldset id="searchType">
							<legend>Escolha o tipo de telefonia</legend>
							<div class="line">
								<html:radio property="searchType" styleId="idmovel" value="movel" onClick="openPageMovel(this)">Móvel</html:radio>
								<html:radio property="searchType" styleId="idmovel" value="fixa" onClick="openPageFixa(this)">Fixa</html:radio>
							</div>
						</fieldset>
					</div>
					<div class="barra"></div>
					<br/>
					<div class="line">
						<div class="linerow bold"><label for="idPlataforma">Plataforma</label><span class="required">*</span></div>
						<div class="linerow bold"><label for="idCategoria">Categoria</label></div>
						<div class="linerow bold"><label for="inTitular">Titular</label></div>
						<div class="linerow bold"><label for="cdPlano">Código do Plano</label></div>
					</div>
					<div class="line">
						<div class="linerow">
			               	<html:select property="idPlataforma" styleId="idPlataforma" styleClass="required" style="width:140px;">
			               		<html:option value="">--Selecione--</html:option>
			               		<c:forEach var="plataformaTOList" items="${plataformaTOList}">
			               			<html:option value="${plataformaTOList.idPlataforma}">${plataformaTOList.nmPlataforma}</html:option>
			               		</c:forEach>
			               	</html:select>
			            </div>
						<div class="linerow">
			               	<html:select property="idCategoria" styleId="idCategoria" styleClass="required" style="width:140px;">
			               		<html:option value="">--Selecione--</html:option>
			               		<c:forEach var="categoriaPlanoTOList" items="${categoriaPlanoTOList}">
			               			<html:option value="${categoriaPlanoTOList.idCategoria}">${categoriaPlanoTOList.nmCategoria}</html:option>
			               		</c:forEach>
			               	</html:select>
			            </div>
						<div class="linerow">
			               	<html:select property="inTitular" styleId="inTitular" styleClass="required" style="width:140px;">
			               		<html:option value="">-- selecione --</html:option>
			               		<html:option value="S">Sim</html:option>
			               		<html:option value="N">N&atilde;o</html:option>
			               	</html:select>
			            </div>
						<div class="linerow">
							<html:text property="cdPlano" styleId="cdPlano"/>
						</div>
					</div>
					<div class="line">
						<div class="linerow bold"><label for="nmPlano">Nome do Plano</label></div>
						<div class="linerow bold"><label for="idSegmento">Subsegmento</label></div>
						<div class="linerow bold"><label for="inInfancia">Inf&acirc;ncia</label></div>
					</div>
					<div class="line">
						<div class="linerow">
							<html:text property="nmPlano" styleId="nmPlano"/>
						</div>
						<div class="linerow">
			               	<html:select property="idSegmento" styleId="idSegmento" styleClass="required" style="width:140px;">
			               		<html:option value="">-- selecione --</html:option>
			               		<html:option value="-1">-- todos --</html:option>
			               		<html:option value="-2">-- não subsegmentados --</html:option>
			               		<c:forEach var="segmentoTOList" items="${segmentoTOList}">
			               			<html:option value="${segmentoTOList.idSegmento}">${segmentoTOList.dsSegmento}</html:option>
			               		</c:forEach>
			               	</html:select>
			            </div>
						<div class="linerow">
			               	<html:select property="inInfancia" styleId="inInfancia" styleClass="required" style="width:140px;">
			               		<html:option value="">-- selecione --</html:option>
			               		<html:option value="S">Sim</html:option>
			               		<html:option value="N">N&atilde;o</html:option>
			               	</html:select>
			            </div>
					</div>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_search_form" styleId="botao_search_form" onClick="searchPlano(this)" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
					</div>
				</catalogo:contentBox>
				
				<div id="messageLoading" class="linerow bold"> Aguarde, carregando dados... <br clear="all"/></div>
				<c:if test="${planoTOList != null}">
					<catalogo:contentBox title="Resultado da Pesquisa" doubt="false" >
					    <div id="tableCfgSegmento" class="tableCfgSegmento" style="${enabledCfgSegmento?'display: block':'display: none'}">
					    
							<display:table name="planoTOList" id="planoTO" pagesize="15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI="" class="tabela-padrao-new tablesorter table_body">
								<input type="checkbox" name="asd" value="aa"/>
								<display:column title="<input type='checkbox' id='checkAll' style='margin-left:4px' onclick='checkAllPlanoSegmento();' style='border:none'/>" headerClass="sortable" style="width:10px;">
									<html:checkbox property="idPlanoCheckedList" styleId="idPlanoAssociado_${planoTO.idPlano}" value="${planoTO.idPlano}" styleClass="semBorda"/>
								</display:column>
								<display:column title="Plataforma" sortable="true" headerClass="sortable">
									${planoTO.plataformaTO.nmPlataforma}
								</display:column>
								<display:column title="C&oacutedigo do Plano" property="cdPlano" sortable="true" headerClass="sortable"/>
								<display:column title="Nome do Plano" property="nmComercial" sortable="true" headerClass="sortable"/>
								<display:column title="SubSegmento" sortable="true" headerClass="sortable">
									${planoTO.segmentoTO.dsSegmento}
								</display:column>
								<display:column title="Infância" sortable="false" headerClass="sortable" style="text-align: center">
									<c:choose>
										<c:when test="${planoTO.segmentoTO != null && planoTO.segmentoTO.inInfancia == 'S'}">
											<img alt="Sim" src="/catalogo/static_server/img/bullets/icon-available.png"/>
										</c:when>
										<c:when test="${planoTO.segmentoTO != null && planoTO.segmentoTO.inInfancia == 'N'}">
											<img alt="Não" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
										</c:when>
										<c:otherwise/>
									</c:choose>
								</display:column>
							</display:table>	
							
							<div class="barra"></div>
							<div class="line">
								<div class="linerow bold"><label for="idSegmentoNew">Subsegmento</label><span class="required">*</span></div>
								<div class="linerow bold"><label for="inInfanciaNew">Infância</label></div>
							</div>
							<div class="line">
								<div class="linerow">
					               	<html:select property="idSegmentoNew" styleId="idSegmentoNew" styleClass="required" style="width:140px;">
					               		<html:option value="">-- selecione --</html:option>
					               		<c:forEach var="segmentoTOList" items="${segmentoTOList}">
					               			<html:option value="${segmentoTOList.idSegmento}">${segmentoTOList.dsSegmento}</html:option>
					               		</c:forEach>
					               	</html:select>
					            </div>
					            <div class="linerow"> 
					            	<html:checkbox property="inInfanciaNew" styleId="inInfanciaNew" styleClass="checkbox"/> 
					            </div>
							</div>
							<div class="btnLeftAlign">
								<div class="botao">
								    <cata:temPermissao acao="voltarSubSegmento">
										<html:button property="botao_back_form" styleId="botao_back_form" onClick="hideCfgTable()" styleClass="btNavegacao74" value="Voltar" alt="Voltar" title=""/><span>&nbsp;</span>
									</cata:temPermissao>
									<cata:temPermissao acao="desassociarSubSegmento">
										<html:button property="botao_disassocite_form" styleId="botao_disassocite_form" onClick="disassociatePlanoSegmento(this)" styleClass="btNavegacao74" value="Desassociar" alt="Desassociar" title=""/><span>&nbsp;</span>
									</cata:temPermissao>
									<cata:temPermissao acao="gravarSubSegmento">
										<html:button property="botao_save_form" styleId="botao_save_form" onClick="savePlanoSegmento(this)" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
									</cata:temPermissao>
								</div>
							</div>
						</div>
						<div id="tableSegmento" class="tableSegmento" style="${enabledCfgSegmento?'display: none':'display: block'}">
						<display:table name="planoTOList" id="planoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
								<display:column title="Plataforma" sortable="true" headerClass="sortable">
									${planoTO.plataformaTO.nmPlataforma}
								</display:column>
								<display:column title="C&oacutedigo do Plano" property="cdPlano" sortable="true" headerClass="sortable"/>
								<display:column title="Nome do Plano" property="nmComercial" sortable="true" headerClass="sortable"/>
								<display:column title="SubSegmento" sortable="true" headerClass="sortable">
									${planoTO.segmentoTO.dsSegmento}
								</display:column>
								<display:column title="Infância" sortable="false" headerClass="sortable" style="text-align: center">
									<c:choose>
										<c:when test="${planoTO.segmentoTO != null && planoTO.segmentoTO.inInfancia == 'S'}">
											<img alt="Sim" src="/catalogo/static_server/img/bullets/icon-available.png"/>
										</c:when>
										<c:when test="${planoTO.segmentoTO != null && planoTO.segmentoTO.inInfancia == 'N'}">
											<img alt="Não" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>
										</c:when>
										<c:otherwise/>
									</c:choose>
								</display:column>
						</display:table>
						<div class="barra"></div>
						<cata:temPermissao acao="configurarSubSegmento">
							<div class="botao">
								<html:button property="botao_configure_form" styleId="botao_configure_form" onClick="showCfgTable()" styleClass="btNavegacao74" value="Configurar" alt="Configurar" title=""/><span>&nbsp;</span>
							</div>
						</cata:temPermissao>
						</div>
						
					</catalogo:contentBox>
				</c:if>
			</div>
		</html:form>	
	</jsp:body>
</catalogo:defaultTemplate>
