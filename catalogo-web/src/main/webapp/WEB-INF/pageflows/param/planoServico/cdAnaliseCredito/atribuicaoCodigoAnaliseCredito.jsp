<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="secao_conteudo" style="position:relative;">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">
			Atribui&ccedil;&atilde;o de C&oacute;digo An&aacute;lise Cr&eacute;dito
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
				
				
				
				<netui:form action="pesquisarCodigoAnaliseCredito">
					<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
					<div class="legendaObrigatorio help">(*) Campo Obrigatório</div>
					<div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161353" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 90px">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<netui:select tabindex="1" style="width:140px;" repeater="true" dataSource="actionForm.idPlataforma" styleClass="required" tagId="select_plataforma" defaultValue="-- Selecione --" optionsDataSource="${plataformas}"
								repeatingOrder="default, option">
							<c:if test="${container.metadata.defaultStage}">
								<netui:selectOption repeatingType="default" value="">
									${container.item}
								</netui:selectOption>
							</c:if>
							<c:if test="${container.metadata.optionStage}">
								<netui:selectOption repeatingType="option" value="${container.item.idPlataforma}">
			                        ${container.item.nmPlataforma}
			                    </netui:selectOption>
		                    </c:if>
						</netui:select>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold" style="width:120px;">Nome Comercial:</div>
						<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome Comercial".</div>
						<div class="hide min_size_required_value">3</div>
						<netui:textBox tabindex="2" tagId="text_nmComercial" size="28" dataSource="actionForm.nmComercial" styleClass="min_size_required" />
					</div>
					
					<div class="fleft">
						<div class="label-form-bold" style="width:110px;">Nome T&eacute;cnico:</div>
						<netui:textBox tabindex="3" tagId="text_nmTecnico" size="28" dataSource="actionForm.nmTecnico" />
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold" style="width: 90px">Tipo Pessoa:</div>
						<netui:select tabindex="4" tagId="select_tipoCliente" style="width: 140px;" repeater="true" dataSource="actionForm.idTipoCliente" defaultValue="-- Selecione --" optionsDataSource="${tipoCliente}" repeatingOrder="default, option" >
							<c:if test="${container.metadata.defaultStage}">
								<netui:selectOption repeatingType="default" value="">${container.item}</netui:selectOption>
							</c:if>
							<c:if test="${container.metadata.optionStage}">
								<netui:selectOption repeatingType="option" value="${container.item.idTipoCliente}">
	                       			<c:choose>
										<c:when test="${fn:toUpperCase(container.item.nmTipoCliente) eq 'PF' or fn:toUpperCase(container.item.nmTipoCliente) eq 'PESSOA FÍSICA' or fn:toUpperCase(container.item.nmTipoCliente) eq 'PESSOA FISICA'}">
											${'Pessoa F&iacute;sica'}
										</c:when>
										<c:otherwise>
											${'Pessoa Jur&iacute;dica'}
										</c:otherwise>
									</c:choose>
			                    </netui:selectOption>
	                    	</c:if>
						</netui:select>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold" style="width: 120px">Tecnologia:</div>
						<netui:select tabindex="5" tagId="select_tecnologia" style="width: 153px;" repeater="true" dataSource="actionForm.idTecnologia" defaultValue="-- Selecione --" optionsDataSource="${tecnologias}" repeatingOrder="default, option" >
							<c:if test="${container.metadata.defaultStage}">
								<netui:selectOption repeatingType="default" value="">
									${container.item}
								</netui:selectOption>
							</c:if>
							<c:if test="${container.metadata.optionStage}">
								<netui:selectOption repeatingType="option" value="${container.item.idTecnologia}">
			                        ${container.item.nmTecnologia}
			                    </netui:selectOption>
		                    </c:if>
						</netui:select>
					</div>

					<div class="fleft">
						<div class="label-form-bold" style="width: 110px">UF:</div>
						<netui:textBox dataSource="actionForm.idsUF" tagId="hidden_ufs" styleClass="hide"/>
						<input type="text" style="width: 136px" disabled="disabled" /><input tabindex="6" onclick="abrirPopup1($(this).next('a').href, [$('hidden_ufs')])" type="button" value="..." title="" /><%--${bundle.default['catalogo.paramPlanoServico.ufs']}--%>
						<netui:anchor action="listarUnidadesFederativa" style="display:none;"/>
					</div>
					
					<br clear="all" /><br clear="all" />
					<div class="fleft">
						<div class="label-form-bold" style="width: 90px">C&oacute;d. SERASA:</div>
							<netui:select tabindex="7" tagId="select_cod_serasa" style="width: 140px;" repeater="true" dataSource="actionForm.cdSerasa" defaultValue="-- Selecione --" optionsDataSource="${serasaLista}" repeatingOrder="default, option" >
								<c:if test="${container.metadata.defaultStage}">
									<netui:selectOption repeatingType="default" value="">
										${container.item}
									</netui:selectOption>
								</c:if>
								<c:if test="${container.metadata.optionStage}">
									<netui:selectOption repeatingType="option" value="${container.item.cdSerasa}">
				                        ${container.item.cdSerasa}
				                    </netui:selectOption>
			                    </c:if>
							</netui:select>
						</div>
					</div>		

					<div class="fleft">
						<div class="label-form-bold" style="width: 120px">Dispon&iacute;vel p/ Venda:</div>
							<netui:select tabindex="8" tagId="select_disp_venda" style="width: 153px;" dataSource="actionForm.idTecnologia" defaultValue="S">
								<netui:selectOption value="A">Ambos</netui:selectOption>
								<netui:selectOption value="N">N&atilde;o</netui:selectOption>
								<netui:selectOption value="S">Sim</netui:selectOption>
							</netui:select>
						</div>
						
						<div class="fleft">
							<div class="label-form-bold" style="width: 110px">Valor Assinatura:</div>
								<select tabindex="9" id="select_operadores" style="width: 34px; margin: 0px" name="${actionForm.idTecnologia}">
									<option value="ig" selected="selected">=</option>
									<option value="ma">&gt;</option>
									<option value="me">&lt;</option>
								</select><input type="text" tabindex="10" name="${actionForm.nmComercial}" size="20" style="margin: 0px; width: 121px; height: 18px" />
							</div>
						</div>	

