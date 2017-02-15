<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div id="div_erro_popup" style="position: relative;"></div>
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Servi&ccedil;o de Negocio</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/erroComum/gravarSN.do">
					<br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold" style="width:150px;">Servi&ccedil;o Negocio:</div>
						<html:select property="erroComumVO.servicoNegocio" tabindex="1" styleId="select_sn" style="width:300px;">
							<html:option value="">SEM SERVIÇO NEGÓCIO</html:option>
							<c:forEach var="listaNegocioTO" items="${listaNegocio}">
							<html:option value="${listaNegocioTO.cdServicoNegocio}">${listaNegocioTO.cdServicoNegocio} - ${listaNegocioTO.dsServicoNegocio}</html:option>
							</c:forEach>
						</html:select>
					</div>
					<br clear="all"/>
					<div class="botao">
						<html:button property="btn_ok" tabindex="2" styleId="bot_gravarSN" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('legado');clearAndShow('enablement');clearAndShow('descricao');send(this, 'legado', null , null)" styleClass="btNavegacao74" value="Ok"/>
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
<div id="legado" class="secao_conteudo" style="position: relative;"></div>
<div id="enablement" class="secao_conteudo" style="position: relative;"></div>
<div id="descricao" class="secao_conteudo" style="position: relative;"></div>