<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Incluir Nova Vari&aacute;vel:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg relative">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/salvarNovaVariavelCabecalhoMatriz.do">					
						<fmt:bundle basename="catalogoprs_messages" >
						<html:hidden property="paginaSolicitada" styleId="pagina_solicitada" />
						<html:hidden property="idMatrizOferta" styleId="idMatrizOferta" value="${idMatrizOferta}" />
			           	<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
			            <div class="link_manual" title="Dúvida">
			            	<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
			            	<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank">
			            		<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/>
			            	</html:link>
						</div>
						
						<br clear="all" />
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 110px">Tipo de Pessoa:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="idTipoCliente" styleId="select_tipoPessoa" styleClass="required" style="width: 110px" onChange="listarTipoCarteira($(this).next('a').href, $F(this))">
								<html:option value="">-- Todos --</html:option>
								<c:forEach var="tipoClienteTO" items="${tipoCliente}">
									<html:option value="${tipoClienteTO.idTipoCliente}">${tipoClienteTO.nmTipoCliente}</html:option>
								</c:forEach>
							</html:select>														
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/buscarListaCarteira.do" styleClass="hide"/>
						</div>	
						
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 100px">Carteira:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="sgCarteira" styleId="select_carteira" styleClass="required" style="width: 180px">
								<html:option value="">-- Todos --</html:option>
								<c:forEach var="carteiraTO" items="${carteira}">
									<html:option value="${carteiraTO.sgCarteira}">${carteiraTO.dsCarteira}</html:option>
								</c:forEach>
							</html:select>
						</div>
	
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 100px">Segmento:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="sgSegmento" styleId="select_segmento" styleClass="required" style="width: 160px" >
								<html:option value="">-- Todos --</html:option>
								<c:forEach var="segmentoTO" items="${segmento}">
									<html:option value="${segmentoTO.sgSegmento}">${segmentoTO.dsSegmento}</html:option>
								</c:forEach>
							</html:select>
						</div>

						<br clear="all"/><br clear="all"/>
						<div class="barra">
						</div>
						<div class="botao">
						    <html:button bundle="messages" property="btn_gravar" value="Gravar" styleId="botao_gravar_variavel_matriz_oferta" onClick="send(this, 'resultado_pesquisa_variaveis', null, 'div_returno_erro_variavel');return false" styleClass="btNavegacao74" altKey="catalogo.matrizOferta.variaveis.Gravar" titleKey="catalogo.matrizOferta.variaveis.Gravar" />
						</div>
						</fmt:bundle>
					</html:form>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
</div>
