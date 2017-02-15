/*****************************************************************************
 *
 * Modulo:    CttUfoRemove
 * Arquivo:   CttUfoRemove.cpp
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
#define CttUfoRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttUfo.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttUfoRemove);

/**************************************************************************
 *  Funcao de Negocios:  CttUfoRemove
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
void implCttUfoRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttUfoRemove::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContatoUfoperadora oContatoUfoperadora;
	char* cidContatoUFOperadora = oSafePointer.getTag( dnode, "idContatoUFOperadora", 0 );
	char* cidUser = getUser();
	
	if( strlennull( cidContatoUFOperadora ) <= 0 )
	{
		setStatusCode("14E0001","idContatoUFOperadora está nulo");
		ULOG_END("implCttUfoRemove::Execute()");
		return;
	}

	if( oContatoUfoperadora.Delete( cidContatoUFOperadora ) )
		setStatusCode("14I0000","Serviço executado com sucesso");
	else
		setStatusCode("14E0001","Falha ao tentar apagar o idContatoUFOperadora");
		
	ULOG_END("implCttUfoRemove::Execute()");
}
