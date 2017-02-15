/*****************************************************************************
 *
 * Modulo:    BXAUFORELACAO
 * Arquivo:   BXAUFORELACAO.cpp
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
#define BXAUFORELACAOCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxaUfo.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXAUFORELACAO);

/**************************************************************************
 *  Funcao de Negocios:  BXAUFORELACAO
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
void implBXAUFORELACAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXAUFORELACAO::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixaUfoperadora oBaixaUfoperadora;
	char* cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	
	if( oBaixaUfoperadora.Relacao( cidBaixa, xml_g ) )
		setStatusCode("14I0000","Servico executado com sucesso");
	else
		setStatusCode("14E0001","idBaixa está nulo");
	ULOG_END("implBXAUFORELACAO::Execute()");
}
