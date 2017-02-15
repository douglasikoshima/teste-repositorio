/*****************************************************************************
 *
 * Modulo:    PrfRemover
 * Arquivo:   PrfRemover.cpp
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
#define PrfRemoverCPP

//Header de Prf de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPrf.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PrfRemover);

/**************************************************************************
 *  Funcao de Negocios:  PrfRemover
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
void implPrfRemover::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPrfRemover::Execute()");
	/* Chamada de Funcao de Negocios */
	CPrf oPrf;
	switch (oPrf.PrfRemover(dnode,xml_g, getUser())) {
		case 0:  oPrf.PrfLista(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1:  setStatusCode("08E0100","Erro ao excluir registros."); break;
		case 2:  setStatusCode("08E0200","idPerfil inválido"); break;
		case 3:  setStatusCode("08E0300","idPerfil está nulo"); break;
		case 4:  setStatusCode("08E0400","Erro na query, rollback foi executado."); break;
		case 5:  setStatusCode("08W0500","Este registro não pode ser apagado, há relacionamentos com usuário e/ou grupo"); break;
		default: setStatusCode("08E9999","Erro não classificado."); break;
	}
	ULOG_END("implPrfRemover::Execute()");
	return;
}
