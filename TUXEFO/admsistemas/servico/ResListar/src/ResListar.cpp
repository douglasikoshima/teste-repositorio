/*****************************************************************************
 *
 * Modulo:    ResListar
 * Arquivo:   ResListar.cpp
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
#define ResListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CRes.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ResListar);

/**************************************************************************
 *  Funcao de Negocios:  ResListar
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
void implResListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implResListar::Execute()");
	CSafePointer oSafePointer;
	CResposta oResposta;
	char* cidPergunta = oSafePointer.getTag( dnode, "idPergunta", 0 );
	if( strlennull( cidPergunta ) <= 0 )
	{
		setStatusCode("14E0000", "idPergunta esta nulo" );
		ULOG_END("implResListar::Execute()");
		return;
	}

	oResposta.ListIdPergunta( cidPergunta );
	oResposta.GetXml( "AdmRespostaVO", xml_g );

	setStatusCode("14I0000","Operacao concluida com sucesso!");
	
	ULOG_END("implResListar::Execute()");
}
