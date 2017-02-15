<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
	<netui-temp:section name="conteudo">

		<div class="titulo">&nbsp;&nbsp; Motorola V3 Black - CDMA - Dual Band - 800/1200Mhz</div>
		<table width="100%">
			<tr>
				<td width="50%">	
					<c:set var="nomeBox" scope="application"  value="Caracteristicas do Modelo"/>
					<jsp:include page="/templates/box_pre.jsp"/>
							<div class="fleft">
								<div class="foto-moldura">
									<netui-data:repeater dataSource="multimidia">
										<netui-data:repeaterItem>
											<img src="/catalogo/static_server/img/711329p1.jpg"/>
										</netui-data:repeaterItem>
									</netui-data:repeater>
								</div>
								<div align="center">
									<a href="#" title="Foto Anterior"><img src="/catalogo/static_server/img/botoes/bt-fotos-anterior.gif" width="13" height="10" alt="Foto Anterior" title="Foto Anterior" /></a>
									<a href="#" title="Próxima Foto"><img src="/catalogo/static_server/img/botoes/bt-fotos-proxima.gif" width="14" height="10" alt="Próxima Foto" title="Próxima Foto" /></a>
								</div>
							</div>&nbsp;&nbsp;
								<div class="fleft">
									<div style="text-align: left">
										<netui-data:repeater dataSource="listaCaracteristica">
											<netui-data:repeaterItem>
												<div class="dados-list"><li>${container.item.nmCaracteristica}</li></div>
											</netui-data:repeaterItem>
										</netui-data:repeater>
									</div>
								</div>
						<br clear="all"/>
					<jsp:include page="/templates/box_pos.jsp"/>
				</td>
				
				<td width="50%">
				<c:set var="nomeBox" scope="application"  value="Código do Produto"/>
					<jsp:include page="/templates/box_pre.jsp"/>
						<netui-data:repeater dataSource="listaSistemaProduto">
							<netui-data:repeaterItem>
						  		<div class="dados-list"><li>${container.item.idSistemaProduto}</li></div>
						  	</netui-data:repeaterItem>
						</netui-data:repeater>
					<jsp:include page="/templates/box_pos.jsp"/>
				</td>
			</tr>
		</table>
	</netui-temp:section>
</netui-temp:template>
			
			

