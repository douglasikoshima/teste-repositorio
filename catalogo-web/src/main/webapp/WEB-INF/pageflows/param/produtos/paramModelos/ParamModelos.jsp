<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>

		<div class="secao_conteudo">
				<div class="conteudo_box_top">
					<div class="conteudo_box_top_center">
						Modelos:
					</div>
					<div class="conteudo_box_top_esq">
					</div>
					<div class="conteudo_box_top_dir openclose">
						<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
					</div>
				</div>
				<div>
					<div>
						<div class="conteudo_box_middle">
							<div class="conteudo_box_middle_mg relative">
								<div class="legendaObrigatorio help">
									(*) Campo Obrigatório
								</div>
								<div class="link_manual" title="Dúvida">
									<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161295" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
								</div>
								<netui:form action="pesquisarModelo">
									<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
									<netui:error key="tipoProduto"/>
									<div class="fleft">
										<div class="label-form-bold label_required" style="width:110px;">Tipo de Produto:<font size="1px" color="#EEB422" valign="center">*</font></div>
										<netui:select tabindex="1" style="width:140px;" styleClass="required" repeater="true" dataSource="actionForm.tipoProduto" defaultValue="-- Selecione --" optionsDataSource="${tipos_produto}"
											repeatingOrder="default, option" onChange="jsonListarFabricantes($(this).next().href, $F(this));return false;" tagId="id_tipo_produto">
											<c:if test="${container.metadata.defaultStage}">
											<netui:selectOption repeatingType="default" value="">
												${container.item}
											</netui:selectOption>
											</c:if>
											<c:if test="${container.metadata.optionStage}">
											<netui:selectOption repeatingType="option" value="${container.item.idTipoProduto}">
						                        ${container.item.nmTipoProduto}
						                    </netui:selectOption>
						                    </c:if>
										</netui:select>
										<netui:anchor action="buscarFabricantes" styleClass="display:none;"/>
									</div>
									
									<div class="fleft">
										<div class="label-form-bold" style="width:110px;">Fabricante:</div>
										<netui:select tabindex="2" tagId="select_fabricante" style="width:140px;"
												dataSource="actionForm.fabricante" styleClass="notRequired">
											<netui:selectOption  value="">-- Selecione --</netui:selectOption>
										</netui:select>
									</div>
									
									<div class="fleft">
										<div class="label-form-bold" style="width:110px;">Modelo:</div>
										<div class="hide min_size_required_message">Obrigatório informar, no mínimo, 2 caracteres para o campo Modelo.</div>
										<div class="hide min_size_required_value">2</div>
										<netui:textBox tabindex="3" styleClass="min_size_required" dataSource="actionForm.modelo"/>
									</div>
									<br clear="all"/><br clear="all"/>
									
									<div class="fleft">
										<div class="label-form-bold" style="width:110px;">Tecnologia:&nbsp;&nbsp;</div>
										<netui:textBox dataSource="actionForm.tecnologias" tagId="hiddenTecnologias" styleClass="hide" />
										<netui:textBox dataSource="actionForm.nomesTecnologias" tagId="hiddenTecnologiasNomes" styleClass="hide" />
										<input type="text"/><input tabindex="4" onclick="abrirPopup1($(this).next().href, [$('hiddenTecnologias')])" type="button" value="..." title="${bundle.default['catalogo.ParamModelos.Tecnologia']}"/>
										<netui:anchor action="listarTecnologias" style="display:none;"/>
									</div>
									
									<div class="fleft">
										<div class="label-form-bold" style="width:110px;">Característica:</div>
										<netui:textBox dataSource="actionForm.caracteristicas" tagId="hiddenCaracteristicas" styleClass="hide"/>
										<netui:textBox dataSource="actionForm.nomesCaracteristicasValores" tagId="hiddenCaracteristicasNomes" styleClass="hide"/>
										<input type="text"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hiddenCaracteristicas')])" type="button" value="..." title="${bundle.default['catalogo.ParamModelos.Caracteristica']}"/>
										<netui:anchor action="listarCaracteristicas" style="display:none;"/>
									</div>
									
									<br clear="all"/>
									<br clear="all"/>
	
									<div class="barra">
									</div>
	
									<div class="botao">
										<!--
											<input type="button" tabindex="7" onClick="habilitar('botao_novo_modelo');clearAndShow('resultado_pesquisa');$('novo_modelo').hide();limparForm(this);Element.hide('botao_novo_modelo');return false;" value="Limpar" class="btNavegacao74" title="${bundle.default['catalogo.global.Limpar']}" />
											<span>&nbsp;</span>
											<netui:button tabindex="6" tagId="botao_pesquisar" type="button" onMouseDown="$('pagina_solicitada').value=1" onClick="$('novo_modelo').hide();habilitar('botao_novo_modelo');$('resultado_pesquisa').innerHTML='';$('resultado_pesquisa').show();if(send(this, 'resultado_pesquisa')){Element.show('botao_novo_modelo');}" styleClass="btNavegacao74" value="Pesquisar" title="${bundle.default['catalogo.ParamModelos.Pesquisar']}"/>
											<cata:temPermissao acao="criarModelo">
												<span>&nbsp;</span><input style="display: none;" id="botao_novo_modelo" type="button" onclick="if(abrirLink('novo_modelo', $(this).next('a').href, 'right_section', [$('id_tipo_produto'), $('select_fabricante')])){$('novo_modelo').show();desabilitar(this);}return false;" class="btNavegacao74" value="Novo" title="${bundle.default['catalogo.ResultadoBuscaModelos.Novo']}" />
													<netui:anchor action="abrirNovoModelo" styleClass="hide">																								
												</netui:anchor>
											</cata:temPermissao>
										-->
										<input type="button" tabindex="7" onClick="clearAndShow('resultado_pesquisa');$('novo_modelo').hide();limparForm(this);return false;" value="Limpar" class="btNavegacao74" title="${bundle.default['catalogo.global.Limpar']}" id="btn_limpar"/>
										<span>&nbsp;</span>
										<netui:button tabindex="6" tagId="botao_pesquisar" type="button" onMouseDown="$('pagina_solicitada').value=1" onClick="$('novo_modelo').hide();$('resultado_pesquisa').innerHTML='';$('resultado_pesquisa').show();send(this, 'resultado_pesquisa');" styleClass="btNavegacao74" value="Pesquisar" title="${bundle.default['catalogo.ParamModelos.Pesquisar']}"/>
									</div>
								</netui:form>
							</div>
						</div>
						<div class="conteudo_box_bottom">
						</div>
					</div>
				</div>
			</div>
			<div id="resultado_pesquisa">
			</div>
			<div id="novo_modelo" class="secao_conteudo">
				
			</div>
			
