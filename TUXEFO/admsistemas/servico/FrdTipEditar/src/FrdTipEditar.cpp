/*****************************************************************************
 *
 * Modulo:    FrdTipEditar
 * Arquivo:   FrdTipEditar.cpp
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
#define FrdTipEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdTip.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdTipEditar);

/**************************************************************************
 *  Funcao de Negocios:  FrdTipEditar
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
void implFrdTipEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdTipEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CTipoFeriado oTipoFeriado;
	char* cdsTipoFeriado = oSafePointer.getTag( dnode, "dsTipoFeriado", 0 );
	char* cidTipoFeriado = oSafePointer.getTag( dnode, "idTipoFeriado", 0 );
	if( cdsTipoFeriado == NULL )
	{
		setStatusCode("14E0000","dsTipoFeriado esta nulo");
		ULOG_END("implFrdTipEditar::Execute()");
		return;
	}
	if( cidTipoFeriado == NULL )
	{
		setStatusCode("14E0000","idTipoFeriado esta nulo");
		ULOG_END("implFrdTipEditar::Execute()");
		return;
	}
	if( oTipoFeriado.Update( cidTipoFeriado, cdsTipoFeriado,
						 getUser() ) )
	{
		oTipoFeriado.ListAll();
		oTipoFeriado.GetXml("TipoFeriadoVO", xml_g);
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14E0000","Falha na tentativa de UPDATE");
		
	ULOG_END("implFrdTipEditar::Execute()");
}
