/*****************************************************************************
 *
 * Modulo:    FrdMncListarId
 * Arquivo:   FrdMncListarId.cpp
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
#define FrdMncListarIdCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CMnc.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdMncListarId);

/**************************************************************************
 *  Funcao de Negocios:  FrdMncListarId
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
void implFrdMncListarId::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdMncListarId::Execute()");
   
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CMunicipio oMunicipio;
	DOMNode* oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", 0 );
	if( oNodeFeriado != NULL )
	{
		char* cidUF = oSafePointer.getTag( oNodeFeriado, "idUf", 0 );
		if( strlennull( cidUF ) > 0 )
		{
			xml_g->createTag( "AdmCalendarioContainerVO" );
			xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );

			oMunicipio.ListIdUF( cidUF );
			oMunicipio.GetXml("MunicipioVO", xml_g);

			xml_g->closeTag();
			setStatusCode("14I0000","Operação concluída com sucesso!");
		}
		else
			setStatusCode("14E0001","idUf não pode estar nula");
	}
	else
		setStatusCode("14E0002","Náo foi encontrada a tag AdmFeriadoVO");
		
	ULOG_END("implFrdMncListarId::Execute()");
}
