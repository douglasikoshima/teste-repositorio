/*****************************************************************************
 *
 * Modulo:    ImnLista
 * Arquivo:   ImnLista.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c 
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/

#define ImnListaCPP

/*****************************************************************************
 * Header de Classe de Infra-Estrutura
 ****************************************************************************/

#include "../../../negocio/acessoCmm/include/CImn.h"
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include <tuxfw.h> 
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

/*****************************************************************************
 * Macro de Definicao do Framework Tuxedo
 ****************************************************************************/

DECLARE_TUXEDO_SERVICE(ImnLista);

/**************************************************************************
 *  Funcao de Negocios:  ImnLista
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

/*****************************************************************************
 * Funcao Local
 ****************************************************************************/

void implImnLista::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implImnLista::Execute()");
	CSafePointer oSafePointer;
	char*     cIdSubSistema;
	CImn      oItemMenu;
	CPgn      oPagina;
	int       iNivel;
	int       iContTag;

	cIdSubSistema = oSafePointer.getTag( dnode, "idSubSistema", 0 );
	
	if( strlen( cIdSubSistema ) > 0 )
	{
		oItemMenu.ListId(cIdSubSistema);

		if( oItemMenu.Quantidade() > 0 )
		{
			iNivel   = 0;
			iContTag = 0;
			
			//Monta o menu raiz
			xml_g->createTag("ItemMenuVO");
			xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" );
			xml_g->addItem("idItemMenu"    , oItemMenu.Registro(0)->cidItemMenu );
			xml_g->addItem("nmMenu"        , oItemMenu.Registro(0)->cnmItemMenu );
			xml_g->addItem("dsHint"        , oItemMenu.Registro(0)->cdsHint );
			xml_g->addItem("nrNivel"       , oItemMenu.Registro(0)->iLevel );
			xml_g->addItem("idItemMenuPai" , oItemMenu.Registro(0)->cidItemMenuPai );
			xml_g->addItem("inVisibilidade", oItemMenu.Registro(0)->cinVisibilidade );
			xml_g->addItem("sqSequencia"   , oItemMenu.Registro(0)->csqApresentacao );
			xml_g->addItem("inFolha"       , oItemMenu.Registro(0)->cinFolha );
			
			//O laco continua apos o raiz
			for( int x = 1; x < oItemMenu.Quantidade(); x++ )
			{
				if( oItemMenu.Registro( x ) != NULL )
				{

					if( oItemMenu.Registro(x)->iLevel > iNivel )
					{
						iNivel = oItemMenu.Registro(x)->iLevel;
						xml_g->createTag("ItemMenuVO");
						iContTag++;
					}//if( Registro(x)->iLevel > iNivel )
					else
					{
						xml_g->closeTag();
						if( oItemMenu.Registro(x)->iLevel < iNivel )
						{
							while( iNivel > oItemMenu.Registro(x)->iLevel )
							{
								xml_g->closeTag();
								iNivel--;
							}
							iNivel = oItemMenu.Registro(x)->iLevel;
						}
						xml_g->createTag("ItemMenuVO");
					}// else if( Registro(x)->iLevel > iNivel )

					xml_g->addItem("idItemMenu"    , oItemMenu.Registro(x)->cidItemMenu );
					xml_g->addItem("nmMenu"        , oItemMenu.Registro(x)->cnmItemMenu );
					xml_g->addItem("dsHint"        , oItemMenu.Registro(x)->cdsHint );
					xml_g->addItem("nrNivel"       , oItemMenu.Registro(x)->iLevel );
					xml_g->addItem("idItemMenuPai" , oItemMenu.Registro(x)->cidItemMenuPai );
					xml_g->addItem("inVisibilidade", oItemMenu.Registro(x)->cinVisibilidade );
					xml_g->addItem("sqSequencia"   , oItemMenu.Registro(x)->csqApresentacao );
					xml_g->addItem("inFolha"       , oItemMenu.Registro(x)->cinFolha );

					if( oPagina.ListIdItemMenu( oItemMenu.Registro(x)->cidItemMenu ) )
					{
						for( int x = 0; x < oPagina.Quantidade(); x++ )
						{
							xml_g->createTag("SistemaPaginaVO");
							if( oPagina.Registro( x ) != NULL )
							{
								xml_g->addItem("idPagina"    , oPagina.Registro(x)->cidPagina            );
								xml_g->addItem("idSubSistema", oPagina.Registro(x)->cidSubSistema        );
								xml_g->addItem("dsSubSistema", oPagina.Registro(x)->cnmSubSistema        );
								xml_g->addItem("nmPagina"    , oPagina.Registro(x)->cnmPagina            );
								xml_g->addItem("nmURL"       , oPagina.Registro(x)->cnmUrl               );
								xml_g->addItem("inDisponib"  , oPagina.Registro(x)->cIndisponibilidade   );
							}
							xml_g->closeTag();
						}
					}
					
				}// if( Registro( x ) != NULL )
			}// for( int x = 0; x < Quantidade(); x++ )
			// Fecha todas as tags
			while( iContTag > 0 )
			{
				xml_g->closeTag();
				iContTag--;
			}
		}// if( oItemMenu.Quantidade() > 0 )
		setStatusCode("08I0000","Operacao realizada com sucesso");
	}
	else
	{
		setStatusCode("08E0000","Tag [idSubSistema] nao existe");
	}
	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
	
	ULOG_END("implImnLista::Execute()"); 
}

