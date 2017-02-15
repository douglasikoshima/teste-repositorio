/*****************************************************************************
 *
 * Modulo:    MncListarId
 * Arquivo:   MncListarId.cpp
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
#define MncListarIdCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CMnc.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(MncListarId);

/**************************************************************************
 *  Funcao de Negocios:  MncListarId
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
void implMncListarId::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implMncListarId::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CMunicipio oMunicipio;
	char* cidMunicipio = oSafePointer.getTag( dnode, "idMunicipio", 0 );
	if( cidMunicipio == NULL )
	{
		setStatusCode("14E0000","idMunicipio esta nulo");
		ULOG_END("implMncListarId::Execute()");
		return;
	}
	oMunicipio.ListId( cidMunicipio );
	oMunicipio.GetXml("MunicipioVO", xml_g);
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	ULOG_END("implMncListarId::Execute()");
}
