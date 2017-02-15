/*****************************************************************************
 *
 * Modulo:    FrdNomListarId
 * Arquivo:   FrdNomListarId.cpp
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
#define FrdNomListarIdCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdNom.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdNomListarId);

/**************************************************************************
 *  Funcao de Negocios:  SrvEditar
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
void implFrdNomListarId::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdNomListarId::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CNomeFeriado oNomeFeriado;
	char* cidNomeFeriado = oSafePointer.getTag( dnode, "idNomeFeriado", 0 );
	if( cidNomeFeriado == NULL )
	{
		setStatusCode("14E0000","idNomeFeriado esta nulo");
		ULOG_END("implFrdNomListarId::Execute()");
		return;
	}
	oNomeFeriado.ListId( cidNomeFeriado );
	oNomeFeriado.GetXml("NomeFeriadoVO", xml_g);
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	ULOG_END("implFrdNomListarId::Execute()");
}
