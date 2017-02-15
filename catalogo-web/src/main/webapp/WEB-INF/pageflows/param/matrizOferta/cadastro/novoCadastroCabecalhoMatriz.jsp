<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="secao_conteudo">
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Cadastro Cabe&ccedil;alho de Matriz Oferta:</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/salvarNovoCabecalhoMatrizOferta.do">			
					<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
					<!-- <netui:error key="tipoProduto"/> -->
					<html:errors name="tipoProduto" /> 
					<div style="height:15px;"></div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 120px">Nome da Matriz:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:text tabindex="6" property="nmMatrizOferta" styleClass="required" size="50" styleId="textbox_nmMatriz" />
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
						<html:text property="dtVigenciaInicial" tabindex="8" size="20" maxlength="10" styleClass="data required" onKeyPress="return isFormatDate(event, this, '/')"/>
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 270px; float: left;">Vig&ecirc;ncia Final:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<div class="data_nome_campo hide">Vig&ecirc;ncia:</div>
						<html:text property="dtVigenciaFinal" tabindex="9" size="20" maxlength="10" styleClass="data required" onKeyPress="return isFormatDate(event, this, '/')"/>
					</div>
					<br clear="all"/><br clear="all"/>
					<div>
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 125px; float: left;">Plataforma:<font size="1px" color="#EEB422" valign="center">* &nbsp;</font></div>
							<div id="lista_plataforma" class="box-dashed">
								<div>
									<html:checkbox tabindex="10" property="ckeckbox_plataformaAll" styleId="ckeckbox_plataformaAll" styleClass="semBorda"  onclick="selectTodosCheckbox('lista_plataforma', '.checkbox_plataforma', this.checked);"/>
										<b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>
								</div>
								<c:if test="${not empty cadastroCabecalhoMatrizOfertaForm.plataformaLista}">
									<logic:iterate id="plataformaListaTO" property="plataformaLista" name="cadastroCabecalhoMatrizOfertaForm">
										<html:multibox property="idsPlataforma" onClick="verificarSelecaoAll(this, 'ckeckbox_plataformaAll')" styleId="idPlataforma_${plataformaListaTO.idPlataforma}"  value="${plataformaListaTO.idPlataforma}" styleClass="semBorda checkbox_plataforma">
										
											<bean:write bundle="messages" name="plataformaListaTO"  property="idPlataforma" />
											
										</html:multibox>
											<bean:write bundle="messages" name="plataformaListaTO" property="nmPlataforma"/><br>
									</logic:iterate>
								</c:if>
							</div>
						</div>
					
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 266px; float: left;">Canal:<font size="1px" color="#EEB422" valign="center">* &nbsp;</font></div>
							<div id="lista_canal" class="box-dashed">
								<div>
									<html:checkbox tabindex="12" property="ckeckbox_canalAll" styleId="ckeckbox_canalAll" styleClass="semBorda" onclick="selectTodosCheckbox('lista_canal', '.checkbox_canal', this.checked);"/>
										<b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>
								</div>
								<c:if test="${not empty cadastroCabecalhoMatrizOfertaForm.listaCanal}">
									<logic:iterate id="listaCanalTO" property="listaCanal" name="cadastroCabecalhoMatrizOfertaForm">
										<html:multibox property="idsCanal" onClick="verificarSelecaoAll(this, 'ckeckbox_canalAll')" styleId="idCanal_${listaCanalTO.idCanal}" value="${listaCanalTO.idCanal}" styleClass="semBorda checkbox_canal">
											
												<bean:write bundle="messages" name="listaCanalTO"  property="idCanal" />
												
										</html:multibox>
												<bean:write bundle="messages" name="listaCanalTO" property="nmCanal"/><br>
										
									</logic:iterate>
								</c:if>
							</div>
						</div>
					</div>					
					<br clear="all"/><br clear="all"/>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 125px; float: left;">Tipo de Opera&ccedil;&atilde;o:<font size="1px" color="#EEB422" valign="center">* &nbsp;</font></div>
						<div id="lista_tpOperacao" class="box-dashed">
							<div>
								<html:checkbox tabindex="11" property="ckeckbox_tpOperacaoAll" styleId="ckeckbox_tpOperacaoAll" styleClass="semBorda" onclick="selectTodosCheckbox('lista_tpOperacao', '.checkbox_tpOperacao', this.checked);"/>
									<b><label style="padding: 0px 0px 2px 3px;">TODOS</label></b>
							</div>
							<c:if test="${not empty cadastroCabecalhoMatrizOfertaForm.tipoOperacaoLista}">
								<logic:iterate id="tipoOperacaoListaTO" property="tipoOperacaoLista" name="cadastroCabecalhoMatrizOfertaForm">
									<html:multibox property="idsTipoOperacao" onClick="verificarSelecaoAll(this, 'ckeckbox_tpOperacaoAll')" styleId="idTpOperacao_${tipoOperacaoListaTO.idTipoOperacao}" value="${tipoOperacaoListaTO.idTipoOperacao}" styleClass="semBorda checkbox_tpOperacao">
										
											<bean:write bundle="messages" name="tipoOperacaoListaTO"  property="idTipoOperacao" />
											
									</html:multibox>
											<bean:write bundle="messages" name="tipoOperacaoListaTO" property="nmOperacao"/><br>
								
								</logic:iterate>
							</c:if>
						</div>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
					    <html:button bundle="messages" tabindex="5" property="btn_limpar" styleId="botao_limpar_form" onClick="limparForm(this);return false;" styleClass="btNavegacao74" value="Limpar" altKey="catalogo.matrizOferta.form.Limpar" titleKey="catalogo.matrizOferta.form.Limpar" />
					    <span>&nbsp;</span>
					    <html:button bundle="messages" tabindex="4" styleId="botao_gravar_cabecalho_matriz_oferta" property="btn_gravar" onClick="if(send(this, 'div_cadastro_novo_cabecalho', null, 'div_returno_erro')){$('resultado_pesquisa').hide()};return false" styleClass="btNavegacao74" value="Gravar" altKey="catalogo.matrizOferta.cabecalho.Gravar" titleKey="catalogo.matrizOferta.cabecalho.Gravar" />
					</div>
				</html:form>
			</div>
		</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>