<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupConfirmacao.jsp">
			
	<netui-temp:section name="conteudo">
	
	<c:if test="${modelos_afetados == null}">
		<img src="/catalogo/static_server/img/transparent.gif" onload="$('botao_sim').onclick();"/>
	</c:if>
		<c:if test="${modelos_afetados != null}">
		<div class="legendaObrigatorio">
			(*) Campo Obrigatório
		</div>
		A alteração afetará a associação com os Modelos:
		<br clear="all"/>
		<br/>
		<div class="box" style="width:70%;">
			<div class="box_top">
				<div class="box_top_center">
					MODELO (NÚMERO DE PRODUTOS ASSOCIADOS)
				</div>
				<div class="box_top_left">
				</div>
				<div class="box_top_right">
				</div>
			</div>
			<div class="box_middle">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="box_middle_left">
					</td>
					<td>
						<div class="box_middle_center">
							<div class="box_middle_center_conteudo vertical-scroll" style="height:200px;">
								<table class="tabela-padrao tablesorter" >
									<netui-data:repeater dataSource="modelos_afetados">
										<tr><td><label class="lblForm">${container.item.nmGrupoProduto} (${container.item.qtdProdutosAfetados})</label></td></tr>
									</netui-data:repeater>
								</table>
		
								<br/>
							</div>
						</div>
					</td>
					<td class="box_middle_right">
					</td>
				</tr>
				</table>
			</div>
			<div class="box_bottom">
				<div class="box_bottom_center">
				</div>
				<div class="box_bottom_left">
				</div>
				<div class="box_bottom_right">
				</div>
			</div>
		</div>
		<br clear="all"/>
		</c:if>
		<br clear="all"/>
		Confirme a alteração:
		<br clear="all"/>
		<form>
		<div style="width:70%;">
			<c:if test="${modelos_afetados != null}">
				<div align="left" class="label_required">Justificativa:<font size="1px" color="#EEB422" valign="center">*</font></div>
				<textarea class="required" id="justificativa_temp" rows="4" cols="45" style="width:100%;"></textarea>
			</c:if>
		</div>
		<c:if test="${modelos_afetados == null}">
			<textarea id="justificativa_temp" rows="4" cols="45" style="width:100%;" class="hide"></textarea>
		</c:if>
		<input type="button" id="botao_sim" onclick="if(checkRequired($(this).form, 'div_erros_popup_confirmacao')){return false;};$('justificativa_alterar_caracteristica').value=$F('justificativa_temp');fecharPopup('popup1');$('botao_gravar_caracteristica').onclick();" class="btNavegacao74" value="Sim" title="${bundle.default['catalogo.popupAlterarCaracteristica.Sim']}"/>
		&nbsp;
		<input type="button" onclick="fecharPopup('popup1');setEditando();" class="btNavegacao74" value="Não" title="${bundle.default['catalogo.popupAlterarCaracteristica.Nao']}"/>
		</form>
		<br/><br/>
	</netui-temp:section>

</netui-temp:template>