/*****************************************************************************
 *
 * Modulo:    PerIncluir
 * Arquivo:   PerIncluir.cpp
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
#define PerIncluirCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CPer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PerIncluir);

/**************************************************************************
 *  Funcao de Negocios:  PerIncluir
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
void implPerIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implPerIncluir::Execute()");
	CSafePointer oSafePointer;
	CPergunta oPergunta;
	char* cidQuestionario = oSafePointer.getTag( dnode, "idQuestionario", 0 );
	char* cidTipoApresentacaoPergunta = oSafePointer.getTag( dnode, "idTipoApresentacaoPergunta", 0 );
	char* cdsPergunta = oSafePointer.getTag( dnode, "dsPergunta", 0 );
	char* cdsScriptPergunta = oSafePointer.getTag( dnode, "dsScriptPergunta", 0 );
	char* cinEncerramento = oSafePointer.getTag( dnode, "inEncerramento", 0 );
	char* cinDisponibilidade = oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	char* cinObrigatoria = oSafePointer.getTag( dnode, "inObrigatoria", 0 );
	char* cidUser = getUser();

	if( strlennull( cidQuestionario ) <= 0 )
	{
		setStatusCode("14E0001", "idQuestionario esta nulo" );
		ULOG_END("implPerIncluir::Execute()");
		return;
	}
	if( strlennull( cidTipoApresentacaoPergunta ) <= 0 )
	{
		setStatusCode("14E0002", "idTipoApresentacaoPergunta esta nulo" );
		ULOG_END("implPerIncluir::Execute()");
		return;
	}
	if( strlennull( cdsPergunta ) <= 0 )
	{
		setStatusCode("14E0003", "dsPergunta esta nulo" );
		ULOG_END("implPerIncluir::Execute()");
		return;
	}
	if( strlennull( cdsScriptPergunta ) <= 0 )
	{
		setStatusCode("14E0004", "dsScriptPergunta esta nulo" );
		ULOG_END("implPerIncluir::Execute()");
		return;
	}
	if( strlennull( cinEncerramento ) <= 0 )
	{
		setStatusCode("14E0005", "inEncerramento esta nulo" );
		ULOG_END("implPerIncluir::Execute()");
		return;
	}
	if( strlennull( cinDisponibilidade ) <= 0 )
	{
		setStatusCode("14E0006", "inDisponibilidade esta nulo" );
		ULOG_END("implPerIncluir::Execute()");
		return;
	}
	if( strlennull( cinObrigatoria ) <= 0 )
	{
		setStatusCode("14E0007", "inObrigatoria esta nulo" );
		ULOG_END("implPerIncluir::Execute()");
		return;
	}
	if( oPergunta.Insert( cidQuestionario,
						  cidTipoApresentacaoPergunta, 
						  cdsPergunta, 
						  cdsScriptPergunta, 
						  cinEncerramento,
						  cinDisponibilidade,
						  cinObrigatoria,
						  cidUser ) )
		setStatusCode("14I0000", "Operacao realizada com sucesso!" );
	else
		setStatusCode("14W0001", "Ja existe esta pergunta" );
		
	ULOG_END("implPerIncluir::Execute()");

}
