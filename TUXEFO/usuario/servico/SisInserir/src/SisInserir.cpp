/*****************************************************************************
 *
 * Modulo:    SisInserir
 * Arquivo:   SisInserir.cpp
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
#define SisInserirCPP

//Header de Sis de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CSis.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SisInserir);

/**************************************************************************
 *  Funcao de Negocios:  SisInserir
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
void implSisInserir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implSisInserir::Execute()");
	/* Chamada de Funcao de Negocios */
	CSis oSis;
	switch (oSis.SisInserir(dnode,xml_g, getUser())) {
		case 0:	oSis.SisLista(dnode,xml_g); setStatusCode("0EI0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idSistema esta nulo"); break;
		case 2: setStatusCode("08E0200","sgSistema esta nulo"); break;
		case 3: setStatusCode("08E0300","dsSistema esta nulo"); break;
		case 4: setStatusCode("08E0400","nmUrlBase esta nulo"); break;
		case 5: setStatusCode("08E0500","inAcessoControlado esta nulo"); break;
		case 6: setStatusCode("08W0600","O nome passado como parametro ja existe na base"); break;
		case 7: setStatusCode("08W0700","A sigla passada como parametro ja existe na base"); break;
		case 8: setStatusCode("08W0800","Falha na insercao"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implSisInserir::Execute()");
	return;
}
