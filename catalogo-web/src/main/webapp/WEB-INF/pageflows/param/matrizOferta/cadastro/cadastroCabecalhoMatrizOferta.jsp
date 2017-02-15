<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="secao_conteudo">
<fmt:bundle basename="catalogoprs_messages" >
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Cadastro Matriz Oferta:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco"/>
		</div>
	</div>
<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/pesquisarCabecalhosMatrizOferta.do">
				<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
				<!-- <netui:error key="tipoProduto"/> -->
				<html:errors name="tipoProduto"/>
			           <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			            <div class="link_manual" title="Dúvida">
			            	<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
			            	<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank">
			            		<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/>
			            	</html:link>
						</div>
						<div style="height:10px;"></div>

						<div class="fleft">
							<div class="label-form-bold label_required data_nome_campo" style="width: 140px">Vigente em:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<c:set var="today" value="<%=new java.util.Date()%>" />
							<fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${today}" var="dataatual" />
							<html:text tabindex="1" property="dtVigencia" value="${dataatual}" styleClass="data required" maxlength="10" size="25" styleId="textbox_dtVigencia" onKeyPress="return isFormatDate(event, this, '/')"/>
						</div>	
						
						<div class="fleft">
							<div class="label-form-bold" style="width: 150px">Nome da Matriz:</div>
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome da Matriz".</div>
							<div class="hide min_size_required_value">3</div>
							<html:text tabindex="2" property="nmMatrizOferta" styleClass="min_size_required" styleId="textbox_nmMatriz" size="60" />
						</div>							

						<br clear="all"/><br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
							<html:button bundle="messages" tabindex="5" property="btn_limpar" onClick="clearAndShow('resultado_pesquisa');limparForm(this);$('div_cadastro_novo_cabecalho').hide();return false;" styleClass="btNavegacao74" value="Limpar" altKey="catalogo.global.Limpar" titleKey="catalogo.global.Limpar" />
						    <span>&nbsp;</span>
						    <html:button bundle="messages" tabindex="4" property="btn_pesquisar" styleId="botao_pesquisar_cabecalho_matriz_oferta" onMouseDown="$('pagina_solicitada').value='1';" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section');$('div_cadastro_novo_cabecalho').hide();" styleClass="btNavegacao74" value="Pesquisar" altKey="catalogo.matrizOferta.Pesquisar" titleKey="catalogo.matrizOferta.Pesquisar" />
						    <span>&nbsp;</span>
							<cata:temPermissao acao="matrizOfertaNovo">
								<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/cadastrarNovoCabecalhoMatriz.do" styleId="botao_novo_cabecalho_matriz" onClick="if(abrirLink('div_cadastro_novo_cabecalho', this.href, 'right_section')){$('resultado_pesquisa').hide();clearAndShow('div_cadastro_novo_cabecalho');}return false">
									<html:button bundle="messages" tabindex="3" property="btn_novo" styleClass="btNavegacao74" value="Novo" altKey="catalogo.matrizOferta.cabecalho.Cadastro" titleKey="catalogo.matrizOferta.cabecalho.Cadastro" />
								</html:link>
							</cata:temPermissao>
						</div>
					
					</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</fmt:bundle>
</div>
<br/>
<div id="resultado_pesquisa" style="display: none; position: relative;"></div>
<div id="div_returno_erro" style="display: none; position: relative;"></div>
<div id="div_cadastro_novo_cabecalho" style="display: none; position: relative;"></div>

