/*****************************************************************************
 *
 * Modulo:    CttRetRelacao
 * Arquivo:   CttRetRelacao.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDMartins           Criacao
 *             charles
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define CttRetRelacaoCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSms.h"
#include "../../../negocio/admatdCmm/include/CFlh.h"
#include "../../../negocio/admatdCmm/include/CFlhAnt.h"
#include "../../../negocio/admatdCmm/include/CCttCmb.h"
#include "../../../negocio/admatdCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttRetRelacao);

/**************************************************************************
 *  Funcao de Negocios:  CttRetRelacao
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
void implCttRetRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttRetRelacao::Execute()");
	CSafePointer   oSafePointer;
	CContatoFolha  oContatoFolha;
	CIndicadorContatoFolha oIndicadorContatoFolha;
	CContatoCombos			oContatoCombos;
	CSms oSms;

	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );

	xml_g->createTag( "AdmSelectsContatoFolhaVO" );
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addItem("idContato", cidContato );
    oSms.ListAll();
    oSms.GetXml( xml_g );


	xml_g->createTag( "folha" );

	if( strlennull( cidContato ) <= 0 ) 
	{
        setStatusCode("14I0000"," cidConta verdade ");
		//AdmIndicadoresAnatelContatoContainerVO
		xml_g->createTag("AdmIndicadoresAnatelContatoContainerVO");

			xml_g->addItem("idContato", cidContato );
		
			xml_g->createTag("indicadoresAssociados");
			xml_g->closeTag();//indicadoresAssociados

			xml_g->createTag("indicadoresExistentes");

				if( oIndicadorContatoFolha.ListAll() > 0 )
					oIndicadorContatoFolha.GetXml( "AdmIndicadorAnatelVO", xml_g );

			xml_g->closeTag();//indicadoresExistentes

		xml_g->closeTag();//AdmIndicadoresAnatelContatoContainerVO


        // charles modificado resposta cliente
        // retorna os grupos com contatos
        setStatusCode("14I0000"," grupo contato ");
	}
	else //if( strlennull( cidContato ) <= 0 ) 
	{
		

        setStatusCode("14I0000"," cidConta Falso ");
		oContatoFolha.ListId( cidContato );
		oContatoFolha.GetXml( "AdmDadosBasicosVO", xml_g );
		
		//AdmIndicadoresAnatelContainerVO
		xml_g->createTag("AdmIndicadoresAnatelContatoContainerVO");

			xml_g->addItem("idContato", cidContato );
			oIndicadorContatoFolha.GetXml( "AdmDadosBasicoVO", xml_g );
		
			xml_g->createTag("indicadoresAssociados");
				if( oIndicadorContatoFolha.ListIdContatoFolhaRel( cidContato ) > 0 )
					oIndicadorContatoFolha.GetXml( "AdmIndicadorAnatelVO", xml_g );
			xml_g->closeTag();//indicadoresAssociados

			xml_g->createTag("indicadoresExistentes");
				if( oIndicadorContatoFolha.ListIdContatoFolhaNaoRel( cidContato ) > 0 )
					oIndicadorContatoFolha.GetXml( "AdmIndicadorAnatelVO", xml_g );
			xml_g->closeTag();//indicadoresExistentes

		xml_g->closeTag(); //AdmIndicadoresAnatelContainerVO

	}   //else if( strlennull( cidContato ) <= 0 ) 

	xml_g->closeTag(); //folha
	
	oContatoFolha.GetXmlSMP( xml_g );

	oContatoCombos.Relacao( xml_g );

	xml_g->closeTag();//AdmSelectsContatoFolhaVO
	setStatusCode("14I0000","Serviço executado com sucesso");
	
	ULOG_END("implCttRetRelacao::Execute()");
}
