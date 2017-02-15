#include <stdio.h>

#include <tuxfw.h>

//#include <XMLImpl.h>


	/************************************************************************
	 * Includes para as classes comuns
	 ************************************************************************/

#include "../../negocio/CampanhaCmm/include/CMotivoCampanha.h"


DECLARE_TUXEDO_SERVICE(UPDMOTIVOCMP);


void implUPDMOTIVOCMP::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	/************************************************************************
	 * Declara Variaveis
	 ************************************************************************/

	char* cidMotivoCampanha        = walkTree( dnode, "idMotivoCampanha"       , 0 ); 
	char* cidSubCampanhaHistorico  = walkTree( dnode, "idSubCampanhaHistorico" , 0 ); 
	char* cidTipoStatusCampanha    = walkTree( dnode, "idTipoStatusCampanha"   , 0 ); 
	char* cidTipoMotivoCampanha    = walkTree( dnode, "idTipoMotivoCampanha"   , 0 ); 
	char* cidTipoSubMotivoCampanha = walkTree( dnode, "idTipoSubMotivoCampanha", 0 ); 
	char* cidUsuarioAlteracao = getUser(); //Modificación CVA 30/09/2004
	/************************************************************************
	 * Declara Estruturas
	 ************************************************************************/
	CMotivoCampanha oMotivoCampanha;

	/************************************************************************
	 * Declara Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Verifica Ponteiros Nulos
	 ************************************************************************/

	if( strlen( cidMotivoCampanha ) <= 0 )
	{
		setStatusCode("00E0000","idMotivoCampanha esta nulo");
		return;
	}

	if( strlen( cidSubCampanhaHistorico ) <= 0 )
	{
		setStatusCode("00E0000","idSubCampanhaHistorico esta nulo");
		return;
	}

	if( strlen( cidTipoStatusCampanha ) <= 0 )
	{
		setStatusCode("00E0000","idTipoStatusCampanha esta nulo");
		return;
	}

	if( strlen( cidTipoMotivoCampanha ) <= 0 )
	{
		setStatusCode("00E0000","idTipoMotivoCampanha esta nulo");
		return;
	}

	if( strlen( cidTipoSubMotivoCampanha ) <= 0 )
	{
		setStatusCode("00E0000","idTipoSubMotivoCampanha esta nulo");
		return;
	}

	/************************************************************************
	 * Inicializa Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Processamento Principal
	 ************************************************************************/

	/* Chamada de Funcao de Negocios */

	if( oMotivoCampanha.Update( cidMotivoCampanha,
		                        cidSubCampanhaHistorico, 
		                        cidTipoStatusCampanha,
                                cidTipoMotivoCampanha,
							    cidTipoSubMotivoCampanha,
								cidUsuarioAlteracao) )

		setStatusCode("00I0000","Operacao concluida com sucesso!");
	else
		setStatusCode("00E0000","Falha na edicao de MotivoCampanha");

	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/

}

