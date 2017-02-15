/*****************************************************************************
 *
 * Modulo:    ImnMover
 * Arquivo:   ImnMover.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDJMartins          Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define ImnMoverCPP

//Header de Imn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CImn.h"
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ImnMover);

/**************************************************************************
 *  Funcao de Negocios:  ImnMover
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
void implImnMover::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implImnMover::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CImn oImn;
	char* cidItemMenu = oSafePointer.getTag( dnode, "idItemMenu", 0 );
	char* cinMoveUp = oSafePointer.getTag( dnode, "inMoveUp", 0 );
	char* cidUser = getUser();
	int   inMoveUp;
	if( strlennull( cinMoveUp ) > 0 )
	{
		if( strcmp( cinMoveUp, "0" ) == 0 )
			inMoveUp = 0;//Para cima
		else
			inMoveUp = 1;//Para baixo

		switch( oImn.ImnMover( cidItemMenu, inMoveUp, cidUser ) ) 
		{
			case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
			case 1: setStatusCode("08E0001","idItemMenu esta nulo"); break;
			case 2: setStatusCode("08E0002","ID do usuario esta nulo"); break;
			case 3: setStatusCode("08W0001","Raizes nao serao movidas"); break;
			case 4: setStatusCode("08W0002","Nao ha como mover apenas um item"); break;
			case 5: setStatusCode("08W0003","Esta operacao nao pode ser realizada"); break;
			default: setStatusCode("08E9999","Erro nao classificado."); break;
		}
	}
	else
		setStatusCode("08E0003","inMoveUp esta nulo");
	ULOG_END("implImnMover::Execute()");
	return;
}
