/*****************************************************************************
 *
 * Modulo:    UsrImnRelaciona
 * Arquivo:   UsrImnRelaciona.cpp
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
#define UsrImnRelacionaCPP

//Header de Usr de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CUsr.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(UsrImnRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  UsrImnRelaciona
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
void implUsrImnRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUsrImnRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	CUsr oUsr;
	switch (oUsr.UsrImnRelaciona(dnode,xml_g, getUser())) {
		case 0:	oUsr.UsrImnRelacao(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Falha ao tentar limpar os grupos relacionados"); break;
		case 2: setStatusCode("08E0200","Erro ao tentar inserir item de menu relacionado"); break;
		case 3: setStatusCode("08E0300","idUsuario esta nulo"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implUsrImnRelaciona::Execute()");
	return;
}
