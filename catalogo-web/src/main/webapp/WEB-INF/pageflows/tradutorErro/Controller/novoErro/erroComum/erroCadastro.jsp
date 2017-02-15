<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<div class="secao_conteudo" id="erroCadastro">
	<div id="div_erro_popup2" style="position: relative;"></div>
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Erro Comum</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div id="novoErro">
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/erroComum/gravarErro.do">
					<br clear="all"/>
					<c:choose>
						<c:when test="${erroComum.servico != null}">
							<c:choose>
								<c:when test="${erroComum.servicoNegocio != null}">
									<div class="fleft">
										<div class="label-form-bold" style="width:100px;">Erro Legado:<font size="1px" color="#EEB422" valign="center">*</font></div>
										<html:text property="erroComumVO.cdLegado" tabindex="8" styleClass="required" style="width:80px;" styleId="cd_erroLegado"/>
									</div>
									<div class="fleft">
										<div class="label-form-bold" style="width:100px;">Erro Padr&atilde;o:</div>
										<html:text property="erroComumVO.cdPadrao" tabindex="9" style="width:80px;" styleId="cd_erroPadrao" readonly="true"/>
									</div>
								</c:when>
								<c:otherwise>
									<div class="fleft">
										<div class="label-form-bold" style="width:100px;">Erro Legado:</div>
										<html:text property="erroComumVO.cdLegadoDef" tabindex="10" style="width:80px;" styleId="cd_erroLegadoDef" readonly="true" value="999"/>
									</div>
									<div class="fleft">
										<div class="label-form-bold" style="width:100px;">Erro Padr&atilde;o:</div>
										<html:text property="erroComumVO.cdPadraoDef" tabindex="11" style="width:80px;" styleId="cd_erroPadraoDef" readonly="true" value="${erroPadrao}"/>
									</div>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<div class="fleft">
								<div class="label-form-bold" style="width:100px;">Erro Legado:</div>
								<html:text property="erroComumVO.cdLegadoDis" tabindex="12" style="width:80px;" styleId="cd_erroLegadoDis" readonly="true"/>
							</div>
							<div class="fleft">
								<div class="label-form-bold" style="width:100px;">Erro Padr&atilde;o:</div>
								<html:text property="erroComumVO.cdPadraoDis" tabindex="13" style="width:80px;" styleId="cd_erroPadraoDis" readonly="true"/>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:120px;">Descri&ccedil;&atilde;o do Erro:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:text property="erroComumVO.mensagem" tabindex="11" styleClass="required" style="width:300px;" styleId="mensagem" />
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="botao">
						<html:button property="btn_ok" tabindex="12" styleId="bot_gravarErro" onMouseDown="$('pagina_solicitada').value='1'" onClick="if(send(this, null, null, 'div_erro_popup2'));return false" styleClass="btNavegacao74" value="Ok"/>
						<html:link styleId="bot_hidden" action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/erroComum/abrirPopup.do?tipoPopup=novo" onClick="abrirPopup1(this.href, null , 'novoErro');return false;">
						</html:link>
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
