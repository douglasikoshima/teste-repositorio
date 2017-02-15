<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<catalogo:defaultTemplate titulo="Catalogo">

	<jsp:attribute name="headScripts">
    	
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/parametrizacaoModelo.css"/>
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/jquery/css/jquery.qtip.min.css"/> <!-- duplicado -->
		
		<script type="text/javascript" src="/catalogo/static_server/js/json2.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/jquery/js/jquery.qtip.min.js"></script>  <!-- duplicado -->
		<script type="text/javascript" src="/catalogo/static_server/jquery/js/jquery.form.min.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/mapMensagens.js"></script>
		
		<script type="text/javascript" >
			var mensagens = new Object();
			mensagens.AUFsselecionadas='<bean:message bundle="messages" key="A.UFs.selecionadas" />';
			mensagens.AValoresselecionados='<bean:message bundle="messages" key="A.valores.selecionados" />';
			mensagens.Alteracoessalvasucesso='<bean:message bundle="messages" key="Alteracoes.salva.sucesso" />';
			mensagens.Alterar='<bean:message bundle="messages" key="Alterar" />';
			mensagens.AlterarnovoGrupoServicos='<bean:message bundle="messages" key="Alterar.novo.Grupo.Servicos" />';
			mensagens.Anterior='<bean:message bundle="messages" key="Anterior" />';
			mensagens.ate='<bean:message bundle="messages" key="ate" />';
			mensagens.Ativo='<bean:message bundle="messages" key="Ativo" />';
			mensagens.Asalteracoesseraoperdidas='<bean:message bundle="messages" key="As.alteracoes.serao.perdidas" />';
			mensagens.AssociarModelo='<bean:message bundle="messages" key="Associar.Modelo" />';
			mensagens.Avulso='<bean:message bundle="messages" key="Avulso" />';
			mensagens.Caracteristica='<bean:message bundle="messages" key="Caracteristica" />';
			mensagens.Caracteristicas='<bean:message bundle="messages" key="Caracteristicas" />';
			mensagens.Classificacao='<bean:message bundle="messages" key="Classificacao" />';
			mensagens.Codigo='<bean:message bundle="messages" key="Codigo" />';
			mensagens.Confirmacao='<bean:message bundle="messages" key="Confirmacao" />';
			mensagens.Cor='<bean:message bundle="messages" key="Cor" />';
			mensagens.Copiar='<bean:message bundle="messages" key="Copiar" />';
			mensagens.de='<bean:message bundle="messages" key="de" />';
			mensagens.Dependente='<bean:message bundle="messages" key="Dependente" />';
			mensagens.Disp='<bean:message bundle="messages" key="Disp" />';
			mensagens.Disponivel='<bean:message bundle="messages" key="Disponivel" />';
			mensagens.DescProduto='<bean:message bundle="messages" key="Desc.Produto" />';
			mensagens.DescProdutoCatalogo='<bean:message bundle="messages" key="Desc.Produto.Catalogo" />';
			mensagens.DescricaoProdutoCatalogo='<bean:message bundle="messages" key="Descricao.Produto.Catalogo" />';
			mensagens.DetalhesDoModelo='<bean:message bundle="messages" key="Detalhes.do.Modelo" />';
			mensagens.DetalheModelo='<bean:message bundle="messages" key="Detalhe.Modelo" />';
			mensagens.Detalhes='<bean:message bundle="messages" key="Detalhes" />';
			mensagens.Excluir='<bean:message bundle="messages" key="Excluir" />';
			mensagens.Fabricante='<bean:message bundle="messages" key="Fabricante" />';
			mensagens.Favorselecionarcaracteristicasmodelo='<bean:message bundle="messages" key="Favor.selecionar.caracteristicas.modelo" />';
			mensagens.Favorselecionartecnologiasmodelo='<bean:message bundle="messages" key="Favor.selecionar.tecnologias.modelo" />';
			mensagens.Favordefinirmultimidiasmodelo='<bean:message bundle="messages" key="Favor.definir.multimidias.modelo" />';
			mensagens.Permissaoalteracaofabricante='<bean:message bundle="messages" key="Permissao.alteracao.fabricante" />';
			mensagens.Favorselecionarfrequenciatipo='<bean:message bundle="messages" key="Favor.selecionar.frequencia.tipo" />';
			mensagens.paratecnologia='<bean:message bundle="messages" key="para.tecnologia" />';
			mensagens.Favorselecionarmultimidia='<bean:message bundle="messages" key="Favor.selecionar.multimidia" />';
			mensagens.Arquivoextensaoinvalidamultimidia='<bean:message bundle="messages" key="Arquivo.extensao.invalida.multimidia" />';
			mensagens.Permissaocadastrovideomultimidia='<bean:message bundle="messages" key="Permissao.cadastro.video.multimidia" />';
			mensagens.Numeromaximomultimidiasmodelo='<bean:message bundle="messages" key="Numero.maximo.multimidias.modelo" />';
			mensagens.Favorclassificarmultimidias='<bean:message bundle="messages" key="Favor.classificar.multimidias" />';
			mensagens.Multimidiasdiferentesmesmaclassificacao='<bean:message bundle="messages" key="Multimidias.diferentes.mesma.classificacao" />';
			mensagens.Desassociacaoclassificacaomultimidia='<bean:message bundle="messages" key="Desassociacao.classificacao.multimidia" />';
			mensagens.Desassociacaocormultimidia='<bean:message bundle="messages" key="Desassociacao.cor.multimidia" />';
			mensagens.Permissaoalteracaocorpadrao='<bean:message bundle="messages" key="Permissao.alteracao.corpadrao" />';
			mensagens.Alteracaoprodutos='<bean:message bundle="messages" key="Alteracao.afeta.associacao.produtos" />';
			mensagens.Alteracaoconfirmacao='<bean:message bundle="messages" key="Alteracao.confirmacao" />';
			mensagens.Exclusaoprodutos='<bean:message bundle="messages" key="Exclusao.afeta.associacao.produtos" />';
			mensagens.Exclusaoconfirmacao='<bean:message bundle="messages" key="Exclusao.confirmacao" />';
			mensagens.FimDeVida='<bean:message bundle="messages" key="Fim.de.Vida" />';
			mensagens.FotoAnterior='<bean:message bundle="messages" key="Foto.Anterior" />';
			mensagens.Incluir='<bean:message bundle="messages" key="Incluir" />';
			mensagens.IncluirNovoValor='<bean:message bundle="messages" key="Incluir.novo.valor" />';
			mensagens.GrupoServicos='<bean:message bundle="messages" key="Grupo.Servicos" />';
			mensagens.Inativo='<bean:message bundle="messages" key="Inativo" />';
			mensagens.Indisponivel='<bean:message bundle="messages" key="Indisponivel" />';
			mensagens.Limitemaximodependentes='<bean:message bundle="messages" key="Limite.maximo.dependentes" />';
			mensagens.LimiteparaAtivacoes='<bean:message bundle="messages" key="Limite.para.Ativacoes" />';
			mensagens.Maximo='<bean:message bundle="messages" key="Maximo" />';
			mensagens.Modelo = '<bean:message bundle="messages" key="Modelo" />';
			mensagens.Mostrandode='<bean:message bundle="messages" key="Mostrando.de" />';
			mensagens.Multimidia='<bean:message bundle="messages" key="Multimidia" />';
			mensagens.Nao='<bean:message bundle="messages" key="Nao" />';
			mensagens.NaoAUFsselecionadas='<bean:message bundle="messages" key="Nao.a.UFs.selecionadas" />';
			mensagens.Naoavalores='<bean:message bundle="messages" key="Nao.a.valores" />';
			mensagens.Naovaloresselecionados='<bean:message bundle="messages" key="Nao.valores.selecionados" />';
			mensagens.Naoforamencontradosregistros = '<bean:message bundle="messages" key="Nao.foram.encontrados.registros" />';
			mensagens.Necessarioterminimoprodutoselecionado = '<bean:message bundle="messages" key="Necessario.ter.minimo.produto.selecionado" />';
			mensagens.NomeArquivo='<bean:message bundle="messages" key="Nome.Arquivo" />';
			mensagens.NomeComercial='<bean:message bundle="messages" key="Nome.Comercial" />';
			mensagens.NomeModelo='<bean:message bundle="messages" key="Nome.Modelo" />';
			mensagens.NomeTecnico='<bean:message bundle="messages" key="Nome.Tecnico" />';
			mensagens.Padrao='<bean:message bundle="messages" key="Padrao" />';
			mensagens.Processando='<bean:message bundle="messages" key="Processando" />';
			mensagens.ProximaFoto='<bean:message bundle="messages" key="Proxima.Foto" />';
			mensagens.Proximo='<bean:message bundle="messages" key="Proximo" />';
			mensagens.Primeiro='<bean:message bundle="messages" key="Primeiro" />';
			mensagens.RegionaisUFs='<bean:message bundle="messages" key="RegionaisUFs" />';
			mensagens.Registros='<bean:message bundle="messages" key="Registros" />';
			mensagens.Salvar='<bean:message bundle="messages" key="Salvar" />';
			mensagens.selecione='<bean:message bundle="messages" key="selecione" />';
			mensagens.Sim='<bean:message bundle="messages" key="Sim" />';
			mensagens.Tecnologia='<bean:message bundle="messages" key="Tecnologia" />';
			mensagens.Tecnologias='<bean:message bundle="messages" key="Tecnologias" />';
			mensagens.TipoProduto='<bean:message bundle="messages" key="Tipo.Produto" />';
			mensagens.Titular='<bean:message bundle="messages" key="Titular" />';
			mensagens.Todos='<bean:message bundle="messages" key="Todos" />';
			mensagens.UF='<bean:message bundle="messages" key="UF" />';
			mensagens.Ultimo='<bean:message bundle="messages" key="Ultimo" />';
			mensagens.WiFiAtivo='<bean:message bundle="messages" key="WiFi.Ativo" />';
			mensagens.E01='<bean:message bundle="messages" key="Erro.01" />';
			mensagens.E02='<bean:message bundle="messages" key="Erro.02" />';
			mensagens.E03='<bean:message bundle="messages" key="Erro.03" />';
			mensagens.E04='<bean:message bundle="messages" key="Erro.04" />';
			mensagens.E05='<bean:message bundle="messages" key="Erro.05" />';
			mensagens.E06='<bean:message bundle="messages" key="Erro.06" />';
			mensagens.M01='<bean:message bundle="messages" key="Mensagem.01" />';
			
			var dataTableLanguage = { 
	       		"sProcessing":   mensagens.Processando + "...",
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
					"sFirst":    "&nbsp;[" + mensagens.Primeiro,
					"sPrevious": "/" + mensagens.Anterior + "]&nbsp;",
					"sNext":     "&nbsp;[" + mensagens.Proximo + "/",
					"sLast":     mensagens.Ultimo + "]"
				}
			};
			
			var popupsDataTableLanguage = { 
				"sProcessing":   mensagens.Processando + "...",
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
					"sFirst":    "[<<",
					"sPrevious": "|<]&nbsp;",
					"sNext":     "&nbsp;[>|",
					"sLast":     ">>]"
				}
			};
		</script>
		
		<script type="text/javascript" src="/catalogo/static_server/js/parametrizacaoModelo.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/popupCaracteristicas.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/popupTecnologias.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/popupMultimidia.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/cadastroModelo.js"></script>
		<script type="text/javascript" src="/catalogo/static_server/js/validacaoForm.js"></script>
	</jsp:attribute>

	<fmt:bundle basename="catalogoprs_messages" >
        <div id="formulario" class="secao_conteudo" style="position:relative;">
			<div id="conteudo_box_top" style="margin-bottom: -5px;">				
				<fmt:message key="Modelos" var="modelos"/>				
				<div class="conteudo_box_top_center" >${modelos}</div>
				<div class="conteudo_box_top_esq"></div>
				<div class="conteudo_box_top_dir openclose">					
					<html:img bundle="messages" titleKey="Reduzir.Expandir.Bloco" altKey="Reduzir.Expandir.Bloco" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" onclick="showHideSecaoConteudo(this);" />					
				</div>
			</div>
			<div class="conteudo_box_middle overflow">
				<div class="conteudo_box_middle_mg overflow">
					<div id="pesquisa_simplificada">
					    <script>carregaMenu('mn_parametrizacao_produtos_modelo');</script>
						<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/modelo/ModeloAction.do">						
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
									<!-- <select id="tipoProdutoPesquisa" validation='{"obrigatorio":true}' style="width: 140px;" onchange="popularFabricantePesquisa();"> -->
									<select id="tipoProdutoPesquisa" validation='{"obrigatorio":true}' style="width: 140px;" onchange="popularFabricantePesquisa();">
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
										<c:forEach var="tipoProduto" items="${tipoProdutos}">
											<option value="${tipoProduto.idTipoProduto}">${tipoProduto.nmTipoProduto}</option>
										</c:forEach>
									</select>
								</div>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Fabricante" />:</div>									
									<html:select property="fabricantePesquisa" styleId="fabricantePesquisa" style="width: 140px;" onchange="popularModeloPesquisa();">
										<html:option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></html:option>									
									</html:select>
								</div>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Modelo" />:</div>
									<html:select property="modeloPesquisa" styleId="modeloPesquisa" style="width: 140px;">
										<html:option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></html:option>
									</html:select>
								</div>
								<div id="tecnologiaPesquisaDiv" class="label-form-bold" style="width: 140px; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Tecnologia" />:</div>
									<html:select property="tecnologiaPesquisa" styleId="tecnologiaPesquisa" style="width: 100%;">
										<html:option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></html:option>
										<c:forEach var="tecnologia" items="${tecnologias}" >
											<html:option value="${tecnologia.idTecnologia}">${tecnologia.nmTecnologia}</html:option>
										</c:forEach>									
									</html:select>
								</div>
								<br clear="all"/>
								<div class="fleft">
									<div class="label-form-bold" style="width: 185px;">										
										<fmt:message key="Selecionar" var="selecionar"/>																										
										<bean:message bundle="messages" key="Caracteristica" />: <html:button property="btn_caracteristica" styleClass="btNavegacao74 marginBottomLeftTop632" onclick="obterCaracteristicasPesquisa();" bundle="messages" titleKey="Selecionar.Caracteristica" value="${selecionar}" />																																																																																						
									</div>
									<div id="caracteristicasSelecionadasPesquisaDiv" style="padding-left: 110px;"></div>
								</div>
							</div>							
						</html:form>
					</div>
				</div>
				<br clear="all"/>
				<div class="barra"></div>
				<div class="botao">
					<fmt:message key="Limpar" var="limpar"/>
					<html:button property="btn_limpar" onclick="limparTela();" styleClass="btNavegacao74" bundle="messages" value="${limpar}" titleKey="Limpar" />
					<span>&nbsp;</span>
					<fmt:message key="Pesquisar" var="pesquisar"/>
					<html:button property="btn_pesquisar" onclick="pesquisarModelos();" styleClass="btNavegacao74" bundle="messages" value="${pesquisar}" titleKey="Pesquisar" />					
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
		<br clear="all">
		<div id="resultado" class="secao_conteudo" style="display:none;position:relative;">
			<div id="conteudo_box_top" style="margin-bottom: -5px;">
				<fmt:message key="Resuldados.pesquisa" var="resulPesquisa"/>
				<div class="conteudo_box_top_center" >${resulPesquisa}</div>
				<div class="conteudo_box_top_esq"></div>
				<div class="conteudo_box_top_dir openclose">				
					<html:img bundle="messages" titleKey="Reduzir.Expandir.Bloco" altKey="Reduzir.Expandir.Bloco" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" onclick="showHideSecaoConteudo(this);" />
				</div>
			</div>
			<div class="conteudo_box_middle">
				<div class="conteudo_box_middle_mg overflow">	
					<div id="resultadoTabelaPesquisa" class="both-scroll">
						<table id="tResultado" class="tabela-padrao table_body"></table>
					</div>
					<br clear="all"/>
					<div class="barra"></div>
					<table class="tabelaIcones">
						<tr style="float: left;">
							<td><label>&Iacute;cones:</label></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><html:img bundle="messages" altKey="Sim" src="/catalogo/static_server/img/bullets/icon-available.png" /></td>
							<td><fmt:message key="Sim" var="sim" />${sim}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><html:img bundle="messages" altKey="Nao" src="/catalogo/static_server/img/bullets/icon-unavailable.png" /></td>
							<td><fmt:message key="Nao" var="nao" />	${nao}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>							
							<td><html:img bundle="messages" altKey="Copiar.Modelo" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif"/></td>
							<td><fmt:message key="Copiar.Modelo" var="copiarModelo" />${copiarModelo}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>	
							<td><html:img bundle="messages" altKey="Excluir.Modelo" src="/catalogo/static_server/img/botoes/bt-excluir.gif" /></td>
							<td><fmt:message key="Excluir.Modelo" var="excluirModelo"/>${excluirModelo}</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><html:img bundle="messages" altKey="Detalhes.do.Modelo" src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif" /></td>
							<td><fmt:message key="Detalhes.do.Modelo" var="detalhesModelo"/>${detalhesModelo}</td>
						</tr>
					</table>
					<div class="barra"></div>
					<div class="botao">
						<html:button bundle="messages" property="btn_novo" onclick="abrirCadastroModelo();" styleClass="btNavegacao74" value="Novo" titleKey="catalogo.ResultadoBuscaModelos.Novo"/>
					</div>
				</div>
			</div>
			<div class="conteudo_box_bottom">
			</div>
		</div>
		<br clear="all">
		
		
		<div id="cadastroModeloDiv" class="secao_conteudo" style="display: none; position: relative;" >
			<div id="conteudo_box_top" style="margin-bottom: -5px;">
				<fmt:message key="Cadastro.Modelo" var="cadModelo" />
				<div class="conteudo_box_top_center">${cadModelo}</div>
				<div class="conteudo_box_top_esq"></div>
				<div class="conteudo_box_top_dir openclose">
					<html:img bundle="messages" titleKey="Reduzir.Expandir.Bloco"  altKey="Reduzir.Expandir.Bloco" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" onclick="showHideSecaoConteudo(this);"/>
				</div>
			</div>
			<div class="conteudo_box_middle overflow">
				<div class="conteudo_box_middle_mg overflow">
					<div id="cadastro_modelo">
						<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/modelo/begin.do">																		
							<div class="fleft" style="width: 99%">
								<div id="ajuda" style="width: 100%" class="ajuda right">								
									<fmt:message key="Duvida" var="duvida"/>
									<div title="${duvida}" class="right">
										<html:link target="_blank" href="/catalogo/static_server/manual/manual_catalogo.html#_Toc213161353"> 
											<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif" />
										</html:link>
									</div>
									<div class="legendaObrigatorio help">
										(*)<bean:message bundle="messages" key="Campo.Obrigatorio" />
									</div>
								</div>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Tipo.Produto"/>:</div><font size="1px" color="#EEB422" valign="center">*</font>																										
									<select id="tipoProdutoCadastro" validation='{"obrigatorio": true}' style="width: 140px;" onchange="popularFabricanteCadastro(null);">
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
										<c:forEach var="tipoProduto" items="${tipoProdutos}">
											<option value="${tipoProduto.idTipoProduto }">${tipoProduto.nmTipoProduto }</option>
										</c:forEach>
									</select>
								</div>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Fabricante"/>:</div><font size="1px" color="#EEB422" valign="center">*</font>
									<select id="fabricanteCadastro" validation='{"obrigatorio": true}' style="width: 140px;">
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
									</select>
								</div>
								<div class="label-form-bold" style="width: 22%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Nome.Comercial"/>:</div><font size="1px" color="#EEB422" valign="center">*</font>
									<input type="text" id="nmGrupoProdutoCadastro" validation='{"obrigatorio": true}' maxlength="100" onKeyPress="return semPontoVirgula(event);" style="width: 280px;"/>
								</div>
								<br clear="all"/>
								<div class="label-form-bold" style="width: 30%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="URL" />:</div>
									<input type="text" id="urlCadastro" maxlength="254" style="width: 280px;"/>
								</div>
								<div class="label-form-bold" style="text-align: left; padding: 0px 0px 0px 20px; margin-left: 58px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Fim.de.Vida" />:</div><font size="1px" color="#EEB422" valign="center">*</font>
									<select id="inFimVidaCadastro" validation='{"obrigatorio": true}'>
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
										<option value="S"><bean:message bundle="messages" key="Sim"/></option>
										<option value="N"><bean:message bundle="messages" key="Nao"/></option>
									</select>
								</div>
								<div class="label-form-bold" style="text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Disponivel"/>:</div><font size="1px" color="#EEB422" valign="center">*</font>
									<select id="inDisponivelCadastro" validation='{"obrigatorio": true}'>
										<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
										<option value="S"><bean:message bundle="messages" key="Sim"/></option>
										<option value="N"><bean:message bundle="messages" key="Nao"/></option>
									</select>
								</div>
								<br clear="all"/>
								<div class="label-form-bold" style="width: 30%; text-align: left; padding: 0px 0px 0px 20px;">
									<div class="label-form-bold-custom"><bean:message bundle="messages" key="Destaque.Comercial" />:</div>
									<textarea id="dsNotaCadastro" rows="5" style="width: 280px;"></textarea>
								</div>
								<br clear="all"/>
								<div class="fleft" style="width: 50%">
									<div class="label-form-bold" style="width: 185px">
										<bean:message bundle="messages" key="Caracteristica"/>: <font size="1px" color="#EEB422" valign="center">*</font>
										<fmt:message key="Selecionar" var="selecionar"/>
										<html:button bundle="messages" property="btn_select_carac" styleClass="btNavegacao74 marginBottomLeftTop632" titleKey="Selecionar.Caracteristica" value="${selecionar}" onclick="obterCaracteristicasCadastro();"/>
									</div>
									<div id="caracteristicasSelecionadasCadastroDiv" class="left"></div>
								</div>
								<div id="tecnologiasCadastroDiv" class="fleft" style="width: 50%">
									<div class="label-form-bold" style="width: 230px;">
										<bean:message bundle="messages" key="Tecnologia"/>/<bean:message bundle="messages" key="Frequencia" />:<font size="1px" color="#EEB422" valign="center">*</font>
										<fmt:message key="Selecionar" var="selecionar"/>
										<html:button bundle="messages" property="btn_select_tecnologia" styleClass="btNavegacao74 marginBottomLeftTop632" titleKey="Selecionar.Tecnologia" value="${selecionar}" onclick="obterTecnologiasCadastro();"/>
									</div>
									<div id="tecnologiasSelecionadasCadastroDiv" class="left"></div>
								</div>
							</div>
						</html:form>
					</div>
				</div>
				<br clear="all"/>
				<div class="barra"></div>
				<div class="botao">
					<fmt:message key="Limpar" var="limpar"/>
					<html:button bundle="messages" property="btn_limpar_cadModelo" onclick="limparCadastroModelo();" styleClass="btNavegacao74" value="${limpar}" titleKey="Limpar" />
					<span>&nbsp;</span>
					<fmt:message key="Cancelar" var="cancelar"/>
					<html:button bundle="messages" property="btn_cancelar" onclick="fecharCadastroModelo();" styleClass="btNavegacao74" value="${cancelar}" titleKey="Cancelar"/>
					<span>&nbsp;</span>
					<fmt:message key="Gravar" var="gravar"/>
					<html:button bundle="messages" property="btn_gravar" onclick="gravarModelo();" styleClass="btNavegacao74" value="${gravar}" titleKey="Gravar"/>
					<span>&nbsp;</span>
					<fmt:message key="Multimidia" var="multimidia"/>
					<html:button bundle="messages" property="btn_multimidia" onclick="obterMultimidiaCadastro();" styleClass="btNavegacao74" value="${multimidia}" titleKey="Multimidia"/>
				</div>
			</div>
			<div class="conteudo_box_bottom"></div>
			
			
		</div>
		
		<!-- popups -->
		<div id="popupCaracteristica" style="display: none; overflow: hidden;">
			<fieldset id="fieldCaracteristica" class="coluna300px fieldsetPopup">
			<fmt:message key="Caracteristica" var="caracteristica" />
				<legend>
					${caracteristica}
				</legend>
				<div id="resultadoTabelaCaracteristica" style="width: 300px; height: 260px; overflow: hidden;">
					<table id="tResultadoCaracteristica" class="tabela-padrao table_body"></table>
				</div>
			</fieldset>
			<fieldset id="fieldValores" class="coluna300px fieldsetPopup" style="display: none;">
			<fmt:message key="Valores" var="valores" />
				<legend>
					${valores}
				</legend>
				<div id="resultadoTabelaValores" style="padding-top: 12px;"></div>
			</fieldset>
		</div>
		<div id="popupIncluirValor" style="display: none;">
			<div class="celula">
				<fmt:message key="Valor" var="valor" />
				<label style="margin-right: 5px;">${valor}:</label><input type="text" id="valor" maxlength="200">
			</div>
		</div>
		<div id="popupDetalhesModelo" style="display: none;">
			<div id="tituloPopupDetalhesModelo" class="titulo"></div>
			<table width="100%">
				<tr>
					<td width="370">
						<fmt:message key="Caracteristicas.Modelo" var="caracModelo"/>	
						<c:set var="nomeBox" scope="application"  value="${caracModelo}"/>
						<jsp:include page="/templates/box_pre.jsp"/>
						<div id="multimidiasPopupDetalhesModelo" class="fleft" style="width: 120px; height: 200px;"></div>
						<div class="fleft" style="width:14px;">
						</div>
						<div class="fleft vertical-scroll" style="width: 220px; height: 200px; overflow: auto;" align="left">
							<div id="caracteristicasPopupDetalhesModelo" align="left"></div>
						</div>
						<br clear="all"/>
						<jsp:include page="/templates/box_pos.jsp"/>
					</td>
					<td width="370">
						<fmt:message key="Codigo.Produto" var="codProduto"/>
						<c:set var="nomeBox" scope="application"  value="${codProduto}"/>
						<jsp:include page="/templates/box_pre.jsp"/>
						<div id="produtosPopupDetalhesModelo"  style="height: 200px; height: 200px; overflow: auto;"></div>
						<jsp:include page="/templates/box_pos.jsp"/>
					</td>
				</tr>
			</table>
		</div>
		<div id="popupTecnologia" style="display: none; overflow: hidden;">
			<fieldset id="fieldTecnologia" class="fieldsetPopup" style="float: left;">
				<fmt:message key="Tecnologias" var="tecnologias" />
				<legend>
					${tecnologias}
				</legend>
				<div id="resultadoTabelaTecnologia" style="width: 200px; height: 260px; overflow: hidden;">
					<table id="tResultadoTecnologia" class="tabela-padrao table_body"></table>
				</div>
			</fieldset>
			<fieldset id="fieldTiposFrequencia" class="fieldsetPopup" style="display: none; float: left;">
				<fmt:message key="Tipos.Frequencia" var="tipFrequencia" />
				<legend>
					${tipFrequencia}
				</legend>
				<div id="resultadoTabelaTiposFrequencia" style="padding-top: 12px;"></div>
			</fieldset>
			<fieldset id="fieldFrequencias" class="fieldsetPopup" style="display: none; float: left;">
				<fmt:message key="Frequencias" var="frequencias" />
				<legend>
					${frequencias}
				</legend>
				<div id="resultadoTabelaFrequencias" style="padding-top: 12px;"></div>
			</fieldset>
		</div>
		<div id="popupMultimidia" style="display: none; overflow: hidden;">
			<fmt:message key="Cadastro.Imagem.Video" var="cadImgVideo" />
			<c:set var="nomeBox" scope="application"  value="${cadImgVideo}:"/>
			<jsp:include page="/templates/box_pre.jsp"/>
			<html:form styleId="uploadForm" action="/br/com/vivo/catalogoPRS/pageflows/param/modelo/uploadFile.do" method="POST" enctype="multipart/form-data" onsubmit="return submeterUploadForm();">
				<input type="file" id="fileMultimidia" name="fileMultimidia" size="50"/>
				<html:submit bundle="messages" titleKey="Adicionar.Imagem.Modelo" styleClass="btNavegacao120" ><bean:message bundle="messages" key="Adicionar"/></html:submit>
			</html:form>
        	<br>
        	<br>
        	<div id="listaImagens">
	        	<div class="fleft vertical-scroll" style="height: 250px; width: 550px">
	        		<table id="tResultadoMultimidia" class="tabela-padrao table_body" style="table-layout: fixed; width: 520px">
	        			<thead>
	        				<tr>
	        					<th width="250px"><bean:message bundle="messages" key="Nome.Arquivo"/></th>
								<th width="50px"><bean:message bundle="messages" key="Excluir"/></th>
								<th width="110px"><bean:message bundle="messages" key="Classificacao"/></th>
								<th width="110px"><bean:message bundle="messages" key="Cor"/></th>
							</tr>
						</thead>
						<tbody></tbody>
	        		</table>
	        	</div>
				<div class="fleft" style="width: 230px;" align="center">
					<html:img styleId="imagem_modelo" height="200px" width="210px" style="display: none;" src=""/>
					<object id="movieSwf" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="210px" height="200px" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,16,0" style="display: none;">
						<param id="paramMovie" name="movie" value="">
					</object>
				</div>
				<div class="fleft" style="width:520px;" align="center">
				</div>
				<br clear="all"/>
	        </div>
			<div class="fleft">
				<div class="label-form-bold-custom"><bean:message bundle="messages" key="Cor.Padrao" />:</div><font size="1px" color="#EEB422" valign="center">*</font>
				<select id="corCadastro" style="width: 140px;">
					<option value="DEFAULT"><bean:message bundle="messages" key="selecione.com.traco"/></option>
					<c:forEach var="cor" items="${cores}">
						<option value="${cor.idCor}">${cor.nmCor}</option>
					</c:forEach>
				</select>
			</div>
	        <jsp:include page="/templates/box_pos.jsp"/>
		</div>
		<div id="popupConfirmacao" style="display: none;">
			<div id="msg1PopupConfirmacao" class="titulo"></div>
			<table width="100%">
				<tr>
					<td width="370">
						<fmt:message key="Codigo.Produto" var="codProduto" />
						<c:set var="nomeBox" scope="application"  value="${codProduto}"/>
						<jsp:include page="/templates/box_pre.jsp"/>
						<div id="produtosPopupConfirmacao"  style="height: 200px; height: 200px; overflow: auto;"></div>
						<jsp:include page="/templates/box_pos.jsp"/>
					</td>
				</tr>
			</table>
			<div id="msg2PopupConfirmacao" class="titulo"></div>
		</div>
		<fmt:message key="Sucesso" var="sucesso"/>
		<div id="mensagemSucessoGravacao" title="${sucesso}" style="display: none;">
			<div id="msg"><bean:message bundle="messages" key="Mensagem.01"/></div>
		</div>
		<div id="mensagemSucessoExclusao" title="${sucesso}" style="display: none;">
			<div id="msg"><bean:message bundle="messages" key="Mensagem.03"/></div>
		</div>
		</fmt:bundle>
</catalogo:defaultTemplate> 