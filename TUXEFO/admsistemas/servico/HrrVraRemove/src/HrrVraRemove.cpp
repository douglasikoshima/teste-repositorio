/*****************************************************************************
 *
 * Modulo:    HrrVraRemove
 * Arquivo:   HrrVraRemove.cpp
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
#define HrrVraRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CHrrVra.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(HrrVraRemove);

/**************************************************************************
 *  Funcao de Negocios:  HrrVraRemove
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
void implHrrVraRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implHrrVraRemove::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CHorarioVerao oHorarioVerao;
	char* cidHorarioVerao = oSafePointer.getTag( dnode, "idHorarioVerao", 0 );
	if( cidHorarioVerao == NULL )
	{
		setStatusCode("14E0000","idHorarioVerao esta nulo");
		ULOG_END("implHrrVraRemove::Execute()");
		return;
	}
	if( oHorarioVerao.Delete( cidHorarioVerao ) )
	{
		oHorarioVerao.ListAll();
		oHorarioVerao.GetXml("HorarioVeraoVO", xml_g);
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14E0000","Falha na tentativa de DELETE");
	ULOG_END("implHrrVraRemove::Execute()");
}
