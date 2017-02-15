/*****************************************************************************
 *
 * Modulo:    ImnInserir
 * Arquivo:   ImnInserir.cpp
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
#define ImnInserirCPP

//Header de Imn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CImn.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ImnInserir);

/**************************************************************************
 *  Funcao de Negocios:  ImnInserir
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
void implImnInserir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implImnInserir::Execute()");
	/* Chamada de Funcao de Negocios */
	CImn oImn;
	char* cidUser = getUser();
	switch (oImn.ImnInserir(dnode,xml_g, cidUser)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","nmMenu esta nulo"); break;
		case 2: setStatusCode("08E0200","idSubSistema esta nulo"); break;
		case 3: setStatusCode("08E0300","dsHint esta nulo"); break;
		case 4: setStatusCode("08E0400","inVisibilidade esta nulo"); break;
		case 5: setStatusCode("08E0500","Sub-sistema ja tem raiz"); break;
		case 6: setStatusCode("08E0600","Erro na query (erro ORACLE)"); break;
		case 7: setStatusCode("08W0700","O mesmo nome nao pode ser usado mais que uma vez por nivel"); break;
		case 8: setStatusCode("08W0800","Uma folha nao pode conter itens"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
    ULOG_END("implImnInserir::Execute()");
	return;
}
