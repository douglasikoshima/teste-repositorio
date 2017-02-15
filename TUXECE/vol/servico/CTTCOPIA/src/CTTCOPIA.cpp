/*****************************************************************************
 *
 * Modulo:    CTTCOPIA
 * Arquivo:   CTTCOPIA.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 12/11/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define CTTCOPIACPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"
#include "../../../negocio/admatdCmm/include/CFlh.h"

#include "../../../negocio/admatdCmm/include/CCttInf.h"
#include "../../../negocio/admatdCmm/include/CFlhCmp.h"
#include "../../../negocio/admatdCmm/include/CCttAca.h"
#include "../../../negocio/admatdCmm/include/CCttUfo.h"
#include "../../../negocio/admatdCmm/include/CFlhAnt.h"
#include "../../../negocio/admatdCmm/include/CFlhAvs.h"
#include "../../../negocio/admatdCmm/include/CFlhBxa.h"
#include "../../../negocio/admatdCmm/include/CFlhPrz.h"
#include "../../../negocio/admatdCmm/include/CCttLin.h"
#include "../../../negocio/admatdCmm/include/CCttPes.h"

#include "../../../negocio/admatdCmm/include/CContatoGrupo.h"
#include "../../../negocio/admatdCmm/include/CContatoSegmentacao.h"
#include "../../../negocio/admatdCmm/include/CContatoTipoCarteira.h"
#include "../../../negocio/admatdCmm/include/CContatoTipoRelacionamento.h"
#include "../../../negocio/admatdCmm/include/CPesquisaSatisfacaoTPPessoa.h"
#include "../../../negocio/admatdCmm/include/CContatoTipoRetorno.h"
#include "../../../negocio/admatdCmm/include/CSequencia.h"
#include "../../../negocio/admatdCmm/include/CTipoRetornoSequencia.h"
#include "../../../negocio/admatdCmm/include/CContatoTipoRetornoRelacoes.h"
#include "../../../negocio/admatdCmm/include/CContatoPesquisaSatisfacao.h"
#include "../../../negocio/admatdCmm/include/CFluxoFaseGrupo.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTCOPIA);

/**************************************************************************
 *  Funcao de Negocios:  CTTCOPIA
 *
 *  Descricao: chama funcao de negocios
 *
 *  Parametros de Entrada e Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de entrada
 *
 *  Parametros de Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de saida
 *
 *************************************************************************/
void implCTTCOPIA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCTTCOPIA::Execute()");
//Variaveis
	int iStatus = 0;
	int iAux;
	int iCont;
	int iqtdCopia = 0;
	int iCopia;
///////////////////////
//Classes para pesquisa
///////////////////////
	CSafePointer oSafePointer;
	CContatoFolha oContatoFolha;
	CContato oContato;
	CContatoInformacao oContatoInformacao;
	CContatoFolhaCampo oContatoFolhaCampo;
	CContatoAcao oContatoAcao;
	CContatoUfoperadora oContatoUfoperadora;
	CIndicadorContatoFolha oIndicadorContatoFolha;
	CContatoFolhaMensagemAviso oContatoFolhaMensagemAviso;
	CContatoFolhaBaixa oContatoFolhaBaixa;
	CPrazoAtendimento oPrazoAtendimento;
	CContatoTipoLinha oContatoTipoLinha;
	CContatoGrupo oContatoGrupo;
	CContatoSegmentacao oContatoSegmentacao;
	CContatoTipoCarteira oContatoTipoCarteira;
	CContatoPesquisaSatisfacao oContatoPesquisaSatisfacao;
	CContatoTipoRetorno oContatoTipoRetorno;
	CSequencia oSequencia;
	CTipoRetornoSequencia oTipoRetornoSequencia;

	CContatoTipoRetornoRelacoes oContatoTipoRelacoesCanal;
	CContatoTipoRetornoRelacoes oContatoTipoRelacoesTpRelacionamento;
	CContatoTipoRetornoRelacoes oContatoTipoRelacoesSegmentacao;
	CContatoTipoRetornoRelacoes oContatoTipoRelacoesProcedencia;
	CContatoTipoRetornoRelacoes oContatoTipoRelacoesTipoLinha;
	CContatoTipoRetornoRelacoes oContatoTipoRelacoesTipoCarteira;
	CContatoTipoRetornoRelacoes oContatoTipoRetornoRelacoes;

