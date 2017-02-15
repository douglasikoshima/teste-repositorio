<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>


<catalogo:defaultTemplate titulo="Home Catalogo">

	<jsp:attribute name="headScripts">
		<script type="text/javascript" src="/catalogo/static_server/js/importacaoservicofixaconsulta.js"></script>
	</jsp:attribute>
	<jsp:body>
		<div class="breadcrumb"> Consulta > Fixa > <span><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/ImportacaoServicoFixaConsultaAction.do';" style="cursor: pointer;">Importa&ccedil;&atilde;o</a><span></div>
		<script>carregaMenu('mn_consultas_fixa_importacao');</script>
		
		<html:form styleId="acaoForm"  action="/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisar.do"  >
			<catalogo:contentBox title="Importa&ccedil;&atilde;o:" doubt="true" requiredFields="true">
				<div class="line">
					<div class="linerow bold"><label for="idTipoImportacao">Tipo de Importa&ccedil;&atilde;o:</label><span class="required">*</span></div>
				</div>
                <div class="line">
                	<div class="linerow bold">
						<html:select  property="idTipoImportacao" styleId="idTipoImportacao" styleClass="required" onchange="mostrarCamposPesquisa(this);" style="width : 250px">
							<html:option  value="0">-- Selecione --</html:option>
							<cata:temPermissao acao="acessarConsultaImportacaoRelacionamento">
								<html:option  value="1">Relacionamento</html:option>
							</cata:temPermissao>
							<cata:temPermissao acao="acessarConsultaImportacaoSCxGCxPMxAC">
								<html:option  value="2">SCxGCxPMxAC - Desconto</html:option>
							</cata:temPermissao>
							<cata:temPermissao acao="acessarConsultaImportacaoSCxENCxPFxGC">
								<html:option  value="3">SCxENCxPFxGC - Financiamento</html:option>
							</cata:temPermissao>
							<cata:temPermissao acao="acessarConsultaImportacaoSCxENCxPFxGCxPMxAC">
								<html:option  value="4">SCxENCxPFxGCxPMxAC - Financiamento</html:option>
							</cata:temPermissao>
                            <cata:temPermissao acao="acessarConsultaImportacaoServico">
                                <html:option  value="5">Servi&ccedil;o</html:option>
                            </cata:temPermissao>
							<cata:temPermissao acao="acessarConsultaImportacaoServico"> <!-- trocar o atributo acao  -->
								<html:option  value="6">Oferta Venda Linha</html:option>
							</cata:temPermissao>
							<cata:temPermissao acao="acessarConsultaImportacaoServico"> <!-- trocar o atributo acao  -->
								<html:option  value="7">Oferta Complementar </html:option>
							</cata:temPermissao>                            
						</html:select>
					</div>
				</div>
				<br/>
                <div class="barra"></div>
                <br/>
				<div class="line" id="preencher_parametros" style="display:none;">
		        	<div class="box_middle_center_conteudo" style="color:red !important;">Favor preencher ao menos um par&acirc;metro da pesquisa</div>
		        	<div class="barra"></div>
				</div>
                <div id="pesquisa" style="display: none">
	                <div class="line">
	                	<div class="linerow bold"><label for="idStatusArquivoImportacao">Status do Arquivo:</label></div>
	                	<div class="linerow bold"><label for="nmArquivo">Nome do arquivo:</label></div>
	                	<div class="linerow bold"><label for="userImportacao">Importado por:</label></div>
	                	<div class="linerow bold"><label for="periodoInicio">Per&iacute;odo de:</label></div>
	                </div>
	                <div class="line">
	                	<div class="linerow bold">
							<html:select property="idStatusArquivoImportacao" styleId="idStatusArquivoImportacao" styleClass="required" >
								<html:option  value="">&nbsp;</html:option>
								<c:forEach var="statusArquivoImportacaoTO" items="${statusArquivoImportacaoTOList}">
									<html:option  value="${statusArquivoImportacaoTO.idStatusArquivoImportacao}">${statusArquivoImportacaoTO.dscStatusArquivoImportacao}</html:option>
								</c:forEach>
		                    </html:select>
	                	</div>
	                	<div class="linerow bold"><html:text property="nmArquivo" styleId="nmArquivo"></html:text></div>
	                	<div class="linerow bold"><html:text property="userImportacao" styleId="userImportacao"></html:text></div>
	                	<div class="linerow bold">
	                		<html:text property="periodoInicio" styleId="periodoInicio" style="data" onKeyPress="return isFormatDate(event, this, '/')"></html:text>
	                	</div>
	                </div>
	                <div class="line">
	                	<div class="linerow bold"><label for="periodoFim">At&eacute;:</label></div>
	                </div>
	                <div class="line">
	                    <div class="linerow bold">
	                    	<html:text property="periodoFim" styleId="periodoFim" style="data" onKeyPress="return isFormatDate(event, this, '/')"></html:text>	             
	                    </div>
	                </div>
					<div class="barra">
					</div>
					<div class="botao">
						<cata:temPermissao acao="acessarConsultaImportacaoServico,acessarConsultaImportacaoSCxGCxPMxAC,acessarConsultaImportacaoRelacionamento,acessarConsultaImportacaoSCxENCxPFxGCxPMxAC,acessarConsultaImportacaoSCxENCxPFxGC">
							<html:button property="botao_listar_arquivos_importados" styleId="botao_listar_arquivos_importados" onClick="pesquisar();" styleClass="btNavegacao74"value="Pesquisar" title="" /><span>&nbsp;</span>																	
						    <!-- <input id="botao_listar_arquivos_importados"  type="button" onClick="pesquisar();" class="btNavegacao74" value="Pesquisar" title=""/><span>&nbsp;</span> -->
						</cata:temPermissao>
						<html:button property="botao_limpar" styleId="botao_limpar" onClick="limpar();" styleClass="btNavegacao74" value="Limpar" title=""></html:button><span>&nbsp;</span>						
					</div>
                </div>
			</catalogo:contentBox>
			
			<c:if test="${resultadoImportacaoList != null}">
				<div id="resultadoImportacaoList"> 
					<catalogo:contentBox title="Resultado da Pesquisa">
						<display:table name="resultadoImportacaoList" id="importacaoServicoFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="nmArquivo" title="Nome do Arquivo" sortable="true" headerClass="sortable" style="word-wrap:break-word; width:350px" />
							<display:column property="dtImportacao" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Data da Importa&ccedil;&atilde;o" sortable="true" headerClass="sortable" />
							<display:column property="dtProcessamento"  format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Data do Processamento" sortable="true" headerClass="sortable" />
							<display:column property="nmUsuarioImportacao"  title="Usu&aacute;rio" sortable="true" headerClass="sortable" />
							<display:column property="statusArquivoImportacaoTO.dscStatusArquivoImportacao"  title="Status" sortable="true" headerClass="sortable" />
							<cata:temPermissao acao="detalheConsultaImportacaoServico,detalheConsultaImportacaoSCxGCxPMxAC,detalheConsultaImportacaoRelacionamento,detalheConsultaImportacaoSCxENCxPFxGCxPMxAC,detalheConsultaImportacaoSCxENCxPFxGC">
								<display:column style="text-align: center;" title="Detalhe" headerClass="sortable" >
									<c:if test="${importacaoServicoFixaTO.statusArquivoImportacaoTO.idStatusArquivoImportacao == 4}">
										<html:link href="javascript:pesquisarCritica('${importacaoServicoFixaTO.id}','${importacaoServicoFixaTO.nmArquivo}')">
											<img src= "/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif" alt="Exibir detalhe"></a>
										</html:link>
									</c:if>
									<c:if test="${importacaoServicoFixaTO.statusArquivoImportacaoTO.idStatusArquivoImportacao == 6}">
										<img src="/catalogo/static_server/img/botoes/valorCarac.gif" alt="${importacaoServicoFixaTO.descErro}" style="width: 10px; height: 10px;" />
									</c:if>
								</display:column>
							</cata:temPermissao>
						</display:table>
					</catalogo:contentBox>
				</div>
			</c:if>

			<!-- CRITICAS DO SERVICOSERVICOARQITEM -->
			<c:if test="${resultadoServicoServicoArqItemList != null}">
				<div id="resultadoServicoServicoArqItemList">
					<catalogo:contentBox title="Arquivo: ${file_name}">
						<display:table name="resultadoServicoServicoArqItemList" id="servicoServicoArqItemTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="acao" title="A&ccedil;&atilde;o" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
							<display:column property="cdServicoMestre"  title="C&oacute;digo do Servi&ccedil;o Rela&ccedil;&atilde;o" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
							<display:column property="sgTipoRelacionamento"  title="Sigla do Tipo de Relacionamento" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
							<display:column property="cdServicoSubordinado"  title="C&oacute;digo do Servi&ccedil;o Relacionado" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
							<display:column property="errosFormatado"  title="Cr&iacute;tica" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
						</display:table>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<cata:temPermissao acao="exportarConsultaImportacaoRelacionamento">
								<html:button property="botao_limpar_form" onClick="exportarCritica()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title="" />
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</div>
			</c:if>

			<!-- CRITICAS DO SOLCOMGRUPOCOMARQITEM -->
			<c:if test="${resultadoSolComGrupoComArqItemList != null}">
				<div id="resultadoSolComGrupoComArqItemList">
					<catalogo:contentBox title="Arquivo: ${file_name}">
						<display:table name="resultadoSolComGrupoComArqItemList" id="solComGrupoComArqItemTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="acao" title="A&ccedil;&atilde;o" sortable="true" headerClass="sortable" />
							<display:column property="cdSolicitacaoComercial"  title="C&oacute;digo da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" style="width:150px;" />
							<display:column property="cdGrupoComercial"  title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" style="width:150px;"/>
							<display:column property="cdPlanoMinutos"  title="C&oacute;digo do Plano de Minutos" sortable="true" headerClass="sortable" style="width:150px;"/>
							<display:column property="cdAreaConcorrencia"  title="C&oacute;digo da &Aacute;rea de Concorr&ecirc;ncia" sortable="true" headerClass="sortable" style="width:150px;"/>
							<display:column property="errosFormatado"  title="Cr&iacute;tica" sortable="true" headerClass="sortable" style="width:150px;"/>
						</display:table>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<cata:temPermissao acao="exportarConsultaImportacaoSCxGCxPMxAC">
								<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="exportarCritica()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title="" />
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</div>
			</c:if>

			<!-- CRITICAS DO SCENCARGOGRUCOMARQITEM -->
			<c:if test="${resultadoSCEncargoGruComArqItemList != null}">
				<div id="resultadoSCEncargoGruComArqItemList">
					<catalogo:contentBox title="Arquivo: ${file_name}">
						<display:table name="resultadoSCEncargoGruComArqItemList" id="scEncargoGruComArqItemTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="acao" title="A&ccedil;&atilde;o" sortable="true" headerClass="sortable" />
							<display:column property="cdSolicitacaoComercial"  title="C&oacute;digo da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" style="width:150px;" />
							<display:column property="cdEncargo"  title="C&oacute;digo do Encargo" sortable="true" headerClass="sortable" style="width:150px;" />
							<display:column property="cdPlanoFinanciamento"  title="C&oacute;digo do Plano de Financiamento" sortable="true" headerClass="sortable" style="width:150px;" />
							<display:column property="cdGrupoComercial"  title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" style="width:150px;"/>
							<display:column property="errosFormatado"  title="Cr&iacute;tica" sortable="true" headerClass="sortable" style="width:150px;"/>
						</display:table>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<cata:temPermissao acao="exportarConsultaImportacaoSCxENCxPFxGC">
								<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="exportarCritica()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title="" />
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</div>
			</c:if>
			
			<!-- CRITICAS DO SCENCARGOGCPMACARQITEM -->
			<c:if test="${resultadoSCEncargoGCPMACArqItemList != null}">
				<div id="resultadoSCEncargoGCPMACArqItemList">
					<catalogo:contentBox title="Arquivo: ${file_name}">
						<display:table name="resultadoSCEncargoGCPMACArqItemList" id="scEncargoGruComArqItemTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
							<display:column property="acao" title="A&ccedil;&atilde;o" sortable="true" headerClass="sortable" />
							<display:column property="cdSolicitacaoComercial"  title="C&oacute;digo da Solicita&ccedil;&atilde;o Comercial" sortable="true" headerClass="sortable" style="width:150px;" />
							<display:column property="cdEncargo"  title="C&oacute;digo do Encargo" sortable="true" headerClass="sortable" style="width:150px;" />
							<display:column property="cdPlanoFinanciamento"  title="C&oacute;digo do Plano de Financiamento" sortable="true" headerClass="sortable" style="width:150px;" />
							<display:column property="cdGrupoComercial"  title="C&oacute;digo do Grupo Comercial" sortable="true" headerClass="sortable" style="width:150px;"/>
							<display:column property="cdPlanoMinutos"  title="C&oacute;digo do Plano de Minutos" sortable="true" headerClass="sortable" style="width:150px;"/>
							<display:column property="cdAreaConcorrencia"  title="C&oacute;digo da &Aacute;rea de Concorr&ecirc;ncia" sortable="true" headerClass="sortable" style="width:150px;"/>
							<display:column property="errosFormatado"  title="Cr&iacute;tica" sortable="true" headerClass="sortable" style="width:150px;"/>
						</display:table>
						<br clear="all"/><br clear="all"/>
						<div class="barra"></div>
						<div class="botao">
							<cata:temPermissao acao="exportarConsultaImportacaoSCxENCxPFxGCxPMxAC">
								<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="exportarCritica()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title="" />
							</cata:temPermissao>
						</div>
					</catalogo:contentBox>
				</div>
			</c:if>
            
            <!-- CRITICAS DO SERVICOARQITEM -->
            <c:if test="${resultadoServicoArqItemList != null}">
                <div id="resultadoServicoArqItemList">
                    <catalogo:contentBox title="Arquivo: ${file_name}">
                        <display:table name="resultadoServicoArqItemList" id="servicoArqItemTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
                            <display:column property="cdServico" title="C&oacute;digo Servi&ccedil;o" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                            <display:column property="nmComercial"  title="Nome do Servi&ccedil;o" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                            <display:column property="disponibilidade"  title="Disponibilidade" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                            <display:column property="errosFormatado"  title="Cr&iacute;tica" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                        </display:table>
                        <br clear="all"/><br clear="all"/>
                        <div class="barra"></div>
                        <div class="botao">
                            <cata:temPermissao acao="exportarConsultaImportacaoServico">
                                <html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="exportarCritica()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title="" />
                            </cata:temPermissao>
                        </div>
                    </catalogo:contentBox>
                </div>
            </c:if> 
            
            <!-- CRITICAS DO OFERTAVLARQITEM (OFERTA)-->
            <c:if test="${resultadoOfertaVLArqItemList != null}">
                <div id="resultadoOfertaVLArqItemList">
                    <catalogo:contentBox title="Arquivo: ${file_name}">
                        <display:table name="resultadoOfertaVLArqItemList" id="OfertaVLArqItemTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
                            <display:column property="cdOferta" title="C&oacute;digo da Oferta" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                            <display:column property="nmOferta"  title="Nome da Oferta" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                            <display:column property="errosFormatado"  title="Cr&iacute;tica" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>                                                                                                             
                        </display:table>
                        <br clear="all"/><br clear="all"/>
                        <div class="barra"></div>
                        <div class="botao">
                            <cata:temPermissao acao="exportarConsultaImportacaoServico">
                            	<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="exportarCritica()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title="" />
                            </cata:temPermissao>
                        </div>
                    </catalogo:contentBox>
                </div>
            </c:if> 
            
            <!-- CRITICAS DO OFERTAVLFARQITEM (OFERTA COMPLEMENTAR)-->
            <c:if test="${resultadoOfertaVLFArqItemList != null}">
                <div id="resultadoOfertaVLFArqItemList">
                    <catalogo:contentBox title="Arquivo: ${file_name}">
                        <display:table name="resultadoOfertaVLFArqItemList" id="OfertaVLFArqItemTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "" class="tabela-padrao-new tablesorter table_body" >
                            <display:column property="cdOferta" title="C&oacute;digo da Oferta" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                            <display:column property="cdPsServico"  title="PS Servi&ccedil;o Complementar" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                            <display:column property="cdOpComServico"  title="Operação Comercial" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                             <display:column property="pzVigencia"  title="Prazo Vigência" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                            <display:column property="errosFormatado"  title="Cr&iacute;tica" sortable="true" headerClass="sortable" style="vertical-align:top !important"/>
                        </display:table>
                        <br clear="all"/><br clear="all"/>
                        <div class="barra"></div>
                        <div class="botao">
                            <cata:temPermissao acao="exportarConsultaImportacaoServico">
                            	<html:button property="botao_limpar_form" styleId="botao_limpar_form" onClick="exportarCritica()" styleClass="btNavegacao74" value="Exportar" alt="Exportar" title="" />
                            </cata:temPermissao>
                        </div>
                    </catalogo:contentBox>
                </div>
            </c:if>				
			<html:hidden property="pagina_solicitada" styleId="pagina_solicitada" value=""/>
			<html:hidden property="idArquivo" styleId="idArquivo" value=""/>
			<html:hidden property="nmArquivoConsulta" styleId="nmArquivoConsulta" value=""/>
			
			<html:hidden property="erro" styleId="erro" value="${erro}"/>			
		</html:form>
	
	</jsp:body>
</catalogo:defaultTemplate>