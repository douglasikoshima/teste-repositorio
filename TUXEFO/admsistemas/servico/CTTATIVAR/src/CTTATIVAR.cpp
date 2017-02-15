/*****************************************************************************
 *
 * Modulo:    CTTATIVAR
 * Arquivo:   CTTATIVAR.cpp
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
#define CTTATIVARCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"
#include "../../../negocio/admatdCmm/include/CFlh.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTATIVAR);

/**************************************************************************
 *  Funcao de Negocios:  CTTATIVAR
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
//TIPO FECHAMENTO
#define FO_TF_ID_ENCERRADO                "1"
#define FO_TF_ID_ENCAMINAMENTO            "2"
#define FO_TF_ID_ENCAMINAMENTO_FECHAMENTO "3"
//TIPO SEQUENCIA
#define FO_TS_ID_ABERTURA                 "1"
#define FO_TS_ID_TRATAMENTO               "2"
#define FO_TS_ID_RETORNO                  "3"
//TIPO RETORNO CONTATO
#define FO_TR_ID_SEMRETORNO               "0"
#define FO_TR_ID_RETORNOGRUPOBKO          "1"
#define FO_TR_ID_RETORNOGRUPORETORNO      "2"
//FASE PROCESSO
#define FO_FP_ID_ABERTURA                 "1"
#define FO_FP_ID_TRATAMENTO               "2"
#define FO_FP_ID_FECHAMENTO               "3"


void implCTTATIVAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCTTATIVAR::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContato oContato;
	CContatoFolha oContatoFolha;

	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cinDisponibilidade = oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	char* cinDesabilitado = "0";
	char* cidUsuario = getUser();

	//Caso nao haja parametro, entende-se que esta desabilitando
	if( strlennull( cinDisponibilidade ) <= 0 )
		cinDisponibilidade = cinDesabilitado;

	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0001","idContato está nulo");
		ULOG_END("implCTTATIVAR::Execute()");
		return;
	}
	//Soh faz velidacoes se for habilitacao
	if( strcmp( cinDisponibilidade, "1" ) == 0 )
	{
		//Soh faz verificacoes se for folha
		if( oContatoFolha.ListId( cidContato ) > 0 )
		{
			//Verifica se o contato eh do tipo processo tratamento
			if( oContatoFolha.TemTipoFechamento( cidContato, FO_FP_ID_TRATAMENTO ) > 0 )
			{
				//Se for um dos dois tipos acima, tem que existir arvore de baixa associada
				if( oContato.TemArvoreBaixa( cidContato ) <= 0 )
				{
					setStatusCode("14W0001","Esta folha não pode ser habilitada porque necessita de árvore de baixa associada");
					ULOG_END("implCTTATIVAR::Execute()");
					return;
				}
			}
			//Toda folha tem que ter grupo de abertura
			if( oContato.TemGrupoAssociado( cidContato, FO_TS_ID_ABERTURA ) <= 0 )
			{
				setStatusCode("14W0003","Esta folha não pode ser habilitada porque necessita de grupo(s) de ABERTURA");
				ULOG_END("implCTTATIVAR::Execute()");
				return;
			}
			//Se existe TipoRetornoContato com TipoRetornoContato igual a RetornoGrupoRetorno, 
			//tem que verificar se tem grupo de retorno
			if( oContato.TemGrupoRetorno( cidContato, FO_TR_ID_RETORNOGRUPORETORNO ) > 0 )
			{
				if( oContato.TemGrupoAssociado( cidContato, FO_TS_ID_RETORNO ) <= 0 )
				{
					setStatusCode("14W0004","Esta folha não pode ser habilitada porque necessita de grupo(s) de RETORNO em tipo de retorno");
					ULOG_END("implCTTATIVAR::Execute()");
					return;
				}
			}
			//Verifica a existencia de tipo linha
			if( oContato.TemTipoLinha( cidContato ) <= 0 )
			{
				setStatusCode("14W0005","Esta folha não pode ser habilitada porque necessita de TIPO DE LINHA");
				ULOG_END("implCTTATIVAR::Execute()");
				return;
			}
			//Verifica a existencia de tipo de pessoa
			if( oContato.TemTipoRelacionamento( cidContato ) <= 0 )
			{
				setStatusCode("14W0006","Esta folha não pode ser habilitada porque necessita de TIPO DE PESSOA");
				ULOG_END("implCTTATIVAR::Execute()");
				return;
			}
			//Verifica a existencia de segmentacao
			if( oContato.TemSegmentacao( cidContato ) <= 0 )
			{
				setStatusCode("14W0007","Esta folha não pode ser habilitada porque necessita de SEGMENTACAO");
				ULOG_END("implCTTATIVAR::Execute()");
				return;
			}
			//Verifica a existencia de tipo carteira
			if( oContato.TemTipoCarteira( cidContato ) <= 0 )
			{
				setStatusCode("14W0008","Esta folha não pode ser habilitada porque necessita de TIPO CARTEIRA");
				ULOG_END("implCTTATIVAR::Execute()");
				return;
			}
			//Verifica a existencia de regionais
			if( oContato.TemRegionais( cidContato ) <= 0 )
			{
				setStatusCode("14W0009","Esta folha não pode ser habilitada porque necessita de REGIONAL");
				ULOG_END("implCTTATIVAR::Execute()");
				return;
			}
		}//if( oContatoFolha.ListId( cidContato ) > 0 )
	}//if( strcmp( cinDisponibilidade, "1" ) == 0 )
	switch( oContato.ativaInativaArvore( cidContato, cidUsuario,
						                 cinDisponibilidade ) )
	{
		case 0: setStatusCode("14I0000", "Operação realizada com sucesso" );break;
		case 1: setStatusCode("14E0002", "idContato está nulo" );break;
		case 2: setStatusCode("14E0003", "Contato não foi achado, idContato está errado" );break;
		case 3: setStatusCode("14W0010", "A raiz não pode ser habilitada nem desabilitada" );break;
		default: setStatusCode("14E9999", "Erro não listado" );break;
	}//switch
	ULOG_END("implCTTATIVAR::Execute()");
}
