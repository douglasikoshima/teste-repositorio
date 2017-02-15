<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>

<catalogo:popUpDefault title="Cadastro de IP's" close="true">
	<div id="contentAllPopUp">
		<div class="legendaObrigatorio help">&nbsp; (*) Campo Obrigat&oacute;rio</div>
		<c:choose>
			<c:when test="${labelSucess != null}">
				<div id="divSucess" style="display:inline; background-color: white;position:relative;">
				<catalogo:contentSimple>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td rowspan="2" class="box_middle_left"></td>
							<td>
								<div class="box_middle_center">
									<div id="conteudo_divSucess" class="box_middle_center_conteudo" style="color: #299B00">${labelSucess}</div>
								</div>
							</td>
							<td rowspan="2" class="box_middle_right"></td>
						</tr>
					</table>
				</catalogo:contentSimple>
			</div>
			</c:when>
			<c:otherwise>
				<div id="divSucess" style="display:none;"></div>
			</c:otherwise>
		</c:choose>		
		<c:if test="${labelError != null}">
			<div id="divErrosMidlePopUp" style="background-color: white;position:relative;"><catalogo:contentError id="contentErrorPopUp" >${labelError}</catalogo:contentError></div>
		</c:if>
		<table>
			<tr>
				<td style="text-align: left;">
					<div id="adicionarIP">
						<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/saveIP" styleId="pacoteBonusForm">
									<div class="label-form-bold2 fleft label_required">IP:<font size="1px" color="#EEB422" valign="center">*</font></div>
									<input type="text" name="numeroIP"  id="numeroIP" style="width: 150px;" maxlength="254" value="" onKeyPress="return semPontoVirgula(event);"/>		
									<input type="button" value="Gravar" class="btNavegacao74" id ="btIncluir" name="btIncluir" onclick="clearEditando();send(this, 'popup1', null, 'div_erros_popup');"/>
									<input type="button"  id="btInclusaoHidden" style="display: none;" onclick="send(this, 'popup1', null, 'div_erros_popup');" />
						</html:form>
					</div>
				</td>
			</tr>
		</table>
		<div id="ipList">
				<div style="overflow: auto; height: 300px; ">
<!-- 					<netui-data:repeater dataSource="listIP" defaultText="<br><span align='center'>Não existem IP's cadastrados.</span>">
						<netui-data:repeaterHeader> -->
							<table cellspacing="0" cellpadding="0" class="tabela-padrao-new tablesorter table_body" id="TabelaRelacionamentosRecentes" style="width: 99%;">
								<thead>
									<tr>
										<th class="sortable">IP</th>
										<th class="sortable">Data Criação</th>
									</tr>
								</thead>
								<tbody>
<!-- 						</netui-data:repeaterHeader>
						<netui-data:repeaterItem> -->
						<c:if test="${pacoteBonusForm.listIP == null}">	
						   <br><span align='center'>Não existem IP's cadastrados.</span>
						</c:if>	
						<c:if test="${pacoteBonusForm.listIP ne null}">							
						<logic:iterate id="listaIP" property="listIP" name="pacoteBonusForm">
							<tr>
								<td class="left">${listaIP.numeroIP}</td>
								<td class="left">${listaIP.dtInclusao}</td>
								<td class="left" style="text-align: center;">
								</td>
							</tr>
						</logic:iterate>
						</c:if>	
<!-- 						</netui-data:repeaterItem>
						<netui-data:repeaterFooter> -->
								</tbody>
							</table>
<!-- 						</netui-data:repeaterFooter>
					</netui-data:repeater> -->
				</div>
		</div>
	   </div>
</catalogo:popUpDefault>