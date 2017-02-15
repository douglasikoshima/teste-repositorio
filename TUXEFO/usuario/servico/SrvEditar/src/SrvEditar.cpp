/*****************************************************************************
 *
 * Modulo:    SrvEditar
 * Arquivo:   SrvEditar.cpp
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
#define SrvEditarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSrv.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SrvEditar);

/**************************************************************************
 *  Funcao de Negocios:  SrvEditar
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
void implSrvEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implSrvEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CSrv oSrv;
	char* cidUser = getUser();
	switch (oSrv.SrvEditar(dnode,xml_g, cidUser)) {
		case 0:	oSrv.SrvListaId(oSrv.getIdHost(), xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idSistema esta nulo."); break;
		case 2: setStatusCode("08E0200","dsSistema esta nulo."); break;
		case 3: setStatusCode("08W0300","Ja existe esta descricao, tente novamente com outra diferente."); break;
		case 4: setStatusCode("08E0400","N�o foi poss�vel editar o servidor"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implSrvEditar::Execute()");
	return;
}