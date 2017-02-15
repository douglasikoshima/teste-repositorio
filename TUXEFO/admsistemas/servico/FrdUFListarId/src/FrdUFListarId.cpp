/*****************************************************************************
 *
 * Modulo:    FrdUFListarId
 * Arquivo:   FrdUFListarId.cpp
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
#define FrdUFListarIdCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdUF.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdUFListarId);

/**************************************************************************
 *  Funcao de Negocios:  FrdUFListarId
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
void implFrdUFListarId::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdUFListarId::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CUFFeriado oUFFeriado;
	char* cidUFFeriado = oSafePointer.getTag( dnode, "idUFFeriado", 0 );
	if( cidUFFeriado == NULL )
	{
		setStatusCode("14E0000","idUFFeriado esta nulo");
		ULOG_END("implFrdUFListarId::Execute()");
		return;
	}
	oUFFeriado.ListId( cidUFFeriado );
	oUFFeriado.GetXml("UFFeriadoVO", xml_g);
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	ULOG_END("implFrdUFListarId::Execute()");
}
