<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Modelo de Vendas Matriz de Ofertas</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
	<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<netui:form action="pesquisarModeloVendaMatrizOferta">
				<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
				<netui:error key="tipoProduto"/>
			           <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			            <div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						<br clear="all" />
						<div class="fleft">
							<div class="label-form-bold" style="width: 140px">C&oacute;digo do modelo:</div>
							<netui:textBox dataSource="actionForm.cdModeloVenda" tabindex="1" tagId="textfield_cd_modelo" styleClass="at_least_two" maxlength="50" size="25" />							
						</div>	
						<div class="fleft">
							<div class="label-form-bold" style="width: 140px">Nome do modelo:</div>
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome do Modelo de Vendas".</div>
							<div class="hide min_size_required_value">3</div>
							<netui:textBox dataSource="actionForm.nmModeloVenda" tabindex="2" tagId="textfield_nm_modelo" styleClass="at_least_two min_size_required" maxlength="50" size="60" />							
						</div>	
						
						<br clear="all"/><br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
							<netui:button tabindex="5" type="button" onClick="clearAndShow('resultado_pesquisa');limparForm(this);$('novo_modelo_vendas').hide();$('resultado_pesquisa').hide();habilitar('botao_novo_modelo_vendas');$('divErros').hide();return false;" styleClass="btNavegacao74" value="Limpar" title="${bundle.default['catalogo.global.Limpar']}"/>
						    <span>&nbsp;</span>
						    <netui:button tabindex="4" tagId="botao_pesquisar_modelo_vendas" type="button" onMouseDown="$('pagina_solicitada').value='1';" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section')" styleClass="btNavegacao74" value="Pesquisar" title="${bundle.default['catalogo.modeloVendas.Pesquisar']}"/>
						    <span>&nbsp;</span>
						    <netui:button tabindex="3" tagId="botao_novo_modelo_vendas" type="button" onClick="desabilitar(this);$('novo_modelo_vendas').show();$('novo_modelo_vendas').scrollTo();$('novo_modelo_vendas').focus();" styleClass="btNavegacao74" value="Novo" title="${bundle.default['catalogo.modeloVendas.Novo']}"/>
						</div>
					</netui:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br clear="all" />
<div id="resultado_pesquisa" style="display: none; position: relative"></div>
<div id="novo_modelo_vendas" class="secao_conteudo" style="display: none; position: relative">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Novo Modelo de Vendas Matriz de Ofertas</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
	<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<netui:form action="criarModeloDeVendaMatrizOferta">
				<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
				<netui:error key="tipoProduto"/>
			           <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			            <div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						<br clear="all" />
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 140px">C&oacute;digo do modelo:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<netui:textBox dataSource="actionForm.cdModeloVenda" tabindex="6" tagId="textfield_novo_cd_modelo" styleClass="required" maxlength="50" size="25" />							
						</div>	
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 140px">Nome do modelo:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<netui:textBox dataSource="actionForm.nmModeloVenda" tabindex="7" tagId="textfield_novo_nome_modelo" styleClass="required" maxlength="50" size="60" />							
						</div>	
						
						<br clear="all"/><br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
							<netui:button tabindex="9" type="button" onClick="limparForm(this);$('divErros').hide();$('novo_modelo_vendas').hide();habilitar('botao_novo_modelo_vendas');return false;" styleClass="btNavegacao74" value="Cancelar" title="${bundle.default['catalogo.modeloVendas.Cancelar']}"/>
						    <span>&nbsp;</span>
						    <netui:button tabindex="8" tagId="botao_gravar_novo_modelo_vendas" type="button" onClick="clearAndShow('resultado_pesquisa');if(send(this, 'resultado_pesquisa', null, 'novo_modelo_vendas')){limparForm(this);$('novo_modelo_vendas').hide();habilitar('botao_novo_modelo_vendas');};" styleClass="btNavegacao74" value="Gravar" title="${bundle.default['catalogo.modeloVendas.Gravar']}"/>
						</div>
					</netui:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>