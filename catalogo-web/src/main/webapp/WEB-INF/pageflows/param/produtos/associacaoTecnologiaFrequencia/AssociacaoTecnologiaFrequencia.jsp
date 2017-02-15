<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

	<c:if test="${id_nova_associacao != null}">
		<img src="/catalogo/static_server/img/transparent.gif" onload="$(link_valores_frequencia_${id_nova_associacao}).onclick();" class="hide"/>
	</c:if>
		<div class="secao_conteudo">
		<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Consulta Tecnologia/ Tipo de Frequ&ecirc;ncia:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
			alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
		<div>
		<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg" style="position:relative;">
				<div style="width:100%;height:23px;" >
					<div class="link_manual" title="Dúvida">
						<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161326" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
					</div>
				</div>
				<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
					<thead>
						<tr>
							<th width="20%">Tecnologia</th>
							<th width="50%" nowrap="nowrap">Tipo de Frequência</th>
							<th width="1px">Frequencia</th>
							<cata:temPermissao acao="alterarAssociacaoTecnologiaTipoFrequencia">
								<th width="1px">Alterar</th>
							</cata:temPermissao>
							<cata:temPermissao acao="excluirAssociacaoTecnologiaTipoFrequencia">
								<th width="1px">Excluir</th>
							</cata:temPermissao>
						</tr>
					</thead>
				</table>
				<div class="both-scroll" style="height:430px;">
				<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaResultadoBuscaProdutos">
					<thead>
						<tr>
							<th width="20%">Tecnologia</th>
							<th width="50%" nowrap="nowrap">Tipo de Frequência</th>
							<th width="1px">Frequencia</th>
							<cata:temPermissao acao="alterarAssociacaoTecnologiaTipoFrequencia">
								<th width="1px">Alterar</th>
							</cata:temPermissao>
							<cata:temPermissao acao="excluirAssociacaoTecnologiaTipoFrequencia">
								<th width="1px">Excluir</th>
							</cata:temPermissao>
						</tr>
					</thead>
				<tbody>
							<logic:iterate id="tecnologiaTO" property="tecnologiaTipoFrequenciaList" name="associacaoFrequenciaForm">
									<tr>
										<td style="text-align: left;">${tecnologiaTO.nmTecnologia}</td>
										<td style="text-align: left;">${tecnologiaTO.listaTipoFrequencia[0].nmTipoFrequencia}</td>
										<td class="center">
										<html:link styleId="link_valores_frequencia_${tecnologiaTO.listaTipoFrequencia[0].idTecnologiaTpFrequencia}" onClick="abrirPopup1(this.href);return false;"
										   action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/valoresFrequencia.do?id_tecnologia=${tecnologiaTO.idTecnologia}&id_tipo_frequencia=${tecnologiaTO.listaTipoFrequencia[0].idTipoFrequencia}&id_tecnologia_tipo_frequencia=${tecnologiaTO.listaTipoFrequencia[0].idTecnologiaTpFrequencia}&qtFrequencia=${tecnologiaTO.listaTipoFrequencia[0].qtFrequencia}" >
										   <img alt="Valores de Frequência" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/table/table16.gif" />
										</html:link>
										</td>
										<cata:temPermissao acao="alterarAssociacaoTecnologiaTipoFrequencia">
											<td class="center">
										<html:link onClick="if(abrirLink('alt_Associacao', this.href)){habilitar('nova_associacao');$('nova_associacaoTecnologiaFrequencia').hide();clearAndShow('alt_Associacao');$('alt_Associacao').scrollTo();}return false;"
										   action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/carregarAlterarTecnologiaFrequencia.do?id_tecnologia=${tecnologiaTO.idTecnologia}&id_tipoFrequencia=${tecnologiaTO.listaTipoFrequencia[0].idTipoFrequencia}" >
										   <img alt="Alterar" src="/catalogo/static_server/img/botoes/bt-alterar.gif"/>
										</html:link>											
											</td>
										</cata:temPermissao>
										<cata:temPermissao acao="excluirAssociacaoTecnologiaTipoFrequencia">
											<td class="center">
										<html:link onClick="abrirPopup1(this.href, null, 'right_section');return false;"
										   action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/popupApagarTecnologiaFrequencia.do?id_tecnologia=${tecnologiaTO.idTecnologia}&id_tipoFrequencia=${tecnologiaTO.listaTipoFrequencia[0].idTipoFrequencia}" >
										   <img alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif" />
										</html:link>											
											</td>
										</cata:temPermissao>
									</tr>
							</logic:iterate>	
				</tbody>
			</table>
			</div>
			<div class="barra"></div>
			<div class="botao"><label class="lblForm">Quantidade de Frequ&ecirc;ncia:${size_tecnologia}</label>
				<cata:temPermissao acao="associarTecnologiaTipoFrequencia">
					<input id="nova_associacao" type="button" onclick="desabilitar(this);$('alt_Associacao').hide(); $('nova_associacaoTecnologiaFrequencia').show();$('nova_associacaoTecnologiaFrequencia').scrollTo();$('tecnologiaRef').focus();"
					class="btNavegacao74" value="Novo" title="Inclui Nova Associação"/>
				</cata:temPermissao>
			</div>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
		</div>
	</div>
	</div>
	<br>
	<div id="alt_Associacao"></div>
	    <div id="nova_associacaoTecnologiaFrequencia" class="secao_conteudo" style="display: none;position:relative;">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Associar Tecnologia/Tipo Frequência</div>
			<div class="conteudo_box_top_esq">
			</div>
			<div class="conteudo_box_top_dir openclose">
			   <img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
			<div>
			<div>
				<div class="conteudo_box_middle">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/addAssociacaoTecnologiaFrequencia.do">
						<div class="conteudo_box_middle_mg">
						    <div class="legendaObrigatorio">(*) Campo Obrigatório</div>
							<div class="fleft">
								<div class="label-form-bold label_required">Tecnologia:<font size="1px" color="#EEB422" valign="center">*</font>&nbsp;</div>
                                <html:select property="idTecnologia" styleId="tecnologiaRef" styleClass="required editavel" style="width:140px;">
                                    <html:option value="">--Selecione--</html:option>
                                    <c:forEach var="comboTecnologiasTO" items="${comboTecnologias}">
                                        <html:option value="${comboTecnologiasTO.idTecnologia}">${comboTecnologiasTO.nmTecnologia}</html:option>
                                    </c:forEach>
                                </html:select>								
							</div>
							<div class="fleft">
								<div class="label-form-bold label_required">&nbsp;Tipo de Frequ&ecirc;ncia:<font size="1px" color="#EEB422" valign="center">*</font></div>
                                <html:select property="idTipoFrequencia" styleId="idTipoFrequencia" styleClass="required editavel" style="width:140px;">
                                    <html:option value="">--Selecione--</html:option>
                                    <c:forEach var="tiposFrequenciaTO" items="${tiposFrequencia}">
                                        <html:option value="${tiposFrequenciaTO.idTipoFrequencia}">${tiposFrequenciaTO.nmTipoFrequencia}</html:option>
                                    </c:forEach>
                                </html:select>									
							</div>
							<br clear="all"/>
							<div style="text-align: right;">
								<input type="button" value="Gravar" class="btNavegacao74" onclick="clearEditando();send(this, 'right_section', null, 'nova_associacaoTecnologiaFrequencia');clearEditando();" title="Gravar Associação"/>
								<input type="button" value="Cancelar" class="btNavegacao74 clearEditavel" onclick="habilitar('nova_associacao');limparForm(this);$('nova_associacaoTecnologiaFrequencia').hide();" title="Cancelar Associação" />
							</div>
						</div>
					</div>
				</html:form>
			</div>
		</div>
	<div class="conteudo_box_bottom"></div>
</div>
</div>
<!-- Para resolver o problema estranho do IE de renderização no caso de conexões lentas -->
<img src="/catalogo/static_server/img/transparent.gif" class="hide" onload="doSuccess();"/>





