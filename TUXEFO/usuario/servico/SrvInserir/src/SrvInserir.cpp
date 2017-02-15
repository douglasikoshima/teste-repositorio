/*****************************************************************************
 *
 * Modulo:    SrvInserir
 * Arquivo:   SrvInserir.cpp
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
#define SrvInserirCPP

//Header de Srv de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CSrv.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SrvInserir);

/**************************************************************************
 *  Funcao de Negocios:  SrvInserir
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
void implSrvInserir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implSrvInserir::Execute()");
	/* Chamada de Funcao de Negocios */
	CSrv oSrv;
	switch (oSrv.SrvInserir(dnode,xml_g, getUser())) {
		case 0:	oSrv.SrvListaId( oSrv.getIdHost(),xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idSistema esta nulo."); break;
		case 2: setStatusCode("08E0200","dsSistema esta nulo."); break;
		case 3: setStatusCode("08W0300","Ja existe esta descricao, tente novamente com outra diferente."); break;
		case 4: setStatusCode("08E0400","Problemas com QUERY no ORACLE."); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implSrvInserir::Execute()");
	return;
}
