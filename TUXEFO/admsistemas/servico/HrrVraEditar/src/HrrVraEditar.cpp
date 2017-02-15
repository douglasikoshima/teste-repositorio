/*****************************************************************************
 *
 * Modulo:    HrrVraEditar
 * Arquivo:   HrrVraEditar.cpp
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
#define HrrVraEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CHrrVra.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(HrrVraEditar);

/**************************************************************************
 *  Funcao de Negocios:  HrrVraEditar
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
void implHrrVraEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implHrrVraEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CHorarioVerao oHorarioVerao;
	char* cidHorarioVerao = oSafePointer.getTag( dnode, "idHorarioVerao", 0 );
	char* cidUF = oSafePointer.getTag( dnode, "idUF", 0 );
	char* cnrHorarioVerao = oSafePointer.getTag( dnode, "nrHorarioVerao", 0 );
	char* cdtInicio = oSafePointer.getTag( dnode, "dtInicio", 0 );
	char* cdtFim = oSafePointer.getTag( dnode, "dtFim", 0 );
	if( cidHorarioVerao == NULL )
	{
		setStatusCode("14E0000","idHorarioVerao esta nulo");
		ULOG_END("implHrrVraEditar::Execute()");
		return;
	}
	if( cidUF == NULL )
	{
		setStatusCode("14E0000","idUF esta nulo");
		ULOG_END("implHrrVraEditar::Execute()");
		return;
	}
	if( cnrHorarioVerao == NULL )
	{
		setStatusCode("14E0000","nrHorarioVerao esta nulo");
		ULOG_END("implHrrVraEditar::Execute()");
		return;
	}
	if( cdtInicio == NULL )
	{
		setStatusCode("14E0000","dtInicio esta nulo");
		ULOG_END("implHrrVraEditar::Execute()");
		return;
	}
	if( cdtFim == NULL )
	{
		setStatusCode("14E0000","dtFim esta nulo");
		ULOG_END("implHrrVraEditar::Execute()");
		return;
	}
	if( oHorarioVerao.Update( cidHorarioVerao, 
		                     cidUF, 
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
		setStatusCode("14E0000","Falha na tentativa de UPDATE");
	ULOG_END("implHrrVraEditar::Execute()");
}
