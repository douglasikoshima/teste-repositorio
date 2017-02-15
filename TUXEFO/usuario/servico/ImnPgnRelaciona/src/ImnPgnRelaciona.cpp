/*****************************************************************************
 *
 * Modulo:    ImnPgnRelaciona
 * Arquivo:   ImnPgnRelaciona.cpp
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
#define ImnPgnRelacionaCPP

//Header de Imn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CImn.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ImnPgnRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  ImnPgnRelaciona
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
void implImnPgnRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implImnPgnRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	CImn oImn;
	switch (oImn.ImnPgnRelaciona(dnode,xml_g, getUser())) {
		case 0:	oImn.ImnPgnRelacao(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Falha ao tentar limpar as paginas relacionadas"); break;
		case 2: setStatusCode("08E0200","Falha ao tentar inserir a pagina relacionada"); break;
		case 3: setStatusCode("08E0300","idItemMenu esta nulo"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implImnPgnRelaciona::Execute()");
	return;
}
