<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="conteudo_box_top">
	<div class="conteudo_box_top_center">Novo Modelo:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
		alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
</div>
<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg" >
				<netui:form action="criarModelo">
					<netui:hidden dataSource="actionForm.idModelo" dataInput="${id_modelo}"/>
					<netui:hidden dataSource="actionForm.tipoSubmit" tagId="tipoSubmit"/>
					<div class="legendaObrigatorio">(*) Campo Obrigatório</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Tipo de Produto:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<netui:hidden dataSource="actionForm.idTipoProduto" dataInput="${modelo.idTipoProduto}"/>
						<netui:select tabindex="8" disabled="true" tagId="gravar_select_tipo_produto" style="width: 140px;" styleClass="required editavel" repeater="true" dataSource="actionForm.idTipoProduto" defaultValue="${modelo.idTipoProduto}" optionsDataSource="${tipos_produto}"
							repeatingOrder="default, option" onChange="jsonListarFabricantes($(this).next().href, $F(this), 'novo_modelo');$('hiddenTecnologiasFrequencias').value='';return false;">
							<c:if test="${container.metadata.optionStage}">
							<netui:selectOption repeatingType="option" value="${container.item.idTipoProduto}">
		                        ${container.item.nmTipoProduto}
		                    </netui:selectOption>
		                    </c:if>
						</netui:select>
						<netui:anchor action="buscarFabricantes" styleClass="display:none;"/>
					</div>
					<c:set var="fabricante_required" value="${(modelo.idTipoProduto eq 7 || modelo.idTipoProduto eq 9 || modelo.idTipoProduto eq 10)?'':'required'}"/>	
					<div class="fleft" >
						<div class="label-form-bold label_required" style="width:170px;">Fabricante:${(modelo.idTipoProduto eq 7 || modelo.idTipoProduto eq 9 || modelo.idTipoProduto eq 10)?'':'<font size="1px" color="#EEB422" valign="center">*</font>'}</div>
						<c:if test="${fabricantes == null}">
							<netui:select tabindex="9" tagId="select_fabricante_novo_modelo" style="width: 140px;" dataSource="actionForm.idFabricante" styleClass="editavel" >
								<netui:selectOption value="">-- Selecione --</netui:selectOption>
							</netui:select>
						</c:if>
						<c:if test="${fabricantes != null}">
							<netui:select tabindex="9" style="width: 140px;" repeater="true" dataSource="actionForm.idFabricante" defaultValue="${modelo.idFabricante}" optionsDataSource="${fabricantes}"
								repeatingOrder="default, option" tagId="select_fabricante_novo_modelo" styleClass="${fabricante_required} editavel">
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
						<input type="text"/><input tabindex="10" onclick="abrirPopup1($(this).next('a').href, [$('hiddenTecnologiasFrequencias'), $('gravar_select_tipo_produto')])" type="button" value="..." title="${bundle.default['catalogo.copiarModelo.tecnologiaFrequencia']}"/>
						<netui:anchor action="listarTecnologiasFrequencias" style="display:none;"/>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Nome Comercial:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<netui:textBox tabindex="11" styleClass="required editavel" dataSource="actionForm.nomeComercial" maxlength="100" onKeyPress="return semPontoVirgula(event);" style="width: 280px;"/>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Dispon&iacute;vel:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<div style="width: 140px">
							<input type="hidden" id="inMultimidia" value="N"/>
							<netui:radioButtonGroup dataSource="actionForm.disponivel" styleClass="required editavel" defaultValue="N">
								<netui:radioButtonOption value="S" tabindex="12" styleClass="semBorda" tagId="disponivel_sim" onClick="if( $('inMultimidia').value=='S' ) { setRequired('hiddenCaracteristicasNovoModelo'); } else { $('disponivel_nao').checked=true; posicionarDivErros('novo_modelo'); clearErrors(); addError('Multimídia e característica devem estar cadastradas.', true, true); }">
									<label class="label-form-bold2" for="disponivel_sim">Sim</label>
								</netui:radioButtonOption>
								<netui:radioButtonOption value="N" tabindex="13" styleClass="semBorda" tagId="disponivel_nao" onClick="setOptional('hiddenCaracteristicasNovoModelo');">
									<label class="label-form-bold2" for="disponivel_nao">N&atilde;o</label>
								</netui:radioButtonOption>
							</netui:radioButtonGroup>
						</div>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Fim de Vida:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<div style="width: 140px">
							<netui:radioButtonGroup dataSource="actionForm.fimVida" styleClass="required editavel" defaultValue="N">
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
					<div id="div_select_caracterisitcas" class="fleft">
						<div class="label-form-bold label_required" style="width:170px;">Caracter&iacute;stica:</div>
						<netui:textBox dataSource="actionForm.caracteristicas" defaultValue="${caracteristicasModeloSelecionadas}" tagId="hiddenCaracteristicasNovoModelo" size="200" styleClass="hide editavel" style="position:absolute;top:0px;"/>
						<input type="text"/><input tabindex="10" onclick="$('tipoSubmit').value='CaracCopy';$('btnCopiarModelo').onclick();" type="button" value="..." title="${bundle.default['catalogo.ParamModelos.Caracteristica']}"/>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold" style="width:170px;">URL:</div>
						<netui:textBox tabindex="11" styleClass="editavel" dataSource="actionForm.url" maxlength="254" style="width: 280px;"/>
					</div>
					
					<br clear="all"/><br clear="all"/>
					
					<div class="fleft">
						<div class="label-form-bold" style="width:480px;">Destaque Comercial:</div>
						<netui:textArea dataSource="actionForm.dsNota" rows="5" style="width: 280px" />
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<netui:button tabindex="13" type="button" onClick="$('novo_modelo').hide();if($('divErros')){ $('divErros').hide(); }" styleClass="btNavegacao74 clearEditavel" value="Cancelar" title="${bundle.default['catalogo.copiarModelo.Cancelar']}"/>
						<span>&nbsp;</span>
						<netui:button tagId="btnCopiarModelo" tabindex="12" type="button" onMouseOver="$('tipoSubmit').value='btnCopy';" onMouseOut="$('tipoSubmit').value='';" onClick="clearEditando();send(this, null, null, 'novo_modelo');setEditando();" styleClass="btNavegacao74" value="Gravar" title="${bundle.default['catalogo.copiarModelo.Gravar']}"/>
						<span>&nbsp;</span>
						<netui:button tabindex="15" type="button" onClick="$('tipoSubmit').value='btnMult';$('btnCopiarModelo').onclick();" styleClass="btNavegacao74" value="Multimídia" title="${bundle.default['catalogo.alterarModelo.Multimidia']}"/>
					</div>
				</netui:form>
			</div>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
</div>