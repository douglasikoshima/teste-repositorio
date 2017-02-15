<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="servicos_update" class="secao_conteudo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Alterar Servi&ccedil;os:</div>
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
						 <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/alterarServico.do">
							<html:hidden property="idServico" value="${idServico}"/>
							<html:hidden property="idPlataforma" value="${idPlataforma}"/>							
							<div class="fleft">
								<div class="label-form-bold" style="width: 130px">Nome T&eacute;cnico:</div>
								<html:text property="cdServico" value="${cdServico}" tabindex="8" size="47" readonly="true" styleClass="readonly"></html:text>								
							</div>
							<div class="fleft">
								<div class="label-form-bold" style="width: 130px">Nome Comercial:</div>
								<html:text property="nmComercial" value="${nmComercial}" tabindex="7" size="47" readonly="true" styleClass="readonly"></html:text>								
							</div>
							
							<br clear="all">
							<br clear="all">
							<div class="fleft">
								<div class="label-form-bold label_required" style="width: 130px">Tipo Servi&ccedil;o:<font size="1px" color="#EEB422" valign="center">*</font></div>
								<c:if test="${tpServico == null}">
									<html:select tabindex="9" styleId="select_servico_novo_tipo" property="dscTipoServico" style="width:140px;"  styleClass="editavel required" value="${dscTipoServico}">
										<option value="">-- Selecione --</option>				    				   			   				   
									</html:select> 											
								</c:if>
								
								<c:if test="${tpServico != null}">
								
							    <html:select property="idTipoServico" styleId="select_servico_novo_tipo" tabindex="9" style="width:140px;" styleClass="editavel required" value="${idTpServico}">
								   <html:option value="">-- Selecione --</html:option>
								    <c:forEach items="${tpServico}" var="tpServicoTO">				       
								       <html:option value="${tpServicoTO.idTipoServico}">${tpServicoTO.dscTipoServico}</html:option>				       
								   </c:forEach>				   			   				   
							 	</html:select> 																																	
								</c:if>
							</div>
							
							<div class="fleft">
								<div class="label-form-bold" style="width:245px;">Ativar Wi-Fi:</div>
								<html:select styleId="select_ativa_wifi" property="opAtivaWiFi" style="width:140px;"  styleClass="required editavel" value="${ativaWiFi == null ? '' : ativaWiFi}" >
									<option value="S">Sim</option>
									<option value="N">N&atilde;o</option>				    				   			   				   
								</html:select> 								
							</div>
							
							<br clear="all" /><br clear="all" />
							
							<div class="tabela-padrao">
								<fieldset class="fieldset">
									<legend class="fieldset_legend">Disponibilidade</legend>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold label_required" style="width: 79px;">Cat&aacute;logo:<font size="1px" color="#EEB422" valign="center">*</font></div>
										<c:if test='${inDispLegado eq "S"}'>
											<html:select tabindex="9" styleId="select_indisponibilidade_catalogo" property="inDisponibilidadeCatalogo" style="width:140px;"  styleClass="required" value="${inDispCatalogo}"  >
												<option value="S">Sim</option>
												<option value="N">N&atilde;o</option>				    				   			   				   
											</html:select> 
 										</c:if>
										<c:if test='${inDispLegado eq "N"}'>
										    <html:hidden property="inDisponibilidadeCatalogo" value="{inDispCatalogo}"/>	
										    <html:select tabindex="9" styleId="select_indisponibilidade_catalogo" property="inDisponibilidadeCatalogo" style="width:140px;"  styleClass="required" value="${inDispCatalogo}" disabled="true" >
												<option value="S">Sim</option>
												<option value="N">N&atilde;o</option>				    				   			   				   
											</html:select> 										
										</c:if>
										
									</div>
									
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold" style="width: 240px;">Sistema Origem:</div>
										<html:text property="nmCategoriaLegado" value="${inDispLegado}" tabindex="7" size="40" readonly="true" styleClass="readonly"></html:text>
									</div>
								</fieldset>
							</div>
							<br clear="all"><br clear="all">
						
							<div class="tabela-padrao">
								<fieldset class="fieldset">								
									<legend class="fieldset_legend">Grupo de Serviços</legend>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold label_required" style="width: 79px;">Cat&aacute;logo:<font size="1px" color="#EEB422" valign="center">*</font></div>
										<c:if test="${categoriaList == null}">
											<html:select tabindex="9" styleId="select_nova_categoria" property="idCategoriaCatalogo" style="width:140px;"  styleClass="editavel required" disabled="true" value="${idCategoriaCatalogo}" >
												<option value="">-- Selecione --</option>																    				   			   				  
											</html:select> 											
										</c:if>
										<c:if test="${categoriaList != null}">
											<html:select property="idCategoriaCatalogo" styleId="select_nova_categoria" styleClass="editavel required" style="width:140px;" tabindex="9" value="${idCategoriaCatalogo}">																										
													<html:option value="">-- Selecione --</html:option>			
													<c:forEach var="categoriaListTO" items="${categoriaList}">
														<html:option value="${categoriaListTO.idCategoria}">${categoriaListTO.nmCategoria}</html:option>
													</c:forEach>																		                   <
											</html:select>		
																													    
										</c:if>
									</div>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold" style="width: 240px;">Sistema Origem:</div>
										<html:text property="nmCategoriaLegado" value="${nmCategoriaLegado}" tabindex="7" size="40" readonly="true" styleClass="readonly"></html:text>										
									</div>
								</fieldset>
							</div>
						
							<br clear="all"><br clear="all">
							<div class="tabela-padrao">
								<fieldset class="fieldset">
									<legend class="fieldset_legend">Limite para Ativa&ccedil;&otilde;es</legend>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold label_required" style="width: 180px;">Padr&atilde;o Cat&aacute;logo:<font size="1px" color="#EEB422" valign="center">*</font></div>
										<html:text property="qtdMinAtivacaoCatalogo" value="${qtdMinAtivCatalogo}" tabindex="7" size="10" styleClass="required" onkeypress="return isIntNumber(event)" ></html:text>										
									</div>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold" style="width: 250px;">Padr&atilde;o Sistema Origem:</div>
										<html:text property="qtdMinAtivLegado" value="${qtdMimAtivLegado}" tabindex="7" size="10" readonly="true" styleClass="readonly" ></html:text>										
									</div>
									<br clear="all" /><br clear="all" />
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold label_required" style="width: 180px;">M&aacute;xima Cat&aacute;logo:<font size="1px" color="#EEB422" valign="center">*</font></div>
										<html:text property="qtdMaxAtivacaoCatalogo" value="${qtdMaxAtivCatalogo}" tabindex="7" size="10" styleClass="required" onkeypress="return isIntNumber(event)" ></html:text>										
									</div>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold" style="width: 250px;">M&aacute;xima Sistema Origem:</div>
										<html:text property="qtdMaxAtivLegado" value="${qtdMaxAtivLegado}" tabindex="7" size="10" readonly="true" styleClass="readonly" ></html:text>										
									</div>
								</fieldset>
							</div>

							<br clear="all"><br clear="all">
							<div class="tabela-padrao">
								<fieldset class="fieldset">
									<legend class="fieldset_legend">&Uacute;ltima Altera&ccedil;&atilde;o</legend>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold" style="width: 150px;">Alterado em:</div>
										<html:text property="dtAlteracaoFormat" value="${dtAlteracaoFormat}" tabindex="10" size="30" readonly="false" styleClass="readonly" ></html:text>										
									</div>
									<div class="fleft" style="padding-top: 10px">
										<div class="label-form-bold" style="width: 150px;">Alterado por:</div>
										<html:text property="nmUsuarioAlteracao" value="${nmUsuarioAlteracao}" tabindex="10" size="30" readonly="false" styleClass="readonly" ></html:text>										
									</div>
								</fieldset>
							</div>
							<br clear="all" />
							<div class="barra">
						</div>
						<div style="text-align: right;" colspan="4">
							<html:button property="bt_gravar" value="Gravar" styleClass="btNavegacao74" onClick="clearEditando();send(this, null, null, 'alterar_servicos');clearEditando();" bundle="messages" titleKey="catalogo.paramPlanoServico.gravarAlteracaoServico"/>
							<html:button property="bt_cancelar" value="Cancelar" styleClass="btNavegacao74 clearEditavel" onclick="$('servicos_update').hide()" bundle="messages" titleKey="catalogo.paramPlanoServico.cancelarAlteracaoServico"/>
						</div>
						</html:form> 
					</div>
				</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
	<br>
</div>