///////////////////////
//Classes para insercao
///////////////////////
	CContatoFolha oContatoFolhaIns;
	CContato oContatoIns;
	CContatoInformacao oContatoInformacaoIns;
	CContatoAcao oContatoAcaoIns;
	CContatoUfoperadora oContatoUfoperadoraIns;
	CIndicadorContatoFolha oIndicadorContatoFolhaIns;
	CContatoFolhaMensagemAviso oContatoFolhaMensagemAvisoIns;
	CContatoFolhaBaixa oContatoFolhaBaixaIns;
	CPrazoAtendimento oPrazoAtendimentoIns;
	CContatoTipoLinha oContatoTipoLinhaIns;
	CContatoGrupo oContatoGrupoIns;
	CContatoSegmentacao oContatoSegmentacaoIns;
	CContatoTipoCarteira oContatoTipoCarteiraIns;
	CContatoTipoRelacionamento oContatoTipoRelacionamentoIns;
	CContatoPesquisaSatisfacao oContatoPesquisaSatisfacaoIns;
	CPesquisaSatisfacaoUF oPesquisaSatisfacaoUFIns;
	CPesquisaSatisfacaoTPPessoa oPesquisaSatisfacaoTPPessoaIns;
	CContatoTipoRetorno oContatoTipoRetornoIns;
	CSequencia oSequenciaIns;
	CTipoRetornoSequencia oTipoRetornoSequenciaIns;
	CContatoTipoRetornoRelacoes oContatoTipoRetornoRelacoesIns;
	CFluxoFaseGrupo oFluxoFaseGrupoIns;

////////////////////////
//Lendo o XML de entrada
////////////////////////
	char* cidContatoDestino = oSafePointer.getTag( dnode, "idContatoDestino", 0 );
	char* cidContatoOrigem = oSafePointer.getTag( dnode, "idContatoOrigem", 0 );
	char* cqtdCopia = oSafePointer.getTag( dnode, "qtdCopia", 0 );
    char* cidTipoArvore = oSafePointer.getTag( dnode, "idTipoArvore", 0 );
	char* cidUser = getUser();
	

	//Faz algumas verificacoes
	if( oContato.ListContato( cidContatoDestino ) <= 0 )
	{
		setStatusCode("14E0001", "O contato de destino não existe" );
		ULOG_END("implCTTCOPIA::Execute()");
		return;
	}
	//Nao trocar esta pesquisa de posicao, pois a classe oContato armazena os dados de origem
	//A cada execucao ela perde os dados anteriores
	if( oContato.ListContato( cidContatoOrigem ) <= 0 )
	{
		setStatusCode("14E0002", "O contato de Origem não existe" );
		ULOG_END("implCTTCOPIA::Execute()");
		return;
	}
//Recupera o contato e ja aproveita e faz validacao
	if( strcmp( cidContatoDestino, cidContatoOrigem ) == 0 )
	{
		setStatusCode("14W0001", "Os contatos de origem e destino não podem ser iguais" );
		ULOG_END("implCTTCOPIA::Execute()");
		return;
	}
	if( oContatoFolha.ListId( cidContatoDestino ) > 0 )
	{
		setStatusCode("14W0002", "O contato de destino não pode ser uma folha" );
		ULOG_END("implCTTCOPIA::Execute()");
		return;
	}
	//if( strcmp( cidContatoDestino, oContato.Registro()->cidContatoPai ) == 0 )
	//{
	//	setStatusCode("14W0003", "O folha não pode ser copiada para a mesma pasta" );
	//	return;
	//}

