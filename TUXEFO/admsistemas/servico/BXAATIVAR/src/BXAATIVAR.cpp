/*****************************************************************************
 *
 * Modulo:    BXAATIVAR
 * Arquivo:   BXAATIVAR.cpp
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
#define BXAATIVARCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxa.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXAATIVAR);

/**************************************************************************
 *  Funcao de Negocios:  BXAATIVAR
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
void implBXAATIVAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXAATIVAR::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixa oBaixa;
	char* cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	char* cinDisponibilidade = oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	if( strlennull( cidBaixa ) <= 0 )
	{
		setStatusCode("14E0001","idBaixa está nulo");
		ULOG_END("implBXAATIVAR::Execute()");
		return;
	}

	xml_g->createTag("AdmAtualizacaoArvoreBaixaVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");

	switch( oBaixa.ativaInativaArvoreUP1( cidBaixa, 
						               cinDisponibilidade, xml_g ) )
	{
		case 0: setStatusCode("14I0000", "Operação realizada com sucesso" );break;
		case 1: setStatusCode("14E0002", "idBaixa está nulo" );xml_g->addItem("idBaixa", "0");break;
		case 2: setStatusCode("14E0003", "Baixa não foi achada, idBaixa está errada" );xml_g->addItem("idBaixa", "0");break;
		case 3: setStatusCode("14W0001", "A raiz não pode ser habilitada nem desabilitada" );xml_g->addItem("idBaixa", "0");break;
		case 4: setStatusCode("14W0002", "Esta operação não foi realizada, pois existem contatos associdados a esta baixa." );xml_g->addItem("idBaixa", "0");break;
		case 5: setStatusCode("14W0003", "Uma baixa com pai desabilitado não pode ser habilitada" );xml_g->addItem("idBaixa", "0");break;
		default: setStatusCode("14E9999", "Erro não listado" );xml_g->addItem("idBaixa", "0");break;
	}//switch

	xml_g->closeTag();

	ULOG_END("implBXAATIVAR::Execute()");
}
