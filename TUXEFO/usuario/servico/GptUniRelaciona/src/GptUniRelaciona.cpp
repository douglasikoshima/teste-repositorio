/*****************************************************************************
 *
 * Modulo:    GptUniRelaciona
 * Arquivo:   GptUniRelaciona.cpp
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
#define GptUniRelacionaCPP

//Header de Gpt de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CGpt.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GptUniRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  GptUniRelaciona
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
void implGptUniRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implGptUniRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	CGpt oGpt;
	switch (oGpt.GptUniRelaciona(dnode,xml_g, getUser())) {
		case 0:	oGpt.GptUniRelacao(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Falha ao tentar apagar a relacao"); break;
		case 2: setStatusCode("08E0200","Erro ao tentar inserir a relacao"); break;
		case 3: setStatusCode("08E0300","idGrupamento esta nul"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implGptUniRelaciona::Execute()");
	return;
}
