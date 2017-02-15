//
// $Id: PerguntaListCanalCampanha.cpp,v 1.1 2009/07/31 15:35:07 a5110702 Exp $
//

#include <stdio.h>
#include <tuxfw.h>

	/************************************************************************
	 * Includes para as classes comuns
	 ************************************************************************/
#include "../../negocio/CampanhaCmm/include/CPergunta.h"
#include "../../negocio/CampanhaCmm/include/CResposta.h"
#include "../../negocio/CampanhaCmm/include/CTipoApresentacaoPergunta.h"

DECLARE_TUXEDO_SERVICE(GETPERGCNLCMP);

void implGETPERGCNLCMP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/************************************************************************
	* Declara Variaveis
	************************************************************************/
	char* cidCanalCampanha = walkTree( dnode, "idCanalCampanha", 0 ); 
	char* cidPergunta      = walkTree( dnode, "idPergunta", 0 ); 
	char* cinResposta      = walkTree( dnode, "inResposta", 0 ); 
	/************************************************************************
	* Declara Estruturas
	************************************************************************/
	CPergunta oPergunta;
	CResposta oResposta;
	CTipoApresentacaoPergunta oTipoApresentacaoPergunta;
	
	/************************************************************************
	* Declara Ponteiros
	************************************************************************/
	
	/************************************************************************
	* Verifica Ponteiros Nulos
	************************************************************************/
	if( strlen( cidCanalCampanha ) <= 0 )
	{
		setStatusCode("00E0000","idCanalCampanha esta nulo");
		return;
	}
	if( strlen( cidPergunta ) <= 0 )
	{
		setStatusCode("00E0000","idPergunta esta nulo");
		return;
	}
	if( strlen( cinResposta ) <= 0 )
	{
		setStatusCode("00E0000","inResposta esta nulo");
		return;
	}
	/************************************************************************
	* Inicializa Ponteiros
	************************************************************************/

  /************************************************************************
   * Processamento Principal
   ************************************************************************/

	/* Chamada de Funcao de Negocios */
	xml_g->createTag("CampanhaExecScriptVO");
	xml_g->addProp( "xmlns", "campanha.fo.vivo.com.br/vo" );
	if( oPergunta.ListIdCanalCampanha( cidCanalCampanha ) > 0 )
	{
		for( int x = 0; x < oPergunta.Quantidade(); x++ )
		{
			// Monsta a pergunta
			// Soh fazer se idPergunta selecionada for diferente da recebida
			if (strcmp(cidPergunta,oPergunta.Registro(x)->cidPergunta))
			{
				xml_g->createTag("CampanhaPerguntaVO");
				xml_g->addProp( "xmlns", "campanha.fo.vivo.com.br/vo" );
				xml_g->addItem("idPergunta"       , oPergunta.Registro(x)->cidPergunta );
				xml_g->addItem("dsPergunta"       , oPergunta.Registro(x)->cdsPergunta );
				xml_g->addItem("dsScriptPergunta" , oPergunta.Registro(x)->cdsScriptPergunta );
				xml_g->addItem("sqApresentacao"   , oPergunta.Registro(x)->csqApresentacao );
				xml_g->addItem("inEncerramento"   , oPergunta.Registro(x)->cinEncerramento );
				xml_g->addItem("inDisponibilidade", oPergunta.Registro(x)->cinDisponibilidade );
				xml_g->addItem("inObrigatoria"    , oPergunta.Registro(x)->cinObrigatoria );
				xml_g->addItem("idApresentacao"   , oPergunta.Registro(x)->cidTipoApresentacaoPergunta );
				//Recupera e monsta as resposta
				ULOG("idPergunta: [%s]"       , oPergunta.Registro(x)->cidPergunta );
				ULOG("dsPergunta: [%s]"       , oPergunta.Registro(x)->cdsPergunta );
				ULOG("dsScriptPergunta: [%s]" , oPergunta.Registro(x)->cdsScriptPergunta );
				ULOG("sqApresentacao: [%s]"   , oPergunta.Registro(x)->csqApresentacao );
				ULOG("inEncerramento: [%s]"   , oPergunta.Registro(x)->cinEncerramento );
				ULOG("inDisponibilidade: [%s]", oPergunta.Registro(x)->cinDisponibilidade );
				ULOG("inObrigatoria: [%s]"    , oPergunta.Registro(x)->cinObrigatoria );
				ULOG("idApresentacao: [%s]"   , oPergunta.Registro(x)->cidTipoApresentacaoPergunta );
				if (atoi(cinResposta) == 1)
				{
					if( oResposta.ListIdPergunta( oPergunta.Registro(x)->cidPergunta ) > 0 )
					{
						oResposta.GetXml( "CampanhaRespostaVO", xml_g );
					}
				}
				xml_g->closeTag();
			}
		}
	}
	xml_g->closeTag();
	
	setStatusCode("00I0000","Operacao concluida com sucesso!");
	
	/************************************************************************
	* Desalocacao de Ponteiros
	************************************************************************/
}

