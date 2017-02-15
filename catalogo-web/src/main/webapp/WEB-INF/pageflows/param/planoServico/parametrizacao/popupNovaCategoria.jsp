<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:popupPadrao>
	<div name="conteudo">
		<div id="popup_valores_categoria" style="position:relative;">
			<c:set var="nomeBox" scope="application" value="Grupo de Serviços"/>
				<jsp:include page="/templates/box_pre.jsp"/>
					<div class="legendaObrigatorio">(*) Campo Obrigatório</div>					
					<div style="width: 99%; height: 60px;">
						
							<table style="width: 99%">
								<tr>
									<td style="text-align: left;">
										<div id="adicionarValor">
																					
											
											<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/adicionarCategorias.do?id_categoria=${idCategoria}&id_plataforma=${idPlataforma}">
												<div class="fleft" style="width: 100%;">
													<div class="label-form-bold2 fleft label_required" style="width: 120px;">Grupo de Serviços:<font size="1px" color="#EEB422" valign="center">*</font></div>
													<html:text property="nmCategoria" size="45" styleClass="required editavel" onkeypress="return semPontoVirgula(event);" maxlength="50"></html:text>												
													<html:hidden property="idCategoria" value="${idCategoria}"/>																										 										
												</div>
												
												<br clear="all" /><br clear="all" />
										   		<div class="fleft" style="width: 100%;">
													<div class="label-form-bold2 fleft label_required" style="width: 120px">Status:<font size="1px" color="#EEB422" valign="center">*</font></div>
													<html:select property="indisponivel" tabindex="2" style="width: 47.2%;"  styleClass="required editavel" styleId="select_categoria">
													   <option value="S">Disponivel</option>
													   <option value="N">Indisponivel</option>				    				   			   				   
												 	</html:select> 
													<html:button property="bt_incluir" value="Incluir" styleClass="btNavegacao74" styleId="btIncluir" onclick="clearEditando();send(this, null, null, 'div_erros_popup');setEditando();" bundle="messages" titleKey="catalogo.popupIncluirNovaCategoria.incluir"/>
												</div>
											</html:form>
											
										</div>
										<div id="alterarValorCategoria">
											
										</div>
									</td>
								</tr>
							</table>
						</div>		
						<div class="both-scroll" style="height:270px;">
						 <table border="0" cellspacing="0" cellpadding="0"  class="tabela-padrao tablesorter" id="TabelaRelacionamentosRecentes" width="90%">																														
							 <!-- <table border="0" cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" id="TabelaRelacionamentosRecentes" width="90%"> -->							
												<thead>
													<tr>
														<th>Descri&ccedil;&atilde;o</th>
														<th>Status</th>
														<th>Alterar</th>
														<th>Excluir</th>														
													</tr>
												</thead>												
									<tbody>											  										
									<logic:iterate id="iterateCategorias" property="categorias" name="servicoForm">
												<tr>
													<td class="word-wrap"><label class="lblForm">${iterateCategorias.nmCategoria}</label></td>
													<td class="word-wrap"><label class="lblForm">${iterateCategorias.indisponivel == 'S' ? 'Disponível' : 'Indisponível'}</td>
													<td class="center">
														<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/abrirAlterarCategoria.do?id_categoria=${iterateCategorias.idCategoria}&nm_categoria=${iterateCategorias.nmCategoria}&indisponivel=${iterateCategorias.indisponivel}" onclick="if(abrirLink('alterarValorCategoria',this.href, 'popup_valores_categoria')){$('adicionarValor').hide();}return false;">
															<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" />
														</html:link> 																										
													</td>
													<td class="center">
														<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/abrirPopupApagarCategoria.do?id_categoria=${iterateCategorias.idCategoria}&nm_categoria=${iterateCategorias.nmCategoria}" onclick="abrirPopup2(this.href, null, 'popup_valores_categoria');return false;">
															<img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" onClick="" alt="Excluir" />
														</html:link> 
																							
													</td> 													
												</tr>																									
										</logic:iterate>										 
									</tbody>  									
							</table>	 							
						 </div>						 
						 <jsp:include page="/templates/box_pos.jsp"/>						 
					</div>			
		</div>
</catalogo:popupPadrao>
