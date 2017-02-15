<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:blankTemplate titulo="Home Catalogo">
    <jsp:attribute name="headScripts">
      <script type="text/javascript" src="/catalogo/static_server/js/relacionamentoServicoFixa.js"></script>
    </jsp:attribute>
    <jsp:body>
        <html:form action="/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/search.do" styleId="relacionamentoServicoFixaForm" >
        	<c:if test="${relacionamentoServicoFixaTOList != null}">
				<div class="secao_conteudo">
	            	<div id="resultadoRelacionamento">
		            	<br/>
		            	<c:if test="${msg_success != null}">
		            		<div id="msg_success" class="box_middle_center_conteudo" style="color: #299B00">${msg_success}</div>
		            	</c:if>
		            	<c:if test="${msg_error != null}">
		            		<div id="msg_error" class="box_middle_center_conteudo" style="color:red !important">${msg_error}</div>
		            	</c:if>		            	
		            	<br/>
						<display:table name="relacionamentoServicoFixaTOList" id="relacionamentoServicoFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "search.do" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="cdServico" title="Código do Serviço" sortable="true" headerClass="sortable" />
							<display:column property="nmServicoSistemaOrigem" title="Nome do Serviço Origem" sortable="true" headerClass="sortable" />
							<display:column property="nmServicoCatalogo" title="Nome do Serviço Catálogo" sortable="true" headerClass="sortable" />
							<display:column property="tipoRelacionamentoTO.dscTipoRelacionamento" title="Tipo de Relacionamento" sortable="true" headerClass="sortable" />
							<display:column title="Status" sortable="true" headerClass="sortable">
								<div class='clean'><div class='${relacionamentoServicoFixaTO.inDisponivel ? "iconativo" : "iconinativo" }'></div></div>
							</display:column>
							<cata:temPermissao acao="excluirRelacionamento">
								<display:column title="Excluir" style="text-align:center; width:60px;">
									<c:if test="${relacionamentoServicoFixaTO.inCriacaoCatalogo}">
										<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" width="15" onclick="remove('${relacionamentoServicoFixaTO.idRelacionamento}')" style="cursor: pointer;"/>
									</c:if>
								</display:column>
							</cata:temPermissao>
							<cata:temPermissao acao="ativarDesativarRelacionamento">
								<display:column title="Ação" style="text-align:center; width:60px;">
										<a href="javascript:changeStatus('${relacionamentoServicoFixaTO.idRelacionamento}', ${relacionamentoServicoFixaTO.inDisponivel})">${relacionamentoServicoFixaTO.inDisponivel ? "Desativar" : "Ativar"}</a>
								</display:column>
							</cata:temPermissao>
						</display:table>
						<div class="legendatable">
						<div><span class="bold">Legenda:</span></div>
						      <div><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir"> <span> Excluir</span></div>
						      <div><div class="iconativo"></div><span>Ativo</span></div>
						      <div><div class="iconinativo"></div><span>Inativo</span></div>
						</div>						
						<!-- botões pesquisar e limpar campos -->
						<div class="barra"></div>
						<div class="botao">
							<cata:temPermissao acao="novoRelacionamento">
								<html:button property="botao_novo" styleId="botao_novo" onClick="novo()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/><span>&nbsp;</span>
							</cata:temPermissao>
						</div>
	            	</div>
	                <div id="pesquisaNovo" style="${mostrarBusca}">
		            	<div class="line">
		            		<div id="msg_obrigatorio" class="box_middle_center_conteudo" style="color:red !important;display:none;">Favor preencher os campos obrigat&oacute;rios</div>
		            		<br/>
		            		<div class="linerow bold ">Tipo de Relacionamento:<span class="required">*</span></div>
		    		        <div class="linerow clearline">
								<html:select property="idTipoRelacionamento" styleId="idTipoRelacionamento" styleClass="required" >
									<html:option  value="0">-- Selecione --</html:option>
									<c:forEach var="tipoRelacionamentoTO" items="${tipoRelacionamentoTOList}">
										<html:option  value="${tipoRelacionamentoTO.idTipoRelacionamento}">${tipoRelacionamentoTO.dscTipoRelacionamento}</html:option>
									</c:forEach>
			                    </html:select>		    		        
		    		        </div>
							<catalogo:contentBox title="Pesquisa" doubt="true" requiredFields="false">
			                    <div class="line">
									<div id="msg_aomenosum" class="box_middle_center_conteudo" style="color:red !important;display:none;">Favor preencher ao menos um par&acirc;metro da pesquisa</div>
									<br/>
			                        <div class="linerow bold"><label for="cdPS">Código Serviço Origem:</label></div>
			                        <div class="linerow bold"><label for="cdServico">Código do Serviço:</label></div>
			                        <div class="linerow bold"><label for=nomeDoServicoOrigem>Nome do Serviço Origem:</label></div>
			                        <div class="linerow bold"><label for="nmComercial">Nome do Serviço Catálogo:</label></div>
			                    </div>
			                    <div class="line">
			                        <div class="linerow bold"><html:text property="cdPS" styleId="cdPS"/></div>
			                        <div class="linerow bold"><html:text property="cdServico" styleId="cdServico"/></div>
			                        <div class="linerow bold"><html:text property="nomeDoServicoOrigem" styleId="nomeDoServicoOrigem"/></div>
			                        <div class="linerow bold"><html:text property="nmComercial" styleId="nmComercial"/></div>
			                    </div>			                    
			                    
			                    <div class="line">
			                        <div class="linerow bold"><label for="tpServico">Tipo de Serviço:</label></div>
			                    </div>
			                    
			                    <div class="line">
									<html:select property="idTipoServico" styleId="idTipoServico" styleClass="required">
										<html:option  value=""></html:option>
										<c:forEach var="tipoServicoTO" items="${tipoServicoTOList}">
											<html:option  value="${tipoServicoTO.idTipoServico}">${tipoServicoTO.dscTipoServico}</html:option>
										</c:forEach>
				                    </html:select>
								</div>
			                    <div class="barra"></div>
			                    <div class="botao">
			                        <html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" styleClass="btNavegacao74" onClick="searchServico()" value="Pesquisar" alt="Pesquisar" title=""/><span>&nbsp;</span>
			                    </div>
							</catalogo:contentBox>
							
							<c:if test="${servicoFixaTOList != null}">
								<div id="resultadoPesquisa">
				                    <catalogo:contentBox title="Resultado Pesquisa" doubt="false" requiredFields="false">
				                    	<div id="msg_selecionar" class="box_middle_center_conteudo" style="color:red !important;display:none;"></div>
										<!-- <netui:checkBoxGroup dataSource="actionForm.idServicoSelecionadoList" > -->
											<display:table name="servicoFixaTOList" id="servicoFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "searchServico.do" class="tabela-padrao-new tablesorter table_body" >
												<display:column title="" style="text-align:center; width:60px;">
													<%-- <netui:checkBoxOption tagId="idServico" value="${servicoFixaTO.idServico}" styleClass="semBorda belongsToForm checkbox" labelStyleClass="hide"  /> --%>
													<html:checkbox property="idServicoSelecionadoList" styleId="idServico" value="${servicoFixaTO.idServico}" styleClass="semBorda belongsToForm checkbox" />
												</display:column>
												<display:column property="cdServico" title="Código do Serviço" sortable="true" headerClass="sortable" />
												<display:column property="cdPS" title="Código do Serviço Origem" sortable="true" headerClass="sortable" />
												<display:column property="nomeDoServicoOrigem" title="Nome do Serviço Origem" sortable="true" headerClass="sortable" />
												<display:column property="nmComercial" title="Nome do Serviço Catálogo" sortable="true" headerClass="sortable" />
												<display:column property="nmTipoServico" title="Tipo de Serviço" sortable="true" headerClass="sortable" />
											</display:table>
										<!-- </netui:checkBoxGroup> -->
										<div class="barra"></div>
					                    <div class="botao">
											<cata:temPermissao acao="gravarRelacionamento">
						                        <html:button property="botao_gravar_form" styleId="botao_gravar_form" styleClass="btNavegacao74" onClick="gravarRelacionamento()" value="Gravar" alt="Gravar" title=""/><span>&nbsp;</span>
											</cata:temPermissao>
					                    </div>										
				                    </catalogo:contentBox> 
								</div>
							</c:if>
						</div>
	                </div>
	            </div> 
	         </c:if>
	         <c:if test="${relacionamentoServicoFixaTOList == null}">
	         	<div class="linerow bold">Aguarde, carregando dados...</div>
	         </c:if>
			<html:hidden property="idServicoTeste" styleId="idServicoTeste" value="${idServicoSearch}"/>
			<html:hidden property="idServicoSearch" styleId="idServicoSearch"/>
			<html:hidden property="idRelacionamento" styleId="idRelacionamento"/>
			<html:hidden property="idSistemaSearch" styleId="idSistemaSearch"/>
		</html:form>
	</jsp:body>	
</catalogo:blankTemplate>