/*****************************************************************************
 *
 * Modulo:    FrdMncRemoveFrd
 * Arquivo:   FrdMncRemoveFrd.cpp
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
#define FrdMncRemoveFrdCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CFrdMnc.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdMncRemoveFrd);

/**************************************************************************
 *  Funcao de Negocios:  FrdMncRemoveFrd
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
void implFrdMncRemoveFrd::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdMncRemoveFrd::Execute()");
	/* Chamada de Funcao de Negocios */
	CMunicipioFeriado oMunicipioFeriado;
	char* cidFeriado = walkTree( dnode, "idFeriado", 0 );
	if( cidFeriado == NULL )
	{
		setStatusCode("00E0000","idFeriado esta nulo");
		ULOG_END("implFrdMncRemoveFrd::Execute()");
		return;
	}
	if( oMunicipioFeriado.Delete( cidFeriado ) )
	{
		oMunicipioFeriado.ListAll();
		oMunicipioFeriado.GetXml("FeriadoNomeVO", xml_g);
		setStatusCode("00I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("00E0000","Falha na tentativa de DELETE");
	ULOG_END("implFrdMncRemoveFrd::Execute()");
}
