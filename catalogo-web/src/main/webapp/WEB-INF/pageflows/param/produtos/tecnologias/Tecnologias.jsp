<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

	<div class="secao_conteudo">
    <catalogo:contentBox title="Tecnologia: " doubt="false" requiredFields="false">
    		<div style="width:100%;height:10px;" >
    			<div class="link_manual" title="Dúvida">
					<a href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161314" target="_blank"><img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/></a>
				</div>
			 </div>
		    <div class="conteudo_box_middle">
		       <display:table name="listaTecnologias" id="listaTecnologiasTO" cellspacing="0" cellpadding="0" style="width: 99%;" requestURIcontext="false" requestURI="orderPromocao.do,pesquisaOfertas" class="tabela-padrao-new tablesorter table_body" >			                      
		            <display:column property="nmTecnologia" title="Tecnologia" sortable="true" headerClass="sortable" style="width: 45%;"/>
		            <display:column property="tecnologiaReferencia.nmTecnologia" title="Tecnologia de Referência" sortable="true" headerClass="sortable" style="width: 45%;" />                            
		        
		            <cata:temPermissao acao="excluirTecnologia">
		                <display:column title="Excluir" headerClass="sortable" style="width: 10%; text-align:center;">
		                    <c:if test='${listaTecnologiasTO.tecnologiaReferencia != null}'>                                                          				       
						        <html:link action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/tecnologias/popupApagarTecnologia.do?id_tecnologia=${listaTecnologiasTO.idTecnologia}" onclick="abrirPopup1(this.href);return false;" >
									<img src="/catalogo/static_server/img/botoes/bt-excluir.gif" class="btnExcluirOfertas"/>
								</html:link>												                                                   					                        
		                    </c:if>
		                </display:column>
		            </cata:temPermissao>        
		        </display:table>
		        </div> 
		        <div class="conteudo_box_middle_mg" style="position:relative;">									
								<div class="barra"></div>
								<div class="botao">
									<label class="lblForm">Quantidade de Tecnologias: ${size_tecnologias}</label>
									<cata:temPermissao acao="adicionarTecnologia">
										<html:button id="botao_nova_tecnologia" property="bt_novo" styleClass="btNavegacao74" value="Novo" onclick="desabilitar(this);$('cadastro_tecnologia').show();$('cadastro_tecnologia').scrollTo();$('nmTecnologia').focus();" bundle="messages" titleKey="catalogo.Tecnologias.Novo" />
									</cata:temPermissao>
								</div>
							</div>
    </catalogo:contentBox>
	</div> 
			<br/>														
			<div  id="cadastro_tecnologia"  class="secao_conteudo" style="display: none;">
			<div  class="conteudo_box_top">
				<div class="conteudo_box_top_center">
					Cadastro de Tecnologia:
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
						<div class="conteudo_box_middle_mg">
							<div class="legendaObrigatorio">(*) Campo Obrigatório</div>	
							<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/tecnologias/addTecnologia.do" styleId="acaoForm" onsubmit="false"> 
								<div class="label-form-bold label_required" style="text-align:left; width:160px;">Tecnologia:<font size="1px" color="#EEB422" valign="center">*</font></div>
								<html:text property="nmTecnologia" styleId="nmTecnologia" styleClass="required editavel" size="50" maxlength="50"/> 
								<br clear="all">
								<div nowrap="nowrap" colspan="2" class="label-form-bold label_required" style="text-align:left;width:160px;">Tecnologia de Refer&ecirc;ncia:<font size="1px" color="#EEB422" valign="center">*</font></div>					 
								
								<c:if test="${listaTecnologias != null}">
								    <html:select property="idTecnologiaPai" styleId="idTecnologiaPai" style="width:264px;" styleClass="required editavel">
								        <option value="">-- Selecione --</option>
								        <c:forEach items="${listaTecnologias}" var="listaTecnologiasTO">
								        <c:if test="${listaTecnologiasTO.tecnologiaReferencia == null}">
								            <option value="${listaTecnologiasTO.idTecnologia}">${listaTecnologiasTO.nmTecnologia}</option>
								            </c:if>
								        </c:forEach>
								    </html:select>
								</c:if> 
				
								<br clear="all"/>
								<div class="barra">
								</div>
								
								<div class="botao" style="width: 800px;">
											<html:button property="bt_cancelar" styleClass="btNavegacao74 clearEditavel" value="Cancelar" onclick="habilitar('botao_nova_tecnologia');limparForm(this);$('cadastro_tecnologia').hide()" bundle="messages" titleKey="catalogo.Tecnologias.Cancelar"/>
											<span>&nbsp;</span>
											<html:button property="bt_gravar" onclick="clearEditando();send(this, 'right_section', null, 'cadastro_tecnologia');setEditando();" styleClass="btNavegacao74" value="Gravar" bundle="messages" titleKey="catalogo.Tecnologias.Gravar"/>
								</div>
							 </html:form> 
						</div>
					</div>
					<div class="conteudo_box_bottom">
					</div>
				</div>
			</div>
		</div>