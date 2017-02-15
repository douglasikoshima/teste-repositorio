/*****************************************************************************
 *
 * Modulo:    FrdNomEditar
 * Arquivo:   FrdNomEditar.cpp
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
#define FrdNomEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdNom.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdNomEditar);

/**************************************************************************
 *  Funcao de Negocios:  FrdNomEditar
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
void implFrdNomEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdNomEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CNomeFeriado oNomeFeriado;
	char* cdsFeriado = oSafePointer.getTag( dnode, "dsFeriado", 0 );
	char* cidNomeFeriado = oSafePointer.getTag( dnode, "idNomeFeriado", 0 );
	char* cidUser = getUser();
	if( cdsFeriado == NULL )
	{
		setStatusCode("14E0001","dsFeriado está nulo");
		ULOG_END("implFrdNomEditar::Execute()");
		return;
	}
	if( cidNomeFeriado == NULL )
	{
		setStatusCode("14E0002","idNomeFeriado está nulo");
		ULOG_END("implFrdNomEditar::Execute()");
		return;
	}
	if( oNomeFeriado.Update( cidNomeFeriado, cdsFeriado,
						     cidUser ) )
	{
		oNomeFeriado.ListAll();
		oNomeFeriado.GetXml("NomeFeriadoVO", xml_g);
		setStatusCode("14I0000","Operação concluída com sucesso!");
	}
	else
		setStatusCode("14E0003","Falha na tentativa de UPDATE");
		
	ULOG_END("implFrdNomEditar::Execute()");
	
}
