<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Oferta SAP Matriz Oferta</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/pesquisarOfertaSapMatrizOferta.do">
 					<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
 				    <html:errors property="tipoProduto"/>
 				    <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div> 
 		            <div class="link_manual" title="Dúvida"> 
 						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a> 
 					</div> 
 					<div style="height:10px;"></div> 
	
 					<div class="fleft"> 
 						<div class="label-form-bold" style="width: 130px">C&oacute;digo:</div> 
						<html:text property="cdOfertaSap" maxlength="10" size="32" styleId="textbox_ofertaSap"/> 					
 					</div>	 
							
 					<div class="fleft">
 						<div class="label-form-bold" style="width: 150px">Descri&ccedil;&atilde;o:</div> 
 						<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Descri&ccedil;&atilde;o"</div> 
 						<div class="hide min_size_required_value">3</div>
						<html:text property="descOfertaSap" styleId="textbox_descricao" size="60" styleClass="min_size_required"></html:text>
 					</div> 
						
 					<br clear="all"/><br clear="all"/>						 
 					<div class="fleft"> 
 						<div class="label-form-bold" style="width: 130px">Utiliza&ccedil;&atilde;o:</div> 
						<html:select property="sgUtilizacao" styleId="select_utilizacao" style="width: 174px">
							<html:option value="">-- Selecione --</html:option>	
							<html:option value="POS">POS</html:option>	
							<html:option value="HIB">HIB</html:option>
						</html:select>							
 					</div> 
 					<div class="fleft"> 
 						<div class="label-form-bold" style="width: 150px">Dispon&iacute;vel:</div> 
 						<html:select property="inDisponivel" styleId="select_disponivel" style="width: 174px">
							<html:option value="">-- Selecione --</html:option>	
							<html:option value="S">SIM</html:option>	
							<html:option value="N">NÃO</html:option>
						</html:select>
 					</div>							 
 					<br clear="all"/><br clear="all"/> 
 					<div class="barra"></div> 
 					<div class="botao"> 
 					    <html:button bundle="messages" property="botao_limpar_form" onClick="clearAndShow('resultado_pesquisa');limparForm(this);$('div_cadastro_nova_oferta_sap').hide();$('div_alterar_oferta_sap').hide();$('div_pesquisa_planos').hide();return false;" styleClass="btNavegacao74" value="Limpar" altKey="catalogo.global.Limpar" titleKey="catalogo.global.Limpar" ></html:button>
 					    <span>&nbsp;</span> 
 					    <html:button bundle="messages" property="botao_pesquisar_form" styleId="botao_pesquisar_oferta_sap" onMouseDown="$('pagina_solicitada').value='1';" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section');$('div_cadastro_nova_oferta_sap').hide();$('div_alterar_oferta_sap').hide();$('div_pesquisa_planos').hide();return false" styleClass="btNavegacao74" value="Pesquisar" altKey="catalogo.matrizOferta.Pesquisar" titleKey="catalogo.matrizOferta.Pesquisar"/>
 					    <span>&nbsp;</span> 
					    <cata:temPermissao acao="novoMatrizOfertaOfertaSap">
 							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/cadastrarNovaOfertaSapMatrizOferta.do" styleId="botao_nova_ofertaSap" onClick="if(abrirLink('div_cadastro_nova_oferta_sap', this.href, 'right_section')){$('resultado_pesquisa').hide();clearAndShow('div_cadastro_nova_oferta_sap');}return false">
 								<html:button bundle="messages" property="novo" styleClass="btNavegacao74" value="Novo" altKey="catalogo.matrizOferta.cabecalho.Cadastro" titleKey="catalogo.matrizOferta.cabecalho.Cadastro" />
 							</html:link>
					    </cata:temPermissao>
 					</div> 
				</html:form>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>

<br/>
<div id="resultado_pesquisa" style="display: none; position: relative;"></div>
<div id="div_erro" style="position: relative; display: none"></div>
<div id="div_cadastro_nova_oferta_sap" style="display: none; position: relative;"></div>
<div id="div_alterar_oferta_sap" style="display: none; position: relative;"></div>
<div id="div_pesquisa_planos" style="display: none; position: relative;"></div>