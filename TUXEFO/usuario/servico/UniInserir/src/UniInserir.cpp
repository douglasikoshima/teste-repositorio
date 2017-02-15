/*****************************************************************************
 *
 * Modulo:    UniInserir
 * Arquivo:   UniInserir.cpp
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
#define UniInserirCPP

//Header de Uni de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CUni.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(UniInserir);

/**************************************************************************
 *  Funcao de Negocios:  UniInserir
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
void implUniInserir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUniInserir::Execute()");
	/* Chamada de Funcao de Negocios */
	CUni oUni;
	char cidUnidade[21+1];
	long iidUnidade = oUni.UniInserir(dnode,xml_g, getUser());
	switch(iidUnidade) {
		case -1: setStatusCode("08E0000","cdUnidade esta nula"); break;
		case -2: setStatusCode("08E0000","dsUnidade esta nula"); break;
		case -3: setStatusCode("08E0000","idPagina esta nula"); break;
		case -4: setStatusCode("08E0000","idPagina esta nula"); break;
		case -5: setStatusCode("08W0000","Codigo ja existe na base"); break;
		default: 
			{
				sprintf(cidUnidade, "%l", iidUnidade);
				switch (oUni.UniListaIdUni(dnode,xml_g, cidUnidade) ) {
					case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
					case 1: setStatusCode("08E0000","Erro na query, rollback foi executado."); break;
					default: setStatusCode("08E0000","Erro nao classificado."); break;
				}
			}
			break;
	}
	ULOG_END("implUniInserir::Execute()");
	return;
}
