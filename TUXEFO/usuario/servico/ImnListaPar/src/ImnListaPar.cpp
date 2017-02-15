/*****************************************************************************
 *
 * Modulo:    ImnListaPar
 * Arquivo:   ImnListaPar.cpp
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

#define ImnListaParCPP

/*****************************************************************************
 * Header de Classe de Infra-Estrutura
 ****************************************************************************/

#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CImn.h"
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include "../../../negocio/acessoCmm/include/CUsr.h"
#include "../../../negocio/acessoCmm/include/CSis.h"
#include "../../../negocio/acessoCmm/include/CSub.h"

#include<time.h>

/*****************************************************************************
 * Macro de Definicao do Framework Tuxedo
 ****************************************************************************/

DECLARE_TUXEDO_SERVICE(ImnListaPar);

/**************************************************************************
 *  Funcao de Negocios:  ImnListaPar
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

void implImnListaPar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implImnListaPar::Execute()");
	/************************************************************************
	 * Declara Variaveis
	 ************************************************************************/
	CImn   oItemMenuGeral;
	CImn   oItemMenu;
	CPgn   oPagina;
	CSub   oSubSistema;
	CSis   oSistema;
	char*  cIdUsuario;
	int    iCont;
	int    iNivel;
	int    iContTag;

	float i = (float)clock()/(float)CLOCKS_PER_SEC;
	float f;

	//Recupera o id de usuario
	cIdUsuario = getUser();

	//Busca o id de front office
	if( oSistema.SisListaSigla( "FO" ) > 0 )
	{
		if( oSubSistema.SubListaTodos( oSistema.getIdSistema() ) > 0 )
		{
			xml_g->createTag("MenuVO");
			xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" );
			for( iCont = 0; iCont < oSubSistema.Quantidade(); iCont++ )
			{
				oItemMenuGeral.ListUserMenuRapida( oSubSistema.Registro(iCont)->cidSubSistema, cIdUsuario );

				if( oItemMenu.MontaMenu( oItemMenuGeral ) > 0 )
				{
					//Monta o menu raiz
					xml_g->createTag("ItemMenuVO");
					xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" );
					xml_g->addItem("idItemMenu"    , oSubSistema.Registro(iCont)->cidSubSistema );
					xml_g->addItem("nmMenu"        , oSubSistema.Registro(iCont)->cnmSubSistema );
					xml_g->addItem("dsHint"        , oSubSistema.Registro(iCont)->cnmSubSistema );
					xml_g->addItem("sqSequencia"   , iCont+1 );

					if( oItemMenu.Quantidade() > 0 )
					{
						iNivel   = -1;
						iContTag = 0;
						//O laco continua apos o raiz
						for( int x = 0; x < oItemMenu.Quantidade(); x++ )
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
											iContTag--;
											iNivel--;
										}
										iNivel = oItemMenu.Registro(x)->iLevel;
									}
									xml_g->createTag("ItemMenuVO");
								}// else if( Registro(x)->iLevel > iNivel )

								xml_g->addItem("idItemMenu"    , oItemMenu.Registro(x)->cidItemMenu );
								xml_g->addItem("nmMenu"        , oItemMenu.Registro(x)->cnmItemMenu );
								xml_g->addItem("dsHint"        , oItemMenu.Registro(x)->cdsHint );
								xml_g->addItem("sqSequencia"   , oItemMenu.Registro(x)->csqApresentacao );

								if( oPagina.ListIdItemMenu( oItemMenu.Registro(x)->cidItemMenu ) > 0 )
								{
									xml_g->createTag("SistemaPaginaVO");
									//xml_g->addItem("idPagina"    , "" );//oPagina.Registro(0)->cidPagina            );
									//xml_g->addItem("idSubSistema", "" );//oPagina.Registro(0)->cidSubSistema        );
									//xml_g->addItem("dsSubSistema", "" );//oPagina.Registro(0)->cnmSubSistema        );
									//xml_g->addItem("nmPagina"    , "" );//oPagina.Registro(0)->cnmPagina            );
									xml_g->addItem("nmURL"       , oPagina.Registro(0)->cnmUrl               );
									//xml_g->addItem("inDisponib"  , "" );//oPagina.Registro(0)->cIndisponibilidade   );
									xml_g->closeTag();
								}//if( oPagina.ListIdItemMenu( oItemMenu.Registro(x)->cidItemMenu ) > 0 )
							}// if( Registro( x ) != NULL )
						}// for( int x = 0; x < Quantidade(); x++ )
						// Fecha todas as tags
						while( iContTag > 0 )
						{
							xml_g->closeTag();
							iContTag--;
						}
					}// if( oItemMenu.Quantidade() > 0 )
					xml_g->closeTag();//Raiz
				}//if( oItemMenu.ListUser( oSubSistema.Registro(iCont)->cidSubSistema, cIdUsuario ) > 0 );
			}//for( iCont = 0; iCont < oSubSistema.Quantidade(); iCont++ )
			xml_g->closeTag();//MenuVO
			setStatusCode("08I0000","Sucesso!");
		}//if( oSubSistema.SubListaTodos( oSistema.getIdSistema() ) > 0 )
		else
		{
			setStatusCode("08W0001","Não foram encontrados SUBSISTEMAS relacionados com ITEMMENU");
		}//if( oSubSistema.SubListaTodos( oSistema.getIdSistema() ) > 0 )
	}//if( oSistema.SisListaSigla( "FO" ) > 0 )
	else
	{
		setStatusCode("08E0003","Não foi possível");
	}//else if( oSistema.SisListaSigla( "FO" ) > 0 )

	f = (float)clock()/(float)CLOCKS_PER_SEC;
	ULOG("\n\nTempo Inicial:%7.5f\nTempo final:%7.5f\nTempo total:%7.5f\n\n", i, f, f-i );
	ULOG_END("implImnListaPar::Execute()");
}
