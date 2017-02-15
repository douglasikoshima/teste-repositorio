<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>

<netui-temp:template templatePage="/templates/popupConfirmacao.jsp">
			
	<netui-temp:section name="conteudo">
		<br clear="all"/>
		<div class="legendaObrigatorio">
			(*) Campo Obrigatório
		</div>
		<br clear="all"/>
		<netui:form action="desassociarProdutosModelos">
		<div id="popup_desassociar_produto_modelo" style="width:70%;">
			Você tem certeza de que deseja excluir essa associação? 
			<br><br>
			<div align="left" class="label_required">Justificativa:<font size="1px" color="#EEB422" valign="center">*</font></div>
			<netui-data:repeater dataSource="ids_produto_grupo_produto">
				<netui-data:repeaterItem>
					<netui:hidden dataSource="actionForm.idsProdutoGrupoProduto" dataInput="${container.item}" />
				</netui-data:repeaterItem>
			</netui-data:repeater>
			<netui-data:repeater dataSource="ids_produto">
				<netui-data:repeaterItem>
					<netui:hidden dataSource="actionForm.idsProduto" dataInput="${container.item}" />
				</netui-data:repeaterItem>
			</netui-data:repeater>
			<netui-data:repeater dataSource="ids_grupo_produto">
				<netui-data:repeaterItem>
					<netui:hidden dataSource="actionForm.idsGrupoProduto" dataInput="${container.item}" />
				</netui-data:repeaterItem>
			</netui-data:repeater>
			<netui:textArea styleClass="required" dataSource="actionForm.justificativa" rows="4" cols="45" style="width:100%;"/>
		</div>
		<br clear="all"/>
		<cata:temPermissao acao="alterarProduto">
			<input type="button" onclick="send(this, null, 'popup1', 'resultado_busca_produto', null, 'div_erros_popup_confirmacao');" class="btNavegacao74" value="Sim" title="${bundle.default['catalogo.popupDesassociarProdutosModelos.Sim']}"/>
		</cata:temPermissao>
		</netui:form>
		&nbsp;
		<cata:temPermissao acao="alterarProduto">
			<input type="button" onclick="fecharPopup('popup1');" class="btNavegacao74" value="Não" title="${bundle.default['catalogo.popupDesassociarProdutosModelos.Nao']}"/>
		</cata:temPermissao>
		<br/><br/>
	</netui-temp:section>

</netui-temp:template>