/*****************************************************************************
 *
 * Modulo:    ResIncluir
 * Arquivo:   ResIncluir.cpp
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
#define ResIncluirCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CRes.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ResIncluir);

/**************************************************************************
 *  Funcao de Negocios:  ResIncluir
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
void implResIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   
   ULOG_START("implResIncluir::Execute()");
   
	CSafePointer oSafePointer;
	CResposta oResposta;
	char* cidPergunta = oSafePointer.getTag( dnode, "idPergunta", 0 );
	char* cidPerguntaSalto = oSafePointer.getTag( dnode, "idPerguntaSalto", 0 );
	char* cinAtivo = oSafePointer.getTag( dnode, "inAtivo", 0 );

	DOMNode* dnAdmRespostaVO = walkDOM( dnode, "AdmRespostaVO", 0 );
	if( dnAdmRespostaVO == NULL )
	{
		setStatusCode("14E0001", "Não foi possível encontrar a TAG <AdmRespostaVO>" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	char* cdsResposta = oSafePointer.getTag( dnAdmRespostaVO, "dsResposta", 0 );
	char* cdsScriptResposta = oSafePointer.getTag( dnAdmRespostaVO, "dsScriptResposta", 0 );
	char* cinEncerramento = oSafePointer.getTag( dnAdmRespostaVO, "inEncerramento", 0 );
	char* cinDisponibilidade = oSafePointer.getTag( dnAdmRespostaVO, "inDisponibilidade", 0 );
	
	char* cidUser = getUser();

	if( strlennull( cidPergunta ) <= 0 )
	{
		setStatusCode("14E0001", "idPergunta está nulo" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	if( strlennull( cdsResposta ) <= 0 )
	{
		setStatusCode("14E0002", "dsResposta está nulo" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	if( strlennull( cdsScriptResposta ) <= 0 )
	{
		setStatusCode("14E0003", "dsScriptResposta está nulo" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	if( strlennull( cinEncerramento ) <= 0 )
	{
		setStatusCode("14E0004", "inEncerramento está nulo" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	if( strlennull( cinDisponibilidade ) <= 0 )
	{
		setStatusCode("14E0005", "inDisponibilidade está nulo" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	if( strlennull( cidPerguntaSalto ) > 0 )
	{
		if( strlennull( cinAtivo ) <= 0 )
		{
			setStatusCode("14E0006", "inAtivo está nulo" );
			ULOG_END("implResIncluir::Execute()");
			return;
		}
	}
	if( strlennull( cidUser ) <= 0 )
	{
		setStatusCode("14E0007", "idUser está nulo" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	if( oResposta.VerifcaTipoPergunta( cidPergunta, FO_MEMO ) )
	{
		setStatusCode("14W0001", "Perguntas do tipo MEMO não podem conter respostas" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	if( oResposta.VerifcaTipoPergunta( cidPergunta, FO_TEXTO ) )
	{
		setStatusCode("14W0002", "Perguntas do tipo TEXTO não podem conter respostas" );
		ULOG_END("implResIncluir::Execute()");
		return;
	}
	if( oResposta.Insert( cidPergunta
						 ,cdsResposta
						 ,cdsScriptResposta
						 ,cinEncerramento
						 ,cinDisponibilidade
						 ,cidPerguntaSalto
						 ,cinAtivo
						 ,cidUser ) )
		setStatusCode("14I0000","Operação concluída com sucesso!");
	else
		setStatusCode("14W0003", "Já existe está resposta" );
	ULOG_END("implResIncluir::Execute()");
}
