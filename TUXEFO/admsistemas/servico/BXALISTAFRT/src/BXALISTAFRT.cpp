/*****************************************************************************
 *
 * Modulo:    BXALISTAFRT
 * Arquivo:   BXALISTAFRT.cpp
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
#define BXALISTAFRTCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxa.h"
#include "../../../negocio/admatdCmm/include/CBxaMsg.h"
#include "../../../negocio/admatdCmm/include/CBxaAnt.h"
#include "../../../negocio/admatdCmm/include/CBxaNom.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXALISTAFRT);

/**************************************************************************
 *  Funcao de Negocios:  BXALISTAFRT
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
void implBXALISTAFRT::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXALISTAFRT::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixa oBaixa;
	CBaixaMensagem oBaixaMensagem;
	CIndicadorAnatelBaixa oIndicadorAnatelBaixaRel;
	CIndicadorAnatelBaixa oIndicadorAnatelBaixaNaoRel;
	CNomeBaixa oNomeBaixa;
	int    iNivel;
	int    iContTag;
	char*  cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	int    iidBaixa = strlennull( cidBaixa );

	xml_g->createTag("AdmArvoreBaixaContainerVO");
	xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
	xml_g->addComment( "Container completo para manutencao de arvore de baixa" );

	//Lista com todos os nomes de baixa
	if( oNomeBaixa.ListAll() > 0 )
		oNomeBaixa.GetXml( "AdmNomeBaixaVO", xml_g );

	//Lista com todos os tipos de comunicacao
	if( oBaixaMensagem.ListTipoComunicacao() > 0 )
		oBaixaMensagem.GetXmlTipoComunicacao( "AdmComunicacaoBaixaVO", xml_g );

	//Lista todas as descricoes de mensagens de baixa
		oBaixaMensagem.ListMensagemBaixa();
		oBaixaMensagem.GetXmlMensagemBaixa( "AdmMensagemAvisoVO", xml_g );

	if( iidBaixa )
		oBaixa.ListId( cidBaixa );
	else
		oBaixa.ListAll();
		//@cassio oBaixa.ListAll( cidBaixa );
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
		
		//Monsta a mensagem e o tipo de comunicacao
		oBaixaMensagem.ListIdBaixa( oBaixa.Registro(0)->cidBaixa );
		if( oBaixaMensagem.Quantidade() > 0 )
		{
			xml_g->addItem("inFolha", "1" );
			oBaixaMensagem.GetXml( "AdmMensagemBaixaVO", "AdmComunicacaoBaixaVO", "AdmMensagemAvisoVO", xml_g );
		}
		else
			xml_g->addItem("inFolha", "0" );

		if( iidBaixa )
		{

			//AdmIndicadoresAnatelContainerVO
			xml_g->createTag("AdmIndicadoresAnatelContainerVO");

				xml_g->addItem("idBaixa", oBaixa.Registro(0)->cidBaixa );
			
				xml_g->createTag("indicadoresAssociados");
					xml_g->addComment("Container de indicadores Anatel relacionados" );
					if( oIndicadorAnatelBaixaRel.ListIdBaixaRel( oBaixa.Registro(0)->cidBaixa ) > 0 )
						oIndicadorAnatelBaixaRel.GetXml( "AdmIndicadorAnatelVO", xml_g );
				xml_g->closeTag();//indicadoresAssociados

				xml_g->createTag("indicadoresExistentes");
					xml_g->addComment("Container de indicadores Anatel nao relacionados" );
					if( oIndicadorAnatelBaixaNaoRel.ListIdBaixaNaoRel( oBaixa.Registro(0)->cidBaixa ) > 0 )
						oIndicadorAnatelBaixaNaoRel.GetXml( "AdmIndicadorAnatelVO", xml_g );
				xml_g->closeTag();//indicadoresExistentes

			xml_g->closeTag();//AdmIndicadoresAnatelContainerVO

		}

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

				oBaixaMensagem.ListIdBaixa( oBaixa.Registro(x)->cidBaixa );
				if( oBaixaMensagem.Quantidade() > 0 )
					xml_g->addItem("inFolha", "1" );
				else
					xml_g->addItem("inFolha", "0" );

				if( iidBaixa )
				{
					//AdmIndicadoresAnatelContainerVO
					xml_g->createTag("AdmIndicadoresAnatelContainerVO");
						xml_g->addItem("idBaixa", oBaixa.Registro(x)->cidBaixa );
						
						xml_g->createTag("indicadoresAssociados");
							xml_g->addComment("Container de indicadores Anatel relacionados" );
							if( oIndicadorAnatelBaixaRel.ListIdBaixaRel( oBaixa.Registro(x)->cidBaixa ) > 0 )
								oIndicadorAnatelBaixaRel.GetXml( "AdmIndicadorAnatelVO", xml_g );
						xml_g->closeTag();//indicadoresAssociados

						xml_g->createTag("indicadoresExistentes");
							xml_g->addComment("Container de indicadores Anatel nao relacionados" );
							if( oIndicadorAnatelBaixaNaoRel.ListIdBaixaNaoRel( oBaixa.Registro(x)->cidBaixa ) > 0 )
								oIndicadorAnatelBaixaNaoRel.GetXml( "AdmIndicadorAnatelVO", xml_g );
						xml_g->closeTag();//indicadoresExistentes

					xml_g->closeTag();//AdmIndicadoresAnatelContainerVO

					//Monsta a mensagem e o tipo de comunicacao
					if( oBaixaMensagem.Quantidade() > 0 )
						oBaixaMensagem.GetXml( "AdmMensagemBaixaVO", "AdmComunicacaoBaixaVO", "AdmMensagemAvisoVO", xml_g );
				}
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

	setStatusCode("14I0000","Operacao concluida com sucesso!");
	
	ULOG_END("implBXALISTAFRT::Execute()");
}