<%-- 
						<div class="fleft">
							<div class="label-form-bold" style="width: 110px">Valor Assinatura:</div>
								<netui:select tabindex="9" tagId="select_operadores" style="width: 35px; margin: 0px" dataSource="actionForm.idTecnologia" defaultValue="1">
									<netui:selectOption value="1">=</netui:selectOption>
									<netui:selectOption value="2">></netui:selectOption>
									<netui:selectOption value="3"><</netui:selectOption>
								</netui:select><netui:textBox tabindex="10" dataSource="actionForm.nmComercial" size="20" style="margin: 0px; width: 130px" />
							</div>
						</div>	
--%>
							
							
							
							
							
							
										

					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<div class="botao">
						<div id="div_botoes" style="width: 25%; float: right">
							<div>
								<input type="button" tabindex="13" value="Limpar" onclick="clearAndShow('alterar_servicos');$('resultado_pesquisa').hide();limparForm(this)" class="btNavegacao74" title="${bundle.default['catalogo.global.Limpar']}" />
								<span>&nbsp;</span>
								<netui:button type="submit" tabindex="14" value="Pesquisar" tagId="botao_pesquisar_parametrizacao" onclick="clearAndShow('resultado_pesquisa');clearAndShow('alterar_servicos');send(this, 'resultado_pesquisa', null, 'right_section'); return false;" styleClass="btNavegacao74" title="${bundle.default['catalogo.paramPlanoServico.pesquisarPlanoServico']}" />
							</div>
						</div>
					</div>
				</netui:form>



			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
<br clear="all"/>
<div id="resultado_pesquisa" style="position:relative;"></div>
		