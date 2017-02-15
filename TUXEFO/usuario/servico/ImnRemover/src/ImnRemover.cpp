/*****************************************************************************
 *
 * Modulo:    ImnRemover
 * Arquivo:   ImnRemover.cpp
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
#define ImnRemoverCPP

//Header de Imn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CImn.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(ImnRemover);

/**************************************************************************
 *  Funcao de Negocios:  ImnRemover
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
void implImnRemover::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implImnRemover::Execute()");
	/* Chamada de Funcao de Negocios */
	CImn oImn;
	switch (oImn.ImnRemover(dnode,xml_g, getUser())) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","idItemMenu est� nulo"); break;
		case 2: setStatusCode("08W0200","Uma raiz n�o pode ser apagada"); break;
		case 3: setStatusCode("08W0300","N�o se pode apagar um item com filhos"); break;
		case 4: setStatusCode("08E0001","Este registro n�o pode ser apagado, pois cont�m depend�ncias"); break;//Este erro tem que ser 1
		case 5: setStatusCode("08W0400","Este item est� relacionado � grupo(s), n�o pode ser apagado"); break;
		case 6: setStatusCode("08W0500","Este item est� relacionado � usu�rio(s), n�o pode ser apagado"); break;
		default: setStatusCode("08E9999","Erro n�o classificado."); break;
	}
	ULOG_END("implImnRemover::Execute()");
	return;
}
