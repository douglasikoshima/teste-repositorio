<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

	<div class="secao_conteudo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Consulta Restri&ccedil;&otilde;es do Modelo:</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaRestricaoModelo/pesquisarModelo.do">
						<html:hidden property="paginaSolicitada" styleId="pagina_solicitada" />
						<div class="conteudo_box_middle_mg relative">
							<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
							<div class="link_manual" title="Dúvida">
								<html:link href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161344" target="_blank" >
									<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
								</html:link>
							</div>	 
							<div class="fleft">
								<div class="label-form02 label_required">Tipo de Produto:<font size="1px" color="#EEB422" valign="center">*</font></div>
									<html:select tabindex="1" style="width:140px;" property="tipoProduto">
										<html:option value="">-- Selecione --</html:option>
											<c:forEach var="tipos_produto" items="${tipos_produto}">
												<html:option value="${tipos_produto.idTipoProduto}">${tipos_produto.nmTipoProduto}</html:option>
											</c:forEach>							
									</html:select>
							</div>
								   
							<div class="fleft">
								<div nowrap="nowrap" class="label-form-bold">Modelo:</div>

								<html:text tabindex="2" property="modelo" styleId="modelo"/>	
							</div>
								<br clear="all"/>
								<br clear="all"/>
			
								<div class="barra">
								</div>
			
								<div class="botao">						
									<html:button bundle="messages" property="btn_limpar" tabindex="4" value="Limpar" onclick="$('resultado_pesquisa').hide();limparForm(this);" styleClass="btNavegacao74" titleKey="catalogo.global.Limpar"/> 
									<span>&nbsp;</span>								
									<html:button bundle="messages" property="botao_pesquisar_modelos" styleId="botao_pesquisar_modelos" tabindex="3" value="Pesquisar" onclick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section');" styleClass="btNavegacao74" onMouseDown="$('pagina_solicitada').value='';return false;" titleKey="catalogo.ConsultaRestricaoModelo.Pesquisar"/>
								</div>
						</div>
					</html:form>				
				</div> 
				<div class="conteudo_box_bottom"></div>
			</div> 
		</div> 
 	 </div>
<div id="resultado_pesquisa" style="position:relative;"></div>

				