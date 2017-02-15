/*****************************************************************************
 *
 * Modulo:    GptEditar
 * Arquivo:   GptEditar.cpp
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
#define GptEditarCPP

//Header de Gpt de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CGpt.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GptEditar);

/**************************************************************************
 *  Funcao de Negocios:  GptEditar
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
void implGptEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implGptEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CGpt oGpt;
	switch (oGpt.GptEditar(dnode,xml_g, getUser() ) ) {
		case 0:	oGpt.GptListaId(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idGrupamento esta nulo"); break;
		case 2: setStatusCode("08E0200","dsGrupamento esta nulo"); break;
		case 3: setStatusCode("08E0300","idGrupamento invalido"); break;
		case 4: setStatusCode("08E0400","Problemas na insercao de grupamento"); break;
		case 5: setStatusCode("08W0500","O nome passado como parametro ja existe na base"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implGptEditar::Execute()");
	return;
}
