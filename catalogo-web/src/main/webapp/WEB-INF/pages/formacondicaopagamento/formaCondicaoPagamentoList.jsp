<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<catalogo:defaultTemplate titulo="Home Catalogo">	
		<style>
			.removerCampo:hover .removerCampo:visited .removerCampo:link {color: blue;}
			.adicionarCampo:hover .adicionarCampo:visited .adicionarCampo:link {color: blue;}
		</style>
		<jsp:attribute name="headScripts">
			<script type="text/javascript" src="/catalogo/static_server/js/formacondicaopagamento.js"></script>
		</jsp:attribute>
		<script>carregaMenu('mn_parametrizacao_pagamento_forma_condicao');</script>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/search.do" styleId="acaoForm">
			<div class="secao_conteudo">
				<catalogo:contentBox title="Cadastro de Formas e Condições de Pagamento" doubt="true">
					<html:hidden property="idFormaCondicaoPagamento" styleId="idFormaCondicaoPagamento"/>
					<html:hidden property="idFormaPagamento" styleId="idFormaPagamento"/>
					<div class="fleft" style="width:30%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="width: 100px;"><label for="sgMeioPagamentoSearch">Meio Pagamento:</label></div>
						<html:select property="sgMeioPagamentoSearch" styleId="sgMeioPagamentoSearch" styleClass="required" style="width: 140px;">
	                    	<html:option value="">-- Selecione --</html:option>
	                    	<c:forEach	var="meioPagamentoListTO" items="${meioPagamentoList}">
	                    	<html:option value="${meioPagamentoListTO.sgMeioPagamento}">${meioPagamentoListTO.nmMeioPagamento}</html:option>
	                    	</c:forEach>
	                   </html:select>
	                </div>
	                <div class="fleft" style="width:36%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="width: 110px;"><label for=idFormaPagamentoSearch>Forma Pagamento:</label></div>
						<html:select property="idFormaPagamentoSearch" styleId="idFormaPagamentoSearch" styleClass="required" style="width: 180px;">
	                    	<html:option value="">-- Selecione --</html:option>
	                    	<c:forEach	var="formaPagamentoListTO" items="${formaPagamentoList}">
	                    	<html:option value="${formaPagamentoListTO.idFormaPagamento}">${formaPagamentoListTO.nmFormaPagamento}</html:option>
	                    	</c:forEach>
	                    </html:select>
					</div>
					<div class="fleft" style="width:31%; margin-bottom: 5px; margin-left: 5px;"> 
						<div class="label-form-bold label_required" style="width: 60px;"><label for="idBandeiraSearch">Bandeira:</label></div>
						 <html:select property="idBandeiraSearch" styleId="idBandeiraSearch" styleClass="required" style="width: 180px;">
	                    	<html:option value="">-- Selecione --</html:option>
	                    	<c:forEach	var="bandeiraListTO" items="${bandeiraList}">
	                    	<html:option value="${bandeiraListTO.idBandeira}">${bandeiraListTO.nmBandeira}</html:option>
	                    	</c:forEach>
	                    </html:select>
					</div>
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<cata:temPermissao acao="novoFormaCondPagtoTelevenda">
							<html:button property="bt_novo" styleId="botao_novo_form" onClick="create()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/>
							<span>&nbsp;</span>
						</cata:temPermissao>
						<html:button property="bt_pesquisar" styleId="botao_pesquisar_form" onClick="search()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
						<span>&nbsp;</span>	
						<html:button property="bt_limpar" styleId="botao_limpar_form" onClick="clearPage()" styleClass="btNavegacao74" value="Limpar" alt="Limpar" title=""/>
					</div>
				</catalogo:contentBox>
				<c:if test="${formaPagamentoResultList != null}">
					<catalogo:contentBox title="Resultado da Pesquisa">	
						<display:table name="formaPagamentoResultList" id="formaPagamentoTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "search.do" class="tabela-padrao-new tablesorter table_body" >	
							<display:column title="Canal" sortable="true" headerClass="sortable" style="width:80px;" >
								<a href="javascript:editar(${formaPagamentoTO.idFormaPagamento})">${formaPagamentoTO.formaPagtoCanalParamTO.formaPagtoCanalAtndParamTO.canalAtendimentoTO.nmCanal}</a>
							</display:column>
							<display:column title="Meio" sortable="true" headerClass="sortable">
								<a href="javascript:editar(${formaPagamentoTO.idFormaPagamento})">
									${formaPagamentoTO.meioPagamentoTO.nmMeioPagamento}
								</a>
							</display:column>
							<display:column title="Forma" sortable="true" headerClass="sortable">
								<a href="javascript:editar(${formaPagamentoTO.idFormaPagamento})">${formaPagamentoTO.nmFormaPagamento}</a>
							</display:column>
							<display:column title="Desconto" property="formaPagtoCanalParamTO.formaPagtoCanalAtndParamTO.valorDesconto" sortable="true" headerClass="sortable" style="text-align:right; width:60px;" format="{0,number,###,##%}" />
							<display:column title="Condição" style="text-align:center; width:60px;">
								<html:link styleId="idPopUpValores" action="/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/abrirCondicoes.do?idFormaPagamento=${formaPagamentoTO.idFormaPagamento}" onClick="abrirPopup1(this.href);return false;" title="Condições">
									<img alt="Condições de Pagamento" src="/catalogo/static_server/img/botoes/valorCarac.gif" />
								</html:link>
							</display:column>
							<display:column title="Bandeira" style="text-align:center; width:60px;">
								<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/abrirBandeiras.do?idFormaPagamento=${formaPagamentoTO.idFormaPagamento}" onClick="abrirPopup1(this.href);return false;" title="Bandeiras">
									<img src="/catalogo/static_server/img/botoes/Flags.png" alt="Bandeiras" height="15px" width="15px" />
								</html:link>
							</display:column>
							<cata:temPermissao acao="excluirFormaCondPagtoTelevenda">
								<display:column title="Excluir" style="text-align:center; width:60px;"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Forma e Condição de Pagamento" width="15" onclick="remove(${formaPagamentoTO.idFormaPagamento})" style="cursor: pointer;"/></display:column>
							</cata:temPermissao>
							<display:footer >
								<tr>
									<td colspan="8">
									 	<table class="tabelaIcones" cellpadding="0" cellspacing="0">
											<tr>
												<td width="30px;">Ícones:</td>
												<td width="15px;"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Forma e Condição de Pagamento" width="15"/></td>
												<td width="220px" >Excluir Forma e Condição de Pagamento</td>
												<td width="15px;"><img src="/catalogo/static_server/img/botoes/Flags.png" alt="Bandeiras" width="15px" height="15px"/></td>
												<td width="60px" >Bandeiras</td>
												<td width="15px;"><img src="/catalogo/static_server/img/botoes/valorCarac.gif" alt="Condições de Pagamento"/></td>
												<td >Condições de Pagamento</td>
											</tr>
										</table>
									</td>
								</tr>
							</display:footer>
						</display:table>
					</catalogo:contentBox>
				</c:if>
				<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
				<c:if test="${labelError != null}">
					<script>
						generateContentErrorForm("${labelError}")
					</script>
				</c:if>
				<c:if test="${cadastrar}">
					<catalogo:contentBox title="Cadastro de Formas e Condições de Pagamento" requiredFields="true">
						<html:hidden property="idBandeira" styleId="idBandeira"/>
						<div class="fleft" style="width:35%; margin-bottom: 5px; margin-left: 20px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 130px;"><label for="idCanalAtendimento">Canal Atendimento:<font size="1px" color="#EEB422">*</font></label></div>
							<html:select property="idCanalAtendimento" styleId="idCanalAtendimento" styleClass="required" style="width: 150px;">
	                    		<html:option value="">-- Selecione --</html:option>
	                    		<c:forEach	var="canalAtendimentoListTO" items="${canalAtendimentoList}">
	                    		<html:option value="${canalAtendimentoListTO.idCanalAtendimento}">${canalAtendimentoListTO.nmCanal}</html:option>
	                    		</c:forEach>
	                    	</html:select>
		                </div>
		                <div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 20px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 150px;"><label for="sgMeioPagamento">Meio Pagamento:<font size="1px" color="#EEB422">*</font></label></div>
								<html:select property="sgMeioPagamento" styleId="sgMeioPagamento" styleClass="required" style="width: 150px;" onchange="alterarCadastro()">
	                    			<html:option value="">-- Selecione --</html:option>
	                    			<c:forEach	var="meioPagamentoListTO" items="${meioPagamentoList}">
	                    			<html:option value="${meioPagamentoListTO.idMeioPagamento}|${meioPagamentoListTO.sgMeioPagamento}">${meioPagamentoListTO.nmMeioPagamento}</html:option>
	                    			</c:forEach>
	                    		</html:select>
						</div>
						<div class="fleft" style="width:35%; margin-bottom: 5px; margin-left: 20px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 130px;"><label for="idCanalDistribuicao">Canal Distribuição:<font size="1px" color="#EEB422">*</font></label></div>
							<html:select property="idCanalDistribuicao" styleId="idCanalDistribuicao" styleClass="required" style="width: 150px;" onChange="alterarCanalDistribuicao()">
	                    			<html:option value="">-- Selecione --</html:option>
	                    			<c:forEach	var="canalListTO" items="${canalList}">
	                    			<html:option value="${canalListTO.idCanal}">${canalListTO.nmCanal}</html:option>
	                    			</c:forEach>
	                    	</html:select>
						</div>
						<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 20px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 150px;"><label for="vlDesconto">Percentual Desconto:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div>
							<html:text property="vlDesconto" styleId="vlDesconto" style="width:130px; text-align:right;"  maxlength="10" onKeyDown="mascara(this,valor);" onKeyPress="mascara(this,valor);" onKeyUp="mascara(this,valor);"/>
						</div>
						<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 20px;"> 
							<div class="label-form-bold label_required" style="text-align: right; width: 130px;"><label for="formaPagamento">Forma Pagamento:<font size="1px" color="#EEB422">*</font></label></div>
							<html:text property="formaPagamento" styleId="formaPagamento" style="width:150px;" styleClass="required"/>
						</div>
						<div style="display: inline;" id="cadastroPlataforma">   
                            <div class="fleft" style="width:80%; margin-bottom: 5px; margin-left: 20px;"> 
								<div class="label-form-bold label_required" style="text-align: right; width: 130px;"><label for="chackPlataforma">Plataforma:<font size="1px" color="#EEB422">*</font></label></div>
								<div style="float: left;">
                                <c:if test="${plataformaList != null}">
                                    <c:forEach items="${plataformaList}" var="plataformaTO" >
                                        <div class="checkPlataforma${plataformaTO.idPlataforma}" style="width:110px;float:left">
                                           <html:multibox property="checkPlataforma" styleId="checkPlataforma" value="${plataformaTO.idPlataforma}" styleClass="semBorda checkbox"/>${plataformaTO.nmPlataforma}&nbsp;
                                        </div>
                                    </c:forEach>
                                    <br clear="all"/><br clear="all"/>
                                </c:if>
                                </div>
                            </div>
                        </div>
                        <div style="display: none;" id="cadastroCredito">	
							<div class="fleft" style="width:80%; margin-bottom: 5px; margin-left: 20px;"> 
								<div class="label-form-bold label_required" style="text-align: right; width: 130px;"><label for="canalDistribuicao">Bandeiras:<font size="1px" color="#EEB422">*</font></label></div>
								<div style="float: left;">
								<c:if test="${bandeiraList != null}">
									<c:forEach items="${bandeiraList}" var="bandeiraTO" >
										&nbsp;<html:multibox property="checkBandeira" styleId="checkBandeira" value="${bandeiraTO.idBandeira}" />&nbsp;${bandeiraTO.nmBandeira}<br />
									</c:forEach>
								</c:if>
								</div>
							</div>
						</div>
						<div style="display: none;" id="cadastroDebito">	
							<div class="fleft" style="width:35%; margin-bottom: 5px; margin-left: 20px;"> 
								<div class="label-form-bold label_required" style="text-align: right; width: 130px;"><label for="vlParcelaMinima">Parcela Mínima:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div>
								<html:text property="vlParcelaMinima" styleId="vlParcelaMinima" style="text-align:right;" styleClass="required" maxlength="10" onKeyDown="mascara(this,valor);" onKeyPress="mascara(this,valor);" onKeyUp="mascara(this,valor);" />
							</div>
							<div id="contentCodInstituicao" style="display: none;">
							<div class="fleft" style="width:45%; margin-bottom: 5px; margin-left: 20px;"> 
								<div class="label-form-bold label_required" style="text-align: right; width: 150px;"><label for="codInstituicaoFinanceira">Cod. Inst. Financeira:<font size="1px" color="#EEB422">*</font></label></div>
								<html:text property="codInstituicaoFinanceira" styleId="codInstituicaoFinanceira" style="width:130px;" styleClass="required" maxlength="20" />
							</div>
							</div>
							<script>
									alterarCanalDistribuicao();
									alterarCadastro();
							</script>
						</div>
						<div class="fleft" style="width:90%; margin-bottom: 5px; margin-left: 20px;" id="contentForm"> 
							<fieldset style="padding: 10px 5px 10px 5px; float: left;">
								<legend style="font-weight: bold; color: #0364CB; font-size: 11px;">Condição Pagamento</legend>
								<c:if test="${condicaoPagamentoList == null}">
									<div class="condicoes" style="width: 770px; float: left; margin-top: 5px;"> 
										<p class="campoCondicao" style="float: left; display: inline; height: 20px;"> 
											<input type="hidden" id="idCondicoesPagto" name="idCondicoesPagto" value="" />
											<b>Quantidade de Parcelas:</b>
											<input type="text" class="required" name="condicoesPagto[]" maxlength="10" style="width: 50px;" onKeyDown="mascara(this,integer);" onKeyPress="mascara(this,integer);" onKeyUp="mascara(this,integer);"  />&nbsp;&nbsp;
											<b>Sigla SAP:</b>
											<input type="text" class="required" name="siglasSAP[]" maxlength="255" style="width: 60px;" />&nbsp;&nbsp;
											<b>Descrição:</b>
											<input type="text" class="required" name="descricoes[]" maxlength="255" style="width: 250px;" />&nbsp;&nbsp;
											<cata:temPermissao acao="removerFormaCondPagtoTelevenda"><a href="#" class="removerCampo">Remover&nbsp;</a></cata:temPermissao>
										</p>
										<cata:temPermissao acao="adicionarFormaCondPagtoTelevenda">
										<p style="float: left; display: inline; height: 20px; padding-top: 0px; margin-top: 4px;"> 
											<a href="#" class="adicionarCampo">/&nbsp;Adicionar</a>
										</p>
										</cata:temPermissao>
									</div> 
								</c:if>
								<c:if test="${condicaoPagamentoList != null}">
									<div class="condicoes" style="width: 770px; float: left; margin-top: 5px;"> 
										<c:forEach items="${condicaoPagamentoList}" var="condicaoPagamentoTO" >
											<p class="campoCondicao" style="float: left; display: inline; height: 20px;"> 
												<input type="hidden" id="idCondicoesPagto" name="idCondicoesPagto" value="${condicaoPagamentoTO.idCondicaoPagamento}" />
												<b>Quantidade de Parcelas:</b>
												<input type="text" class="required" name="condicoesPagto[]" maxlength="10" style="width: 50px;" value="${condicaoPagamentoTO.qtParcelas}" onKeyDown="mascara(this,integer);" onKeyPress="mascara(this,integer);" onKeyUp="mascara(this,integer);" />&nbsp;&nbsp;
												<b>Sigla SAP:</b>
												<input type="text" class="required" name="siglasSAP[]" maxlength="255" style="width: 60px;" value="${condicaoPagamentoTO.sgCondicaoPagamento}" />&nbsp;&nbsp;
												<b>Descrição:</b>
												<input type="text" class="required" name="descricoes[]" maxlength="255" style="width: 250px;" value="${condicaoPagamentoTO.nmCondicaoPagamento}" />&nbsp;&nbsp;
												<cata:temPermissao acao="removerFormaCondPagtoTelevenda">
													<a href="#" class="removerCampo">Remover&nbsp;</a>
												</cata:temPermissao>
											</p>
										</c:forEach>
										<cata:temPermissao acao="adicionarFormaCondPagtoTelevenda">
											<p style="float: left; display: inline; height: 20px; padding-top: 0px; margin-top: 4px;"> 
												<a href="#" class="adicionarCampo">/&nbsp;Adicionar</a>
											</p> 
										</cata:temPermissao>
									</div> 
								</c:if>
							</fieldset>
						</div>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<html:button styleId="botao_cancelar_form" property="bt_cancelar" onClick="cancel()" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title=""/><span>&nbsp;</span> 
							<cata:temPermissao acao="gravarFormaCondPagtoTelevenda">
								<html:button styleId="botao_gravar_form" property="bt_gravar" onClick="save()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
							</cata:temPermissao>
						</div>   
					</catalogo:contentBox>
				</c:if>
			</div>	
		</html:form>
</catalogo:defaultTemplate>