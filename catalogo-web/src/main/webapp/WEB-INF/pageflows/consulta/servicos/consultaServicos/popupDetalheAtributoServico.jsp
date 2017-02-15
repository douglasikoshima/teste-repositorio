<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
			
	<netui-temp:section name="conteudo">
		<c:set var="nomeBox" scope="application"  value="Detalhes do Serviço: Pacote Longa Distância"/>
		<jsp:include page="/templates/box_pre.jsp"/>
		<div align="left">
			<form>
				<table width="100%"  cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter tablesorter-left">
					<thead><br>
						<tr>
							<th>Grupo/Grupo de Serviços:</th>
							<th>Nome do Serviço:</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
						</tr>
					</tbody>
				</table>
			<br clear="all"/>
			</form>
		</div>
		<jsp:include page="/templates/box_pos.jsp"/>
	</netui-temp:section>

</netui-temp:template>