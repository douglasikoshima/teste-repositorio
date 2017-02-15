<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<catalogo:defaultTemplate titulo="Catalogo">

	<jsp:attribute name="headScripts">

		<!-- <link rel="stylesheet" type="text/css" href="/catalogo/pages/parametrizacaoProdutos/parametrizacaoProdutos.css"/> -->
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/parametrizacaoProdutos.css"/>
		<!-- <link rel="stylesheet" type="text/css" href="/catalogo/pages/parametrizacaoProdutos/jquery.qtip.min.css"/> -->	
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/jquery.qtip.min.css"/>	
		<!-- <script type="text/javascript" src="/catalogo/pages/parametrizacaoProdutos/parametrizacaoProdutos.js"></script> -->
		<script type="text/javascript" src="/catalogo/static_server/js/parametrizacaoProdutos.js"></script>
		<!-- <script type="text/javascript" src="/catalogo/pages/parametrizacaoProdutos/validacaoProdutoForm.js"></script> -->
		<script type="text/javascript" src="/catalogo/static_server/js/validacaoProdutoForm.js"></script>
		<!-- <script type="text/javascript" src="/catalogo/pages/parametrizacaoProdutos/mapMensagens.js"></script> -->
		<script type="text/javascript" src="/catalogo/static_server/js/mapMensagens.js"></script>
		<!-- <script type="text/javascript" src="/catalogo/pages/parametrizacaoProdutos/jquery.qtip.min.js"></script> -->
		<script type="text/javascript" src="/catalogo/static_server/js/jquery.qtip.min.js"></script>
		<script type="text/javascript" >
			mensagens.AUFsselecionadas='<bean:message bundle="messages" key="A.UFs.selecionadas" />';
			mensagens.Alteracoessalvasucesso='<bean:message bundle="messages" key="Alteracoes.salva.sucesso" />';
			mensagens.Alterar='<bean:message bundle="messages" key="Alterar" />';
			mensagens.AlterarnovoGrupoServicos='<bean:message bundle="messages" key="Alterar.novo.Grupo.Servicos" />';
			mensagens.Anterior='<bean:message bundle="messages" key="Anterior" />';
			mensagens.ate='<bean:message bundle="messages" key="ate" />';
			mensagens.Ativo='<bean:message bundle="messages" key="Ativo" />';
			mensagens.Asalteracoesseraoperdidas='<bean:message bundle="messages" key="As.alteracoes.serao.perdidas" />';
			mensagens.AssociarModelo='<bean:message bundle="messages" key="Associar.Modelo" />';
			mensagens.Avulso ='<bean:message bundle="messages" key="Avulso" />';
			mensagens.Classificacao='<bean:message bundle="messages" key="Classificacao" />';
			mensagens.Codigo='<bean:message bundle="messages" key="Codigo" />';
			mensagens.Cor='<bean:message bundle="messages" key="Cor" />';
			mensagens.de='<bean:message bundle="messages" key="de" />';
			mensagens.Dependente ='<bean:message bundle="messages" key="Dependente" />';
			mensagens.Disp='<bean:message bundle="messages" key="Disp" />';
			mensagens.Disponivel='<bean:message bundle="messages" key="Disponivel" />';
			mensagens.DescProduto='<bean:message bundle="messages" key="Desc.Produto" />';
			mensagens.DescProdutoCatalogo='<bean:message bundle="messages" key="Desc.Produto.Catalogo" />';
			mensagens.DescricaoProdutoCatalogo='<bean:message bundle="messages" key="Descricao.Produto.Catalogo" />';
			mensagens.Detalhes ='<bean:message bundle="messages" key="Detalhes" />';
			mensagens.Excluir = '<bean:message bundle="messages" key="Excluir" />';	
			mensagens.Fabricante='<bean:message bundle="messages" key="Fabricante" />';
			mensagens.Incluir='<bean:message bundle="messages" key="Incluir" />';
			mensagens.GrupoServicos='<bean:message bundle="messages" key="Grupo.Servicos" />';
			mensagens.Inativo='<bean:message bundle="messages" key="Inativo" />';
			mensagens.Indisponivel='<bean:message bundle="messages" key="Indisponivel" />';
			mensagens.Limitemaximodependentes='<bean:message bundle="messages" key="Limite.maximo.dependentes" />';
			mensagens.LimiteparaAtivacoes='<bean:message bundle="messages" key="Limite.para.Ativacoes" />';
			mensagens.Maximo='<bean:message bundle="messages" key="Maximo" />';
			mensagens.Modelo = '<bean:message bundle="messages" key="Modelo" />';
			mensagens.Mostrandode='<bean:message bundle="messages" key="Mostrando.de" />';
			mensagens.Nao='<bean:message bundle="messages" key="Nao" />';
			mensagens.NaoAUFsselecionadas='<bean:message bundle="messages" key="Nao.a.UFs.selecionadas" />';
			mensagens.Naoforamencontradosregistros = '<bean:message bundle="messages" key="Nao.foram.encontrados.registros" />';
			mensagens.Erro01 = '<bean:message bundle="messages" key="Erro.01" />';
			mensagens.Erro07 = '<bean:message bundle="messages" key="Erro.07" />';
			mensagens.NomeComercial='<bean:message bundle="messages" key="Nome.Comercial.Alternativo1" />';
			mensagens.NomeTecnico='<bean:message bundle="messages" key="Nome.Tecnico" />';
			mensagens.Padrao='<bean:message bundle="messages" key="Padrao" />';
			mensagens.Processando='<bean:message bundle="messages" key="Processando" />';
			mensagens.Proximo='<bean:message bundle="messages" key="Proximo" />';
			mensagens.Primeiro='<bean:message bundle="messages" key="Primeiro" />';
			mensagens.RegionaisUFs='<bean:message bundle="messages" key="RegionaisUFs" />';
			mensagens.Registros='<bean:message bundle="messages" key="Registros" />';
			mensagens.Sim='<bean:message bundle="messages" key="Sim" />';
			mensagens.Tecnologia ='<bean:message bundle="messages" key="Tecnologia" />';
			mensagens.TipoProduto='<bean:message bundle="messages" key="Tipo.Produto" />';
			mensagens.Titular ='<bean:message bundle="messages" key="Titular" />';
			mensagens.Todos='<bean:message bundle="messages" key="Todos" />';
			mensagens.UF='<bean:message bundle="messages" key="UF" />';
			mensagens.Ultimo='<bean:message bundle="messages" key="Ultimo" />';
			mensagens.WiFiAtivo='<bean:message bundle="messages" key="" />';
			mensagens.E01='<bean:message bundle="messages" key="Erro.01" />';
			mensagens.E02='<bean:message bundle="messages" key="Erro.02" />';
			mensagens.E03='<bean:message bundle="messages" key="Erro.03" />';
			mensagens.E04='<bean:message bundle="messages" key="Erro.04" />';
			mensagens.E05='<bean:message bundle="messages" key="Erro.05" />';
			mensagens.E06='<bean:message bundle="messages" key="Erro.06" />';
			var dataTableLanguage = {
	       		"sProcessing":   mensagens.Processando+"...",
				"sLengthMenu":   "",
				"sZeroRecords":  mensagens.Naoforamencontradosregistros,
				"sInfo":         "",
				"sInfoEmpty":    "",
				"sInfoFiltered": "",
				"sInfoThousands":".",
				"sInfoPostFix":  "",
				"sSearch":       "",
				"sUrl":          "",
				"oPaginate": {
					"sFirst":    "&nbsp;["+mensagens.Primeiro,
					"sPrevious": "/"+mensagens.Anterior+"]&nbsp;",
					"sNext":     "&nbsp;["+mensagens.Proximo+"/",
					"sLast":     mensagens.Ultimo+"]"
				}
			};
		</script>
	</jsp:attribute>
	<fmt:bundle basename="catalogoprs_messages" >
		<script>carregaMenu('mn_parametrizacao_produtos_produtos');</script>
		<div id="formulario" class="secao_conteudo" style="position:relative;">
			<div id="conteudo_box_top" style="margin-bottom: -5px;">
				<fmt:message key="Produtos" var="produtos"/>
				<div class="conteudo_box_top_center" >${produtos}</div>
				<div class="conteudo_box_top_esq"></div>
				<div class="conteudo_box_top_dir openclose">
					<html:img bundle="messages" titleKey="Reduzir.Expandir.Bloco" altKey="Reduzir.Expandir.Bloco" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" onclick="showHideSecaoConteudo(this);" />
				</div>
			</div>
			<div class="conteudo_box_middle overflow">
				<div class="conteudo_box_middle_mg overflow">
					<div id="pesquisa_simplificada">
						<form>
						<!-- <netui:form action="begin"> -->
							<div class="fleft" style="width: 99%">
								<div id="ajuda" style="width:100%" class="ajuda right">
									<fmt:message key="Duvida" var="duvida"/>
									<div title="${duvida}" class="right">
										<html:link target="_blank" href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161353">
											<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/>
										</html:link>
									</div>
									<div class="legendaObrigatorio help">
										(*)<bean:message bundle="messages" key="Campo.Obrigatorio" />
									</div>
								</div>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Tipo.Produto"/>:</div><font size="1px" color="#EEB422" valign="center">*</font>
									<select  validation='{"obrigatorio":true}' id="tipoProduto" style="width:100%" onchange="populaFabricante();">
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
										<c:forEach var="tipoProduto" items="${tipoProdutos}">
											<option value="${tipoProduto.idTipoProduto }">${tipoProduto.nmTipoProduto }</option>
										</c:forEach>
									</select>
								</div>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Fabricante" />:</div><font size="1px" color="#EEB422" valign="center">*</font>
									<select validation='{"obrigatorio":true}' id="fabricante" onchange="populaModelo()" style="width:100%">
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
									</select>
								</div>
								<div id="filtroTecnologia" class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Tecnologia" />:</div><font size="1px" color="#EEB422" valign="center">*</font>
									<select  validation='{"obrigatorio":true}' onchange="populaModelo()" id="tecnologia" style="width:100%">
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
										<c:forEach var="tecnologia" items="${tecnologias}">
											<option value="${tecnologia.idTecnologia }">${tecnologia.nmTecnologia }</option>
										</c:forEach>
									</select>
								</div>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Modelo" />:</div><input onclick="habilitaModelo(this);" id="modeloAtivo" type="checkbox">
									<select id="modelo" style="width:100%;display:none;">
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
									</select>
								</div>
								<br clear="all"/>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"  style="width: 100%"><bean:message bundle="messages" key="Codigo.Produto" />:</div>
									<input id="codigoProduto" type="text" style="width: 100%">
								</div>
							</div>
						</form>
					</div>
				</div>
				<br clear="all"/>
				<div class="barra"></div>
				<div class="botao">
					<fmt:message key="Limpar" var="limpar"/>
					<html:button tabindex="8" property="btn_limpar" onclick="limparTela();" styleClass="btNavegacao74" bundle="messages" value="${limpar}" titleKey="Limpar" />
					<span>&nbsp;</span>
					<fmt:message key="Pesquisar" var="pesquisar"/>
					<html:button property="btn_pesquisar" onclick="pesquisar();" styleClass="btNavegacao74" bundle="messages" value="${pesquisar}" titleKey="Pesquisar" />
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
		<br clear="all">
		<div id="resultado" class="secao_conteudo" style="display:none;position:relative;">
			<div id="conteudo_box_top" style="margin-bottom: -5px;">
				<div class="conteudo_box_top_center" ><bean:message bundle="messages" key="Resuldados.pesquisa" /></div>
				<div class="conteudo_box_top_esq"></div>
				<div class="conteudo_box_top_dir openclose">
					<html:img bundle="messages" titleKey="Reduzir.Expandir.Bloco" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" onclick="showHideSecaoConteudo(this);" />
				</div>
			</div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg overflow">	
					<div id="resultadoTabelaPesquisa" class="both-scroll">
						<table id="tResultado" class="tabela-padrao table_body">
						</table>
					</div>
					<br clear="all">
					<fmt:message key="Associar.Modelo" var="associar"/>
					<html:button bundle="messages" property="btn_associar_modelo" onclick="abrirVincularModelo();" styleClass="btNavegacao120" value="${associar}"/>
					
					<fmt:message key="Remover.Modelo" var="remover"/>
					<html:button bundle="messages" property="idBtRemoverModelo" styleId="idBtRemoverModelo" style="display:none;" onclick="removerVinculoModelo();" styleClass="btNavegacao120" value="${remover}" />
				</div>
			</div>
			<div class="conteudo_box_bottom">
			</div>
		</div>
		<div id="descricaoProduto" class="conteudo_box_middle_mg relative" style="display:none;position:relative;width:500px;">
			<input id="descIdProduto" type="hidden">
			<div class="coluna30">
				<label class="label-form-bold-custom ajuda" style="text-align:right;"><bean:message bundle="messages" key="Codigo.Produto.SAP" />:</label>
			</div>
			<div class="coluna65">
				<input type="text" id="descCodigoSAP" disabled="disabled" style="width: 100%;">
			</div>
			<br clear="all"/>
			<div class="coluna30">
				<label class="label-form-bold-custom ajuda" style="text-align:right;"><bean:message bundle="messages" key="Nome.Comercial.Alternativo1" />:</label>
			</div>
			<div class="coluna65">
				<input type="text" id="descNomeComercial" style="width: 100%;">
			</div>
			<br clear="all"/>
			<div class="coluna30">
				<label class="label-form-bold-custom ajuda" style="text-align:right;"><bean:message bundle="messages" key="Descricao.Produto" />:</label>
			</div>
			<div class="coluna65">
				<input type="text" id="descDescricaoProduto" disabled="disabled" style="width: 100%;">
			</div>
			<br clear="all"/>
			<div class="coluna30">
				<label class="label-form-bold-custom ajuda" style="text-align:right;"><bean:message bundle="messages" key="Composicao.Produto" />:</label>
			</div>
			<div class="coluna65">
				<input type="text" id="descComposicaoProduto" disabled="disabled" style="width: 100%;">
			</div>
			<br clear="all"/>
			<div class="coluna30">
				<label class="label-form-bold-custom ajuda" style="text-align:right;"><bean:message bundle="messages" key="Tipo.Produto" />:</label>
			</div>
			<div class="coluna65">
				<select id="descTipoProduto" style="width:100%"/>
			</div>
			<br clear="all"/>
			<div class="coluna30">
				<label class="label-form-bold-custom ajuda" style="text-align:right;"><bean:message bundle="messages" key="Destaque.Produto" />:</label>
			</div>
			<div class="coluna65">
				<textarea id="descDestaqueProduto" rows="3" style="width: 100%;"></textarea>
			</div>
			<br clear="all"/>
			<div class="coluna30">
				<label class="label-form-bold-custom ajuda" style="text-align:right;"><bean:message bundle="messages" key="Cor.Produto" />:</label>
			</div>
			<div class="coluna65">
				<input type="text" id="descCorProduto" disabled="disabled" style="width: 100%;">
			</div>
		</div>
		<div id="associarModelo" style="display: none;">
			<div id="tabelaModelos" style="width: 485px; height: 256px; overflow-x: hidden; overflow-y: auto;">
				<table id="tabelaModelosResultados" class="tabela-padrao table_body">
				</table>
			</div>
		</div>
		<div id="detalheModelo" style="display:none;">
			<div id="imgModelo" style="width:490px; height:200px"></div>
			<br clear="all"/>
			<div id="botoesDetalheModelo">
				<html:img styleId="btDetalheAnterior" onclick="imagemDetalheAnterior()" src="/catalogo/static_server/img/botoes/bt-fotos-anterior.gif"/>
				<html:img src="/catalogo/static_server/img/botoes/bt-fotos-middle.gif"/>
				<html:img styleId="btDetalhePosterior" onclick="imagemDetalhePosterior();" src="/catalogo/static_server/img/botoes/bt-fotos-proxima.gif"/>
			</div>
		</div>
		<fmt:message key="Sucesso" var="sucesso"/>
		<div id="mensagemSucesso" title="${sucesso}"style="display:none;">
			<div id="msg"><bean:message bundle="messages" key="Mensagem.01" /></div>
		</div>
		<div id="mensagemSucessoModelo" title="${sucesso}"style="display:none;">
			<div id="msg"><bean:message bundle="messages" key="Mensagem.02" /></div>
		</div>
		<div id="mensagemSucessoRemoverModelo" title="${sucesso}"style="display:none;">
			<div id="msg"><bean:message bundle="messages" key="Mensagem.03" /></div>
		</div>
		<div id="mensagemConfirmModelo" title="${sucesso}"style="display:none;">
			<div id="msg"><bean:message bundle="messages" key="Mensagem.04" /></div>
		</div>
		<div id="mensagemErro" title="msgErro" style="display:none;" >
			<div id="msg">${labelError}</div>
		</div>
		</fmt:bundle>
</catalogo:defaultTemplate>