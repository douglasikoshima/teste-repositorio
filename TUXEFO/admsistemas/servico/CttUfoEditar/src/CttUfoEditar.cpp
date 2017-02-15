/*****************************************************************************
 *
 * Modulo:    CttUfoEditar
 * Arquivo:   CttUfoEditar.cpp
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
#define CttUfoEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttUfo.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttUfoEditar);

/**************************************************************************
 *  Funcao de Negocios:  CttUfoEditar
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
void implCttUfoEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttUfoEditar::Execute()");
   
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContatoUfoperadora oContatoUfoperadora;
	char* cidContatoUFOperadora = ""; //oSafePointer.getTag( dnode, "idContatoUFOperadora", 0 );
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cidUFOperadora = oSafePointer.getTag( dnode, "idUFOperadora", 0 );
	char* cdtInicioVigencia = oSafePointer.getTag( dnode, "dtInicioVigencia", 0 );
	char* cdtFimVigencia = oSafePointer.getTag( dnode, "dtFimVigencia", 0 );
	char* cinDisponibilidade = oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	char* cidUser = getUser();
	char  cindeterminado[1+1];
	memset(cindeterminado, 0, sizeof( cindeterminado ) );
	
	//if( strlennull( cidContatoUFOperadora ) <= 0 )
	//{
	//	setStatusCode("14E0000","idContatoUFOperadora está nulo");
	//	return;
	//}
	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0001","idContato está nulo");
		ULOG_END("implCttUfoEditar::Execute()");
		return;
	}
	if( strlennull( cidUFOperadora ) <= 0 )
	{
		setStatusCode("14E0002","idUFOperadora está nulo");
		ULOG_END("implCttUfoEditar::Execute()");
		return;
	}
	if( strlennull( cinDisponibilidade ) <= 0 )
	{
		setStatusCode("14E0003","inDisponibilidade está nulo");
		ULOG_END("implCttUfoEditar::Execute()");
		return;
	}
	if( strlennull( cdtInicioVigencia ) <= 0 )
		cdtInicioVigencia = '\0';
	
	if( oContatoUfoperadora.Update( cidContatoUFOperadora
	                               ,cidContato
	                               ,cidUFOperadora
	                               ,cdtInicioVigencia
	                               ,cdtFimVigencia
	                               ,cinDisponibilidade
	                               ,cidUser ) )
		setStatusCode("14I0000","Serviço executado com sucesso");
	else
		setStatusCode("14E0004","Falha ao tentar realizar a edição de Contato Operadoras");
		
	ULOG_END("implCttUfoEditar::Execute()");
}
