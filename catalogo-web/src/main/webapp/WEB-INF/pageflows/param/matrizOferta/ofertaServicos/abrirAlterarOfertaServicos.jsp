<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="alterar_oferta_servicos">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Alterar Oferta de Servi&ccedil;os</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/alterarOfertaServicosMatriz.do">
					<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			    	<div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
					<br clear="all" />
					<table>
						<tr>
							<td class="fleft">
								<html:hidden property="idOfertaServico" styleId="idOfertaServico" value="${idOfertaServico}"/>
								<div class="label-form-bold label_required" style="width: 140px">C&oacute;digo da oferta:<font size="1px" color="#EEB422" valign="center">*</font></div> 
								<html:text property="cdOfertaServico" styleId="cdOfertaServico" tabindex="12" readonly="true" styleClass="required readonly" value="${cdOfertaServico}" maxlength="50" size="25"/>
							</td>
							<td class="fleft">
								<div class="label-form-bold label_required" style="width: 140px">Nome da oferta:<font size="1px" color="#EEB422" valign="center">*</font></div> 
								<html:text property="nmOfertaServico" styleId="nmOfertaServico" value="${nmOfertaServico}"tabindex="13" styleClass="required" maxlength="50" size="60" />
							</td>
						</tr>
						<tr>
							<td class="fleft" colspan="2">
								<br/>
								<div class="label-form-bold label_required" style="width: 140px">Notas: </div>
								<html:textarea property="dsNota" styleId="textfield_ds_nota" value="${dsNota}" cols="84" rows="5" tabindex="10"/>
							</td>
						</tr>
					</table>						
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:button property="botao_alterar_gravar" tabindex="14" styleId="botao_alterar_gravar" onClick="clearAndShow('resultado_pesquisa');if(send(this, 'resultado_pesquisa', null, 'alterar_oferta_servicos')){limparForm(this);$('alterar_oferta_servicos').hide()};" styleClass="btNavegacao74" value="Gravar" title="Gravar Oferta de Serviços" alt="Gravar Oferta de Serviços"/>
						<span>&nbsp;</span>
						<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/abrirServicosAssociadosOfertaServicosMatriz.do?id_oferta_servico=${idOfertaServico}&pagina_solicitada=${no_pagina}" styleId="botao_alterar_servicos" 
						    onClick="if(abrirLink('servicosOfertaServicos', this.href, 'right_section')){clearAndShow('servicosOfertaServicos')}return false">
					    	<html:button tabindex="15" property="botao_alterar_servicos" styleId="botao_alterar_servicos" styleClass="btNavegacao74" onMouseDown="$('pagina_solicitada').value='1';" value="Serviços" alt="Abrir Popup de Associação de Serviços" title="Abrir Popup de Associação de Serviços"/>
						</html:link>
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>