// Recupera Contato Folha e ja aproveita e faz validacao
	//Nao trocar esta pesquisa de posicao, pois a classe oContatoFolha armazena os dados de origem
	//A cada execucao ela perde os dados anteriores
	if( oContatoFolha.ListId( cidContatoOrigem ) <= 0 )
	{
		setStatusCode("14W0004", "O contato de origem deve ser uma folha" );
		ULOG_END("implCTTCOPIA::Execute()");
		return;
	}
	//Neste ponto temos os dados da folha (somente dados da tabela contatoadm.contatofolha) e
	//os dados de contato ( contatoadm.contato ) em oContatoFolha e oContato respectivamente
	if( strlennull( cqtdCopia ) <= 0 )
		iqtdCopia = 1;
	else
		iqtdCopia = atoi( cqtdCopia );


//Recupera ContatoInformacao
	ULOGI("oContatoInformacao.ListId( %s );", cidContatoOrigem );
	oContatoInformacao.ListId( cidContatoOrigem );
//Recupera ContatoFolhaCampo
	//ULOGI("oContatoFolhaCampo.ListId( %s );", cidContatoOrigem );
	//oContatoFolhaCampo.ListId( cidContatoOrigem );
//Recupera ContatoAcao
	ULOGI("oContatoAcao.ListIdContato( %s );", cidContatoOrigem );
	oContatoAcao.ListIdContato( cidContatoOrigem );
//Recupera ContatoUfoperadora
	ULOGI("oIndicadorContatoFolha.ListIdContatoFolha( %s );", cidContatoOrigem );
	oContatoUfoperadora.ListIdContato( cidContatoOrigem );
//Recupera IndicadorContatoFolha
	ULOGI("oIndicadorContatoFolha.ListIdContatoFolha( %s );", cidContatoOrigem );
	oIndicadorContatoFolha.ListIdContatoFolha( cidContatoOrigem );
//Recupera CContatoFolhaMensagemAviso
	ULOGI("oContatoFolhaMensagemAviso.ListId( %s );", cidContatoOrigem );
	oContatoFolhaMensagemAviso.ListId( cidContatoOrigem );
//Recupera ContatoFolhaBaixa
	ULOGI("oContatoFolhaBaixa.ListIdContato( %s );", cidContatoOrigem );
	oContatoFolhaBaixa.ListIdContato( cidContatoOrigem );
//Recupera PrazoAtendimento
	ULOGI("oPrazoAtendimento.ListIdContato( %s );", cidContatoOrigem );
	oPrazoAtendimento.ListIdContato( cidContatoOrigem );
//Recupera ContatoTipoLinha
	ULOGI("oContatoTipoLinha.ListIdContato( %s );", cidContatoOrigem );
	oContatoTipoLinha.ListIdContato( cidContatoOrigem );
//Recupera ContatoGrupo
	ULOGI("oContatoGrupo.RelacaoPorIdContato( %s );", cidContatoOrigem );
	oContatoGrupo.RelacaoPorIdContato( cidContatoOrigem );
//REcupera ContatoSegmentacao
	ULOGI("oContatoSegmentacao.RelacaoPorIdContato( %s );", cidContatoOrigem );
	oContatoSegmentacao.RelacaoPorIdContato( cidContatoOrigem );
//Recupera ContatoTipoCarteira
	ULOGI("oContatoTipoCarteira.RelacaoPorIdContato( %s );", cidContatoOrigem );
	oContatoTipoCarteira.RelacaoPorIdContato( cidContatoOrigem );
//Recupera ContatoPesquisaSatisfacao
	ULOGI("oContatoPesquisaSatisfacao.RelacaoPorIdContato( %s );", cidContatoOrigem );
	oContatoPesquisaSatisfacao.RelacaoPorIdContato( cidContatoOrigem );
//Recupera ContatoTipoRetorno
	ULOGI("oContatoTipoRetorno.RelacaoPorIdContato( %s );", cidContatoOrigem );
	oContatoTipoRetorno.RelacaoPorIdContato( cidContatoOrigem );
