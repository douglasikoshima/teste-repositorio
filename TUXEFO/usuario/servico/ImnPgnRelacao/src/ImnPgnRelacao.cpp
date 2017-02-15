/*****************************************************************************
 *
 * Modulo:    ImnPgnRelacao
 * Arquivo:   ImnPgnRelacao.cpp
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
#define ImnPgnRelacaoCPP

//Header de Imn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CImn.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ImnPgnRelacao);

/**************************************************************************
 *  Funcao de Negocios:  ImnPgnRelacao
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
void implImnPgnRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implImnPgnRelacao::Execute()");
	/* Chamada de Funcao de Negocios */
	CImn oImn;
	switch (oImn.ImnPgnRelacao(dnode,xml_g)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Erro ao excluir registros."); break;
		case 2: setStatusCode("08E0200","idGrupo esta nulo"); break;
		case 3: setStatusCode("08E0300","Erro de paramentro invalido, insira um valor na tag."); break;
		case 4: setStatusCode("08E0400","Erro na query, rollback foi executado."); break;
		case 5: setStatusCode("08E0500","Erro no minimo uma tag requerida deve existir."); break;
		case 6: setStatusCode("08E0600","Erro gerou exception no middleware ou banco de dados."); break;
		case 7: setStatusCode("08I0700","Execucao com sucesso"); break;
		case 8: setStatusCode("08E0800","Menu inexistente"); break;
		case 9: setStatusCode("08E0900","Problemas ao tentar abir o cursor do oracle"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
    ULOG_END("implImnPgnRelacao::Execute()");
	return;
}