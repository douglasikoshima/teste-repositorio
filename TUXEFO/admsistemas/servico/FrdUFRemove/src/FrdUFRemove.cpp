/*****************************************************************************
 *
 * Modulo:    FrdUFRemove
 * Arquivo:   FrdUFRemove.cpp
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
#define FrdUFRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdUF.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdUFRemove);

/**************************************************************************
 *  Funcao de Negocios:  FrdUFRemove
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
void implFrdUFRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdUFRemove::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CUFFeriado oUFFeriado;
	char* cidUFFeriado = oSafePointer.getTag( dnode, "idUFFeriado", 0 );
	if( cidUFFeriado == NULL )
	{
		setStatusCode("14E0000","idUFFeriado esta nulo");
		ULOG_END("implFrdUFRemove::Execute()");
		return;
	}
	if( oUFFeriado.Delete( cidUFFeriado ) )
	{
		oUFFeriado.ListAll();
		oUFFeriado.GetXml("FeriadoNomeVO", xml_g);
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14E0000","Falha na tentativa de DELETE");
	ULOG_END("implFrdUFRemove::Execute()");
}
