/*****************************************************************************
 *
 * Modulo:    CttBxaRelacao
 * Arquivo:   CttBxaRelacao.cpp
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
#define CttBxaRelacaoCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxa.h"

//Macro de Definicao do Framework Tuxedo
 DECLARE_TUXEDO_SERVICE(CttBxaRelacao);

/**************************************************************************
 *  Funcao de Negocios:  CttBxaRelacao
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
void implCttBxaRelacao::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCTTATIVAR::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixa oBaixa;
	int    iNivel;
	int    iContTag;
	char*  cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char*  cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );

	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0000","idContato está nulo");
		ULOG_END("implCTTATIVAR::Execute()");
		return;
	}

	xml_g->createTag("AdmArvoreBaixaContainerVO");
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

	oBaixa.RelacaoBxaCtt( cidContato, cidBaixa );

	if( oBaixa.Quantidade() > 0 )
	{
		iNivel   = 0;
		iContTag = 0;
		
		//Monta o menu raiz
		xml_g->createTag("AdmFolhaBaixaVO");
		xml_g->addItem("idBaixa", oBaixa.Registro(0)->cidBaixa );
		xml_g->addItem("idNomeBaixa", oBaixa.Registro(0)->cidNomeBaixa );
		xml_g->addItem("idBaixaPai", oBaixa.Registro(0)->cidBaixaPai );
		xml_g->addItem("nmBaixa", oBaixa.Registro(0)->cnmBaixa );
		xml_g->addItem("nrNivel", oBaixa.Registro(0)->iLevel );
		xml_g->addItem("dsPath", oBaixa.Registro(0)->cdsPath );
		xml_g->addItem("inDisponibilidade", oBaixa.Registro(0)->cinDisponibilidade );
		xml_g->addItem("inFolha", oBaixa.isFolha( oBaixa.Registro(0)->cidBaixa ) );
		
		//O laco continua apos o raiz
		for( int x = 1; x < oBaixa.Quantidade(); x++ )
		{
			if( oBaixa.Registro( x ) != NULL )
			{

				if( oBaixa.Registro(x)->iLevel > iNivel )
				{
					iNivel = oBaixa.Registro(x)->iLevel;
					xml_g->createTag("AdmFolhaBaixaVO");
					iContTag++;
				}//if( Registro(x)->iLevel > iNivel )
				else
				{
					xml_g->closeTag();
					if( oBaixa.Registro(x)->iLevel < iNivel )
					{
						while( iNivel > oBaixa.Registro(x)->iLevel )
						{
							xml_g->closeTag();
							iNivel--;
						}
						iNivel = oBaixa.Registro(x)->iLevel;
					}
					xml_g->createTag("AdmFolhaBaixaVO");
				}// else if( Registro(x)->iLevel > iNivel )

				xml_g->addItem("idBaixa", oBaixa.Registro(x)->cidBaixa );
				xml_g->addItem("idNomeBaixa", oBaixa.Registro(x)->cidNomeBaixa );
				xml_g->addItem("idBaixaPai", oBaixa.Registro(x)->cidBaixaPai );
				xml_g->addItem("nmBaixa", oBaixa.Registro(x)->cnmBaixa );
				xml_g->addItem("nrNivel", oBaixa.Registro(x)->iLevel );
				xml_g->addItem("dsPath", oBaixa.Registro(x)->cdsPath );
				xml_g->addItem("inDisponibilidade", oBaixa.Registro(x)->cinDisponibilidade );
				xml_g->addItem("inFolha", oBaixa.isFolha( oBaixa.Registro(x)->cidBaixa ) );

			}// if( Registro( x ) != NULL )
		}// for( int x = 0; x < Quantidade(); x++ )
		// Fecha todas as tags
		while( iContTag > 0 )
		{
			xml_g->closeTag();
			iContTag--;
		}
	}// if( oBaixa.Quantidade() > 0 )
	
	xml_g->closeTag();//AdmArvoreBaixaContainerVO

	setStatusCode("14I0000","Operação concluída com sucesso!");
	ULOG_END("implCTTATIVAR::Execute()");
}
