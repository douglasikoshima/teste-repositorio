/*****************************************************************************
 *
 * Modulo:    ImnListaId
 * Arquivo:   ImnListaId.cpp
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

#define ImnListaIdCPP

/*****************************************************************************
 * Header de Classe de Infra-Estrutura
 ****************************************************************************/

#include "../../../negocio/acessoCmm/include/CImn.h"
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include "../../../negocio/acessoCmm/include/CUsr.h"
#include "../../../negocio/acessoCmm/include/CSafePointer.h"
#include <tuxfw.h> 

/*****************************************************************************
 * Macro de Definicao do Framework Tuxedo
 ****************************************************************************/

DECLARE_TUXEDO_SERVICE(ImnListaId);

/**************************************************************************
 *  Funcao de Negocios:  ImnListaId
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

void implImnListaId::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implImnListaId::Execute()");
	
	/************************************************************************
	 * Declara Variaveis
	 ************************************************************************/
	CSafePointer oSafePointer;
	CImn   oItemMenu;
	CPgn   oPagina;
	char*  cIdSubSistema;
	char*  cIdUsuario;
	int    iNivel;
	int    iContTag;

	/************************************************************************
	 * Declara Estruturas
	 ************************************************************************/

	/************************************************************************
	 * Declara Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Verifica Ponteiros Nulos
	 ************************************************************************/

	/************************************************************************
	 * Inicializa Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Processamento Principal
	 ************************************************************************/

	/* Chamada de Funcao de Negocios */

	cIdSubSistema = oSafePointer.getTag( dnode, "idSubSistema", 0 );
	cIdUsuario    = getUser();
	
	if( ( strlennull( cIdSubSistema ) > 0 ) && ( strlennull( cIdUsuario ) > 0 ) )
	{
		oItemMenu.ListUser(cIdSubSistema, cIdUsuario);

		//Monta o menu raiz
		xml_g->createTag("ItemMenuVO");
		xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" );
		xml_g->addItem("idItemMenu"    , oItemMenu.Registro(0)->cidItemMenu );
		xml_g->addItem("nmMenu"        , oItemMenu.Registro(0)->cnmItemMenu );
		xml_g->addItem("dsHint"        , oItemMenu.Registro(0)->cdsHint );
		xml_g->addItem("nrNivel"       , oItemMenu.Registro(0)->iLevel );
		xml_g->addItem("idItemmenuPai" , oItemMenu.Registro(0)->cidItemMenuPai );
		xml_g->addItem("inVisibilidade", oItemMenu.Registro(0)->cinVisibilidade );
		xml_g->addItem("sqSequencia"   , oItemMenu.Registro(0)->csqApresentacao );
		xml_g->addItem("inFolha"       , oItemMenu.Registro(0)->cinFolha );

		if( oItemMenu.Quantidade() > 0 )
		{
			iNivel   = 0;
			iContTag = 0;
			
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
					xml_g->addItem("idItemmenuPai" , oItemMenu.Registro(x)->cidItemMenuPai );
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
		xml_g->closeTag();//Raiz
		setStatusCode("08I0000","Sucesso!");
	}
	else
	{
		if( strlennull( cIdSubSistema ) <= 0 )
			setStatusCode("08E0001","idSubSistema esta nulo");
		else
			setStatusCode("08E0002","Usuario no XML HEADER esta nulo");
	}
	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
	 
	ULOG_END("implImnListaId::Execute()");
}
