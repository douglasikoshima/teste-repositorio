/*****************************************************************************
 *
 * Modulo:    FrdUFRemoveUF
 * Arquivo:   FrdUFRemoveUF.cpp
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
#define FrdUFRemoveUFCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdUF.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdUFRemoveUF);

/**************************************************************************
 *  Funcao de Negocios:  FrdUFRemoveUF
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
void implFrdUFRemoveUF::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdUFRemoveUF::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CUFFeriado oUFFeriado;
	char* cidUF = oSafePointer.getTag( dnode, "idUF", 0 );
	if( cidUF == NULL )
	{
		setStatusCode("14E0000","idUF esta nulo");
		ULOG_END("implFrdUFRemoveUF::Execute()");
		return;
	}
	if( oUFFeriado.EraseUF( cidUF ) )
	{
		oUFFeriado.ListAll();
		oUFFeriado.GetXml("FeriadoNomeVO", xml_g);
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14E0000","Falha na tentativa de DELETE");
		
	ULOG_END("implFrdUFRemoveUF::Execute()");
}
