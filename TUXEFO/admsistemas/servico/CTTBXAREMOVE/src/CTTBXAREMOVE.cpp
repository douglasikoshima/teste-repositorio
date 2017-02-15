/*****************************************************************************
 *
 * Modulo:    CTTBXAREMOVE
 * Arquivo:   CTTBXAREMOVE.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define CTTBXAREMOVECPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CTTBXAREMOVE);

/**************************************************************************
 *  Funcao de Negocios:  CTTBXAREMOVE
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
void implCTTBXAREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	setStatusCode("14I0000","Operacao concluida com sucesso!");
}
