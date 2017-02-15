/*****************************************************************************
 *
 * Modulo:    PrfEditar
 * Arquivo:   PrfEditar.cpp
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
#define PrfEditarCPP

//Header de Prf de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPrf.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PrfEditar);

/**************************************************************************
 *  Funcao de Negocios:  PrfEditar
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
void implPrfEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPrfEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CPrf oPrf;
	switch (oPrf.PrfEditar(dnode,xml_g, getUser())) {
		case 0:	oPrf.PrfListaId(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idPerfil esta nulo"); break;
		case 2: setStatusCode("08E0200","dsPerfil esta nulo"); break;
		case 3: setStatusCode("08E0300","idPerfil invalido"); break;
		case 4: setStatusCode("08E0400","Problemas na insercao de perfil"); break;
		case 5: setStatusCode("08W0500","O nome passado como parametro ja existe na base"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implPrfEditar::Execute()");
	return;
}
