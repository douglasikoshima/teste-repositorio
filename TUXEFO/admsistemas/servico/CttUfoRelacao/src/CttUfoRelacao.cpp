/*****************************************************************************
 *
 * Modulo:    CttUfoRelacao
 * Arquivo:   CttUfoRelacao.cpp
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
#define CttUfoRelacaoCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttUfo.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttUfoRelacao);

/**************************************************************************
 *  Funcao de Negocios:  CttUfoRelacao
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
void implCttUfoRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttUfoRelacao::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContatoUfoperadora oContatoUfoperadora;
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	
	if( oContatoUfoperadora.Relacao( cidContato, xml_g ) )
		setStatusCode("14I0000","Servico executado com sucesso");
	else
		setStatusCode("14E0001","idContato está nulo");
	ULOG_END("implCttUfoRelacao::Execute()");
   
}
