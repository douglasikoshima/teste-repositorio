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
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrdMnc.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FRDMNCREMOVEM);

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
void implFRDMNCREMOVEM::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFRDMNCREMOVEM::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CMunicipioFeriado oMunicipioFeriado;
	char* cidMunicipio = oSafePointer.getTag( dnode, "idMunicipio", 0 );
	if( cidMunicipio == NULL )
	{
		setStatusCode("14E0001","idMunicipio está nulo");
		ULOG_END("implFRDMNCREMOVEM::Execute()");
		return;
	}
	if( oMunicipioFeriado.EraseMnc( cidMunicipio ) )
	{
		oMunicipioFeriado.ListAll();
		oMunicipioFeriado.GetXml("FeriadoNomeVO", xml_g);
		setStatusCode("14I0000","Operação concluída com sucesso!");
	}
	else
		setStatusCode("14E0002","Falha na tentativa de DELETE");
	ULOG_END("implFRDMNCREMOVEM::Execute()");
}
