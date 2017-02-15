/*****************************************************************************
 *
 * Modulo:    CttPerListar
 * Arquivo:   CttPerListar.cpp
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
#define CttPerListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttPes.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttPerListar);

/**************************************************************************
 *  Funcao de Negocios:  CttPerListar
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
void implCttPerListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttPerListar::Execute()");
	CSafePointer oSafePointer;
	CPesquisaSatisfacaoUF oPesquisaSatisfacaoUF;
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cidQuestionario = oSafePointer.getTag( dnode, "idQuestionarioAtual", 0 );
	char* cidTipoPessoa = oSafePointer.getTag( dnode, "idTipoPessoaAtual", 0 );

	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0001","idContato está nulo");
		ULOG_END("implCttPerListar::Execute()");
		return;
	}
	oPesquisaSatisfacaoUF.Relacao( 
		                           cidContato, 
								   cidQuestionario, 
								   cidTipoPessoa, 
								   xml_g );
	setStatusCode("14I0000","Operação realizada com sucesso!");
	ULOG_END("implCttPerListar::Execute()");
}
