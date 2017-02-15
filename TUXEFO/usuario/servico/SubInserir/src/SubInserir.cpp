/*****************************************************************************
 *
 * Modulo:    SubInserir
 * Arquivo:   SubInserir.cpp
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
#define SubInserirCPP

//Header de Sub de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CSub.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SubInserir);

/**************************************************************************
 *  Funcao de Negocios:  SubInserir
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
void implSubInserir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implSubInserir::Execute()");
	/* Chamada de Funcao de Negocios */
	CSub oSub;
	switch (oSub.SubInserir(dnode,xml_g, getUser())) {
		case 0:	oSub.SubLista(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","dsSubSistema esta nulo"); break;
		case 2: setStatusCode("08E0200","idSistema esta nulo"); break;
		case 3: setStatusCode("08W0300","Nome passado como parametro ja existe na base"); break;
		case 4: setStatusCode("08E0400","Falha na edicao de Sub-sistema"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implSubInserir::Execute()");
	return;
}
