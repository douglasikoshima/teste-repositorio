<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="secao_conteudo">

	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Alterar Cabe&ccedil;alho de Matriz Oferta:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/alterarCabecalhoMatrizOferta.do">
					<fmt:bundle basename="catalogoprs_messages" >
					<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
					<html:hidden property="idMatrizOferta" styleId="id_matriz_oferta" value="${idMatrizOferta}" />
					<html:hidden property="inicialVigencia" styleId="hidden_vigenciaInicial" value="${vigenciaInicial}"/>
					<html:hidden property="finalVigencia" styleId="hidden_vigenciaFinal" value="${vigenciaFinal}"/>
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
		            <div class="link_manual" title="Dúvida">
		            	<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
		            	<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank">
		            		<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/>
		            	</html:link>
					</div>
					<div style="height:10px;"></div>
	
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 120px">Nome da Matriz:<font size="1px" color="#EEB422" valign="center">*</font></div>						
						<html:text tabindex="6" property="nmMatrizOferta" value="${nmMatrizOferta}" styleClass="required" size="50" styleId="textbox_nmMatriz"/>
					</div>	
						
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 120px">Tipo de Matriz:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select tabindex="7" styleId="select_tipoMatriz" styleClass="required" style="width: auto" property="idMatrizOfertaTipo" value="${idTipoMatrizOferta}">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="tipoMatrizTO" items="${tipoMatriz}" >
								<html:option value="${tipoMatrizTO.idMatrizOfertaTipo}">${tipoMatrizTO.nmMatrizOfertaTipo}</html:option>
							</c:forEach>
						</html:select>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 120px; float: left;">Vig&ecirc;ncia Inicial:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<div class="data_nome_campo hide">Vig&ecirc;ncia:</div>
						<html:text property="dtVigenciaInicial" value="${vigenciaInicial}" tabindex="8" size="20" maxlength="10" styleClass="data required" onKeyPress="return isFormatDate(event, this, '/')"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 270px; float: left;">Vig&ecirc;ncia Final:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<div class="data_nome_campo hide">Vig&ecirc;ncia:</div>
						<html:text property="dtVigenciaFinal" value="${vigenciaFinal}" tabindex="9" size="20" maxlength="10" styleClass="data required" onKeyPress="return isFormatDate(event, this, '/')"/>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div>
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 125px; float: left;">Plataforma:<font size="1px" color="#EEB422" valign="center">* &nbsp;</font></div>
							<div id="lista_plataforma" class="box-dashed">
								<div>
									<html:checkbox tabindex="10" property="ckeckbox_plataformaAll" styleId="ckeckbox_plataformaAll" styleClass="semBorda"  onclick="selectTodosCheckbox('lista_plataforma', '.checkbox_plataforma', this.checked);"/><b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>									
								</div>
								<logic:iterate id="plataformaListaTO" property="plataformaLista" name="cadastroCabecalhoMatrizOfertaForm">
									<html:multibox property="idsPlataforma" onClick="verificarSelecaoAll(this, 'ckeckbox_plataformaAll')" styleId="idPlataforma_${plataformaListaTO.idPlataforma}"  value="${plataformaListaTO.idPlataforma}" styleClass="semBorda checkbox_plataforma">
									
										<bean:write bundle="messages" name="plataformaListaTO"  property="idPlataforma" />
										
									</html:multibox>
										<bean:write bundle="messages" name="plataformaListaTO" property="nmPlataforma"/><br>
								</logic:iterate>
							</div>
						</div>
						
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 266px; float: left;">Canal:<font size="1px" color="#EEB422" valign="center">* &nbsp;</font></div>
							<div id="lista_canal" class="box-dashed">
								<div>
									<html:checkbox tabindex="12" property="ckeckbox_canalAll" styleId="ckeckbox_canalAll" styleClass="semBorda" onclick="selectTodosCheckbox('lista_canal', '.checkbox_canal', this.checked);"/><b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>
								</div>
								<logic:iterate id="listaCanalTO" property="listaCanal" name="cadastroCabecalhoMatrizOfertaForm">
									<html:multibox property="idsCanal" onClick="verificarSelecaoAll(this, 'ckeckbox_canalAll')" styleId="idCanal_${listaCanalTO.idCanal}" value="${listaCanalTO.idCanal}" styleClass="semBorda checkbox_canal">
										
											<bean:write bundle="messages" name="listaCanalTO"  property="idCanal" />
											
									</html:multibox>
											<bean:write bundle="messages" name="listaCanalTO" property="nmCanal"/><br>
									
								</logic:iterate>
							</div>
						</div>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 125px; float: left;">Tipo de&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>Opera&ccedil;&atilde;o:<font size="1px" color="#EEB422" valign="center">* &nbsp;</font></div>
						<div id="lista_tpOperacao" class="box-dashed">
							<div>
								<html:checkbox tabindex="11" property="ckeckbox_tpOperacaoAll" styleId="ckeckbox_tpOperacaoAll" styleClass="semBorda" onclick="selectTodosCheckbox('lista_tpOperacao', '.checkbox_tpOperacao', this.checked);"/><b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>
							</div>
							<logic:iterate id="tipoOperacaoListaTO" property="tipoOperacaoLista" name="cadastroCabecalhoMatrizOfertaForm">
								<html:multibox property="idsTipoOperacao" onClick="verificarSelecaoAll(this, 'ckeckbox_tpOperacaoAll')" styleId="idTpOperacao_${tipoOperacaoListaTO.idTipoOperacao}" value="${tipoOperacaoListaTO.idTipoOperacao}" styleClass="semBorda checkbox_tpOperacao">
									
										<bean:write bundle="messages" name="tipoOperacaoListaTO"  property="idTipoOperacao" />
										
								</html:multibox>
										<bean:write bundle="messages" name="tipoOperacaoListaTO" property="nmOperacao"/><br>
							
							</logic:iterate>
						</div>
					</div>
					
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
					    <span>&nbsp;</span>
					    <html:link action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/pesquisarListaVariaveisMatrizOferta.do?id_matriz_oferta=${idMatrizOferta}&pagina_solicitada=${no_pagina}" styleId="botao_pesquisar_variaveis_matriz_oferta" onClick="if(abrirLink('resultado_pesquisa_variaveis', this.href, 'div_returno_erro_alteracao')){clearAndShow('resultado_pesquisa_variaveis')}return false">
					    	<html:button bundle="messages" tabindex="14" property="btn_variaveis" styleClass="btNavegacao74" onMouseDown="$('pagina_solicitada').value='1';" value="Vari&aacute;veis" altKey="catalogo.matrizOferta.variaveis.Pesquisar" titleKey="catalogo.matrizOferta.variaveis.Pesquisar" />
					    </html:link>
					    <span>&nbsp;</span>
					    <html:button bundle="messages" tabindex="13" property="btn_gravar" styleId="botao_gravar_cabecalho_matriz_oferta" onClick="if(send(this, 'div_alterar_cabecalho_matriz', null, 'div_returno_erro_alteracao')){};return false" styleClass="btNavegacao74" value="Gravar" altKey="catalogo.matrizOferta.cabecalho.Alterar" titleKey="catalogo.matrizOferta.cabecalho.Alterar"/>
					</div>
					</fmt:bundle>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
<br/>
<div id="div_erro_variaveis_elegibilidade" style="position: relative;"></div>
<div id="resultado_pesquisa_variaveis" style="display: none; position: relative;"></div>