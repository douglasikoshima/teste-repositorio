<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<catalogo:popupPadrao>
	<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/listarUFs.do">
		<c:set var="nomeBox" scope="application"  value="UF:"/>
		<jsp:include page="/templates/box_pre.jsp"/>
			<table width="100%">
				<tr>
					<td valign="top" width="50%">
						<div style="height:200px" class="vertical-scroll">
							<form>
								<table class="tabConteudoGeral" width="100%">
									<tr>
										<td><input type="checkbox" value="Todas" onclick="selecionarTodosCheckbox($(this).up('table').select('.checkbox'), this.checked);" class="semBorda"/></td>
									    <td><b>Todas</b></td>
									</tr>
									
								<logic:iterate id="ufs" property="ufsList" name="consultaPlanosForm">
									<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
										<td width=10px">																							   
											<html:multibox property="ufsSelecionados" onClick="selecionarChavePopup('hiddenUFs', this, 'ddds_uf_',  null, null);if($F('select_plataforma')!=1){mostrarValoresDaChaveNoPopup('div_valores', 'ddds_uf_', '${ufs.idUf}');}" styleId="ufs_cb_${ufs.idUf}" value="${ufs.idUf}" styleClass="semBorda checkbox" >
						                       <bean:write bundle="messages" name="ufs"  property="idUf" />
											</html:multibox>
										</td>
										<td width="100px" nowrap="nowrap">
											<label for="ufs_cb_${ufs.idUf}" onclick="if($F('select_plataforma')!=1){mostrarValoresDaChaveNoPopup('div_valores', 'ddds_uf_', '${ufs.idUf}');}return false;">${ufs.nmUF}</label>
										</td>
									</tr>
								</logic:iterate>
								
								</table>
							</form>
						</div>
					</td>
					<td valign="top" width="50%">
						<div id="div_valores" class="fleft vertical-scroll" style="height:280px;width:100%;">
						<logic:iterate id="ddds" property="ufsList" name="consultaPlanosForm">
							<table id="ddds_uf_${ddds.idUf}" style="display:none;" class="tabConteudoGeral" width="200px" >
								<tr><td class="titulo word-wrap" colspan="2">DDDs PARA: ${ddds.nmUF}</td></tr>
									<c:if test="${ddds != null}">
									
										<c:forEach items="${ddds.listaAreaRegistro}" var="sublista" >
											<tr onmouseover="$(this).className='mouseOver'" onmouseout="$(this).className='mouseOut'">
												<td width=10px">
													<html:multibox property="dddsSelecionados" onClick="selecionarValorDaChavePopup('hiddenUFs', '${sublista.idUf}', this);" styleClass="semBorda checkbox" value="${sublista.codigoArea}" styleId="ddds_uf_cb_${codigoArea}">
														<c:out value="${sublista.codigoArea}"/>
													</html:multibox>
												</td>
												<td width="90%" class="word-wrap">
													<label for="ddds_uf_cb_${sublista.codigoArea}">${sublista.codigoArea}</label>
												</td>
											</tr>
										</c:forEach>
									</c:if>
							</table>
							</logic:iterate>
						
						</div>
					</td>
				</tr>
			</table>
		<jsp:include page="/templates/box_pos.jsp"/>
		<div class="botao">
			<html:button bundle="messages" property="btOk" styleClass="btOk" onclick="fecharPopup('popup1');" value="OK" titleKey="catalogo.popupSelecionarUfs.OK"/>
		</div>
	</html:form>
</catalogo:popupPadrao>
