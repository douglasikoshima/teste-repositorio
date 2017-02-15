/*****************************************************************************
 *
 * Modulo:    GrpImnRelaciona
 * Arquivo:   GrpImnRelaciona.cpp
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
#define GrpImnRelacionaCPP

//Header de Grp de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CGrp.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GrpImnRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  GrpImnRelaciona
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
void implGrpImnRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implGrpImnRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	CGrp oGrp;
	switch (oGrp.GrpImnRelaciona(dnode,xml_g, getUser())) {
		case 0:	oGrp.GrpImnRelacao(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Falha ao tentar limpar itens de menu nao relacionados"); break;
		case 2: setStatusCode("08E0200","Falha ao tentar inserir itens de menu relacionados"); break;
		case 3: setStatusCode("08E0300","idGrupo esta nulo"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implGrpImnRelaciona::Execute()");
	return;
}