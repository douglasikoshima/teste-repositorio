<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Sistema Legado</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/erroComum/gravarSL.do">
					<br clear="all"/>
					<c:choose>
						<c:when test="${erroComum.servicoNegocio != null}">
							<div class="fleft">
								<div class="label-form-bold" style="width:150px;">Sistema Legado:</div>
								<html:select property="erroComumVO.sistemaLegado" tabindex="3" styleId="select_sl" style="width:300px;">
									<html:option value="">SEM LEGADO</html:option>
									<c:forEach var="listaLegadoTO" items="${listaLegado}">
									<html:option value="${listaLegadoTO.cdSistemaLegado}">${listaLegadoTO.cdSistemaLegado} - ${listaLegadoTO.dsSistemaLegado}</html:option>
									</c:forEach>
								</html:select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="fleft">
								<div class="label-form-bold label_required" style="width:150px;">Sistema Legado:<font size="1px" color="#EEB422" valign="center">*</font></div>
								<html:select property="erroComumVO.sistemaLegado" tabindex="4" styleClass="required" styleId="select_sl" style="width:300px;">
									<html:option value="">-- Selecione --</html:option>
									<c:forEach var="listaLegadoTO" items="${listaLegado}">
									<html:option value="${listaLegadoTO.cdSistemaLegado}">${listaLegadoTO.cdSistemaLegado} - ${listaLegadoTO.dsSistemaLegado} </html:option>
									</c:forEach>
								</html:select>
							</div>
						</c:otherwise>
					</c:choose>
					<br clear="all"/>
					<div class="botao">
						<html:button property="btn_ok" tabindex="5" styleId="bot_gravarSL" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('enablement');clearAndShow('descricao');send(this, 'enablement', null , null);" styleClass="btNavegacao74" value="Ok"/>
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>