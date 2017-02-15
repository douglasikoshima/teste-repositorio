/*****************************************************************************
 *
 * Modulo:    CttRemove
 * Arquivo:   CttRemove.cpp
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
#define CttRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttRemove);

/**************************************************************************
 *  Funcao de Negocios:  CttRemove
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
void implCttRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttRemove::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContato oContato;
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cidUser = getUser();

	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0003","idContato está nulo");
		ULOG_END("implCttRemove::Execute()");
		return;
	}
	switch( oContato.Delete( cidContato, cidUser ) )
	{
		case 0: setStatusCode("14I0000","Operação concluída com sucesso!");break;
		case 1: setStatusCode("14E0002","idContato está nulo");break;
		case 2: setStatusCode("14W0001","Não se pode apagar itens com filhos");break;
		case 3: setStatusCode("14E0001","Esta folha não pode ser apagada, pois está configurada.");break;
		case 4: setStatusCode("14E0001","Não é possível excluir esta folha, pois ela esta associada a uma funcionalidade dos canais eletrônicos.");break;
		default: setStatusCode("14E9999", oContato.GetErro() );break;
	}
	ULOG_END("implCttRemove::Execute()");
}
