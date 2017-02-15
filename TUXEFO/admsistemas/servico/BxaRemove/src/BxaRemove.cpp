/*****************************************************************************
 *
 * Modulo:    BxaRemove
 * Arquivo:   BxaRemove.cpp
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
#define BxaRemoveCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxa.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BxaRemove);

/**************************************************************************
 *  Funcao de Negocios:  BxaRemove
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
void implBxaRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBxaRemove::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixa oBaixa;
	char* cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	char* cidUser = getUser();

	if( strlennull( cidBaixa ) <= 0 )
	{
	   
		setStatusCode("14E0002","idBaixa está nulo");
		ULOG_END("implBxaRemove::Execute()");
		return;
	}


	switch( oBaixa.Delete( cidBaixa, cidUser ) )
	{
		case 0: setStatusCode("14I0000","Operação concluída com sucesso!");break;
		case 1: setStatusCode("14W0001",oBaixa.GetErro());break;
		case 2: setStatusCode("14E0001",oBaixa.GetErro());break;
		default: setStatusCode("14E9999","Erro não classificado."); break;
	}
	ULOG_END("implBxaRemove::Execute()");
}
