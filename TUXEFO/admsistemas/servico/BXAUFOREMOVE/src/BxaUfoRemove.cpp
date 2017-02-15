/*****************************************************************************
 *
 * Modulo:    BXAUFOREMOVE
 * Arquivo:   BXAUFOREMOVE.cpp
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
#define BXAUFOREMOVECPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxaUfo.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXAUFOREMOVE);

/**************************************************************************
 *  Funcao de Negocios:  BXAUFOREMOVE
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
void implBXAUFOREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXAUFOREMOVE::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixaUfoperadora oBaixaUfoperadora;
	char* cidBaixaUFOperadora = oSafePointer.getTag( dnode, "idBaixaUFOperadora", 0 );
	char* cidUser = getUser();
	
	if( strlennull( cidBaixaUFOperadora ) <= 0 )
	{
		setStatusCode("14E0001","idBaixaUFOperadora está nulo");
		return;
	}

	if( oBaixaUfoperadora.Delete( cidBaixaUFOperadora ) )
		setStatusCode("14I0000","Serviço executado com sucesso");
	else
		setStatusCode("14E0001","Falha ao tentar apagar o idBaixaUFOperadora");
		
	ULOG_END("implBXAUFOREMOVE::Execute()");
}
