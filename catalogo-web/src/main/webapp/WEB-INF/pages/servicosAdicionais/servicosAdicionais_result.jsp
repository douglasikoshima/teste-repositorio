<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/search.do" styleId="acaoForm" >
<div id="resultadoServicosAdicionais">
<script type="text/javascript" src="/catalogo/static_server/js/servicosAdicionais.js"></script>
<c:if test="${servicosAdicionaisTOList != null}">
	<catalogo:contentBox title="Resultado da Pesquisa" doubt="false" requiredFields="false">

		<display:table name="servicosAdicionaisTOList" id="displayServicosAdicionais" pagesize="15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="ordenar.do"
			class="tabela-padrao-new tablesorter table_body">
					<display:column title="Nome do Servi&ccedil;o" sortable="true" headerClass="sortable">
						<a href="javascript:visualizar('${displayServicosAdicionais.idServicosAdicionais}')">${displayServicosAdicionais.nomeServico}</a>
					</display:column>
			<display:column property="solicitacaoComercial.nmSolicitacaoComercial" title="Nome da Sol. Comercial" sortable="true" headerClass="sortable" />
			<display:column property="tempoVigencia" title="Tempo de Vigência (Meses)" sortable="true" headerClass="sortable" />
			<display:column property="periodoVigencia" title="Período Vigência" sortable="true" headerClass="sortable" />
					<display:column title="Dependente" sortable="false" headerClass="sortable"> 
				    	<div class="clean icon">
							<div class=${displayServicosAdicionais.dependente ? "icondep" : ""}></div>
						</div>
					</display:column> 
					<cata:temPermissao acao="excluirServicoAdicional">
						<c:if test="${!displayServicosAdicionais.dependente}">
							<display:column title="Excluir" headerClass="sortable">
								<div class="clean" id="botaoExcluir"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas" alt="Excluir" onClick="excluirServicosAdicionais('${displayServicosAdicionais.idServicosAdicionais}')"></div>
							</display:column>						
						</c:if>
					</cata:temPermissao>
		</display:table>
	</catalogo:contentBox>
</c:if> 
<!-- Carregar combo de servicos --> 
<c:if test="${servicoTOList != null}">
	<html:select property="comboNomeServico" styleId="comboNomeServico" style="width: 340px !important;" onchange="carregarSolicitacaoList()" styleClass="data changeable">
		<html:option value="0">--Selecione--</html:option>
		<c:forEach var="servicoTO" items="${servicoTOList}">
			<html:option value="${servicoTO.idServico}|${servicoTO.cdServico}">${servicoTO.nomeServico}</html:option>
		</c:forEach>
	</html:select>
</c:if> 
<!-- Carregar combo de Solicitacao Comercial --> 
<c:if test="${SolicitacaoTOList != null}">
	<html:select property="comboNomeSolicitacao" styleId="comboNomeSolicitacao" style="width: 150px !important;" onchange="carregarTempoVigencia()" styleClass="data changeable">
		<html:option value="0">--Selecione--</html:option>
		<c:forEach var="solicitacaoTO" items="${SolicitacaoTOList}">
			<html:option value="${solicitacaoTO.idSolicitacaoComercial}|${solicitacaoTO.cdSolicitacaoComercial}|${solicitacaoTO.prazoMaximoVigencia}">${solicitacaoTO.nmSolicitacaoComercial}</html:option>
		</c:forEach>
	</html:select>
