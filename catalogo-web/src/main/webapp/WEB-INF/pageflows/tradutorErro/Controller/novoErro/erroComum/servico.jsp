<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Servi&ccedil;o (Enablement)</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/erroComum/gravarSE.do">
					<br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:150px;">Servi&ccedil;o Enablement:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select property="erroComumVO.servico" tabindex="6" styleClass="required" styleId="select_se" style="width:300px;">
							<html:option value="">SEM SERVIÇO</html:option>
							<c:forEach var="listaServicoTO" items="${listaServico}">
							<html:option value="${listaServicoTO.cdServico}">${listaServicoTO.cdServico} - ${listaServicoTO.dsServico}</html:option>
							</c:forEach>
						</html:select>
					</div>
					<br clear="all"/>
					<div class="botao">
						<html:button property="btn_ok" tabindex="7" styleId="bot_gravarSE" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('descricao');send(this, 'descricao', null , null);" styleClass="btNavegacao74" value="Ok"/>
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>