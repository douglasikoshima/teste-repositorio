/*****************************************************************************
 *
 * Modulo:    GptSisLista
 * Arquivo:   GptSisLista.cpp
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
#define GptListaCPP

//Header de Gpt de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CGpt.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GptSisLista);

/**************************************************************************
 *  Funcao de Negocios:  GptSisLista
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
void implGptSisLista::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implGptSisLista::Execute()");
	/* Chamada de Funcao de Negocios */
	CGpt oGpt;
	switch (oGpt.GptSisLista(dnode,xml_g)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implGptSisLista::Execute()");
	return;
}
