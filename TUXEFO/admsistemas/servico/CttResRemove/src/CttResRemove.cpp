/*****************************************************************************
 *
 * Modulo:    CttResRemove
 * Arquivo:   CttResRemove.cpp
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
#define CttResRemoveCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttResRemove);

/**************************************************************************
 *  Funcao de Negocios:  CttResRemove
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
void implCttResRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/* Chamada de Funcao de Negocios */
	ULOG_START("implCttResRemove::Execute()");
	CSafePointer oSafePointer;
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	
	if( strlennull( cidContato ) > 0 )
	{
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14E0000","idContato esta nulo");
	ULOG_END("implCttResRemove::Execute()");
}
