<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Itens Matriz Oferta:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>

	<div class="conteudo_box_middle">
		<div class="conteudo_box_middle_mg relative">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/pesquisarItensMatrizOferta.do">				
				<fmt:bundle basename="catalogoprs_messages" >
				<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
				<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
				<div class="link_manual" title="Dúvida">
					<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
					<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank" >
						<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
					</html:link>
				</div> 
				
				<br clear="all"/>
				
				<div class="fleft" id="isBegin" style="width: 625px">
					<div class="label-form-bold label_required" style="width: 105px;">Nome da Matriz:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:select tabindex="1" styleId="select_matrizOferta" property="idMatrizOferta" onChange="buscarListaOfertaSap($(this).next('a').href, $F('select_matrizOferta'));return false;" styleClass="required" style="width: 455px">
						<html:option value="">-- Selecione --</html:option>
						<c:forEach items="${matrizOferta}" var="matrizOfertaTO">
							<html:option value="${matrizOfertaTO.idMatrizOferta}">${matrizOfertaTO.nmMatrizOferta}</html:option>
						</c:forEach>
					</html:select>
					<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/buscarListaOfertaSapPorMatriz.do" styleClass="display:none;" />
				</div>
				
				<div class="fleft">
					<div class="label-form-bold label_required data_nome_campo" style="width: 80px;">Vigente em:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:text tabindex="3" property="dtVigenciaForm" value="${dataVigencia}" styleClass="data required" maxlength="10" size="22" styleId="textbox_dataVigencia" onKeyPress="return isFormatDate(event, this, '/')" style="width: 90px;"  />
				</div>
				
				<br clear="all"/>
				<br clear="all"/>
				
				<div class="fleft">
					<div class="label-form-bold label_required" style="width: 105px;">Org. de Vendas:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:text property="sgOrganizacaoVendas" styleId="hidden_org_vendas" styleClass="hide required"/>
					<input type="text" style="width: 93px" />
					<html:button bundle="messages" tabindex="2" property="btn_organizacoes" onclick="abrirPopup1($(this).next().href, [$('hidden_org_vendas')])" value="..." altKey="catalogo.matrizOferta.itens.Organizacoes"  titleKey="catalogo.matrizOferta.itens.Organizacoes" style="width: 21px"/>
					<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/buscarListaOrganizacaoDeVendas.do" styleClass="hide"/>
				</div>
				<div class="fleft">
					<div class="label-form-bold label_required" style="width: 85px;">Canal Atend.:<font size="1px" color="#EEB422" valign="center">*</font></div>
					<html:select tabindex="7" styleClass="required" property="idCanalAtendimento" styleId="select_canalatendimento" style="width: 175px;">
						<html:option value="">-- Selecione --</html:option>
						<c:forEach items="${canalList}" var="canalListTO" >
							<html:option value="${canalListTO.idCanalAtendimento}">${canalListTO.nmCanal}</html:option>
						</c:forEach>
					</html:select>
				</div>
				
				<div class="fleft">
					<div class="label-form-bold label_required" style="width: 80px;">Oferta SAP:&nbsp;&nbsp;</div>
					<html:select tabindex="5" property="idOfertaSap" styleId="select_ofertasap" style="width: 230px;" >
						<html:option styleId="select_ofertasap" styleClass="testeCombo" value="">-- Selecione --</html:option>
					</html:select>
				</div>
				
				<br clear="all" />
				<br clear="all" />
				
				<div class="fleft">
					<div class="label-form-bold" style="width: 105px">Cod. Produto:&nbsp;&nbsp;&nbsp;</div>
					<html:text tabindex="4" property="cdCodigo" styleId="textbox_cdProduto" size="31" style="width: 115px;"/>
				</div>
				
				<div class="fleft">
					<div class="label-form-bold" style="width: 85px;" >Pt. Vendas:&nbsp;&nbsp;&nbsp;</div>
					<html:text tabindex="6" property="canalVendas" styleId="textbox_nmPlano" size="22" style="width: 175px;"/>
				</div>
				
				<div class="fleft">
					<div class="label-form-bold" style="width: 80px;">A&ccedil;&atilde;o:&nbsp;&nbsp;</div>
					<html:select tabindex="8" styleId="select_acao" style="width: 230px;" property="sgAcao">
						<html:option value="">-- Selecione --</html:option>
						<c:forEach items="${acaoList}" var="acaoListTO">
							<html:option value="${acaoListTO.sgAcao}">${acaoListTO.sgAcao} - ${acaoListTO.nmAcao}</html:option>
						</c:forEach>					
					</html:select>
				</div>

				
				<br clear="all"/><br clear="all"/>
				<div class="barra">
				</div>
				<div class="botao">
					<html:button bundle="messages" tabindex="10" property="btn_limpar" onClick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" styleClass="btNavegacao74" value="Limpar" altKey="catalogo.global.Limpar" titleKey="catalogo.global.Limpar"/>
					<span>&nbsp;</span>
					<html:button bundle="messages" tabindex="9" styleId="botao_pesquisar_itens_matriz_oferta" onMouseDown="$('pagina_solicitada').value='1';" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null, 'right_section')"  property="btn_pesquisar" styleClass="btNavegacao74" value="Pesquisar" altKey="catalogo.matrizOferta.itens.Pesquisar" titleKey="catalogo.matrizOferta.itens.Pesquisar" />
				</div>
				</fmt:bundle>
			</html:form>
		</div>
	</div>
	<div class="conteudo_box_bottom"></div>

</div>
<br/>
<div id="div_returno_erro_alteracao_itens" style="display: none; position: relative;"></div>
<div id="resultado_pesquisa" style="position: relative;"></div>
