/*****************************************************************************
 *
 * Modulo:    SubEditar
 * Arquivo:   SubEditar.cpp
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
#define SubEditarCPP

//Header de Sub de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CSub.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SubEditar);

/**************************************************************************
 *  Funcao de Negocios:  SubEditar
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
void implSubEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implSubEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CSub oSub;
	switch (oSub.SubEditar(dnode,xml_g, getUser())) {
		case 0:	oSub.SubLista(dnode,xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idSubSistema esta nulo."); break;
		case 2: setStatusCode("08E0200","dsSubSistema esta nulo."); break;
		case 3: setStatusCode("08E0300","Erro ao executar o UPDATE."); break;
		case 4: setStatusCode("08E0400","Registro para UPDATE nao foi achado."); break;
		case 5: setStatusCode("08W0500","Nome passado como parametro ja existe na base"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implSubEditar::Execute()");
	return;
}
