/*****************************************************************************
 *
 * Modulo:    BXAFTRRELACAO
 * Arquivo:   BXAFTRRELACAO.cpp
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
#define BXAFTRRELACAOCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxaFtr.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXAFTRRELACAO);

/**************************************************************************
 *  Funcao de Negocios:  BXAFTRRELACAO
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
void implBXAFTRRELACAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXAFTRRELACAO::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixaFiltro oBaixaFiltro;
	char* cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	
	if( oBaixaFiltro.Relacao( cidBaixa, xml_g ) )
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	else
		setStatusCode("14E0001","idBaixa está nulo");
   ULOG_END("implBXAFTRRELACAO::Execute()");		
}
