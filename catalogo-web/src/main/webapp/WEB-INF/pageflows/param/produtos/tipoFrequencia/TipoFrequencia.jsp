<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>

			<div class="secao_conteudo">
				<div class="conteudo_box_top">
					<div class="conteudo_box_top_center">
						Tipo de Frequ&ecirc;ncia:
					</div>
					<div class="conteudo_box_top_esq">
					</div>
					<div class="conteudo_box_top_dir openclose">
						<img id="img_MaxMin" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" onclick="showHideSecaoConteudo(this);" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
					</div>
				</div>
				<div>
					<div>
						<div class="conteudo_box_middle">
							<div class="conteudo_box_middle_mg" style="position:relative;">
								<div style="width:100%;height:23px;" >
									<div class="link_manual" title="Dúvida">
										<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161318" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
									</div>
								</div>
								<table cellspacing="0" width="100px" cellpadding="0" class="tabela-padrao tablesorter table_header" id="TabelaServicosAtivos">
									<thead>
										<tr>
											<th width="49%">Tipo de Frequência</th>
											<th width="49%"> Quantidade de Frequência</th>
											<cata:temPermissao acao="excluirTipoFrequencia">
												<th width="1px">Excluir</th>
											</cata:temPermissao>
										</tr>
									</thead>
								</table>
								<div class="both-scroll" style="height:430px">
										<table cellspacing="0" width="100px" cellpadding="0" class="tabela-padrao tablesorter table_body" id="TabelaServicosAtivos">
											<thead>
												<tr>
													<th width="49%">Tipo de Frequência</th>
													<th width="49%"> Quantidade de Frequência</th>
													<cata:temPermissao acao="excluirTipoFrequencia">
														<th width="1px">Excluir</th>
													</cata:temPermissao>
												</tr>
											</thead>
											<tbody>
										<c:forEach var="listaTipoFrequenciaTO" items="${listaTipoFrequencia}">
											<tr>
												<td>${listaTipoFrequenciaTO.nmTipoFrequencia}</td>
												<td>${listaTipoFrequenciaTO.qtFrequencia}</td>
												<cata:temPermissao acao="excluirTipoFrequencia">
													<td class="center">
														<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/tipoFrequencia/abrirPopupApagarTipoFrequencia.do?id_tipo_frequencia=${listaTipoFrequenciaTO.idTipoFrequencia}" onClick="abrirPopup1(this.href);return false;">
															<img alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif"/> 	
														</html:link>
													</td>
												</cata:temPermissao>
											</tr>
										</c:forEach>
											</tbody>
										</table>
									</netui-data:repeaterFooter>
								</netui-data:repeater>
								</div>
								<div class="barra"></div>
								<div>
									<table>
										<tr>
											<td width="99%" align="left">
												<label class="lblForm" >Quantidade de Tipo de Frequência: ${listaTipoFrequenciaSize}</label>
											</td>
											<td>
												<cata:temPermissao acao="adicionarTipoFrequencia">
													<html:button styleId="botao_novo_tipo_frequencia" property="bt_novo" value="Novo" styleClass="btNavegacao74" onclick="desabilitar(this);$('cadastroTipoFrequencia').show();$('cadastroTipoFrequencia').scrollTo();$('tipoFrequencia').focus();" bundle="messages" titleKey="catalogo.TipoFrequencia.Novo"/>
												</cata:temPermissao>
											</td>
										</tr>
									</table>						
								</div>
							</div>
						</div>
						<div class="conteudo_box_bottom">	
						</div>
					</div>
				</div>
		</div>
	
	<div id="novo_tipo_frequencia">
	<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/tipoFrequencia/addTipoFrequencia.do">
	<c:choose>
		<c:when test="${actionForm.tipoFrequencia!=null}"> 
			<div id="cadastroTipoFrequencia" class="secao_conteudo" style="display: ;">
		</c:when>
		<c:otherwise>
			<div id="cadastroTipoFrequencia" class="secao_conteudo" style="display: none;">
		</c:otherwise>
	</c:choose>
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">
				Cadastro de Tipo de Frequ&ecirc;ncia:
			</div>
			<div class="conteudo_box_top_esq">
			</div>
			<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
						<div class="legendaObrigatorio">(*) Campo Obrigatório</div>
							<div>
								<div>
									<div class="label-form-bold label_required" style="text-align:left; width: 200px;">Tipo de Frequência:<font size="1px" color="#EEB422" valign="center">*</font></div>
									<html:text property="tipoFrequencia" styleClass="nome required editavel" size="50" maxlength="50" />
								</div>
								<br clear="all"/>
								<div>
									<div class="label-form-bold label_required" style="text-align:left;width: 200px;">Quantidade de Frequências:<font size="1px" color="#EEB422" valign="center">*</font></div>
 									<html:text property="qtdeFrequencia" styleClass="nome required editavel" size="3" maxlength="2" onkeypress="return mascaraNumerica(event, 0,0,0,0);"/>
								</div>
							</div>
							<br clear="all"/>
							<div class="barra">
							</div>
							<br clear="all"/>
							<div class="botao">
								<html:button property="bt_cancelar" value="Cancelar" styleClass="btNavegacao120 clearEditavel" onclick="habilitar('botao_novo_tipo_frequencia');limparForm(this);${'cadastroTipoFrequencia'}.hide()" bundle="messages" titleKey="catalogo.TipoFrequencia.Cancelar" /> 
								<span>&nbsp;</span>
								<html:button property="bt_gravar" value="Gravar" styleClass="btNavegacao74" onClick="clearEditando();send(this, 'right_section', null, 'novo_tipo_frequencia');setEditando();" bundle="messages" titleKey="catalogo.TipoFrequencia.Gravar"/>
							</div>
					</div>
				</div>
				<div class="conteudo_box_bottom"></div>
			</div>
		</div>
	</div>
	</html:form>
	</div>
	<br/>
	<br/>

		
