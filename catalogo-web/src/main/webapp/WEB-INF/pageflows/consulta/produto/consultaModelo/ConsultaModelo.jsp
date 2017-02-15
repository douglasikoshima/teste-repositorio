<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	<div class="secao_conteudo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Consulta Modelo:</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"	alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
			</div>
		<div>
		<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/pesquisarModelo.do" >				
				<input type="hidden" name="pagina_solicitada" value="<c:out value='${paginaSolicitada}'/>">
				<html:errors name="tipoProduto"/>
			           <div class="legendaObrigatorio help">(*) Campo Obrigatório</div>
			            <div class="link_manual" title="Dúvida">
			            	<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
							<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank">
								<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
							</html:link>
						</div>
						<div class="fleft">
							<div class="label-form02 label_required" style="width: 100px;">Tipo&nbsp;Produto:<font size="1px" color="#EEB422" valign="center">*</font>&nbsp;
							</div>
							    <html:select property="tipoProduto" styleId="tipoProduto" styleClass="required" onChange="jsonListarFabricantes($(this).next().href, $F(this));return false;" style="width:139px;" tabindex="1" >
							    	<c:if test="${tipos_produto != null}">
							    		<html:option value="">-- Selecione --</html:option>
							    		<c:forEach var="container" items="${tipos_produto}">
											<html:option value="${container.idTipoProduto}">${container.nmTipoProduto}</html:option>
										</c:forEach>
							    	</c:if>
							    </html:select>
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/buscarFabricantes.do" styleClass="display:none;"/>							
						</div>
						
						<div class="fleft">
							<div class="label-form-bold" style=" width: 100px;">Fabricante:</div>
							<html:select property="fabricante" styleId="select_fabricante" styleClass="notRequired" style="width:140px;" tabindex="2" >
							<html:option value="">-- Selecione --</html:option>
							</html:select> 
						</div>
						
						<div class="fleft">
							<div class="label-form-bold" style="width:100px;"> Tecnologia:</div>
																	
							<html:hidden property="tecnologias" styleId="hiddenTecnologias" />
							<html:text property="nomesTecnologias" styleId="hiddenTecnologiasNomes" styleClass="hide"/>
							<input type="text"/>
							<html:button bundle="messages" property="btn_abrir" tabindex="3" onclick="abrirPopup1($(this).next().href, [$('hiddenTecnologias')]);" value="..." titleKey="catalogo.ConsultaModelo.Tecnologia" />
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/abrirpopupTecnologiaModelo.do" style="display:none;"/>
										
						</div>
						<br clear="all"/>
						<br>						
						
						<div class="fleft">
							<div class="label-form-bold" style="width:100px;">Caracter&iacute;stica:</div>
							<html:hidden property="caracteristicas" styleId="hiddenCaracteristicas"/>
							<html:text property="nomesCaracteristicasValores" styleId="hiddenCaracteristicas_Nomes" styleClass="hide"/>
							<html:hidden property="valoresCaracteristicas" styleId="hiddenValoresCaracteristicas"/>
							<input type="text" size="20"/>
							<html:button bundle="messages" property="bt_abrirpopup" tabindex="4" onclick="abrirPopup1($(this).next('a').href, [$('hiddenCaracteristicas'), $('hiddenValoresCaracteristicas')])" value="..." titleKey="catalogo.ConsultaModelo.Caracteristica"/>
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/abrirpopupCaracteristicaModelo.do" style="display:none;"/>
										
						</div>
					
						<div class="fleft">
							<div class="label-form-bold" style="width:100px;">&nbsp;&nbsp;Modelos:&nbsp;</div>
							<div class="hide min_size_required_message">Obrigatório informar, no mínimo, 2 caracteres para o campo Modelo.</div>
							<div class="hide min_size_required_value">2</div>
							<html:text property="modelo" tabindex="5" styleClass="min_size_required" size="25"/>
							
						</div>
						
						<div class="fleft">
							<div nowrap="nowrap" class="label-form-bold" style="width:100px;">&nbsp;&nbsp;&nbsp;Per&iacute;odo de&nbsp;</div>
							<div class="data_nome_campo hide">Período de</div>
							<input type="text" tabindex="6" name="periodoInicio" id="periodo_de" onkeypress="return mascara_data(event,this);" size="11" style="data" />&nbsp;a&nbsp;
							<div class="data_nome_campo hide">Período até</div>
							<input type="text" tabindex="7" name="periodoFim" id="periodo_ate" onkeypress="return mascara_data(event,this);" size="11" style="data" />&nbsp;a&nbsp;
						</div>			
								
						<br clear="all"/>
						<br>
	
						<div class="barra">
						</div>
						<div class="botao">
							<html:button bundle="messages" tabindex="9" property="bot_limpar" styleId="bot_limpar" styleClass="btNavegacao74" onClick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" value="Limpar" titleKey="catalogo.global.Limpar"/>
						    <span>&nbsp;</span>
						    <html:button bundle="messages" tabindex="8" property="bot_pesquisa" styleId="bot_pesquisa" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null , 'right_section');" styleClass="btNavegacao74" value="Pesquisar" titleKey="catalogo.ConsultaModelo.Pesquisar"/>
						</div>
					</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom">
			</div>
		</div>
	</div>
</div>
<br/>
						
<div id="resultado_pesquisa" style="position: relative;"></div>
	

