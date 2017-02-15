<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<div class="secao_conteudo">
<div id="conteudo_divErros"></div>
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Hist&oacute;rico</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg relative">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/historico/listaDados.do">
						<html:hidden styleId="pagina_solicitada" property="pageNumber"/>
				   	     <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			       	     <div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
							<div style="height: 3px"></div>
						
						<div class="fleft">
							<div class="label-form-bold" style="width: 80px">Login:</div>
							<html:text tabindex="1" property="login" size="25" styleId="textbox_login"/>
						</div>	
						<div class="fleft">
							<div class="label-form-bold" style="width: 80px">Data:</div>
							<html:text tabindex="2" property="dataScript" value="" styleClass="data required" maxlength="10" size="25" styleId="textbox_data" onKeyPress="return isFormatDate(event, this, '/')"/>
						</div>
						<div class="fleft">
							<div class="label-form-bold" style="width: 80px">Tabela:</div>
							<html:text tabindex="3" property="tabela" size="25" styleId="textbox_tabela" />
						</div>	
						<div class="fleft">
						</div>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="bt_limpar" tabindex="5" onClick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" styleClass="btNavegacao74" value="Limpar" bundle="messages" altKey="catalogo.global.Limpar" titleKey="catalogo.global.Limpar"/>
						    <span>&nbsp;</span>
						    <html:button property="bt_pesquisar" tabindex="4" styleId="bot_pesquisa" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section');" styleClass="btNavegacao74" value="Pesquisar" bundle="messages" altKey="catalogo.tradutorErro.historico.Pesquisar" titleKey="catalogo.tradutorErro.historico.Pesquisar"/>
						</div>
						
					</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br/>
<div id="resultado_pesquisa" style="display: none; position: relative;"></div>                      
