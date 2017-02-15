<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupConfirmacao.jsp">
	<netui-temp:section name="conteudo">
		<input id="larguraPopup" value="500px" class="hide"/>
		<input id="alturaPopup" value="150px" class="hide"/>
		<div style="width: 99%; height: 150px; padding: 30px 0px 0px 0px" class="box">
			<b><c:out value="${return}"/></b>
		<br clear="all" />
		<div style="width: 99%; margin: 40px 0px 5px 0px; text-align: center;">
			<input type="button" onclick="fecharPopup('popup1');$('link_parametrizacao_matrizOferta_importar_tipo_matriz_contrato').onclick();" class="btNavegacao74" value="OK" title="Ok"/>
		</div>		
		</div>

	</netui-temp:section>
</netui-temp:template>