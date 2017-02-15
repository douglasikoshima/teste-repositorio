/*****************************************************************************
 *
 * Modulo:    CttFtrRelacao
 * Arquivo:   CttFtrRelacao.cpp
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
#define CttFtrRelacaoCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttFtr.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttFtrRelacao);

/**************************************************************************
 *  Funcao de Negocios:  CttFtrRelacao
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
void implCttFtrRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttFtrRelacao::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContatoFiltro oContatoFiltro;
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	
	if( oContatoFiltro.Relacao( cidContato, xml_g ) )
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	else
		setStatusCode("14E0001","idContato está nulo");
		
	ULOG_END("implCttFtrRelacao::Execute()");
}
