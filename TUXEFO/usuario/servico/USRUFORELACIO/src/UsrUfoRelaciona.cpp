/*****************************************************************************
 *
 * Modulo:    UsrUfoRelaciona
 * Arquivo:   UsrUfoRelaciona.cpp
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
#define UsrUfoRelacionaCPP

//Header de Usr de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CUsr.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(USRUFORELACIO);

/**************************************************************************
 *  Funcao de Negocios:  UsrUfoRelaciona
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
void implUSRUFORELACIO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUSRUFORELACIO::Execute()");
	
	/* Chamada de Funcao de Negocios */
	CUsr oUsr;
	switch (oUsr.UsrUfoRelaciona(dnode,xml_g, getUser())) {
		case 0:	oUsr.UsrUfoRelacao(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Falha na exclusao de UFOperadoras relacionadas"); break;
		case 2: setStatusCode("08E0200","Erro na insecao de UFOperadora"); break;
		case 3: setStatusCode("08E0300","idUsuario esta nulo"); break;
		case 4: setStatusCode("08W0400","Somente usuário DESLIGADO pode ficar sem regionais associadas."); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implUSRUFORELACIO::Execute()");
	return;
}