</c:if> 
<c:if test="${servicosAdicionaisTO != null}">
    <catalogo:contentBox title="Adicionar Serviço Adicional" doubt="true" requiredFields="true">
	    <div class="boxServPromo clear">
		    <div class="boxline">
				<div class="linerow bold">Código do Serviço:</div>
				<div class="linerow clear">
					<input id="idServico" disabled="true" type="text" value="${servicosAdicionaisTO.cdServico}"/>
				</div>
            </div>
            <div class="boxline">
                <div class="linerow bold">Tipo do Serviço:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboTipoServico">
				    <input id="tipoServico" disabled="true" type="text" value="${servicosAdicionaisTO.nmTipoServico}"/>
                </div>
            </div>
            <div class="boxline" style="width:380px !important">
				<div class="linerow bold">Nome do Serviço:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboNomeServico" style="width:370px !important">
				    <input id="nmServico" disabled="true" type="text" value="${servicosAdicionaisTO.nomeServico}"/>
				</div>
			</div>
			<div class="boxline">
				<div class="linerow bold">Código da Solicitação:</div>
				<div class="linerow clear">
					<input type="text" id="codSolicitacao" disabled="true" value="${servicosAdicionaisTO.cdSolicitacaoComercial}"/>
				</div>
			</div>
			<div class="boxline">
				<div class="linerow bold">Nome da Solicitação:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboNomeSolicitacao">
				    <input id="nmSolicitacaoComercial" disabled="true" type="text" value="${servicosAdicionaisTO.nomeSolicitacaoComercial}"/>
				</div>
			</div>
			<div class="boxline">
	 			<div class="linerow bold">Tempo de Vigência (Meses):<span class="required">*</span></div>
				<div class="linerow clear">
				    <input id="tempoVigencia" disabled="true" type="text" value="${servicosAdicionaisTO.tempoVigencia}"/>
			    </div>
			</div>
			<div class="clear"></div>
			<div class="boxline">
				<div class="linerow bold">Data Inicio de Vig&#234;ncia<span class="required">*</span></div>
				<div class="linerow clear">
					<input type="text" id="dtInicio" disabled="disabled" value="${servicosAdicionaisTO.dtInicioFormatado}" />
				</div>
			</div>
			<div class="boxline">
				<div class="linerow bold">Data Fim de Vig&#234;ncia:</div>
				<div class="linerow clear">
					<input type="text" id="dtFim" disabled="disabled" value="${servicosAdicionaisTO.dtFimFormatado}" />
				</div>
			</div>
				<!-- BOTÃO ADICIONAR NOVOS ITENS !-->
			<div class="barra"></div>
			<cata:temPermissao acao="adicionarServicoAdicional">
				<div class="botao">
					<html:button property="botao_gravar" styleId="botao_gravar" onClick="novo()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/>
				</div>
			</cata:temPermissao>
		</div>
	</catalogo:contentBox>
</c:if>
<c:if test='${novo eq "novo"}'>
<catalogo:contentBox title="Adicionar Serviço Adicional" doubt="true" requiredFields="true">
				<div class="boxServPromo clear">
				<div class="boxline">
				<div class="linerow bold">Código do Serviço:</div>
				<div class="linerow clear"><input id="idServico" disabled="true" type="text" /></div>
				</div>
				<div class="boxline">
				<div class="linerow bold">Tipo do Serviço:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboTipoServico">
				<html:select property="comboTipoServico" styleId="comboTipoServico" onchange="carregarNomeServicoList()" styleClass="data changeable">
					<html:option value="0">--Selecione--</html:option>
					<c:forEach var="tipoServicoTO" items="${tipoServicoTOList}">
						<html:option value="${tipoServicoTO.idTipoServico}">${tipoServicoTO.nmTipoServico}</html:option>
					</c:forEach>
				</html:select></div>
				</div>
				<div class="boxline" style="width:380px !important">
				<div class="linerow bold">Nome do Serviço:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboNomeServico" style="width:370px !important">
				<html:select styleClass="data" property="comboNomeServico" styleId="comboNomeServico" disabled="disabled" style="width: 340px !important;">
					<html:option value="0">--Selecione--</html:option>
				</html:select></div>
				</div>
				<div class="boxline">
				<div class="linerow bold">Código da Solicitação:</div>
				<div class="linerow clear"><input type="text" id="codSolicitacao" disabled="true" /></div>
				</div>
				<div class="boxline">
				<div class="linerow bold">Nome da Solicitação:<span class="required">*</span></div>
				<div class="linerow clear" id="divComboNomeSolicitacao">
				<html:select styleClass="data" property="comboNomeSolicitacao" styleId="comboNomeSolicitacao" disabled="disabled" style="width: 150px !important;">
					<html:option value="0">--Selecione--</html:option>
				</html:select></div>
				</div>
				<div class="boxline">
				<div class="linerow bold">Tempo de Vigência (Meses):<span class="required">*</span></div>
				<div class="linerow clear"><input onkeypress='return SomenteNumero(event)' type="text" id="idTempoVigencia" disabled="true" /></div>
				</div>

				<div class="clear"></div>

				<div class="boxline">
				<div class="linerow bold">Data Inicio de Vig&#234;ncia<span class="required">*</span></div>
				<div class="linerow clear"><input type="text" id="dataVigenteInicio" value="" /></div>
				</div>

				<div class="boxline">
				<div class="linerow bold">Data Fim de Vig&#234;ncia:</div>
				<div class="linerow clear"><input type="text" id="dataVigenteFim" value="" /></div>
				</div>

				<!-- BOTÃO ADICIONAR NOVOS ITENS !-->
				<div class="barra"></div>
				<cata:temPermissao acao="adicionarServicoAdicional">
					<div class="botao"><netui:button Id="botao_gravar" type="button" onClick="gravarServicosAdicionais()" styleClass="btNavegacao74" value="Adicionar" alt="Adicionar Serviços" title="" /></div>
				</cata:temPermissao></div></div>
			</catalogo:contentBox>
</c:if>
<html:hidden property="${msg_type}" styleId="${msg_type}" value="${msg_info}" /></div>
</html:form>
