#include <stdio.h>
#include <tuxfw.h>
// #include <XMLImpl.h>

	/************************************************************************
	 * Includes para as classes comuns
	 ************************************************************************/
#include "../../negocio/CampanhaCmm/include/CPergunta.h"
#include "../../negocio/CampanhaCmm/include/CResposta.h"
#include "../../negocio/CampanhaCmm/include/CTipoApresentacaoPergunta.h"

DECLARE_TUXEDO_SERVICE(GETPERGLISTID);

void implGETPERGLISTID::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/************************************************************************
	 * Declara Variaveis
	 ************************************************************************/
	char* cidPergunta = walkTree( dnode, "idPergunta", 0 ); 
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
	if( oPergunta.ListId( cidPergunta ) > 0 )
	{
		if( oPergunta.Quantidade() > 0 )
		{
			//Monsta a pergunta
			xml_g->createTag("CampanhaPerguntaVO");
			xml_g->addProp( "xmlns", "campanha.fo.vivo.com.br/vo" );
			xml_g->addItem("idPergunta"       , oPergunta.Registro(0)->cidPergunta );
			xml_g->addItem("idTipoApresentacaoPergunta" , oPergunta.Registro(0)->cidTipoApresentacaoPergunta );
			xml_g->addItem("dsPergunta"       , oPergunta.Registro(0)->cdsPergunta );
			xml_g->addItem("inEncerramento"   , oPergunta.Registro(0)->cinEncerramento );
			xml_g->addItem("inDisponibilidade", oPergunta.Registro(0)->cinDisponibilidade );
			xml_g->addItem("dsScriptPergunta" , oPergunta.Registro(0)->cdsScriptPergunta );
			xml_g->addItem("sqApresentacao"   , oPergunta.Registro(0)->csqApresentacao );
			xml_g->addItem("inObrigatoria"    , oPergunta.Registro(0)->cinObrigatoria );
			//Recupera e monta a Apresentacao
			if( oTipoApresentacaoPergunta.ListId( oPergunta.Registro(0)->cidTipoApresentacaoPergunta ) > 0 )
			{
				oTipoApresentacaoPergunta.GetXml( "ApresentacaoVO", xml_g );
			}
			//Recupera e monsta as resposta
			if( oResposta.ListIdPergunta( cidPergunta ) > 0 )
			{
				oResposta.GetXml( "CampanhaRespostaVO", xml_g );
			}
			xml_g->closeTag();
			
		}
		setStatusCode("00I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("00E0000","Nada foi encontrado, sem retorno");

	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
}
