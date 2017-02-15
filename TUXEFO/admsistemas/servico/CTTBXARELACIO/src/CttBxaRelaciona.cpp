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
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFlhBxa.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTBXARELACIO);

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
void implCTTBXARELACIO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCTTBXARELACIO::Execute()");
	CSafePointer oSafePointer;
	CContatoFolhaBaixa oContatoFolhaBaixa;
	CContato oContato;
	bool blDelete = true;
	
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	char* cinInclui = oSafePointer.getTag( dnode, "inInclui", 0 );
	char  cAuxinInclui[21+1];
	char* cidUser = getUser();
	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0001","idContato está nulo");
		ULOG_END("implCTTBXARELACIO::Execute()");
		return;
	}
	if( strlennull( cidBaixa ) <= 0 )
	{
		setStatusCode("14E0002","idBaixa está nulo");
		ULOG_END("implCTTBXARELACIO::Execute()");
		return;
	}
	if( strlennull( cinInclui ) <= 0 )
		strcpy( cAuxinInclui, "0" );
	else
		strcpy( cAuxinInclui, cinInclui );

	if( strcmp( cAuxinInclui, "0" ) == 0 )
	{
		//Verifica se esta relacionado, antes de apagar
		if( oContatoFolhaBaixa.ListId( cidContato, cidBaixa ) )
		{
			//Soh pode apagar as associacoes de baixa com contato se o processo não 
			//tiver em processoe for a ultima baixa associda ao contato
			if( oContatoFolhaBaixa.ContaBaixa( cidContato ) == 1 )
			{
				if( oContato.TemProcessoEmAndamento( cidContato ) > 0 )
				{
					setStatusCode("14W0001","Este contato participa de atendimento(s) ainda aberto(s) e não pode ficar sem itens de baixa.");
					blDelete = false;
				}
			}
			if( blDelete )
			{
				oContatoFolhaBaixa.Delete( cidContato, cidBaixa );
				setStatusCode("14I0000","Operação concluída com sucesso!");
			}
		}
		else
		{
			//Se nao existe nao apaga, da um warning
			setStatusCode("14W0002","Esta baixa não está relacionada com o contato em questão");
		}
	}
	else
	{
		//Verifica se ja esta relacionado, antes de incluir
		if( !oContatoFolhaBaixa.ListId( cidContato, cidBaixa ) )
		{
			oContatoFolhaBaixa.Insert( cidContato, cidBaixa, cidUser );
			setStatusCode("14I0000","Operação concluída com sucesso!");
		}
		else
		{
			//Se existe a relacao, nao pode incluir
			setStatusCode("14W0003","Esta baixa já está relacionada com o contato em questão");
		}
	}
	XMLString::release( &cidContato );
	XMLString::release( &cidBaixa );
	XMLString::release( &cinInclui );
	ULOG_END("implCTTBXARELACIO::Execute()");
}
