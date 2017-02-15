/*****************************************************************************
 *
 * Modulo:    PERMOVER
 * Arquivo:   PERMOVER.cpp
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
#define PERMOVERCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CPer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PERMOVER);

/**************************************************************************
 *  Funcao de Negocios:  PERMOVER
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
void implPERMOVER::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implPERMOVER::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CPergunta oPergunta;
	char* cidPergunta = oSafePointer.getTag( dnode, "idPergunta", 0 );
	char* cinMoveUp = oSafePointer.getTag( dnode, "inMoveUp", 0 );
	char* cidUser = getUser();
	int   inMoveUp;
	if( strlennull( cinMoveUp ) > 0 )
	{
		if( strcmp( cinMoveUp, "0" ) == 0 )
			inMoveUp = 0;//Para cima
		else
			inMoveUp = 1;//Para baixo

		switch( oPergunta.Mover( cidPergunta, inMoveUp, cidUser ) ) 
		{
			case 0:	setStatusCode("14I0000","Servi�o executado com sucesso."); break;
			case 1: setStatusCode("14E0001","idPergunta est� nulo"); break;
			case 2: setStatusCode("14E0002","ID do usu�rio est� nulo"); break;
			case 3: setStatusCode("14E0003","N�o foi achado o question�rio para esta pergunta"); break;
			case 4: setStatusCode("14W0001","N�o h� como mover apenas um item"); break;
			case 5: setStatusCode("14W0002","Esta opera��o n�o pode ser realizada"); break;
			default: setStatusCode("14E9999","Erro n�o classificado."); break;
		}
	}
	else
		setStatusCode("14E0004","inMoveUp est� nulo");
	ULOG_END("implPERMOVER::Execute()");
	return;
}
