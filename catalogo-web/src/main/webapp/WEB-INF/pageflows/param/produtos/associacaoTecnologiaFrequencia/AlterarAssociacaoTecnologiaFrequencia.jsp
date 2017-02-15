<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

   <div id="alterar_associacaoTecnologiaFrequencia" class="secao_conteudo">
			<div class="conteudo_box_top">
				<div class="conteudo_box_top_center">Associar Tecnologia/Tipo Frequência</div>
				<div class="conteudo_box_top_esq">
				</div>
				<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
			</div>
			<div>
				<div>
					<div class="conteudo_box_middle">
					<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/alterarFrequencia.do">
						<html:hidden property="idTecnologiaTipoFrequencia" styleId="idTecnologiaTipoFrequencia" value="${tecnologiaTpFrequencia.idTecnologiaTpFrequencia}"/>
						<div class="conteudo_box_middle_mg">
						<br>
							<div class="fleft">
								<div class="label-form-bold label_required">Tecnologia:<font size="1px" color="#EEB422" valign="center">*</font>&nbsp;</div>
                                <html:select property="idTecnologia" styleId="idTecnologia" styleClass="required editavel" style="width:140px;">
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
								<input type="button" value="Gravar" class="btNavegacao74" onclick="clearEditando();send(this, 'right_section', null, 'alterar_associacaoTecnologiaFrequencia');setEditando();" title="Gravar Associa&ccedil;&atilde;o"/>
								<input type="button" value="Cancelar" class="btNavegacao74" onclick="$('alt_Associacao').hide()" title="Cancelar Altera&ccedil;&atilde;o de Associa&ccedil;&atilde;o"/>
							</div>
						</div>
					
					</div>
				</html:form>
			</div>
		</div>
	<div class="conteudo_box_bottom"></div>
</div>
