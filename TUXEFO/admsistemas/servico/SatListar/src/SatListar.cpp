/*****************************************************************************
 *
 * Modulo:    SatListar
 * Arquivo:   SatListar.cpp
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
#define SatListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CSat.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SatListar);

/**************************************************************************
 *  Funcao de Negocios:  SatListar
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
void implSatListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implSatListar::Execute()");
	CSafePointer oSafePointer;
	CPesquisaSatisfacao oPesquisaSatisfacao;
	char* cdsQuestionario = oSafePointer.getTag( dnode, "dsQuestionario", 0 );

	xml_g->createTag("AdmQuestionarioContainerVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );

	if( strlennull( cdsQuestionario ) <= 0 )
		oPesquisaSatisfacao.ListAll();
	else
		oPesquisaSatisfacao.ListPar( cdsQuestionario );
	oPesquisaSatisfacao.GetXml( "AdmQuestionarioVO", xml_g );

	xml_g->closeTag();
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	ULOG_END("implSatListar::Execute()");
}

/*
	CPergunta oPergunta;
	CTipoApresentacaoPergunta oTipoApresentacaoPergunta;
	char* cidQuestionario = oSafePointer.getTag( dnode, "idQuestionario", 0 );
	if( strlennull( cidQuestionario ) <= 0 )
	{
		setStatusCode("14E0000", "idQuestionario esta nulo" );
		return;
	}

	xml_g->createTag("AdmSatisfacaoContainerVO");

	oPergunta.ListIdPesquisa( cidQuestionario );
	if( oPergunta.Quantidade() > 0 )
	{
		xml_g->addItem("idQuestionario", oPergunta.Registro(0)->cidPesquisaSatisfacao );
		xml_g->addItem("dsQuestionario", oPergunta.Registro(0)->cnmPesquisaSatisfacao );
		
		oPergunta.GetXml( "AdmPerguntaVO", xml_g );
	}
	else
	{
		xml_g->addItem("idQuestionario", "" );
		xml_g->addItem("dsQuestionario", "" );
	}

	oTipoApresentacaoPergunta.ListAll();
	oTipoApresentacaoPergunta.GetXml( "AdmListaObjetosSatisfacaoVO", xml_g );

	xml_g->closeTag();
	setStatusCode("14I0000","Operacao concluida com sucesso!");
*/