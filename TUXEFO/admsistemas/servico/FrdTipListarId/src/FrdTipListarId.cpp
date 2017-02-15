/*****************************************************************************
 *
 * Modulo:    FrdTipListarId
 * Arquivo:   FrdTipListarId.cpp
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
#define FrdTipListarIdCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdTip.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdTipListarId);

/**************************************************************************
 *  Funcao de Negocios:  FrdTipListarId
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
void implFrdTipListarId::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdTipListarId::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CTipoFeriado oTipoFeriado;
	char* cidTipoFeriado = oSafePointer.getTag( dnode, "idTipoFeriado", 0 );
	if( cidTipoFeriado == NULL )
	{
		setStatusCode("14E0000","idTipoFeriado esta nulo");
		ULOG_END("implFrdTipListarId::Execute()");
		return;
	}
	oTipoFeriado.ListId( cidTipoFeriado );
	oTipoFeriado.GetXml("TipoFeriadoVO", xml_g);
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	
	ULOG_END("implFrdTipListarId::Execute()");
}
