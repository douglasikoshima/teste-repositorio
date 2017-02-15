/*****************************************************************************
 *
 * Modulo:    ResRemove
 * Arquivo:   ResRemove.cpp
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
#define ResRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CRes.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ResRemove);

/**************************************************************************
 *  Funcao de Negocios:  ResRemove
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
void implResRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implResRemove::Execute()");
	CSafePointer oSafePointer;
	CResposta oResposta;
	char* cidResposta = oSafePointer.getTag( dnode, "idResposta", 0 );
	if( strlennull( cidResposta ) <= 0 )
	{
		setStatusCode("14E0000", "idResposta esta nulo" );
		ULOG_END("implResRemove::Execute()");
		return;
	}

	if( oResposta.Delete( cidResposta ) )
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	else
		setStatusCode("14W0000", "Resposta nao pode apagada pois esta sendo referenciada" );
		
	ULOG_END("implResRemove::Execute()");
}
