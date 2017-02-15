/*****************************************************************************
 *
 * Modulo:    FrdUFRemoveFrd
 * Arquivo:   FrdUFRemoveFrd.cpp
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
#define FrdUFRemoveFrdCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdUF.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdUFRemoveFrd);

/**************************************************************************
 *  Funcao de Negocios:  FrdUFRemoveFrd
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
void implFrdUFRemoveFrd::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdUFRemoveFrd::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CUFFeriado oUFFeriado;
	char* cidFeriado = oSafePointer.getTag( dnode, "idFeriado", 0 );
	if( cidFeriado == NULL )
	{
		setStatusCode("14E0000","idFeriado esta nulo");
		ULOG_END("implFrdUFRemoveFrd::Execute()");
		return;
	}
	if( oUFFeriado.Delete( cidFeriado ) )
	{
		oUFFeriado.ListAll();
		oUFFeriado.GetXml("FeriadoNomeVO", xml_g);
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14E0000","Falha na tentativa de DELETE");
		
	ULOG_END("implFrdUFRemoveFrd::Execute()");
}
