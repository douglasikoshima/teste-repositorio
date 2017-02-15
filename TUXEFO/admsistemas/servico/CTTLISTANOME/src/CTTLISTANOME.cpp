/*****************************************************************************
 * Arquivo:   CTTLISTANOME.cpp
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 16/03/2005  C_edmartins           Criacao
 *
 ****************************************************************************/

//Definicao Global
#define CTTLISTANOMECPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CCttNom.h"
#include "../../../negocio/admatdCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTLISTANOME);

void implCTTLISTANOME::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCTTLISTANOME::Execute()");
   
	CNomeContato oNomeContato;
	CSafePointer oSafePointer;

	char* cnmContato = oSafePointer.getTag( dnode, "nmContato", 0 );
    char* cidTipoArvore = oSafePointer.getTag( dnode, "idTipoArvore", 0 );
	
	xml_g->createTag("AdmArvoreContainerVO");
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

	oNomeContato.ListPar( cnmContato,cidTipoArvore );
	oNomeContato.GetXml( "AdmNomeContatoVO", xml_g );

	xml_g->closeTag();//AdmArvoreContainerVO
	
	setStatusCode("14I0000","Serviço finalizado com sucesso!");
	
	ULOG_END("implCTTLISTANOME::Execute()");
}