//Recupera TipoRetornoSequencia
	ULOGI("oTipoRetornoSequencia.ListIdContato( %s );", cidContatoOrigem );
	oTipoRetornoSequencia.ListIdContato( cidContatoOrigem );
	
	//Realiza multiplas copias
	for( iCopia = 0; iCopia < iqtdCopia; iCopia++ )
	{
	    // Insere contato igual ao de origem
		iStatus = 1;
		
        ULOG("oContatoIns.InsertCopia('%s','%s','%s','%s,'%s,'%s');"
            , cidContatoDestino
            , oContato.Registro()->cidNomeContato
            , oContato.Registro()->cinDisponibilidade
            , cidUser
            , cidTipoArvore
            , oContato.Registro()->cdsTipoProcesso);

		switch( oContatoIns.InsertCopia( cidContatoDestino
							            ,oContato.Registro()->cidNomeContato
							            ,oContato.Registro()->cinDisponibilidade
							            ,cidUser
                                        ,cidTipoArvore
                                        ,oContato.Registro()->cdsTipoProcesso) )
		{
			case 0: iStatus = 0; break;
			case 1: setStatusCode("14E0003", oContatoIns.GetErro() ); break;
			default: setStatusCode("14E9999", "Erro não listado" ); break;
		}
		if( iStatus != 0 )
		{
		   ULOG_END("implCTTCOPIA::Execute()");
			return;
		}

	/*********************************************************************************
	 ***
	 *** OBSERVACAO: Na insercao acima, se bem sucedida a classe oContatoIns.Registro()->cidContato
	 ***             contem o idContato que sera utilizado em todas as insercao que envolvem contato
	 *********************************************************************************/
	// Insere uma folha, identica a de origem
		ULOGI("Insere uma folha, identica a de origem");
		if( oContatoFolhaIns.Insert( oContatoIns.Registro()->cidContato
									,oContatoFolha.Registro()->cidPagina
									,oContatoFolha.Registro()->cidTipoRetorno
									,oContatoFolha.Registro()->cidTipoFechamento
									,oContatoFolha.Registro()->cidTipoProcesso
									,oContatoFolha.Registro()->cqtHorasPrazo
									,oContatoFolha.Registro()->cinFechamentoImediato
									,oContatoFolha.Registro()->cvlPeso
									,oContatoFolha.Registro()->cinProcessoTecnico
									,cidUser
                                    ,oContatoFolha.Registro()->cqtHorasPrazoAnatel
                                    ,oContatoFolha.Registro()->cInSms
                                    ,oContatoFolha.Registro()->cDsSms
                                    ,oContatoFolha.Registro()->cInRelacionamento
                                    ,oContatoFolha.Registro()->cInProtocolo
                                    ,oContatoFolha.Registro()->cInexibeProtocolo
                                    ,oContatoFolha.Registro()->cDsContatoCanais
                                    ,oContatoFolha.Registro()->cDsMsgExcecao
									,oContatoFolha.Registro()->cInCancelamento
                                    ,oContatoFolha.Registro()->cIdClassificacaoSms
                                    ,oContatoFolha.Registro()->cInAberturaContato
                                    ,oContatoFolha.Registro()->cSgRegraEncaminhamento
                                    ,oContatoFolha.Registro()->cSgFluxoAtendimento) == 1 )

		{
	//Insere ContatoInformacao
		ULOGI("Insere ContatoInformacao");
			for( iCont = 0; iCont < oContatoInformacao.Quantidade(); iCont++ )
			{
				oContatoInformacaoIns.Insert( oContatoIns.Registro()->cidContato
											 ,oContatoInformacao.Registro(iCont)->cidUFOperadora
											 ,oContatoInformacao.Registro(iCont)->cidTipoLinha
											 ,oContatoInformacao.Registro(iCont)->cidTipoCliente
											 ,oContatoInformacao.Registro(iCont)->cnmURLContatoInformacao
											 ,cidUser );
			}
	//Insere oContatoFolhaCampo
		ULOGI("oContatoFolhaCampo.Copy(...)");
		oContatoFolhaCampo.Copy( cidContatoOrigem 
			                    ,oContatoIns.Registro()->cidContato
						        ,cidUser );
	//Insere oContatoAcao
		ULOGI("Insere oContatoAcao");
			for( iCont = 0; iCont < oContatoAcao.Quantidade(); iCont++ )
			{
				oContatoAcaoIns.Insert( oContatoAcao.Registro(iCont)->cidUFOperadora
									   ,oContatoAcao.Registro(iCont)->cidTipoLinha
									   ,oContatoAcao.Registro(iCont)->cnmURLContatoAcao
									   ,oContatoIns.Registro()->cidContato
									   ,cidUser );
			}
	//Insere ContatoUfoperadora
		ULOGI("Insere ContatoUfoperadora");
			for( iCont = 0; iCont < oContatoUfoperadora.Quantidade(); iCont++ )
			{
				oContatoUfoperadoraIns.Insert( oContatoIns.Registro()->cidContato
											  ,oContatoUfoperadora.Registro(iCont)->cidUFOperadora
											  ,oContatoUfoperadora.Registro(iCont)->cdtInicioVigencia
											  ,oContatoUfoperadora.Registro(iCont)->cdtFimVigencia
											  ,oContatoUfoperadora.Registro(iCont)->cinDisponibilidade
											  ,cidUser );
			}
	//Insere oIndicadorContatoFolha
		ULOGI("Insere oIndicadorContatoFolha");
			for( iCont = 0; iCont < oIndicadorContatoFolha.Quantidade(); iCont++ )
			{
				oIndicadorContatoFolhaIns.Insert( oContatoIns.Registro()->cidContato
												 ,oIndicadorContatoFolha.Registro(iCont)->cidIndicadorAnatel
												 ,cidUser );
			}
	//Insere ContatoFolhaMensagemAviso
		ULOGI("Insere ContatoFolhaMensagemAviso");
			for( iCont = 0; iCont < oContatoFolhaMensagemAviso.Quantidade(); iCont++ )
			{
				oContatoFolhaMensagemAvisoIns.Insert( oContatoIns.Registro()->cidContato
													 ,oContatoFolhaMensagemAviso.Registro(iCont)->cidMensagemAviso
													 ,cidUser );
			}
	//Insere ContatoFolhaBaixa
		ULOGI("Insere ContatoFolhaBaixa");
			for( iCont = 0; iCont < oContatoFolhaBaixa.Quantidade(); iCont++ )
			{
				oContatoFolhaBaixaIns.Insert( oContatoIns.Registro()->cidContato
											 ,oContatoFolhaBaixa.Registro(iCont)->cidBaixa
											 ,cidUser );
			}
	//Insere PrazoAtendimento
		ULOGI("Insere PrazoAtendimento");
			for( iCont = 0; iCont < oPrazoAtendimento.Quantidade(); iCont++ )
			{
				oPrazoAtendimentoIns.Insert( oContatoIns.Registro()->cidContato
											,oPrazoAtendimento.Registro(iCont)->cidSegmentacao
											,oPrazoAtendimento.Registro(iCont)->cidProcedencia
											,oPrazoAtendimento.Registro(iCont)->cqtDiasPrazoAtendimento
											,cidUser );
			}
	//Insere ContatoTipoLinha
		ULOGI("();");
			for( iCont = 0; iCont < oContatoTipoLinha.Quantidade(); iCont++ )
			{
				oContatoTipoLinhaIns.Insert( oContatoIns.Registro()->cidContato
											,oContatoTipoLinha.Registro(iCont)->cidTipoLinha
											,cidUser );
			}
	//Insere ContatoGrupo, Sequencia e NivelSequencia
		ULOGI("Insere ContatoGrupo, Sequencia e NivelSequencia");
			for( iCont = 0; iCont < oContatoGrupo.Quantidade(); iCont++ )
			{
		ULOGI("oContatoGrupoIns.Insert");
				oContatoGrupoIns.Insert( oContatoIns.Registro()->cidContato
										,oContatoGrupo.Registro(iCont)->cidGrupo
										,cidUser );
				//Recupera todas as sequencias de um dos contatos grupos de origem
				oSequencia.RelacaoPoridContatoGrupo( oContatoGrupo.Registro(iCont)->cidContatoGrupo );
				for( iAux = 0; iAux < oSequencia.Quantidade(); iAux++ )
				{
					
		ULOGI("oSequenciaIns.Insert");
        // ULOG( ">>> TipoSequencia [%s]", oSequencia.Registro(iCont)->cidTipoSequencia );

                    // oSequenciaIns.Insert( oContatoGrupoIns.Registro(oContatoGrupoIns.Ultimo())->cidContatoGrupo //Recupera sempre o ultimo inserido
										 // ,oSequencia.Registro(iCont)->cidTipoSequencia
										 // ,oSequencia.Registro(iCont)->csqOrdem
										 // ,oContatoGrupoIns.Registro(oContatoGrupoIns.Ultimo())->cidGrupo //Recupera sempre o ultimo inserido
										 // ,cidUser );

                       oSequenciaIns.Insert( oContatoGrupoIns.Registro(oContatoGrupoIns.Ultimo())->cidContatoGrupo //Recupera sempre o ultimo inserido
										 ,oSequencia.Registro(iAux)->cidTipoSequencia
										 ,oSequencia.Registro(iAux)->csqOrdem
										 ,oContatoGrupoIns.Registro(oContatoGrupoIns.Ultimo())->cidGrupo //Recupera sempre o ultimo inserido
										 ,cidUser );
                                         
				}
			}
	//Insere ContatoSegmentacao
		ULOGI("Insere ContatoSegmentacao");
			for( iCont = 0; iCont < oContatoSegmentacao.Quantidade(); iCont++ )
			{
				oContatoSegmentacaoIns.Insert( oContatoIns.Registro()->cidContato
											  ,oContatoSegmentacao.Registro(iCont)->cidSegmentacao
											  ,cidUser );
			}

	//Insere ContatoTipoCarteira
		ULOGI("Insere ContatoTipoCarteira");
			for( iCont = 0; iCont < oContatoTipoCarteira.Quantidade(); iCont++ )
			{
				oContatoTipoCarteiraIns.Insert( oContatoIns.Registro()->cidContato
											   ,oContatoTipoCarteira.Registro(iCont)->cidTipoCarteira
											   ,cidUser );
			}
	//Insere ContatoTipoRelacionamento
		ULOGI("Insere ContatoTipoRelacionamento");
		oContatoTipoRelacionamentoIns.InsertSelect( cidContatoOrigem
			                                 ,oContatoIns.Registro()->cidContato
											 ,cidUser );
	//Insere ContatoPesquisaSatisfacao
		ULOGI("Insere ContatoPesquisaSatisfacao");
			for( iCont = 0; iCont < oContatoPesquisaSatisfacao.Quantidade(); iCont++ )
			{
				oContatoPesquisaSatisfacaoIns.Insert( oContatoIns.Registro()->cidContato
													 ,oContatoPesquisaSatisfacao.Registro(iCont)->cidPesquisaSatisfacao
													 ,cidUser );
			}
	//Insere ContatoPesquisaSatisfacao
		ULOGI("Insere ContatoPesquisaSatisfacao");
			for( iCont = 0; iCont < oContatoPesquisaSatisfacao.Quantidade(); iCont++ )
			{
    			//Soh insere se a classe pai tiver o relacionamento
				if( strlennull( oContatoPesquisaSatisfacao.Registro(iCont)->cidTipoPessoa ) > 0 )
				{
					//Tem que ser o mesmo tipo que o inserido
					if( ( iAux = oContatoPesquisaSatisfacaoIns.Find( oContatoPesquisaSatisfacao.Registro(iCont)->cidContato 
																	,oContatoPesquisaSatisfacao.Registro(iCont)->cidPesquisaSatisfacao ) ) != -1 )
					{
						oPesquisaSatisfacaoTPPessoaIns.Insert( oContatoPesquisaSatisfacaoIns.Registro(iAux)->cidTipoPessoa
															  ,cidUser );
					}
				}
			}
	//Insere PesquisaSatisfacaoUF
		ULOGI("Insere PesquisaSatisfacaoUF");
		oPesquisaSatisfacaoUFIns.Insert( cidContatoOrigem
										,oContatoIns.Registro()->cidContato
										,cidUser );
	//Insere ContatoTipoRetorno
		ULOGI("Insere ContatoTipoRetorno");
			for( iCont = 0; iCont < oContatoTipoRetorno.Quantidade(); iCont++ )
			{
				oContatoTipoRetornoIns.Insert( oContatoIns.Registro()->cidContato
											  ,oContatoTipoRetorno.Registro(iCont)->cidTipoRetornoContato
											  ,cidUser );
			}
	//Insere TipoRetornoSequencia
		ULOGI("Insere TipoRetornoSequencia");
			int iTipoRetorno;
			int iSequencia;
			for( iCont = 0; iCont < oTipoRetornoSequencia.Quantidade(); iCont++ )
			{
				iSequencia = oSequenciaIns.FindPoridGrupoTipoSequencia( oTipoRetornoSequencia.Registro(iCont)->cidGrupo 
																	   ,oTipoRetornoSequencia.Registro(iCont)->cidTipoSequencia );
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oTipoRetornoSequencia.Registro(iCont)->cidTipoRetornoContato );
				//Caso nao ache algum dos parametros, nao insere
				if( ( iTipoRetorno >= 0 ) && ( iSequencia >= 0 ) )
				{
                    if ( oTipoRetornoSequenciaIns.Existe( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
													,oSequenciaIns.Registro(iSequencia)->cidSequencia) == false )
                    {
					    ULOGI("oTipoRetornoSequenciaIns.Insert()");
					    oTipoRetornoSequenciaIns.Insert( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
													    ,oSequenciaIns.Registro(iSequencia)->cidSequencia
													    ,cidUser );
                    }
                    else
                    {
					    ULOG("IDCONTATOTIPORETORNO=%s,IDSEQUENCIA=%s (já existe e não foi copiado)",
                            oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno,
                            oSequenciaIns.Registro(iSequencia)->cidSequencia);
                    }
				}
			}
	//Insere em FluxoFaseGrupo
		ULOGI("Insere FluxoFaseGrupo");
		oFluxoFaseGrupoIns.Copia( cidContatoOrigem, 
							      oContatoIns.Registro()->cidContato,
				                  cidUser );
	//Recupera e insere todos os dados relacionados com ContatoTipoRetorno
		ULOGI("oContatoTipoRetornoRelacoes.ListIdContatoCanal();");
			oContatoTipoRetornoRelacoes.ListIdContatoCanal( cidContatoOrigem );
			for( iCont = 0; iCont < oContatoTipoRetornoRelacoes.Quantidade(); iCont++ )
			{
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidTipoRetornoContato );
				oContatoTipoRetornoRelacoesIns.InsertCanal( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidContatoRelacao
														   ,cidUser );
			}
		ULOGI("oContatoTipoRetornoRelacoes.ListIdContatoTpRelacionamento();");
			oContatoTipoRetornoRelacoes.ListIdContatoTpRelacionamento( cidContatoOrigem );
			for( iCont = 0; iCont < oContatoTipoRetornoRelacoes.Quantidade(); iCont++ )
			{ 
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidTipoRetornoContato );
				oContatoTipoRetornoRelacoesIns.InsertTpRelacionamento( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
																	  ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidContatoRelacao
																	  ,cidUser );
			}
		ULOGI("oContatoTipoRetornoRelacoes.ListIdContatoSegmentacao();");
			oContatoTipoRetornoRelacoes.ListIdContatoSegmentacao( cidContatoOrigem );
			for( iCont = 0; iCont < oContatoTipoRetornoRelacoes.Quantidade(); iCont++ )
			{
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidTipoRetornoContato );
				oContatoTipoRetornoRelacoesIns.InsertSegmentacao( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
																 ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidContatoRelacao
																 ,cidUser );
			}
		ULOGI("oContatoTipoRetornoRelacoes.ListIdContatoProcedencia();");
			oContatoTipoRetornoRelacoes.ListIdContatoProcedencia( cidContatoOrigem );
			for( iCont = 0; iCont < oContatoTipoRetornoRelacoes.Quantidade(); iCont++ )
			{
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidTipoRetornoContato );
				oContatoTipoRetornoRelacoesIns.InsertProcedencia( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
																 ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidContatoRelacao
																 ,cidUser );
			}
		ULOGI("oContatoTipoRetornoRelacoes.ListIdContatoTipoLinha();");
			oContatoTipoRetornoRelacoes.ListIdContatoTipoLinha( cidContatoOrigem );
			for( iCont = 0; iCont < oContatoTipoRetornoRelacoes.Quantidade(); iCont++ )
			{
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidTipoRetornoContato );
				oContatoTipoRetornoRelacoesIns.InsertTipoLinha( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
															   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidContatoRelacao
															   ,cidUser );
			}
		ULOGI("oContatoTipoRetornoRelacoes.ListIdContatoTipoCarteira();");
			oContatoTipoRetornoRelacoes.ListIdContatoTipoCarteira( cidContatoOrigem );
			for( iCont = 0; iCont < oContatoTipoRetornoRelacoes.Quantidade(); iCont++ )
			{
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidTipoRetornoContato );
				oContatoTipoRetornoRelacoesIns.InsertTipoCarteira( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
																  ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidContatoRelacao
																  ,cidUser );
			}
		ULOGI("oContatoTipoRetornoRelacoes.ListIdContatoTipoPessoa();");
			oContatoTipoRetornoRelacoes.ListIdContatoTipoPessoa( cidContatoOrigem );
			for( iCont = 0; iCont < oContatoTipoRetornoRelacoes.Quantidade(); iCont++ )
			{
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidTipoRetornoContato );
				oContatoTipoRetornoRelacoesIns.InsertTipoPessoa( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
																,oContatoTipoRetornoRelacoes.Registro(iCont)->cidContatoRelacao
																,cidUser );
			}
		ULOGI("oContatoTipoRetornoRelacoes.ListIdContatoRegional();");
			oContatoTipoRetornoRelacoes.ListIdContatoRegional( cidContatoOrigem );
			for( iCont = 0; iCont < oContatoTipoRetornoRelacoes.Quantidade(); iCont++ )
			{
				iTipoRetorno = oContatoTipoRetornoIns.Find( oContatoIns.Registro()->cidContato
														   ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidTipoRetornoContato );
				oContatoTipoRetornoRelacoesIns.InsertRegional( oContatoTipoRetornoIns.Registro(iTipoRetorno)->cidContatoTipoRetorno
															  ,oContatoTipoRetornoRelacoes.Registro(iCont)->cidContatoRelacao
															  ,cidUser );
			}
		ULOGI("oContatoGrupo.InsertCopiaRC();");
			oContatoGrupo.InsertCopiaRC( 
											cidContatoOrigem
										  , oContatoIns.Registro()->cidContato
										  , cidUser
				                       );
		ULOGI("oContatoGrupo.InsertCopiaCRI();");
			oContatoGrupo.InsertCopiaCRI( 
											cidContatoOrigem
										  , oContatoIns.Registro()->cidContato
										  , cidUser
				                       );
		}
		else
		{
			setStatusCode("14E0005", "Falha na inclusão de folha" );
			return;
		}
	}//for( iCopia = 0; iCopia < iqtdCopia; iCopia++ )
	setStatusCode("14I0000", "Operação realizada com sucesso" );
	ULOG_END("implCTTCOPIA::Execute()");
}
