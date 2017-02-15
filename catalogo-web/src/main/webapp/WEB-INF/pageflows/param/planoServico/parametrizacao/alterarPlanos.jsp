<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

<div id="plano_update" class="secao_conteudo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Alterar Plano:</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
				 alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
						<div class="legendaObrigatorio">(*) Campo Obrigatório</div>
						<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/alterarPlanos.do">
							<html:hidden property="idPlano" value="${idPlano}"/>
							<html:hidden property="idPlataforma" value="${idPlataforma}"/>
							<div class="fleft">
								<div class="label-form-bold" style="width: 115px">Nome T&eacute;cnico:</div>
								<html:text property="cdCodigo" value="${cdCodigo}" tabindex="8" size="40" readonly="true" styleClass="readonly"/>
							</div>
							<div class="fleft">
								<div class="label-form-bold" style="width: 115px">Nome Comercial:</div>
								<html:text property="nmComercial" value="${nmComercial}" tabindex="7" size="40" readonly="true" styleClass="readonly"/>
							</div>
							<div class="fleft">
								<div class="label-form-bold" style="width: 80px">Titular:</div> 
								<html:checkbox property="indTitDep" tabindex="10" value="${indTitDep}" disabled="true" styleClass="semBorda belongsToForm checkbox_modelo"  />
							</div>
							<br clear="all"><br clear="all">
							<div class="fleft">
								<div class="label-form-bold" style="width: 115px">Tipo de Plano:</div>
								<c:choose>
									<c:when test="${tipoPlano == null}">
										<html:select property="idTpPlano" tabindex="9" styleId="select_servico_novo_tipo" style="width: 214px;">
											<html:option value="">-- Selecione --</html:option>
										</html:select>
									</c:when>
									<c:otherwise>
										<html:select property="idTpPlano" tabindex="9" style="width: 214px;" value="${idTipoPlano}" styleId="select_plano_novo_tipo">
											<html:option value="">-- Selecione --</html:option>
											<c:forEach var="tipoPlanoTo" items="${tipoPlano}">
												<html:option value="${tipoPlanoTo.idTipoPlano}">${tipoPlanoTo.nmTipoPlano}</html:option>
											</c:forEach>
										</html:select>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="fleft">
								<div class="label-form-bold" style="width:120px;">Ativar Wi-Fi:</div>
								<html:select property="opAtivaWiFi" styleId="select_ativa_wifi" style="width:140px;" value="${ativaWiFi}">
									<html:option value="S">Sim</html:option>
									<html:option value="N">Não</html:option>
								</html:select>
							</div>
							
							<br clear="all"><br clear="all">
							<div style="width: 100%; text-align: center; padding: 0px 42px 0px 35px">
								<div style="width: 50%; float: left;">
									<fieldset style="width: 97%; text-align: left; border: 1px solid #7D7D7D; padding: 0px 10px 15px 10px">
										<legend class="fieldset_legend">Limite de dependentes</legend>
										<div class="fleft" style="padding-top: 10px">
											<div class="label-form-bold label_required" style="width: 70px;">Cat&aacute;logo:<font size="1px" color="#EEB422" valign="center">*</font></div>
											<html:text property="qtdMaxDependenteCatalogo" value="${qtdMaxDependenteCatalogo}" tabindex="7" size="10" styleClass="required" onKeyPress="return isIntNumber(event)"/>
										</div>
										<div class="fleft" style="padding-top: 10px">
											<div class="label-form-bold" style="width: 110px;">Sistema Origem:</div>
											<html:text property="qtdMaxDependenteLegado" value="${qtdMaxDependenteLegado}" tabindex="7" size="10" readonly="true" styleClass="readonly" />
										</div>
									</fieldset>
								</div>
							
								<div style="width: 50%; float: left;">
									<fieldset style="width: 97%; text-align: left; border: 1px solid #7D7D7D; padding: 0px 10px 15px 10px">
										<legend class="fieldset_legend">Disponibilidade</legend>
										<div class="fleft" style="padding-top: 10px">
											<div class="label-form-bold label_required" style="width: 70px;">Cat&aacute;logo:<font size="1px" color="#EEB422" valign="center">*</font></div>
											<c:if test='${inDispLegado eq "Sim"}'>
											<html:select property="inDisponibilidadeCatalogo" tabindex="9" style="width:60px;" value="${inDispCatalogo}" styleId="select_indisponibilidade_catalogo" styleClass="required">
												<html:option value="S">Sim</html:option>
												<html:option value="N">Não</html:option>
											</html:select>
											</c:if>
											<c:if test='${inDispLegado eq "Não"}'>
											<html:select property="inDisponibilidadeCatalogo" tabindex="9" style="width:60px;" value="${inDispCatalogo}" styleId="select_indisponibilidade_catalogo"  disabled="true" >
												<html:option value="S">Sim</html:option>
												<html:option value="N">Não</html:option>
											</html:select>
										</c:if>
										</div>
										<div class="fleft" style="padding-top: 10px">
											<div class="label-form-bold" style="width: 110px;">Sistema Origem:</div>
											<html:text property="indDisponibilidadeLegado" value="${inDispLegado}" tabindex="7" size="10" readonly="true" styleClass="readonly"/>
										</div>
									</fieldset>
								</div>
							</div>
							
							<br clear="all"><br clear="all">
							<div class="tabela-padrao">
								<fieldset class="fieldset">
									<legend class="fieldset_legend">&Uacute;ltima Altera&ccedil;&atilde;o</legend>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold" style="width: 150px;">Alterado em:</div>
										<html:text property="dtUltimaAlteracaoFormat" value="${dtUltimaAlteracaoFormat}" tabindex="10" size="30" readonly="false" styleClass="readonly"/>
									</div>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold" style="width: 150px;">Alterado por:</div>
										<html:text property="nmUsuarioAlteracao" value="${nmUsuarioAlteracao}" tabindex="10" size="30" readonly="false" styleClass="readonly" />
									</div>
								</fieldset>
							</div>
						<br clear="all" />
						<div class="barra">
						</div>
						<div style="text-align: right;" colspan="4">
 							<html:button property="btn_gravar" value="Gravar" styleClass="btNavegacao74" onClick="clearEditando();send(this, null, null, 'alterar_servicos');clearEditando();" bundle="messages" titleKey="catalogo.paramPlanoServico.gravarAlteracaoPlano"/>
							<html:button property="btn_cancelar" value="Cancelar" styleClass="btNavegacao74 clearEditavel" onclick="$('plano_update').hide()" bundle="messages" titleKey="catalogo.paramPlanoServico.cancelarAlteracaoPlano"/>
						</div>
						</html:form>
					</div>
				</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
	<br>
</div>
