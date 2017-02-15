/*****************************************************************************
 *
 * Modulo:    GrpInserir
 * Arquivo:   GrpInserir.cpp
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
#define GrpInserirCPP

//Header de Grp de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CGrp.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GrpInserir);

/**************************************************************************
 *  Funcao de Negocios:  GrpInserir
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
void implGrpInserir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implGrpInserir::Execute()");
	/* Chamada de Funcao de Negocios */
	CGrp oGrp;
	char* cidUser = getUser();
	switch (oGrp.GrpInserir(dnode,xml_g, cidUser))
	{
		case 0:	oGrp.getXml("GruposUsuarioVO", "GrupoUsuarioVO", xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idGrupo está nulo"); break;
		case 2: setStatusCode("08E0200","dsGrupo está nulo"); break;
		case 3: setStatusCode("08E0300","idGrupo inválido"); break;
		case 4: setStatusCode("08E0400","Falha na edição de grupo"); break;
		case 5: setStatusCode("08W0500","O nome passado como parâmetro já existe na base"); break;
		default: setStatusCode("08E9999","Erro não classificado."); break;
	}
	ULOG_END("implGrpInserir::Execute()");
	return;
}
