<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
	  <!-- INÍCIO MENU-->
	    <div id="menu">
	        <ul>
	        	<br clear="all"/>
				<li class="menu-item-pai-off" id="menu_home"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/inicio/goHome.do">P&aacute;gina Inicial</a></li>
				<cata:temPermissao acao="acessarServicoAdicional,acessarAnaliseCreditoLinha,acessarAnaliseCreditoPrioridade,acessarOfertafixa,acessarOfertaBandaLarga,acessarImportacaoRelacionamento,acessarImportacaoSCxGCxPMxAC,acessarCategoriaFamilia,acessarGrupoComercial,acessarFinanciamento,consultarCaracteristica,consultarModelo,consultarProduto,consultarFormasCondicoesPagamento,consultarTecnologia,consultarTipoFrequencia,consultarFrequencia,consultarAssociacaoTecnologiaTipoFrequencia,consultarDesconto,paramentrizacaoPlanosServicos,parametrizacaoAcessoConfigurar,cadastrarMatrizOferta,itensMatrizOferta,ofertaSapMatrizOferta,importarArquivoMatrizOferta,importarMatrizTipoContrato,ofertaServicosMatrizOferta,disponibilidadeProduto,pacoteBonusServicos,produtoAcao,produtoCor,acessarProdutoMatrizOferta,acessarGerenciadorRegras,acessarSubSegmento,acessarCategorizarFinanceiro,acessarConfiguracaoAnaliseCredito,acessarConfiguraRestricao">
					<li id="mn_parametrizacao" class="menu-item-pai-off"><a href="javascript:TwMenu('sub_parametrizacao',1);void(0);">Parametriza&ccedil;&atilde;o</a>
						<ul id="sub_parametrizacao">

							<li class="menu-item" id="mn_parametrizacao_acesso"><a href="javascript:TwMenu('sub_parametrizacao_acesso',2);void(0);">Acesso</a>
								<ul id="sub_parametrizacao_acesso">
									<cata:temPermissao acao="parametrizacaoAcessoConfigurar">
										<li class="menu-item-link" id="mn_parametrizacao_acesso_planos_servicos"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/ParametrizacaoAcessoPlanosServicosAction.do');">Planos e Serviços</a></li>
									</cata:temPermissao>
								</ul>
							</li>
							
							<li class="menu-item" id="mn_parametrizacao_fixa"><a href="javascript:TwMenu('sub_parametrizacao_fixa',2);void(0);">Fixa</a>
								<ul id="sub_parametrizacao_fixa">
									<cata:temPermissao acao="acessarAnaliseCreditoLinha">
										<li class="menu-item-link" id="mn_parametrizacao_fixa_analise_credito_linha"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/AnaliseCreditoLinhaAction.do';">An&aacute;lise de Cr&eacute;dito <br/>de Linha</a></li>
									</cata:temPermissao>
                                    <cata:temPermissao acao="acessarAnaliseCreditoPrioridade">
                                        <li class="menu-item-link" id="mn_parametrizacao_fixa_analise_credito_prioridade"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecreditopriorizar/AnaliseCreditoPriorizarAction.do';">An&aacute;lise de Cr&eacute;dito - Prioridade</a></li>
                                    </cata:temPermissao>
									<cata:temPermissao acao="acessarImportacaoRelacionamento,acessarImportacaoSCxGCxPMxAC,acessarImportacaoServico,acessarImportacaoSCxENCxPFxGC,acessarImportacaoSCxENCxPFxGCxPMxAC">
										<li class="menu-item-link" id="mn_parametrizacao_fixa_importacao"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/ImportacaoServicoFixaAction.do';">Importa&ccedil;&atilde;o</a></li>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarCategoriaFamilia">
										<li class="menu-item-link" id="mn_parametrizacao_familia_categoria"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/FamiliaCategoriaAction.do';" >Fam&iacute;lia | Categoria</a></li>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarFinanciamento">
										<li class="menu-item-link" id="mn_parametrizacao_cadastro_financiamento"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/financiamento/CadastroFinanciamentoAction.do';" >Financiamentos</a></li>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarGerenciadorRegras">
										<li class="menu-item-link" id="mn_parametrizacao_gerenciador_regras"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/filtro1/GerenciadorRegrasConfiguracaoFiltro1Action.do';">Gerenciador <br>de Regras</a></li>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarGrupoComercial">
										<li class="menu-item-link" id="mn_parametrizacao_canal_venda_agrupador"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/CanalVendaAgrupadorAction.do';">Grupo Comercial |<br/>Agrupador</a></li>
									</cata:temPermissao>
                                    <cata:temPermissao acao="acessarOfertafixa">
                                        <li class="menu-item-link" id="mn_parametrizacao_oferta"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/OfertaFixaAction.do';">Oferta Venda Linha</a></li>
                                    </cata:temPermissao>
									<cata:temPermissao acao="acessarOfertaBandaLarga">
										<li class="menu-item-link" id="mn_parametrizacao_promocaofixa"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/PromocaoFixaAction.do';">Oferta Banda Larga</a></li>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarServico">
										<li class="menu-item-link" id="mn_parametrizacao_servico"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/ServicoFixaAction.do';">Serviço</a></li>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarServicoAdicional">
										<li class="menu-item-link" id="mn_parametrizacao_servico_adicionais"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/ServicosAdicionaisAction.do';">Serviços Adicionais</a></li>
									</cata:temPermissao>
								</ul>
							</li>
							
							<li class="menu-item" id="mn_parametrizacao_matriz_oferta"><a href="javascript:TwMenu('sub_parametrizacao_matriz_oferta',2);void(0);">Matriz de Oferta</a>
								<ul id="sub_parametrizacao_matriz_oferta">
									<cata:temPermissao acao="acessarAssociaProdutoAcao">
										<li class="menu-item-link" id="mn_parametrizacao_matriz_oferta_associacaoproduto"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acaoproduto/AssociacaoAcaoProdutoAction.do">Associar Produtos<br /> &agrave; A&ccedil;&otilde;es</a></li>
									</cata:temPermissao>

									<cata:temPermissao acao="cadastrarMatrizOferta">
										<li class="menu-item-link" id="mn_parametrizacao_matriz_oferta_cadastrar"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/cadastro/CadastroCabecalhoMatrizOfertaAction.do');">Cadastro</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="produtoAcao,produtoAcaoTelevendas">
										<li class="menu-item-link" id="mn_parametrizacao_matriz_oferta_acao"><a href="javascript:document.location.href='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acao/AcaoAction.do';">Cadastro Ações<br /> de Vendas</a></li>									
									</cata:temPermissao>
									
									<cata:temPermissao acao="importarArquivoMatrizOferta">
										<li class="menu-item-link" id="mn_parametrizacao_matrizOferta_importar_arquivo"><a id="link_parametrizacao_matrizOferta_importar_arquivo" onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/importacao/ImportacaoMatrizOfertaAction.do');">Importa&ccedil;&atilde;o</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="importarMatrizTipoContrato"> 
										<li class="menu-item-link" id="mn_parametrizacao_importacao_matriz_contrato"><a onclick="javascript:document.location.href='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/ImportacaoTipoMatrizContratoAction.do'">Importação Tipos<br />de Matriz e Contrato</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="itensMatrizOferta">
										<li class="menu-item-link" id="mn_parametrizacao_matriz_oferta_itens"><a id="link_itens_matriz_oferta" onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/ItensMatrizOfertaAction.do');">Itens</a></li>
									</cata:temPermissao>

									<cata:temPermissao acao="acessarProdutoMatrizOferta">
										<li class="menu-item-link" id="mn_parametrizacao_matriz_oferta_produto_preco"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/produto/MatrizOfertaProdutoPrecoAction.do';">Produto <br/> Matriz Oferta</a></li>
									</cata:temPermissao>

									<cata:temPermissao acao="ofertaServicosMatrizOferta">
										<li class="menu-item-link" id="mn_parametrizacao_matriz_oferta_ofertaServicos"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaServicos/OfertaServicosMatrizOfertaAction.do');">Ofertas de Servi&ccedil;os</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="ofertaSapMatrizOferta">
										<li class="menu-item-link" id="mn_parametrizacao_matrizOferta_cadastro_ofertasap"><a id="link_parametrizacao_matrizOferta_cadastro_ofertasap" onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/ofertaSap/CadastroOfertaSapAction.do');">Oferta SAP</a></li>
									</cata:temPermissao>
									<%-- 
									<cata:temPermissao acao="modeloVendaMatrizOferta">
										<li class="menu-item-link" id="mn_parametrizacao_matriz_oferta_modeloVenda"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/modeloVenda/ModeloVendaMatrizOfertaController.jpf');">Modelo de Vendas</a></li>
									</cata:temPermissao>
									--%>

								</ul>
							</li>
							
							<li class="menu-item" id="mn_parametrizacao_pagamento"><a href="javascript:TwMenu('sub_parametrizacao_pagamento',2);void(0);">Pagamento</a>
								<ul id="sub_parametrizacao_pagamento">
									<cata:temPermissao acao="acessarBandeira">
										<li class="menu-item-link" id="mn_parametrizacao_pagamento_cadastro_bandeira"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/bandeira/CadastroBandeiraAction.do">Bandeira</a></li>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarFormaCondPagtoTelevenda">
										<li class="menu-item-link" id="mn_parametrizacao_pagamento_forma_condicao"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/FormaCondicaoPagamentoAction.do">Formas e Condições <br /> de Pagamento<br /><br /></a></li>
									</cata:temPermissao>
								</ul>
							</li>
							
							<li class="menu-item" id="mn_parametrizacao_planoServico"><a href="javascript:TwMenu('sub_parametrizacao_planoServico',2);void(0);">Planos Servi&ccedil;os</a>
								<ul id="sub_parametrizacao_planoServico">
									<cata:temPermissao acao="acessarSubSegmento">
										<li class="menu-item-link" id="mn_parametrizacao_segmento_planos_servicos"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/SegmentoPlanoServicoAction.do">Configura&ccedil;&atilde;o de SubSegmento</a></li>									
									</cata:temPermissao>
									<cata:temPermissao acao="paramentrizacaoPlanosServicos">
										<li class="menu-item-link" id="mn_parametrizacao_planos_servicos"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/parametrizacao/ParametrizacaoPlanoServicoAction.do');">Parametriza&ccedil;&atilde;o</a></li>
										<li class="menu-item-link" id="mn_parametrizacao_plnsrv_restricaouf"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/restricaouf/RestricaoUFPlanoServicoAction.do">Restrição por UF</a></li>
									</cata:temPermissao>
							<%-- 
									<cata:temPermissao acao="paramentrizacaoPlanosServicos">
										<li class="menu-item-link" id="mn_parametrizacao_cdAnalise_credito"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/cdAnaliseCredito/AtribuicaoCodigoAnaliseCreditoController.jpf');">Atribui&ccedil;&atilde;o de C&oacute;digo<br>An&aacute;lise Cr&eacute;dito</a></li>
									</cata:temPermissao>
							--%>		
								</ul>
							</li>
							<li class="menu-item" id="mn_parametrizacao_produtos"><a href="javascript:TwMenu('sub_parametrizacao_produtos',2);void(0);">Produtos</a>
								<ul id="sub_parametrizacao_produtos">
								    
								    <cata:temPermissao acao="consultarAssociacaoTecnologiaTipoFrequencia">
									<li class="menu-item-link" id="mn_parametrizacao_produtos_associar"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/associacaoTecnologiaFrequencia/AssociacaoTecnologiaFrequenciaAction.do');">Associar<br>Tecnologia e Tipo<br>de Frequ&ecirc;ncia</a></li>
									</cata:temPermissao>

								    <cata:temPermissao acao="produtoCor">
									<li class="menu-item-link" id="menu_cadastroCor"><a onclick="document.location.href='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/cor/CorAction.do';">Cadastro de Cor</a></li>
									</cata:temPermissao>

									<cata:temPermissao acao="consultarCaracteristica">
								    <li class="menu-item-link" id="menu_CaracteristicasController"><a onclick="document.location.href='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/CaracteristicaAction.do';">Caracter&iacute;sticas</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="consultarDesconto">
									<li class="menu-item-link" id="mn_parametrizacao_produtos_desconto"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/descontos/DescontosAction.do');">Desconto</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="consultarFormasCondicoesPagamento">
									<li class="menu-item-link" id="mn_parametrizacao_produtos_precos"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/formasCondicoesPagamento/FormasCondicoesPagamentoAction.do');">Formas e<br>Condi&ccedil;&otilde;es de<br>Pagamento</a></li>	
									</cata:temPermissao>
									
									<cata:temPermissao acao="consultarFrequencia">
									<li class="menu-item-link" id="menu_FrequenciasController"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/frequencias/FrequenciasAction.do');">Frequ&ecirc;ncias</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="consultarModelo">
										<li class="menu-item-link" id="mn_parametrizacao_produtos_modelo"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelo/ModeloAction.do">Modelo</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="consultarProduto">
									<li class="menu-item-link" id="mn_parametrizacao_produtos_produtos"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/ProdutosAction.do">Produtos</a></li>	
									</cata:temPermissao>
																		
									<cata:temPermissao acao="consultarTecnologia">
									<li class="menu-item-link" id="mn_parametrizacao_produtos_tecnologia"><a  onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/tecnologias/TecnologiasAction.do');">Tecnologia</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="consultarTipoFrequencia">
									<li class="menu-item-link" id="menu_TipoFrequenciaController"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/tipoFrequencia/TipoFrequenciaAction.do');">Tipo de<br>Frequ&ecirc;ncias</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="pacoteBonusServicos">
										<li class="menu-item-link" id="menu_PacoteBonus"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/pacotebonus/PacoteBonusAction.do';">Serviço<br /> Interatividade</a></li>
									</cata:temPermissao>
									
									<!--Esperar da Vivo -->
								<%-- 
									<cata:temPermissao acao="consultarTipoFrequencia">
									<li class="menu-item-link" id="mn_parametrizacao_produtos_variavelModelo"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/variaveisModelo/AssociarVariaveisModeloController.jpf');">Vari&aacute;veis do<br>Modelo</a></li>
									</cata:temPermissao>
								--%>
								
									<cata:temPermissao acao="produtoGrupoCaract">
									    <li class="menu-item-link" id="mn_parametrizacao_grupo_caracteristicas"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/grupoCaracteristica/GrupoCaracteristicaAction.do">Grupo de <br>Caracter&iacute;sticas</a></li>
									</cata:temPermissao>		
									
									<cata:temPermissao acao="acessarConfiguraRestricao">
										<li class="menu-item-link" id="mn_parametrizacao_configurar_restricao"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricao/AssociacaoModeloRestricaoAction.do';" >Configurar Restrição</a></li>
									</cata:temPermissao>
								</ul>
							</li>
							
							<li class="menu-item" id="mn_parametrizacao_programa_pontos"><a href="javascript:TwMenu('sub_parametrizacao_programa_pontos',2);void(0);">Programa Pontos</a>
								<ul id="sub_parametrizacao_programa_pontos">
									<cata:temPermissao acao="disponibilidadeProduto">
										<li class="menu-item-link" id="mn_parametrizacaoProdutosPP"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/ParametrizacaoProdutosPPAction.do');">Produtos</a></li>
									</cata:temPermissao>
								</ul>
							</li>
							
							
							<li class="menu-item" id="mn_parametrizacao_analise"><a href="javascript:TwMenu('sub_parametrizacao_analise',2);void(0);">Análise Crédito</a>
								<ul id="sub_parametrizacao_analise">
									<cata:temPermissao acao="acessarCategoria">
										<li class="menu-item-link" id="mn_parametrizacao_analise_cadastro_categoria"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/CategoriaScoreAction.do">Categoria</a></li>
									</cata:temPermissao>
									<cata:temPermissao acao="acessarCategorizarFinanceiro">
										<li class="menu-item-link" id="mn_parametrizacao_analise_categorizacao"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/categorizacao/CategorizacaoAnaliseCreditoAction.do">Categorização Planos/Serviços/<br />Oferta Complementar</a></li>
									</cata:temPermissao>
									
									<cata:temPermissao acao="acessarConfiguracaoAnaliseCredito">
										<li class="menu-item-link" id="mn_parametrizacao_configuracao_analise"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/ConfiguracaoAnaliseCreditoAction.do">Configuração Análise<br /> de Crédito</a></li>
									</cata:temPermissao>
								</ul>
							</li>
							
						</ul>
					</li>
				</cata:temPermissao>

				<cata:temPermissao acao="acessarConsultaImportacaoRelacionamento,acessarConsultaImportacaoSCxGCxPMxAC,acessarRelatorioServico,acessarRelatorioSolicitacaoComercial,acessarRelatorioGrupoComercial,acessarRelatorioRelacionamento,acessarRelatorioSCxGCxPMxAC,consultaModelo,consultaRestricoesModelo,consultaServicos,consultaPlanos,consultaPerfilAcessoPlanosServicos,consultaMatrizOfertaImportacao">
					<li id="mn_consultas" class="menu-item-pai-off"><a href="javascript:TwMenu('sub_consultas',1);void(0);">Consultas</a>
						<ul id="sub_consultas">
								<li class="menu-item" id="mn_consultas_acesso"><a href="javascript:TwMenu('sub_consultas_acesso',2);void(0);">Acesso</a>
									<ul id="sub_consultas_acesso">
										<cata:temPermissao acao="consultaPerfilAcessoPlanosServicos">
											<li class="menu-item-link" id="mn_consulta_acesso_planos_servicos"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/ConsultaAcessoPlanosServicosAction.do');">Planos e Serviços</a></li>
										</cata:temPermissao>
									</ul>
								</li>
								
								<li class="menu-item" id="mn_consultas_fixa"><a href="javascript:TwMenu('sub_consultas_fixa',2);void(0);">Fixa</a>
									<ul id="sub_consultas_fixa">
										<cata:temPermissao acao="acessarRelatorioServico,acessarRelatorioSolicitacaoComercial,acessarRelatorioGrupoComercial,acessarRelatorioRelacionamento,acessarRelatorioSCxGCxPMxAC">
											<li class="menu-item-link" id="mn_consulta_fixa_relatorio"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/RelatorioFixaAction.do';">Relat&oacute;rio</a></li>
										</cata:temPermissao>
										<cata:temPermissao acao="acessarConsultaImportacaoRelacionamento,acessarConsultaImportacaoSCxGCxPMxAC">
											<li class="menu-item-link" id="mn_consultas_fixa_importacao"><a onclick="document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/ImportacaoServicoFixaConsultaAction.do';">Importa&ccedil;&atilde;o</a></li>
										</cata:temPermissao>
									</ul>
								</li>

								<li class="menu-item" id="mn_consultas_matriz"><a href="javascript:TwMenu('sub_consultas_matriz',2);void(0);">Matriz de Oferta</a>
									<ul id="sub_consultas_matriz">
										<cata:temPermissao acao="consultaMatrizOfertaImportacao">
											<li class="menu-item-link" id="mn_consultas_matriz_importacao"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/matrizOferta/importacao/ConsultaImportacaoMatrizOfertaAction.do');">Importa&ccedil;&atilde;o</a></li>
										</cata:temPermissao>
									</ul>
								</li>
							
								<li class="menu-item" id="mn_consultas_produtos"><a href="javascript:TwMenu('sub_consultas_produtos',2);void(0);">Produtos</a>
									<ul id="sub_consultas_produtos">
										<cata:temPermissao acao="consultaModelo">
											<li class="menu-item-link" id="mn_consultas_produtos_consultaModelo"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaModelo/ConsultaModeloAction.do');">Consulta Modelo</a></li>
										</cata:temPermissao>
										
										<cata:temPermissao acao="consultaRestricoesModelo">
											<li class="menu-item-link" id="mn_consultas_produtos_OfertaProduto">
											<a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/produto/consultaRestricaoModelo/ConsultaRestricaoModeloAction.do');">Consulta<br>Restri&ccedil;&otilde;es<br>do Modelo</a></li>
										</cata:temPermissao>
										
										<cata:temPermissao acao="acessarConfiguraRestricao">
											<li class="menu-item-link" id="mn_consultas_produtos_configurar_restricao"><a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/modelorestricaoconsulta/ConsultaModeloRestricaoAction.do">Consulta<br>Configurar<br>Restri&ccedil;&atilde;o</a></li>
										</cata:temPermissao>
									</ul>
								</li>
							<cata:temPermissao acao="consultaPlanos">
								<li class="menu-item" id="mn_consultas_planos"><a href="javascript:TwMenu('sub_consultas_planos',2);void(0);">Planos</a>
									<ul id="sub_consultas_planos">
										<li class="menu-item-link" id="mn_consultas_planos_plano"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/planos/consultaPlanos/ConsultaPlanosAction.do');">Consulta Planos</a></li>
									</ul>	
								</li>
							</cata:temPermissao>
							<cata:temPermissao acao="consultaServicos">
								<li class="menu-item" id="mn_consultas_servicos"><a href="javascript:TwMenu('sub_consultas_servicos',2);void(0);">Servi&ccedil;os</a>
									<ul id="sub_consultas_servicos">									    
										<li class="menu-item-link" id="mn_consultas_servicos_servicos"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/consulta/servicos/consultaServicos/ConsultaServicosAction.do');">Consulta Servi&ccedil;os</a></li>
									</ul>	
								</li>
							</cata:temPermissao>
						</ul>
					</li>
				</cata:temPermissao>
				<li class="menu-item-pai-off">
					<a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/inicio/carregarAlterarSenha.do?alterar_senha_username=${logged_user.user.username}">Alterar Senha</a>
				</li>

				<cata:temPermissao acao="parametrizacaoErros">
					<li id="mn_administracao" class="menu-item-pai-off"><a href="javascript:TwMenu('sub_administracao',1);void(0);">Administra&ccedil;&atilde;o</a>
						<ul id="sub_administracao">
							<li class="menu-item" id="mn_tradutor_erros"><a href="javascript:TwMenu('sub_tradutor_erros',2);void(0);">Tradutor de Erros</a>
								<ul id="sub_tradutor_erros">
									<li class="menu-item-link" id="mn_servico_negocio"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servicoNegocio/ServicoNegocioAction.do');">Servi&ccedil;o Negocio</a></li>
									<li class="menu-item-link" id="mn_sistema_legado"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/sistemaLegado/SistemaLegadoAction.do');">Sistema Legado</a></li>
									<li class="menu-item-link" id="mn_enablement"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/servico/ServicoEnablementAction.do');">Enablement</a></li>
									<li class="menu-item-link" id="mn_erro_comum"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/novoErro/erroComum/ErroComumAction.do');">Erro Comum</a></li>
									<li class="menu-item-link" id="mn_erros_cadastros"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/errosCadastros/ErrosCadastrosAction.do');">Erros Cadastrados</a></li>
									<li class="menu-item-link" id="mn_historico"><a onclick="chamadaLinkMenu('/catalogo/br/com/vivo/catalogoPRS/pageflows/tradutorErro/Controller/historico/HistoricoAction.do');">Hist&oacute;rico</a></li>
				  				</ul>
							</li>
						</ul>
					</li>
				</cata:temPermissao>
			</ul>
	    </div>
	  <!-- FIM MENU -->