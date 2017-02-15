<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupConfirmacao.jsp">
	<netui-temp:section name="conteudo">
	<input id="larguraPopup" value="500px" class="hide"/>
	<input id="alturaPopup" value="150px" class="hide"/>
		<div>
			<br clear="all" /><br clear="all" /> <br clear="all" />
			Confirme a alteração para o modelo de venda: <b>${cdModeloVenda}</b>
			<br clear="all" /><br clear="all" /><br clear="all" />
			<input type="button" id="botao_sim" onclick="$('botao_alterar_modelo_venda').onclick();fecharPopup('popup1');return false;" class="btNavegacao74" value="Sim" title="${bundle.default['catalogo.popupAlterarModelo.Sim']}"/>
			&nbsp;
			<input type="button" onclick="$('batao_cancelar_alteracao_modelo_venda').onclick();fecharPopup('popup1');" class="btNavegacao74" value="Não" title="${bundle.default['catalogo.popupAlterarModelo.Não']}"/>
		</div>
	</netui-temp:section>
</netui-temp:template>