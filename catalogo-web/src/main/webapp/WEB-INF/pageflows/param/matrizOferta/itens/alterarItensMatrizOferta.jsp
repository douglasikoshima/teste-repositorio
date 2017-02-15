<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_erro_alteracao_itens"></div>
<br clear="all">
<div id="alterar_item_matriz_oferta" style="position: relative;">
	<div class="secao_conteudo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Alterar Itens Matriz Oferta:</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg">
					
					<div class="legendaObrigatorio">(*) Campo Obrigat&oacute;rio</div>
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/alterarItensMatrizOferta.do">
												
						<div class="fleft">
							<label class="label-form-bold" style="width: 140px">Produto:</label> 
							<html:text property="trash" value="${cdCodigo}" readonly="true" styleClass="readonly" style="width: 120px" />&nbsp;
							<html:text property="trash" value="${dsProduto}" readonly="true"	styleClass="readonly" style="width: 505px"/>
						</div> 
						
						<br clear="all" />
						<br clear="all" />
						
						<div class="fleft">
							<label class="label-form-bold" style="width: 140px">Oferta SAP:</label>
							<html:text property="trash" value="${cdOfertaSAP}" readonly="true" styleClass="readonly" style="width: 120px" />&nbsp;
							<html:text property="trash" value="${dscOfertaSAP}"  readonly="true" styleClass="readonly" style="width: 267px" />
						</div>
						<div class="fleft">
							<label class="label-form-bold" style="width: 100px">Utiliza&ccedil;&atilde;o:</label> 
							<html:text property="trash" value="${sgUtilizacao}" readonly="true" styleClass="readOnly" style="width: 135px" />
						</div>
						
						<br clear="all" />
						<br clear="all" />
						
						<div class="fleft">
							<label class="label-form-bold" style="width: 140px">Organiza&ccedil;&atilde;o de Vendas:</label> 
							<html:text property="sgOrganizacaoVendas" value="${sgOrganizacaoVendas}" readonly="true" styleClass="readonly" style="width: 180px" />
						</div>
						<div class="fleft">
							<label class="label-form-bold" style="width: 76px">DDD:</label> 
							<html:text property="codigoArea" value="${codigoArea}" readonly="true" styleClass="readonly" style="width: 135px" />
						</div>
						<div class="fleft">
							<label class="label-form-bold" style="width: 100px">Pt. Vendas:</label> 
							<html:text property="canalVendas" value="${canalVendas}" readonly="true" styleClass="readonly" style="width: 135px"  />
						</div>

						<br clear="all" />
						<br clear="all" />						
						
						<div class="fleft">
							<label class="label-form-bold" style="width: 140px">Escritório de Vendas:</label> 
							<html:text property="trash" value="${nmEscritorioVenda}" readonly="true" styleClass="readonly" style="width: 180px" />
						</div>
						
						<div class="fleft">
							<label class="label-form-bold" style="width: 76px">A&ccedil;&atilde;o:</label> 
							<html:text property="trash" value="${nmAcao}" readonly="true" styleClass="readonly" style="width: 135px" />
						</div>
						
						<br clear="all" />
						<br clear="all" />
						
					<div style="width: 99%; text-align: center">
						<fieldset style="width: 95%; text-align: center; border: 1px solid #7D7D7D; padding: 0px 10px 15px 10px">
							<legend class="fieldset_legend">Pre&ccedil;o</legend>
							<div>
								<label class="label-form-bold2">Selecione a forma que deseja precificar o produto (À vista/Parcelado)
							</div>
		
							<!-- <netui:radioButtonGroup dataSource="actionForm.precificacao" defaultValue="Parcelado" > --> 
							
								<table>
									<tr>
										<td></td>
										<td></td>
										<td><label class="lblForm">Pre&ccedil;o Base</label></td>
										<td><label class="lblForm">Pre&ccedil;o na Oferta</label></td>
										<td><label class="lblForm">Valor Chip</label></td>
										<td><label class="lblForm">Total</label></td>
									</tr>
									<tr>
										<td>
											<!-- <netui:radioButtonOption value="Parcelado" styleClass="semBorda" tagId="id_parcelado"  onClick="habilitaDesabilitaPreco(this);"/> -->
											<html:radio property="precificacao" value="Parcelado" styleClass="semBorda" styleId="id_parcelado" onClick="habilitaDesabilitaPreco(this);"/>
										</td>
										<td>
											<label class="lblForm">Parcelado</label>
										</td>
										<td>	
											<fmt:formatNumber value="${valorBase}" type="currency" var="valor_Base"/>
											<html:text property="valorBase" value="${valor_Base}" readonly="true" styleClass="readonly"/>
										</td> 
										<td>
											<div class="label_required" style="display: none">pre&ccedil;o parcelado.</div>
											<html:text property="valorItemNovo" value="${valorItem}" styleClass="required" styleId="id_valor_item" onKeyPress="return formatar_moeda_negativa(this, '', ',', event);" onMouseDown="this.value=''" />
										</td>
										<td>
											<fmt:formatNumber  value="${valorChip}" type="currency" var="valor_Chip" />
											<html:text property="trash" value="${valor_Chip}"  readonly="true" styleClass="readonly"/>
										</td>
										<td>
											<fmt:formatNumber value="${valorTotal}" type="currency" var="valor_Total" />
											<html:text property="trash" value="${valor_Total}" readonly="true" styleClass="readonly"/>

										</td>
									</tr>
									<tr>
										<td> 				
											<!-- <netui:radioButtonOption value="À vista" styleClass="semBorda" tagId="id_vista" onClick="habilitaDesabilitaPreco(this);" /> -->
											<html:radio property="precificacao" value="À vista" styleClass="semBorda" styleId="id_vista" onClick="habilitaDesabilitaPreco(this);" />											
										</td>
										<td>
											<label class="lblForm">À vista</label>
										</td>
										<td>
											<fmt:formatNumber value="${valorBaseAVista}" type="currency" var="valor_Base_A_Vista" />
											<html:text property="valorBaseAVista" value="${valor_Base_A_Vista}" readonly="true" styleClass="readonly"/>
										</td> 
										<td>
										    <div class="label_required" style="display: none">preço à vista.</div>
											<html:text property="valorItemAVistaNovo" value="${valorItemAVista}" disabled="true" styleClass="readonly" styleId="id_valor_item_vista" onKeyPress="return formatar_moeda_negativa(this, '', ',', event);" onMouseDown="this.value=''" />
										</td>
										<td>
											<fmt:formatNumber value="${valorChipAVista}" type="currency" var="valor_Chip_A_Vista"  />
											<html:text property="trash" value="${valor_Chip_A_Vista}" readonly="true" styleClass="readonly"/>
										</td>
										<td>
											<fmt:formatNumber value="${valorTotalAVista}" type="currency" var="valor_Total_A_Vista"/>
											<html:text property="trash" value="${valor_Total_A_Vista}" readonly="true" styleClass="readonly"/>
										</td>
									</tr>
								</table>
							<!-- </netui:radioButtonGroup> -->
							
							<br clear="all" />
							<br clear="all" />
							<div class="fleft">
								<div class="label-form-bold label_required" style="width: 189px;">Vig&ecirc;ncia Inicial</div>
								<c:set var="data_Inicial" value="${dtInicial}" />
								<html:text styleId="tbDtInicial" property="dtInicioNova" value="${data_Inicial}" styleClass="required" onKeyPress="return isFormatDate(event, this, '/')" maxlength="10" />
							</div>
							<div class="fleft">
								<div class="label-form-bold label_required" style="width: 220px;">Vig&ecirc;ncia Final</div>
								<c:set var="data_Final" value="${dtFinal}"/>
								<html:text property="dtFimNova" value="${data_Final}" styleClass="required" onKeyPress="return isFormatDate(event, this, '/')" maxlength="10" />
							</div>
							
							<html:hidden property="idMatrizOfertaItemPreco" value="${idMatrizOfertaItemPreco}"/>
							<html:hidden property="dtInicioAtual" value="${data_Inicial}"/>
							<html:hidden property="dtFimAtual" value="${data_Final}"/>							
							<html:hidden property="valorItemAtual" value="${valorItem}" />
							<html:hidden property="valorItemAvistaAtual" value="${valorItemAVista}" />
							<html:hidden property="idCanalDistricuicao" value="${idCanalDistribuicao}" />
							<html:hidden property="idAcao" value="${idAcao}" />
							<html:hidden property="idEscritorioVenda" value="${idEscritorioVenda}" />
							
							</fieldset>
						</div>
							<br clear="all"/><br clear="all"/>
							<div class="barra"></div>
							<div class="botao">
								<html:button bundle="messages" property="btn_cancelar" value="Cancelar" styleClass="btNavegacao74" onclick="limparForm(this);$('alterar_item_matriz_oferta').hide();return false;" titleKey="catalogo.global.form.Limpar" />	
									<span>&nbsp;</span>
								<html:button bundle="messages" styleId="btnGravar" property="btnGravar" value="Gravar" styleClass="btNavegacao74" onclick="if (alteraDataInicioVigencia('tbDtInicial')){ send(this, 'div_alterar_itens_matriz', null, 'div_erro_alteracao_itens'); }" titleKey="catalogo.global.form.Alterar"/>
							</div>
					</html:form>
				</div>
			</div>
		</div>
	<div class="conteudo_box_bottom"></div><br clear="all"/>
</div>