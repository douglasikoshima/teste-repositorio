/*****************************************************************************
 *
 * Modulo:    ImnEditar
 * Arquivo:   ImnEditar.cpp
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
#define ImnEditarCPP

//Header de Imn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CImn.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ImnEditar);

/**************************************************************************
 *  Funcao de Negocios:  ImnEditar
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
void implImnEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implImnEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CImn oImn;
	char* cidUser = getUser();
	switch (oImn.ImnEditar(dnode,xml_g, cidUser)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Erro na query (ORACLE)"); break;
		case 2: setStatusCode("08E0200","nmMenu esta nulo"); break;
		case 3: setStatusCode("08E0300","dsHint esta nulo"); break;
		case 4: setStatusCode("08E0400","sqApresentacao esta nulo"); break;
		case 5: setStatusCode("08E0500","inVisibilidade esta nulo"); break;
		case 6: setStatusCode("08E0600","idItemMenu esta nulo"); break;
		case 7: setStatusCode("08W0100","Este nome de menu ja existe para o subsistema selecionado"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implImnEditar::Execute()");
	return;
}
