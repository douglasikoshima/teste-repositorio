<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Alterar Oferta SAP</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
</div>
<div>
	<div class="conteudo_box_middle">
		<div class="conteudo_box_middle_mg relative">
			<table border="0" cellpadding="0" cellspacing="0" style="width: 99%"><tr><td>
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/salvarAlteracaoOfertaSap.do">
				<html:hidden property="idOfertaSap" styleId="idOfertaSap" value="${idOfertaSap}"/>
				<html:errors property="tipoProduto"/>
			    <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
	            <div class="link_manual" title="Dúvida">
					<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
				</div>
				<div style="height:10px;"></div>
				
				<div class="fleft">
					<div class="label-form-bold label_required" style="width: 130px">C&oacute;digo:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:text property="cdOfertaSap" tabindex="8" value="${cdOfertaSap}" styleClass="required" maxlength="3" size="32" styleId="textbox_cdOfertaSap" ></html:text>
				</div>	
				<div class="fleft">
					<div class="label-form-bold label_required" style="width: 150px">Descri&ccedil;&atilde;o:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:text property="descOfertaSap" Value="${dscOfertaSap}" styleClass="required" maxlength="255" styleId="textbox_descricao" size="60" />
				</div>
					
				<br clear="all"/><br clear="all"/>						
				<div class="fleft">
					<div class="label-form-bold label_required" style="width: 130px">Utiliza&ccedil;&atilde;o:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:select property="sgUtilizacao" tabindex="10" styleId="select_utilizacao" styleClass="required" style="width: 174px" value="${sgUtilizacao}">
						<html:option value="">-- Selecione --</html:option>
						<html:option value="POS">POS</html:option>
						<html:option value="HIB">HIB</html:option>
					</html:select>
				</div>
				
				<div class="fleft">
					<div class="label-form-bold label_required" style="width: 150px">Ativo:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:select property="inDisponivel" tabindex="4" styleId="select_disponivel_new" styleClass="required" style="width: 174px" value="${inDisponivel}">
						<html:option value="">-- Selecione --</html:option>
						<html:option value="S">SIM</html:option>
						<html:option value="N">NÃO</html:option>
					</html:select>
				</div>		

				<br clear="all"/><br clear="all"/>
				<div class="barra"></div>
				<div class="botao">
					<c:choose>
						<c:when test="${labelBotton eq 'btnComporOferta'}">
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/selecionarMatrizOfertaComposicao.do?id_oferta_sap=${idOfertaSap}" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'));$('div_alterar_oferta_sap').hide();$('resultado_pesquisa').hide();return false">
								<html:button property="comporOferta" value="Compor Oferta" styleClass="btNavegacao120"/>
							</html:link>
						 </c:when>
						 <c:otherwise>
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/visualizarComposicao.do?id_oferta_sap=${idOfertaSap}&dsc_oferta_sap=${dscOfertaSap}" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'));return false">
								<html:button property="composicaoOferta" styleClass="btNavegacao120" value="Composi&ccedil;&atilde;o"/>
							</html:link>
						 </c:otherwise>
					</c:choose>
				    <span>&nbsp;</span>
					<html:button bundle="messages" property="limpar" styleId="botao_limpar_form" onClick="limparForm(this);return false;" styleClass="btNavegacao74" value="Limpar" altKey="catalogo.matrizOferta.form.Limpar" titleKey="catalogo.matrizOferta.form.Limpar"/>
				    <span>&nbsp;</span>
				    <html:button bundle="messages" property="btn_gravar" value="Gravar" styleClass="btNavegacao74" onClick="if(send(this, null, null, 'div_alterar_oferta_sap')){$('div_alterar_oferta_sap').hide()};return false" titleKey="catalogo.paramPlanoServico.gravarAlteracaoServico"/>
				</div>
			</html:form>
			</td></tr></table>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>

<br/>
<div id="resultado_pesquisa" style="display: none; position: relative;"></div>
<div id="div_returno_erro" style="display: none; position: relative;"></div>