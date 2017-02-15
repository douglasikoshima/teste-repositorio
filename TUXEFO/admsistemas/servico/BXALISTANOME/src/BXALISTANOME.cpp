/*****************************************************************************
 * Arquivo:   BXALISTANOME.cpp
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 16/03/2005  C_edmartins           Criacao
 *
 ****************************************************************************/

//Definicao Global
#define BXALISTANOMECPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CBxaNom.h"
#include "../../../negocio/admatdCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXALISTANOME);

void implBXALISTANOME::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXALISTANOME::Execute()");
	CNomeBaixa oNomeBaixa;
	CSafePointer oSafePointer;

	char* cnmBaixa = oSafePointer.getTag( dnode, "nmBaixa", 0 );
	
	xml_g->createTag("AdmArvoreBaixaContainerVO");
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

	oNomeBaixa.ListPar( cnmBaixa );
	oNomeBaixa.GetXml( "AdmNomeBaixaVO", xml_g );

	xml_g->closeTag();//AdmArvoreContainerVO
	
	setStatusCode("14I0000","Serviço finalizado com sucesso!");
	ULOG_END("implBXALISTANOME::Execute()");
}