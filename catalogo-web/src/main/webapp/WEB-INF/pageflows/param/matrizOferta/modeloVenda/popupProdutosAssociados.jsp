<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
	<netui-temp:section name="conteudo">
	<div id="popup_produtos_associado">
		<input id="larguraPopup" value="780px" class="hide"/>
		<input id="alturaPopup" value="300px" class="hide"/>
		<c:set var="nomeBox" scope="application"  value="Produtos Associados ao: ${modeloVenda}"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div>
			<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header" >
				<thead>
					<tr>
						<th class="sortable">C&oacute;digo</th>
						<th class="sortable">Descri&ccedil;&atilde;o</th>
						<th class="sortable">Modelo</th>
					</tr>
				</thead>
			</table>
			
			<div style="height:300px;" class="both-scroll">
				<netui-data:repeater dataSource="produtos" defaultText="<br><span align='center'>Nunhum produto associado.</span>">
					<netui-data:repeaterHeader>
						<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
							<thead>
								<tr>
									<th class="sortable">C&oacute;digo</th>
									<th class="sortable">Descri&ccedil;&atilde;o</th>
									<th class="sortable">Modelo</th>
								</tr>
							</thead>
							<tbody>
					</netui-data:repeaterHeader>
								<netui-data:repeaterItem>
									<tr>
										<td style="text-align: left;">${container.item.cdCodigo}</td>
										<td style="text-align: left;">${container.item.dsProduto}</td>
										<td style="text-align: left;">${container.item.nmGrupoProduto}</td>
									</tr>
								</netui-data:repeaterItem>
								<netui-data:repeaterFooter>
							</tbody>
						</table>
								</netui-data:repeaterFooter>
				</netui-data:repeater>
			</div>
			<br>
			<div id="paginacao_" class="paginacao" style="width:99%;position: absolute;bottom: 5px;" align="right">
				<c:forEach begin="1" end="${totalPagina}" var="no_pagina">
					<c:choose>
						<c:when test="${no_pagina == paginaAtual}">
							<netui:anchor tagId="pagina_valor_caracteristica_selecionada" styleClass="selected" action="listarProdutosAssociado" onClick="abrirPopup1(this.href);return false;">
								<netui:parameter  name="id_modelo_venda" value="${idModeloVenda}"/>
								<netui:parameter  name="pagina_solicitada" value="${no_pagina}"/>
								${no_pagina}
							</netui:anchor>
						</c:when>
						<c:otherwise>
							<netui:anchor action="listarProdutosAssociado" onClick="abrirPopup1(this.href);return false;">
								<netui:parameter  name="id_modelo_venda" value="${idModeloVenda}"/>
								<netui:parameter  name="pagina_solicitada" value="${no_pagina}"/>
								${no_pagina}
							</netui:anchor>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
		<div style="text-align: left;" class="lblForm">Quantidade: ${totalRegistros}</div>
	</div>
	</netui-temp:section>
</netui-temp:template>