/*****************************************************************************
 *
 * Modulo:    FrdUFListar
 * Arquivo:   FrdUFListar.cpp
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
#define FrdUFListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdUFListar);

/**************************************************************************
 *  Funcao de Negocios:  FrdUFListar
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
void implFrdUFListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdUFListar::Execute()");
   
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	DOMNode* oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", 0 );
	char* cidFeriado;
	
	if( oNodeFeriado == NULL )
	{
		setStatusCode("14E0000","Nao foi encontrada a tag AdmFeriadoVO");
		ULOG_END("implFrdUFListar::Execute()");
		return;
	}

	cidFeriado = oSafePointer.getTag( oNodeFeriado, "idFeriado", 0 );
	if( strlennull( cidFeriado ) <= 0 )
	{
		setStatusCode("14E0000","idFeriado esta nulo");
		ULOG_END("implFrdUFListar::Execute()");
		return;
	}

	oFeriado.RelacaoFeriadoUF( cidFeriado, xml_g );
	
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	
	ULOG_END("implFrdUFListar::Execute()");
}
