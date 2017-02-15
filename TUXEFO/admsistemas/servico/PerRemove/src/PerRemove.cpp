/*****************************************************************************
 *
 * Modulo:    PerRemove
 * Arquivo:   PerRemove.cpp
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
#define PerRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CPer.h"
#include "../../../negocio/admatdCmm/include/CRes.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PerRemove);

/**************************************************************************
 *  Funcao de Negocios:  PerRemove
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
void implPerRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implPerRemove::Execute()");
	CSafePointer oSafePointer;
	CPergunta oPergunta;
	CResposta oResposta;
	char* cidPergunta = oSafePointer.getTag( dnode, "idPergunta", 0 );
	if( strlennull( cidPergunta ) <= 0 )
	{
		setStatusCode("14E0000", "idPergunta esta nulo" );
		ULOG_END("implPerRemove::Execute()");
		return;
	}

	if( oResposta.DeleteIdPergunta( cidPergunta ) )
	{
		if( oPergunta.Delete( cidPergunta ) )
			setStatusCode("14I0000","Operacao concluida com sucesso!");
		else
			setStatusCode("14W0000", "Pergunta com depedencia, nao pode ser apagada" );
	}
	else
		setStatusCode("14W0000","Repostas desta pergunta contem dependencia e nao podem ser apagadas");
		
	ULOG_END("implPerRemove::Execute()");
}
