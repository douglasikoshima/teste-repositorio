/*****************************************************************************
 *
 * Modulo:    UniEditar
 * Arquivo:   UniEditar.cpp
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
#define UniEditarCPP

//Header de Uni de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CUni.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(UniEditar);

/**************************************************************************
 *  Funcao de Negocios:  UniEditar
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
void implUniEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUniEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CUni oUni;
	switch (oUni.UniEditar(dnode,xml_g, getUser())) {
		case 0:	
			switch( oUni.UniListaId(dnode,xml_g) )
			{
				case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
				case 1: setStatusCode("08E0100","Erro na query, rollback foi executado."); break;
				default: setStatusCode("08E9999","Erro nao classificado."); break;
			}
			break;
		case 1: setStatusCode("08E0100","idUnidade esta nulo"); break;
		case 2: setStatusCode("08E0200","cdUnidade esta nulo"); break;
		case 3: setStatusCode("08E0300","nmUnidade esta nulo"); break;
		case 4: setStatusCode("08E0400","Problemas com a query"); break;
		case 5: setStatusCode("08W0500","Codigo duplicado, nao pode haver dois codigos iguais em todo o sistema"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implUniEditar::Execute()");
	return;
}
