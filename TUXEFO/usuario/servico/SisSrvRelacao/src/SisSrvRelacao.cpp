/*****************************************************************************
 *
 * Modulo:    SisSrvRelacao
 * Arquivo:   SisSrvRelacao.cpp
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
#define SisSrvRelacaoCPP

//Header de Sis de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CSis.h"
#include <tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SisSrvRelacao);

/**************************************************************************
 *  Funcao de Negocios:  SisSrvRelacao
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
void implSisSrvRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implSisSrvRelacao::Execute()");
	/* Chamada de Funcao de Negocios */
	CSis oSis;
	switch (oSis.SisSrvRelacao(dnode,xml_g)) {
		case 0:	setStatusCode("08I0000","Servico executado com sucesso."); break;
		case 1: setStatusCode("08E0100","Erro ao excluir registros."); break;
		case 2: setStatusCode("08E0200","Erro sem paramentro, necessario enviar a tag."); break;
		case 3: setStatusCode("08E0300","idSistema esta nulo"); break;
		case 4: setStatusCode("08E0400","Erro na query, rollback foi executado."); break;
		case 5: setStatusCode("08E0500","Erro no minimo uma tag requerida deve existir."); break;
		case 6: setStatusCode("08E0600","Erro gerou exception no middleware ou banco de dados."); break;
		case 7: setStatusCode("08I0700","Execucao com sucesso"); break;
		case 8: setStatusCode("08E0800","Menu inexistente"); break;
		case 9: setStatusCode("08E0900","Problemas ao tentar abir o cursor do oracle"); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implSisSrvRelacao::Execute()");
	return;
}