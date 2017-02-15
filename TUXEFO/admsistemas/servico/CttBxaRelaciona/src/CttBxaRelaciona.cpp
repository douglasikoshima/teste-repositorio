/*****************************************************************************
 *
 * Modulo:    CttBxaRelaciona
 * Arquivo:   CttBxaRelaciona.cpp
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
#define CttBxaRelacionaCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CFlhBxa.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttBxaRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  CttBxaRelaciona
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
void implCttBxaRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttBxaRelaciona::Execute()");
	CContatoFolhaBaixa oContatoFolhaBaixa;
	
	char* cidContato = walkTree( dnode, "idContato", 0 );
	char* cidBaixa = walkTree( dnode, "idBaixa", 0 );
	char* cidUser = getUser();
	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("00E0000","idContato esta nulo");
		ULOG_END("implCttBxaRelaciona::Execute()");
		return;
	}
	if( strlennull( cidBaixa ) <= 0 )
	{
		setStatusCode("00E0000","idBaixa esta nulo");
		ULOG_END("implCttBxaRelaciona::Execute()");
		return;
	}

	if( oContatoFolhaBaixa.ListId( cidContato, cidBaixa ) )
	{
		oContatoFolhaBaixa.Delete( cidContato, cidBaixa );
		setStatusCode("00I0000","Operacao concluida com sucesso!");
	}
	else
	{
		oContatoFolhaBaixa.Insert( cidContato, cidBaixa, cidUser );
		setStatusCode("00I0000","Operacao concluida com sucesso!");
	}
	ULOG_END("implCttBxaRelaciona::Execute()");
}
