/*****************************************************************************
 *
 * Modulo:    GrpImnRelacao
 * Arquivo:   GrpImnRelacao.cpp
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
#define GrpImnRelacaoCPP

//Header de Grp de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CGrp.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GrpImnRelacao);

/**************************************************************************
 *  Funcao de Negocios:  GrpImnRelacao
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
void implGrpImnRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implGrpImnRelacao::Execute()");
	/* Chamada de Funcao de Negocios */
	CGrp oGrp;
	switch (oGrp.GrpImnRelacao(dnode,xml_g)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idGrupo esta nulo"); break;
		case 2: setStatusCode("08E0100","idSubSistema esta nulo"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implGrpImnRelacao::Execute()");
	return;
}
