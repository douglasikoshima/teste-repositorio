/*****************************************************************************
 *
 * Modulo:    CttInfRemover
 * Arquivo:   CttInfRemover.cpp
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
#define CttInfRemoverCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttInf.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttInfRemover);

/**************************************************************************
 *  Funcao de Negocios:  CttInfRemover
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
void implCttInfRemover::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttInfRemover::Execute()");
	/* Chamada de Funcao de Negocios */
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContatoInformacao oContatoInformacao;
	char* cidContatoInformacao = oSafePointer.getTag( dnode, "idContatoInformacao", 0 );
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );

	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0001","idContato está nulo");
		ULOG_END("implCttInfRemover::Execute()");
		return;
	}
	if( strlennull( cidContatoInformacao ) <= 0 )
	{
		setStatusCode("14E0002","idContatoInformacao está nulo");
		ULOG_END("implCttInfRemover::Execute()");
		return;
	}
	if( oContatoInformacao.Delete( cidContatoInformacao ) )
	{

		oContatoInformacao.Relacao( cidContato, xml_g );

		setStatusCode("14I0003","Operação concluída com sucesso!");
	}
	else
		setStatusCode("14E0004","Não há registros para apagar");
		
	ULOG_END("implCttInfRemover::Execute()");


}
