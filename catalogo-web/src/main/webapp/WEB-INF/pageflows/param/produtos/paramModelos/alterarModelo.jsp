<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="conteudo_box_top">
	<div class="conteudo_box_top_center">Cadastro de Modelo:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
		alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
</div>
<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg" >
				<netui:form action="alterarModelo">
					<div class="legendaObrigatorio">(*) Campo Obrigatório</div>
					<netui:hidden dataSource="actionForm.idModelo" dataInput="${modelo.idModelo}"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Tipo de Produto:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<netui:select tabindex="8" tagId="gravar_select_tipo_produto" style="width:140px;" styleClass="required editavel" repeater="true" dataSource="actionForm.idTipoProduto" defaultValue="${modelo.idTipoProduto}" optionsDataSource="${tipos_produto}"
							repeatingOrder="default, option" onChange="${(modelo.qtdProdutosAfetados eq 0)?'jsonListarFabricantes($(this).next().href, $F(this), &#39;novo_modelo&#39;);':''}return false;">
							<c:if test="${container.metadata.defaultStage}">
								<netui:selectOption repeatingType="default" value="">
		                        	-- Selecione --
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
						<div class="label-form-bold label_required" style="width:170px;">Fabricante:${modelo.idTipoProduto eq 7 || modelo.idTipoProduto eq 9 || modelo.idTipoProduto eq 10?'':'<font size="1px" color="#EEB422" valign="center">*</font>'}</div>
						<c:if test="${fabricantes == null}">
							<netui:select tabindex="9" tagId="select_fabricante_novo_modelo" dataSource="actionForm.idFabricante" styleClass="editavel" style="width: 140px;">
								<netui:selectOption value="">-- Selecione --</netui:selectOption>
							</netui:select>
						</c:if>
						<c:set var="fabricante_required" value="${(modelo.idTipoProduto eq 7 || modelo.idTipoProduto eq 9 || modelo.idTipoProduto eq 10)?'':'required'}"/>					
						<c:if test="${fabricantes != null}">
							<netui:select tabindex="9" style="width: 140px;" repeater="true" dataSource="actionForm.idFabricante" defaultValue="${modelo.idFabricante}" optionsDataSource="${fabricantes}"
								repeatingOrder="default, option" tagId="select_fabricante_novo_modelo" styleClass="${fabricante_required} editavel" disabled="${(modelo.qtdProdutosAfetados eq 0)?'false':'true'}">
								<c:if test="${container.metadata.defaultStage}">
									<netui:selectOption repeatingType="default" value="">
										-- Selecione --
									</netui:selectOption>
								</c:if>
								<c:if test="${container.metadata.optionStage}">
								<netui:selectOption repeatingType="option" value="${container.item.idFabricante}">
			                        ${container.item.nmFabricante}
			                    </netui:selectOption>
			                    </c:if>
							</netui:select>
						</c:if>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<c:set var="tecno_required" value="${(modelo.idTipoProduto eq 1 || modelo.idTipoProduto eq 6 || modelo.idTipoProduto eq 2 || modelo.idTipoProduto eq 11)?'required':''}"/>					
					<div id="div_select_tecnologias_frequencias" class="fleft" style="visibility:${modelo.idTipoProduto eq 9 ?'hidden':''};">
						<div class="label-form-bold label_required" style="width:170px;">Tecnologia/Frequência:${tecno_required=='required'?'<font size="1px" color="#EEB422" valign="center">*</font>':''}</div>
						<netui:textBox dataSource="actionForm.tecnologiasFrequencias" defaultValue="${tecnologiasSelecionadas}" tagId="hiddenTecnologiasFrequencias" size="200" styleClass="hide ${tecno_required} editavel" style="position:absolute;top:0px;"/>
						<input type="text"/><input tabindex="10" onclick="abrirPopup1($(this).next('a').href, [$('hiddenTecnologiasFrequencias'), $('gravar_select_tipo_produto')])" type="button" value="..." title="${bundle.default['catalogo.alterarModelo.tecnologiaFrequencia']}"/>
						<netui:anchor action="listarTecnologiasFrequencias" style="display:none;"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Nome Comercial:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<netui:textBox tabindex="11" styleClass="required editavel" dataSource="actionForm.nomeComercial" defaultValue="${modelo.nmModelo}" maxlength="100" onKeyPress="return semPontoVirgula(event);" style="width: 280px;"/>
					</div>
					<netui:textArea tagId="justificativa_alterar_modelo" styleClass="hide" dataSource="actionForm.justificativa"  />					
					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Dispon&iacute;vel:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<div style="width: 140px">
							<input type="hidden" id="inMultimidia" value="${modelo.inMultimidia}"/>
							<netui:radioButtonGroup dataSource="actionForm.disponivel" styleClass="required editavel" defaultValue="${modelo.inDisponivel}">
								<netui:radioButtonOption value="S" tabindex="12" styleClass="semBorda" tagId="disponivel_sim" onClick="if( $('inMultimidia').value=='S' ) { setRequired('hiddenCaracteristicasModelo'); } else { $('disponivel_nao').checked=true; posicionarDivErros('novo_modelo'); clearErrors(); addError('Multimídia e característica devem estar cadastradas.', true, true); }">
									<label class="label-form-bold2" for="disponivel_sim">Sim</label>
								</netui:radioButtonOption>
								<netui:radioButtonOption value="N" tabindex="13" styleClass="semBorda" tagId="disponivel_nao" onClick="setOptional('hiddenCaracteristicasModelo');">
									<label class="label-form-bold2" for="disponivel_nao">N&atilde;o</label>
								</netui:radioButtonOption>
							</netui:radioButtonGroup>
						</div>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Fim de Vida:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<div style="width: 140px">
							<netui:radioButtonGroup dataSource="actionForm.fimVida" styleClass="required editavel" defaultValue="${modelo.inFimVida}">
								<netui:radioButtonOption value="S" tabindex="12" styleClass="semBorda" tagId="fim_vida_sim">
									<label class="label-form-bold2" for="fim_vida_sim">Sim</label>
								</netui:radioButtonOption>
								<netui:radioButtonOption value="N" tabindex="13" styleClass="semBorda" tagId="fim_vida_nao">
									<label class="label-form-bold2" for="fim_vida_nao">N&atilde;o</label>
								</netui:radioButtonOption>
							</netui:radioButtonGroup>
						</div>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<c:set var="carac_required" value="${(modelo.inDisponivel eq 'N')?' ':' required '}"/>
					<div id="div_select_caracterisitcas" class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Caracter&iacute;stica:${carac_required==' required '?'<font size="1px" color="#EEB422" valign="center">*</font>':''}</div>
						<netui:textBox dataSource="actionForm.caracteristicas" tagId="hiddenCaracteristicasModelo" defaultValue="${caracteristicasModeloSelecionadas}" size="200" styleClass="hide${carac_required}editavel" style="position:absolute;top:0px;"/>
						<input type="text"/><input tabindex="10" onclick="abrirPopup1($(this).next('a').href)" type="button" value="..." title="${bundle.default['catalogo.ParamModelos.Caracteristica']}"/>
						<netui:anchor action="popupAlterarCaracteristica" style="display:none;">
							<netui:parameter name="id_modelo" value="${id_modelo}"/>
						</netui:anchor>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold" style="width:170px;">URL:</div>
						<netui:textBox tabindex="11" styleClass="editavel" dataSource="actionForm.url" defaultValue="${ (modelo.link eq null) ? '' : modelo.link.nmMultimidiaLink  }" maxlength="254" style="width: 280px;"/>
					</div>
					
					<br clear="all"/><br clear="all"/>
					
					<div class="fleft">
						<div class="label-form-bold" style="width:480px;">Destaque Comercial:</div>
						<netui:textArea dataSource="actionForm.dsNota" rows="5" style="width: 280px" defaultValue="${modelo.dsNota}"/>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<netui:button tabindex="14" type="button" onClick="clearEditando();$('novo_modelo').hide();if($('divErros')){ $('divErros').hide(); }" styleClass="btNavegacao74" value="Cancelar" title="${bundle.default['catalogo.alterarModelo.Cancelar']}"/>
						<span>&nbsp;</span>
						<netui:button tabindex="13" tagId="botao_alterar_modelo" type="button" onClick="clearEditando();send(this, null, null, 'novo_modelo');clearEditando();" styleClass="hide" value="Gravar" title="${bundle.default['catalogo.alterarModelo.Gravar']}"/>
						<netui:button tabindex="12" type="button" onClick="clearEditando();if(checkRequired($(this).form, 'novo_modelo')){return false};abrirPopup1($(this).next('a').href, null, 'novo_modelo');setEditando();return false;" styleClass="btNavegacao74" value="Gravar" title="${bundle.default['catalogo.alterarModelo.Gravar']}"/>
						<netui:anchor action="popupAlterarModelo" styleClass="hide">
							<netui:parameter name="id_modelo" value="${modelo.idModelo}"/>
						</netui:anchor>
						<span>&nbsp;</span>
						
						<netui:button tabindex="15" tagId="botao_imagem_modelo" type="button" onClick="clearEditando();abrirPopup1($(this).next('a').href, null, 'novo_modelo');clearEditando();" styleClass="btNavegacao74" value="Multimídia" title="${bundle.default['catalogo.alterarModelo.Multimidia']}"/>
						<netui:anchor action="popupImagemModelo" styleClass="hide">
							<netui:parameter name="id_modelo" value="${modelo.idModelo}"/>
						</netui:anchor>
						<!--
						<netui:button tabindex="15" tagId="botao_multimida_modelo" type="button" onClick="if(abrirLink('multimidia_modelo', $(this).next().href)){clearAndShow('multimidia_modelo');}return false;" styleClass="btNavegacao74" value="Multimidia" title="${bundle.default['catalogo.alterarModelo.Multimidia']}"/>
						<netui:anchor action="multimidiaModelo" styleClass="hide">
							<netui:parameter name="id_modelo" value="${modelo.idModelo}"/>
						</netui:anchor>
						-->
					</div>
				</netui:form>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
</div>
<div id="multimidia_modelo"></div>