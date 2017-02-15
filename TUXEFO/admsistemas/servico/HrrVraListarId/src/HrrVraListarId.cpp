/*****************************************************************************
 *
 * Modulo:    HrrVraListarId
 * Arquivo:   HrrVraListarId.cpp
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
#define HrrVraListarIdCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CHrrVra.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(HrrVraListarId);

/**************************************************************************
 *  Funcao de Negocios:  HrrVraListarId
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
void implHrrVraListarId::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implHrrVraListarId::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CHorarioVerao oHorarioVerao;
	char* cidHorarioVerao = oSafePointer.getTag( dnode, "idHorarioVerao", 0 );
	if( cidHorarioVerao == NULL )
	{
		setStatusCode("14E0000","idHorarioVerao esta nulo");
		ULOG_END("implHrrVraListarId::Execute()");
		return;
	}
	oHorarioVerao.ListId( cidHorarioVerao );
	oHorarioVerao.GetXml("HorarioVeraoVO", xml_g);
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	ULOG_END("implHrrVraListarId::Execute()");
}
