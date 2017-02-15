/*****************************************************************************
 *
 * Modulo:    HrrVraIncluir
 * Arquivo:   HrrVraIncluir.cpp
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
#define HrrVraIncluirCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CHrrVra.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(HrrVraIncluir);

/**************************************************************************
 *  Funcao de Negocios:  HrrVraIncluir
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
void implHrrVraIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implHrrVraIncluir::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CHorarioVerao oHorarioVerao;
	char* cidUF = oSafePointer.getTag( dnode, "idUF", 0 );
	char* cnrHorarioVerao = oSafePointer.getTag( dnode, "nrHorarioVerao", 0 );
	char* cdtInicio = oSafePointer.getTag( dnode, "dtInicio", 0 );
	char* cdtFim = oSafePointer.getTag( dnode, "dtFim", 0 );
	if( cidUF == NULL )
	{
		setStatusCode("14E0000","idUF esta nulo");
		ULOG_END("implHrrVraIncluir::Execute()");
		return;
	}
	if( cnrHorarioVerao == NULL )
	{
		setStatusCode("14E0000","nrHorarioVerao esta nulo");
		ULOG_END("implHrrVraIncluir::Execute()");
		return;
	}
	if( cdtInicio == NULL )
	{
		setStatusCode("14E0000","dtInicio esta nulo");
		ULOG_END("implHrrVraIncluir::Execute()");
		return;
	}
	if( cdtFim == NULL )
	{
		setStatusCode("14E0000","dtFim esta nulo");
		ULOG_END("implHrrVraIncluir::Execute()");
		return;
	}
	if( oHorarioVerao.Insert( cidUF, 
		                      cnrHorarioVerao, 
		                      cdtInicio, 
		                      cdtFim,
						 getUser() ) )
	{
		oHorarioVerao.ListAll();
		oHorarioVerao.GetXml("HorarioVeraoVO", xml_g);
		setStatusCode("14I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("14E0000","Falha na tentativa de USERT");
	ULOG_END("implHrrVraIncluir::Execute()");
}
