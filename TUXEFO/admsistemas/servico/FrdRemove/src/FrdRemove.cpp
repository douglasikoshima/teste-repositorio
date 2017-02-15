/*****************************************************************************
 *
 * Modulo:    FrdRemove
 * Arquivo:   FrdRemove.cpp
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
#define FrdRemoveCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CFrd.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(FrdRemove);

/**************************************************************************
 *  Funcao de Negocios:  FrdRemove
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
void implFrdRemove::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implFrdRemove::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CFeriado oFeriado;
	DOMNode* oNodeFeriado = walkDOM( dnode, "AdmFeriadoVO", 0 );
	if( oNodeFeriado != NULL )
	{
	   
		char* cidFeriado = oSafePointer.getTag( oNodeFeriado, "idFeriado", 0 );
		
		if( strlennull( cidFeriado ) <= 0 )
		{
			setStatusCode("14E0000","idFeriado esta nulo");
			ULOG_END("implFrdRemove::Execute()");
			return;
		}
		switch( oFeriado.Delete( cidFeriado ) )
		{
			case 0:
				setStatusCode("14I0000","Operacao concluida com sucesso!");
				break;
			case 1:
				setStatusCode("14E0000","Falha na tentativa de DELETE");
				break;
			case 2:
				setStatusCode("14W0000","Feriado nao pode ser apagado, pois existe relacao");
				break;
			case 3:
				setStatusCode("14W0000","Somente datas futuras podem ser apagadas");
				break;
			default:
				setStatusCode("14E0000","Erro nao listado");
				break;
		}
	}
	else
		setStatusCode("14E0000","Nao foi encontrada a tag AdmFeriadoVO");
		
	ULOG_END("implFrdRemove::Execute()");
}
