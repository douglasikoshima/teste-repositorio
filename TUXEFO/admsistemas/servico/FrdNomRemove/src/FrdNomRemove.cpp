/*****************************************************************************
 *
 * Modulo:    FrdNomRemove
 * Arquivo:   FrdNomRemove.cpp
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
#define FrdNomRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdNom.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdNomRemove);

/**************************************************************************
 *  Funcao de Negocios:  FrdNomRemove
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
void implFrdNomRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdNomRemove::Execute()");
   
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CNomeFeriado oNomeFeriado;
	char* cidNomeFeriado = oSafePointer.getTag( dnode, "idNomeFeriado", 0 );
	if( cidNomeFeriado == NULL )
	{
		setStatusCode("14E0000","idNomeFeriado esta nulo");
		ULOG_END("implFrdNomRemove::Execute()");
		return;
	}
	if( oNomeFeriado.Delete( cidNomeFeriado ) )
	{
		oNomeFeriado.ListAll();
		oNomeFeriado.GetXml("FeriadoNomeVO", xml_g);
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14E0000","Falha na tentativa de DELETE");
		
   ULOG_END("implFrdNomRemove::Execute()");
}
