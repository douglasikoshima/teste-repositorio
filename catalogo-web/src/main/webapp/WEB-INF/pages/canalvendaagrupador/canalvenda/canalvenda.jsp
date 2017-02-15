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
		<script type="text/javascript" src="/catalogo/static_server/js/canalvenda.js"></script>
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/canalvenda/canalvenda.css"/>
  </jsp:attribute>
  <jsp:body>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/pesquisar.do" styleId="canalVendaForm" onsubmit="false">
			<html:hidden property="idCanalVendaAlternarIndisponivel" styleId="idCanalVendaAlternarIndisponivel"/>
			<html:hidden property="idCanalVenda" styleId="idCanalVenda"/>
			<html:hidden property="cdCanalVenda" styleId="cdCanalVenda"/>
			<html:hidden property="inDisponivel" styleId="inDisponivel"/>
			<html:hidden property="inCriacaoCatalogo" styleId="inCriacaoCatalogo"/>
			<html:hidden property="tabelaAgrupadoresAtiva" styleId="tabelaAgrupadoresAtiva"/>
			<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
			<c:if test="${labelError != null}">
				<script>
					generateContentErrorForm("${labelError}")
				</script>
			</c:if>
	
			<catalogo:contentBox title="Pesquisa" doubt="true">
				<div class="fleft" style="width:25%; "> 
					<div class="label-form-bold label_required" style="text-align: left; width: 140px;"><label for="cdCanalVendaSearch">C&oacute;digo:</label></div>
					<html:text property="cdCanalVendaSearch" styleId="cdCanalVendaSearch" style="width:140px;" styleClass="required" maxlength="254"/>
				</div>
				<div class="fleft" style="width:25%; "> 
					<div class="label-form-bold label_required" style="text-align: left; width: 140px;"><label for="nmCanalVendaSearch">Nome:</label></div>
					<html:text property="nmCanalVendaSearch" styleId="nmCanalVendaSearch" style="width:140px;" styleClass="required" maxlength="254"/>
				</div>
				<div class="fleft" style="width:22%; margin-bottom: 5px; margin-left: 5px;">
					<div class="label-form-bold label_required" style="text-align: left; width: 80px;"> <label for="inCriacaoCatalogoSearch" >Origem:</label> </div>
					<html:select property="inCriacaoCatalogoSearch" styleId="inCriacaoCatalogoSearch" styleClass="required" style="width:100px; height: 60px;">
						<html:option value="" > Ambos </html:option>
	                    <html:option value="S" > Catalogo </html:option >
	                    <html:option value="N" > Legado </html:option >
                   	</html:select>
				</div>
				<div class="fleft" style="width:22%; margin-bottom: 5px; margin-left: 5px;">
					<div class="label-form-bold label_required" style="text-align: left; width: 80px;"> <label for="inDisponivelSearch" >Disponibilidade:</label> </div>
					<html:select property="inDisponivelSearch" styleId="inDisponivelSearch" styleClass="required" style="width:100px; height: 60px;" >
						<html:option value="S" > Sim </html:option>
	                    <html:option value="N" > N&atilde;o </html:option >
	                    <html:option value="" > Ambos </html:option >
                   	</html:select>
				</div>
				<div class="fleft" style="width:22%; margin-bottom: 5px; margin-left: 5px;">
					<div class="label-form-bold label_required" style="text-align: left; width: 80px;"> <label for="idEpsSearch" >Agrupador:</label> </div>
                    <html:select property="idEpsSearch" styleId="idEpsSearch" styleClass="required" >
                        <html:option value="">--Selecione--</html:option>
                        <c:forEach var="epsTO" items="${epsTOList}">
                            <html:option value="${epsTO.idEps}">${epsTO.nmEps}</html:option>
                        </c:forEach>
                    </html:select>								
				</div>
				<br clear="all"/>
				<div class="barra"></div>
				<div class="botao">
					<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="pesquisar()" styleClass="btNavegacao74" value="Pesquisar" alt="Pesquisar" title=""/>
					<span>&nbsp;</span>
					<cata:temPermissao acao="novoGrupoComercial">
						<html:button property="botao_novo_form" styleId="botao_novo_form" onClick="criarNovo()" styleClass="btNavegacao74" value="Novo" alt="Novo" title=""/>
						<span>&nbsp;</span>
					</cata:temPermissao>
				</div>
			</catalogo:contentBox>
			<c:if test="${pesquisaAtiva}">
				<catalogo:contentBox title="Resultado da Pesquisa" >
					<div id="tabelaGruposComerciais" style="${!tabelaAgrupadoresAtiva?'display: block':'display: none'}" >
						<display:table name="canalVendaTOList" id="canalVendaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column title="Origem" sortable="true" headerClass="sortable">
								<c:choose>
									<c:when test="${canalVendaTO.inCriacaoCatalogo=='S'}">
										Catalogo
									</c:when>
									<c:otherwise>
										Legado
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="Código" property="cdCanalVenda" sortable="true" headerClass="sortable"/>
							<display:column title="Nome" property="nmCanalVenda" sortable="true" headerClass="sortable"/>
							<display:column title="Agrupador" property="epsTO.nmEps" sortable="true" headerClass="sortable"/>
							<display:column title="Status" sortable="true" headerClass="sortable" style="text-align: center">
								<catalogo:flagDefault test="${canalVendaTO.inDisponivel=='S'}" />
							</display:column>
							<cata:temPermissao acao="alterarGrupoComercial">
								<display:column title="Alterar" style="text-align:center; width:40px;">
									<img src="/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar" onclick="editar(${canalVendaTO.idCanalVenda})" width="15" style="cursor: pointer;"/>
								</display:column>
							</cata:temPermissao>
							<cata:temPermissao acao="excluirGrupoComercial">
								<display:column title="Excluir" style="text-align:center; width:40px;">
									<c:if test="${canalVendaTO.inCriacaoCatalogo=='S'}">
										<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir" onclick="remover(${canalVendaTO.idCanalVenda})" width="15" style="cursor: pointer;"/>
									</c:if>
								</display:column>
							</cata:temPermissao>
							<cata:temPermissao acao="ativarDesativarGrupoComercial">
								<display:column title="Ação" style="text-align: center;" sortable="true" headerClass="sortable" >
									<c:choose>
										<c:when test="${canalVendaTO.inDisponivel=='S'}">
											<a href="javascript:alternarInDisponivel(${canalVendaTO.idCanalVenda},'${canalVendaTO.inDisponivel}')" >Desativar</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:alternarInDisponivel(${canalVendaTO.idCanalVenda},'${canalVendaTO.inDisponivel}')" >Ativar</a>
										</c:otherwise>
									</c:choose>
								</display:column>
							</cata:temPermissao>
						</display:table>
						<div class="legendatable">
							<div><span class="bold">Legenda:</span></div>
							<div><img src= "/catalogo/static_server/img/botoes/bt-alterar.gif" alt="Alterar"> <span> Alterar</span></div>
							<div><img src= "/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir"> <span> Excluir</span></div>
							<div><div class="iconativo"></div><span>Ativo</span></div>
							<div><div class="iconinativo"></div><span>Inativo</span></div>
						</div>
						<br clear="all"/>
						<div class="barra"></div>
					    <div class="botao">
							<html:button property="botao_pesquisar_form" styleId="botao_pesquisar_form" onClick="exibirTabelaAgrupador()" styleClass="btNavegacao120" value="Associar Agrupador" alt="Associar Agrupador" title=""/>
							<span>&nbsp;</span>
					    </div>
					</div>
					<div id="tabelaAgrupadores" style="${tabelaAgrupadoresAtiva?'display: block':'display: none'}">
							<display:table name="canalVendaTOList" id="canalVendaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
								<display:column title="" headerClass="sortable" style="width:10px;">
									<html:checkbox property="idCanalVendaCheckedList" styleId="idCanalCheckBoxElem_${canalVendaTO.idCanalVenda}" styleClass="CanalCheckBoxElem" value="${canalVendaTO.idCanalVenda}"/>
								</display:column>
								<display:column title="Origem" headerClass="sortable">
									<c:choose>
										<c:when test="${canalVendaTO.inCriacaoCatalogo=='S'}">
											Catalogo
										</c:when>
										<c:otherwise>
											Legado
										</c:otherwise>
									</c:choose>
								</display:column>
								<display:column title="Código" property="cdCanalVenda" headerClass="sortable"/>
								<display:column title="Nome" property="nmCanalVenda" headerClass="sortable"/>
								<display:column title="Agrupador" property="epsTO.nmEps" headerClass="sortable"/>
							</display:table>
						<br clear="all"/>
						<div class="barra"></div>
						<div class="line" >
						    <div class="linerow bold"><label for="idEps">Agrupador:</label><span class="required">*</span></div>
						    <div class="linerow bold"><label for=""></label></div>
						    <div class="linerow bold"><label for=""></label></div>
						</div>
						<div class="line" >
						    <div class="linerow bold">
                                <html:select property="idEps" styleId="idEps" styleClass="required">
                                    <html:option value="">--Selecione--</html:option>
                                    <c:forEach var="epsTO" items="${epsTOList}">
                                        <html:option value="${epsTO.idEps}">${epsTO.nmEps}</html:option>
                                    </c:forEach>
                                </html:select>								
						    </div>
						    <div class="botaoAEsquerda">
						    	<cata:temPermissao acao="associarAgrupadorGrupoComercial">
									<html:button property="botao_associar_form" styleId="botao_associar_form" onClick="associarAgrupador()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
									<span>&nbsp;</span>
								</cata:temPermissao>
								<cata:temPermissao acao="desassociarAgrupadorGrupoComercial">
									<html:button property="botao_desassociar_form" styleId="botao_desassociar_form" onClick="desassociarAgrupador()" styleClass="btNavegacao74" value="Desassociar" alt="Desassociar" title=""/>
									<span>&nbsp;</span>
								</cata:temPermissao>
								<html:button property="botao_voltar_form" styleId="botao_voltar_form" onClick="exibirPesquisa()" styleClass="btNavegacao74" value="Voltar" alt="Voltar" title=""/>
								<span>&nbsp;</span>
						    </div>
						</div>
					</div>
				</catalogo:contentBox>	
			</c:if>
			<div id="mensagemLoadSearch" class="linerow bold">  Aguarde, carregando dados...</div>
			<c:if test="${cadastroAtivo}">				
				<catalogo:contentBox title="Cadastro de Grupo Comercial" doubt="true" requiredFields="true">
					<div class="fleft" style="width:22%; "> 
						<div class="label-form-bold label_required" style="text-align: left; width: 140px;"><label for="nmCanalVenda">Nome:</label><span class="required">*</span></div>
						<html:text property="nmCanalVenda" styleId="nmCanalVenda" style="width:140px;" styleClass="required" maxlength="254" />
					</div>
					<br clear="all"/>
					<div class="barra"></div>
					<cata:temPermissao acao="gravarGrupoComercial">
						<div class="botao">
							<html:button property="botao_gravar_form" styleId="botao_gravar_form" onClick="salvar()" styleClass="btNavegacao74" value="Gravar" alt="Gravar" title=""/>
							<span>&nbsp;</span>
						</div>
					</cata:temPermissao>
				</catalogo:contentBox>
			</c:if>
        </html:form>     
   </jsp:body>        
</catalogo:blankTemplate>