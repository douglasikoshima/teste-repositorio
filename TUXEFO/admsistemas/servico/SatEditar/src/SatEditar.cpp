/*****************************************************************************
 *
 * Modulo:    SatEditar
 * Arquivo:   SatEditar.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define SatEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CSat.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SatEditar);

/**************************************************************************
 *  Funcao de Negocios:  SatEditar
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
void implSatEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implSatEditar::Execute()");
	CSafePointer oSafePointer;
	CPesquisaSatisfacao oPesquisaSatisfacao;
	char* cidQuestionario = oSafePointer.getTag( dnode, "idQuestionario", 0 );
	char* cdsQuestionario = oSafePointer.getTag( dnode, "dsQuestionario", 0 );
	char* cidUser = getUser();

	if( strlennull( cidQuestionario ) <= 0 )
	{
		setStatusCode("14E0001", "idQuestionario está nulo" );
		ULOG_END("implSatEditar::Execute()");
		return;
	}
	if( strlennull( cdsQuestionario ) <= 0 )
	{
		setStatusCode("14E0002", "dsQuestionario está nulo" );
		ULOG_END("implSatEditar::Execute()");
		return;
	}

	switch( oPesquisaSatisfacao.Update( cidQuestionario
						               ,cdsQuestionario
						               ,cidUser ) )
	{
		case 0:	setStatusCode("14W0001", "Questionário já cadastrado" );break;
		case 1:	setStatusCode("14I0000", "Operação concluída com sucesso!");break;
		case 2:	setStatusCode("14W0002", "Questionário já cadastrado" );break;
		default:setStatusCode("14E0003", "Erro não listado" );break;
	}
	ULOG_END("implSatEditar::Execute()");
}
