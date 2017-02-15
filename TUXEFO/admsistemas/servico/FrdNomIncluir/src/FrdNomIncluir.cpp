/*****************************************************************************
 *
 * Modulo:    FrdNomIncluir
 * Arquivo:   FrdNomIncluir.cpp
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
#define FrdNomIncluirCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdNomIncluir);

/**************************************************************************
 *  Funcao de Negocios:  FrdNomIncluir
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
void implFrdNomIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdNomIncluir::Execute()");
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	DOMNode* oNodeFeriado;
	char* cdtFeriado;
	char* cidDsFeriado;
	char* cdsFeriado;
	char* cidTipoFeriado;
	char* cindMovel;
	char* cidUF;
	char* cindMnc;
	char* cidUser = getUser();
	char  cidFeriado[21+1];
	char* cinRelatorio;

	xml_g->createTag( "AdmCalendarioContainerVO" );
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );

	for( int x=0;; x++ )
	{
		oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", x );
		if( oNodeFeriado != NULL )
		{
			cdtFeriado = oSafePointer.getTag( oNodeFeriado, "dtFeriado", 0 );
			cidDsFeriado = oSafePointer.getTag( oNodeFeriado, "idDsFeriado", 0 );
			cdsFeriado = oSafePointer.getTag( oNodeFeriado, "dsFeriado", 0 );
			cidTipoFeriado = oSafePointer.getTag( oNodeFeriado, "idTipoFeriado", 0 );
			cindMovel = oSafePointer.getTag( oNodeFeriado, "indMovel", 0 );
			cidUser = getUser();
			cidUF = oSafePointer.getTag( oNodeFeriado, "idUf", 0 );
			cindMnc = oSafePointer.getTag( oNodeFeriado, "idMunicipio", 0 );
			cinRelatorio = oSafePointer.getTag( oNodeFeriado, "inRelatorio", 0 );
			if( strlennull( cdtFeriado ) <= 0 )
			{
				setStatusCode("14E0001","dtFeriado está nulo");
				ULOG_END("implFrdNomIncluir::Execute()");
				return;
			}
			if( strlennull( cdtFeriado ) <= 0 )
			{
				setStatusCode("14E0002","dtFeriado está nulo");
				ULOG_END("implFrdNomIncluir::Execute()");
				return;
			}
			if( strlennull( cdtFeriado ) <= 0 )
			{
				setStatusCode("14E0003","dtFeriado está nulo");
				ULOG_END("implFrdNomIncluir::Execute()");
				return;
			}
			if( strlennull( cidDsFeriado ) <= 0 )
			{
				if( strlennull( cdsFeriado ) <= 0 )
				{
					setStatusCode("14E0004","dsFeriado está nulo");
					ULOG_END("implFrdNomIncluir::Execute()");
					return;
				}
			}
			if( strlennull( cidTipoFeriado ) <= 0 )
			{
				setStatusCode("14E0005","idTipoFeriado está nulo");
				ULOG_END("implFrdNomIncluir::Execute()");
				return;
			}
			if( strlennull( cindMovel ) <= 0 )
			{
				setStatusCode("14E0006","indMovel está nulo");
				ULOG_END("implFrdNomIncluir::Execute()");
				return;
			}
			memset( cidFeriado, 0, sizeof( cidFeriado ) );
			if( oFeriado.Insert( cidTipoFeriado, 
							     cidDsFeriado,
								 cdsFeriado,
								 cindMovel, 
								 cdtFeriado,
								 cinRelatorio,
								 cidUser,
								 cidUF,
								 cindMnc,
								 cidFeriado ) != 1)
			{
				setStatusCode("14E0007","Falha ao tentar incluir um feriado");
				break;
			} // oFeriado.Insert
			oNodeFeriado = walkDOM( dnode, "relacionados", 0 );
			if( oNodeFeriado != NULL )
			{
				int iCont;
				for( iCont=0;;iCont++ )
				{
					cidUF  = oSafePointer.getTag( oNodeFeriado, "ns1:idUF", iCont );
					if( strlennull( cidUF ) <= 0 )
						break;
					oFeriado.InsertUF( 
										cidUF,
										cidFeriado,
										cidUser 
									 );
				}
				for( iCont=0;;iCont++ )
				{
					cindMnc  = oSafePointer.getTag( oNodeFeriado, "idMunicipio", iCont );
					if( strlennull( cindMnc ) <= 0 )
						break;
					oFeriado.InsertMnc( 
										cindMnc,
										cidFeriado,
										cidUser 
							          );
				}

			}
			else
			{
				setStatusCode("14E0008","Tag [relacionados] está faltando");
				break;
			}

		}
		else
		{
			setStatusCode("14I0000","Operacao concluída com sucesso!");
			break;
		}

	}//for( int x;; x++ )
	xml_g->closeTag();//AdmCalendarioContainerVO
	ULOG_END("implFrdNomIncluir::Execute()");
}
