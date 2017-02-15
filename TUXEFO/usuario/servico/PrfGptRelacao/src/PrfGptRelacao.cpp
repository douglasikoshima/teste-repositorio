/*****************************************************************************
 *
 * Modulo:    PrfGptRelacao
 * Arquivo:   PrfGptRelacao.cpp
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
#define PrfGptRelacaoCPP

//Header de Prf de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPrf.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PrfGptRelacao);

/**************************************************************************
 *  Funcao de Negocios:  PrfGptRelacao
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
void implPrfGptRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPrfGptRelacao::Execute()");
	/* Chamada de Funcao de Negocios */
	CPrf oPrf;
	switch (oPrf.PrfGptRelacao(dnode,xml_g)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 3: setStatusCode("08E0300","idPerfil esta nulo"); break;
		case 4: setStatusCode("08E0400","Erro na query, rollback foi executado."); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implPrfGptRelacao::Execute()");
	return;
}
