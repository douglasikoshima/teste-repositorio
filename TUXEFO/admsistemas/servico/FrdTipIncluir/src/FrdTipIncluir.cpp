/*****************************************************************************
 *
 * Modulo:    FrdTipIncluir
 * Arquivo:   FrdTipIncluir.cpp
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
#define FrdTipIncluirCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdTipIncluir);

/**************************************************************************
 *  Funcao de Negocios:  FrdTipIncluir
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
void implFrdTipIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdTipIncluir::Execute()");
   
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	DOMNode* oNodeFeriado;

	char* cdtFeriado;
	char* cidDsFeriado;
	char* cdsFeriado;
	char* cidTipoFeriado;
	char* cindMovel;
	char* cinRelatorio;
	char* cidUser = getUser();

	xml_g->createTag( "AdmCalendarioContainerVO" );
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	for( int x=0;;x++ )
	{
		oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", x );
		if( oNodeFeriado != NULL )
		{
			cdtFeriado = oSafePointer.getTag( oNodeFeriado, "dtFeriado", 0 );
			cidDsFeriado = oSafePointer.getTag( oNodeFeriado, "idDsFeriado", 0 );
			cdsFeriado = oSafePointer.getTag( oNodeFeriado, "dsFeriado", 0 );
			cidTipoFeriado = oSafePointer.getTag( oNodeFeriado, "idTipoFeriado", 0 );
			cindMovel = oSafePointer.getTag( oNodeFeriado, "indMovel", 0 );
			cinRelatorio = oSafePointer.getTag( oNodeFeriado, "inRelatorio", 0 );
			if( strlennull( cdtFeriado ) <= 0 )
			{
				setStatusCode("14E0000","dtFeriado esta nulo");
				ULOG_END("implFrdTipIncluir::Execute()");
				return;
			}
			if( strlennull( cidDsFeriado ) <= 0 )
			{
				if( strlennull( cdsFeriado ) <= 0 )
				{
					setStatusCode("14E0000","dsFeriado esta nulo");
					ULOG_END("implFrdTipIncluir::Execute()");
					return;
				}
			}
			if( strlennull( cidTipoFeriado ) <= 0 )
			{
				setStatusCode("14E0000","idTipoFeriado esta nulo");
				ULOG_END("implFrdTipIncluir::Execute()");
				return;
			}
			if( strlennull( cindMovel ) <= 0 )
			{
				setStatusCode("14E0000","indMovel esta nulo");
				ULOG_END("implFrdTipIncluir::Execute()");
				return;
			}
			if( !oFeriado.Insert( cidTipoFeriado, 
								  cidDsFeriado,
								  cdsFeriado,
								  cindMovel, 
								  cdtFeriado,
								  cinRelatorio,
								  cidUser ) )
			{
				setStatusCode("14E0000","Falha na tentativa de inserir pontes");
				
				break;
			}
		}
		else
		{
			setStatusCode("14I0000","Operacao concluida com sucesso!");
			break;
		}
			
	}//for( int x=0;;x++ )

	xml_g->closeTag();//AdmCalendarioContainerVO
	
	ULOG_END("implFrdTipIncluir::Execute()");
}
