/*****************************************************************************
 *
 * Modulo:    PrfUniRelaciona
 * Arquivo:   PrfUniRelaciona.cpp
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
#define PrfUniRelacionaCPP

//Header de Prf de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPrf.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PRFUNIRELACIO);

/**************************************************************************
 *  Funcao de Negocios:  PrfUniRelaciona
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
void implPRFUNIRELACIO::Execute(DOMNode*dnode,XMLGen*xml_g)
{ 
	ULOG_START("implPRFUNIRELACIO::Execute()");
	/* Chamada de Funcao de Negocios */
	CPrf oPrf;
	switch (oPrf.PrfUniRelaciona(dnode,xml_g, getUser())) {
		case 0:	oPrf.PrfUniRelacao(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idPerfil esta nulo"); break;
		case 2: setStatusCode("08E0200","idPagina esta nulo"); break;
		case 3: setStatusCode("08W0100","Unidade com depência ou chave primária foi ferida"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implPRFUNIRELACIO::Execute()");
	return;
}
