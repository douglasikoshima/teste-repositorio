/*****************************************************************************
 *
 * Modulo:    PerEditar
 * Arquivo:   PerEditar.cpp
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
#define PerEditarCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CPer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PerEditar);

/**************************************************************************
 *  Funcao de Negocios:  PerEditar
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
void implPerEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implPerEditar::Execute()");
	CSafePointer oSafePointer;
	CPergunta oPergunta;
	char* cidQuestionario = oSafePointer.getTag( dnode, "idQuestionario", 0 );
	char* cidPergunta = oSafePointer.getTag( dnode, "idPergunta", 0 );
	char* cidTipoApresentacaoPergunta = oSafePointer.getTag( dnode, "idTipoApresentacaoPergunta", 0 );
	char* cdsPergunta = oSafePointer.getTag( dnode, "dsPergunta", 0 );
	char* cdsScriptPergunta = oSafePointer.getTag( dnode, "dsScriptPergunta", 0 );
	char* cinEncerramento = oSafePointer.getTag( dnode, "inEncerramento", 0 );
	char* cinDisponibilidade = oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	char* cinObrigatoria = oSafePointer.getTag( dnode, "inObrigatoria", 0 );
	char* cidUser = getUser();

	if( strlennull( cidPergunta ) <= 0 )
	{
		setStatusCode("14E0001", "idPergunta estestáa nulo" );
		ULOG_END("implPerEditar::Execute()");
		return;
	}
	if( strlennull( cidTipoApresentacaoPergunta ) <= 0 )
	{
		setStatusCode("14E0002", "idTipoApresentacaoPergunta está nulo" );
		ULOG_END("implPerEditar::Execute()");
		return;
	}
	if( strlennull( cdsPergunta ) <= 0 )
	{
		setStatusCode("14E0003", "dsPergunta está nulo" );
		ULOG_END("implPerEditar::Execute()");
		return;
	}
	if( strlennull( cdsScriptPergunta ) <= 0 )
	{
		setStatusCode("14E0004", "dsScriptPergunta está nulo" );
		ULOG_END("implPerEditar::Execute()");
		return;
	}
	if( strlennull( cinEncerramento ) <= 0 )
	{
		setStatusCode("14E0006", "inEncerramento está nulo" );
		ULOG_END("implPerEditar::Execute()");
		return;
	}
	if( strlennull( cinDisponibilidade ) <= 0 )
	{
		setStatusCode("14E0007", "inDisponibilidade está nulo" );
		ULOG_END("implPerEditar::Execute()");
		return;
	}
	if( strlennull( cinObrigatoria ) <= 0 )
	{
		setStatusCode("14E0008", "inObrigatoria está nulo" );
		ULOG_END("implPerEditar::Execute()");
		return;
	}
	if( oPergunta.PerguntaTextoMemo( cidPergunta, cidTipoApresentacaoPergunta ) )
	{
		setStatusCode("14W0001", "Não se pode modificar perguntas com resposta para os tipos MEMO ou TEXTO" );
		ULOG_END("implPerEditar::Execute()");
		return;
	}
	if( oPergunta.Update( cidQuestionario,
						  cidPergunta, 
						  cidTipoApresentacaoPergunta, 
						  cdsPergunta, 
						  cdsScriptPergunta, 
						  cinEncerramento,
						  cinDisponibilidade,
						  cinObrigatoria,
						  cidUser ) )
		setStatusCode("14I0000", "Operação realizada com sucesso!" );
	else
		setStatusCode("14W0002", "Já existe esta pergunta" );
		
	ULOG_END("implPerEditar::Execute()");

}
