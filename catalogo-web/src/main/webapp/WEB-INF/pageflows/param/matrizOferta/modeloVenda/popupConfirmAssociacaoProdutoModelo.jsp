<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<netui-temp:template templatePage="/templates/popupConfirmacao.jsp">
	<netui-temp:section name="conteudo">
			<div>
				<br clear="all" /><br clear="all" /> <br clear="all" />
				Os produtos selecionados ser&atilde;o associados ao Modelo de Venda: <b>${cdModeloVenda}</b>
				<br clear="all" /><br clear="all" /><br clear="all" />
				<input type="button" id="botao_sim" onclick="$('botao_hide_associar_novos_produtos_modelo').onclick();fecharPopup('popup2');return false;" class="btNavegacao74" value="Sim" title="${bundle.default['catalogo.popupAlterarModelo.Sim']}"/>
				&nbsp;
				<input type="button" onclick="fecharPopup('popup2');" class="btNavegacao74" value="Não" title="${bundle.default['catalogo.popupAlterarModelo.Não']}"/>
				<br clear="all" /><br clear="all" />
			</div>
	</netui-temp:section>
</netui-temp:template>