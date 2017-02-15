<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/parametrizarDispAcaoCanal.do?id_produto=${id_produto}">	
	
    <div id="disp_acao_canal">
    	<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Disponibilidade por Ação e por Canal:</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg">

					<div>
						<fieldset class="content">
							<div style="margin-left: 5px;">
							<br />
							<label>Produto</label><br />
							<c:out value="${nm_produto}"/>
							</div>
						</fieldset>
						</br>
						<fieldset class="content" style="height: 150px">
							<h3>Ações</h3>
							<div style="text-align: center; float: left; width: 100%;" id="div-acao-selecao">
								<br/>
								<div class="contentStepLeft">
						            <label for="acoes-disponiveis" id="Selecionáveis">Selecionáveis</label><br/>
						            <html:select property="sgsAcoesOp" styleId="acoes-disponiveis" style="width: 280px; height: 100px;" multiple="true">
						            	<c:forEach var="acaoDisp" items="${acaoDisp}">
						            		<html:option value="${acaoDisp.sigla}">${acaoDisp.descricao}</html:option>
						            	</c:forEach>
						            </html:select>
								</div>
								<div class="contentStepCenter">
									</br>
									<button type="button" id="add-acoes" class="acao" onclick="addOneValueSelect($('acoes-disponiveis'), $('acoes-selecionadas'));">&gt;</button></br>
									<button type="button" id="remove-acoes" class="acao" onclick="addOneValueSelect($('acoes-selecionadas'), $('acoes-disponiveis'));">&lt;</button></br>
								</div>
								<div class="contentStepRight">
						            <label for="acoes-selecionadas" id="Selecionados">Selecionados</label><br/>
						            <html:select property="sgsAcoes" styleId="acoes-selecionadas" style="width: 280px; height: 100px;" multiple="true">
						            	<c:forEach var="acaoConfig" items="${acaoConfig}">
						            		<html:option value="${acaoConfig.sigla}">${acaoConfig.descricao}</html:option>
						            	</c:forEach>
						            </html:select>
								</div>
							</div>
						</fieldset>
						</br>
						<fieldset class="content" style="height: 150px">
							<h3>Organizações de Vendas</h3>
							<div style="text-align: center; float: left; width: 100%;" id="div-organizacoes-vendas-selecao">
								<br/>
								<div class="contentStepLeft">
								    <label for="org-vendas-disponiveis" id="Selecionáveis">Selecionáveis</label><br/>
								    <html:select property="idsOrgVendasOp" styleId="org-vendas-disponiveis" style="width: 280px; height: 100px;" multiple="true">
								    	<c:forEach var="orgVendasDisp" items="${orgVendasDisp}">
								    		<html:option value="${orgVendasDisp.idOrganizacaoVendas}">${orgVendasDisp.sgOrganizacaoVendas}</html:option>
								    	</c:forEach>
								    </html:select>
								</div>
								<div class="contentStepCenter">
									</br>
									<button type="button" id="add-org-vendas" class="acao" onclick="addOneValueSelect($('org-vendas-disponiveis'), $('org-vendas-selecionadas'));" >&gt;</button></br>
									<button type="button" id="remove-org-vendas" class="acao" onclick="addOneValueSelect($('org-vendas-selecionadas'), $('org-vendas-disponiveis'));" >&lt;</button></br>
								</div>
								<div class="contentStepRight">
						            <label for="org-vendas-selecionadas" id="Selecionados">Selecionados</label><br/>
						            <html:select property="idsOrgVendas" styleId="org-vendas-selecionadas" style="width: 280px; height: 100px;" multiple="true">
						            	<c:forEach var="orgVendasConfig" items="${orgVendasConfig}">
						            		<html:option value="${orgVendasConfig.idOrganizacaoVendas}">${orgVendasConfig.sgOrganizacaoVendas}</html:option>
						            	</c:forEach>
						            </html:select>
								</div>
							</div>
						</fieldset>
						</br>
						<fieldset class="content" style="height: 150px">
							<h3>Canais de Atendimento</h3>
							<div style="text-align: center; float: left; width: 100%;" id="div-canais-atend-selecao">
								<br/>
								<div class="contentStepLeft">
								    <label for="canais-atend-disponiveis" id="Selecionáveis">Selecionáveis</label><br/>
								    <html:select property="idsCanaisAtendOp" styleId="canais-atend-disponiveis" style="width: 280px; height: 100px;" multiple="true">
								    	<c:forEach var="canalAtendDisp" items="${canalAtendDisp}">
								    		<html:option value="${canalAtendDisp.idCanal}">${canalAtendDisp.nmCanal}</html:option>
								    	</c:forEach>
								    </html:select>
								</div>
								<div class="contentStepCenter">
									</br>
									<button type="button" id="add-canais-atend" class="acao" onclick="addOneValueSelect($('canais-atend-disponiveis'), $('canais-atend-selecionadas'));" >&gt;</button></br>
									<button type="button" id="remove-canais-atend" class="acao" onclick="addOneValueSelect($('canais-atend-selecionadas'), $('canais-atend-disponiveis'));" >&lt;</button></br>
								</div>
								<div class="contentStepRight">
						            <label for="canais-atend-selecionadas" id="Selecionados">Selecionados</label><br/>
						            <html:select property="idsCanaisAtend" styleId="canais-atend-selecionadas" style="width: 280px; height: 100px;" multiple="true">
						            	<c:forEach var="canalAtendConfig" items="${canalAtendConfig}">
						            		<html:option value="${canalAtendConfig.idCanal}">${canalAtendConfig.nmCanal}</html:option>
						            	</c:forEach>
						            </html:select>
								</div>
							</div>
						</fieldset>
					</div>
					<div class="barra"></div>
					<div class="botao">
						<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/popupConfirmacaoCancel.do" onclick="abrirPopup1(this.href, null , 'right_section');return false;">	
							<input />
							<html:button property="bt_cancelar" tabindex="8" styleClass="btNavegacao74" value="Cancelar" bundle="messages" altKey="catalogo.pp.produtos.Cancelar" titleKey="catalogo.pp.produtos.Cancelar"/>
						</html:link>
						<span>&nbsp;</span>
						<cata:temPermissao acao="gravarDisponibilidadeAcaoCanal">
							<html:button tabindex="9" property="botao_gravar_acao" styleId="botao_gravar_acao" onclick="submitConfigPP();" styleClass="btNavegacao74" value="Gravar" bundle="messages" altKey="catalogo.pp.produtos.GravarFiltro" titleKey="catalogo.pp.produtos.GravarFiltro"/>
						</cata:temPermissao>
					</div>
				</div>
				<div class="conteudo_box_bottom"></div>
			</div>
		</div>
    </div>

</html:form>    
