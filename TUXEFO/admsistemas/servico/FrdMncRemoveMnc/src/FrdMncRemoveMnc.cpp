/*****************************************************************************
 *
 * Modulo:    FrdMncRemoveMnc
 * Arquivo:   FrdMncRemoveMnc.cpp
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
#define FrdMncRemoveMncCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CFrdMnc.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdMncRemoveMnc);

/**************************************************************************
 *  Funcao de Negocios:  FrdMncRemoveMnc
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
void implFrdMncRemoveMnc::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdMncRemoveMnc::Execute()");
	/* Chamada de Funcao de Negocios */
	CMunicipioFeriado oMunicipioFeriado;
	char* cidMunicipio = walkTree( dnode, "idMunicipio", 0 );
	if( cidMunicipio == NULL )
	{
		setStatusCode("00E0000","idMunicipio esta nulo");
		ULOG_END("implFrdMncRemoveMnc::Execute()");
		return;
	}
	if( oMunicipioFeriado.EraseMnc( cidMunicipio ) )
	{
		oMunicipioFeriado.ListAll();
		oMunicipioFeriado.GetXml("FeriadoNomeVO", xml_g);
		setStatusCode("00I0000","Operacao concluida com sucesso!");
	}
	else
		setStatusCode("00E0000","Falha na tentativa de DELETE");
		
	ULOG_END("implFrdMncRemoveMnc::Execute()");
	
}
