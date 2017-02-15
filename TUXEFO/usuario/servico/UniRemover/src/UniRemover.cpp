/*****************************************************************************
 *
 * Modulo:    UniRemover
 * Arquivo:   UniRemover.cpp
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
#define UniRemoverCPP

//Header de Uni de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CUni.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(UniRemover);

/**************************************************************************
 *  Funcao de Negocios:  UniRemover
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
void implUniRemover::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUniRemover::Execute()");
	/* Chamada de Funcao de Negocios */
	CUni oUni;
	switch (oUni.UniRemover(dnode,xml_g, getUser())) {
		case 0:	oUni.UniListaIdUni(dnode,xml_g, "-1" ); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idUnidade esta nulo"); break;
		case 2: setStatusCode("08E0200","idPagina esta nulo"); break;
		case 3: oUni.UniListaIdUni(dnode,xml_g, "-1" ); setStatusCode("08W0300","Esta unidade tem dependência com PERFIL"); break;
		case 4: oUni.UniListaIdUni(dnode,xml_g, "-1" ); setStatusCode("08W0400","Esta unidade tem dependência com GRUPAMENTO"); break;
		//Este case precisa de retornar "E0001", porque a tela java transforma este erro em warning
		case 5: oUni.UniListaIdUni(dnode,xml_g, "-1" ); setStatusCode("08E0001","Esta unidade tem dependências fora da administração de usuário"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implUniRemover::Execute()");
	return;
}
