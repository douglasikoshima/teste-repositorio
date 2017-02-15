/*****************************************************************************
 *
 * Modulo:    USRREMOVE
 * Arquivo:   USRREMOVE.cpp
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
#define USRREMOVECPP

//Header de Usr de Infra-Estrutura
#include<tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(USRREMOVE);

/**************************************************************************
 *  Funcao de Negocios:  USRREMOVE
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
void implUSRREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	setStatusCode("08I0000","Execucao com sucesso");
}
