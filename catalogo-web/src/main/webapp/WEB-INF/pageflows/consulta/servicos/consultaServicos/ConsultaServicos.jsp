<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

	<div class="secao_conteudo">	
	 
		<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Consulta Servi&ccedil;os:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
		<div>
		<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">																							
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/pesquisar.do" styleId="acaoForm" onsubmit="false">
				
			    <%-- <html:hidden property="paginaSolicitada" styleId="pagina_solicitada"  /> --%>
								
				<div class="legendaObrigatorio help">(*) Campo Obrigatório</div>
			    <div class="link_manual" title="Dúvida">
					<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161348" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
				</div>
								
			<div div class="fleft">
					<div class="label-form02 label_required" style="width: 120px">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
																																
		 		<html:select property="idPlataforma" styleId="select_plataforma" tabindex="1" style="width:140px;" styleClass="required" 		 		
		 			onChange="displayDivSistema(this.value, 'div_campo_sistema'); jsonListarCategorias($('link_listarCategorias').href, $F(this));return false;">
				   <option value="">-- Selecione --</option>
				    <c:forEach items="${plataformas}" var="plataformaListTO">				       
				       <option value="${plataformaListTO.idPlataforma}">${plataformaListTO.nmPlataforma}</option>				       
				   </c:forEach>				   			   				   
			 	</html:select> 
																	  			                       
			    <html:link styleId="link_listarCategorias" action="/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/listarCategorias.do" styleClass="display:none;"/>			    
			</div>
																
				<div class="fleft" id="div_campo_sistema" style="display: none;">
					<div class="label-form-bold	 label_required" style="width:120px;">Sistema:</div>
					
				<html:select property="idSistema" tabindex="2" style="width:140px;" styleId="select_sistema" onchange="jsonListarCategorias($('link_listarCategorias').href, $F('select_plataforma'), $F(this));return false;">
				   <option value="">-- Selecione --</option>
				   <option value="2">ATLYS</option>
				   <option value="3">NGIN</option>				    				   			   				   
			 	</html:select> 
														
				</div>
				
				<br clear="all"/><br clear="all"/>
				<div div class="fleft">
					<div class="label-form02" style="width:120px">Grupo de Serviços:</div>					
				<html:select property="idGrupoServico" styleId="select_categoria" tabindex="3" style="width:402px;" styleClass="notRequired">
				   <option value="">-- Selecione --</option>				    			   			   				  
			 	</html:select> 									
				</div>
								
				<br clear="all"/>
				<br clear="all"/>
				<div class="fleft">
					<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 2 caracteres para o campo Nome do Servi&ccedil;o.</div>
					<div class="hide min_size_required_value">2</div>
					<div class="label-form-bold" style="width:120px">Nome do Servi&ccedil;o:</div>					
					<html:text tabindex="4" property="nomeServico" styleId="nomeServico" styleClass="at_least_one min_size_required" size="25" maxlength="50" onKeyPress="if(CapturaTeclaEntra(event,'botao_pesquisar')){return false;}"/>
				</div>	
				
				<div class="fleft">
					<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 2 caracteres para o campo C&oacute;digo do Servi&ccedil;o.</div>
					<div class="hide min_size_required_value">2</div>
					<div class="label-form-bold" style="width:120px">&nbsp;&nbsp;C&oacute;digo do Servi&ccedil;o:</div>					
					<html:text tabindex="5" property="codigoServico" styleId="codigoServico" styleClass="at_least_one min_size_required" size="25" onKeyPress="if(CapturaTeclaEntra(event,'botao_pesquisar')){return false;}" />
				</div>
				
				<div class="fleft">
					<div class="label-form-bold" style="width:110px">Nome do Plano:</div>					
					<html:text tabindex="6" property="nomePlano" styleId="nomePlano" size="25"  style="width: 141px" />
				</div>
				
					<br clear="all"/><br clear="all"/>

					<div class="barra">
					</div>

					<div class="botao">
					<html:button bundle="messages" property="btn_limpar" tabindex="7" value="Limpar" onclick="$('resultado_pesquisa').hide();limparForm(this);" styleClass="btNavegacao74" titleKey="catalogo.global.Limpar" />
					<span>&nbsp;</span>
					<html:button bundle="messages" property="botao_pesquisar_servico" tabindex="6" value="Pesquisar" styleId="botao_pesquisar_servico" onclick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section'); return false;" styleClass="btNavegacao74" titleKey="catalogo.ConsultaServicos.Pesquisar"/>
				</div>
			</div>
			</html:form>
		</div>	
	</div>	
</div>

		<div class="conteudo_box_bottom">
		</div>
	
	<div id="resultado_pesquisa" style="position:relative;">
	</div>

</div>



