<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupConfirmacao.jsp">
			
	<netui-temp:section name="conteudo">
		<br/>
		<c:if test="${modelos_afetados != null}">
		<div class="legendaObrigatorio">
			(*) Campo Obrigatório
		</div>
		A Exclusão afetará a associação com os Modelos:
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
							<div class="box_middle_center_conteudo">
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
		</c:if>
		<br clear="all"/>
		Confirme a exclusão:
		<br clear="all"/>
		<netui:form action="apagarImagem">
		<div style="width:70%;">
			<c:if test="${modelos_afetados != null}">
				<div align="left" class="label_required">Justificativa:<font size="1px" color="#EEB422" valign="center">*</font></div>
				<netui:textArea styleClass="required" dataSource="actionForm.justificativa" rows="4" cols="45" style="width:100%;"/>
			</c:if>
			<netui:hidden dataSource="actionForm.idEntidade" dataInput="${id_multimidia}"/>
			<netui:hidden dataSource="actionForm.idModelo" dataInput="${id_modelo}"/>
			<netui:hidden dataSource="actionForm.idCorPadrao" dataInput="${idCorPadrao}"/>
		</div>
		<br clear="all"/>
		<input type="button" onclick="send(this, null, 'popup2', 'div_erros_popup');" class="btNavegacao74" value="Sim" title="${bundle.default['catalogo.popupApagarImagem.Sim']}"/>
		</netui:form>
		&nbsp;
		<input type="button" onclick="fecharPopup('popup2');" class="btNavegacao74" value="Não" title="${bundle.default['catalogo.popupApagarImagem.Não']}"/>
		<br/><br/>
	</netui-temp:section>

</netui-temp:template>