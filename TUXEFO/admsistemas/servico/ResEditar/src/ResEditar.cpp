/*****************************************************************************
 *
 * Modulo:    ResEditar
 * Arquivo:   ResEditar.cpp
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
#define ResEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CRes.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ResEditar);

/**************************************************************************
 *  Funcao de Negocios:  ResEditar
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
void implResEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implResEditar::Execute()");
	CSafePointer oSafePointer;
	CResposta oResposta;
	DOMNode* node = walkDOM( dnode, "AdmRespostaVO", 0 );
	char* cidResposta = oSafePointer.getTag( node, "idResposta", 0 );
	char* cdsResposta = oSafePointer.getTag( node, "dsResposta", 0 );
	char* cdsScriptResposta = oSafePointer.getTag( node, "dsScriptResposta", 0 );
	char* cinEncerramento = oSafePointer.getTag( node, "inEncerramento", 0 );
	char* cinDisponibilidade = oSafePointer.getTag( node, "inDisponibilidade", 0 );
	DOMNode* nodeSalto = walkDOM( dnode, "AdmSaltoVO", 0 );
	char* cidPerguntaSalto = oSafePointer.getTag( nodeSalto, "idPergunta", 0 );
	char* cinAtivo = oSafePointer.getTag( nodeSalto, "ativo", 0 );
	char* cidUser = getUser();

	if( strlennull( cidResposta ) <= 0 )
	{
		setStatusCode("14E0001", "idResposta está nulo" );
		ULOG_END("implResEditar::Execute()");
		return;
	}
	if( strlennull( cdsResposta ) <= 0 )
	{
		setStatusCode("14E0002", "dsResposta está nulo" );
		ULOG_END("implResEditar::Execute()");
		return;
	}
	if( strlennull( cdsScriptResposta ) <= 0 )
	{
		setStatusCode("14E0003", "dsScriptResposta está nulo" );
		return;
	}
	if( strlennull( cinEncerramento ) <= 0 )
	{
		setStatusCode("14E0005", "inEncerramento está nulo" );
		ULOG_END("implResEditar::Execute()");
		return;
	}
	if( strlennull( cinDisponibilidade ) <= 0 )
	{
		setStatusCode("14E0006", "inDisponibilidade está nulo" );
		ULOG_END("implResEditar::Execute()");
		return;
	}
	if( strlennull( cidPerguntaSalto ) > 0 )
	{
		if( strlennull( cinAtivo ) <= 0 )
		{
			setStatusCode("14E0007", "ativo está nulo" );
			ULOG_END("implResEditar::Execute()");
			return;
		}
	}
	if( strlennull( cidUser ) <= 0 )
	{
		setStatusCode("14E0008", "idUser está nulo" );
		ULOG_END("implResEditar::Execute()");
		return;
	}

	if( oResposta.Update( cidResposta
			 			 ,""
						 ,cdsResposta
						 ,cdsScriptResposta
					    ,cinEncerramento
						 ,cinDisponibilidade
						 ,cidPerguntaSalto
						 ,cinAtivo
 						 ,cidUser ) )
		setStatusCode("14I0000","Operação concluída com sucesso!");
	else
		setStatusCode("14W0001", "Já existe está resposta" );
		
	ULOG_END("implResEditar::Execute()");

}
