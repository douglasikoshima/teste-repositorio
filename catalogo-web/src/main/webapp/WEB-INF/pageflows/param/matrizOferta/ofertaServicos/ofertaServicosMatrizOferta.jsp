<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Oferta de Servi&ccedil;os Matriz</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg relative">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/pesquisarOfertaServicosMatriz.do">
						<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
						<html:errors name="tipoServico"/>
						<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
						<div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						<br clear="all"/>
						<div class="fleft">
							<div class="label-form-bold" style="width:140px;">C&oacute;digo da Oferta:&nbsp;</div>
							<html:text property="cdOfertaServico" styleId="cdOfertaServico" tabindex="1" size="25"/>
						</div>
						<div class="fleft">
							<div class="label-form-bold" style="width:170px;">Nome da Oferta:&nbsp;</div>
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome da Oferta".</div>
							<div class="hide min_size_required_value">3</div>
							<html:text property="nmOfertaServico" styleId="nmOfertaServico" tabindex="2" styleClass="min_size_required" size="60"/>
						</div>
						<br clear="all"/><br clear="all"/>
						<div class="fleft">
							<div class="label-form-bold" style="width:140px;">C&oacute;digo do Servi&ccedil;o:&nbsp;</div>
							<html:text property="cdCodigo" styleId="cdCodigo" tabindex="3"  size="25"/>
						</div>
						<div class="fleft">
							<div class="label-form-bold" style="width:170px;">Nome do Servi&ccedil;o:&nbsp;</div>
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome do Servi&ccedil;o".</div>
							<div class="hide min_size_required_value">3</div>
							<html:text property="nmComercial" styleId="nmComercial" tabindex="4" styleClass="min_size_required" size="60"/>
						</div>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="bt_limpar" styleId="bt_limpar" tabindex="5" onClick="limparForm(this);$('resultado_pesquisa').hide();$('alterarOfertaServicos').hide();$('novo_oferta_servico').hide();$('servicosOfertaServicos').hide();habilitar('botao_nova_ofertas_servico');return false;" styleClass="btNavegacao74" value="Limpar" title="Limpar"/>
							<span>&nbsp;</span>
						    <html:button property="bt_listar" styleId="botao_listar_ofertas_servico" tabindex="6" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section');$('alterarOfertaServicos').hide();$('servicosOfertaServicos').hide();" styleClass="btNavegacao74" value="Pesquisar" title="Pesquisar"/>
						    <span>&nbsp;</span>
						    <cata:temPermissao acao="novoOfertaServicosMatrizOferta">
							    <html:button property="bt_novo" styleId="botao_nova_ofertas_servico" tabindex="7" onMouseDown="$('pagina_solicitada').value='1';" onClick="desabilitar(this);$('novo_oferta_servico').show();$('alterar_oferta_servicos').hide();$('novo_oferta_servico').scrollTo();$('novo_oferta_servico').focus();" styleClass="btNavegacao74" value="Novo" title="Novo" alt="Novo"/>
						    </cata:temPermissao>
						</div>
					</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br />

<div id="resultado_pesquisa" style="position: relative;"></div>

<div id="novo_oferta_servico" class="secao_conteudo" style="display: none; position: relative;">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Nova Oferta de Servi&ccedil;o Matriz de Ofertas</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg relative">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/criarOfertaServicosMatriz.do">
						<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
						<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
						<div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						<br clear="all" />
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 140px">C&oacute;digo da oferta:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:text property="cdOfertaServico" styleId="textfield_novo_cd_oferta" tabindex="8" styleClass="required" maxlength="50" size="25"/>						
						</div>	
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 143px">Nome da oferta:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:text property="nmOfertaServico" styleId="textfield_novo_nome_oferta" tabindex="9" styleClass="required" maxlength="50" size="60"/>						
						</div>
						<br clear="all" />
						<br clear="all" />
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 140px">Notas: </div>
							<html:textarea property="dsNota" styleId="textfield_ds_nota" tabindex="10" cols="84" rows="5"/>				
						</div>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button property="bt_limpar" styleId="bt_limpar" tabindex="10" onClick="limparForm(this);$('divErros').hide();return false;" styleClass="btNavegacao74" value="Limpar" title="Limpar"/>
						    <span>&nbsp;</span>
						    <html:button property="botao_gravar_novo_oferta_servicos" styleId="botao_gravar_novo_oferta_servicos" tabindex="11" onClick="clearAndShow('resultado_pesquisa');if(send(this, 'resultado_pesquisa', null, 'novo_oferta_servico')){limparForm(this);$('novo_oferta_servico').hide();}habilitar('botao_nova_ofertas_servico');return false;" styleClass="btNavegacao74" value="Gravar" title="Gravar" alt="Gravar" />
						</div>
					</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<div id="alterarOfertaServicos" style="position: relative;"></div><br>
<div id="servicosOfertaServicos" style="position: relative;"></div>