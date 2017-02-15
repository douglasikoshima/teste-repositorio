/*****************************************************************************
 *
 * Modulo:    CttInfRelacao
 * Arquivo:   CttInfRelacao.cpp
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
#define CttInfRelacaoCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttInf.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttInfRelacao);

/**************************************************************************
 *  Funcao de Negocios:  CttInfRelacao
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
void implCttInfRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttInfRelacao::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContatoInformacao oContatoInformacao;
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	if( oContatoInformacao.Relacao( cidContato, xml_g ) )
		setStatusCode("14I0000","Opera��o conclu�da com sucesso!");
	else
		setStatusCode("14E0000","idContato est� nulo");
	ULOG_END("implCttInfRelacao::Execute()");
}
