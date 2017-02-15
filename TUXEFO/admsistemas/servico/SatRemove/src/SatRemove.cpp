/*****************************************************************************
 *
 * Modulo:    SatRemove
 * Arquivo:   SatRemove.cpp
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
#define SatRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CSat.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SatRemove);

/**************************************************************************
 *  Funcao de Negocios:  SatRemove
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
void implSatRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implSatRemove::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CPesquisaSatisfacao oPesquisaSatisfacao;
	char* cidQuestionario = oSafePointer.getTag( dnode, "idQuestionario", 0 );
	if( strlennull( cidQuestionario ) <= 0 )
	{
		setStatusCode("14E0001","idQuestionario está nulo");
		ULOG_END("implSatRemove::Execute()");
		return;
	}
	if( oPesquisaSatisfacao.Delete( cidQuestionario ) )
	{
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14W0001", "Este item contem referencias e não pode ser apagado" );
	ULOG_END("implSatRemove::Execute()");
}
