/*****************************************************************************
 *
 * Modulo:    PgnUniRelaciona
 * Arquivo:   PgnUniRelaciona.cpp
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
#define PgnUniRelacionaCPP

//Header de Pgn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PgnUniRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  PgnUniRelaciona
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
void implPgnUniRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPgnUniRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	CPgn oPgn;
	switch (oPgn.PgnUniRelaciona(dnode,xml_g, getUser())) {
		case 0:	oPgn.PgnUniRelacao(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Falha ao tentar limpar as unidades relacionadas"); break;
		case 2: setStatusCode("08E0200","Falha na insercao de unidades relacionadas"); break;
		case 3: setStatusCode("08E0300","idPagina esta nula"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implPgnUniRelaciona::Execute()");
	return;
}
