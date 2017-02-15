<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Buscar Planos</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<table border="0" cellpadding="0" cellspacing="0" style="width: 99%"><tr><td>
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/pesquisarListaPlanos.do">
				    <html:hidden property="paginaSolicitada" styleId="paginaSolicitada"/>
				    <html:hidden property="idMatrizOferta" value="${idMatrizOferta}"/>
				    <html:hidden property="idOfertaSap" value="${idOfertaSap}"/>
				    <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
		            <div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
					<div style="height:10px;"></div>
					<div class="fleft">
						<div class="label-form-bold" style="width: 100px">Nome do Plano:</div>
						<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome do Plano"</div>
						<div class="hide min_size_required_value">3</div>
						<html:text property="nmPlano" styleId="textbox_nmPlano" size="30" styleClass="min_size_required"/>
					</div>	
					
					<div class="fleft">
						<div class="label-form-bold" style="width: 120px">C&oacute;digo do Plano:</div>
						<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "C&oacute;digo do Plano"</div>
						<div class="hide min_size_required_value">3</div>
						<html:text property="cdPlano" styleClass="min_size_required" styleId="textbox_cdPlano" size="25"/>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:110px;">UF:</div>
						<html:text property="idsUf" styleId="hiddenUFs"  styleClass="hide"/>
						<input type="text" style="width: 122px"/>
						<html:button bundle="messages" tabindex="6" property="btn_abrir" onclick="abrirPopup1($(this).next('a').href, [$('hiddenUFs')]);" value="..." titleKey="catalogo.consultaPlanos.Uf"/>
						<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/pesquisarListaUfs.do" style="display:none;"/>
					</div>
					<br clear="all"/><br clear="all"/>						
					<div class="fleft">
						<div class="label-form02 label_required" style="width:100px;">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select property="idPlataforma" style="width:164px;" styleClass="required" styleId="select_plataforma">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="plataformaTO" items="${plataforma}">
								<html:option value="${plataformaTO.idPlataforma}">${plataformaTO.nmPlataforma}</html:option>	
							</c:forEach>
						</html:select>
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:120px;">Tecnologia:</div>
						<html:select property="idTecnologia" styleId="select_tecnologia" style="width:137px;">
							<html:option value="">-- Selecione -- </html:option>
							<c:forEach var="tecnologiaTO" items="${tecnologia}">
								<c:if test="${tecnologiaTO.tecnologiaReferencia == null }">
									<html:option value="${tecnologiaTO.idTecnologia}">${tecnologiaTO.nmTecnologia}</html:option>
								</c:if>
							</c:forEach>
						</html:select>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
					    <html:button bundle="messages" property="limpar_form" styleId="botao_limpar_form_planos" onClick="limparForm(this);$('divErros').hide();$('resultado_pesquisa_sap').hide();return false" styleClass="btNavegacao74" value="Limpar" altKey="catalogo.global.form.Limpar" titleKey="catalogo.global.form.Limpar" />
					    <span>&nbsp;</span>
						<html:button bundle="messages" property="botao_pesquisar_planos_oferta" styleId="botao_pesquisar_planos_oferta" onMouseDown="$('paginaSolicitada').value='1';" onClick="clearAndShow('resultado_pesquisa_sap');send(this, 'resultado_pesquisa_sap', null, 'div_erro');return false" styleClass="btNavegacao74" value="Pesquisar" altKey="catalogo.global.form.Pesquisar" titleKey="catalogo.global.form.Pesquisar" />
					</div>
				</html:form>
				</td></tr></table>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br clear="all"/>
<div id="div_retorno_erro" style="position: relative; display: none"></div>
<div id="resultado_pesquisa_sap" style="position: relative; display: none"></div>