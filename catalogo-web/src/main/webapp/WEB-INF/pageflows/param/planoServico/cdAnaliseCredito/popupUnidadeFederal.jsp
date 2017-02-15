<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<netui-temp:template templatePage="/templates/popupPadrao.jsp">
			
	<netui-temp:section name="conteudo">
		<c:set var="nomeBox" scope="application"  value="UF:"/>
		<jsp:include page="/templates/box_pre.jsp"/>
			<table width="100%" border="0">
				<tr>
					<td valign="top" width="50%">
						<div style="height:230px; margin-top: 10px" class="vertical-scroll">
							<form>
								<table class="tabConteudoGeral" width="100%">
									<tr>
										<td><input type="checkbox" value="Todas" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkbox'), this.checked);" class="semBorda"/></td>
									    <td><b>Todas</b></td>
									</tr>
								<netui:checkBoxGroup dataSource="pageFlow.ufsSelecionados" optionsDataSource="${ufs}" repeater="true">
									<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
										<td width=10px">
											<netui:checkBoxOption onClick="selecionarChavePopup('hidden_ufs', this, 'ddds_uf_',  null, null);if($F('select_plataforma')!=1){mostrarValoresDaChaveNoPopup('div_valores', 'ddds_uf_', '${container.item.idUf}');}" tagId="ufs_cb_${container.item.idUf}" value="${container.item.idUf}" styleClass="semBorda checkbox"/>
										</td>
										<td width="100px" nowrap="nowrap">
											<label for="ufs_cb_${container.item.idUf}" onclick="if($F('select_plataforma')!=1){mostrarValoresDaChaveNoPopup('div_valores', 'ddds_uf_', '${container.item.idUf}');}return false;">${container.item.nmUF}</label>
										</td>
									</tr>
								</netui:checkBoxGroup>
								</table>
							</form>
						</div>
					</td>
					<td valign="top" width="50%">
						<div id="div_valores" class="fleft vertical-scroll" style="height:230px; margin-top: 10px; width: 100%">
							<c:forEach items="${ufs}" var="uf" >
									<table id="ddds_uf_${uf.idUf}" style="display:none;" class="tabConteudoGeral" width="200px" >
									<tr><td class="titulo word-wrap" colspan="2">DDDs PARA: ${uf.nmUF}</td></tr>
									<c:if test="${uf.listaAreaRegistro.areaRegistroList !=null }">
									<netui:checkBoxGroup dataSource="pageFlow.dddsSelecionados" optionsDataSource="${uf.listaAreaRegistro.areaRegistroList}" repeater="true">
										<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
											<td width="10%" align="center">
												<netui:checkBoxOption onClick="selecionarValorDaChavePopup('hidden_ufs', '${uf.idUf}', this);" tagId="ddds_uf_cb_${container.item.codigoArea}" value="${container.item.codigoArea}" styleClass="semBorda checkbox"/>
											</td>
											<td width="90%" class="word-wrap">
												<label for="ddds_uf_cb_${container.item.codigoArea}">${container.item.codigoArea}</label>
											</td>
										</tr>
									</netui:checkBoxGroup>
									</c:if>
									</table>
							</c:forEach>
						</div>
					</td>
				</tr>
			</table>
		<jsp:include page="/templates/box_pos.jsp"/>
		<div class="botao">
			<input class="btOk" type="button" onclick="fecharPopup('popup1');" value="OK" title="${bundle.default['catalogo.popupSelecionarUfs.OK']}"/>
		</div>
	</netui-temp:section>

</netui-temp:template>