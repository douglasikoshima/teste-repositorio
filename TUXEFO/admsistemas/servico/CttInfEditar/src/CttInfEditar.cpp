/*****************************************************************************
 *
 * Modulo:    CttInfEditar
 * Arquivo:   CttInfEditar.cpp
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
#define CttInfEditarCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttInf.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttInfEditar);

/**************************************************************************
 *  Funcao de Negocios:  CttInfEditar
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
void implCttInfEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttInfEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CContatoInformacao oContatoInformacao;
	char* cidContatoInformacao = oSafePointer.getTag( dnode, "idContatoInformacao", 0 );
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cidTipoRelacionamento = oSafePointer.getTag( dnode, "idTipoRelacionamento", 0 );
	char* cidUFOperadora = oSafePointer.getTag( dnode, "idUFOperadora", 0 );
	char* cidTipoLinha = oSafePointer.getTag( dnode, "idTipoLinha", 0 );
	char* cnmLink = oSafePointer.getTag( dnode, "nmLink", 0 );

	if( strlennull( cidContatoInformacao ) <= 0 )
	{
		setStatusCode("14E0001","idContatoInformacao está nulo");
		ULOG_END("implCttInfEditar::Execute()");
		return;
	}
	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0002","idContato está nulo");
		ULOG_END("implCttInfEditar::Execute()");
		return;
	}
	if( strlennull( cidTipoRelacionamento ) <= 0 )
	{
		setStatusCode("14E0003","idTipoRelacionamento está nulo");
		ULOG_END("implCttInfEditar::Execute()");
		return;
	}
	if( strlennull( cidUFOperadora ) <= 0 )
	{
		setStatusCode("14E0004","idUFOperadora está nulo");
		ULOG_END("implCttInfEditar::Execute()");
		return;
	}
	if( strlennull( cidTipoLinha ) <= 0 )
	{
		setStatusCode("14E0005","idTipoLinha está nulo");
		ULOG_END("implCttInfEditar::Execute()");
		return;
	}
	if( strlennull( cnmLink ) <= 0 )
	{
		setStatusCode("14E0006","nmLink está nulo");
		ULOG_END("implCttInfEditar::Execute()");
		return;
	}
	if( oContatoInformacao.Update( cidContatoInformacao
		                          ,cidContato
								  ,cidUFOperadora
								  ,cidTipoLinha
								  ,cidTipoRelacionamento
								  ,cnmLink
								  ,getUser() ) )
	{

		oContatoInformacao.Relacao( cidContato, xml_g );

		setStatusCode("14I0000","Operação concluída com sucesso!");
	}
	else
		setStatusCode("14E0007","idContato está nulo");
		
	ULOG_END("implCttInfEditar::Execute()");

}
