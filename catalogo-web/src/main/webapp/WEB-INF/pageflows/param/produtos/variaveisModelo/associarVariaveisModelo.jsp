<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Vari&aacute;veis do Modelo</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img id="img_MaxMin" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" onclick="showHideSecaoConteudo(this);" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
		
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<div class="legendaObrigatorio help">(*) Campo Obrigatório</div>
				<div class="link_manual" title="Dúvida">
					<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161318" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
				</div>
				<div>
					<netui:form action="pesquisarVariaveisDoModelo">
						<div id="div_tipo_pesquisa" style="position: relative; width: 100%">
							<div class="fleft">
								<div style="padding-left: 20px; width: 400px">
									<netui:radioButtonGroup dataSource="actionForm.tipoPesquisa" defaultValue="modelo" orientation="horizontal">
									    <netui:radioButtonOption value="modelo"  styleClass="semBorda" onClick="definirTipoPesquisaVariavelModelo($F(this))"><label style="padding-right: 50px; font-weight: bold;">Consultar por Modelo</label></netui:radioButtonOption>
									    <netui:radioButtonOption value="variavel"  styleClass="semBorda"  onClick="definirTipoPesquisaVariavelModelo($F(this))"><label style="font-weight: bold;">Consultar por Vari&aacute;veis</label></netui:radioButtonOption>
									</netui:radioButtonGroup>
								</div>
							</div>
							<br clear="all" /><br clear="all" />
							<div class="barra"></div>
						</div>
						<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada" />
						<br clear="all" />
						<div id="div_form_pesquisar_modelo" style="position: relative; display: block;">
							
							<div class="fleft">
								<div class="label-form-bold label_required" style="width:125px;">Tipo de Produto:<font size="1px" color="#EEB422" valign="center">*</font></div>
								<netui:select tabindex="1" styleClass="required" tagId="select_tipo_produto" style="width:143px;" repeater="true" dataSource="actionForm.idTipoProduto" defaultValue="-- Selecione --" optionsDataSource="${tipoProduto}" repeatingOrder="default, option" onChange="jsonListarFabricantes($(this).next('a').href, $F(this));return false;">
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
								<div class="label-form-bold label_required" style="width: 110px;">Fabricante:</div>
								<netui:select tabindex="2" tagId="select_fabricante" style="width: 140px;" dataSource="actionForm.idFabricante">
									<netui:selectOption  value="">-- Selecione --</netui:selectOption>
								</netui:select>
							</div>
							
							<div class="fleft">
								<div class="label-form-bold" style="width: 90px;">Modelo:</div>
								<netui:textBox tabindex="3" size="32" dataSource="actionForm.nmModelo" />
							</div>
						<%-- Não apagar esse codigo sem que a ERS seja atualizada pelo o pessoal da VIVO.
							<br clear="all" /><br clear="all" />
							<div class="fleft">
								<div class="label-form-bold" style="width:125px;">Tecnologia:</div>
								<netui:textBox dataSource="actionForm.idsTecnologia" tagId="hidden_tecnologias" styleClass="hide" />
								<netui:textBox dataSource="actionForm.nomesTecnologias" tagId="hidden_tecnologias_nomes" styleClass="hide" />
								<input size="22" type="text"/><input tabindex="4" onclick="abrirPopup1($(this).next().href, [$('hidden_tecnologias')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.Tecnologia']}" title="${bundle.default['catalogo.variavel.modelo.Tecnologia']}"/>
								<netui:anchor action="listarTecnologias" style="display:none;"/>
							</div>
						--%>	
						</div>
						
						<div id="div_form_pesquisar_variavel" style="position: relative; display: none;">
							<div class="fleft">
								<div class="label-form-bold label_required" style="width: 118px;">Tipo de Cliente:</div>
								<netui:textBox dataSource="actionForm.idsTipoCliente" tagId="hidden_lista_tipo_cliente" styleClass="hide"/>
								<input type="text" id="textfield_tipo_cliente" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_tipo_cliente')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.TipoCliente']}" title="${bundle.default['catalogo.variavel.modelo.TipoCliente']}" />
								<netui:anchor action="buscarListaTipoCliente" styleClass="hide" />
							</div>
							
							<div class="fleft">
								<div class="label-form-bold label_required" style="width: 100px;">Carteira:</div>
								<netui:textBox dataSource="actionForm.sgCarteira" tagId="hidden_lista_carteira" styleClass="hide"/>
								<input type="text" id="textfield_carteira" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_carteira')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.Carteira']}" title="${bundle.default['catalogo.variavel.modelo.Carteira']}" />
								<netui:anchor action="buscarListaCarteira" styleClass="hide" />
							</div>
	
							<div class="fleft">
								<div class="label-form-bold label_required" style="width: 100px;">Segmento:</div>
								<netui:textBox dataSource="actionForm.sgSegmento" tagId="hidden_lista_segmento" styleClass="hide"/>
								<input type="text" id="textfield_segmento" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_segmento')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.Segmento']}" title="${bundle.default['catalogo.variavel.modelo.Segmento']}" />
								<netui:anchor action="buscarListaSegmento" styleClass="hide" />
							</div>
							
							<br clear="all" /><br clear="all" />
							<div class="fleft">
								<div class="label-form-bold label_required" style="width: 118px;">Canal:</div>
								<netui:textBox dataSource="actionForm.idsCanal" tagId="hidden_lista_canal" styleClass="hide"/>
								<input type="text" id="textfield_canal" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_canal')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.Canal']}" title="${bundle.default['catalogo.variavel.modelo.Canal']}" />
								<netui:anchor action="buscarListaCanal" styleClass="hide" />
							</div>
							
							<div class="fleft">
								<div class="label-form-bold label_required" style="width: 100px;">UF:</div>
								<netui:textBox dataSource="actionForm.idsUf" tagId="hidden_lista_uf" styleClass="hide"/>
								<input type="text" id="textfield_uf" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_uf')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.UF']}" title="${bundle.default['catalogo.variavel.modelo.UF']}" />
								<netui:anchor action="buscarListaUf" styleClass="hide" />
							</div>
						</div>
						
						<br clear="all" /><br clear="all" />
						<div class="barra"></div>
						<div class="botao">
							<netui:button tabindex="6" type="button" onClick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" styleClass="btNavegacao74" value="Limpar" alt="${bundle.default['catalogo.global.Limpar']}" title="${bundle.default['catalogo.global.Limpar']}"/>
						    <span>&nbsp;</span>
						    <netui:button tabindex="5" tagId="botao_pesquisar_variaveis_modelo" type="button" onMouseDown="$('pagina_solicitada').value='1';" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section');" styleClass="btNavegacao74" value="Pesquisar" alt="${bundle.default['catalogo.variavel.modelo.Pesquisar']}" title="${bundle.default['catalogo.variavel.modelo.Pesquisar']}"/>
						</div>
					</netui:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div
	</div>
</div>
<br/>
<div id="resultado_pesquisa" style="display: none; position: relative;"></div>
		