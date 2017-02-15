/*****************************************************************************
 *
 * Modulo:    SubListaIdSis
 * Arquivo:   SubListaIdSis.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDJMartins          Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define SubListaIdSisCPP

//Header de Sub de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CSub.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SubListaIdSis);

/**************************************************************************
 *  Funcao de Negocios:  SubListaIdSis
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
void implSubListaIdSis::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implSubListaIdSis::Execute()");
	/* Chamada de Funcao de Negocios */
	CSub oSub;
	switch (oSub.SubListaIdSis(dnode,xml_g)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idSistema esta nulo"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implSubListaIdSis::Execute()");
	return;
}