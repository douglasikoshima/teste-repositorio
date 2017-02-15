/*****************************************************************************
 *
 * Modulo:    GrpRemover
 * Arquivo:   GrpRemover.cpp
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
#define GrpRemoverCPP

//Header de Grp de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CGrp.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GrpRemover);

/**************************************************************************
 *  Funcao de Negocios:  GrpRemover
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
void implGrpRemover::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implGrpRemover::Execute()");
	/* Chamada de Funcao de Negocios */
	CGrp oGrp;
	char* cidUser = getUser();
	switch (oGrp.GrpRemover(dnode,xml_g, cidUser)) 
	{
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idGrupo est� nulo"); break;
		case 2: setStatusCode("08E0200","idGrupo inv�lido"); break;
		case 3: setStatusCode("08E0300","Grupo n�o existe"); break;
		case 4: setStatusCode("08W0100","Grupo pertence a um atendimento em andamento"); break;
		case 5: setStatusCode("08W0200","Este grupo n�o pode ser apagado, pois existem contatos configurados somente com este grupo"); break;
		case 6: setStatusCode("08E0001","Houve uma falha na exclus�o do grupo"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implGrpRemover::Execute()");
	return;
}
