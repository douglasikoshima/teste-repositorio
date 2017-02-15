<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Associar Vari&aacute;veis</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img id="img_MaxMin" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" onclick="showHideSecaoConteudo(this);" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
		
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<div>
					<netui:form action="pesquisarVariaveisDoModelo">
						<br clear="all" />
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 118px;">Tipo de Cliente:</div>
							<netui:textBox dataSource="actionForm.idsTipoCliente" tagId="hidden_lista_tipo_cliente" styleClass="hide"/>
							<input type="text" id="textfield_tipo_cliente" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_tipo_cliente')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.TipoCliente']}" title="${bundle.default['catalogo.variavel.modelo.TipoCliente']}" />
							<netui:anchor action="buscarListaTipoCliente" styleClass="hide" />
						</div>
						
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 100px;">Carteira:</div>
							<netui:textBox dataSource="actionForm.sgCarteira" tagId="hidden_lista_carteira" styleClass="hide"/>
							<input type="text" id="textfield_carteira" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_carteira')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.Carteira']}" title="${bundle.default['catalogo.variavel.modelo.Carteira']}" />
							<netui:anchor action="buscarListaCarteira" styleClass="hide" />
						</div>

						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 100px;">Segmento:</div>
							<netui:textBox dataSource="actionForm.sgSegmento" tagId="hidden_lista_segmento" styleClass="hide"/>
							<input type="text" id="textfield_segmento" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_segmento')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.Segmento']}" title="${bundle.default['catalogo.variavel.modelo.Segmento']}" />
							<netui:anchor action="buscarListaSegmento" styleClass="hide" />
						</div>
						
						<br clear="all" /><br clear="all" />
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 118px;">Canal:</div>
							<netui:textBox dataSource="actionForm.idsCanal" tagId="hidden_lista_canal" styleClass="hide"/>
							<input type="text" id="textfield_canal" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_canal')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.Canal']}" title="${bundle.default['catalogo.variavel.modelo.Canal']}" />
							<netui:anchor action="buscarListaCanal" styleClass="hide" />
						</div>
						
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 100px;">UF:</div>
							<netui:textBox dataSource="actionForm.idsUf" tagId="hidden_lista_uf" styleClass="hide"/>
							<input type="text" id="textfield_uf" readonly="readonly" size="22"/><input tabindex="5" onclick="abrirPopup1($(this).next('a').href, [$('hidden_lista_uf')])" type="button" value="..." alt="${bundle.default['catalogo.variavel.modelo.UF']}" title="${bundle.default['catalogo.variavel.modelo.UF']}" />
							<netui:anchor action="buscarListaUf" styleClass="hide" />
						</div>
						
						<br clear="all" /><br clear="all" />
						<div class="barra"></div>
						<div class="botao">
							<netui:button tabindex="6" type="button" onClick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" styleClass="btNavegacao74" value="Limpar" alt="${bundle.default['catalogo.global.Limpar']}" title="${bundle.default['catalogo.global.Limpar']}"/>
						    <span>&nbsp;</span>
						    <netui:button tabindex="5" tagId="botao_pesquisar_variaveis_modelo" type="button" onMouseDown="$('pagina_solicitada').value='1';" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section');" styleClass="btNavegacao74" value="Pesquisar" alt="${bundle.default['catalogo.variavel.modelo.Pesquisar']}" title="${bundle.default['catalogo.variavel.modelo.Pesquisar']}"/>
						</div>
					</netui:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div
	</div>
</div>