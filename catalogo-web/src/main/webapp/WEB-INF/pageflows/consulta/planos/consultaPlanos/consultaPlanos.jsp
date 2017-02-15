<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<div class="breadcrumb"> Consultas > Planos > <span><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/ConsultaPlanosAction.do');" style="cursor: pointer;">Consulta Planos</a><span><span></div>
			<div class="secao_conteudo" style="position:relative;">
				<div class="conteudo_box_top">
					<div class="conteudo_box_top_center">
						Consulta Planos:
					</div>
					<div class="conteudo_box_top_esq">
					</div>
					<div class="conteudo_box_top_dir openclose">
						<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" title="Reduzir/Expandir Bloco" />
					</div>
				</div>
				<div>
					<div>
						<div class="conteudo_box_middle">
							<div class="conteudo_box_middle_mg">
	
								<div id="pesquisa_simplificada" class="relative">
									<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/listarVariaveisPlano.do">
										<html:hidden styleId="pagina_solicitada" property="paginaSolicitada"/>
										<div class="legendaObrigatorio help">(*) Campo Obrigatório</div>
										<div class="link_manual" title="Dúvida">
											<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161353" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
										</div>
										<div class="fleft">
											<div class="label-form-bold" style="width:110px;">Nome Plano:</div>
											<html:text tabindex="1" property="nomePlano" size="25"/>
										</div>
										
										<div class="fleft">
											<div class="label-form-bold" style="width:110px;">Código do Plano:</div>
											<html:text tabindex="2" property="codigoPlano" size="25"/>
										</div>
										
										<br clear="all"/>
										<br clear="all"/>																				
										<div class="fleft">
											<div class="label-form02 label_required" style="width:110px;">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
											
											<html:select tabindex="4" property="idPlataforma" styleId="select_plataforma" style="width:140px;" styleClass="required" onchange="$('hiddenUFs').value=''; setRequiredTecnologia(this, 'select_tecnologia'); setRequiredUF(this, 'hiddenUFs'); setRequiredSistema(this, 'select_sistema')">
				                                <html:option value="">--Selecione--</html:option>
				                                <c:forEach var="idPlataformaTO" items="${plataformas}">
				                                    <html:option value="${idPlataformaTO.idPlataforma}">${idPlataformaTO.nmPlataforma}</html:option>
				                                </c:forEach>
				                            </html:select>	
											
										</div>
										
										<div class="fleft">
											<div class="label-form-bold label_required" style="width:110px;">Tecnologia:</div>
											
											<html:select tabindex="5" property="idTecnologia" styleId="select_tecnologia" style="width:140px;" styleClass="required">
				                                <html:option value="">--Selecione--</html:option>
					                                <c:forEach var="idTecnologiaTO" items="${tecnologias}">
					                                	<c:if test="${idTecnologiaTO.tecnologiaReferencia == null}">
					                                    	<html:option value="${idTecnologiaTO.idTecnologia}">${idTecnologiaTO.nmTecnologia}</html:option>
					                                    </c:if>
					                                </c:forEach>
				                            </html:select>
											
										</div>
																				
										<div class="fleft">
											<div class="label-form-bold label_required" style="width:110px;">UF:</div>
											<html:text property="ufs" styleId="hiddenUFs" styleClass="hide"/>
											<input type="text"/>
											<html:button bundle="messages" property="btn_abrir" tabindex="6" onclick="if($F('select_plataforma') != ''){abrirPopup1($(this).next('a').href, [$('hiddenUFs')]);}else{addError('Por favor informar o campo Plataforma', true, true);}" value="..." titleKey="catalogo.consultaPlanos.Uf" />
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/listarUFs.do" styleClass="display:none;"/>
										</div>
										<br clear="all"/><br clear="all"/>
										
										<div class="fleft" id="divSistema" style="display: none">
											<div class="label-form02 label_required" style="width:110px;">Sistema:</div>
											
											<html:select tabindex="7" property="idSistema" styleId="select_sistema" style="width:140px;" styleClass="required" onChange="sistemaAtysSetRequiredTecnologia(this, 'select_tecnologia'); sistemaAtysSetRequiredUF(this, 'hiddenUFs')">
				                                <html:option value="">--Selecione--</html:option>
				                                <html:option value="2">ATLYS</html:option>
				                                <html:option value="3">NGIN</html:option>
				                            </html:select>
										</div>
											
										<br clear="all" />
										<br clear="all" />
										
										<div class="barra">
										</div>
					
										<div class="botao">
											<html:button bundle="messages" property="btn_limpar" tabindex="8" value="Limpar" onclick="$('resultado_pesquisa').hide();limparForm(this);" styleClass="btNavegacao74" titleKey="catalogo.global.Limpar" />											 
											<span>&nbsp;</span>
											<html:button bundle="messages" property="botao_listar_variaveis" styleId="botao_listar_variaveis" styleClass="btNavegacao74 hide" value="Variaveis" onClick="$(this).form.action=$(this).next('a').href;send(this, 'lista_variaveis', null, 'right_section')" titleKey="catalogo.consultaPlanos.Variaveis"/>
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/listarVariaveisPlano.do" styleClass="hide">
											</html:link>
											<html:button bundle="messages" property="bota_listar_planos" tabindex="7" styleId="bota_listar_planos" onClick="clearAndShow('resultado_pesquisa');$(this).form.action=$(this).next('a').href;send(this, 'resultado_pesquisa', null, 'right_section')" styleClass="btNavegacao74" value="Pesquisar" titleKey="catalogo.consultaPlanos.Pesquisar" />
											<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/pesquisaSimplesPlanos.do" styleClass="hide">
											</html:link>
										</div>
									</html:form>
								</div>
						</div>
						<div class="conteudo_box_bottom">
						</div>
					</div>
				</div>
			</div>
		</div>
			<br/>
			<div id="nova_frequencia" class="secao_conteudo" style="display: none;">
				<div class="conteudo_box_top">
					<div class="conteudo_box_top_center">
						Nova Frequência:
					</div>
					<div class="conteudo_box_top_esq">
					</div>
					<div class="conteudo_box_top_dir">
						<img src="/catalogo/static_server/img/background/box-topo-right.gif"/>
					</div>
				</div>
				<div>
					<div>
						<div class="conteudo_box_middle">
							<div class="conteudo_box_middle_mg" >
							</div>
						</div>
						<div class="conteudo_box_bottom">
						</div>
					</div>
				</div>
			</div>
			<div id="resultado_pesquisa" style="position:relative">
			</div>
