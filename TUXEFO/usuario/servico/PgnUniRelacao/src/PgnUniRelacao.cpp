/*****************************************************************************
 *
 * Modulo:    PgnUniRelacao
 * Arquivo:   PgnUniRelacao.cpp
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
#define PgnUniRelacaoCPP

//Header de Pgn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PgnUniRelacao);

/**************************************************************************
 *  Funcao de Negocios:  PgnUniRelacao
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
void implPgnUniRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPgnUniRelacao::Execute()");
	/* Chamada de Funcao de Negocios */
	CPgn oPgn;
	switch (oPgn.PgnUniRelacao(dnode,xml_g)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Erro ao excluir registros."); break;
		case 2: setStatusCode("08E0200","cidPerfil esta nulo"); break;
		case 3: setStatusCode("08E0300","cidPagina esta nulo"); break;
		case 4: setStatusCode("08E0400","Erro na query, rollback foi executado."); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implPgnUniRelacao::Execute()");
	return;
}
