<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Alterar Modelo de Vendas Matriz de Ofertas</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
	<div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<netui:form action="updateModeloDeVendaMatrizOferta">
				<netui:hidden tagId="pagina_solicitada" dataSource="actionForm.paginaSolicitada"/>
				<netui:hidden tagId="id_modelo_venda" dataSource="actionForm.idModeloVenda" dataInput="${idModeloVenda}" />
				<netui:error key="tipoProduto"/>
			           <div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			            <div class="link_manual" title="Dúvida">
							<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161341" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
						</div>
						<br clear="all" />
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 140px">C&oacute;digo do modelo:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<netui:textBox dataSource="actionForm.cdModeloVenda" defaultValue="${cdModeloVenda}" readonly="true" tabindex="6" tagId="textfield_novo_cd_modelo" styleClass="required readonly" maxlength="50" size="25" />							
						</div>	
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 140px">Nome do modelo:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<netui:textBox dataSource="actionForm.nmModeloVenda" defaultValue="${nmModeloVenda}" tabindex="7" tagId="textfield_novo_nome_modelo" styleClass="required" maxlength="50" size="60" />							
						</div>	
						
						<br clear="all"/><br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
							<netui:button tabindex="11" tagId="batao_cancelar_alteracao_modelo_venda" type="button" onClick="limparForm(this);$('divErros').hide();$('novo_modelo_vendas').hide();$('div_alterar_modelo_venda').hide();habilitar('botao_novo_modelo_vendas');return false;" styleClass="btNavegacao74" value="Cancelar" alt="${bundle.default['catalogo.modeloVendas.gravar.Cancelar']}" title="${bundle.default['catalogo.modeloVendas.gravar.Cancelar']}"/>
						    <span>&nbsp;</span>
						  	<netui:button tabindex="10" tagId="botao_alterar_modelo_venda" type="button" onClick="clearEditando();send(this, 'resultado_pesquisa', null, 'div_alterar_modelo_venda');clearEditando();" styleClass="hide" value="Gravar" title="${bundle.default['catalogo.alterarModelo.Gravar']}"/>
							<span>&nbsp;</span>
						    <netui:button tabindex="9" tagId="botao_gravar_novo_modelo_vendas" type="button" onClick="clearEditando();if(checkRequired($(this).form, 'div_alterar_modelo_venda')){return false};abrirPopup1($(this).next('a').href, null, 'div_alterar_modelo_venda');setEditando();return false;" styleClass="btNavegacao74" value="Gravar" alt="${bundle.default['catalogo.modeloVendas.gravar.Alterar']}" title="${bundle.default['catalogo.modeloVendas.gravar.Alterar']}"/>
						    <netui:anchor action="confirmAlterarModeloVenda" styleClass="hide">
								<netui:parameter name="cd_modelo_venda" value="${cdModeloVenda}"/>
							</netui:anchor>
							<span>&nbsp;&nbsp;</span>
							<netui:anchor action="listarProdutosAssociadosAoModelo" tagId="link_listar_produtos_associados" onClick="clearAndShow('lista_produtos_associados');abrirLink('lista_produtos_associados', this.href, 'right_section');return false">
							  	<netui:parameter name="id_modelo_venda" value="${idModeloVenda}"/>
							  	<netui:parameter name="cd_modelo_venda" value="${cdModeloVenda}"/>
							  	<netui:parameter name="nm_modelo_venda" value="${nmModeloVenda}"/>
							  	<netui:parameter  name="pagina_solicitada" value="1"/>
							  	<netui:button tabindex="8" tagId="botao_listar_produtos_associado" type="button" styleClass="btNavegacao74" value="Produtos" alt="${bundle.default['catalogo.alterarModelo.produtos.Pesquisar']}" title="${bundle.default['catalogo.alterarModelo.produtos.Pesquisar']}"/>
							</netui:anchor>					  	
						</div>
					</netui:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
<br clear="all" />
<div id="lista_produtos_associados" style="position: relative; display: none;">
</div>