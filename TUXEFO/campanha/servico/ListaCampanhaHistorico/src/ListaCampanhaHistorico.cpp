#include <stdio.h>
#include <tuxfw.h>

	/************************************************************************
	 * Includes para as classes comuns
	 ************************************************************************/
#include "CListaCampanhaHistorico.h"

DECLARE_TUXEDO_SERVICE(GETCMPHISTORI);

void implGETCMPHISTORI::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/************************************************************************
	 * Declara Variaveis
	 ************************************************************************/
	char* cidPessoaDePara        = walkTree( dnode, "idPessoaDePara", 0 ); 
	char* dtInicio               = walkTree( dnode, "dtInicio", 0 ); 
	char* dtTermino              = walkTree( dnode, "dtTermino", 0 ); 
	/************************************************************************
	 * Declara Estruturas
	 ************************************************************************/
	CListaCampanhaHistorico oListaCampanhaHistorico;

	/************************************************************************
	 * Declara Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Verifica Ponteiros Nulos
	 ************************************************************************/
	if( strlen( cidPessoaDePara ) <= 0 )
	{
		setStatusCode("00E0000","idPessoaDePara esta nulo");
		return;
	}
	if( strlen( dtInicio ) <= 0 )
	{
		setStatusCode("00E0000","dtInicio esta nulo");
		return;
	}
	if( strlen( dtTermino ) <= 0 )
	{
		setStatusCode("00E0000","dtTermino esta nulo");
		return;
	}
	/************************************************************************
	 * Inicializa Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Processamento Principal
	 ************************************************************************/

	/* Chamada de Funcao de Negocios */
	if( oListaCampanhaHistorico.ListId( cidPessoaDePara, dtInicio, dtTermino ) )
	{
		xml_g->createTag("tns:ListaCampanhaHistoricoVO");
		xml_g->addProp( "xmlns:tns", "campanha.fo.vivo.com.br/vo" );
		xml_g->addProp( "xmlns:xxl", "campanha.fo.vivo.com.br/vo" );
		xml_g->addProp( "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance" );
		    oListaCampanhaHistorico.GetXml( NULL, xml_g );
		xml_g->closeTag();
		setStatusCode("05I0000","Executado com sucesso.");
	}
	else
        {
		xml_g->createTag("tns:ListaCampanhaHistoricoVO");
		xml_g->addProp( "xmlns:tns", "campanha.fo.vivo.com.br/vo" );
		xml_g->addProp( "xmlns:xxl", "campanha.fo.vivo.com.br/vo" );
		xml_g->addProp( "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance" );
                /* xml_g->createTag("tns:CampanhaHistorico");
                    xml_g->addItem("idCampanha","");
                    xml_g->addItem("sgCampanha","" );
                    xml_g->addItem("idSubCampanha","");
                    xml_g->addItem("sgSubCampanha","");
                    xml_g->addItem("idCanal","" );
                    xml_g->addItem("sgCanal","" );
                    xml_g->addItem("dtAtendimento","" );
                    xml_g->addItem("idOperadora","" );
                    xml_g->addItem("sgOperadora","" );
                    xml_g->addItem("idMotivo","" );
                    xml_g->addItem("sgMotivo","" );
                    xml_g->addItem("idSubMotivo","" );
                    xml_g->addItem("sgSubMotivo","" );
                    xml_g->addItem("idStatus","" );
                    xml_g->addItem("sgStatus","" );
                    xml_g->addItem("vlTempoMedio","" );*/
                 xml_g->closeTag();
		xml_g->closeTag();
		setStatusCode("05I0000","Nenhum registro retornado.");
        }
	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
}
