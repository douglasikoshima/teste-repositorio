<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

			<div class="secao_conteudo">
				<div class="conteudo_box_top">
					<div class="conteudo_box_top_center">
						Frequências:
					</div>
					<div class="conteudo_box_top_esq">
					</div>
					<div class="conteudo_box_top_dir openclose">
						<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
					</div>
				</div>
				<div>
					<div>
						<div class="conteudo_box_middle">
							<div class="conteudo_box_middle_mg" style="position:relative;">
								<div style="width:100%;height:23px;" >
									<div class="link_manual" title="Dúvida">
										<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161322" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
									</div>
								</div>
								<table class="tabela-padrao tablesorter table_header">
									<thead>
										<tr>
											<th width="100%" class="sortable">Frequência</th>
											<cata:temPermissao acao="excluirFrequencia">
												<th width="1px">Excluir</th>
											</cata:temPermissao>
										</tr>
									</thead>
								</table>
								<div class="both-scroll" style="height:430px">
<!-- 								<netui-data:repeater dataSource="frequencias">
									<netui-data:repeaterHeader> -->
										<table class="tabela-padrao tablesorter table_body">
											<thead style="visibility:;">
												<tr>
													<th width="100%">Frequência</th>
													<cata:temPermissao acao="excluirFrequencia">
														<th width="1px">Excluir</th>
													</cata:temPermissao>
												</tr>
											</thead>
											<tbody>
<!-- 									</netui-data:repeaterHeader>
									<netui-data:repeaterItem> -->
									<logic:iterate id="vlfrequenciaTO" property="vlfrequenciaList" name="frequenciaForm">
										<tr>
											<td style="text-align: left;">${vlfrequenciaTO.vlFrequencia}</td>
											<cata:temPermissao acao="excluirFrequencia">
												<td class="center">
													<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/frequencias/popupApagarFrequencia.do?id_frequencia=${vlfrequenciaTO.idVlFrequencia}" 
													onClick="abrirPopup1(this.href);return false;">
														<img onclick="" alt="Excluir" src="/catalogo/static_server/img/botoes/bt-excluir.gif"/>
													</html:link>
												</td>
											</cata:temPermissao>
										</tr>
									</logic:iterate>	
<!-- 								    </netui-data:repeaterItem>
									<netui-data:repeaterFooter> -->
											</tbody>
										</table>
<!-- 									</netui-data:repeaterFooter>
								</netui-data:repeater> -->
								</div>
								<div class="barra">
								</div>

								<div class="botao">
									<label class="lblForm">Quantidade de Frequência: ${size_frequencias}</label>
									<cata:temPermissao acao="adicionarFrequencia">
										<html:button id="botao_nova_frequencia" property="bt_novo" onclick="desabilitar(this);$('nova_frequencia').show();$('nova_frequencia').scrollTo();$('valorFrequencia').focus();" styleClass="btNavegacao74" value="Novo" bundle="messages" titleKey="catalogo.ProdutosFrequencia.Novo"/>
									</cata:temPermissao>
								</div>
							</div>
						</div>
						<div class="conteudo_box_bottom">
						</div>
					</div>
				</div>
			</div>
			<br/>
			<div id="nova_frequencia" class="secao_conteudo" style="display: none;">
				<div class="conteudo_box_top">
					<div class="conteudo_box_top_center">
						Nova Frequência:
					</div>
					<div class="conteudo_box_top_esq">
					</div>
					<div class="conteudo_box_top_dir openclose">
						<img id="img_MaxMin" onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
					</div>
					<div class="conteudo_box_top_dir">
						<img src="/catalogo/static_server/img/background/box-topo-right.gif"/>
					</div>
				</div>
				<div>
					<div>
						<div class="conteudo_box_middle">
							<div class="conteudo_box_middle_mg" >
								<div class="legendaObrigatorio">(*) Campo Obrigatório</div>
								<html:form styleId="form_add_frequencia" action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/frequencias/addFrequencia.do">
									<div class="label-form-bold label_required" style="width:140px; text-align:right; vertical-align: middle;">
										Frequência:<font size="1px" color="#EEB422" valign="center">*</font>
									</div>
									<html:text tabindex="1" property="valorFrequencia" styleId="valorFrequencia" styleClass="required editavel" maxlength="20"/>
									
									<br clear="all"/>
									<div class="botao">
										<html:button property="bt_cancelar" tabindex="3" styleClass="btNavegacao120 clearEditavel" value="Cancelar" onclick="habilitar('botao_nova_frequencia');limparForm(this);$('nova_frequencia').hide()" bundle="messages" titleKey="catalogo.ProdutosFrequencia.Cancelar"/>
										<span>&nbsp;</span>
										<html:button property="bt_gravar" tabindex="2" onClick="clearEditando();send(this, 'right_section', null, 'nova_frequencia');setEditando();" styleClass="btNavegacao74" value="Gravar" bundle="messages" titleKey="catalogo.ProdutosFrequencia.Gravar"/>
									</div>
								</html:form>
							</div>
						</div>
						<div class="conteudo_box_bottom">
						</div>
					</div>
				</div>
			</div>



