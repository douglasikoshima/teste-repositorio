/*****************************************************************************
 *
 * Modulo:    MncListar
 * Arquivo:   MncListar.cpp
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
#define MncListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CMnc.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(MncListar);

/**************************************************************************
 *  Funcao de Negocios:  MncListar
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
void implMncListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implMncListar::Execute()");
	/* Chamada de Funcao de Negocios */
	CMunicipio oMunicipio;
	oMunicipio.ListAll();
	oMunicipio.GetXml("MunicipioVO", xml_g);
	setStatusCode("14I0000","Operacao concluida com sucesso!");
	ULOG_END("implMncListar::Execute()");
}
