<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div class="secao_conteudo" style="position:relative;">
	<div class="conteudo_box_top">
	    <div class="conteudo_box_top_center">
			Parametriza&ccedil;&atilde;o de Planos e Servi&ccedil;os:
		</div>
		<div class="conteudo_box_top_esq">
		</div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div class="conteudo_box_middle">
		<div class="conteudo_box_middle_mg">
			<div id="pesquisa_simplificada" class="relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/pesquisarServicos.do">
					<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/> 
					<div class="legendaObrigatorio help">(*) Campo Obrigatório</div>
					<div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161353" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
					<div>
						<div class="fleft" style="width: 99%">
							<div class="label-form-bold" style="width: 25%;">Tipo de Pesquisa:</div>
							<div class="label-form-bold"  style="width: 70%; text-align: left; padding: 0px 0px 0px 20px;">
							
							<html:link styleId="url_servicos" action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/pesquisarServicos.do" styleClass="display:none;"/>							
							<html:radio property="tpPesquisa" styleId="radio_servico" value="Por Servi&ccedil;o" tabindex="1" styleClass="semBorda"
							onclick="definirTipoPesquisa($('url_servicos').href, this.id, 'textfield_regional', 'textfield_categoria', 'checkbox_titular', 'link_servicos', 'link_planos', $(this).form);$('resultado_pesquisa').hide();clearAndShow('alterar_servicos')">							
								Por Serviço
							</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
							
							<html:link styleId="url_planos" action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/pesquisarPlanos.do" styleClass="display:none;"/>
							<html:radio property="tpPesquisa" value="Por Plano" tabindex="2" styleClass="semBorda" styleId="radio_plano"
							onclick="definirTipoPesquisa($('url_planos').href, this.id, 'textfield_regional', 'textfield_categoria', 'checkbox_titular', 'link_servicos', 'link_planos', $(this).form);$('resultado_pesquisa').hide();clearAndShow('alterar_servicos')">							
								Por Plano
							</html:radio>
							</div>
						</div>
					</div>
										
					<br clear="all" />
					<div class="barra"></div>
					<br clear="all" />
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 185px">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
						
						<html:select property="idPlataforma" styleId="select_plataforma" tabindex="3" style="width:140px;" styleClass="required" 		 		
				 			onChange="jsonListarCategorias($('link_listarGrupoServicos').href, $F(this));return false;">
						   <option value="">-- Selecione --</option>
						    <c:forEach items="${plataformas}" var="plataformaListTO">				       
						       <option value="${plataformaListTO.idPlataforma}">${plataformaListTO.nmPlataforma}</option>				       
						   </c:forEach>				   			   				   
					 	</html:select> 							 						 	
					 	<html:link styleId="link_listarGrupoServicos" action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/listarGrupoServicos.do" styleClass="display:none;"/> 																																				
					</div>

					<br clear="all"/><br clear="all"/>
					<div id="textfield_categoria" class="fleft" style="display: block">
						<div class="label-form-bold" style="width: 185px">Grupo de Serviços no Catálogo:</div>
						<html:select property="idCategoria" tabindex="4" style="width:140px;" styleId="select_categoria">
						   <option value="">-- Selecione --</option>				    				   			   				   
					 	</html:select> 
						<cata:temPermissao acao="incluirCategoriaPlanosServicos">						
						<html:link styleId="pesquisar_categorias" action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/pesquisarCategorias.do?id_sistema=${idSistema}" onclick="if($F('select_plataforma') != ''){abrirPopup1(this.href, [$('select_plataforma')], 'resultado_pesquisa');}else{addError('Por favor informar o campo Plataforma', true, true);}" title="Valores da Característica">
							<html:button property="bt_novo" tabindex="5" value="Novo Grupo Serviços" onclick="$('resultado_pesquisa').hide();return false;" styleClass="btNavegacao120" bundle="messages" titleKey="catalogo.paramPlanoServico.novaCategoria" />							
						</html:link>					
						</cata:temPermissao>
					</div>
					<div id="textfield_regional" class="fleft" style="display: none">
						<div class="label-form-bold" style="width: 185px">Regional:</div>										
						<html:text property="regionais" styleId="hidden_regionais" styleClass="hide"></html:text>
						<input type="text" style="width: 121px" /><html:button property="bt_regional" tabindex="6" onclick="abrirPopup1($(this).next('a').href, [$('hidden_regionais')]); $('hidden_regionais').value='';" value="..."  bundle="messages" titleKey="catalogo.paramPlanoServico.regional" />						
						<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/listarRegionais.do" styleClass="display:none;"/>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo Nome Comercial.</div>
						<div class="hide min_size_required_value">3</div>
						<div class="label-form-bold" style="width: 185px">Nome Comercial:</div>
						<html:text property="nmServico" styleClass="min_size_required" maxlength="50" tabindex="7" size="50"></html:text>						
					</div>
			
					<br clear="all"/>
					<br clear="all"/>
					<div class="fleft">
						<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo Nome T&eacute;cnico.</div>
						<div class="hide min_size_required_value">3</div>
						<div class="label-form-bold" style="width: 185px">Nome T&eacute;cnico:</div>
						<html:text property="cdServico" styleClass="min_size_required" maxlength="25" tabindex="8" size="50" />						
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 185px">Disponibilidade para venda:<font size="1px" color="#EEB422" valign="center">*</font></div>
						
						<html:select property="disponibilidade" tabindex="9" styleClass="required" style="width:140px;" styleId="select_disponibilidade">
						   <option value="S">Sim</option>
						   <option value="N">Não</option>
						   <option value="A">Ambos</option>				    				   			   				   
					 	</html:select> 												
					</div>
					<div id="checkbox_titular" class="fleft" style="display: none">
						<div class="label-form-bold" style="width: 105px">Titular:</div> 
						<html:checkbox property="indTitular" styleId="indTitular" tabindex="10" styleClass="semBorda belongsToForm checkbox_modelo" value="true"/>						
						<html:hidden property="indTitular" value="false"/>
					</div>

					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<div class="botao">
						<c:choose>
							<c:when test="${servicoList > 0 or planoList > 0}">
								<div id="div_links" style="width: 74.8%; float: left">
									<div id="link_servicos" style="display: block; float: left; width: 99.5%; font-weight: bold; padding: 0px 0px 0px 20px">
										<c:if test="${servicoList > 0}">
										<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/popupVisualizaServicos.do" tabindex="11" onclick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;">
												Existem Serviços que sofreram altera&ccedil;&otilde;es de par&acirc;metros. Clique aqui para visualiz&aacute;-los
										</html:link>
										</c:if>
									</div>									
									<div id="link_planos" style="display: none; float: left; width: 99.5%; font-weight: bold; padding: 0px 0px 0px 20px">
										<c:if test="${planoList > 0}">
										<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/popupVisualizaPlanos.do" tabindex="12" onclick="abrirPopup1(this.href, null, 'resultado_pesquisa');return false;">
												Existem Planos que sofreram altera&ccedil;&otilde;es de par&acirc;metros. Clique aqui para visualiz&aacute;-los
										</html:link>
										</c:if>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div id="link_planos" style="display: none; padding: 0px; margin: 0px"></div>
								<div id="link_servicos" style="display: none; padding: 0px; margin: 0px"></div>
							</c:otherwise>
						</c:choose>
						<div id="div_botoes" style="width: 25%; float: right">
						    <div>
						    	 <html:button property="btn_limpar" tabindex="13" value="Limpar" onclick="clearAndShow('alterar_servicos');$('resultado_pesquisa').hide();limparForm(this)" styleClass="btNavegacao74" bundle="messages" titleKey="catalogo.global.Limpar"/>						    
								 <span>&nbsp;</span>
								 <html:button property="btn_pesquisar" tabindex="14" value="Pesquisar" styleId="botao_pesquisar_parametrizacao" onclick="clearAndShow('resultado_pesquisa');clearAndShow('alterar_servicos');send(this, 'resultado_pesquisa', null, 'right_section'); return false; " styleClass="btNavegacao74" bundle="messages" titleKey="catalogo.paramPlanoServico.pesquisarPlanoServico"/>	
	  						</div>																						
						</div>
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
<br clear="all"/>
<div id="resultado_pesquisa" style="position:relative;"></div>
<div id="alterar_servicos" style="position:relative;"></div>
<div id="div_associar_nova_categoria" style="position: relative; display: none"></div>
