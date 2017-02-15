/*****************************************************************************
 *
 * Modulo:    GrpEditar
 * Arquivo:   GrpEditar.cpp
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
#define GrpEditarCPP

//Header de Grp de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CGrp.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GrpEditar);

/**************************************************************************
 *  Funcao de Negocios:  GrpEditar
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
void implGrpEditar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implGrpEditar::Execute()");
	/* Chamada de Funcao de Negocios */
	CGrp oGrp;
	switch (oGrp.GrpEditar(dnode,xml_g, getUser())) {
		case 0:	oGrp.getXml("GruposUsuarioVO", "GrupoUsuarioVO", xml_g); setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idGrupo está nulo"); break;
		case 2: setStatusCode("08E0200","dsGrupo está nulo"); break;
		case 3: setStatusCode("08E0300","idGrupo inválido"); break;
		case 4: setStatusCode("08W0400","O nome passado como parâmetro já existe na base"); break;
		case 5: setStatusCode("08W0500","Nome editado com sucesso, mas o TIPO não pode ser alterado porque tem atendimento."); break;
		case 6: setStatusCode("08W0600","Operação realizada com sucesso. Todas as relações destes grupo com perfil de contato e linha(s) foram apagadas."); break;
		case 7: setStatusCode("08W0700","Nome editado com sucesso, mas o TIPO não pode ser alterado porque existe contato associado."); break;
		case 8: setStatusCode("08W0800","Nome editado com sucesso, mas o TIPO não pode ser alterado porque existe perfil associado."); break;
		case 9: setStatusCode("08W0900","Nome editado com sucesso, mas o TIPO não pode ser alterado porque existe linha telefônica associada."); break;
		default: setStatusCode("08E9999","Erro não classificado."); break;
	}
	ULOG_END("implGrpEditar::Execute()");
	return;
}
