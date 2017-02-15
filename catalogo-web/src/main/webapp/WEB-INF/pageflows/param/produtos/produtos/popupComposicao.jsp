<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
	<netui-temp:section name="conteudo">
	<c:set var="nomeBox" scope="application"  value="Composição"/>
	<input type="hidden" id="larguraPopup" value="400"/>
	<input type="hidden" id="alturaPopup" value="200"/>
	<jsp:include page="/templates/box_pre.jsp"/>
		<div id="popup_alterar_composicao_produto">
			${dsProduto}
		</div>
		<div class="botao">
			<input type="button" onclick="fecharPopup('popup1');" value="Ok" class="btOk" title="${bundle.default['catalogo.global.OK']}"/>
		</div>
	<jsp:include page="/templates/box_pos.jsp"/>
	<br/>
			
	</netui-temp:section>
</netui-temp:template>