/*****************************************************************************
 *
 * Modulo:    BxaListar
 * Arquivo:   BxaListar.cpp
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
#define BxaListarCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../include/CBxaListar.h"
#include "../include/CBxaListarMsg.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXALSTTESTE);

/**************************************************************************
 *  Funcao de Negocios:  BxaListar
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
void implBXALSTTESTE::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXALSTTESTE::Execute()");
	// Chamada de Funcao de Negocios
	CBaixa oBaixa;
	CBaixaMensagem oBaixaMensagem;

    char idBaixaWrk[ 23 ];

	int    iNivel;
	int    iContTag;
	int    idTpComunicAux = 0;
    int    idContatoAux = 0;
    int    idBaixaAux = 0;

    char *cidBaixa = walkTree( dnode, "idBaixa", 0 );
    if ( cidBaixa )
    {
        idBaixaAux = atoi(cidBaixa);
    }

    char *cidContato = walkTree( dnode, "idContato", 0 );
    if ( cidContato )
    {
        idContatoAux = atoi(cidContato);
    }

    char *cidTipoComunic =  walkTree( dnode, "idTipoComunicacao", 0 );
    if ( cidTipoComunic )
    {
        idTpComunicAux = atoi(cidTipoComunic);
    }
    
	if ( idBaixaAux == 0 )
	{
	   if ( idContatoAux == 0 )
 	      oBaixa.ListChildrenSemContat();
	   else
          // oBaixa.ListOnlyParent( cidContato );
 	      oBaixa.ListBxaComContatos( cidContato );
    }
	else
	{
	   if ( idContatoAux == 0 )
	      oBaixa.ListChildrenIdBaixa( cidBaixa );
	   else
 	      oBaixa.ListBxa( cidBaixa, cidContato );
          // oBaixa.ListChildren( cidBaixa, cidContato );
	}

	if( oBaixa.Quantidade() > 0 )
	{
		iNivel   = 0;
		iContTag = 0;
		
		//Monta o menu raiz
		xml_g->createTag("AdmFolhaBaixaVO");
		xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

      if ( idBaixaAux > 0 )
         xml_g->createTag("AdmFolhaBaixaVO");

            xml_g->addItem("idBaixa", oBaixa.Registro(0)->cidBaixa );
      		xml_g->addItem("idNomeBaixa", oBaixa.Registro(0)->cidNomeBaixa );
      		xml_g->addItem("idBaixaPai", oBaixa.Registro(0)->cidBaixaPai );
      		xml_g->addItem("nmBaixa", oBaixa.Registro(0)->cnmBaixa );
      		xml_g->addItem("nrNivel", oBaixa.Registro(0)->iLevel );
		
      if ( idBaixaAux > 0 )
			xml_g->closeTag();

         //O laco continua apos o raiz
		for( int x = 1; x < oBaixa.Quantidade(); x++ )
		{
			if( oBaixa.Registro( x ) != NULL )
			{

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
               strcpy( idBaixaWrk,oBaixa.Registro(x)->cidBaixa );

					xml_g->addItem("idNomeBaixa", oBaixa.Registro(x)->cidNomeBaixa );
					xml_g->addItem("idBaixaPai", oBaixa.Registro(x)->cidBaixaPai );
					xml_g->addItem("nmBaixa", oBaixa.Registro(x)->cnmBaixa );
					xml_g->addItem("nrNivel", oBaixa.Registro(x)->iLevel );
					
					//Monta as mensagens
                    if ( idTpComunicAux > 0 )
                    {
                        oBaixaMensagem.ListTpComunicWFSel( idBaixaWrk, idTpComunicAux );
                        if( oBaixaMensagem.Quantidade() > 0 )
   	                        oBaixaMensagem.GetXmlTpComunicWF( "AdmMensagemBaixaVO", "AdmComunicacaoBaixaVO", "AdmMensagemAvisoVO", xml_g );
                    }
                    else
                    {
                        oBaixaMensagem.ListTpComunicWF( idBaixaWrk );
                        if( oBaixaMensagem.Quantidade() > 0 )
                            oBaixaMensagem.GetXmlTpComunicWF( "AdmMensagemBaixaVO", "AdmComunicacaoBaixaVO", "AdmMensagemAvisoVO", xml_g );
                    }
				   
				}//if( oBaixaFiltro.TemFiltro( Registro(x)->cidContato ) )
			}// if( Registro( x ) != NULL )
		}// for( int x = 0; x < Quantidade(); x++ )
		// Fecha todas as tags
		while( iContTag > 0 )
		{
			xml_g->closeTag();
			iContTag--;
		}
		setStatusCode("00I0000","Operacao concluida com sucesso!");
	}// if( oBaixa.Quantidade() > 0 )
	else
   {
		xml_g->createTag("AdmFolhaBaixaVO");
		xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
   	xml_g->closeTag();
		setStatusCode("00I0000","Sem dados para retorno");
   }

    if ( cidBaixa )
    {
        XMLString::release(&cidBaixa);
    }

    if ( cidContato )
    {
        XMLString::release(&cidBaixa);
    }

    if ( cidTipoComunic )
    {
        XMLString::release(&cidTipoComunic);
    }
    ULOG_END("implBXALSTTESTE::Execute()");    
}
