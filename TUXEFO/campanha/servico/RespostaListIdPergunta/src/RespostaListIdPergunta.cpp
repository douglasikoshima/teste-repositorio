#include <stdio.h>
#include <tuxfw.h>
// #include <XMLImpl.h>

	/************************************************************************
	 * Includes para as classes comuns
	 ************************************************************************/
#include "../../negocio/CampanhaCmm/include/CResposta.h"

DECLARE_TUXEDO_SERVICE(GETRESPIDPERG);

void implGETRESPIDPERG::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/************************************************************************
	 * Declara Variaveis
	 ************************************************************************/
	char* cidPergunta = walkTree( dnode, "idPergunta", 0 ); 
		/************************************************************************
	 * Declara Estruturas
	 ************************************************************************/
	CResposta oResposta;

	/************************************************************************
	 * Declara Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Verifica Ponteiros Nulos
	 ************************************************************************/
	if( strlen( cidPergunta ) <= 0 )
	{
		setStatusCode("00E0000","idPergunta esta nulo");
		return;
	}
	/************************************************************************
	 * Inicializa Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Processamento Principal
	 ************************************************************************/

	/* Chamada de Funcao de Negocios */
	if( oResposta.ListIdPergunta( cidPergunta ) > 0 )
	{
		if( oResposta.Quantidade() > 0 )
		{
			oResposta.GetXml( "CampanhaRespostaVO", xml_g );
		}
		setStatusCode("00I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("00E0000","Nada foi encontrado, sem retorno");

	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
}
