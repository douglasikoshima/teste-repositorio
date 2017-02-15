/*****************************************************************************
 *
 * Modulo:    SubRemover
 * Arquivo:   SubRemover.cpp
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
#define SubRemoverCPP

//Header de Sub de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CSub.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SubRemover);

/**************************************************************************
 *  Funcao de Negocios:  SubRemover
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
void implSubRemover::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implSubRemover::Execute()");
	/* Chamada de Funcao de Negocios */
	CSub oSub;
	char* cidUser = getUser();
	switch (oSub.SubRemover(dnode,xml_g, cidUser)) {
		case 0:	oSub.SubLista(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Erro ao excluir registros."); break;
		case 2: setStatusCode("08E0200","idSubSistema inválido."); break;
		case 3: setStatusCode("08E0300","idSubSistema está nulo."); break;
		case 4: setStatusCode("08E0400","Servico executado com falha."); break;
		case 5: oSub.SubLista(dnode,xml_g); setStatusCode("08W0001","Este registro não pode ser apagado, pois existem dependências fora do modelo de ACESSO"); break;
		case 6: oSub.SubLista(dnode,xml_g); setStatusCode("08W0002","Este registro não pode ser apagado, pois existem dependências com MENU."); break;
		case 7: oSub.SubLista(dnode,xml_g); setStatusCode("08W0003","Este registro não pode ser apagado, pois existem dependências com PÁGINAS."); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implSubRemover::Execute()");
}
