<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
	<netui-temp:section name="conteudo">
	<div id="popup_valores_caracteristica" style="position:relative;">
	<c:set var="nomeBox" scope="application" value="Valores Característica"/>
	<jsp:include page="/templates/box_pre.jsp"/>
		<div class="legendaObrigatorio">
			(*) Campo Obrigatório
		</div>
		<table>
			<tr>
				<td style="text-align: left;">
					<div id="adicionarValor">
						<cata:temPermissao acao="adicionarValor">
							<netui:form action="addValorCaracteristica">
								<div class="label-form-bold2 fleft label_required">Valor:<font size="1px" color="#EEB422" valign="center">*</font></div>
								<netui:textBox dataSource="actionForm.valorCaracteristica" styleClass="required editavel" onKeyPress="return semPontoVirgula(event);" maxlength="200"/>
								<netui:hidden dataSource="actionForm.idCaracteristica" dataInput="${id_caracteristica}"/>
								<input type="button" value="Incluir" class="btNavegacao74" id ="btIncluir" onclick="clearEditando();send(this, null, null, 'div_erros_popup');setEditando();" title="${bundle.default['catalogo.popupValoresCaracteristica.Incluir']}"/>
							</netui:form>
						</cata:temPermissao>
					</div>
					<div id="alterarValorCaracteristica">
						
					</div>
				</td>
			</tr>
		</table>
		<table  cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" id="TabelaRelacionamentosRecentes" width="90%">
					<thead>
						<tr>
							<th width="80%">Valor</th>
							<cata:temPermissao acao="alterarValorCaracteristica">
								<th width="1px">Alterar</th>
							</cata:temPermissao>
							<cata:temPermissao acao="excluirValor">
								<th width="1px">Excluir</th>
							</cata:temPermissao>
						</tr>
					</thead>
					<tbody height="100%">
				</tbody>
				</table>
				<div class="vertical-scroll" style="height:200px;">
			<netui-data:repeater dataSource="valoresCaracteristica">
				<netui-data:repeaterHeader>
					<table  cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaRelacionamentosRecentes" width="90%">
					<thead>
						<tr>
							<th width="80%">Valor02</th>
							<cata:temPermissao acao="alterarValorCaracteristica">
								<th width="1px">Alterar</th>
							</cata:temPermissao>
							<cata:temPermissao acao="excluirValor">
								<th width="1px">Excluir</th>
							</cata:temPermissao>
						</tr>
					</thead>
					<tbody height="100%">
				</netui-data:repeaterHeader>
				<netui-data:repeaterItem>
					<tr>
						<td style="text-align: left;" class="word-wrap"><label class="lblForm">${container.item.valor}</label></td>
						<cata:temPermissao acao="alterarValorCaracteristica">
							<td class="center">
								
								<netui:anchor action="abrirAlterarValorCaracteristica" onClick="if(abrirLink('alterarValorCaracteristica',this.href, 'popup_valores_caracteristica')){$('adicionarValor').hide();}return false;">
									<netui:parameter name="id_caracteristica" value="${id_caracteristica}"/>
									<netui:parameter name="id_valor_caracteristica" value="${container.item.idValorCaracteristica}"/>
									<img src= "/catalogo/static_server/img/botoes/bt-alterar.gif"/>
								</netui:anchor>
							</td>
						</cata:temPermissao>
						<cata:temPermissao acao="excluirValor">
							<td class="center">
								<netui:anchor action="abrirPopupApagarValorCaracteristica" onClick="abrirPopup2(this.href, null, 'popup_valores_caracteristica');return false;">
									<netui:parameter name="id_caracteristica" value="${id_caracteristica}"/>
									<netui:parameter name="id_valor_caracteristica" value="${container.item.idValorCaracteristica}"/>
									<img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" onClick=""/>
								</netui:anchor>
							</td>
						</cata:temPermissao>
					</tr>
				</netui-data:repeaterItem>
				<netui-data:repeaterFooter>
					</tbody>
				</table>
				</netui-data:repeaterFooter>
			</netui-data:repeater>
			</div>
			<br>
				<div id="paginacao_" class="paginacao" style="width:99%;position: absolute;bottom: 10px;" align="right">
					<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
						<c:choose>
							<c:when test="${no_pagina == paginaAtual}">
								<netui:anchor tagId="pagina_valor_caracteristica_selecionada" styleClass="selected" action="ValoresCaracteristica" onClick="abrirPopup1(this.href);return false;">
									<netui:parameter  name="id_caracteristica" value="${id_caracteristica}"/>
									<netui:parameter  name="pagina_solicitada" value="${no_pagina}"/>
									${no_pagina}
								</netui:anchor>
							</c:when>
							<c:otherwise>
								<netui:anchor action="ValoresCaracteristica" onClick="abrirPopup1(this.href);return false;">
									<netui:parameter  name="id_caracteristica" value="${id_caracteristica}"/>
									<netui:parameter  name="pagina_solicitada" value="${no_pagina}"/>
									${no_pagina}
								</netui:anchor>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
		<jsp:include page="/templates/box_pos.jsp"/>
		<div style="text-align: left;" class="lblForm">Quantidade de Valores: ${size_valoresCaracteristica}</div>

	</div>
	</netui-temp:section>
</netui-temp:template>
 