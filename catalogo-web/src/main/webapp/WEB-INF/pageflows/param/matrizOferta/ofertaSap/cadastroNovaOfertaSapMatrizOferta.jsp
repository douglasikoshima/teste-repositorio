<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Cadastrar Nova Oferta SAP</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<table border="0" cellpadding="0" cellspacing="0" style="width: 99%"><tr><td>
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/salvarNovaOfertaSapMatrizOferta.do">
					<html:errors property="tipoProduto"/>
				    <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
		            <div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
					<div style="height:10px;"></div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 150px">C&oacute;digo:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:text property="cdOfertaSap" styleClass="required" maxlength="3" styleId="textbox_ofertaSap" size="32"/>
					</div>	
							
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 130px">Descri&ccedil;&atilde;o:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:text property="descOfertaSap" styleId="textbox_descricao" size="60" styleClass="required" maxlength="255"/>
					</div>
						
					<br clear="all"/><br clear="all"/>						
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 150px">Utiliza&ccedil;&atilde;o (Pre&ccedil;o Base):<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select property="sgUtilizacao" styleId="select_utilizacao" styleClass="required" style="width: 174px">
							<html:option value="">-- Selecione --</html:option>	
							<html:option value="POS">POS</html:option>	
							<html:option value="HIB">HIB</html:option>
						</html:select>	
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 130px">Ativo:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select property="inDisponivel" styleId="select_disponivel" styleClass="required" style="width: 174px">
							<html:option value="">-- Selecione --</html:option>	
							<html:option value="S">SIM</html:option>	
							<html:option value="N">NÃO</html:option>
						</html:select>
					</div>		
	
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
					   	<html:button bundle="messages" property="cancelar_form" styleId="botao_cancelar_cadastro_ofertasap" onClick="limparForm(this);$('divErros').hide();$('div_cadastro_nova_oferta_sap').hide();return false" styleClass="btNavegacao74" value="Cancelar" altKey="catalogo.matrizOferta.form.Limpar" titleKey="catalogo.matrizOferta.form.Limpar"/>
					    <span>&nbsp;</span>
						<html:button bundle="messages" property="gravar_form" styleId="botao_gravar_oferta_oferta_sap" onClick="if(send(this, 'div_cadastro_nova_oferta_sap', null, 'resultado_pesquisa')){$('resultado_pesquisa').hide()}; return false" styleClass="btNavegacao74" value="Gravar" altKey="catalogo.matrizOferta.cabecalho.Gravar" titleKey="catalogo.matrizOferta.cabecalho.Gravar" />
					</div>
				</html:form>
				</td></tr></table>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>